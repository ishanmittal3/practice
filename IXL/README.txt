I've included my code in an eclipse project, named IXL.
The source code for each question can be found in its individual package in IXL/src directory.

Question 1: package stringSets
Question 2: package states
Question 3: package textWrap

Each package contains a java class with the required method(s) in it. 
It also includes a Test class, containing jUnit tests. 


Comments on Question 2:

I've modified the given code to read the state codes and names from a file into two HashMaps - one with <code,name> and another with <name,code>. The maps are created once (I've made them static variables) and the input file doesn't need to be read again. This makes retrieving name/code faster.
For generating the html select list, I first put the state names in a Priority Queue and retrieved them one by one. This makes sure that we get the states in sorted order (to display on a webpage).
This refactoring also makes the code easier to read.
