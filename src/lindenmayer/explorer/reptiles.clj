(ns lindenmayer.explorer.reptiles
  (:require
    [lindenmayer.compiler :refer [l-system]]))

; based on http://canonical.org/~kragen/named-msgs/reptile-lsystems

(def rep-4 {
  :reptile-1
  (first
    (drop
      5
      (l-system
        "R"
        ("^" "" "")
        ("F=GG" "G=GG" "R=FF+FF[+G|R]FF+FF[+G|R]FF+FF+FF-F[+R]RF+")
        90
        50)))

  :reptile-1a
  (first
    (drop
      1
      (l-system
        "++X++F-F-FF-FF-F-F"
        ("^" "" "")
        ("F=FF" "G=GG" "X=X[G+G-X][-G+G-X][+G+G-X][++F-F-F[+F]F-F[+F]F-F-F]")
        90
        50)))

  :reptile-2
  (first
    (drop
      4
      (l-system
        "Q"
        ("^" "" "")
        ("F=FF" "P=FFF|Q|PFFF+FFFF+FFF|Q|F+PFF-FF+FF+" "Q=FFF|P|QFFF-FFFF-FFF|P|F-QFF+FF-FF-")
        90
        50)))

  :reptile-3
  (first
    (drop
      5
      (l-system
        "R"
        ("^" "" "")
        ("F=FF" "R=F+R-F-FFF+L-F+FF+FFF+L-FFF+F+R-FFF+FF+" "L=F-L+F+FFF-R+F-FF-FFF-R+FFF-F-L+FFF-FF-")
        90
        50)))

  :reptile-4
  (first
    (drop
      4
      (l-system
        "R"
        ("^" "")
        ("F=FF" "R=++FFR+FFR+FFR++FFFRF")
        60
        50)))




 })
