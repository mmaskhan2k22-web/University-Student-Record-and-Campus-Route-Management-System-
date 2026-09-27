//Reviewed and Finalized By Member 02 - Stack and Queue Implementation
public class ActionStack{

    private String[] actions;
    private int top;
    private int capacity;

    public ActionStack(int capacity){
        this.capacity = capacity;
        actions = new String[capacity];
        top = -1;
    }

    public void push(String action){
        if(top == capacity - 1){
            for(int i = 0; i < capacity - 1; i++){
                actions[i] = actions[i+1];
            }
            top = capacity - 2;
        }
        top++;
        actions[top] = action;
    }

    public String pop(){
        if(top == -1) return null;
        String val = actions[top];
        actions[top] = null;
        top--;
        return val;
    }

    public void displayActions(){
        if(top == -1){
            System.out.println("No recent actions to display.");
            return;
        }
        System.out.println("----- Recent Actions (Stack, most recent first) -----");
        for(int i = top; i >= 0; i--){
            System.out.println((top - i + 1) + ". " + actions[i]);
        }
    }
}
