(ns lindenmayer-systems.client.core
  (:use [monet.canvas :only [get-context save restore stroke-width stroke-cap stroke-style begin-path line-to stroke close-path ]]
        [jayq.core :only [$ document-ready data text attr hide bind prevent]]
        ))

(defn dragon 
  "Unfolding the dragon"
  [depth turn]
  (if (pos? depth)
    (let [next-depth (dec depth)
          left       (lazy-seq (dragon next-depth :left))
          right      (lazy-seq (dragon next-depth :right))]
      (concat
        right 
        (if turn 
          (cons turn left)
          left)))))

(defn move 
  "Destructures a state (comprising a pair of co-ordinates and 
   a direction) and moves one step in that direction"
  [[[x y] dir]]
  (case dir
    :north [x (inc y)]
    :south [x (dec y)]
    :east  [(inc x) y]
    :west  [(dec x) y]))

(def direction-mapper
  { :north { :left :west,  :right :east,  :fwd :north, :back :south }
    :south { :left :east,  :right :west,  :fwd :south, :back :north }
    :east  { :left :north, :right :south, :fwd :east,  :back :west  }
    :west  { :left :south, :right :north, :fwd :west,  :back :east  } })

(defn new-direction 
  "Destructures a state and a command, and works out the new direction,
   e.g. if direction is currently north, but the command is to turn left,
   then the new direction is east."
  [[_ dir] cmd]
  (get-in direction-mapper [dir cmd]))

(defn next-state 
  "Evolves the current state and a given command to determine the next state,
   e.g. if the current position is (4,3) pointing north, then move to (4,4)
   and turn in to the heading relative to the command."
  ([] [])
  ([curr-state] curr-state)
  ([curr-state cmd]
   [ (move curr-state) (new-direction curr-state cmd) ]))

(defn ->coords [transformer-fn]
  (let [init-state [[0 0] :north ]]
    (fn [folds]
      (->> folds
        (reductions next-state init-state)
        (map (comp transformer-fn first)))))) 

;; Co-ordinate transforming functions

(defn scale [z]
  (fn [coll] (map (partial * z) coll)))

(defn offset [a]
  (fn [coll] (map + a coll)))

(defn rotate [theta]
  (fn [coll] coll)) ; TODO

; Also need:
;  - bounds
;  - scale-offset calculator to maximize for available screen area

(def simple-mapper (->coords identity)) 

(defn scaling-mapper [x y z] 
  (->coords (comp (offset [x y]) (scale z)))) 

;(simple-mapper (dragon 5 nil))  
;((scaling-mapper 500 500 16) (dragon 7 nil))  

(defn draw-path-segments! [ctx coords]
  (doseq [[x y] coords]
    (line-to ctx x y))
  ctx) ; return the context for threading

(defn draw! [ctx coords]
  (->
    ctx
    (save)
    (stroke-width 2)
    (stroke-cap :square)
    (stroke-style :red)
    (begin-path)
    (draw-path-segments! coords)
    (stroke)
    (close-path)
    (restore)))

(defn available-area []
  (let [div (first ($ :div#wrapper))]
    [ (.-offsetWidth div) (.-offsetHeight div) ]))

(document-ready
  (fn []
    (let [canvas ($ :canvas#world)
          ctx    (get-context (.get canvas 0) "2d")
          [w h]  (available-area)
          coords ((scaling-mapper 200 200 6) (dragon 11 nil))]
      (->
        canvas
        (attr :width w)
        (attr :height h))
      (draw! ctx coords))))
