/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import se.kth.id1020.util.*;

/**
 *
 * @author Peter
 */
public class SameWord {
    
    Word word;
    Attributes attributes;
    
    public SameWord(Word w, Attributes a){
        this.word = w;
        this.attributes = a;
    }
    
    public Word getWord(){
        return this.word;
    }
    
    public Attributes getAtt(){
        return this.attributes;
    }
    
    public Document getDocument(){
        return this.attributes.document;
    }
}
