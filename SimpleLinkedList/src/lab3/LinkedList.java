package lab3;

public class LinkedList {
    
    private Node first;
    private int counter;
    
    public void push(int var){
       Node oldFirst = first;
       first = new Node();
       first.item = var;
       first.next = oldFirst;
       counter++;
    }
    
    public int pop(){
        int popVar = first.item;
        first = first.next;
        counter--;
        return popVar;
    }
    
    public void swap(Node A, Node B){
        int temp = A.item;
        A.item = B.item;
        B.item = temp;
    }
    
    public boolean isEmpty(){
        return first == null;
    }
    
    public int getSize(){
        return counter;
    }
    
    public int bubbleSort(){
        int length = counter-2;
        boolean swapped = true;
        int swapCounter = 0;
        
        while(length>=0 && swapped==true){
            swapped = false;
            Node temp = first;
            for(int i = 0; i <= length; i++){
                if(temp.item>temp.next.item){
                        swap(temp, temp.next);
                        swapCounter++;
                        swapped = true;
                }
                temp = temp.next;   
            }
            length--;
        }
    return swapCounter;    
    }
    
    public int inversions(){
        int invCount = 0;
        Node n1 = first;
        Node n2;
        while (n1.next != null) {
            n2 = n1.next;
            while(n2 != null){
                if(n1.item > n2.item){
                    invCount++;
                }
                n2 = n2.next;
            }
            n1 = n1.next;
        }
        
        return invCount;
    }
    
}
