/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import se.kth.id1020.util.*;

/**
 *
 * @author Peter
 */
public class CharNode {
    
    char        firstLetter;
    ArrayList<SameChar> sameChar = new ArrayList<>();
    
    public CharNode(Word w, Attributes att){
        this.firstLetter = w.word.toLowerCase().charAt(0);
        //System.out.println ("Creating a new node in "+firstLetter+ " and placing "+w.word+" in the array");
        sameChar.add(new SameChar(w, att));
    }
    
    public void putWord(Word w, Attributes att){
        //System.out.println ("Searching for an empty node");
        int index = binarySearchIndex(w.word.toLowerCase(), 0, sameChar.size());
        
        if(index == sameChar.size()){
            sameChar.add(index, new SameChar(w, att));
            return;
        }
        
        if(sameChar.get(index).getWord().toLowerCase().compareTo(w.word.toLowerCase())==0){
                //System.out.println ("node for the word "+sameChar.get(i).getWord().toLowerCase()+" exists. Placing "+w.word+" in that node");
                sameChar.get(index).addSameWord(w, att);
                return; 
        }
        sameChar.add(index, new SameChar(w, att));
    }
       
        //System.out.println ("Node "+firstLetter+ " is empty, placing the word "+ w.word+" in that node");
                
    
    
    public ArrayList<SameChar> getSameCharList(){
        return this.sameChar;
    }
    
    public char getFirstLetter(){
        return this.firstLetter;
    }
    
    private int binarySearchIndex(String w, int lo, int hi){
        if (hi <= lo) return hi;
        int mid = lo + (hi - lo) / 2;
        int cmp = sameChar.get(mid).word.compareTo(w);
        if      (cmp > 0) return binarySearchIndex(w, lo, mid);
        else if (cmp < 0) return binarySearchIndex(w, mid+1, hi);
        else              return mid;
    }
}
