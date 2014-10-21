(ns paip.simple-program.rules)

;(use 'clojure.tools.trace)
;(trace-ns 'paip.simple-program.rules)

(use 'paip.simple-program.rules)

(def ^{:doc "A grammar for a trivial subset of English."}
  ^:dynamic *simple-grammar*
  {:sentence [:noun-phrase :verb-phrase]
   :noun-phrase [:Article :Noun]
   :verb-phrase [:Verb :noun-phrase]
   :Article #{"the" "a"}
   :Noun #{"man" "ball" "woman" "table"}
   :Verb #{"hit" "took" "saw" "liked"}})

(def ^:dynamic *grammar* *simple-grammar*)

(defn rule-lhs [rule]
  "The left-hand side of a rule."
  (first rule)
)

(defn rule-rhs [rule]
  "The right-hand side of a rule."
  (rest (rest rule))
)

(defn rewrites [category]
  "Return a list of the possible rewrites for this category."
  (rule-rhs (assoc category *grammar*)))

(defn generate [phrase]
  (cond (get *grammar* phrase) (generate (get *grammar* phrase))
    (sequential? phrase) (mapcat generate phrase)
    (set? phrase)  (generate (rand-nth (seq phrase)))
    :else [phrase]))

;TRACE t484: (paip.simple-program.rules/generate :sentence)
;TRACE t485: | (paip.simple-program.rules/generate [:noun-phrase :verb-phrase])
;TRACE t486: | | (paip.simple-program.rules/generate :noun-phrase)
;TRACE t487: | | | (paip.simple-program.rules/generate [:Article :Noun])
;TRACE t488: | | | | (paip.simple-program.rules/generate :Article)
;TRACE t489: | | | | | (paip.simple-program.rules/generate #{"a" "the"})
;TRACE t490: | | | | | | (paip.simple-program.rules/generate "the")
;TRACE t490: | | | | | | => ["the"]
;TRACE t489: | | | | | => ["the"]
;TRACE t488: | | | | => ["the"]
;TRACE t491: | | | | (paip.simple-program.rules/generate :Noun)
;TRACE t492: | | | | | (paip.simple-program.rules/generate #{"table" "man" "woman" "ball"})
;TRACE t493: | | | | | | (paip.simple-program.rules/generate "table")
;TRACE t493: | | | | | | => ["table"]
;TRACE t492: | | | | | => ["table"]
;TRACE t491: | | | | => ["table"]
;TRACE t487: | | | => ("the" "table")
;TRACE t486: | | => ("the" "table")
;TRACE t494: | | (paip.simple-program.rules/generate :verb-phrase)
;TRACE t495: | | | (paip.simple-program.rules/generate [:Verb :noun-phrase])
;TRACE t496: | | | | (paip.simple-program.rules/generate :Verb)
;TRACE t497: | | | | | (paip.simple-program.rules/generate #{"liked" "saw" "hit" "took"})
;TRACE t498: | | | | | | (paip.simple-program.rules/generate "hit")
;TRACE t498: | | | | | | => ["hit"]
;TRACE t497: | | | | | => ["hit"]
;TRACE t496: | | | | => ["hit"]
;TRACE t499: | | | | (paip.simple-program.rules/generate :noun-phrase)
;TRACE t500: | | | | | (paip.simple-program.rules/generate [:Article :Noun])
;TRACE t501: | | | | | | (paip.simple-program.rules/generate :Article)
;TRACE t502: | | | | | | | (paip.simple-program.rules/generate #{"a" "the"})
;TRACE t503: | | | | | | | | (paip.simple-program.rules/generate "the")
;TRACE t503: | | | | | | | | => ["the"]
;TRACE t502: | | | | | | | => ["the"]
;TRACE t501: | | | | | | => ["the"]
;TRACE t504: | | | | | | (paip.simple-program.rules/generate :Noun)
;TRACE t505: | | | | | | | (paip.simple-program.rules/generate #{"table" "man" "woman" "ball"})
;TRACE t506: | | | | | | | | (paip.simple-program.rules/generate "man")
;TRACE t506: | | | | | | | | => ["man"]
;TRACE t505: | | | | | | | => ["man"]
;TRACE t504: | | | | | | => ["man"]
;TRACE t500: | | | | | => ("the" "man")
;TRACE t499: | | | | => ("the" "man")
;TRACE t495: | | | => ("hit" "the" "man")
;TRACE t494: | | => ("hit" "the" "man")
;TRACE t485: | => ("the" "table" "hit" "the" "man")
;TRACE t484: => ("the" "table" "hit" "the" "man")
;("the" "table" "hit" "the" "man")