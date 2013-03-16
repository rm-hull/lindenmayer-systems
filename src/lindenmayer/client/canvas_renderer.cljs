(ns lindenmayer.client.canvas-renderer
  (:use [monet.canvas :only [save restore stroke-width stroke-cap stroke-style 
                             begin-path line-to move-to stroke close-path transform]]))

(defn- draw-path-segments! [ctx data]
  (doseq [d data]
    (when-let [color (:color d)]
      (stroke-style ctx color))
    (apply (if (:restore-point d) move-to line-to) ctx (:coords d)))
  ctx) ; return the context for threading

(defn ->canvas [ctx]
  (fn [data [w h] bounds matrix]
    (.log js/console (pr-str "screen-size" [w h]))
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
