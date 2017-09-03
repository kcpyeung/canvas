(ns canvas.rectangle
  (:require [canvas.line :refer [line]]))

(defn- line-coords [x1 y1 x2 y2]
  (list
    (list x1 y1 x2 y1)
    (list x2 y1 x2 y2)
    (list x2 y2 x1 y2)
    (list x1 y2 x1 y1)))

(defn- draw-line [canvas [x1 y1 x2 y2]]
  (let [new-canvas (line canvas x1 y1 x2 y2)]
    (if (= canvas new-canvas) nil new-canvas)))

(defn rectangle [canvas x1 y1 x2 y2]
  (let [lines (line-coords x1 y1 x2 y2)
        new-canvas (reduce draw-line canvas lines)]
    (if (nil? new-canvas) canvas new-canvas)))
