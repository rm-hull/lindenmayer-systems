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

(defn split-on-assignment [symbols]
  [ (first symbols) (vec (drop 2 symbols)) ])

(defn generate [rule]
  (->>
    (clojure.string/lower-case rule)
    seq
    (mapv ->symbol)))

(defn builder [rules]
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

