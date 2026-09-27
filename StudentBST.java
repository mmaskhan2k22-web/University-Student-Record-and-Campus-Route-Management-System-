// Reviewed and finalized by Member 3 - BST and Hashing implementation
class BSTNode {
    Student data;
    BSTNode left;
    BSTNode right;

    public BSTNode(Student data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class StudentBST {

    private BSTNode root;

    public void insert(Student student) {
        root = insertHelper(root, student);
    }

    private BSTNode insertHelper(BSTNode node, Student student) {
        if (node == null) {
            return new BSTNode(student);
        }

        int cmp = student.id.compareTo(node.data.id);

        if (cmp < 0) {
            node.left = insertHelper(node.left, student);
        } else if (cmp > 0) {
            node.right = insertHelper(node.right, student);
        }

        return node;
    }

    public Student search(String id) {
        BSTNode node = root;
        while (node != null) {
            int cmp = id.compareTo(node.data.id);
            if (cmp == 0) {
                return node.data;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    public void delete(String id) {
        root = deleteHelper(root, id);
    }

    private BSTNode deleteHelper(BSTNode node, String id) {
        if (node == null) {
            return null;
        }

        int cmp = id.compareTo(node.data.id);

        if (cmp < 0) {
            node.left = deleteHelper(node.left, id);
        } else if (cmp > 0) {
            node.right = deleteHelper(node.right, id);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            BSTNode successor = node.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            node.data = successor.data;
            node.right = deleteHelper(node.right, successor.data.id);
        }
        return node;
    }

    public void displayInOrder() {
        if (root == null) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("----- Students in order of ID (BST) -----");
        inOrderHelper(root);
    }

    private void inOrderHelper(BSTNode node) {
        if (node == null) {
            return;
        }
        inOrderHelper(node.left);
        System.out.println(node.data);
        inOrderHelper(node.right);
    }
}
