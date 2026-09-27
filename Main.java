import java.util.Scanner;

public class Main {

    static StudentLinkedList studentList = new StudentLinkedList();
    static ActionStack actionStack = new ActionStack(50);
    static ServiceQueue serviceQueue = new ServiceQueue(50);
    static StudentBST studentTree = new StudentBST();
    static StudentHashTable hashTable = new StudentHashTable(20);
    static CampusGraph campusGraph = new CampusGraph();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        while (choice != 16) {
            printMenu();
            System.out.print("Enter your choice: ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number from the menu.");
                sc.next();
                continue;
            }

            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    updateStudent(sc);
                    break;
                case 3:
                    deleteStudent(sc);
                    break;
                case 4:
                    studentList.displayAll();
                    break;
                case 5:
                    addServiceRequest(sc);
                    break;
                case 6:
                    processServiceRequest();
                    break;
                case 7:
                    actionStack.displayActions();
                    break;
                case 8:
                    studentTree.displayInOrder();
                    break;
                case 9:
                    searchStudentByHash(sc);
                    break;
                case 10:
                    addLocation(sc);
                    break;
                case 11:
                    removeLocation(sc);
                    break;
                case 12:
                    addConnection(sc);
                    break;
                case 13:
                    removeConnection(sc);
                    break;
                case 14:
                    campusGraph.displayGraph();
                    break;
                case 15:
                    traverseCampus(sc);
                    break;
                case 16:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a number between 1 and 16.");
            }
        }

        sc.close();
    }

    static void printMenu() {
        System.out.println("\n===== University Student Record and Campus Route Management System =====");
        System.out.println("1.  Add Student Record");
        System.out.println("2.  Update Student Record");
        System.out.println("3.  Delete Student Record");
        System.out.println("4.  Display All Records using Linked List");
        System.out.println("5.  Add Service Request to Queue");
        System.out.println("6.  Process Next Service Request");
        System.out.println("7.  Display Recent Actions using Stack");
        System.out.println("8.  Display Students using BST");
        System.out.println("9.  Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations using BFS or DFS");
        System.out.println("16. Exit");
    }

    static void addStudent(Scanner sc) {
        System.out.print("Enter Student ID: ");
        String id = sc.next();

        if (studentList.search(id) != null) {
            System.out.println("A student with this ID already exists. Cannot add duplicate.");
            return;
        }

        System.out.print("Enter Student Name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Enter Programme: ");
        String programme = sc.nextLine();

        double marks = readMarks(sc);

        Student newStudent = new Student(id, name, programme, marks);

        studentList.add(newStudent);
        studentTree.insert(newStudent);
        hashTable.insert(newStudent);

        actionStack.push("Added student " + id);
        System.out.println("Student record added successfully.");
    }

    static void updateStudent(Scanner sc) {
        System.out.print("Enter Student ID to update: ");
        String id = sc.next();

        Student existing = studentList.search(id);
        if (existing == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new Name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Enter new Programme: ");
        String programme = sc.nextLine();

        double marks = readMarks(sc);

        existing.name = name;
        existing.programme = programme;
        existing.marks = marks;

        actionStack.push("Updated student " + id);
        System.out.println("Student record updated successfully.");
    }

    static void deleteStudent(Scanner sc) {
        System.out.print("Enter Student ID to delete: ");
        String id = sc.next();

        boolean removed = studentList.delete(id);
        if (!removed) {
            System.out.println("Student not found. Nothing was deleted.");
            return;
        }

        studentTree.delete(id);
        hashTable.remove(id);

        actionStack.push("Deleted student " + id);
        System.out.println("Student record deleted successfully.");
    }

    static double readMarks(Scanner sc) {
        double marks = -1;
        while (marks < 0 || marks > 100) {
            System.out.print("Enter Marks (0-100): ");
            if (sc.hasNextDouble()) {
                marks = sc.nextDouble();
                if (marks < 0 || marks > 100) {
                    System.out.println("Marks must be between 0 and 100.");
                }
            } else {
                System.out.println("Please enter a valid number for marks.");
                sc.next();
            }
        }
        return marks;
    }

    static void addServiceRequest(Scanner sc) {
        System.out.print("Enter Student ID for the service request: ");
        String id = sc.next();
        System.out.print("Enter a short description of the request: ");
        sc.nextLine();
        String description = sc.nextLine();

        String request = "Student " + id + " - " + description;
        boolean added = serviceQueue.enqueue(request);

        if (added) {
            actionStack.push("Service request added for " + id);
            System.out.println("Service request added to the queue.");
        } else {
            System.out.println("Queue is full. Cannot add more requests right now.");
        }
    }

    static void processServiceRequest() {
        String request = serviceQueue.dequeue();
        if (request == null) {
            System.out.println("No service requests to process. Queue is empty.");
        } else {
            System.out.println("Processing request: " + request);
            actionStack.push("Processed request: " + request);
        }
    }

    static void searchStudentByHash(Scanner sc) {
        System.out.print("Enter Student ID to search: ");
        String id = sc.next();

        Student found = hashTable.search(id);
        if (found == null) {
            System.out.println("No student found with ID " + id);
        } else {
            System.out.println("Student found -> " + found);
        }
    }

    static void addLocation(Scanner sc) {
        System.out.print("Enter new campus location name: ");
        sc.nextLine();
        String location = sc.nextLine();

        boolean added = campusGraph.addLocation(location);
        if (added) {
            actionStack.push("Added campus location " + location);
            System.out.println("Location added.");
        } else {
            System.out.println("This location already exists.");
        }
    }

    static void removeLocation(Scanner sc) {
        System.out.print("Enter campus location to remove: ");
        sc.nextLine();
        String location = sc.nextLine();

        boolean removed = campusGraph.removeLocation(location);
        if (removed) {
            actionStack.push("Removed campus location " + location);
            System.out.println("Location removed.");
        } else {
            System.out.println("Location not found.");
        }
    }

    static void addConnection(Scanner sc) {
        System.out.print("Enter first location: ");
        sc.nextLine();
        String loc1 = sc.nextLine();
        System.out.print("Enter second location: ");
        String loc2 = sc.nextLine();

        boolean added = campusGraph.addConnection(loc1, loc2);
        if (added) {
            actionStack.push("Added road between " + loc1 + " and " + loc2);
            System.out.println("Connection added.");
        } else {
            System.out.println("Could not add connection. Check that both locations exist.");
        }
    }

    static void removeConnection(Scanner sc) {
        System.out.print("Enter first location: ");
        sc.nextLine();
        String loc1 = sc.nextLine();
        System.out.print("Enter second location: ");
        String loc2 = sc.nextLine();

        boolean removed = campusGraph.removeConnection(loc1, loc2);
        if (removed) {
            actionStack.push("Removed road between " + loc1 + " and " + loc2);
            System.out.println("Connection removed.");
        } else {
            System.out.println("Connection not found.");
        }
    }

    static void traverseCampus(Scanner sc) {
        System.out.print("Enter starting location: ");
        sc.nextLine();
        String start = sc.nextLine();

        System.out.print("Choose traversal type (B for BFS, D for DFS): ");
        String type = sc.nextLine().trim();

        if (type.equalsIgnoreCase("B")) {
            campusGraph.bfs(start);
        } else if (type.equalsIgnoreCase("D")) {
            campusGraph.dfs(start);
        } else {
            System.out.println("Invalid option. Please enter B or D.");
        }
    }
}
