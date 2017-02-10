(ns number-cruncher.interface
  (:gen-interface
   :name com.lispcast.MyInterface
   :extends [Iterable Runnable]
   :methods [[go [int java.util.Collection] void]]))

