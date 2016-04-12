(ns lindenmayer.explorer
  (:require
    [compojure.core :refer [defroutes GET]]
    [lindenmayer.compiler :refer [l-system]]
    [lindenmayer.png-render]
    [turtle.core :refer [draw!]]
    [turtle.renderer.bitmap :refer [->img]]
    [turtle.renderer.vector :refer [->svg]]))

(def presets {
  :heighways-dragon
  (first
    (drop
      13
      (l-system
        "^X"
        ("" "")
        ("X=X+Y^" "Y=^X-Y")
        90
        15)))

  :koch-curve
  (first
    (drop
      5
      (l-system
        "-F"
        ("^")
        ("F=F+F-F-F+F")
        90
        10)))

  :koch-snowflake
  (first
    (drop
      5
      (l-system
        "-F--F--F"
        ("^")
        ("F=F+F--F+F")
        60
        10)))

  :quadratic-koch-island
  (first
    (drop
      2
      (l-system
        "F-F-F-F"
        ("^")
        ("F=F+FF-FF-F-F+F+FF-F-F+F+FF+FF-F")
        90
        15)))

  :koch-crystal
  (first
    (drop
      4
      (l-system
        "F-F-F-F"
        ("^")
        ("F=FF-F--F-F")
        90
        15)))

  :koch-ring
  (first
    (drop
      4
      (l-system
        "F-F-F-F"
        ("^")
        ("F=FF-F-F-F-F-F+F")
        90
        15)))

  :koch-blocks
  (first
    (drop
      3
      (l-system
        "F-F-F-F"
        ("^")
        ("F=FF-F+F-F-FF")
        90
        30)))

  :koch-islands
  (first
    (drop
      2
      (l-system
        "F+F+F+F"
        ("^" "~")
        ("F=F+f-FF+F+FF+Ff+FF-f+FF-F-FF-Ff-FFF" "f=ffffff")
        90
        15)))

  :cesaro-koch-fractal
  (first
    (drop
      6
      (l-system
        "F"
        ("^")
        ("F=F+F--F+F")
        85
        30)))

  :fass-curve
  (first
    (drop
      4
      (l-system
        "-L"
        ("" "" "^")
        ("L=LF+RFR+FL-F-LFLFL-FRFR+" "R=-LFLF+RFRFR+F+RF-LFL-FR" "F=F")
        90
        15)))

  :peano-gosper-curve
  (first
    (drop
      4
      (l-system
        "A"
        ("^" "^")
        ("A=A-B--B+A++AA+B-" "B=+A-BB--B-A++A+B")
        60
        30)))

  :sierpinski-curve
  (first
    (drop
      8
      (l-system
        "A"
        ("^" "^")
        ("A=B-A-B" "B=A+B+A")
        60 10)))

  :sierpinski-triangle
  (first
    (drop
      6
      (l-system
        "F-G-G"
        ("^" "^")
        ("F=F-G+F+G-F" "G=GG")
        120
        40)))

  :sierpinski-median-curve
  (first
    (drop
      11
      (l-system
        "L"
        ("" "")
        ("L=+R-^-R+" "R=-L+^+L-")
        45
        10)))

  :hilberts-space-filling-curve
  (first
    (drop
      6
      (l-system
        "X"
        ("" "")
        ("X=-Y^+X^X+^Y-" "Y=+X^-Y^Y-^X+")
        90
        20)))

  :sierpinski-carpet
  (first
    (drop
      4
      (l-system
        "F"
        ("^" "^")
        ("F=F+F-F-F-G+F+F+F-F" "G=GGG")
        90 20)))

  :lace
  (first
    (drop
      8
      (l-system
        "W"
        ("" "" "" "")
        ("W=+++X--^--Z^X+", "X=---W++^++Y^W-", "Y=+Z^X--^--Z+++", "Z=-Y^W++^++Y---")
        30
        10)))

  :shrub
  (first
    (drop
      5
      (l-system
        "F"
        ("^")
        ("F=#8F[+F]F[-F][F]")
        21.3
        25)))

  :tree
  (first
    (drop
      4
      (l-system
        "F"
        ("^")
        ("F=#8FF-[#3-F+F+F]+[#9+F-F-F]")
        22.5
        40)))

  :fractal-plant
  (first
    (drop
      7
      (l-system
        "X"
        ("" "^")
        ("X=#9F-[[X]+]+F[+FX]-X" "F=FF")
        25
        8)))

  :penrose-tiling
  (first
    (drop
      5
      (l-system
        "[B]++[B]++[B]++[B]++[B]"
        ("^" "^" "^" "^" "")
        ("A=CE++DE----BE[-CE----AE]++" "B=+CE--DE[---AE--BE]+" "C=-AE++BE[+++CE++DE]-" "D=--CE++++AE[+DE++++BE]--BE" "E=")
        36
        60)))

  :t1
  (first
    (drop
      7
      (l-system
        "F"
        ("^" )
        ("F=FF-F")
        90
        30)))

  :t2
  (first
    (drop
      6
      (l-system
        "FB"
        ("^" "" "" "" "" "")
        ("F=-A-" "X=FAB" "G=K[BFKBG]X" "K=KAB" "B=GGK" "A=F-F[+F]")
        60
        40)))

  })

(defn- explore [id]
  (draw! ->svg (presets id) [1000 600]))

(defroutes routes
  (GET "/explorer/:id" [id]
    (explore (keyword id)))

  (GET "/random" []
    (explore (rand-nth (keys presets)))))
