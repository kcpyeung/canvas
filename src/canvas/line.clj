(ns canvas.line)

(defn- sorted-x1-x2 [[a b :as numbers]]
  (if (> b a) numbers [b a]))

(defn- out-of-bounds? [canvas x1 y1 x2]
  (let [max-y (- (count canvas) 2)
        row (first canvas)
        max-x (- (count row) 2)]
    (or
      (not (pos? x1))
      (> x2 max-x)
      (not (pos? y1))
      (> y1 max-y))))

(defn line [canvas x1 y1 x2 y2]
  (let [[x1' x2'] (sorted-x1-x2 [x1 x2])
        should-draw-at? (set (range x1' (inc x2')))]
    (if (out-of-bounds? canvas x1' y1 x2')
      nil
      (let [row (nth canvas (inc y1))]
        (letfn [(draw-line [x-coord] (if (should-draw-at? x-coord) "*" (nth row x-coord)))]
          (let [all-x-indexes (range (count row))
                drawn-line (map draw-line all-x-indexes)
                all-y-indexes (range (count canvas))]
            (letfn [(place-row-in-canvas [y-coord] (if (= y1 y-coord) drawn-line (nth canvas y-coord)))]
              (map place-row-in-canvas all-y-indexes))))))))
