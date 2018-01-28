This is a small application to stimulate the sale notification system.
the program is writen in Java 8 and using maven to buidl and package application.

the input message taken from input file:
the format of message as below:

MESSAGE_TYPE|PRODUCT_TYPE|PRICE|AMOUNT|ADJUSTMENT_TYPE|ADJUSTMENT_AMOUNT
where
 - message_type:
    0: for sale , AMOUNT ,ADJUSTMENT_TYPE, ADJUSTMENT_AMOUNT field will be empty
    1: sale detail message : ADJUSTMENT_TYPE, ADJUSTMENT_AMOUNT field will be empty
    2: sale adjustment message : AMOUNT field will be empty
 -product type: any word such as : apple, babana
 - price is double value : 10
 - amount: the number of occurrence
 - adjustment_type: could be any of
    ADD : addition adjustment
    SUB : subtraction
    MUL : multiple
 - ADJUSTMENT_AMOUNT : adjustment amount

the program will validate input message based on message types,any invalidate message will be logged and skip for processing


How to build and run the test for application:

execute following command to build and run unit test

mvn clean install

package applicatio:

mvn jar:jar
using following command to run application:

jav -jar <path_to_jar_file> <path_to_input_file>


Thank your for reading and reviewing code!

Joey