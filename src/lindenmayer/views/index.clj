(ns lindenmayer.views.index
  (:use [noir.core :only [defpage defpartial]]
        [hiccup.core :only [html]] 
        [hiccup.page :only [include-css include-js html5]]
        [hiccup.element :only [javascript-tag]]))

; When using {:optimizations :whitespace}, the Google Closure compiler combines
; its JavaScript inputs into a single file, which obviates the need for a "deps.js"
; file for dependencies.  However, true to ":whitespace", the compiler does not remove
; the code that tries to fetch the (nonexistent) "deps.js" file.  Thus, we have to turn
; off that feature here by setting CLOSURE_NO_DEPS.
;
; Note that this would not be necessary for :simple or :advanced optimizations.
(defn include-clojurescript [path]
  (list
    (javascript-tag "var CLOSURE_NO_DEPS = true;")
    (include-js path)))

(defpartial layout [& content]
  (html5
    [:head
     [:title "Lindenmayer Systems Explorer"]
     (include-css "/css/default.css")
     (include-css "/css/ribbon.css")
     (include-js "https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js")]
    [:body
     [:div#wrapper
      content]
      (include-clojurescript "/cljs/lindenmayer.js")
     ]))

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

