(ns lindenmayer.explorer.botany
  (:require
   [lindenmayer.compiler :refer [l-system]]))

(def arboria {:shrub
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
                 8)))})
