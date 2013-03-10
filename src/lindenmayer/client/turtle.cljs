(ns lindenmayer-systems.client.turtle
  (:use [monet.canvas :only [save restore stroke-width stroke-cap stroke-style 
                             begin-path line-to stroke close-path transform]]))

(def colors [:red :green :blue :yellow :cyan :magenta :black "#663300" "#68FF33"])

(defn- bounding-box 
  "Calculates the smallest and largest [x,y] points"
  [coords]
  (let [[min-x max-x] (apply (juxt min max) (map first coords))
        [min-y max-y] (apply (juxt min max) (map second coords))]
   [[min-x min-y] [max-x max-y]]))

(def radians (/ Math/PI 180.0))

(defn- deg->rad [theta]
  (* theta radians))

(defn- update-position [state cmd]
  (if (= cmd :fwd)
    (let [rad   (deg->rad (:heading state))
          dist  (:distance state) 
          [x y] (:coords state)]
      (assoc state :coords [ (+ x (* dist (Math/cos rad))) 
                             (+ y (* dist (Math/sin rad)))]))
    state))

(defn- update-heading [state cmd]
  (let [heading (:heading state)
        turn    (case cmd
                  :left  (- (:angle state))
                  :right (:angle state)
                  0)]
    (assoc state :heading (+ heading turn))))

(defn- update-color [state cmd & peek-ahead]
  (if (and (= cmd :color) (number? (first peek-ahead)))
    (assoc state :color-index peek-ahead)
    (dissoc state :color-index)))

(defn- update-stack [state cmd]
  state) ; TODO

(defn- next-state 
  "Evolves the current state and a given command to determine the next state,
   e.g. if the current position is (4,3) pointing north, then move to (4,4)
   and turn in to the heading relative to the command."
  ([] [])
  ([curr-state] curr-state)
  ([curr-state [cmd & peek-ahead]]
   (if (keyword? cmd)
     (->
       curr-state
       (update-position cmd) 
       (update-heading cmd) 
       (update-color cmd peek-ahead)
       (update-stack cmd)) 
     curr-state)))

(defn- process [color distance angle]
  (let [init-state { :coords [0 0] :heading 0 :color-index color :stack [] :distance distance :angle angle}]
    (fn [cmds]
      (->>
        cmds
        (partition-all 2 1)
        (reductions next-state init-state)))))

(defn- calc-matrix-transform [[screen-x screen-y] [[min-x min-y] [max-x max-y]]]
  (let [scale-x (/ screen-x (- max-x min-x))
        scale-y (/ screen-y (- max-y min-y))
        scale   (min scale-x scale-y)]
    [ scale 0 0 (- scale) (- min-x) (* scale max-y) ]  
    )) 

(defn- draw-path-segments! [ctx data]
  (doseq [d data]
    (when-let [index (:color-index d)]
      (stroke-style ctx (get colors index)))
    (apply line-to ctx (:coords d)))
  ctx) ; return the context for threading

(defn draw! [ctx turn-angle cmds available-area]
  (let [distance 10
        width    2.5
        color    0 ;(rand-int (count colors))
        data   ((process color distance turn-angle) cmds)
        bounds (bounding-box (map :coords data))
        matrix (calc-matrix-transform available-area bounds)]
    (.log js/console (pr-str "available-area" available-area))
    (.log js/console (pr-str "bounds" bounds))
    (.log js/console (pr-str "matrix" matrix))
    (->
      (apply transform ctx matrix)
      (stroke-width width)
      (stroke-cap :square)
      (stroke-style :red)
      (begin-path)
      (draw-path-segments! data)
      (stroke)
      (close-path))))


;(def s (nth sierpinski-seq 8))
;
;(def d (nth dragon-seq 13))
;
;((process 0 10 60) d)
;
;(def bbox (bounding-box (map :coords ((process 0 10 60) s)))) 
;
;bbox
;
;(calc-matrix-transform [1000 500] bbox)
;
;(* 60 12.5)
