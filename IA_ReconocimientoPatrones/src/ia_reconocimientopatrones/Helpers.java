/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_reconocimientopatrones;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 *
 * @author Christian
 */
public class Helpers {

    public int[][] RemoverPadding4(int[][] pixeles) {
        int[][] pixelesTrimeados = new int[20][20];
        int height = pixeles.length;
        int width = pixeles[0].length;
        for (int fila = 0; fila < height; fila++) {
            if ((fila > 3) && (fila < 24)) {
                for (int col = 0; col < width; col++) {
                    if ((col > 3) && (col < 24)) {
                        pixelesTrimeados[fila - 4][col - 4] = pixeles[fila][col];
                    }
                }
            }
        }
        return pixelesTrimeados;
    }

    public int UnsignedToBytes(byte b) {
        return b & 0xFF;
    }

    public void ImprimirMatriz(double[][] matriz, String titulo) {
        if ((titulo != null) && (!titulo.equals(""))) {
            System.out.println("*************************" + titulo + "*************************");
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(4);
        for (int i = 0; i < matriz.length; i++) {
            for (int k = 0; k < matriz[0].length; k++) {
                System.out.print(nf.format(matriz[i][k]) + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void ImprimirMatriz_ArrayList(ArrayList<ArrayList<Double>> matriz, String titulo) {
        if ((titulo != null) && (!titulo.equals(""))) {
            System.out.println("*************************" + titulo + "*************************");
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(4);
        for (int u = 0; u < matriz.size(); u++) {
            for (int v = 0; v < matriz.get(u).size(); v++) {
                System.out.print(nf.format(matriz.get(u).get(v)) + "\t\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void ImprimirVector_ArrayList(ArrayList<Double> vector, String titulo) {
        if ((titulo != null) && (!titulo.equals(""))) {
            System.out.println("*************************" + titulo + "*************************");
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(4);
        for (int u = 0; u < vector.size(); u++) {
            System.out.print(nf.format(vector.get(u)) + "\t\t");
        }
        System.out.println();
        System.out.println();
    }

    public void ImprimirVectorEnFormaMatriz(int[] promediosTresCuadrantes, int n) {
        int t = 0;
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                System.out.print(promediosTresCuadrantes[t] + "\t");
                t++;
            }
            System.out.println();
        }
        System.out.println();
    }

    public void ImprimirValores(ArrayList<ArrayList<Integer>> matrizValoresGrupo5, int n, String titulo) {
        if ((titulo != null) && (!titulo.equals(""))) {
            System.out.println("*************************" + titulo + "*************************");
        }

        for (int u = 0; u < matrizValoresGrupo5.size(); u++) {
            for (int v = 0; v < matrizValoresGrupo5.get(u).size(); v++) {
                System.out.print(matrizValoresGrupo5.get(u).get(v) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void ImprimirMatrizEnteros(int[][] matriz, String titulo) {
        if ((titulo != null) && (!titulo.equals(""))) {
            System.out.println("*************************" + titulo + "*************************");
        }
        System.out.print(" \t");
        for (int i = 0; i < 10; i++) {
            System.out.print("[" + i + "]" + "\t");
        }
        System.out.println();
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("[" + i + "]" + "\t");
            for (int k = 0; k < matriz[0].length; k++) {                
                System.out.print(matriz[i][k] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
