(ns lindenmayer.views.index
  (:use [noir.core :only [defpage defpartial]]
        [hiccup.core :only [html]] 
        [hiccup.page :only [include-css include-js html5]]
        [hiccup.element :only [javascript-tag]]))

(defpartial layout [& content]
  (html5
    [:head
     [:title "Lindenmayer Systems Explorer"]
     (include-css "/css/default.css")
     (include-css "/css/ribbon.css")
     (include-js "https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js")
     (include-js "/cljs/lindenmayer.js")]
    [:body
     [:div#wrapper
      content]]))

(defpartial ribbon [text href]
  (html
    [:div#ribbon
      [:p
        [:a {:href href :title href :rel "me"} text]]]))

(defpage "/" {:as params}
  (layout
    (html
      [:div
        (ribbon "Fork me on GitHub!" "https://github.com/rm-hull/lindenmayer-systems")
        [:canvas#world]])))

