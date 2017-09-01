(ns canvas.canvas-test
  (:require [clojure.test :refer :all]
            [canvas.canvas :refer :all]))

(deftest a-canvas-is-valid-if
  (testing "its dimensions are positive"
    (is (= (list
             (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")) (canvas 10 8)))))

(deftest a-canvas-is-invalid-if
  (testing "its width is negative"
    (is (nil? (canvas -10 8))))
  (testing "its height is negative"
    (is (nil? (canvas 10 -8))))
  (testing "its width is zero"
    (is (nil? (canvas 0 8))))
  (testing "its height is zero"
    (is (nil? (canvas 10 0)))))

(run-tests)
