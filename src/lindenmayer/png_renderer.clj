(ns lindenmayer.png-renderer
  (:import [java.awt.image BufferedImage]
           [java.awt.geom AffineTransform GeneralPath]  
           [java.awt Color Graphics2D RenderingHints BasicStroke]))

; TODO
(defn- awt-converter [color]
  Color/RED)

(defn- generate-shape [g2d data]
  (let [path (GeneralPath.)
        line-to (fn [[x y]] (.lineTo path (double x) (double y)))]
    (.moveTo path 0.0 0.0)
    (doseq [d data]
      (when-let [color (:color d)]
        (.setColor g2d (awt-converter color)))
      (line-to (:coords d)) )
    path))

(defn ->img [data [w h] bounds matrix]
  (let [img (BufferedImage. w h BufferedImage/TYPE_INT_RGB)
        g2d (.createGraphics img)]
    (doto g2d
      (.setBackground Color/WHITE)
      (.clearRect 0 0 w h)
      (.setRenderingHint RenderingHints/KEY_ANTIALIASING RenderingHints/VALUE_ANTIALIAS_ON)
      (.setRenderingHint RenderingHints/KEY_INTERPOLATION RenderingHints/VALUE_INTERPOLATION_BICUBIC)
      (.setRenderingHint RenderingHints/KEY_RENDERING RenderingHints/VALUE_RENDER_QUALITY)
      (.setStroke (BasicStroke. 3))
      (.setTransform (AffineTransform. (into-array Double/TYPE matrix)))
      (.draw (generate-shape g2d data)))
    img))
