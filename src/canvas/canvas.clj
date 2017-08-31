(ns canvas.canvas)

(defn- horizontal-row [x]
  (->> (range x)
       (map (fn [_] "-"))))

(defn- vertical-row [x]
  (as-> (- x 2) v
        (range v)
        (map (fn [_] " ") v)
        (concat (list "|") v)
        (concat v (list "|"))))

(defn- vertical-rows [x y]
  (->> (- y 2)
       range
       (map (fn [_] (vertical-row x)))))

(defn canvas [x y]
  (if
    (and (pos? x) (pos? y))
    (as-> (vertical-rows x y) v
          (concat (list (horizontal-row x)) v)
          (concat v (list (horizontal-row x))))
    nil))
