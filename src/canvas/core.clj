(ns canvas.core
  (:require [canvas.canvas :refer :all]
            [canvas.line :refer :all]
            [canvas.rectangle :refer :all]
            [canvas.bucket-fill :refer :all]
            [clojure.string :refer [join split]])
  (:gen-class :name canvas.core :main true))

(defn- split-args [f args]
  (->> (split args #" ")
       (map #(f %1))))

(defn- draw [rows]
  (if rows
    (->> rows
         (map (fn [row] (apply str row)))
         (join \newline)
         println)))

(defn -main []
  (loop [[command & [_ & rest]] ""
         current-canvas         nil]
    (let [args    (join rest)
          to-draw (cond
                    (= \Q command) (System/exit 0)
                    (= \C command) (let [[x y] (split-args #(Integer/parseInt %) args)] (canvas x y))
                    (= \L command) (let [[x1 y1 x2 y2] (split-args #(Integer/parseInt %) args)] (line current-canvas x1 y1 x2 y2))
                    (= \R command) (let [[x1 y1 x2 y2] (split-args #(Integer/parseInt %) args)] (rectangle current-canvas x1 y1 x2 y2))
                    (= \F command) (let [[char x y] (split-args identity args)] (bucket-fill current-canvas char (Integer/parseInt x) (Integer/parseInt y))))]
      (draw to-draw)
      (print "enter command: ")
      (flush)
      (recur (read-line) to-draw))))
