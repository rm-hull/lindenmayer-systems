(ns lindenmayer.compiler-test
  (:require
    [clojure.test :refer :all]
    [lindenmayer.compiler :refer :all]))

(deftest check-split-on-assignment
  (is (= ((comp split-on-assignment generate) "A=B-A-B")
         ['a ['b :left 'a :left 'b]])))

(deftest check-builder
  (is (= (builder ["X=X+YF", "Y=FX-Y"])
         {
           'x ['x :right 'y 'f]
           'y ['f 'x :left 'y] })))


; ==============================================
; Sierpinski Triangle
;   start  : A
;   rules  : (A → B−A−B), (B → A+B+A)
;   angle  : 60°
; ==============================================
(macroexpand-1 '(l-system "A" ("^" "^") ("A=B-A-B" "B=A+B+A")))
;(take 5 (l-system "A" ("^" "^") ("A=B-A-B" "B=A+B+A")))
; ==============================================
; Koch Curve
;   start  : F
;   rules  : (F → F+F−F−F+F)
; ==============================================
(macroexpand-1 '(l-system "-F" ("^") ("F=F+F-F-F+F")))
;(take 5 (l-system "-F" ("^") ("F=F+F-F-F+F")))
; ==============================================
; Dragon
;   start  : FX
;   rules  : (X → X+YF), (Y → FX-Y)
; ==============================================
(macroexpand-1 '(l-system "X" ("^" "") ("X=X+Y^" "Y=^X-Y")))
;(take 5 (l-system "X" ("^" "") ("X=X+Y^" "Y=^X-Y")))
; ==============================================
; Fractal plant
;   start  : X
;   rules  : (X → F-[[X]+X]+F[+FX]-X), (F → FF)
;   angle  : 25°
; ==============================================
;(macroexpand-1 '(l-system "X" ("" "^") ("X=F-[[X]+]+F[+FX]-X" "F=FF")))
;(take 5 (l-system "X" ("" "^") ("X=F-[[X]+]+F[+FX]-X" "F=FF")))
(macroexpand-1 '(l-system "[B]++[B]++[B]++[B]++[B]" ("^" "^" "^" "^" "") ("A=CE++DE----BE[-CE----AE]++" "B=+CE--DE[---AE--BE]+" "C=-AE++BE[+++CE++DE]-" "D=--CE++++AE[+DE++++BE]--BE" "E=")))
;(take 5 (l-system "[B]++[B]++[B]++[B]++[B]" ("^" "^" "^" "^" "") ("A=CE++DE----BE[-CE----AE]++" "B=+CE--DE[---AE--BE]+" "C=-AE++BE[+++CE++DE]-" "D=--CE++++AE[+DE++++BE]--BE" "E=")))

