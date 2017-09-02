(ns canvas.core
  (:require [canvas.canvas :refer :all]
            [canvas.line :refer :all]
            [clojure.string :refer [join split]])
  (:gen-class :name canvas.core :main true))

(def current-canvas (atom nil))

(defn- split-args [args]
  (->> (split args #" ")
       (map #(Integer/parseInt %1))))

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
        (= \C command) (let [[x y] (split-args args)] (swap! current-canvas (fn [_] (canvas x y))))
        (= \L command) (let [[x1 y1 x2 y2] (split-args args)] (swap! current-canvas (fn [latest] (line latest x1 y1 x2 y2)))))
      (draw @current-canvas)
      (print "enter command: ")
      (flush)
      (recur (read-line)))))
