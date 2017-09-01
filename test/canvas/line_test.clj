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
             (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")) (line canvas 7 4 4 4) (line canvas 4 4 7 4)))))

  (testing "the smallest horizontal line is one pixel"
    (let [canvas (canvas 10 8)]
      (is (= (list
             (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" " " " " " " "*" " " " " " " " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")) (line canvas 4 4 4 4))))))

(deftest test-invalid-horizontal-lines
  (testing "can't draw past left border"
    (let [canvas (canvas 10 8)]
      (is (nil? (line canvas -1 3 7 3)))))
  (testing "can't draw on left border"
    (let [canvas (canvas 10 8)]
      (is (nil? (line canvas 0 3 7 3)))))
  (testing "can't draw on right border"
    (let [canvas (canvas 10 8)]
      (is (nil? (line canvas 1 3 9 3)))))
  (testing "can't draw past right border"
    (let [canvas (canvas 10 8)]
      (is (nil? (line canvas 1 3 10 3)))))
  (testing "can't draw past top border"
    (let [canvas (canvas 10 8)]
      (is (nil? (line canvas 1 -1 5 -1)))))
  (testing "can't draw on top border"
    (let [canvas (canvas 10 8)]
      (is (nil? (line canvas 1 0 5 0)))))
  (testing "can't draw past bottom border"
    (let [canvas (canvas 10 8)]
      (is (nil? (line canvas 1 8 5 8)))))
  (testing "can't draw on bottom border"
    (let [canvas (canvas 10 8)]
      (is (nil? (line canvas 1 7 5 7))))))

(run-tests)
