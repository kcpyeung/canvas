(ns canvas.line-test
  (:require [clojure.test :refer :all]
            [canvas.canvas :refer :all]
            [canvas.line :refer :all]))

(let [canvas (canvas 10 8)]
  (deftest smallest-line
    (testing "is one pixel"
      (is (= (list
               (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")
               (list "|" " " " " " " " " " " " " " " " " "|")
               (list "|" " " " " " " " " " " " " " " " " "|")
               (list "|" " " " " " " " " " " " " " " " " "|")
               (list "|" " " " " " " "x" " " " " " " " " "|")
               (list "|" " " " " " " " " " " " " " " " " "|")
               (list "|" " " " " " " " " " " " " " " " " "|")
               (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")) (line canvas 4 4 4 4)))))

  (deftest horizontal-lines
    (testing "can be drawn bidirectionally"
      (is (= (list
               (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")
               (list "|" " " " " " " " " " " " " " " " " "|")
               (list "|" " " " " " " " " " " " " " " " " "|")
               (list "|" "x" "x" "x" "x" "x" "x" "x" " " "|")
               (list "|" " " " " " " " " " " " " " " " " "|")
               (list "|" " " " " " " " " " " " " " " " " "|")
               (list "|" " " " " " " " " " " " " " " " " "|")
               (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")) (line canvas 1 3 7 3) (line canvas 7 3 1 3))))

    (testing "are invalid if"
      (testing "past left border"
        (is (nil? (line canvas -1 3 7 3))))
      (testing "on left border"
        (is (nil? (line canvas 0 3 7 3))))
      (testing "on right border"
        (is (nil? (line canvas 1 3 9 3))))
      (testing "past right border"
        (is (nil? (line canvas 1 3 10 3))))
      (testing "past top border"
        (is (nil? (line canvas 1 -1 5 -1))))
      (testing "on top border"
        (is (nil? (line canvas 1 0 5 0))))
      (testing "past bottom border"
        (is (nil? (line canvas 1 8 5 8))))
      (testing "on bottom border"
        (is (nil? (line canvas 1 7 5 7))))))

  (deftest vertical-lines
    (testing "can be drawn bidirectionally"
      (is (= (list
               (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")
               (list "|" " " " " " " " " " " " " " " " " "|")
               (list "|" " " " " "x" " " " " " " " " " " "|")
               (list "|" " " " " "x" " " " " " " " " " " "|")
               (list "|" " " " " "x" " " " " " " " " " " "|")
               (list "|" " " " " "x" " " " " " " " " " " "|")
               (list "|" " " " " "x" " " " " " " " " " " "|")
               (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")) (line canvas 3 2 3 6) (line canvas 3 6 3 2))))

    (testing "are invalid if"
      (testing "past top border"
        (is (nil? (line canvas 3 -1 3 5))))
      (testing "on top border"
        (is (nil? (line canvas 3 0 3 5))))
      (testing "past bottom border"
        (is (nil? (line canvas 3 1 3 8))))
      (testing "on bottom border"
        (is (nil? (line canvas 3 1 3 7))))
      (testing "past left border"
        (is (nil? (line canvas -1 1 -1 6))))
      (testing "on left border"
        (is (nil? (line canvas 0 1 0 6))))
      (testing "past right border"
        (is (nil? (line canvas 10 1 10 6))))
      (testing "on right border"
        (is (nil? (line canvas 9 1 9 6))))))

  (deftest diagonal-lines
    (testing "are not supported"
      (is (nil? (line canvas 3 5 6 6))))))

(run-tests)
