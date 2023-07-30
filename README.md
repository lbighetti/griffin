# Griffin Backend Task

## Context
> section copied from Griffin email

Banks use double entry bookkeeping to keep track of all money as a way of
reducing mistakes. In double entry accounting, you can't just add or subtract
from the balance of an account, you must also keep track of where it goes. So
for example, if you take money out at the ATM, it's not just "subtract £20 from
your account", the bank tracks that as "debit £20 from your account, and credit
£20 to the 'cash' account". In that way, we always know where the money came
from, and where it's going.

The ledger keeps track of the balance of all accounts as a series of journal
entries.

A journal entry consists of multiple line items. Each line item contains an
account id, an amount, and a type flag specifying whether it is a debit or
credit. To be considered _balanced_, the line items within the journal entry
must balance, i.e. the sum of all credits must equal the sum of all debits.

## Task
> section copied from Griffin email

Given the Clojure specs in `journal_entry.clj`, write a function that takes a
`::journal-entry`, and returns whether said journal entry is balanced or not.

-----

## Setup

The project requires the following:
- Clojure
- Java
- Leiningen

The project was developed using the following versions:
- Clojure 1.11.1
- Java 20.0.2
- Leiningen 2.9.9

## Running tests

To run tests simply do:
- `lein test`

## Reasoning and notes

The first thing to note is this is my first time writing Clojure.
Before getting started with this task I did some basic clojure exercises to get familiar with the syntax
and data structures.

I researched how development is usually done, and I tried to follow recommendations as best as I could.
Overal, I:
- Used Rich comments like `(comment ... :rfc)` to draft and progress assembling the correct data and also developing the requested function.
- Followed a REPL-driven development approach
    - I've used Calva plugin in VScode
    - Developed mostly by running code directly in my editor by means of a Jack-in REPL, and sometimes calling functions from the REPL itself.
- Used Leiningen to help me create the project, structure files and run tests.
    - I know Leiningen is not required, but it is a well estabilished tool and seemed reasonable to use it.
    - It was a good experience and easy jump for me as it's quite similar to elixir's mix tool (mix was indeed largely inspired and influenced by Leiningen)
- Used `s/valid?` to check if my assembled datastructures followed the spec corretly and `s/explain` to debug issues.