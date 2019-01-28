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

(defn- is-empty? [coord]
  (= coord " "))

(defn- draw-line-on-canvas [canvas char x1 y1 x2 y2 is-right-row? is-right-column?]
  (if (out-of-bounds? canvas x1 y1 x2 y2)
    canvas
    (letfn [(draw-columns [row] (map-indexed (fn [x-coord char-at-x-coord] (if (and (is-empty? char-at-x-coord) (is-right-column? x-coord)) char char-at-x-coord)) row))
            (draw-rows [y-coord row] (if (is-right-row? y-coord) (draw-columns row) row))]
      (map-indexed draw-rows canvas))))

(defmulti draw-line (fn [canvas char x1 y1 x2 y2]
                 (cond
                   (= y1 y2) :horizontal
                   (= x1 x2) :vertical
                   :default :diagonal)))

(defmethod draw-line :horizontal [canvas char x1 y1 x2 y2]
  (let [[x1' x2'] (sorted [x1 x2])
        is-right-row? (partial = y1)
        is-right-column? (set (range x1' (inc x2')))]
    (draw-line-on-canvas canvas char x1' y1 x2' y2 is-right-row? is-right-column?)))

(defmethod draw-line :vertical [canvas char x1 y1 x2 y2]
  (let [[y1' y2'] (sorted [y1 y2])
        is-right-row? (set (range y1' (inc y2')))
        is-right-column? (partial = x1)]
    (draw-line-on-canvas canvas char x1 y1' x2 y2' is-right-row? is-right-column?)))

(defmethod draw-line :diagonal [canvas _ _ _ _ _] canvas)

(defn line
  ([canvas x1 y1 x2 y2] (line canvas "x" x1 y1 x2 y2))
  ([canvas char x1 y1 x2 y2] (draw-line canvas char x1 y1 x2 y2)))
