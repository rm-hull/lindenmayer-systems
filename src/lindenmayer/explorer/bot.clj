(ns lindenmayer.explorer.bot
  (:require
   [lindenmayer.compiler :refer [l-system]]))

(def interesting {:t1
                  (first
                   (drop
                    7
                    (l-system
                     "F"
                     ("^")
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

                  :t3
                  (first
                   (drop
                    8
                    (l-system
                     "DOO"
                     ("^" "" "" "" "" "")
                     ("F=DAD-DO" "Y=+DDF" "O=YA" "W=FOO" "A=FFO+--WF" "D=")
                     90
                     40)))

                  :t4
                  (first
                   (drop
                    7
                    (l-system
                     "M"
                     ("^" "" "")
                     ("F=MF" "H=H+F" "M=[]-FFMM")
                     60
                     50)))

                  :t5
                  (first
                   (drop
                    6
                    (l-system
                     "IHIF"
                     ("^" "" "" "" "")
                     ("F=[H-]" "I=FHF" "J=[+]I" "S=[]" "H=+SHJFF")
                     60
                     30)))

                  :t6
                  (first
                   (drop
                    11
                    (l-system
                     "FKKI"
                     ("^" "" "")
                     ("F=++IKKF-+" "I=[]F" "K=")
                     60
                     60)))

                  :t7
                  (first
                   (drop
                    7
                    (l-system
                     "ZZZF"
                     ("^" "")
                     ("F=[]-+-ZFFF" "Z=FFF")
                     90
                     40)))

                  :t8
                  (first
                   (drop
                    9
                    (l-system
                     "NNF"
                     ("^" "")
                     ("F=[-]" "N=F-FNF-NFF")
                     90
                     30)))

                  :t9
                  (first
                   (drop
                    3
                    (l-system
                     "WFF"
                     ("^" "" "")
                     ("F=[]+[RFWWF]" "W=F+FF" "R=")
                     90
                     30)))})

