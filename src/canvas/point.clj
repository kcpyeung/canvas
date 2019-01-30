(ns canvas.point
  (:require
    [canvas.line :refer [line]]))

(defn point [canvas char x y]
  (line canvas char x y x y))

(defn is-empty? [canvas [x y]]
  (->> canvas
       (map-indexed (fn [i row] (if (= i y) row nil)))
       (remove nil?)
       first
       (map-indexed (fn [i col] (if (= i x) col nil)))
       (remove nil?)
       first
       (= " ")))

(defn- neighbouring-coords [x y]
  [[(dec x) (dec y)] [x (dec y)] [(inc x) (dec y)]
   [(dec x) y      ]             [(inc x) y]
   [(dec x) (inc y)] [x (inc y)] [(inc x) (inc y)]])

(defn neighbours [canvas x y]
  (->> (neighbouring-coords x y)
       (filter #(is-empty? canvas %))))
