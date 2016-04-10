(defproject lindenmayer-systems "0.2.0-SNAPSHOT"
  :description "An L-System explorer in Clojure"
  :url "http://lindenmayer-systems.destructuring-bind.org"
  :license {
    :name "The MIT License (MIT)"
    :url "http://opensource.org/licenses/MIT"}
  :dependencies [
    [org.clojure/clojure "1.8.0"]
    [org.clojure/data.json "0.2.6"]
    [com.taoensso/timbre "4.3.1"]
    [compojure "1.5.0"]
    [ring "1.4.0"]
    [hiccup "1.0.5"]
    [ring-logger-timbre "0.7.5"]
    [metrics-clojure-ring "2.6.1"]
    [rm-hull/ring-gzip-middleware "0.1.7"]
    [rm-hull/turtle "0.1.9-SNAPSHOT"]]
  :scm {:url "git@github.com:rm-hull/lindenmayer-systems.git"}
  :ring {
    :handler lindenmayer.handler/app}
  :plugins [
    [lein-ring "0.9.7"]]
  :source-paths ["src"]
  :jar-exclusions [#"(?:^|/).git"]
  :uberjar-exclusions [#"\.SF" #"\.RSA" #"\.DSA"]
  :min-lein-version "2.6.1"
    :profiles {
    :uberjar {:aot :all}
    :dev {
      :global-vars {*warn-on-reflection* true}
      :dependencies [
        [org.clojure/test.check "0.9.0"]]
      :plugins [
        [lein-cloverage "1.0.6"]]}})
