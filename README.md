University Student Record and Campus Route Management System
CIT300 - Data Structures and Algorithms, Graded Practical Assignment 1


About this project

This is our submission for the Week 10 practical assignment. The task was to build a Java console app that uses everything from weeks 1-9, linked lists, stacks, queues, trees, hashing and graphs, in one project instead of separate exercises.

We picked a University Student Record and Campus Route Management System. Basically two things combined - a system to manage student records (add/update/delete/search/display) and a system that models the campus as a network of locations connected by roads, since that's what the graph part of the assignment needed.


What each part does

Student records are kept in a linked list we wrote ourselves (didn't use java.util.LinkedList since that defeats the purpose of the assignment).

We also used a stack to log the last few actions done in the program - kind of like a simple history/undo feature.

Service requests from students go into a queue so they get handled in the order they came in.

For organizing students by ID we used a BST, and a hash table on top of that for faster ID lookups (chaining is used to handle collisions).

The graph part represents campus locations as vertices and roads as edges, stored as an adjacency list. You can add/remove locations and roads, print the whole network, and run BFS or DFS starting from any location.

There's input validation in most places too - invalid menu numbers, marks outside 0-100, duplicate IDs, duplicate locations, missing students etc are all handled with a message instead of crashing.


Compiling and running

javac *.java
java Main

then just use the menu (1-16)


Files

Main.java - the menu + connects everything together
Student.java - student record fields
StudentLinkedList.java - linked list for students
ActionStack.java - stack for recent actions
ServiceQueue.java - queue for service requests
StudentBST.java - BST organized by student ID
StudentHashTable.java - hash table for ID search
CampusGraph.java - graph + BFS/DFS for campus locations


Group members

Member 1
Name: M.M.Askhan
Student ID: 23DA2-1106
Responsibility: Linked list implementation and student record management
Contribution: wrote StudentLinkedList.java and Student.java, tested the add/update/delete/display operations

Member 2
Name: H.A.M.Maash
Student ID: 23DA2-0472
Responsibility: Stack and queue implementation
Contribution: wrote ActionStack.java and ServiceQueue.java, tested the action history and the service request queue

Member 3
Name: M.H.Muhammed
Student ID: 23DA2-0533
Responsibility: BST and hashing/search functionality
Contribution: wrote StudentBST.java and StudentHashTable.java, tested sorted display and the ID search

Member 4
Name: M.A.M.Afras
Student ID: 23DA2-0705
Responsibility: Graph implementation, campus locations, connections and BFS/DFS traversal
Contribution: wrote CampusGraph.java, went through the self study videos for the graph part since it wasn't fully covered in lectures yet, tested BFS and DFS.

All members - integration of the components into Main.java, overall testing and debugging, this README, and GitHub collaboration (commits, branches, pull requests).



A note on how we built this

We kept things pretty basic on purpose - plain arrays for the stack,queue,hash table, a normal BST (not AVL didn't need the extra complexity), adjacency list for the graph. Didn't use Java's built in Stack,Queue,LinkedList classes since the whole point was to build these ourselves and actually understand them not just call a library.
