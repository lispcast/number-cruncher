(ns number-cruncher.threads
  (:gen-class))

(defn -main [& args]
  (.addShutdownHook
    (Runtime/getRuntime)
    (Thread. (fn []
               (println "Shutting down."))))

  (println "Running . . .")

  (doto (Thread. (fn []
                   (loop [x 0]
                     (Thread/sleep 1000)
                     (println x)
                     (when (= x 4)
                       (throw (ex-info "Oops! X=4" {})))
                     (recur (inc x)))))
    (.setUncaughtExceptionHandler
      (reify Thread$UncaughtExceptionHandler
        (uncaughtException [this thread exception]
          (println "Cleaning up!"))))
    (.start))



  (Thread/sleep 10000)
  (System/exit 0))
