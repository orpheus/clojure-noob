(defproject repl "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :repl-options {:init-ns repl.core})


(require '[clojure.string :as s])

(defn parse-int [x] (Integer/parseInt (str x)))

(defn is-palindrome? [x]
  (= (parse-int x) (parse-int (s/reverse (str x)))))

(defn find-largest-3-digit-palindrome
  ([]
   (find-largest-3-digit-palindrome 999 999 0))
  ([x y p]
   (if (= x 99) p)
   (if (= y 99) (find-largest-3-digit-palindrome (- x 1) 999 p)
                (if (is-palindrome? (* x y))
                  (if (> (* x y) p)
                    (find-largest-3-digit-palindrome x (- y 1) (* x y))
                    (find-largest-3-digit-palindrome x (- y 1) p))
                  (find-largest-3-digit-palindrome x (- y 1) p)))))