(ns canvas.core
  (:require [canvas.canvas :refer :all]
            [canvas.line :refer :all]
            [clojure.string :refer [starts-with?]])
  (:gen-class :name canvas.core :main true))

(defn -main []
  (loop [line ""]
    (cond
      (starts-with? line "Q") (System/exit 0)
      (starts-with? line "C") (println "Canvas!")
      (starts-with? line "L") (println "Line!"))
    (recur (read-line))))
