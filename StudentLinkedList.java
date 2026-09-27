// Reviewed and finalized by Member 1 - Linked List implementation
class StudentNode{
    Student data;
    StudentNode next;

    StudentNode(Student data){
        this.data = data;
        this.next = null;
    }
}

public class StudentLinkedList{

    StudentNode head;

    public void add(Student student){
        StudentNode newNode = new StudentNode(student);

        if(head == null){
            head = newNode;
            return;
        }

        StudentNode temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public Student search(String id){
        StudentNode temp = head;
        while(temp != null){
            if(temp.data.id.equalsIgnoreCase(id)){
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    public boolean delete(String id){

        if(head == null) return false;

        if(head.data.id.equalsIgnoreCase(id)){
            head = head.next;
            return true;
        }

        StudentNode temp = head;
        while(temp.next != null){
            if(temp.next.data.id.equalsIgnoreCase(id)){
                temp.next = temp.next.next;
                return true;
            }
            temp = temp.next;
        }

        return false;
    }

    public void displayAll(){
        if(head == null){
            System.out.println("No student records found.");
            return;
        }

        System.out.println("----- All Student Records (Linked List) -----");
        StudentNode temp = head;
        while(temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
}
