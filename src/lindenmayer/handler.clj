(ns lindenmayer.handler
  (:require
    [compojure.core :refer [defroutes]]
    [compojure.handler :as handler]
    [compojure.route :as route]
    [ring.middleware.params :refer [wrap-params]]
    [ring.middleware.gzip :refer [wrap-gzip]]
    [hiccup.middleware :refer [wrap-base-url]]
    [ring.logger.timbre :as logger.timbre]
    [metrics.ring.expose :refer [expose-metrics-as-json]]
    [metrics.ring.instrument :refer [instrument]]
    [lindenmayer.explorer :as explorer]))

(defroutes app-routes
  explorer/routes
  (route/not-found "You step in the stream, but the water has moved on."))

(def app
  (->
    app-routes
    (logger.timbre/wrap-with-logger)
    (expose-metrics-as-json)
    (instrument)
    (wrap-base-url)
    (wrap-params)
    (wrap-gzip)
    (handler/api)))

