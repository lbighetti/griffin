(ns griffin.journal-entry
  (:require [clojure.spec.alpha :as s]))

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
  (if (not (s/valid? :griffin.journal-entry/journal-entry entry))
    (throw (IllegalArgumentException. "invalid journal entry"))
    (let [summary  (reduce (fn [acc line-item]
                             (case (get line-item :type)
                               :credit (update acc :credit + (get line-item :amount))
                               :debit (update acc :debit + (get line-item :amount))))
                           {:credit 0 :debit 0}
                           (get entry :line-items))]
      (= (get summary :credit) (get summary :debit)))))