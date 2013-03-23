(ns lindenmayer.client.core
   (:require-macros [lindenmayer.compiler :as compiler])
   (:use [turtle.core :only [draw!]]
         [turtle.renderer.canvas :only [->canvas]]
         [monet.canvas :only [get-context]]
         [jayq.core :only [$ document-ready attr]]))

(defn annotate 
  "Returns a function that can reduce a list by annotating :left, :right and 
   :fwd instructions by the given distances and angles."
  [angle distance] 
  (fn 
    ([] [])
    ([xs] xs) 
    ([xs y]
      (case y
        :right (conj (conj xs y) angle)
        :left  (conj (conj xs y) angle)
        :fwd   (conj (conj xs y) distance) 
        (conj xs y)))))

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
      ; Temporary for demo purposes --> to be moved into a data structure
      (let [cmd (case (rand-int 11)                                     ;  Axiom                     Constants            Rules
                    0 (reduce (annotate 90 10)  [] (nth (compiler/l-system "^X"                      ("" "")              ("X=X+Y^" "Y=^X-Y"))       13))    ; Heighway's Dragon
                    1 (reduce (annotate 90 10)  [] (nth (compiler/l-system "-F"                      ("^")                ("F=F+F-F-F+F"))            5))    ; Koch Curve
                    2 (reduce (annotate 60 10)  [] (nth (compiler/l-system "A"                       ("^" "^")            ("A=B-A-B" "B=A+B+A"))      8))    ; Sierpinski Curve
                    3 (reduce (annotate 120 40) [] (nth (compiler/l-system "F-G-G"                   ("^" "^")            ("F=F-G+F+G-F" "G=GG"))     6))    ; Sierpinski Triangle
                    4 (reduce (annotate 45 10)  [] (nth (compiler/l-system "L"                       ("" "")              ("L=+R-^-R+" "R=-L+^+L-")) 13))    ; Sierpinski Median Curve
                    5 (reduce (annotate 90 20)  [] (nth (compiler/l-system "X"                       ("" "")              ("X=-Y^+X^X+^Y-" "Y=+X^-Y^Y-^X+")) 6))  ; Hilbert's space-filling Curve
                    6 (reduce (annotate 90 20)  [] (nth (compiler/l-system "F"                       ("^" "^")            ("F=F+F-F-F-G+F+F+F-F" "G=GGG")) 4)) ; Sierpinski's Carpet
                    7 (reduce (annotate 30 10)  [] (nth (compiler/l-system "W"                       ("" "" "" "")        ("W=+++X--^--Z^X+", "X=---W++^++Y^W-", "Y=+Z^X--^--Z+++", "Z=-Y^W++^++Y---")) 8)) ; Lace
                    8 (reduce (annotate 22 40)  [] (nth (compiler/l-system "^F"                      ("^")                ("F=#8FF-[#3-F+F+F]+[#9+F-F-F]")) 4)) ; Tree
                    9 (reduce (annotate 25 40)  [] (nth (compiler/l-system "X"                       ("" "^")             ("X=F-[[X]+]+F[+FX]-X" "F=FF")) 8)) ; Fractal Plant
                   10 (reduce (annotate 36 60)  [] (nth (compiler/l-system "[B]++[B]++[B]++[B]++[B]" ("^" "^" "^" "^" "") ("A=CE++DE----BE[-CE----AE]++" "B=+CE--DE[---AE--BE]+" "C=-AE++BE[+++CE++DE]-" "D=--CE++++AE[+DE++++BE]--BE" "E=")) 5)) ; Penrose Tiling
            )]
        (draw! (->canvas ctx) cmd [w h])))))
