public class StudentHashTable {

    private StudentNode[] buckets;
    private int tableSize;

    public StudentHashTable(int tableSize) {
        this.tableSize = tableSize;
        this.buckets = new StudentNode[tableSize];
    }

    private int hash(String id) {
        int total = 0;
        for (int i = 0; i < id.length(); i++) {
            total = total + id.charAt(i);
        }
        return total % tableSize;
    }

    public void insert(Student student) {
        int bucketIndex = hash(student.id);
        StudentNode newNode = new StudentNode(student);

        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = newNode;
        } else {
            newNode.next = buckets[bucketIndex];
            buckets[bucketIndex] = newNode;
        }
    }

    public Student search(String id) {
        int bucketIndex = hash(id);
        StudentNode node = buckets[bucketIndex];

        while (node != null) {
            if (node.data.id.equalsIgnoreCase(id)) {
                return node.data;
            }
            node = node.next;
        }
        return null;
    }

    public void remove(String id) {
        int bucketIndex = hash(id);
        StudentNode node = buckets[bucketIndex];
        StudentNode previousNode = null;

        while (node != null) {
            if (node.data.id.equalsIgnoreCase(id)) {
                if (previousNode == null) {
                    buckets[bucketIndex] = node.next;
                } else {
                    previousNode.next = node.next;
                }
                return;
            }
            previousNode = node;
            node = node.next;
        }
    }
}
