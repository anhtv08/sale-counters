This is a small application to stimulate the sale notification system.
The program is written in Java 8 and using maven to build and package application.

The input message taken from input file:
The assumption format of message as below:

MESSAGE_TYPE|PRODUCT_TYPE|PRICE|AMOUNT|ADJUSTMENT_TYPE|ADJUSTMENT_AMOUNT
where
 - message_type:
    0: for sale , AMOUNT ,ADJUSTMENT_TYPE, ADJUSTMENT_AMOUNT field will be empty
    1: sale detail message : ADJUSTMENT_TYPE, ADJUSTMENT_AMOUNT field will be empty
    2: sale adjustment message : AMOUNT field will be empty
 -product type: any word such as : apple, banana
 - price is double value : 10
 - amount: the number of occurrence
 - adjustment_type: could be any of
    ADD : addition adjustment
    SUB : subtraction
    MUL : multiple
 - ADJUSTMENT_AMOUNT : adjustment amount

 Prerequisites:
  - Java 8
  - Maven

Project structure:
 - src folder : source code and unit test code and data
 - data folder: sample input data folder
The program will validate input message based on message types,any invalidate message will be logged and skip for processing


1. How to build and run the test for application:

 - mvn clean install

2. package application:

  - mvn jar:jar

3. using following command to run application:

  - java -jar <path_to_jar_file> <path_to_input_file>

  - Please provide the absolute path to the input file

Thank your for reading and reviewing code!

Joey