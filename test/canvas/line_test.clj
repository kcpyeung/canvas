(ns canvas.line-test
  (:require [clojure.test :refer :all]
            [canvas.canvas :refer :all]
            [canvas.line :refer :all]))

(deftest test-valid-horizontal-lines
  (testing "drawing a horizontal line in an empty space, from left to right"
    (let [canvas (canvas 10 8)]
      (is (= (list
             (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" "*" "*" "*" "*" "*" "*" "*" " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")) (line canvas 1 3 7 3)))))

  (testing "drawing a horizontal line in an empty space, from right to left"
    (let [canvas (canvas 10 8)]
      (is (= (list
             (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" " " " " " " "*" "*" "*" "*" " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")) (line canvas 7 4 4 4) (line canvas 4 4 7 4))))))

(deftest test-invalid-horizontal-lines
  (testing "can't draw past left border"
    (let [canvas (canvas 10 8)]
      (is (nil? (line canvas -1 3 7 3)))))
  (testing "can't draw on left border"
    (let [canvas (canvas 10 8)]
      (is (nil? (line canvas 0 3 7 3)))))

  )

(run-tests)
