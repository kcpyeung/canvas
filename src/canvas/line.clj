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

(defmulti line (fn [canvas x1 y1 x2 y2]
                 (cond
                   (= y1 y2) :horizontal
                   (= x1 x2) :vertical
                   :default :diagonal)))

(defmethod line :horizontal [canvas x1 y1 x2 y2]
  (let [[x1' x2'] (sorted [x1 x2])
        should-draw-at? (set (range x1' (inc x2')))]
    (if (out-of-bounds? canvas x1' y1 x2' y2)
      nil
      (let [row (nth canvas (inc y1))]
        (letfn [(draw-line [x-coord] (if (should-draw-at? x-coord) "x" (nth row x-coord)))]
          (let [all-x-indexes (range (count row))
                drawn-line (map draw-line all-x-indexes)
                all-y-indexes (range (count canvas))]
            (letfn [(place-row-in-canvas [y-coord] (if (= y1 y-coord) drawn-line (nth canvas y-coord)))]
              (map place-row-in-canvas all-y-indexes))))))))

(defmethod line :vertical [canvas x1 y1 x2 y2]
  (let [[y1' y2'] (sorted [y1 y2])]
    (if (out-of-bounds? canvas x1 y1' x2 y2')
      nil
      (let [all-x-indexes (range (count (first canvas)))
            all-y-indexes (range (count canvas))
            is-right-row? (set (range y1' (inc y2')))
            is-right-column? (partial = x1)]
        (letfn [(draw-column [row] (map #(if (is-right-column? %1) "x" (nth row %1)) all-x-indexes))
                (draw-row [y-coord row]
                  (if (is-right-row? y-coord)
                    (draw-column row)
                    row))]
          (map draw-row all-y-indexes canvas))))))

(defmethod line :diagonal [_ _ _ _ _] nil)
