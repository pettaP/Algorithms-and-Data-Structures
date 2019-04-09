
import java.util.*;
import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Peter
 */
public class TinySearchEngine implements TinySearchEngineBase {
    
    final CharNode[] firstChar = new CharNode[256];
     
    @Override
    public void insert(Word word, Attributes att) {
        //System.out.println (word.word);
        String st = word.word.toLowerCase();
        char ch = st.charAt(0);
        int  index = ch;
        
        if(firstChar[index]== null){
            firstChar[index] = new CharNode(word, att);
            //System.out.println ("Creating a new node at index "+index+" in the array");
        }
        else{
            firstChar[index].putWord(word, att);
            //System.out.println ("Putting a the word "+word.word+" in node at index "+index+" in the array");
        }
        
    }

    @Override
    public List<Document> search(String string) {
        ArrayList<Attributes> attList = new ArrayList<>();
        String[] query = string.split(" ");
        boolean sortByOrder = false;
        int index = 0;
        int orderbyIndex = 0;
        boolean sortbyDesc = false;
        
        if(string.length()==0)
            return null;
        
        if(query.length==1){
            attList = searchWord(string);
        }
        
        if(query.length > 1){
            for(String element: query){
                if(element.compareToIgnoreCase("orderby")==0){
                sortByOrder = true;
                orderbyIndex = index;
                    for(int i=orderbyIndex; i<query.length;i++){
                        if(query[i].compareToIgnoreCase("desc")==0){
                            sortbyDesc = true;
                            break;
                        }
                    }
                }
            index++;
            }
            attList = multipleWords(query, orderbyIndex, sortByOrder);
        }
        
        if(sortByOrder){
            System.out.println("Choosing sorting by "+query[orderbyIndex+1]);
            bubbleSort(attList, query[orderbyIndex+1], sortbyDesc);
            return checkForDup(attList);
        }
        
       bubbleSort(attList, "default", sortbyDesc);
        
        return checkForDup(attList);
    }
    
    private ArrayList<Attributes> searchWord(String string) {
        if(string.length()==0)
            return null;
        
        ArrayList<Attributes> doc = new ArrayList<>();
        ArrayList<SameWord> sameWord = new ArrayList<>();
        int index = (int) string.toLowerCase().charAt(0);
        ArrayList<SameChar> charNodes = firstChar[index].getSameCharList();
        String docName = "";
        
        int arrayIndex = binarySearchIndex(string, charNodes, 0, charNodes.size());
        
        if(arrayIndex < 0)
            return doc;
        
        System.out.println (charNodes.get(arrayIndex).word);
        sameWord = charNodes.get(arrayIndex).getWordlist();
            
        for(int i=0; i<sameWord.size();i++)
                doc.add(sameWord.get(i).attributes);
            
        return doc;
    }
    
    private int binarySearchIndex(String w, ArrayList<SameChar> charNodes, int lo, int hi){
        if (hi <= lo) return -1;
        int mid = lo + (hi - lo) / 2;
        int cmp = charNodes.get(mid).word.compareTo(w);
        if      (cmp > 0) return binarySearchIndex(w, charNodes, lo, mid);
        else if (cmp < 0) return binarySearchIndex(w, charNodes, mid+1, hi);
        else              return mid;
    }
    
    private ArrayList<Attributes> multipleWords(String[] query, int index, boolean sortByOrder){
        ArrayList<Attributes> docList = new ArrayList<>(searchWord(query[0]));
        ArrayList<Attributes> tempList = new ArrayList<>();
        if(!sortByOrder)
            index = query.length;
        
        for(int i=1; i<index; i++){
            tempList = searchWord(query[i]);
            int docSize = docList.size();
            for(int j=0; j<tempList.size(); j++){
                Attributes d = tempList.get(j);
                boolean foundDuplicate = false;
                for(int k=0; k<docSize; k++){
                    if(d.document.compareTo(docList.get(k).document) == 0){
                        foundDuplicate = true;
                        break;
                    }
                }
                if(!foundDuplicate)
                    docList.add(tempList.get(j));
            }
        }
        return docList;
    }
    
    public void bubbleSort(ArrayList<Attributes> docList, String param, boolean sortbyDesc){
        //ListIterator<Document> iterator = docList.listIterator();
        System.out.println ("We are in bubble sort");
        Attributes temp;
        if (docList.size()>1) // check if the number of orders is larger than 1
        {
            if(param.compareToIgnoreCase("occurance")==0){
                System.out.println ("Sorting by occurance");
                for (int x=0; x<docList.size(); x++) // bubble sort outer loop
                {
                    for (int i=0; i < docList.size()-x-1; i++) {
                        if (docList.get(i).occurrence > (docList.get(i+1).occurrence))
                        {
                            temp = docList.get(i);
                            docList.set(i,docList.get(i+1) );
                            docList.set(i+1, temp);
                        }
                    }
                }
            }
            if(param.compareToIgnoreCase("popularity")==0){
            System.out.println ("Sorting by popularity");
                for (int x=0; x<docList.size(); x++) // bubble sort outer loop
                {
                    for (int i=0; i < docList.size()-x-1; i++) {
                        if (docList.get(i).document.popularity > (docList.get(i+1).document.popularity))
                        {
                            temp = docList.get(i);
                            docList.set(i,docList.get(i+1) );
                            docList.set(i+1, temp);
                        }
                    }
                }
            }
            if(param.compareToIgnoreCase("count")==0){
                System.out.println ("Sorting by count");
                for (int x=0; x<docList.size(); x++) // bubble sort outer loop
                {
                    ArrayList<String> strings = new ArrayList<>();
                    for (int i=0; i<docList.size(); i++){
                        strings.add(docList.get(i).document.name);
                    }
                    for (int i=0; i < docList.size()-x-1; i++) {
                        if (Collections.frequency(strings, docList.get(i).document.name) < Collections.frequency(strings, docList.get(i+1).document.name))
                        {
                            temp = docList.get(i);
                            docList.set(i,docList.get(i+1));
                            docList.set(i+1, temp);
                        }
                    }
                }
            }
            else{
                System.out.println ("Default sorting");
                for (int x=0; x<docList.size(); x++) // bubble sort outer loop
                {
                    for (int i=0; i < docList.size()-x-1; i++) {
                        if (docList.get(i).document.name.compareTo(docList.get(i+1).document.name) > 0)
                        {
                            temp = docList.get(i);
                            docList.set(i,docList.get(i+1) );
                            docList.set(i+1, temp);
                        }
                    }
                }
            }
        }
    
        if(sortbyDesc)
            Collections.reverse(docList);
        
    }
    
    private ArrayList<Document> toDoc (ArrayList<Attributes> att){
        ArrayList<Document> doc = new ArrayList<>();
        
        for(int i=0; i<att.size(); i++){
            doc.add(i, att.get(i).document);
        }
        return doc;
    }
    
    public ArrayList<Document> checkForDup (ArrayList<Attributes> att){
        ArrayList<Document> doc = new ArrayList<>();
        String docName = "";
        for(int i=0; i<att.size();i++){
            if(docName.compareTo(att.get(i).document.name)!=0){
                doc.add(att.get(i).document);
                docName = att.get(i).document.name;
            }
        }
        return doc;
    }
    
}


//Collections.frequency(sameWord, sameWord.get(i));