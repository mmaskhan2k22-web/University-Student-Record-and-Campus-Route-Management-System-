public class ServiceQueue{

    private String[] requests;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public ServiceQueue(int capacity){
        this.capacity = capacity;
        requests = new String[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public boolean enqueue(String request){
        if(size == capacity) return false;
        rear = (rear + 1) % capacity;
        requests[rear] = request;
        size++;
        return true;
    }

    public String dequeue(){
        if(size == 0) return null;
        String val = requests[front];
        requests[front] = null;
        front = (front + 1) % capacity;
        size--;
        return val;
    }

    public void displayQueue(){
        if(size == 0){
            System.out.println("No pending service requests.");
            return;
        }
        System.out.println("----- Pending Service Requests -----");
        int idx = front;
        for(int i = 0; i < size; i++){
            System.out.println((i+1) + ". " + requests[idx]);
            idx = (idx + 1) % capacity;
        }
    }
}
