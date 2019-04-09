
import edu.princeton.cs.algs4.IndexMinPQ;
import java.util.*;
import se.kth.id1020.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Peter
 */
public class DFP {
    private boolean[] marked;
    private int[] subGraphId;
    private Edge[] edgeTo;
    private double[] distTo;
    private final int source;
    private int countVert;
    private int countGraphs;
    private IndexMinPQ<Double> pq;
    
    public DFP(Graph g, int s){
        marked = new boolean[g.numberOfVertices()];
        subGraphId = new int[g.numberOfVertices()];
        this.source = s;
        countGraphs = 0;
        
        for(int i=source; i<marked.length; i++){
            if(!marked[i]){
                dfs(g, i);
                //System.out.println ("DFS method is called "+countGraphs+" times");
                countGraphs++;
            }
        }
    }
    
    private void dfs(Graph g, int v){
        marked[v] = true;
        Iterator<Edge> edgeItr = g.adj(v).iterator();
        countVert++;
        int curr = 0;
        subGraphId[v] = countGraphs;
        
        while(edgeItr.hasNext()){
            curr = edgeItr.next().to;
            if(!marked[curr]){
                //System.out.println ("Found a node that is mrked false");
                v = curr;
                dfs(g, v);   
            }
        }
    }
    
    public int getCount(){ 
        return this.countVert;
    }
    
    public int graphCount(){
            return this.countGraphs;
    }
    
    public int shortestPath(Graph g, String from, String to, boolean weighted){
        int fromIndex = findIndexOf(g, from);
        int toIndex = findIndexOf(g, to);
        edgeTo = new Edge[g.numberOfVertices()];
        distTo = new double[g.numberOfVertices()];
        pq = new IndexMinPQ<>(g.numberOfVertices());
        
        if(subGraphId[fromIndex]!= subGraphId[toIndex])
            return -1;
        
        
        for(int i=0; i<g.numberOfVertices();i++)
            distTo[i] = Double.POSITIVE_INFINITY;
           
        
        distTo[fromIndex] = 0.0;
        pq.insert(fromIndex, 0.0);
        
        while(!pq.isEmpty())
            relax(g, pq.delMin(), weighted);
        
        
        System.out.println ("Starting vertex has index: " +fromIndex + " which is " + g.vertex(fromIndex).label);
        System.out.println ("Finishing vertex has index: " +toIndex + " which is " + g.vertex(toIndex).label);
        
        return getPath(fromIndex, toIndex);
    }
    
    
    private int findIndexOf(Graph g, String from){
        for(int i=0; i<countVert; i++){
            if(g.vertex(i).label.compareToIgnoreCase(from)==0)
                return i;
        }
        return -1;
    }
    
    private void relax(Graph g, int v, boolean weighted){
        for(Edge e : g.adj(v)){
            int w = e.to;
            double weight;
            if(weighted)
                weight = e.weight;
            else
                weight = 1;
            if(distTo[w]>distTo[v]+ weight){
                distTo[w] = distTo[v]+ weight;
                edgeTo[w] = e;
                if (pq.contains(w))
                    pq.change(w, distTo[w]);
                else
                    pq.insert(w, distTo[w]);
            }
        }
    }
    
    private int getPath(int fromIndex, int toIndex){
        boolean finish = false;
        int curr = toIndex;
        int vertexCount = 1;
        
        System.out.println ("We traverse the graph in the following manner");
        System.out.print (curr);
        while(!finish){
            curr = edgeTo[curr].from;
            System.out.print (" <- " + curr);
            vertexCount++;
            if(edgeTo[curr].from==fromIndex)
                finish = true;
        }
        System.out.print (" <- " + fromIndex + "\n");
        vertexCount++;
        return vertexCount;
    }
    
}
