(ns paip.simple-program.simple)

;https://github.com/clojure/tools.trace
;enable trace
;(require 'clojure.tools.trace)poli
;(use 'clojure.tools.trace)
;(trace-ns 'paip.simple-program.simple)

;http://blog.tomeklipski.com/2013/04/running-and-debugging-clojure-code-with.html
;(use 'paip.simple-program.simple)

;http://juliangamble.com/blog/2012/07/13/amazing-lisp-books-living-again-in-clojure/

(declare sentence noun-phrase verb-phrase Article Noun Verb Adj PP Adj* PP* random-elt one-of)


(defn sentence []     (concat (noun-phrase) (verb-phrase)))

(defn noun-phrase [] (concat (Article) (Adj*) (Noun) (PP*)))
(defn verb-phrase []  (concat (Verb) (noun-phrase)))

(defn Article []      (one-of '(the a)))
(defn Noun []         (one-of '(man ball woman table)))
(defn Verb []         (one-of '(hit took saw liked)))
(defn Adj []          (one-of '(big little blue green adiabatic)))
(defn Prep []         (one-of '(to in by with on)))

(defn PP []           (concat (Prep) (noun-phrase)))

(defn Adj* []
  (if (= (rand-int 2) 0)
    nil
    (concat (Adj) (Adj*)))
  )

(defn PP* []
  ( if (random-elt '(t nil))
    (concat (PP) (PP*))
    nil )
  )

(defn random-elt [choices]
  "Choose an element from a list at random."
  (nth choices (rand (count choices)))
  )

(defn one-of [set]
  "Pick one element of set, and make a list of it."
  (list (random-elt set))
  )

;TRACE t64411: (paip.simple-program.simple/sentence)
;TRACE t64412: | (paip.simple-program.simple/noun-phrase)
;TRACE t64413: | | (paip.simple-program.simple/Article)
;TRACE t64414: | | | (paip.simple-program.simple/one-of (the a))
;TRACE t64415: | | | | (paip.simple-program.simple/random-elt (the a))
;TRACE t64415: | | | | => the
;TRACE t64414: | | | => (the)
;TRACE t64413: | | => (the)
;TRACE t64416: | | (paip.simple-program.simple/Adj*)
;TRACE t64416: | | => nil
;TRACE t64417: | | (paip.simple-program.simple/Noun)
;TRACE t64418: | | | (paip.simple-program.simple/one-of (man ball woman table))
;TRACE t64419: | | | | (paip.simple-program.simple/random-elt (man ball woman table))
;TRACE t64419: | | | | => man
;TRACE t64418: | | | => (man)
;TRACE t64417: | | => (man)
;TRACE t64420: | | (paip.simple-program.simple/PP*)
;TRACE t64421: | | | (paip.simple-program.simple/random-elt (t nil))
;TRACE t64421: | | | => nil
;TRACE t64420: | | => nil
;TRACE t64412: | => (the man)
;TRACE t64422: | (paip.simple-program.simple/verb-phrase)
;TRACE t64423: | | (paip.simple-program.simple/Verb)
;TRACE t64424: | | | (paip.simple-program.simple/one-of (hit took saw liked))
;TRACE t64425: | | | | (paip.simple-program.simple/random-elt (hit took saw liked))
;TRACE t64425: | | | | => hit
;TRACE t64424: | | | => (hit)
;TRACE t64423: | | => (hit)
;TRACE t64426: | | (paip.simple-program.simple/noun-phrase)
;TRACE t64427: | | | (paip.simple-program.simple/Article)
;TRACE t64428: | | | | (paip.simple-program.simple/one-of (the a))
;TRACE t64429: | | | | | (paip.simple-program.simple/random-elt (the a))
;TRACE t64429: | | | | | => a
;TRACE t64428: | | | | => (a)
;TRACE t64427: | | | => (a)
;TRACE t64430: | | | (paip.simple-program.simple/Adj*)
;TRACE t64430: | | | => nil
;TRACE t64431: | | | (paip.simple-program.simple/Noun)
;TRACE t64432: | | | | (paip.simple-program.simple/one-of (man ball woman table))
;TRACE t64433: | | | | | (paip.simple-program.simple/random-elt (man ball woman table))
;TRACE t64433: | | | | | => woman
;TRACE t64432: | | | | => (woman)
;TRACE t64431: | | | => (woman)
;TRACE t64434: | | | (paip.simple-program.simple/PP*)
;TRACE t64435: | | | | (paip.simple-program.simple/random-elt (t nil))
;TRACE t64435: | | | | => nil
;TRACE t64434: | | | => nil
;TRACE t64426: | | => (a woman)
;TRACE t64422: | => (hit a woman)
;TRACE t64411: => (the man hit a woman)
;(the man hit a woman)











