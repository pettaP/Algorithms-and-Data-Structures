/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.*;

/**
 *
 * @author Peter
 */
public class Tests {

        static ArrayList<String> strings = new ArrayList<>();
         
    public static void main(String[] args) {
        
        strings.add("ddd");
        
        System.out.println (strings);
        
        int index = binarySearchIndex("a", 0, strings.size());
        
        strings.add(index, "a");
        
        System.out.println (strings);
        
        index = binarySearchIndex("d", 0, strings.size());
        
        strings.add(index, "d");
        
        System.out.println (strings);
        
    }
    
    public static int binarySearchIndex(String w, int lo, int hi){
        if (hi <= lo) return lo;
        int mid = lo + (hi - lo) / 2;
        int cmp = strings.get(mid).compareTo(w);
        if      (cmp > 0) return binarySearchIndex(w, lo, mid);
        else if (cmp < 0) return binarySearchIndex(w, mid+1, hi);
        else              return mid;
    }
}
