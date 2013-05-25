(defproject lindenmayer-systems "0.1.0"
  :url "http://lindenmayer-systems.destructuring-bind.org"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/data.json "0.2.2"]
                 [org.clojure/data.xml "0.0.7"]
                 [clj-http "0.7.2"]
                 [clj-time "0.5.0"]
                 [compojure "1.1.5"]
                 [hiccup "1.0.3"]
                 [jayq "2.3.0"]
                 [com.novemberain/monger "1.5.0-rc1"]
                 [rm-hull/ring-gzip-middleware "0.1.5"]
                 [rm-hull/monet "0.1.7"]
                 [rm-hull/turtle "0.1.4"]]
  :cljsbuild
    {:builds
     [{:source-paths ["src/lindenmayer/client"],
       :compiler
       {:pretty-print true,
        :output-to "resources/public/cljs/lindenmayer.js",
        :externs ["externs/jquery.js"],
        :optimizations :advanced,
        :print-input-delimiter true}}]}
  :hooks [leiningen.cljsbuild]
  :plugins [[lein-ring "0.8.5"]
            [rm-hull/lein-cljsbuild "0.3.1-SNAPSHOT"]]
  :ring {:handler lindenmayer.handler/app}
  :min-lein-version "2.1.3"
  :warn-on-reflection true
  :description "A web-based lindenmayer-systems animator")
