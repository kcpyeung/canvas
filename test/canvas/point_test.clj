(ns canvas.point-test
  (:require [clojure.test :refer :all]
            [canvas.canvas :refer :all]
            [canvas.point :refer :all]
            [canvas.rectangle :refer :all]))

(let [canvas               (canvas 10 8)
      canvas-with-rectange (rectangle canvas 2 2 8 6)]
  (deftest valid-points
    (testing "is within canvas"
             (is
              (=
               (list
                (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")
                (list "|" " " " " " " " " " " " " " " " " "|")
                (list "|" " " " " " " " " " " " " " " " " "|")
                (list "|" " " " " " " " " " " " " " " " " "|")
                (list "|" " " " " " " " " " " " " " " " " "|")
                (list "|" " " " " " " "*" " " " " " " " " "|")
                (list "|" " " " " " " " " " " " " " " " " "|")
                (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-"))
               (point canvas "*" 4 5))))

    (testing "is on blank space"
             (is
              (=
               (list
                (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")
                (list "|" " " " " " " " " " " " " " " " " "|")
                (list "|" " " "x" "x" "x" "x" "x" "x" "x" "|")
                (list "|" " " "x" " " " " " " " " " " "x" "|")
                (list "|" " " "x" " " " " " " " " " " "x" "|")
                (list "|" " " "x" " " "*" " " " " " " "x" "|")
                (list "|" " " "x" "x" "x" "x" "x" "x" "x" "|")
                (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-"))
               (point canvas-with-rectange "*" 4 5)))))

  (deftest invalid-points
    (testing "is outside canvas"
             (is (= canvas (point canvas "*" 24 5))))

    (testing "is on a occupied spot"
             (is (= canvas-with-rectange (point canvas-with-rectange "*" 2 2)))))

  (deftest empty-detection
    (testing "empty cell"
             (is (true? (is-empty? canvas 3 3))))

    (testing "top border"
             (is (false? (is-empty? canvas 3 0))))

    (testing "left border"
             (is (false? (is-empty? canvas 0 3))))

    (testing "right border"
             (is (false? (is-empty? canvas 0 7))))

    (testing "bottom border"
             (is (false? (is-empty? canvas 9 3))))

    (testing "negative index"
             (is (false? (is-empty? canvas -9 3))))

    (testing "too large index"
             (is (false? (is-empty? canvas 900 3))))

    (testing "occupied point"
             (is (false? (is-empty? canvas-with-rectange 2 2)))))

  (deftest neighbouring-points
    (testing "when all 8 are empty, then they are returned"
             (is (= [[2 2] [3 2] [4 2] [2 3] [4 3] [2 4] [3 4] [4 4]] (neighbours canvas 3 3))))))

(run-tests)
