(ns paip.simple-program.simple)

;https://github.com/clojure/tools.trace
;enable trace
;(require 'clojure.tools.trace)
;(use 'clojure.tools.trace)
;(trace-ns 'paip.simple-program.simple)

;http://blog.tomeklipski.com/2013/04/running-and-debugging-clojure-code-with.html

(defn random-elt [choices] 
  "Choose an element from a list at random."
  (nth choices (rand (count choices)))
)

(defn one-of [set]
  "Pick one element of set, and make a list of it."
  (list (random-elt set))
)

(defn Article []     (one-of '(the a)))
(defn Noun []        (one-of '(man ball woman table)))
(defn Verb []        (one-of '(hit took saw liked)))

(defn noun-phrase [] (concat (Article) (Noun)))
(defn verb-phrase [] (concat (Verb) (noun-phrase)))

(defn sentence []    (concat (noun-phrase) (verb-phrase)))





