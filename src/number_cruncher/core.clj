(ns number-cruncher.core
  (:gen-class
   :name com.lispcast.NumberCruncher
   :state state
   :init init
   :implements [Iterable]
   :methods [[average [Number] void]])
  (:require [clojure.core.async :as async]))

(defn average-in
  "Takes an average (tuple of numerator and denominator) and a number
  and combines them into a new average."
  [[n d] x]
  [(+ n x) (inc d)])

(defn average
  "Averages in a new number `x` into an atom `a` and puts the result
  on channel `c`."
  [a c x]
  (let [[n d] (swap! a average-in x)]
    (async/put! c (double (/ n d)))))

(defn blocking-channel-seq
  "Creates a lazy seq from a channel, which will block for new items."
  [chan]
  (lazy-seq
   (let [v (async/<!! chan)]
     (if (nil? v) ;; it's closed
       nil
       (cons v (blocking-channel-seq chan))))))

(defn -init []
  [[] {:atom (atom [0 0])
       :chan (async/chan (async/sliding-buffer 100))}])

(defn -average [this x]
  (let [{:keys [atom chan]} (.-state this)]
    (average atom chan x))
  nil)

(defn -iterator [this]
  (clojure.lang.SeqIterator.
    (blocking-channel-seq (:chan (.-state this)))))
