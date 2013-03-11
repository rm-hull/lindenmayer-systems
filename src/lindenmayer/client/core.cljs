(ns lindenmayer-systems.client.core
  (:use [lindenmayer-systems.client.turtle :only [draw!]]
        [monet.canvas :only [get-context]]
        [jayq.core :only [$ document-ready attr]]))

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

(def sierpinski-triangle-seq
  (letfn [(seq0 [f g] 
            (lazy-seq (cons (flatten f) (seq0 
                                          [f :left g :right f :right g :left f]   
                                          [g g]))))]
   (seq0 [:fwd] [:fwd]))) 


(def sierpinski-curve-seq
  (letfn [(seq0 [a b] 
            (lazy-seq (cons (flatten a) (seq0 
                                          [b :left a :left b] 
                                          [a :right b :right a]))))]
    (seq0 [:fwd] [:fwd]))) 


(def sierpinski-median-curve-seq
  (letfn [(seq0 [l r] 
            (lazy-seq (cons (flatten l) (seq0 
                                          [:right r :left :fwd :left r :right] 
                                          [:left l :right :fwd :right l :left]))))]
    (seq0 [] []))) 

(def space-filling-curve-seq
  (letfn [(seq0 [x y] 
            (lazy-seq (cons (flatten x) (seq0 
                                          [:left y :fwd :right x :fwd x :right :fwd y :left] 
                                          [:right x :fwd :left y :fwd y :left :fwd x :right]))))]
    (seq0 [] [])))

(defn available-area []
  (let [div (first ($ :div#wrapper))]
    [ (.-offsetWidth div) (.-offsetHeight div) ]))

(document-ready
  (fn []
    (let [canvas ($ :canvas#world)
          ctx    (get-context (.get canvas 0) "2d")
          [w h]  (available-area) ]
      (->
        canvas
        (attr :width w)
        (attr :height h))
      (case (rand-int 6)
        0 (draw! ctx 90  (nth dragon-seq 13) [w h])
        1 (draw! ctx 90  (nth koch-curve-seq 5) [w h])
        2 (draw! ctx 60  (nth sierpinski-curve-seq 8) [w h])
        3 (draw! ctx 120 (nth sierpinski-triangle-seq 6) [w h])
        4 (draw! ctx 45  (nth sierpinski-median-curve-seq 13) [w h])
        5 (draw! ctx 90  (nth space-filling-curve-seq 6) [w h])
        ))))
