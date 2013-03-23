(defproject lindenmayer-systems "0.1.0"
  :url "http://lindenmayer-systems.destructuring-bind.org"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [noir "1.3.0"]
                 [jayq "2.3.0"]
                 [ns-tracker "0.2.1"]
                 [rm-hull/ring-gzip-middleware "0.1.5"]
                 [rm-hull/monet "0.1.6"]
                 [rm-hull/turtle "0.1.1"]]
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
  :plugins [[rm-hull/lein-cljsbuild "0.3.1-SNAPSHOT"]]
  :profiles {:dev {:dependencies [[vimclojure/server "2.3.6"]]}}
  :main lindenmayer.server
  :min-lein-version "2.1.1"
  :warn-on-reflection true
  :description "A web-based lindenmayer-systems animator")
