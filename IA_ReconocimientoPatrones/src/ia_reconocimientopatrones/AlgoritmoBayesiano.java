/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_reconocimientopatrones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        this.grupoNumero0 = new ArrayList<>();
        this.grupoNumero1 = new ArrayList<>();
        this.grupoNumero2 = new ArrayList<>();
        this.grupoNumero3 = new ArrayList<>();
        this.grupoNumero4 = new ArrayList<>();
        this.grupoNumero5 = new ArrayList<>();
        this.grupoNumero6 = new ArrayList<>();
        this.grupoNumero7 = new ArrayList<>();
        this.grupoNumero8 = new ArrayList<>();
        this.grupoNumero9 = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            grupoNumero0.add(0.0);
            grupoNumero1.add(0.0);
            grupoNumero2.add(0.0);
            grupoNumero3.add(0.0);
            grupoNumero4.add(0.0);
            grupoNumero5.add(0.0);
            grupoNumero6.add(0.0);
            grupoNumero7.add(0.0);
            grupoNumero8.add(0.0);
            grupoNumero9.add(0.0);
        }
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
            for (int i = 0; i < 10; i++) {
                cantidadNumeros.add(0);
            }
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

                //ALMACENAMIENTO DE LAS NUEVAS MEDIAS
                switch (num) {
                    case 0:
                        cantidadNumeros.set(0, cantidadNumeros.get(0) + 1);
                        //car1
                        grupoNumero0.set(0, (grupoNumero0.get(0) * (cantidadNumeros.get(0) - 1) + car1) / cantidadNumeros.get(0));
                        //car2
                        grupoNumero0.set(1, (grupoNumero0.get(1) * (cantidadNumeros.get(0) - 1) + car2p) / cantidadNumeros.get(0));
                        //car3
                        grupoNumero0.set(2, (grupoNumero0.get(2) * (cantidadNumeros.get(0) - 1) + car3p) / cantidadNumeros.get(0));
                        break;
                    case 1:
                        cantidadNumeros.set(1, cantidadNumeros.get(1) + 1);
                        grupoNumero1.set(0, (grupoNumero1.get(0) * (cantidadNumeros.get(1) - 1) + car1) / cantidadNumeros.get(1));
                        grupoNumero1.set(1, (grupoNumero1.get(1) * (cantidadNumeros.get(1) - 1) + car2p) / cantidadNumeros.get(1));
                        grupoNumero1.set(2, (grupoNumero1.get(2) * (cantidadNumeros.get(1) - 1) + car3p) / cantidadNumeros.get(1));
                        break;
                    case 2:
                        cantidadNumeros.set(2, cantidadNumeros.get(2) + 1);
                        grupoNumero2.set(0, (grupoNumero2.get(0) * (cantidadNumeros.get(2) - 1) + car1) / cantidadNumeros.get(2));
                        grupoNumero2.set(1, (grupoNumero2.get(1) * (cantidadNumeros.get(2) - 1) + car2p) / cantidadNumeros.get(2));
                        grupoNumero2.set(2, (grupoNumero2.get(2) * (cantidadNumeros.get(2) - 1) + car3p) / cantidadNumeros.get(2));
                        break;
                    case 3:
                        cantidadNumeros.set(3, cantidadNumeros.get(3) + 1);
                        grupoNumero3.set(0, (grupoNumero3.get(0) * (cantidadNumeros.get(3) - 1) + car1) / cantidadNumeros.get(3));
                        grupoNumero3.set(1, (grupoNumero3.get(1) * (cantidadNumeros.get(3) - 1) + car2p) / cantidadNumeros.get(3));
                        grupoNumero3.set(2, (grupoNumero3.get(2) * (cantidadNumeros.get(3) - 1) + car3p) / cantidadNumeros.get(3));
                        break;
                    case 4:
                        cantidadNumeros.set(4, cantidadNumeros.get(4) + 1);
                        grupoNumero4.set(0, (grupoNumero4.get(0) * (cantidadNumeros.get(4) - 1) + car1) / cantidadNumeros.get(4));
                        grupoNumero4.set(1, (grupoNumero4.get(1) * (cantidadNumeros.get(4) - 1) + car2p) / cantidadNumeros.get(4));
                        grupoNumero4.set(2, (grupoNumero4.get(2) * (cantidadNumeros.get(4) - 1) + car3p) / cantidadNumeros.get(4));
                        break;
                    case 5:
                        cantidadNumeros.set(5, cantidadNumeros.get(5) + 1);
                        grupoNumero5.set(0, (grupoNumero5.get(0) * (cantidadNumeros.get(5) - 1) + car1) / cantidadNumeros.get(5));
                        grupoNumero5.set(1, (grupoNumero5.get(1) * (cantidadNumeros.get(5) - 1) + car2p) / cantidadNumeros.get(5));
                        grupoNumero5.set(2, (grupoNumero5.get(2) * (cantidadNumeros.get(5) - 1) + car3p) / cantidadNumeros.get(5));
                        break;
                    case 6:
                        cantidadNumeros.set(6, cantidadNumeros.get(6) + 1);
                        grupoNumero6.set(0, (grupoNumero6.get(0) * (cantidadNumeros.get(6) - 1) + car1) / cantidadNumeros.get(6));
                        grupoNumero6.set(1, (grupoNumero6.get(1) * (cantidadNumeros.get(6) - 1) + car2p) / cantidadNumeros.get(6));
                        grupoNumero6.set(2, (grupoNumero6.get(2) * (cantidadNumeros.get(6) - 1) + car3p) / cantidadNumeros.get(6));
                        break;
                    case 7:
                        cantidadNumeros.set(7, cantidadNumeros.get(7) + 1);
                        grupoNumero7.set(0, (grupoNumero7.get(0) * (cantidadNumeros.get(7) - 1) + car1) / cantidadNumeros.get(7));
                        grupoNumero7.set(1, (grupoNumero7.get(1) * (cantidadNumeros.get(7) - 1) + car2p) / cantidadNumeros.get(7));
                        grupoNumero7.set(2, (grupoNumero7.get(2) * (cantidadNumeros.get(7) - 1) + car3p) / cantidadNumeros.get(7));
                        break;
                    case 8:
                        cantidadNumeros.set(8, cantidadNumeros.get(8) + 1);
                        grupoNumero8.set(0, (grupoNumero8.get(0) * (cantidadNumeros.get(8) - 1) + car1) / cantidadNumeros.get(8));
                        grupoNumero8.set(1, (grupoNumero8.get(1) * (cantidadNumeros.get(8) - 1) + car2p) / cantidadNumeros.get(8));
                        grupoNumero8.set(2, (grupoNumero8.get(2) * (cantidadNumeros.get(8) - 1) + car3p) / cantidadNumeros.get(8));
                        break;
                    case 9:
                        cantidadNumeros.set(9, cantidadNumeros.get(9) + 1);
                        grupoNumero9.set(0, (grupoNumero9.get(0) * (cantidadNumeros.get(9) - 1) + car1) / cantidadNumeros.get(9));
                        grupoNumero9.set(1, (grupoNumero9.get(1) * (cantidadNumeros.get(9) - 1) + car2p) / cantidadNumeros.get(9));
                        grupoNumero9.set(2, (grupoNumero9.get(2) * (cantidadNumeros.get(9) - 1) + car3p) / cantidadNumeros.get(9));
                        break;
                }

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

    public void ProcesarIrisFlowersDataSet() {
        //Son 3 especies de flores
        ArrayList<Double> mediasEspecie1 = new ArrayList<>();
        ArrayList<Double> mediasEspecie2 = new ArrayList<>();
        ArrayList<Double> mediasEspecie3 = new ArrayList<>();
        ArrayList<Double> listaCaracteristicas = new ArrayList<>();
        int nroCaracteristicas = 4;
        for (int i = 0; i < nroCaracteristicas; i++) {
            mediasEspecie1.add(0.0);
            mediasEspecie2.add(0.0);
            mediasEspecie3.add(0.0);
            listaCaracteristicas.add(0.0);
        }
        ArrayList<Integer> cantidadFloresXTipo = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            cantidadFloresXTipo.add(0);
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:/Users/Christian/Documents/PUCP/2013-2/APLICACIONES DE CIENCIAS DE LA COMPUTACIÓN/Tarea Académica 2/iris flower data set.txt"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                for (int i = 0; i < nroCaracteristicas; i++) {
                    listaCaracteristicas.set(i, Double.valueOf(parts[i]));                    
                }
                String especie = parts[4];

                switch (especie) {
                    case "I. setosa":
                        cantidadFloresXTipo.set(0, cantidadFloresXTipo.get(0) + 1);
                        // PARA N-CARACTERÍSTICAS
                        for (int i = 0; i < nroCaracteristicas; i++) {
                            double valorCar = listaCaracteristicas.get(i); //obtener el valor de la i-ésima característica
                            mediasEspecie1.set(i, (mediasEspecie1.get(i) * (cantidadFloresXTipo.get(0) - 1) + valorCar) / cantidadFloresXTipo.get(0));
                        }
                        break;
                    case "I. versicolor":
                        cantidadFloresXTipo.set(1, cantidadFloresXTipo.get(1) + 1);
                        // PARA N-CARACTERÍSTICAS
                        for (int i = 0; i < nroCaracteristicas; i++) {
                            double valorCar = listaCaracteristicas.get(i); //obtener el valor de la i-ésima característica
                            mediasEspecie2.set(i, (mediasEspecie2.get(i) * (cantidadFloresXTipo.get(1) - 1) + valorCar) / cantidadFloresXTipo.get(1));
                        }
                        break;
                    case "I. virginica":
                        cantidadFloresXTipo.set(2, cantidadFloresXTipo.get(2) + 1);
                        // PARA N-CARACTERÍSTICAS
                        for (int i = 0; i < nroCaracteristicas; i++) {
                            double valorCar = listaCaracteristicas.get(i); //obtener el valor de la i-ésima característica
                            mediasEspecie3.set(i, (mediasEspecie3.get(i) * (cantidadFloresXTipo.get(2) - 1) + valorCar) / cantidadFloresXTipo.get(2));
                        }
                        break;
                    default:
                        break;
                }
            }
            System.out.print("Medias Especie 1");
            for (Double media : mediasEspecie1) {
                System.out.print(media + " ");
            }
            System.out.println();
            System.out.print("Medias Especie 2");
            for (Double media : mediasEspecie2) {
                System.out.print(media + " ");
            }
            System.out.println();
            System.out.print("Medias Especie 3");
            for (Double media : mediasEspecie3) {
                System.out.print(media + " ");
            }
            System.out.println();
        } catch (IOException | NumberFormatException e) {
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
