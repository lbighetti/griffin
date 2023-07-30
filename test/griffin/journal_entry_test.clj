(ns griffin.journal-entry-test
  (:require [clojure.test :refer :all]
            [griffin.journal-entry :refer :all]))

(def balanced-journal-entry
  {:id #uuid "706bf012-1a5f-4967-999a-ca8c1b239cb6"
   :line-items '({:account-id #uuid "582e8a03-6106-42a8-897a-5b972438aee0"
                  :amount 100
                  :type :credit}
                 {:account-id #uuid "f2659b8f-426a-4aa7-a013-6290def47bfb"
                  :amount 100
                  :type :debit})})
(def imbalanced-journal-entry
  {:id #uuid "706bf012-1a5f-4967-999a-ca8c1b239cb6"
   :line-items '({:account-id #uuid "582e8a03-6106-42a8-897a-5b972438aee0"
                  :amount 100
                  :type :credit}
                 {:account-id #uuid "f2659b8f-426a-4aa7-a013-6290def47bfb"
                  :amount 50
                  :type :debit})})

(deftest is-journal-entry-balanced-test
  (testing "with a balanced journal entry"
    (is (= (griffin.journal-entry/is_journal_entry_balanced? balanced-journal-entry) true)))
  (testing "with an imbalanced journal entry"
    (is (= (griffin.journal-entry/is_journal_entry_balanced? imbalanced-journal-entry) false))))
