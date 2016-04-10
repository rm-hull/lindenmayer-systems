(ns lindenmayer.explorer
  (:require
    [compojure.core :refer [defroutes GET]]
    [lindenmayer.compiler :refer [l-system]]
    [lindenmayer.png-render]
    [turtle.core :refer [draw!]]
    [turtle.renderer.bitmap :refer [->img]]
    [turtle.renderer.vector :refer [->svg]]))

(defn- annotate
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

(defn- explore [id]
  (let [cmd (case id
                    0 (reduce (annotate 90 10)  [] (nth (l-system "^X"                      ("" "")              ("X=X+Y^" "Y=^X-Y"))       13))    ; Heighway's Dragon
                    1 (reduce (annotate 90 10)  [] (nth (l-system "-F"                      ("^")                ("F=F+F-F-F+F"))            5))    ; Koch Curve
                    2 (reduce (annotate 60 10)  [] (nth (l-system "A"                       ("^" "^")            ("A=B-A-B" "B=A+B+A"))      8))    ; Sierpinski Curve
                    3 (reduce (annotate 120 40) [] (nth (l-system "F-G-G"                   ("^" "^")            ("F=F-G+F+G-F" "G=GG"))     6))    ; Sierpinski Triangle
                    4 (reduce (annotate 45 10)  [] (nth (l-system "L"                       ("" "")              ("L=+R-^-R+" "R=-L+^+L-")) 13))    ; Sierpinski Median Curve
                    5 (reduce (annotate 90 20)  [] (nth (l-system "X"                       ("" "")              ("X=-Y^+X^X+^Y-" "Y=+X^-Y^Y-^X+")) 6))  ; Hilbert's space-filling Curve
                    6 (reduce (annotate 90 20)  [] (nth (l-system "F"                       ("^" "^")            ("F=F+F-F-F-G+F+F+F-F" "G=GGG")) 4)) ; Sierpinski's Carpet
                    7 (reduce (annotate 30 10)  [] (nth (l-system "W"                       ("" "" "" "")        ("W=+++X--^--Z^X+", "X=---W++^++Y^W-", "Y=+Z^X--^--Z+++", "Z=-Y^W++^++Y---")) 8)) ; Lace
                    8 (reduce (annotate 22 40)  [] (nth (l-system "^F"                      ("^")                ("F=#8FF-[#3-F+F+F]+[#9+F-F-F]")) 4)) ; Tree
                    9 (reduce (annotate 25 40)  [] (nth (l-system "X"                       ("" "^")             ("X=F-[[X]+]+F[+FX]-X" "F=FF")) 8)) ; Fractal Plant
                   10 (reduce (annotate 36 60)  [] (nth (l-system "[B]++[B]++[B]++[B]++[B]" ("^" "^" "^" "^" "") ("A=CE++DE----BE[-CE----AE]++" "B=+CE--DE[---AE--BE]+" "C=-AE++BE[+++CE++DE]-" "D=--CE++++AE[+DE++++BE]--BE" "E=")) 5)) ; Penrose Tiling
            )]
    (draw! ->svg cmd [1000 600])))

(defroutes routes
  (GET "/explorer/:id" [id]
    (explore (Integer/parseInt id)))

  (GET "/random" []
    (explore (rand-int 11))))
