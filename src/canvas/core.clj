(ns canvas.core
  (:require [canvas.canvas :refer :all]
            [canvas.line :refer :all]
            [clojure.string :refer [join]])
  (:gen-class :name canvas.core :main true))

(def current-canvas (atom nil))

(defn- draw [rows]
  (if rows
    (->> rows
         (map (fn [row] (apply str row)))
         (join \newline)
         println)))

(defn -main []
  (loop [[command & [_ & rest]] ""]
    (let [args (join rest)]
      (cond
        (= \Q command) (System/exit 0)
        (= \C command) (swap! current-canvas (fn [_] (canvas 10 8)))
        (= \L command) (swap! current-canvas (fn [latest] (line latest 3 5 7 5))))
      (draw @current-canvas)
      (print "enter command: ")
      (flush)
      (recur (read-line)))))
