(ns lindenmayer.client.canvas-renderer
  (:use [monet.canvas :only [save restore stroke-width stroke-cap stroke-style 
                             begin-path line-to stroke close-path transform]]))

(defn- draw-path-segments! [ctx data]
  (doseq [d data]
    (when-let [color (:color d)]
      (stroke-style ctx color))
    (apply line-to ctx (:coords d)))
  ctx) ; return the context for threading

(defn render! [ctx]
  (fn [data bounds matrix]
    (.log js/console (pr-str "bounds" bounds))
    (.log js/console (pr-str "matrix" matrix))
    (->
      (apply transform ctx matrix)
      (stroke-width 3)
      (stroke-cap :square)
      (begin-path)
      (draw-path-segments! data)
      (stroke)
      (close-path))))
