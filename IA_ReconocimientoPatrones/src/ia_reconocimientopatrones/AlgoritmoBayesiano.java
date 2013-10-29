/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_reconocimientopatrones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Christian
 */
public class AlgoritmoBayesiano {

    private ArrayList<Double> grupoNumero0;
    private ArrayList<Double> grupoNumero1;
    private ArrayList<Double> grupoNumero2;
    private ArrayList<Double> grupoNumero3;
    private ArrayList<Double> grupoNumero4;
    private ArrayList<Double> grupoNumero5;
    private ArrayList<Double> grupoNumero6;
    private ArrayList<Double> grupoNumero7;
    private ArrayList<Double> grupoNumero8;
    private ArrayList<Double> grupoNumero9;

    public AlgoritmoBayesiano() {
    }

    public AlgoritmoBayesiano(ArrayList<Double> grupoNumero0, ArrayList<Double> grupoNumero1, ArrayList<Double> grupoNumero2,
            ArrayList<Double> grupoNumero3, ArrayList<Double> grupoNumero4, ArrayList<Double> grupoNumero5, ArrayList<Double> grupoNumero6,
            ArrayList<Double> grupoNumero7, ArrayList<Double> grupoNumero8, ArrayList<Double> grupoNumero9) {

        this.grupoNumero0 = grupoNumero0;
        this.grupoNumero1 = grupoNumero1;
        this.grupoNumero2 = grupoNumero2;
        this.grupoNumero3 = grupoNumero3;
        this.grupoNumero4 = grupoNumero4;
        this.grupoNumero5 = grupoNumero5;
        this.grupoNumero6 = grupoNumero6;
        this.grupoNumero7 = grupoNumero7;
        this.grupoNumero8 = grupoNumero8;
        this.grupoNumero9 = grupoNumero9;
    }

