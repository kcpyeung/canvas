(ns canvas.canvas-test
  (:require [clojure.test :refer :all]
            [canvas.canvas :refer :all]))

(deftest test-make-canvas
  (testing "making a canvas of the specified dimension"
    (is (= (list
             (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "|" " " " " " " " " " " " " " " " " "|")
             (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")) (canvas 10 8)))))

(deftest test-invalid-canvas
  (testing "negative x yields nil"
    (is (nil? (canvas -10 8))))
  (testing "negative y yields nil"
    (is (nil? (canvas 10 -8))))
  (testing "zero x yields nil"
    (is (nil? (canvas 0 8))))
  (testing "zero y yields nil"
    (is (nil? (canvas 10 0)))))

(run-tests)
