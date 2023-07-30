(ns griffin.journal-entry
  (:require [clojure.spec.alpha :as s]))

(comment
  (s/explain :griffin.journal-entry/id
             "f81d4fae-7dec-11d0-a765-00a0c91e6bf6")
  (def uuid (java.util.UUID/randomUUID))
  (uuid? uuid)
  (s/valid? :griffin.journal-entry/id uuid)


  (s/valid? :griffin.journal-entry/line-item
            {:account-id #uuid "582e8a03-6106-42a8-897a-5b972438aee0"
             :amount 100
             :type :credit})

  (def line-item
    {:account-id #uuid "582e8a03-6106-42a8-897a-5b972438aee0"
     :amount 100
     :type :credit})

  (def line-items
    '({:account-id #uuid "582e8a03-6106-42a8-897a-5b972438aee0"
       :amount 100
       :type :credit}
      {:account-id #uuid "f2659b8f-426a-4aa7-a013-6290def47bfb"
       :amount 100
       :type :debit}))

  (s/valid? :griffin.journal-entry/line-items
            line-items)
  (java.util.UUID/randomUUID)

  (def journal-entry
    {:id #uuid "706bf012-1a5f-4967-999a-ca8c1b239cb6"
     :line-items '({:account-id #uuid "582e8a03-6106-42a8-897a-5b972438aee0"
                    :amount 100
                    :type :credit}
                   {:account-id #uuid "f2659b8f-426a-4aa7-a013-6290def47bfb"
                    :amount 100
                    :type :debit})})

  (s/valid? :griffin.journal-entry/journal-entry
            journal-entry)

  (get journal-entry :line-items)
  
  (def summary (reduce (fn [acc line-item]
                         (case (get line-item :type)
                           :credit (update acc :credit + (get line-item :amount))
                           :debit (update acc :debit + (get line-item :amount))))
                       {:credit 0 :debit 0}
                       line-items))
  (= (get summary :credit) (get summary :debit))
  :rcf)

(s/def ::id uuid?)
(s/def ::account-id uuid?)
(s/def ::type #{:credit :debit})
(s/def ::amount pos-int?)
(s/def ::line-item (s/keys :req-un [::account-id ::amount ::type]))
(s/def ::line-items (s/coll-of ::line-item :min-count 2))
(s/def ::journal-entry (s/keys :req-un [::id ::line-items]))


(defn is_journal_entry_balanced?
  "Check whether journal entry is balanced or not"
  [entry]
  (let [summary  (reduce (fn [acc line-item]
                           (case (get line-item :type)
                             :credit (update acc :credit + (get line-item :amount))
                             :debit (update acc :debit + (get line-item :amount))))
                         {:credit 0 :debit 0}
                         (get entry :line-items))]
    (= (get summary :credit) (get summary :debit))))