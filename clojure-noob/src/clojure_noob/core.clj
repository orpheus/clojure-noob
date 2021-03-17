(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a majestical elf"))

(println "In a Castle yonder the secret forest lives faeries and pixies")

(defn wand
  []
  (println "ZzzzSwoosh ... .. . *starlight*"))

(+ 1 (* 2 3) 4)

(def names
  ["Ryan" "Roark" "Rearden" "Raeyna" "Raenor" "Ragnar" "Raeyn"])

(defn error-message
  [severity]
  (str "IT'S A: "
       (if (= severity :mild)
         "HEIFER"
         "BEAR")))

(def name "Protogonus")
(str "\"Phanes\" - " name)

;; c-x c-e - run expression before cursor

;; Maps - :keyword <any> - value can be of any type even functions
(def places {:SanDiego ["Ocean Beach" "Pacific Beach" "La Jolla"]
             :Folsom ["American River" "Beals Points" "Briggs Ranch"]})
(def beaches (hash-map :SanDiego ["Ocean Beach" "Mission Beach" {:Pacific "PB"}]))
;; get
(get beaches :SanDiego)
(beaches :SanDiego)
(:SanDiego beaches)
;; get-in
(get-in beaches [:SanDiego 2 :Pacific])
;; provide default
(get beaches :LagunaBeach "Beach doesn't exist")

;; Vector
(def gods ["Phanes" "Protogonus"])
(def gods (vector "Phanes" "Protogonus"))
;; conj adds elements to the end of the vector
(conj gods "Nyx")
;; get at index
(get gods 0)
(gods 0)

;; Lists
'(:I :Am :A :List)
(def heroes `(:Ganicus :Spartacus :Crixu))
(def heroes (list :Theseus :Hercules :Odysseus))
;; conj - adds to the beginning of the list
(conj heroes :Hektor :Achilles)
;; get at index
(nth heroes 1)

;; Sets

;; hash
;; #{"apollo" "divine" 11 :god}
(hash-set "apollo" "divine" 11 :god)
;; sets are unique values only, this merges the duplicates
(hash-set 1 1 2 2)
;; conj - adds to end
(conj #{"apollo" "divine"} "apollo")
(conj #{"apollo" "divine"} "zeus")

;; create from vectors and lists
(set [1 2 3 4])
(set (conj '(1 2 3 4) 5))

;; check for existance with contains or get
(def heroes #{:theseus :hercules :odysseus})
(contains? heroes :theseus)
(get heroes :theseus)

;; Calling Functions
((or + -) 1 2 3)
;; result of 'and' is the first falsy value or last truthy
((and (= 1 1) +) 1 2 3)
((first [+ 0]) 1 2 3)

;; - not valid fn calls
;; (1 2 3 4)
;; ("test" 1 2 3)
;; these return an error like
(def invalid-fn-error "<x> cannot be cast to clojure.lang.IFn just means that
 you’re trying to use something as a function when it’s not.")

;; more-funcs
(inc 1.1)
;; fns takings fns
(map inc [0 1 2 3])

;; Defining Functions

;; defn
;; Function Name
;; Docstring (defining the function) - optional
;; Paremeters listed in brackets
;; Fn body

(defn zelda-or-fire-emblem
  "Zelda or Fire Emblem, which is it?"
  [game]
  (if (= game "fe") "Fire Emblem"
      "Zelda: Ocarina"))
(doc zelda-or-fire-emblem)

;; the number of parameters is the function's arity
;; Arity overloading - provide default vales for arguments
;; two-param is 2-arity
(defn katarinas-thunderbolt
  "A strong thunder magic spell"
  ([magic luck]
   (str "I use Katarina's thunderbolt for " magic " magic damage with a luck chance of " luck " percent"))
  ([magic]
   (katarinas-thunderbolt magic 1)))

;; multiple Arity functions don't need to call itself. they can do completely
;; seperate tasks

(defn PaulAtreides
  "Pray or Fight"
  ([]
   (str "I will not Fear. Fear is the mind-killer. I will face my fear."))
  ([warcry]
   (str "Hear me! " warcry)))

;; variable arity function with a rest-parameter
(defn i-like [this] (str "I like " this))
(defn games-i-like [& games]
  (clojure.string/join " and " (map i-like games)))

(games-i-like "Zelda" "Fire Emblem")

;; when you mix normal parameters and rest parameters, the rest parameter comes last
(defn drums
  [most-important-drum & rest-of-kit]
  (str "The " most-important-drum " is more important than the following: "
       (clojure.string/join ", " rest-of-kit)))

(drums "kick" "snare" "hats" "toms")

;; Destruction parameters
(defn desk-items
  "I will take only the first item you give me"
  [[first-desk-item]]
  (str "The first item listed was: " first-desk-item))

(desk-items ["Kali" "Perseus" "3ds"])

(defn two-desk-items
  "I will take two desk items"
  [[first-item second-item]]
  (str "You passed in: " (clojure.string/join " and " (list first-item second-item))))

(two-desk-items ["Kali" "Perseus"])

(defn join-and
  "Join a list of strings together with 'and'"
  [aleyist]
  (str (clojure.string/join " and " aleyist)))

(defn all-desk-items
  "I will list all the items you pass me"
  [first-itm second-itm & rest-itm]
  (str "Here is everything on your desk: "
       (join-and (list first-itm second-itm (join-and rest-itm)))))

(all-desk-items "Kali" "Perseus" "Rokits" "3DS" "iPod")

(defn dif-arity-destructure
  "Note here that the destructring form"
  ([first-arg second-arg & rest-args]
   (join-and (list first-arg second-arg (join-and rest-args))))
  ([[first-thing-in-vector-of-arg-1] [first-thing-in-vector-of-arg-2]]
   (str first-thing-in-vector-of-arg-1 " and " first-thing-in-vector-of-arg-2)))

(dif-arity-destructure 1 2 3 4)
(dif-arity-destructure [1 2] [3 4])

;; m-n cider-jack-in - open repl
;; c-c m-n m-n - switch to current namespace
;; c-c c-k - compile current code for use in buffer and repl
;; c-x c-e - run the command previous of cursor

;; Destructure Maps
(defn holy-items
  "Listen a holy item for Christ and Arthur"
  ;; Associate the name 'christ-item' with the value associated
  ;; with the key ':christ' in the map passed as an arg
  [{ christ-item :christ arthur-item :arthur  }]
  (println (str "Holy Item, Christ: " christ-item))
  (println (str "Holy Item, Arthur: " arthur-item)))

;; (holy-items {:christ "Grail" :arthur "Excalibur"})

(defn holy-items-2
  "Another way to break keys out of a map while still keeping access
  to the original map"
  [{ :keys [christ-item arthur-item] :as holy-items-map }]
  (println (str "Christ's Item: " christ-item))
  (println (str "Arthur's Item: " arthur-item))
  (clojure.pprint/pprint holy-items-map))

(holy-items-2 {:christ-item "Spear of Longinus" :arthur-item "Excalibur" })

;; In general, you can think of destructuring as instructing Clojure on how to
;; associate names with values in a list, map, set, or vector.

;; Function Body
;; - returns the last form evaluated

(defn random-processess
  []
  (+ 42 72)
  (map + (list 1 2 3 4))
  "My Name is Raeyn")
(random-processess)

;; Anonymous functions
;; 1st form
(fn [param-list]
  ;; function body
 )

(map (fn [name] (str "Hello, " name)) ["Lord Chrom" "Princess Lucina"])
;; associate your anonymous fn with a name
(def anonymous-function (fn [x] (* x 3)))

;; 2nd form
;; #(* % 3)

(#(* % 3) 8)
(map #(str "Goodday' " %) ["Queen Sumia" "Your Highness Lissa"])

;; the '#' desigates an anonymous function
;; the '%' is a 'reader macro' that represents the argument passed in

;; take in multiple args
(#(str %1 " and " %2) "Robin" "Tikki")
;; use the 'rest' parameter with '%&'
(str (#(clojure.string/join ", " %&) "Lucina" "Cynthia")
     ", and Severa are friends")
;; above is not a good example because 'str' doesn't need to be passed
;; a function. anonymouse functions are good for things like 'map'
;; where you do need to pass a function

;; #(println (join-and %1)) is the anonymous fn
(map #(println (join-and %1)) [["Marth" "Caeda"] ["Chrom" "Sumia"]])

;; Returning Functions (closures)
;; - can access all the variables in the scope of the created fn
(defn use-power
  "Create power functions"
  [power]
  #(str "I use " power " for " % " damage!"))

(def fire-power (use-power "Fire"))
(fire-power 9)
