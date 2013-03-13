(ns lindenmayer.client.turtle
  (:use [monet.canvas :only [save restore stroke-width stroke-cap stroke-style 
                             begin-path line-to stroke close-path transform]]))

;; Basic Turtle implementation
;; ---------------------------
;; Allows a seq of keywords to 'drive' a turtle on an HTML5 canvas:
;;
;;   (def commands '(:color :red, :fwd 50, :left 10, :fwd 45, :right 72)
;;   (draw! ctx commands [100 100])
;;
;; By default, the drawing is scaled to fill the screen dimensions
;; (in this case [100,100])

(def colors [:red :green :blue :yellow :cyan :magenta :black :white "#663300" "#68FF33"])

(defn- bounding-box 
  "Calculates the smallest and largest [x,y] points"
  [coords]
  (let [[min-x max-x] (apply (juxt min max) (map first coords))
        [min-y max-y] (apply (juxt min max) (map second coords))]
   [[min-x min-y] [max-x max-y]]))

(def radians (/ Math/PI 180.0))

(defn- deg->rad [theta]
  (* theta radians))

(defn- move-forward 
  "Given a state (containing a heading), move forward by the supplied
   distance."
  [state dist]
  (let [rad   (deg->rad (:heading state))
        [x y] (:coords state)]
    (assoc state :coords [ (+ x (* dist (Math/cos rad))) 
                           (+ y (* dist (Math/sin rad)))])))

(defn- turn 
  "Given a state, and an operation (either the + or - function),
   update such that the new heading is altered by the angle"
  [op state angle]
  (let [heading (:heading state)]
    (assoc state :heading (mod (op heading angle) 360))))

(defn- update-color [state color]
  (assoc state :color color))

(defn- color-index [index]
  (get colors index))

(defn- push-state [state _]
  state) ; TODO

(defn- pop-state [state _]
  state) ; TODO

(def state-mapper
  { :color   update-color 
    :color-index (comp update-color color-index) 
    :left    (partial turn -)
    :right   (partial turn +)
    :fwd     move-forward 
    :save    push-state
    :restore pop-state })

(defn- next-state 
  "Evolves the current state and a given command to determine the next state,
   e.g. if the current position is (4,3) pointing north, then move to (4,4)
   and turn in to the heading relative to the command."
  ([] [])
  ([curr-state] curr-state)
  ([curr-state [cmd & peek-ahead]]
    (if-let [update-fn (get state-mapper cmd nil)]
      ; always need stack/heading/coords, but not color
      (update-fn (dissoc curr-state :color) (first peek-ahead))
      curr-state)))

(defn- process [cmds]
  (let [init-state { :coords [0 0] :heading 0 :stack []}]
    (->>
      cmds
      (partition-all 2 1)
      (reductions next-state init-state))))

(defn- calc-matrix-transform 
  "Calculates an affine transform matrix which will scale a drawing 
   constrained by the min/max bounds to the given screen co-ords. Note
   that the drawing is flipped so (0,0) will be represented at (or near)
   the lower edge, not the upper edge."
  [[screen-x screen-y] [[min-x min-y] [max-x max-y]]]
  (let [scale-x (/ screen-x (- max-x min-x))
        scale-y (/ screen-y (- max-y min-y))
        scale   (min scale-x scale-y)]
    [ scale 0 0 (- scale) (- min-x) (* scale max-y) ])) 

(defn- draw-path-segments! [ctx data]
  (doseq [d data]
    (when-let [color (:color d)]
      (stroke-style ctx color))
    (apply line-to ctx (:coords d)))
  ctx) ; return the context for threading

(defn draw! [ctx cmds screen-area]
  (let [width    2.5
        data   (process (concat [:color :red] cmds))
        bounds (bounding-box (map :coords data))
        matrix (calc-matrix-transform screen-area bounds)]
    (.log js/console (pr-str "screen-area" screen-area))
    (.log js/console (pr-str "bounds" bounds))
    (.log js/console (pr-str "matrix" matrix))
    ;(.log js/console (pr-str "data" data))
    (->
      (apply transform ctx matrix)
      (stroke-width width)
      (stroke-cap :square)
      (begin-path)
      (draw-path-segments! data)
      (stroke)
      (close-path))))
