(ns lindenmayer.server
  (:use [compojure.response]
        [ring.util.response :only [response content-type]])
  (:require [noir.server :as server]
            [ring.middleware.gzip :as deflate])
  (:import [javax.imageio ImageIO]
           [java.awt.image RenderedImage]
           [java.io ByteArrayInputStream ByteArrayOutputStream]))

(server/load-views "src/lindenmayer/views")

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (read-string (get (System/getenv) "PORT" "8080"))]
    (server/add-middleware deflate/wrap-gzip)
    (server/start port {:mode mode
                        :ns 'lindenmayer})))

(defn- create-pipe [f pipe-size]
  (with-open [out-stream (ByteArrayOutputStream. pipe-size)]
    (f out-stream)
    (ByteArrayInputStream. (.toByteArray out-stream))))

(extend-protocol Renderable
  RenderedImage
  (render [this _]
    (let [stream (create-pipe #(ImageIO/write this "png" %) 0x20000)]
      (content-type (response stream) "image/png"))))

