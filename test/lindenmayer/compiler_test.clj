(ns lindenmayer.compiler-test
  (:require
    [clojure.test :refer :all]
    [lindenmayer.compiler :refer :all]))

(deftest check-split-on-assignment
  (let [symbol-table (make-symbol-table 30 10)
        convert (make-converter symbol-table)]
    (is (= ((comp split-on-assignment convert) "A=B-A-B")
           ['A ['B [:right 30] 'A [:right 30] 'B]]))))

(deftest check-builder
  (let [symbol-table (make-symbol-table 30 10)
        convert (make-converter symbol-table)]
    (is (= (builder convert ["X=X+Y^", "Y=^X-Y"])
           {
             'X ['X [:left 30] 'Y [:fwd 10]]
             'Y [[:fwd 10] 'X [:right 30] 'Y] }))))

; ==============================================
; Sierpinski Triangle
;   start  : A
;   rules  : (A → B−A−B), (B → A+B+A)
;   angle  : 60°
; ==============================================
;(macroexpand-1 '(l-system "A" ("^" "^") ("A=B-A-B" "B=A+B+A") 60 10))
;(flatten (nth (l-system "A" ("^" "^") ("A=B-A-B" "B=A+B+A") 60 10) 5))
; ==============================================
; Koch Curve
;   start  : F
;   rules  : (F → F+F−F−F+F)
; ==============================================
;(macroexpand-1 '(l-system "-F" ("^") ("F=F+F-F-F+F")))
;(take 5 (l-system "-F" ("^") ("F=F+F-F-F+F")))
; ==============================================
; Dragon
;   start  : FX
;   rules  : (X → X+YF), (Y → FX-Y)
; ==============================================
;(macroexpand-1 '(l-system "X" ("^" "") ("X=X+Y^" "Y=^X-Y")))
;(take 5 (l-system "X" ("^" "") ("X=X+Y^" "Y=^X-Y")))
; ==============================================
; Fractal plant
;   start  : X
;   rules  : (X → F-[[X]+X]+F[+FX]-X), (F → FF)
;   angle  : 25°
; ==============================================
;(macroexpand-1 '(l-system "X" ("" "^") ("X=F-[[X]+]+F[+FX]-X" "F=FF")))
;(take 5 (l-system "X" ("" "^") ("X=F-[[X]+]+F[+FX]-X" "F=FF")))
;(macroexpand-1 '(l-system "[B]++[B]++[B]++[B]++[B]" ("^" "^" "^" "^" "") ("A=CE++DE----BE[-CE----AE]++" "B=+CE--DE[---AE--BE]+" "C=-AE++BE[+++CE++DE]-" "D=--CE++++AE[+DE++++BE]--BE" "E=")))
;(take 5 (l-system "[B]++[B]++[B]++[B]++[B]" ("^" "^" "^" "^" "") ("A=CE++DE----BE[-CE----AE]++" "B=+CE--DE[---AE--BE]+" "C=-AE++BE[+++CE++DE]-" "D=--CE++++AE[+DE++++BE]--BE" "E=")))

