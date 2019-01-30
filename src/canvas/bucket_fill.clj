(ns canvas.bucket-fill
  (:require
    [canvas.point :refer :all]))

(defn bucket-fill [canvas char x y]
  (let [neighbours (neighbours canvas x y)
        drawn      (->> neighbours
                        (reduce #(point %1 char (first %2) (second %2)) canvas))]
    (reduce #(bucket-fill %1 char (first %2) (second %2)) drawn neighbours)))
