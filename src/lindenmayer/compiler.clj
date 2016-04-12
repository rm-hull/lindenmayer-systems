(ns lindenmayer.compiler)

(defn make-symbol-table [angle distance]
  {
    \- [:right angle]
    \+ [:left angle]
    \^ [:fwd distance]
    \~ [:pen :up :fwd distance :pen :down]
    \[ :save
    \] :restore
    \# :color-index
    \0 0
    \1 1
    \2 2
    \3 3
    \4 4
    \5 5
    \6 6
    \7 7
    \8 8
    \9 9 })

(defn split-on-assignment [symbols]
  [ (first symbols) (vec (drop 2 symbols)) ])

(defn make-converter [symbol-table]
  (fn [rule]
    (->>
      (clojure.string/lower-case rule)
      (mapv #(get symbol-table % (symbol (str %)))))))

(defn builder [converter rules]
  (->>
    rules
    (map (comp split-on-assignment converter))
    (into (array-map))))

(defmacro l-system [axiom constants rules angle distance]
  (let [symbol-table (make-symbol-table angle distance)
        convert (make-converter symbol-table)
        rules (builder convert rules)
        params (keys rules)
        init-args (map convert constants)]
    `(letfn [(seq0# [~@params]
              (cons ~(convert axiom) (lazy-seq (seq0# ~@(vals rules)))))]
       (seq0# ~@init-args))))