    public void Formula() {
        try {
            //calcular matriz de covarianza
            //calcular la matriz de covarianza inversa
            //
            int d = 3; //dimension del vector de caracteristicas hasta ahora 3 caracteristicas
            //interpretar bien el uso de la matriz de medias con el X, en la formula general


        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }

    public void ProcesarDatos(int nroImagenes, ArrayList<byte[]> imagenesArray, ArrayList<Integer> labelsArray) {
        try {

            ArrayList<Integer> cantidadNumeros = new ArrayList<Integer>();
            ArrayList<String> output = new ArrayList<>();

            for (int i = 0; i < nroImagenes; i++) {
                byte[] imagen = imagenesArray.get(i);
                int[][] pixeles = new int[28][28];
                int k = 0;

                int num = labelsArray.get(i); //saco el numero que voy a utilizar

                for (int u = 0; u < 28; u++) {
                    for (int v = 0; v < 28; v++) {
                        int p = this.UnsignedToBytes(imagen[k]);
                        if (p != 0) {
                            p = 1;
                        }
                        pixeles[u][v] = p;
                        k++;
                        //System.out.print(pixel[u][v] + " ");
                    }
                    //System.out.println();
                }
                int[][] pixelesTrimeados = this.RemoverPadding4(pixeles);
                //ahora que tengo la matriz con los numeros debo, comenzar a llenar los arreglos

                int car1 = CalcularCaracteristica1(pixelesTrimeados);
                //int car2 = CalcularCaracteristica2(pixelesTrimeados);
                //int car3 = CalcularCaracteristica3(pixelesTrimeados);
                int car2p = SimetriaHorizontal(pixelesTrimeados);
                int car3p = SimetriaVertical(pixelesTrimeados);
                output.add("Label: " + num + " - 1s: " + car1 + " - SimV: " + car2p + " - SimH: " + car3p);
                //System.out.println("Label: " + num + " - 1s: " + car1 + " - SimV: " + car2p + " - SimH: " + car3p);
/*
                 switch (num) {
                 case 0:
                 cantidadNumeros.set(0, cantidadNumeros.get(0) + 1);
                 //car1
                 grupoNumero0.set(0, (grupoNumero0.get(0) * (cantidadNumeros.get(0) - 1) + car1) / cantidadNumeros.get(0));
                 //car2
                 grupoNumero0.set(1, (grupoNumero0.get(1) * (cantidadNumeros.get(1) - 1) + car2) / cantidadNumeros.get(1));
                 //car3
                 grupoNumero0.set(2, (grupoNumero0.get(2) * (cantidadNumeros.get(2) - 1) + car3) / cantidadNumeros.get(2));

                 case 1:
                 cantidadNumeros.set(1, cantidadNumeros.get(1) + 1);

                 grupoNumero1.set(0, (grupoNumero1.get(0) * (cantidadNumeros.get(0) - 1) + car1) / cantidadNumeros.get(0));
                 grupoNumero1.set(1, (grupoNumero1.get(1) * (cantidadNumeros.get(1) - 1) + car2) / cantidadNumeros.get(1));
                 grupoNumero1.set(2, (grupoNumero1.get(2) * (cantidadNumeros.get(2) - 1) + car3) / cantidadNumeros.get(2));

                 case 2:
                 cantidadNumeros.set(2, cantidadNumeros.get(2) + 1);

                 grupoNumero2.set(0, (grupoNumero2.get(0) * (cantidadNumeros.get(0) - 1) + car1) / cantidadNumeros.get(0));
                 grupoNumero2.set(1, (grupoNumero2.get(1) * (cantidadNumeros.get(1) - 1) + car2) / cantidadNumeros.get(1));
                 grupoNumero2.set(2, (grupoNumero2.get(2) * (cantidadNumeros.get(2) - 1) + car3) / cantidadNumeros.get(2));

                 case 3:
                 cantidadNumeros.set(3, cantidadNumeros.get(3) + 1);

                 grupoNumero3.set(0, (grupoNumero3.get(0) * (cantidadNumeros.get(0) - 1) + car1) / cantidadNumeros.get(0));
                 grupoNumero3.set(1, (grupoNumero3.get(1) * (cantidadNumeros.get(1) - 1) + car2) / cantidadNumeros.get(1));
                 grupoNumero3.set(2, (grupoNumero3.get(2) * (cantidadNumeros.get(2) - 1) + car3) / cantidadNumeros.get(2));

                 case 4:
                 cantidadNumeros.set(4, cantidadNumeros.get(4) + 1);

                 grupoNumero4.set(0, (grupoNumero4.get(0) * (cantidadNumeros.get(0) - 1) + car1) / cantidadNumeros.get(0));
                 grupoNumero4.set(1, (grupoNumero4.get(1) * (cantidadNumeros.get(1) - 1) + car2) / cantidadNumeros.get(1));
                 grupoNumero4.set(2, (grupoNumero4.get(2) * (cantidadNumeros.get(2) - 1) + car3) / cantidadNumeros.get(2));

                 case 5:
                 cantidadNumeros.set(5, cantidadNumeros.get(5) + 1);

                 grupoNumero5.set(0, (grupoNumero5.get(0) * (cantidadNumeros.get(0) - 1) + car1) / cantidadNumeros.get(0));
                 grupoNumero5.set(1, (grupoNumero5.get(1) * (cantidadNumeros.get(1) - 1) + car2) / cantidadNumeros.get(1));
                 grupoNumero5.set(2, (grupoNumero5.get(2) * (cantidadNumeros.get(2) - 1) + car3) / cantidadNumeros.get(2));

                 case 6:
                 cantidadNumeros.set(6, cantidadNumeros.get(6) + 1);

                 grupoNumero7.set(0, (grupoNumero7.get(0) * (cantidadNumeros.get(0) - 1) + car1) / cantidadNumeros.get(0));
                 grupoNumero7.set(1, (grupoNumero7.get(1) * (cantidadNumeros.get(1) - 1) + car2) / cantidadNumeros.get(1));
                 grupoNumero7.set(2, (grupoNumero7.get(2) * (cantidadNumeros.get(2) - 1) + car3) / cantidadNumeros.get(2));

                 case 7:
                 cantidadNumeros.set(7, cantidadNumeros.get(7) + 1);

                 grupoNumero1.set(0, (grupoNumero1.get(0) * (cantidadNumeros.get(0) - 1) + car1) / cantidadNumeros.get(0));
                 grupoNumero1.set(1, (grupoNumero1.get(1) * (cantidadNumeros.get(1) - 1) + car2) / cantidadNumeros.get(1));
                 grupoNumero1.set(2, (grupoNumero1.get(2) * (cantidadNumeros.get(2) - 1) + car3) / cantidadNumeros.get(2));

                 case 8:
                 cantidadNumeros.set(8, cantidadNumeros.get(8) + 1);

                 grupoNumero8.set(0, (grupoNumero8.get(0) * (cantidadNumeros.get(0) - 1) + car1) / cantidadNumeros.get(0));
                 grupoNumero8.set(1, (grupoNumero8.get(1) * (cantidadNumeros.get(1) - 1) + car2) / cantidadNumeros.get(1));
                 grupoNumero8.set(2, (grupoNumero8.get(2) * (cantidadNumeros.get(2) - 1) + car3) / cantidadNumeros.get(2));
                 case 9:
                 cantidadNumeros.set(9, cantidadNumeros.get(9) + 1);

                 grupoNumero9.set(0, (grupoNumero9.get(0) * (cantidadNumeros.get(0) - 1) + car1) / cantidadNumeros.get(0));
                 grupoNumero9.set(1, (grupoNumero9.get(1) * (cantidadNumeros.get(1) - 1) + car2) / cantidadNumeros.get(1));
                 grupoNumero9.set(2, (grupoNumero9.get(2) * (cantidadNumeros.get(2) - 1) + car3) / cantidadNumeros.get(2));
                 }
                 */
            }
            Collections.sort(output);
            for (String s : output) {
                System.out.println(s + " ");
            }
            output.toString();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private int[][] RemoverPadding4(int[][] pixeles) {
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

    //Caracteristicas
    //NUMERO DE 1's EN LA MATRIZ
    private int CalcularCaracteristica1(int[][] pixeles) {
        int height = pixeles.length;
        int width = pixeles[0].length;
        int total = 0;
        for (int fila = 0; fila < height; fila++) {
            for (int col = 0; col < width; col++) {
                if (pixeles[fila][col] != 0) {
                    total++;
                }
            }
        }
        return total;
    }

    //SIMETRÍA HORIZONTAL
    private int CalcularCaracteristica2(int[][] pixeles) {
        int height = pixeles.length;
        int width = pixeles[0].length;
        int[][] primeramatriz = new int[10][20];
        int[][] segundamatriz = new int[10][20];
        int coincidencias = 0;

        for (int fila = 0; fila <= 9; fila++) {
            for (int col = 0; col < width; col++) {
                primeramatriz[fila][col] = pixeles[fila][col];
            }
        }

        for (int fila = 19; fila >= 10; fila--) {
            for (int col = 0; col < width; col++) {
                segundamatriz[19 - fila][col] = pixeles[fila][col];
            }
        }

        for (int fila = 0; fila < 11; fila++) {
            for (int col = 0; col < width; col++) {
                if (primeramatriz[fila][col] == segundamatriz[fila][col]) {
                    coincidencias++;
                }
            }
        }
        return coincidencias;
        //si tiene valor de coincidencia de 200 entonces es identido es simetricamente horizontal               
    }

    //SIMETRÍA VERTICAL
    private int CalcularCaracteristica3(int[][] pixeles) {
        int height = pixeles.length;
        int width = pixeles[0].length;
        int[][] primeramatriz = new int[20][10];
        int[][] segundamatriz = new int[20][10];

        int coincidencias = 0;
        for (int fila = 0; fila < height; fila++) {
            for (int col = 0; col < 11; col++) {
                primeramatriz[fila][col] = pixeles[fila][col];
            }
        }

        for (int fila = 0; fila < height; fila++) {
            for (int col = 20; col > 11; col--) {
                segundamatriz[fila][col] = pixeles[fila][col];
            }
        }

        for (int fila = 0; fila < height; fila++) {
            for (int col = 0; col < 11; col++) {
                if (primeramatriz[fila][col] == segundamatriz[fila][col]) {
                    coincidencias++;
                }
            }
        }
        return coincidencias;
        //si tiene valor de coincidencia de 200 entonces es identido es simetricamente horizontal
    }

    //SIMETRÍA HORIZONTAL v2
    private int SimetriaHorizontal(int[][] pixeles) {
        int nfilas = pixeles.length;
        int ncolumnas = pixeles[0].length;
        int nro1sArriba = 0, nro1sAbajo = 0;

        for (int fila = 0; fila < (nfilas - 10); fila++) { //[0..9]
            for (int col = 0; col < ncolumnas; col++) { //[0..19]
                if (pixeles[fila][col] != 0) {
                    nro1sArriba++;
                }
                if (pixeles[nfilas - fila - 1][col] != 0) { //[19..10]
                    nro1sAbajo++;
                }
            }
        }
        return Math.abs(nro1sArriba - nro1sAbajo);
    }

    //SIMETRÍA VERTICAL v2
    private int SimetriaVertical(int[][] pixeles) {
        int nfilas = pixeles.length;
        int ncolumnas = pixeles[0].length;
        int nro1sIzquierda = 0, nro1sDerecha = 0;

        for (int col = 0; col < (ncolumnas - 10); col++) { //[0..9]
            for (int fila = 0; fila < nfilas; fila++) { //[0..19]
                if (pixeles[fila][col] != 0) {
                    nro1sIzquierda++;
                }
                if (pixeles[fila][ncolumnas - col - 1] != 0) { //[19..10]
                    nro1sDerecha++;
                }
            }
        }
        return Math.abs(nro1sIzquierda - nro1sDerecha);
    }
}
