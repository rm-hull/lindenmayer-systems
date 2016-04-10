(ns lindenmayer.png-render
  (:require
    [ring.util.response :refer [response content-type]]
    [compojure.response :refer [Renderable]])
  (:import
    [javax.imageio ImageIO]
    [java.awt.image RenderedImage]
    [java.io OutputStream ByteArrayInputStream ByteArrayOutputStream]))

(defn- create-pipe [f pipe-size]
  (with-open [out-stream (ByteArrayOutputStream. pipe-size)]
    (f out-stream)
    (ByteArrayInputStream. (.toByteArray out-stream))))

(defn- png-writer [^RenderedImage img ^OutputStream out]
  (ImageIO/write img "png" out))

(extend-protocol Renderable
  RenderedImage
  (render [this _]
    (->
      (partial png-writer this)
      (create-pipe 0x20000)
      response
      (content-type "image/png"))))
