(ns lindenmayer-systems.client.core
  (:use [monet.canvas :only [get-context get-pixel rect fill-style save restore]]
        [monet.core :only [animation-frame]]
        [jayq.core :only [$ document-ready data text attr hide bind prevent]]
        [jayq.util :only [log]]
        [clojure.string :only [split]]))

(defn dragon 
  "Unfolding the dragon"
  [depth turn]
  (if (pos? depth)
    (let [next-depth (dec depth)
          left       (dragon next-depth :left)
          right      (dragon next-depth :right)]
      (concat
        right 
        (if turn 
          (cons turn left)
          left)))))
