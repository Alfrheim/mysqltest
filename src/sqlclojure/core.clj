(ns sqlclojure.core
  (:require [clojure.java.jdbc :as j])
  (:gen-class))

;; http://davetorre.github.io/Clojure-JDBC/

(def mysql-db {:subprotocol "mysql"
               :subname "//127.0.0.1:3306/dummy"
               :user "dummy"
               :password "dummy"})

(j/insert! mysql-db :fruit
           {:name "Apple" :appearance "rosy" :cost 24}
           {:name "Orange" :appearance "round" :cost 49})
;; ({:generated_key 1} {:generated_key 2})


(j/query mysql-db
         ["select * from fruit where appearance = ?" "rosy"]
         :row-fn :cost)

(defn truncate [table_name]
  (j/execute! mysql-db ["truncate ?" table_name]))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
