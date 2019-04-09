/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import se.kth.id1020.Graph;
import se.kth.id1020.DataSource;
/**
 *
 * @author Peter
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph g = DataSource.load();
        
        DFP dfp = new DFP(g, 0);
        
        System.out.println ("Total amount of verticies in graph = " +dfp.getCount());
        System.out.println ("Number of sub graphs = " +dfp.graphCount());
        System.out.println ();
        System.out.println ("Number of components traversed in unweighted graph = " +dfp.shortestPath(g, "Renyn", "Parses", false));
        System.out.println ();
        System.out.println ("Number of components traversed in weighted graph = " +dfp.shortestPath(g, "Renyn", "Parses", true));
    }
    
}
