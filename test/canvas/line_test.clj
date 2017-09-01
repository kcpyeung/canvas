(ns canvas.line-test
  (:require [clojure.test :refer :all]
            [canvas.canvas :refer :all]
            [canvas.line :refer :all]))

(let [canvas (canvas 10 8)]
  (deftest smallest-line
    (testing "the smallest line is one pixel"
      (is (= (list
               (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")
               (list "|" " " " " " " " " " " " " " " " " "|")
               (list "|" " " " " " " " " " " " " " " " " "|")
               (list "|" " " " " " " " " " " " " " " " " "|")
               (list "|" " " " " " " "*" " " " " " " " " "|")
               (list "|" " " " " " " " " " " " " " " " " "|")
               (list "|" " " " " " " " " " " " " " " " " "|")
               (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")) (line canvas 4 4 4 4)))))

  (deftest horizontal-lines
    (testing "valid lines"
      (testing "drawing a horizontal line in an empty space from left to right is the same as from right to left"
        (is (= (list
                 (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")
                 (list "|" " " " " " " " " " " " " " " " " "|")
                 (list "|" " " " " " " " " " " " " " " " " "|")
                 (list "|" "*" "*" "*" "*" "*" "*" "*" " " "|")
                 (list "|" " " " " " " " " " " " " " " " " "|")
                 (list "|" " " " " " " " " " " " " " " " " "|")
                 (list "|" " " " " " " " " " " " " " " " " "|")
                 (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")) (line canvas 1 3 7 3) (line canvas 7 3 1 3)))))

    (testing "invalid lines"
      (testing "can't draw past left border"
        (is (nil? (line canvas -1 3 7 3))))
      (testing "can't draw on left border"
        (is (nil? (line canvas 0 3 7 3))))
      (testing "can't draw on right border"
        (is (nil? (line canvas 1 3 9 3))))
      (testing "can't draw past right border"
        (is (nil? (line canvas 1 3 10 3))))
      (testing "can't draw past top border"
        (is (nil? (line canvas 1 -1 5 -1))))
      (testing "can't draw on top border"
        (is (nil? (line canvas 1 0 5 0))))
      (testing "can't draw past bottom border"
        (is (nil? (line canvas 1 8 5 8))))
      (testing "can't draw on bottom border"
        (is (nil? (line canvas 1 7 5 7))))))

  (deftest vertical-lines
    (testing "drawing a vertical line in an empty space from top to bottom is the same as bottom to top"
      (is (= (list
               (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")
               (list "|" " " " " " " " " " " " " " " " " "|")
               (list "|" " " " " "*" " " " " " " " " " " "|")
               (list "|" " " " " "*" " " " " " " " " " " "|")
               (list "|" " " " " "*" " " " " " " " " " " "|")
               (list "|" " " " " "*" " " " " " " " " " " "|")
               (list "|" " " " " "*" " " " " " " " " " " "|")
               (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")) (line canvas 3 2 3 6) (line canvas 3 6 3 2))))

    (testing "invalid lines"
      (testing "can't draw past top border"
        (is (nil? (line canvas 3 -1 3 5))))
      (testing "can't draw on top border"
        (is (nil? (line canvas 3 0 3 5))))
      (testing "can't draw past bottom border"
        (is (nil? (line canvas 3 1 3 8))))
      (testing "can't draw on bottom border"
        (is (nil? (line canvas 3 1 3 7))))
      (testing "can't draw past left border"
        (is (nil? (line canvas -1 1 -1 6))))
      (testing "can't draw on left border"
        (is (nil? (line canvas 0 1 0 6))))
      (testing "can't draw past right border"
        (is (nil? (line canvas 10 1 10 6))))
      (testing "can't draw on right border"
        (is (nil? (line canvas 9 1 9 6))))))

  (deftest diagonal-lines
    (testing "are not supported"
      (is (nil? (line canvas 3 5 6 6))))))

(run-tests)
