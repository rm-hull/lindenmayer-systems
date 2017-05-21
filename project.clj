(defproject lindenmayer-systems "0.2.0"
  :description "An L-System explorer in Clojure"
  :url "http://lindenmayer-systems.destructuring-bind.org"
  :license {
    :name "The MIT License (MIT)"
    :url "http://opensource.org/licenses/MIT"}
  :dependencies [
    [org.clojure/clojure "1.8.0"]
    [org.clojure/data.json "0.2.6"]
    [com.taoensso/timbre "4.10.0"]
    [compojure "1.6.0"]
    [ring "1.6.1"]
    [hiccup "1.0.5"]
    [ring-logger-timbre "0.7.5"]
    [metrics-clojure-ring "2.9.0"]
    [rm-hull/ring-gzip-middleware "0.1.7"]
    [rm-hull/turtle "0.1.10"]
    [rm-hull/ring-cede "0.1.0"]]
  :scm {:url "git@github.com:rm-hull/lindenmayer-systems.git"}
  :ring {
    :handler lindenmayer.handler/app}
  :source-paths ["src"]
  :resource-paths ["resources"]
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
        [lein-cloverage "1.0.9"]
        [lein-ring "0.12.0"]]}})
