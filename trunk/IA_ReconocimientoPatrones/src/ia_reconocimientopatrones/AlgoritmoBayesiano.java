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
//import org.apache.commons.math3.stat.correlation.Covariance;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.descriptive.moment.VectorialMean;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.linear.LUDecomposition;

import java.text.NumberFormat;

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
    
    private ArrayList<Double> grupoNumero0Aux;
    private ArrayList<Double> grupoNumero1Aux;
    private ArrayList<Double> grupoNumero2Aux;
    private ArrayList<Double> grupoNumero3Aux;
    private ArrayList<Double> grupoNumero4Aux;
    private ArrayList<Double> grupoNumero5Aux;
    private ArrayList<Double> grupoNumero6Aux;
    private ArrayList<Double> grupoNumero7Aux;
    private ArrayList<Double> grupoNumero8Aux;
    private ArrayList<Double> grupoNumero9Aux;
        
    private ArrayList<ArrayList<Double>> grupo0ArrayArray;
    private ArrayList<ArrayList<Double>> grupo1ArrayArray;
    private ArrayList<ArrayList<Double>> grupo2ArrayArray;
    private ArrayList<ArrayList<Double>> grupo3ArrayArray;
    private ArrayList<ArrayList<Double>> grupo4ArrayArray;
    private ArrayList<ArrayList<Double>> grupo5ArrayArray;
    private ArrayList<ArrayList<Double>> grupo6ArrayArray;
    private ArrayList<ArrayList<Double>> grupo7ArrayArray;
    private ArrayList<ArrayList<Double>> grupo8ArrayArray;
    private ArrayList<ArrayList<Double>> grupo9ArrayArray;
    
    
    private ArrayList<Integer> cantidadNumeros;

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

        this.grupoNumero0Aux = new ArrayList<>();
        this.grupoNumero1Aux = new ArrayList<>();
        this.grupoNumero2Aux = new ArrayList<>();
        this.grupoNumero3Aux = new ArrayList<>();
        this.grupoNumero4Aux = new ArrayList<>();
        this.grupoNumero5Aux = new ArrayList<>();
        this.grupoNumero6Aux = new ArrayList<>();
        this.grupoNumero7Aux = new ArrayList<>();
        this.grupoNumero8Aux = new ArrayList<>();
        this.grupoNumero9Aux = new ArrayList<>();
        
        this.cantidadNumeros = new ArrayList<>();

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

            //grupoNumero0Aux.add(0.0);           
        }
        grupo0ArrayArray = new ArrayList<>();
        grupo1ArrayArray = new ArrayList<>();
        grupo2ArrayArray = new ArrayList<>();
        grupo3ArrayArray = new ArrayList<>();
        grupo4ArrayArray = new ArrayList<>();
        grupo5ArrayArray = new ArrayList<>();
        grupo6ArrayArray = new ArrayList<>();
        grupo7ArrayArray = new ArrayList<>();
        grupo8ArrayArray = new ArrayList<>();
        grupo9ArrayArray = new ArrayList<>();
        
    }


    public void Formula() {
        try {
            //calcular matriz de covarianza
            //calcular la matriz de covarianza inversa
            //
            //utilizo el arreglo de medias, en este caso para el numero 0 grupoNumero0

            double[][] matrixaux0 = new double[grupo0ArrayArray.size()][grupo0ArrayArray.get(0).size()];
            double[][] matrixaux1 = new double[grupo1ArrayArray.size()][grupo1ArrayArray.get(0).size()];
            double[][] matrixaux2 = new double[grupo2ArrayArray.size()][grupo2ArrayArray.get(0).size()];
            double[][] matrixaux3 = new double[grupo3ArrayArray.size()][grupo3ArrayArray.get(0).size()];
            double[][] matrixaux4 = new double[grupo4ArrayArray.size()][grupo4ArrayArray.get(0).size()];
            double[][] matrixaux5 = new double[grupo5ArrayArray.size()][grupo5ArrayArray.get(0).size()];
            double[][] matrixaux6 = new double[grupo6ArrayArray.size()][grupo6ArrayArray.get(0).size()];
            double[][] matrixaux7 = new double[grupo7ArrayArray.size()][grupo7ArrayArray.get(0).size()];
            double[][] matrixaux8 = new double[grupo8ArrayArray.size()][grupo8ArrayArray.get(0).size()];
            double[][] matrixaux9 = new double[grupo9ArrayArray.size()][grupo9ArrayArray.get(0).size()];                        

            double[][] covMatrix0;
            double[][] covMatrix1;
            double[][] covMatrix2;
            double[][] covMatrix3;
            double[][] covMatrix4;
            double[][] covMatrix5;
            double[][] covMatrix6;
            double[][] covMatrix7;
            double[][] covMatrix8;
            double[][] covMatrix9;
                                   
            RealMatrix rMatrix0;
            RealMatrix rMatrix1;
            RealMatrix rMatrix2;
            RealMatrix rMatrix3;
            RealMatrix rMatrix4;
            RealMatrix rMatrix5;
            RealMatrix rMatrix6;
            RealMatrix rMatrix7;
            RealMatrix rMatrix8;
            RealMatrix rMatrix9;
            
            
            
            //para el grupo 0
            
            for (int u = 0; u < grupo0ArrayArray.size(); u++) {
                for (int v = 0; v < grupo0ArrayArray.get(u).size(); v++) {
                    matrixaux0[u][v] = grupo0ArrayArray.get(u).get(v);
                }
            }
            
            Covariance cov0 = new Covariance(matrixaux0, false);
                                  
            rMatrix0 = cov0.getCovarianceMatrix();

            covMatrix0 = rMatrix0.getData();

            NumberFormat nf0 = NumberFormat.getInstance();
            nf0.setMaximumFractionDigits(2);

            System.out.print("Grupo 0" +"\n");
            
            for (int i = 0; i < covMatrix0.length; i++) {
                for (int k = 0; k < covMatrix0.length; k++) {
                    System.out.print(nf0.format(covMatrix0[i][k]) + "  ");
                }
                System.out.println();
            }
            
            //para el numero 1
            for (int u = 0; u < grupo1ArrayArray.size(); u++) {
                for (int v = 0; v < grupo1ArrayArray.get(u).size(); v++) {
                    matrixaux1[u][v] = grupo1ArrayArray.get(u).get(v);
                }
            }
            
            Covariance cov1 = new Covariance(matrixaux1, false);
                                  
            rMatrix1 = cov1.getCovarianceMatrix();

            covMatrix1 = rMatrix1.getData();

            NumberFormat nf1 = NumberFormat.getInstance();
            nf1.setMaximumFractionDigits(2);

            System.out.print("Grupo 1"+"\n");
            
            for (int i = 0; i < covMatrix1.length; i++) {
                for (int k = 0; k < covMatrix1.length; k++) {
                    System.out.print(nf1.format(covMatrix1[i][k]) + "  ");
                }
                System.out.println();
            }
            
            //para el numero 2
             for (int u = 0; u < grupo2ArrayArray.size(); u++) {
                for (int v = 0; v < grupo2ArrayArray.get(u).size(); v++) {
                    matrixaux2[u][v] = grupo2ArrayArray.get(u).get(v);
                }
            }
            
            Covariance cov2 = new Covariance(matrixaux2, false);
                                  
            rMatrix2 = cov2.getCovarianceMatrix();

            covMatrix2 = rMatrix2.getData();

            NumberFormat nf2 = NumberFormat.getInstance();
            nf2.setMaximumFractionDigits(2);

            System.out.print("Grupo 2"+"\n");
            
            for (int i = 0; i < covMatrix2.length; i++) {
                for (int k = 0; k < covMatrix2.length; k++) {
                    System.out.print(nf2.format(covMatrix2[i][k]) + "  ");
                }
                System.out.println();
            }
            
            //para el numero 3
            for (int u = 0; u < grupo3ArrayArray.size(); u++) {
                for (int v = 0; v < grupo3ArrayArray.get(u).size(); v++) {
                    matrixaux3[u][v] = grupo3ArrayArray.get(u).get(v);
                }
            }
            
            Covariance cov3 = new Covariance(matrixaux3, false);
                                  
            rMatrix3 = cov3.getCovarianceMatrix();

            covMatrix3 = rMatrix3.getData();

            NumberFormat nf3 = NumberFormat.getInstance();
            nf3.setMaximumFractionDigits(2);

            System.out.print("Grupo 3"+"\n");
            
            for (int i = 0; i < covMatrix3.length; i++) {
                for (int k = 0; k < covMatrix3.length; k++) {
                    System.out.print(nf3.format(covMatrix3[i][k]) + "  ");
                }
                System.out.println();
            }            
            
            //para el numero 4
            for (int u = 0; u < grupo4ArrayArray.size(); u++) {
                for (int v = 0; v < grupo4ArrayArray.get(u).size(); v++) {
                    matrixaux4[u][v] = grupo4ArrayArray.get(u).get(v);
                }
            }
            
            Covariance cov4 = new Covariance(matrixaux4, false);
                                  
            rMatrix4 = cov4.getCovarianceMatrix();

            covMatrix4 = rMatrix4.getData();

            NumberFormat nf4 = NumberFormat.getInstance();
            nf4.setMaximumFractionDigits(2);

            System.out.print("Grupo 4"+"\n");
            
            for (int i = 0; i < covMatrix4.length; i++) {
                for (int k = 0; k < covMatrix4.length; k++) {
                    System.out.print(nf4.format(covMatrix4[i][k]) + "  ");
                }
                System.out.println();
            }
            
            
            //para el numero 5
            for (int u = 0; u < grupo5ArrayArray.size(); u++) {
                for (int v = 0; v < grupo5ArrayArray.get(u).size(); v++) {
                    matrixaux5[u][v] = grupo5ArrayArray.get(u).get(v);
                }
            }
            
            Covariance cov5 = new Covariance(matrixaux5, false);
                                  
            rMatrix5 = cov5.getCovarianceMatrix();

            covMatrix5 = rMatrix5.getData();

            NumberFormat nf5 = NumberFormat.getInstance();
            nf5.setMaximumFractionDigits(2);

            System.out.print("Grupo 5"+"\n");
            
            for (int i = 0; i < covMatrix5.length; i++) {
                for (int k = 0; k < covMatrix5.length; k++) {
                    System.out.print(nf5.format(covMatrix5[i][k]) + "  ");
                }
                System.out.println();
            }            
            
            //para el numero 6
            for (int u = 0; u < grupo6ArrayArray.size(); u++) {
                for (int v = 0; v < grupo6ArrayArray.get(u).size(); v++) {
                    matrixaux6[u][v] = grupo6ArrayArray.get(u).get(v);
                }
            }
            
            Covariance cov6 = new Covariance(matrixaux6, false);
                                  
            rMatrix6 = cov6.getCovarianceMatrix();

            covMatrix6 = rMatrix6.getData();

            NumberFormat nf6 = NumberFormat.getInstance();
            nf6.setMaximumFractionDigits(2);

            System.out.print("Grupo 6"+"\n");
            
            for (int i = 0; i < covMatrix6.length; i++) {
                for (int k = 0; k < covMatrix6.length; k++) {
                    System.out.print(nf6.format(covMatrix6[i][k]) + "  ");
                }
                System.out.println();
            }            
            
            //para el numero 7
            for (int u = 0; u < grupo7ArrayArray.size(); u++) {
                for (int v = 0; v < grupo7ArrayArray.get(u).size(); v++) {
                    matrixaux7[u][v] = grupo7ArrayArray.get(u).get(v);
                }
            }
            
            Covariance cov7 = new Covariance(matrixaux7, false);
                                  
            rMatrix7 = cov7.getCovarianceMatrix();

            covMatrix7 = rMatrix7.getData();

            NumberFormat nf7 = NumberFormat.getInstance();
            nf7.setMaximumFractionDigits(2);

            System.out.print("Grupo 7"+"\n");
            
            for (int i = 0; i < covMatrix7.length; i++) {
                for (int k = 0; k < covMatrix7.length; k++) {
                    System.out.print(nf7.format(covMatrix7[i][k]) + "  ");
                }
                System.out.println();
            }
            
            //para el numero 8
            for (int u = 0; u < grupo8ArrayArray.size(); u++) {
                for (int v = 0; v < grupo8ArrayArray.get(u).size(); v++) {
                    matrixaux8[u][v] = grupo8ArrayArray.get(u).get(v);
                }
            }
            
            Covariance cov8 = new Covariance(matrixaux8, false);
                                  
            rMatrix8 = cov8.getCovarianceMatrix();

            covMatrix8 = rMatrix8.getData();

            NumberFormat nf8 = NumberFormat.getInstance();
            nf8.setMaximumFractionDigits(2);

            System.out.print("Grupo 8"+"\n");
            
            for (int i = 0; i < covMatrix8.length; i++) {
                for (int k = 0; k < covMatrix8.length; k++) {
                    System.out.print(nf8.format(covMatrix8[i][k]) + "  ");
                }
                System.out.println();
            }            
            
            //para el numero 9
            for (int u = 0; u < grupo9ArrayArray.size(); u++) {
                for (int v = 0; v < grupo9ArrayArray.get(u).size(); v++) {
                    matrixaux9[u][v] = grupo9ArrayArray.get(u).get(v);
                }
            }
            
            Covariance cov9 = new Covariance(matrixaux9, false);
                                  
            rMatrix9 = cov9.getCovarianceMatrix();

            covMatrix9 = rMatrix9.getData();

            NumberFormat nf9 = NumberFormat.getInstance();
            nf9.setMaximumFractionDigits(2);
            
            System.out.print("Grupo 9"+"\n");

            for (int i = 0; i < covMatrix9.length; i++) {
                for (int k = 0; k < covMatrix9.length; k++) {
                    System.out.print(nf9.format(covMatrix9[i][k]) + "  ");
                }
                System.out.println();
            }            
            
            
            
            
                                 

            int d = 3; //dimension del vector de caracteristicas hasta ahora 3 caracteristicas
            //interpretar bien el uso de la matriz de medias con el X, en la formula general


        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }

    public void ProcesarDatos(int nroImagenes, ArrayList<byte[]> imagenesArray, ArrayList<Integer> labelsArray) {
        try {

            //ArrayList<Integer> cantidadNumeros = new ArrayList<Integer>();
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
                //output.add("Label: " + num + " - 1s: " + car1 + " - SimV: " + car2p + " - SimH: " + car3p);
                output.add(num + "\t" + car1 + "\t" + car2p + "\t" + car3p);
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
                        
                        grupoNumero0Aux = new ArrayList<>();
                        grupoNumero0Aux.add((double) car1);
                        grupoNumero0Aux.add((double) car2p);
                        grupoNumero0Aux.add((double) car3p);

                        grupo0ArrayArray.add(grupoNumero0Aux);                       
                        break;
                    case 1:
                        cantidadNumeros.set(1, cantidadNumeros.get(1) + 1);
                        
                        grupoNumero1.set(0, (grupoNumero1.get(0) * (cantidadNumeros.get(1) - 1) + car1) / cantidadNumeros.get(1));
                        grupoNumero1.set(1, (grupoNumero1.get(1) * (cantidadNumeros.get(1) - 1) + car2p) / cantidadNumeros.get(1));
                        grupoNumero1.set(2, (grupoNumero1.get(2) * (cantidadNumeros.get(1) - 1) + car3p) / cantidadNumeros.get(1));
                        
                        grupoNumero1Aux = new ArrayList<>();
                        grupoNumero1Aux.add((double) car1);
                        grupoNumero1Aux.add((double) car2p);
                        grupoNumero1Aux.add((double) car3p);

                        grupo1ArrayArray.add(grupoNumero1Aux);   
                        
                        break;
                    case 2:
                        cantidadNumeros.set(2, cantidadNumeros.get(2) + 1);
                        
                        grupoNumero2.set(0, (grupoNumero2.get(0) * (cantidadNumeros.get(2) - 1) + car1) / cantidadNumeros.get(2));
                        grupoNumero2.set(1, (grupoNumero2.get(1) * (cantidadNumeros.get(2) - 1) + car2p) / cantidadNumeros.get(2));
                        grupoNumero2.set(2, (grupoNumero2.get(2) * (cantidadNumeros.get(2) - 1) + car3p) / cantidadNumeros.get(2));
                        
                        grupoNumero2Aux = new ArrayList<>();
                        grupoNumero2Aux.add((double) car1);
                        grupoNumero2Aux.add((double) car2p);
                        grupoNumero2Aux.add((double) car3p);

                        grupo2ArrayArray.add(grupoNumero2Aux);   
                        
                        break;
                    case 3:
                        cantidadNumeros.set(3, cantidadNumeros.get(3) + 1);
                        
                        grupoNumero3.set(0, (grupoNumero3.get(0) * (cantidadNumeros.get(3) - 1) + car1) / cantidadNumeros.get(3));
                        grupoNumero3.set(1, (grupoNumero3.get(1) * (cantidadNumeros.get(3) - 1) + car2p) / cantidadNumeros.get(3));
                        grupoNumero3.set(2, (grupoNumero3.get(2) * (cantidadNumeros.get(3) - 1) + car3p) / cantidadNumeros.get(3));
                        
                        grupoNumero3Aux = new ArrayList<>();
                        grupoNumero3Aux.add((double) car1);
                        grupoNumero3Aux.add((double) car2p);
                        grupoNumero3Aux.add((double) car3p);

                        grupo3ArrayArray.add(grupoNumero3Aux);   
                        break;
                    case 4:
                        cantidadNumeros.set(4, cantidadNumeros.get(4) + 1);
                        
                        grupoNumero4.set(0, (grupoNumero4.get(0) * (cantidadNumeros.get(4) - 1) + car1) / cantidadNumeros.get(4));
                        grupoNumero4.set(1, (grupoNumero4.get(1) * (cantidadNumeros.get(4) - 1) + car2p) / cantidadNumeros.get(4));
                        grupoNumero4.set(2, (grupoNumero4.get(2) * (cantidadNumeros.get(4) - 1) + car3p) / cantidadNumeros.get(4));
                        
                        grupoNumero4Aux = new ArrayList<>();
                        grupoNumero4Aux.add((double) car1);
                        grupoNumero4Aux.add((double) car2p);
                        grupoNumero4Aux.add((double) car3p);

                        grupo4ArrayArray.add(grupoNumero4Aux);                           
                        
                        break;
                    case 5:
                        cantidadNumeros.set(5, cantidadNumeros.get(5) + 1);
                        
                        grupoNumero5.set(0, (grupoNumero5.get(0) * (cantidadNumeros.get(5) - 1) + car1) / cantidadNumeros.get(5));
                        grupoNumero5.set(1, (grupoNumero5.get(1) * (cantidadNumeros.get(5) - 1) + car2p) / cantidadNumeros.get(5));
                        grupoNumero5.set(2, (grupoNumero5.get(2) * (cantidadNumeros.get(5) - 1) + car3p) / cantidadNumeros.get(5));
                        
                        grupoNumero5Aux = new ArrayList<>();
                        grupoNumero5Aux.add((double) car1);
                        grupoNumero5Aux.add((double) car2p);
                        grupoNumero5Aux.add((double) car3p);

                        grupo5ArrayArray.add(grupoNumero5Aux);                           
                        
                        break;
                    case 6:
                        cantidadNumeros.set(6, cantidadNumeros.get(6) + 1);
                        
                        grupoNumero6.set(0, (grupoNumero6.get(0) * (cantidadNumeros.get(6) - 1) + car1) / cantidadNumeros.get(6));
                        grupoNumero6.set(1, (grupoNumero6.get(1) * (cantidadNumeros.get(6) - 1) + car2p) / cantidadNumeros.get(6));
                        grupoNumero6.set(2, (grupoNumero6.get(2) * (cantidadNumeros.get(6) - 1) + car3p) / cantidadNumeros.get(6));
                        
                        grupoNumero6Aux = new ArrayList<>();
                        grupoNumero6Aux.add((double) car1);
                        grupoNumero6Aux.add((double) car2p);
                        grupoNumero6Aux.add((double) car3p);

                        grupo6ArrayArray.add(grupoNumero6Aux);   
                        
                        break;
                    case 7:
                        cantidadNumeros.set(7, cantidadNumeros.get(7) + 1);
                        
                        grupoNumero7.set(0, (grupoNumero7.get(0) * (cantidadNumeros.get(7) - 1) + car1) / cantidadNumeros.get(7));
                        grupoNumero7.set(1, (grupoNumero7.get(1) * (cantidadNumeros.get(7) - 1) + car2p) / cantidadNumeros.get(7));
                        grupoNumero7.set(2, (grupoNumero7.get(2) * (cantidadNumeros.get(7) - 1) + car3p) / cantidadNumeros.get(7));
                        
                        grupoNumero7Aux = new ArrayList<>();
                        grupoNumero7Aux.add((double) car1);
                        grupoNumero7Aux.add((double) car2p);
                        grupoNumero7Aux.add((double) car3p);

                        grupo7ArrayArray.add(grupoNumero7Aux);                           
                        break;
                    case 8:
                        cantidadNumeros.set(8, cantidadNumeros.get(8) + 1);
                        
                        grupoNumero8.set(0, (grupoNumero8.get(0) * (cantidadNumeros.get(8) - 1) + car1) / cantidadNumeros.get(8));
                        grupoNumero8.set(1, (grupoNumero8.get(1) * (cantidadNumeros.get(8) - 1) + car2p) / cantidadNumeros.get(8));
                        grupoNumero8.set(2, (grupoNumero8.get(2) * (cantidadNumeros.get(8) - 1) + car3p) / cantidadNumeros.get(8));
                        
                        grupoNumero8Aux = new ArrayList<>();
                        grupoNumero8Aux.add((double) car1);
                        grupoNumero8Aux.add((double) car2p);
                        grupoNumero8Aux.add((double) car3p);

                        grupo8ArrayArray.add(grupoNumero8Aux);   
                        
                        break;
                    case 9:
                        cantidadNumeros.set(9, cantidadNumeros.get(9) + 1);
                        
                        grupoNumero9.set(0, (grupoNumero9.get(0) * (cantidadNumeros.get(9) - 1) + car1) / cantidadNumeros.get(9));
                        grupoNumero9.set(1, (grupoNumero9.get(1) * (cantidadNumeros.get(9) - 1) + car2p) / cantidadNumeros.get(9));
                        grupoNumero9.set(2, (grupoNumero9.get(2) * (cantidadNumeros.get(9) - 1) + car3p) / cantidadNumeros.get(9));
                        
                        grupoNumero9Aux = new ArrayList<>();
                        grupoNumero9Aux.add((double) car1);
                        grupoNumero9Aux.add((double) car2p);
                        grupoNumero9Aux.add((double) car3p);

                        grupo9ArrayArray.add(grupoNumero9Aux);   
                        
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
