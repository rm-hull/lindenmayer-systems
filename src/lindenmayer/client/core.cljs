(ns lindenmayer-systems.client.core
  (:use [monet.canvas :only [get-context save restore stroke-width stroke-cap stroke-style begin-path line-to stroke close-path ]]
        [jayq.core :only [$ document-ready data text attr hide bind prevent]]))

; ==============================================
; Dragon
;   start  : FX
;   rules  : (X → X+YF), (Y → FX-Y)
; ==============================================

(def dragon-seq
  "Unfolding the dragon"
  (letfn [(seq0 [x y] 
            (lazy-seq (cons (flatten x) (seq0 [x :right y :fwd] [:fwd x :left y]))))]
    (seq0 [] []))) 

; ==============================================
; Koch Curve
;   start  : F
;   rules  : (F → F+F−F−F+F)
; ==============================================

(def koch-curve-seq
  (letfn [(seq0 [f]
            (lazy-seq (cons (flatten f) (seq0 [f :right f :left f :left f :right f]))))]
    (seq0 [:fwd])))

; ==============================================
; Sierpinski Triangle
;   start  : A
;   rules  : (A → B−A−B), (B → A+B+A)
;   angle  : 60°
; ==============================================

(def sierpinski-seq
  (letfn [(seq0 [a b] 
            (lazy-seq (cons (flatten a) (seq0 [b :left a :left b] [a :right b :right a]))))]
    (seq0 [:fwd] [])) ) 

(defn move 
  "Destructures a state (comprising a pair of co-ordinates and 
   a direction) and moves one step in that direction"
  [[[x y] dir] cmd]
  (case cmd
    :fwd 
      (case dir
        :north [x (inc y)]
        :south [x (dec y)]
        :east  [(inc x) y]
        :west  [(dec x) y])
    [x y]))

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
   [ (move curr-state cmd) (new-direction curr-state cmd) ]))

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
    (stroke-width 1)
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
          l-system (nth dragon-seq 13)
          ;l-system (nth koch-curve-seq 5)
          ;l-system (nth sierpinski-seq 4)
          coords ((scaling-mapper 500 500 5) l-system)] 
      (->
        canvas
        (attr :width w)
        (attr :height h))
      (draw! ctx coords))))
