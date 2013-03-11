(ns lindenmayer.compiler)

(defn- ->symbol [c]
  (case c
    \+ :right
    \- :left
    \[ :save
    \] :restore
    \# :colour
    \^ :fwd
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

; TODO - doesnt quite capture initial condition, assumes the first param
(defmacro l-system [axioms & rules]
  (let [rules (builder rules)
        params (keys rules)
        init-args (map generate axioms)]
    `(letfn [(seq0# [~@params]
              (lazy-seq (cons (flatten ~(first params)) (seq0# ~@(vals rules)))))]
       (seq0# ~@init-args))))


; ==============================================
; Sierpinski Triangle
;   start  : A
;   rules  : (A → B−A−B), (B → A+B+A)
;   angle  : 60°
; ==============================================
(macroexpand-1 '(l-system ("^" "^") "A=B-A-B" "B=A+B+A"))
(take 5 (l-system ("^" "^") "A=B-A-B" "B=A+B+A")) 

; ==============================================
; Koch Curve
;   start  : F
;   rules  : (F → F+F−F−F+F)
; ==============================================
(macroexpand-1 '(l-system "^" "F=F+F-F-F+F"))
(take 5 (l-system "^" "F=F+F-F-F+F")) 

; ==============================================
; Dragon
;   start  : FX
;   rules  : (X → X+YF), (Y → FX-Y)
; ==============================================
(macroexpand-1 '(l-system ("^" "") "X=X+Y^" "Y=^X-Y"))
(take 5 (l-system ("^" "") "X=X+Y^" "Y=^X-Y"))

; ==============================================
; Fractal plant
;   start  : X
;   rules  : (X → F-[[X]+X]+F[+FX]-X), (F → FF)
;   angle  : 25°
; ==============================================
(macroexpand-1 '(l-system ("" "^") "X=F-[[X]+]+F[+FX]-X" "F=FF"))
(take 5 (l-system ("" "^") "X=F-[[X]+]+F[+FX]-X" "F=FF"))
