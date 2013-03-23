(ns lindenmayer.compiler)

(defn- ->symbol [c]
  (case c
    \+ :right
    \- :left
    \[ :save
    \] :restore
    \# :color-index
    \^ :fwd
    \0 0
    \1 1
    \2 2
    \3 3
    \4 4
    \5 5
    \6 6
    \7 7
    \8 8
    \9 9
    (symbol (str c))))

(defn- split-on-assignment [symbols]
  [ (first symbols) (vec (drop 2 symbols)) ])

(defn- generate [rule]
  (->> 
    (clojure.string/lower-case rule)
    seq
    (map ->symbol)
    vec))

(defn- builder [rules] 
  (->> 
    rules
    (map (comp split-on-assignment generate))
    (into (array-map))))

(defmacro l-system [axiom constants rules]
  (let [rules (builder rules)
        params (keys rules)
        init-args (map generate constants)]
    `(letfn [(seq0# [~@params]
              (lazy-seq (cons (flatten ~(generate axiom)) (seq0# ~@(vals rules)))))]
       (seq0# ~@init-args))))


; ==============================================
; Sierpinski Triangle
;   start  : A
;   rules  : (A → B−A−B), (B → A+B+A)
;   angle  : 60°
; ==============================================
;(macroexpand-1 '(l-system "A" ("^" "^") ("A=B-A-B" "B=A+B+A")))
;(take 5 (l-system "A" ("^" "^") ("A=B-A-B" "B=A+B+A"))) 
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

