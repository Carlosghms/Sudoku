

import java.util.Scanner;

/**
 *
 * @author CarlosGilHerranz
 */
public class Sudoku {
    
    private static int solucion=0;
    
    public static boolean check(int[][] sudoku, int n, int columna, int fila){  //Comprueba que se cumplan las resticciones de filas y columnas para el numero n.
        boolean cumple=true;
        
        //Comprobación filas y columnas
        
        for(int x=0;x<9&&cumple;x++)
            if(sudoku[x][fila]==n) // Detectado n en la misma columna
                cumple=false;

        for(int y=0;y<9&&cumple;y++)
            if(sudoku[columna][y]==n) // Detectado n en la misma fila
                cumple=false;
        
    return cumple;
    }
    
    public static boolean EnCaja(int[][] sudoku, int n, int columna, int fila){
        boolean enCaja=false;
        int cajaX=((columna/3)*3);
        int cajaY=((fila/3)*3);
        
        for(int y=0;y<3&&!enCaja;y++)
            for(int x=0;x<3&&!enCaja;x++)
                if(sudoku[cajaX+x][cajaY+y]==n) //El número ya está en la caja
                    enCaja=true; 
                
        return enCaja;
    }
    
    public static boolean sudokuCompleto(int [][]sudoku){
        boolean completo=true;
        
        
            for(int y=0;y<9&&completo;y++)
                for(int x=0;x<9&&completo;x++)
                    if(sudoku[x][y]==0) 
                        completo=false;
                    
        return completo;
    }
    
    public static void compute_sudoku(int[][]parcial, int columna, int fila){
        boolean entra=false;
          
        while(columna<9&&fila<9){
            if(parcial[columna][fila]==0)
                for (int numero = 1; numero<=9; numero++){
                    entra=true;
                    if(!EnCaja(parcial,numero,columna, fila)&&check(parcial,numero,columna,fila)){
                        parcial[columna][fila] = numero;
                        
                        compute_sudoku(parcial,columna,fila);
                    }
                    parcial[columna][fila]= 0;
                }
            if(entra)
                return;
            
            else if(columna==8){
                columna=0;
                fila++;
            }
            else
                columna++;
        }
       if (sudokuCompleto(parcial)){
            solucion+=1;  
            imprimirSudoku(parcial);
        } 
    }   
                        
    public static void imprimirSudoku(int[][] s){
        int fila, columna, caja;
        System.out.println();
        for (fila=0; fila<3; fila++){
                
                for(columna=0; columna<9; columna++){
                    System.out.print(s[columna][fila] + " ");
                    if(columna==2||columna==5)
                        System.out.print("| ");
                }
                System.out.print("| ");
            
            System.out.println();
        }
        System.out.println("----------------------");
        for (fila=3; fila<6; fila++){
            
                for(columna=0; columna<9; columna++){
                    System.out.print(s[columna][fila] + " ");
                    if(columna==2||columna==5)
                        System.out.print("| ");
                }
                System.out.print("| ");
            
            System.out.println();
        }
        System.out.println("----------------------");
        for (fila=6; fila<9; fila++){
            
                for(columna=0; columna<9; columna++){
                    System.out.print(s[columna][fila] + " ");
                    if(columna==2||columna==5)
                        System.out.print("| ");
                }
                System.out.print("| ");
            
            System.out.println();
        }
        System.out.println();
    }
    
public static void main(String[] args) {
        Scanner s = new Scanner (System.in);
        int[][] sudoku= new int[9][9]; //[x].[y]
        
        for(int y=0;y<9;y++)                           
            for(int x=0;x<9;x++)
                    sudoku[x][y]=s.nextInt();
        
        compute_sudoku(sudoku,0,0);
        System.out.print(solucion);
    
}
}