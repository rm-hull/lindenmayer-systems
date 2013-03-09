(ns lindenmayer-systems.client.compiler)

(defn to-symbol [c]
  (case c
    \+ :right
    \- :left
    \[ :save
    \] :restore
    \c :colour
    \f :fwd
    (symbol (str c))))

(defn split-on-equals [symbols]
  { (first symbols) (drop 2 symbols) })

(defn generate-rule [rule]
  (->> 
    (clojure.string/lower-case rule)
    seq
    (map to-symbol)
    split-on-equals))

(defn compile-rules [& rules] 
  (->> 
    rules
    (map generate-rule)
    (apply merge)))

; (compile-rules "A=B-A-B" "B=A+B+A")
; =>
; { a [b :left a :left b]
;   b [a :right b :right a] } 
