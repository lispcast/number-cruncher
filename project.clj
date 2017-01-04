(defproject com.lispcast/number-cruncher "0.1.1-SNAPSHOT"
  :description "Part of PurelyFunctional.tv"
  :url "https://purelyfunctional.tv/"
  :license {:name "CC0 1.0 Universal (CC0 1.0) Public Domain Dedication"
            :url "http://creativecommons.org/publicdomain/zero/1.0/"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/core.async "0.2.395"]]

  :aot [number-cruncher.core])
