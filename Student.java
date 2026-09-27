public class Student {
    String id;
    String name;
    String programme;
    double marks;

    public Student(String id, String name, String programme, double marks){
        this.id = id;
        this.name = name;
        this.programme = programme;
        this.marks = marks;
    }

    public String toString(){
        return "ID: " + id + " | Name: " + name + " | Programme: " + programme + " | Marks: " + marks;
    }
}
