(ns canvas.point
  (:require
    [canvas.line :refer [line]]))

(defn point [canvas char x y]
  (line canvas char x y x y))
