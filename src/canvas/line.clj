(ns canvas.line)

(defn- sorted [[a b :as numbers]]
  (if (> b a) numbers [b a]))

(defn- out-of-bounds? [canvas x1 y1 x2 y2]
  (let [max-y (- (count canvas) 2)
        row (first canvas)
        max-x (- (count row) 2)]
    (or
      (not (pos? x1))
      (> x2 max-x)
      (not (pos? y1))
      (> y2 max-y))))

(defn- draw-line-on-canvas [canvas x1 y1 x2 y2 is-right-row? is-right-column?]
  (if (out-of-bounds? canvas x1 y1 x2 y2)
    canvas
    (letfn [(draw-columns [row] (map-indexed (fn [x-coord char-at-x-coord] (if (is-right-column? x-coord) "x" char-at-x-coord)) row))
            (draw-rows [y-coord row] (if (is-right-row? y-coord) (draw-columns row) row))]
      (map-indexed draw-rows canvas))))

(defmulti line (fn [canvas x1 y1 x2 y2]
                 (cond
                   (= y1 y2) :horizontal
                   (= x1 x2) :vertical
                   :default :diagonal)))

(defmethod line :horizontal [canvas x1 y1 x2 y2]
  (let [[x1' x2'] (sorted [x1 x2])
        is-right-row? (partial = y1)
        is-right-column? (set (range x1' (inc x2')))]
    (draw-line-on-canvas canvas x1' y1 x2' y2 is-right-row? is-right-column?)))

(defmethod line :vertical [canvas x1 y1 x2 y2]
  (let [[y1' y2'] (sorted [y1 y2])
        is-right-row? (set (range y1' (inc y2')))
        is-right-column? (partial = x1)]
    (draw-line-on-canvas canvas x1 y1' x2 y2' is-right-row? is-right-column?)))

(defmethod line :diagonal [canvas _ _ _ _] canvas)
