(ns canvas.point
  (:require
    [canvas.line :refer [line]]))

(defn point [canvas char x y]
  (line canvas char x y x y))

(defn neighbours [canvas x y]
  [[(dec x) (dec y)] [x (dec y)] [(inc x) (dec y)]
   [(dec x) y      ]             [(inc x) y]
   [(dec x) (inc y)] [x (inc y)] [(inc x) (inc y)]])
