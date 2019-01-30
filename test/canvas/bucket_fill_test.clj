(ns canvas.bucket-fill-test
  (:require [clojure.test :refer :all]
            [canvas.canvas :refer :all]
            [canvas.point :refer :all]
            [canvas.rectangle :refer :all]
            [canvas.bucket-fill :refer :all]))

(let [canvas               (canvas 10 10)
      canvas-with-rectange (rectangle canvas 2 2 6 6)]

  (deftest filling-empty-canvas
    (testing "entire canvas is filled"
             (is
               (= (list
                   (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")
                   (list "|" "o" "o" "o" "o" "o" "o" "o" "o" "|")
                   (list "|" "o" "o" "o" "o" "o" "o" "o" "o" "|")
                   (list "|" "o" "o" "o" "o" "o" "o" "o" "o" "|")
                   (list "|" "o" "o" "o" "o" "o" "o" "o" "o" "|")
                   (list "|" "o" "o" "o" "o" "o" "o" "o" "o" "|")
                   (list "|" "o" "o" "o" "o" "o" "o" "o" "o" "|")
                   (list "|" "o" "o" "o" "o" "o" "o" "o" "o" "|")
                   (list "|" "o" "o" "o" "o" "o" "o" "o" "o" "|")
                   (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")) (bucket-fill canvas "o" 4 4))))))


(run-tests)
