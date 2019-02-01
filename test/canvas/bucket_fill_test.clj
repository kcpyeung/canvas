(ns canvas.bucket-fill-test
  (:require [clojure.test :refer :all]
            [canvas.canvas :refer :all]
            [canvas.line :refer :all]
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
                   (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")) (bucket-fill canvas "o" 4 4)))))

  (deftest filling-canvas-with-objects
    (testing "filling inside"
             (is
              (= (list
                  (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")
                  (list "|" " " " " " " " " " " " " " " " " "|")
                  (list "|" " " "x" "x" "x" "x" "x" " " " " "|")
                  (list "|" " " "x" "o" "o" "o" "x" " " " " "|")
                  (list "|" " " "x" "o" "o" "o" "x" " " " " "|")
                  (list "|" " " "x" "o" "o" "o" "x" " " " " "|")
                  (list "|" " " "x" "x" "x" "x" "x" " " " " "|")
                  (list "|" " " " " " " " " " " " " " " " " "|")
                  (list "|" " " " " " " " " " " " " " " " " "|")
                  (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")) (bucket-fill canvas-with-rectange "o" 4 4))))

    (testing "filling outside"
             (is
              (= (list
                  (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")
                  (list "|" "o" "o" "o" "o" "o" "o" "o" "o" "|")
                  (list "|" "o" "x" "x" "x" "x" "x" "o" "o" "|")
                  (list "|" "o" "x" " " " " " " "x" "o" "o" "|")
                  (list "|" "o" "x" " " " " " " "x" "o" "o" "|")
                  (list "|" "o" "x" " " " " " " "x" "o" "o" "|")
                  (list "|" "o" "x" "x" "x" "x" "x" "o" "o" "|")
                  (list "|" "o" "o" "o" "o" "o" "o" "o" "o" "|")
                  (list "|" "o" "o" "o" "o" "o" "o" "o" "o" "|")
                  (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")) (bucket-fill canvas-with-rectange "o" 1 1))))

    (testing "filling around line"
             (let [canvas-with-line (line canvas 2 2 6 2)]
               (is
                (=
                  (list
                   (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")
                   (list "|" "o" "o" "o" "o" "o" "o" "o" "o" "|")
                   (list "|" "o" "x" "x" "x" "x" "x" "o" "o" "|")
                   (list "|" "o" "o" "o" "o" "o" "o" "o" "o" "|")
                   (list "|" "o" "o" "o" "o" "o" "o" "o" "o" "|")
                   (list "|" "o" "o" "o" "o" "o" "o" "o" "o" "|")
                   (list "|" "o" "o" "o" "o" "o" "o" "o" "o" "|")
                   (list "|" "o" "o" "o" "o" "o" "o" "o" "o" "|")
                   (list "|" "o" "o" "o" "o" "o" "o" "o" "o" "|")
                   (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-"))
                  (bucket-fill canvas-with-line "o" 1 1)))))))


(run-tests)
