/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

/**
 *
 * @author Peter
 */
public class Lab3 {
    public static void main(String[] args) {
        LinkedList lk = new LinkedList();
        
       
        lk.push(1);
        lk.push(2);
        lk.push(4);
        lk.push(3); 
        lk.push(5);
        lk.push(6);
        lk.push(7);
        lk.push(8);
        
        int invCount = lk.inversions();
        int swapCount = lk.bubbleSort();
        
        while(!lk.isEmpty()){
            System.out.print (lk.pop()+" ");
        }
        System.out.println();
        System.out.println ("Inversions found: " + invCount);
        System.out.println ("Swappes performed: " + swapCount);
        
    }
    
    
}
