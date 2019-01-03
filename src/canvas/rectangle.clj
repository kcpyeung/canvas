(ns canvas.rectangle
  (:require [canvas.line :refer [line]]))

(defn- line-coords [x1 y1 x2 y2]
  (list
    (list x1 y1 x2 y1)
    (list x2 y1 x2 y2)
    (list x2 y2 x1 y2)
    (list x1 y2 x1 y1)))

(defn- draw-line [canvas [x1 y1 x2 y2]]
  (line canvas x1 y1 x2 y2))

(defn rectangle [canvas x1 y1 x2 y2]
  (->> (line-coords x1 y1 x2 y2)
       (reduce draw-line canvas)))
