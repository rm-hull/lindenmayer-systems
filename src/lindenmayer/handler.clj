(ns lindenmayer.handler
  (:use [compojure.core]
        [compojure.response]
        [ring.util.response :only [response content-type]]
        [ring.middleware.params :only [wrap-params]] 
        [ring.middleware.gzip :only [wrap-gzip]]
        [hiccup.middleware :only [wrap-base-url]])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [monger.core :as mg]
            [clj-http.client :as http]  
            [lindenmayer.views.explorer :as explorer]  
            [lindenmayer.views.sitemap :as sitemap])
  (:import [javax.imageio ImageIO]
           [java.awt.image RenderedImage]
           [java.io ByteArrayInputStream ByteArrayOutputStream]))

(defn- ping [url delay-mins]
  (future 
    (Thread/sleep (* delay-mins 60 1000))
    (ping url delay-mins)
    (http/get url)))

(defn- create-pipe [f pipe-size]
  (with-open [out-stream (ByteArrayOutputStream. pipe-size)]
    (f out-stream)
    (ByteArrayInputStream. (.toByteArray out-stream))))

(extend-protocol Renderable
  RenderedImage
  (render [this _]
    (let [stream (create-pipe #(ImageIO/write this "png" %) 0x20000)]
      (-> (response stream) (content-type "image/png")))))

(def mongo-client
  (when-let [connection-details (System/getenv "MONGOHQ_URL")]
    (mg/connect-via-uri! connection-details))) 

(def keep-alive
  (when-let [url (System/getenv "KEEPALIVE_URL")]
    (ping url 47)))

(defroutes app-routes
  explorer/routes
  sitemap/routes
  (route/resources "/assets")
  (route/not-found "You step in the stream, but the water has moved on."))

(def app 
  (-> 
    (handler/site app-routes)
    (wrap-base-url)
    (wrap-params)
    (wrap-gzip)))

