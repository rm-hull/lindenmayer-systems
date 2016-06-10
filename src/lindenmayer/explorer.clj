(ns lindenmayer.explorer
  (:require
    [compojure.core :refer [defroutes GET]]
    [turtle.core :refer [draw!]]
    [turtle.renderer.bitmap :refer [->img]]
    [turtle.renderer.vector :refer [->svg]]
    [lindenmayer.explorer.botany :as botany]
    [lindenmayer.explorer.bot :as bot]
    [lindenmayer.explorer.presets :as core]
    [lindenmayer.explorer.reptiles :as reptiles]))

(def presets
  (merge
    botany/arboria
    bot/interesting
    core/fractals
    reptiles/rep-4))

(defn- explore [id]
  (draw! ->svg (presets id) [1000 600]))

(defroutes routes
  (GET "/explorer/:id" [id]
    (explore (keyword id)))

  (GET "/random" []
    (explore (rand-nth (keys presets)))))
