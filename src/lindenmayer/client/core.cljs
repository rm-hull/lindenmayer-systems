(ns lindenmayer-systems.client.core
   (:require-macros [lindenmayer.compiler :as compiler])
   (:use [lindenmayer.client.turtle :only [draw!]]
        [monet.canvas :only [get-context]]
        [jayq.core :only [$ document-ready attr]]))

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
      (case (rand-int 8)
        0 (draw! ctx 90  (nth (compiler/l-system ("" "")   "X=X+Y^" "Y=^X-Y")       13) [w h])   ; Heighway's Dragon
        1 (draw! ctx 90  (nth (compiler/l-system ("^")     "F=F+F-F-F+F")            5) [w h])   ; Koch Curve
        2 (draw! ctx 60  (nth (compiler/l-system ("^" "^") "A=B-A-B" "B=A+B+A")      8) [w h])   ; Sierpinski Curve
        3 (draw! ctx 120 (nth (compiler/l-system ("^" "^") "F=F-G+F+G-F" "G=GG")     6) [w h])   ; Sierpinski Triangle
        4 (draw! ctx 45  (nth (compiler/l-system ("" "")   "L=+R-^-R+" "R=-L+^+L-") 13) [w h])   ; Sierpinski Median Curve
        5 (draw! ctx 90  (nth (compiler/l-system ("" "")   "X=-Y^+X^X+^Y-" "Y=+X^-Y^Y-^X+") 6) [w h]) ; Space-filling Curve
        6 (draw! ctx 90  (nth (compiler/l-system ("^" "^") "F=F+F-F-F-G+F+F+F-F" "G=GGG") 4) [w h]) ; Sierpinski's Carpet
        7 (draw! ctx 30  (nth (compiler/l-system ("" "")   "W=+++X--^--Z^X+", "X=---W++^++Y^W-", "Y=+Z^X--^--Z+++", "Z=-Y^W++^++Y---") 8) [w h]) ; Lace
        ))))
