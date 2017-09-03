(ns canvas.rectangle-test
  (:require [clojure.test :refer :all]
            [canvas.rectangle :refer [rectangle]]
            [canvas.canvas :refer [canvas]]))

(let [canvas (canvas 10 8)]
  (deftest smallest-rectangle
    (testing "is 3x3"
      (is (= (list
               (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")
               (list "|" " " " " " " " " " " " " " " " " "|")
               (list "|" " " "x" "x" "x" " " " " " " " " "|")
               (list "|" " " "x" " " "x" " " " " " " " " "|")
               (list "|" " " "x" "x" "x" " " " " " " " " "|")
               (list "|" " " " " " " " " " " " " " " " " "|")
               (list "|" " " " " " " " " " " " " " " " " "|")
               (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")) (rectangle canvas 2 2 4 4))))))

(run-tests)
