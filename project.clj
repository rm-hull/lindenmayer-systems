(defproject lindenmayer-systems "0.1.0-SNAPSHOT"
  :url "http://lindenmayer-systems.destructuring-bind.org"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.0"]
                 [noir "1.3.0-beta10"]
                 [jayq "2.0.0"]
                 [rm-hull/ring-gzip-middleware "0.1.4-SNAPSHOT"]
                 [rm-hull/monet "0.1.4-SNAPSHOT"]]
  :cljsbuild
    {:builds
     [{:source-paths ["src/lindenmayer/client"],
       :compiler
       {:pretty-print true,
        :output-to "resources/public/cljs/lindenmayer.js",
        :externs ["externs/jquery-1.8.js"],
        :optimizations :advanced,
        :print-input-delimiter true}}]}
  :hooks [leiningen.cljsbuild]
  :plugins [[lein-cljsbuild "0.3.0"]]
  :profiles {:dev {:dependencies [[vimclojure/server "2.3.6"]]}}
  :main lindenmayer.server
  :min-lein-version "2.0.0"
  :warn-on-reflection true
  :description "A web-based lindenmayer-systems animator")
