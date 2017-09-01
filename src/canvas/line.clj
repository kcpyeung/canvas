(ns canvas.line)

(defn line [canvas x1 y1 x2 y2]
  (let [row (nth canvas (inc y1))
        all-char-indexes (range (count row))
        line-char-position? (set (range x1 (inc x2)))]
    (letfn [(draw-line [x-coord] (if (line-char-position? x-coord) "*" (nth row x-coord)))]
      (let [row-indexes (range (count canvas))
            drawn-line (map draw-line all-char-indexes)]
        (letfn[(place-row-in-canvas [y-coord] (if (= y1 y-coord) drawn-line (nth canvas y-coord)))]
          (map place-row-in-canvas row-indexes))))))
