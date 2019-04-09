/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;
import se.kth.id1020.util.*;

/**
 *
 * @author Peter
 */
public class SameChar {
    
    String word;
    ArrayList<SameWord> sameWord = new ArrayList<SameWord>();
    
    public SameChar(Word w, Attributes att){
        this.word = w.word.toLowerCase();
        //System.out.println ("Creating a new node for "+w.word+".");
        addSameWord(w, att);
    }
    
    public void addSameWord(Word word, Attributes att){
        //System.out.println ("Adding the word, "+word.word+" to the excisting node for "+word.word);
        sameWord.add(new SameWord(word, att));
    }
    
    public String getWord(){
        return this.word;
    }
    
    public ArrayList<SameWord> getWordlist(){
        return this.sameWord;
    }
    
}
