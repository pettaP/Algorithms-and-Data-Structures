/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursion;

/**
 *
 * @author Peter
 */
public class Recursion {
    public static void main(String[] args) {
        
        Pascal rp = new IterativePascal();
    
        rp.printPascal(5, true);
    }
    
}

class RecursivePascal extends ErrorPascal implements Pascal {
    private int[][] arr;
    
    @Override
    public void printPascal(int n, boolean up){
        
        super.sanityCheck(n);
        
        if (arr == null)
                arr = new int[n+1][n+1];
        
        if (up == true){
            int k = n;
            
                
            while (k >= 0){
 
                System.out.print (binom(n, k) + " ");
                k--;
            }
            System.out.println ();
            if (n >= 0)
                printPascal(n-1, true);
        }
        
                
        if (up == false){
            int row = 0;
            while (n >= row){
                int k = row;
                while (k >= 0){
 
                    System.out.print (binom(row, k) + " ");
                    k--;
                }
                System.out.println ();
                row++;
            }
        }
            
    }
    
    @Override
    public int binom(int n, int k){
       
        super.sanityCheck(n, k);
        
       if (k == 0||k == n){
            if (arr[n][k] == 0){ 
                arr[n][k] = 1;
                return 1;
            }
            else
                return arr[n][k];
       }
       else{
           if (arr[n][k] == 0){
               arr[n][k] = binom(n-1,k-1)+ binom(n-1,k);
               return arr[n][k];
           }
           else
               return arr[n][k];
       }
           
       }
    
}

class IterativePascal extends ErrorPascal implements Pascal {

    @Override
    public void printPascal(int row, boolean up) {
        
        super.sanityCheck(row);
        
        if ( up == true){
            int col;
            while (row > 0){
                col = row;
                while(col > 0){
                    System.out.print (binom(row, col));
                    col--;
                }
                System.out.println();
                row--;
            }
        }
        
        if (up == false){
            int rowcount = 0;
            int colcount = 0;
            while (rowcount <= row){
                colcount = rowcount;
                while (colcount > 0 ){
                    System.out.print (binom(rowcount, colcount));
                    colcount--;
                }
                System.out.println();
                rowcount++;
            }
        }
    }

    @Override
    public int binom(int row, int col) {
        
        super.sanityCheck(row, col);
        
        if (row == 0 || row == col || col == 0)
            return 1;
        
        else{
            int[][] arr = new int[row+1][row+1];
            arr[0][0] = 1;
        
            for(int i = 1; i < row; i++){
                for(int j = 0; j < col; j++){
                    if (j == 0)
                        arr[i][j] = 1;
                    else
                        arr[i][j] = arr[i-1][j-1]+ arr[i-1][j];
                }
            }
        
            return arr[row-1][col-1]; 
        }
    }
}

interface Pascal {
    
    public void printPascal (int n, boolean up);
    
    public int binom(int n, int k); 
    
}

abstract class ErrorPascal implements Pascal {
    
    public void sanityCheck (int n)throws IllegalArgumentException{
        
        if (n <= 0)
            throw new IllegalArgumentException("Paramter must be 1 or more."); 
    }
    
    public void sanityCheck (int n, int k)throws IllegalArgumentException{
        
        if (k < 0 || k > n)
            throw new IllegalArgumentException("Paramter cannot be more than n value or below 0."); 
    }
}
