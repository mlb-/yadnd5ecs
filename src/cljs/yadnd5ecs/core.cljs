(ns yadnd5ecs.core
  (:require [om.core :as om :include-macros true]
            [om-tools.dom :as dom :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]))

(enable-console-print!)

(def app-state (atom {:name {:player "Bob"
                             :character "Bruenor"}
                      :race {:main :dwarf
                             :subrace :mountain}}))

(def races
  {:dwarf {:ability-score-increase [{:constitution 2}]
           :age "Dwarves mature at the same rate as humans, but
           they’re considered young until they reach the age of 50. On
           average, they live about 350 years."
           :alignment "Most dwarves are lawful, believing firmly in the benefits of a well-ordered society. They tend toward good as well, with a strong sense of fair play and a belief that everyone deserves to share in the benefits of a just order."
           :size "Dwarves stand between 4 and 5 feet tall and average about 150 pounds. Your size is Medium."
           :speed "25 feet"
           :traits ["Darkvision"
                    "Dwarven Resilience"
                    "Dwarven Combat Training"
                    "Tool Proficiency"
                    "Stonecunning"]
           :languages ["Common"
                       "Dwarvish"]
           :subraces {:hill {:ability-score-increases [{:wisdom 1}]
                             :traits ["Dwarven Toughness"]}
                      :mountain {:ability-score-increases [{:strength 2}]
                                 :traits ["Dwarven Armor Training"]}}}})

(defcomponent name-view
  [name owner]
  (render
   [_]
   (let [{:keys [player
                 character]} name]
     (dom/span
      (dom/h1 (str "Name: " player))
      (dom/h1 (str "Character: " character))))))

(defcomponent race-view
  [race owner]
  (render
   [_]
   (let [{:keys [main subrace]} race
         race-info (main races)
         subrace-info (-> race-info
                          :subraces
                          subrace)
         {:keys [traits languages speed]} subrace-info
         traits (into (:traits race-info)
                      traits)
         languages (into (:languages race-info)
                         languages)
         speed (or speed
                   (:speed race-info))]
     (dom/span
      (dom/h1 "Race stuff")
      (dom/h2 "Traits:"
              (dom/ul
               (map dom/li
                    traits)))
      (dom/h2 "Languages:"
              (dom/ul
               (map dom/li
                    languages)))
      (dom/h2 "Base speed: " speed)))))

(defcomponent app-view
  [app owner]
  (render
   [_]
   (dom/div
    (dom/h1 (str "Hello " (-> app :name :character)
                 (when-let [name (-> app :name :player)]
                   (str " (" name ")"))
                 "!"))
    (om/build name-view (:name app))
    (om/build race-view (:race app)))))

(defn main []
  (om/root
    app-view
    app-state
    {:target (. js/document (getElementById "app"))}))
