package ia_reconocimientopatrones;
//import org.apache.commons.math3.stat.correlation.Covariance;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.Covariance;

public class AlgoritmoBayesiano {

    private ArrayList<Double> mediasGrupoNumero0;
    private ArrayList<Double> mediasGrupoNumero1;
    private ArrayList<Double> mediasGrupoNumero2;
    private ArrayList<Double> mediasGrupoNumero3;
    private ArrayList<Double> mediasGrupoNumero4;
    private ArrayList<Double> mediasGrupoNumero5;
    private ArrayList<Double> mediasGrupoNumero6;
    private ArrayList<Double> mediasGrupoNumero7;
    private ArrayList<Double> mediasGrupoNumero8;
    private ArrayList<Double> mediasGrupoNumero9;
    private ArrayList<Double> valoresGrupoNumero0;
    private ArrayList<Double> valoresGrupoNumero1;
    private ArrayList<Double> valoresGrupoNumero2;
    private ArrayList<Double> valoresGrupoNumero3;
    private ArrayList<Double> valoresGrupoNumero4;
    private ArrayList<Double> valoresGrupoNumero5;
    private ArrayList<Double> valoresGrupoNumero6;
    private ArrayList<Double> valoresGrupoNumero7;
    private ArrayList<Double> valoresGrupoNumero8;
    private ArrayList<Double> valoresGrupoNumero9;
    private ArrayList<ArrayList<Double>> matrizValoresGrupo0;
    private ArrayList<ArrayList<Double>> matrizValoresGrupo1;
    private ArrayList<ArrayList<Double>> matrizValoresGrupo2;
    private ArrayList<ArrayList<Double>> matrizValoresGrupo3;
    private ArrayList<ArrayList<Double>> matrizValoresGrupo4;
    private ArrayList<ArrayList<Double>> matrizValoresGrupo5;
    private ArrayList<ArrayList<Double>> matrizValoresGrupo6;
    private ArrayList<ArrayList<Double>> matrizValoresGrupo7;
    private ArrayList<ArrayList<Double>> matrizValoresGrupo8;
    private ArrayList<ArrayList<Double>> matrizValoresGrupo9;
    private ArrayList<Integer> cantidadNumeros;
    private int nroCaracteristicas;
    private ArrayList<Double> listaCaracteristicas;

    public AlgoritmoBayesiano() {
        nroCaracteristicas = 3; //3 características: nros 1's, simetría horizontal, y simetría vertical
        listaCaracteristicas = new ArrayList<>(nroCaracteristicas);

        mediasGrupoNumero0 = new ArrayList<>(nroCaracteristicas);
        mediasGrupoNumero1 = new ArrayList<>(nroCaracteristicas);
        mediasGrupoNumero2 = new ArrayList<>(nroCaracteristicas);
        mediasGrupoNumero3 = new ArrayList<>(nroCaracteristicas);
        mediasGrupoNumero4 = new ArrayList<>(nroCaracteristicas);
        mediasGrupoNumero5 = new ArrayList<>(nroCaracteristicas);
        mediasGrupoNumero6 = new ArrayList<>(nroCaracteristicas);
        mediasGrupoNumero7 = new ArrayList<>(nroCaracteristicas);
        mediasGrupoNumero8 = new ArrayList<>(nroCaracteristicas);
        mediasGrupoNumero9 = new ArrayList<>(nroCaracteristicas);
        for (int i = 1; i <= nroCaracteristicas; i++) {
            mediasGrupoNumero0.add(0.0);
            mediasGrupoNumero1.add(0.0);
            mediasGrupoNumero2.add(0.0);
            mediasGrupoNumero3.add(0.0);
            mediasGrupoNumero4.add(0.0);
            mediasGrupoNumero5.add(0.0);
            mediasGrupoNumero6.add(0.0);
            mediasGrupoNumero7.add(0.0);
            mediasGrupoNumero8.add(0.0);
            mediasGrupoNumero9.add(0.0);
            listaCaracteristicas.add(0.0);
        }

        cantidadNumeros = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            cantidadNumeros.add(0);
        }

        matrizValoresGrupo0 = new ArrayList<>(6000);
        matrizValoresGrupo1 = new ArrayList<>(6000);
        matrizValoresGrupo2 = new ArrayList<>(6000);
        matrizValoresGrupo3 = new ArrayList<>(6000);
        matrizValoresGrupo4 = new ArrayList<>(6000);
        matrizValoresGrupo5 = new ArrayList<>(6000);
        matrizValoresGrupo6 = new ArrayList<>(6000);
        matrizValoresGrupo7 = new ArrayList<>(6000);
        matrizValoresGrupo8 = new ArrayList<>(6000);
        matrizValoresGrupo9 = new ArrayList<>(6000);
    }

    public void Formula() {
        try {
            //calcular matriz de covarianza
            //calcular la matriz de covarianza inversa
            //utilizo el arreglo de medias, en este caso para el numero 0 mediasGrupoNumero0
            double[][] matrixaux0 = new double[matrizValoresGrupo0.size()][matrizValoresGrupo0.get(0).size()];
            double[][] matrixaux1 = new double[matrizValoresGrupo1.size()][matrizValoresGrupo1.get(0).size()];
            double[][] matrixaux2 = new double[matrizValoresGrupo2.size()][matrizValoresGrupo2.get(0).size()];
            double[][] matrixaux3 = new double[matrizValoresGrupo3.size()][matrizValoresGrupo3.get(0).size()];
            double[][] matrixaux4 = new double[matrizValoresGrupo4.size()][matrizValoresGrupo4.get(0).size()];
            double[][] matrixaux5 = new double[matrizValoresGrupo5.size()][matrizValoresGrupo5.get(0).size()];
            double[][] matrixaux6 = new double[matrizValoresGrupo6.size()][matrizValoresGrupo6.get(0).size()];
            double[][] matrixaux7 = new double[matrizValoresGrupo7.size()][matrizValoresGrupo7.get(0).size()];
            double[][] matrixaux8 = new double[matrizValoresGrupo8.size()][matrizValoresGrupo8.get(0).size()];
            double[][] matrixaux9 = new double[matrizValoresGrupo9.size()][matrizValoresGrupo9.get(0).size()];

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

            for (int u = 0; u < matrizValoresGrupo0.size(); u++) {
                for (int v = 0; v < matrizValoresGrupo0.get(u).size(); v++) {
                    matrixaux0[u][v] = matrizValoresGrupo0.get(u).get(v);
                }
            }

            Covariance cov0 = new Covariance(matrixaux0, false);

            rMatrix0 = cov0.getCovarianceMatrix();

            covMatrix0 = rMatrix0.getData();

            NumberFormat nf0 = NumberFormat.getInstance();
            nf0.setMaximumFractionDigits(2);

            System.out.print("Grupo 0" + "\n");

            for (int i = 0; i < covMatrix0.length; i++) {
                for (int k = 0; k < covMatrix0.length; k++) {
                    System.out.print(nf0.format(covMatrix0[i][k]) + "  ");
                }
                System.out.println();
            }

            //para el numero 1
            for (int u = 0; u < matrizValoresGrupo1.size(); u++) {
                for (int v = 0; v < matrizValoresGrupo1.get(u).size(); v++) {
                    matrixaux1[u][v] = matrizValoresGrupo1.get(u).get(v);
                }
            }

            Covariance cov1 = new Covariance(matrixaux1, false);

            rMatrix1 = cov1.getCovarianceMatrix();

            covMatrix1 = rMatrix1.getData();

            NumberFormat nf1 = NumberFormat.getInstance();
            nf1.setMaximumFractionDigits(2);

            System.out.print("Grupo 1" + "\n");

            for (int i = 0; i < covMatrix1.length; i++) {
                for (int k = 0; k < covMatrix1.length; k++) {
                    System.out.print(nf1.format(covMatrix1[i][k]) + "  ");
                }
                System.out.println();
            }

            //para el numero 2
            for (int u = 0; u < matrizValoresGrupo2.size(); u++) {
                for (int v = 0; v < matrizValoresGrupo2.get(u).size(); v++) {
                    matrixaux2[u][v] = matrizValoresGrupo2.get(u).get(v);
                }
            }

            Covariance cov2 = new Covariance(matrixaux2, false);

            rMatrix2 = cov2.getCovarianceMatrix();

            covMatrix2 = rMatrix2.getData();

            NumberFormat nf2 = NumberFormat.getInstance();
            nf2.setMaximumFractionDigits(2);

            System.out.print("Grupo 2" + "\n");

            for (int i = 0; i < covMatrix2.length; i++) {
                for (int k = 0; k < covMatrix2.length; k++) {
                    System.out.print(nf2.format(covMatrix2[i][k]) + "  ");
                }
                System.out.println();
            }

            //para el numero 3
            for (int u = 0; u < matrizValoresGrupo3.size(); u++) {
                for (int v = 0; v < matrizValoresGrupo3.get(u).size(); v++) {
                    matrixaux3[u][v] = matrizValoresGrupo3.get(u).get(v);
                }
            }

            Covariance cov3 = new Covariance(matrixaux3, false);

            rMatrix3 = cov3.getCovarianceMatrix();

            covMatrix3 = rMatrix3.getData();

            NumberFormat nf3 = NumberFormat.getInstance();
            nf3.setMaximumFractionDigits(2);

            System.out.print("Grupo 3" + "\n");

            for (int i = 0; i < covMatrix3.length; i++) {
                for (int k = 0; k < covMatrix3.length; k++) {
                    System.out.print(nf3.format(covMatrix3[i][k]) + "  ");
                }
                System.out.println();
            }

            //para el numero 4
            for (int u = 0; u < matrizValoresGrupo4.size(); u++) {
                for (int v = 0; v < matrizValoresGrupo4.get(u).size(); v++) {
                    matrixaux4[u][v] = matrizValoresGrupo4.get(u).get(v);
                }
            }

            Covariance cov4 = new Covariance(matrixaux4, false);

            rMatrix4 = cov4.getCovarianceMatrix();

            covMatrix4 = rMatrix4.getData();

            NumberFormat nf4 = NumberFormat.getInstance();
            nf4.setMaximumFractionDigits(2);

            System.out.print("Grupo 4" + "\n");

            for (int i = 0; i < covMatrix4.length; i++) {
                for (int k = 0; k < covMatrix4.length; k++) {
                    System.out.print(nf4.format(covMatrix4[i][k]) + "  ");
                }
                System.out.println();
            }


            //para el numero 5
            for (int u = 0; u < matrizValoresGrupo5.size(); u++) {
                for (int v = 0; v < matrizValoresGrupo5.get(u).size(); v++) {
                    matrixaux5[u][v] = matrizValoresGrupo5.get(u).get(v);
                }
            }

            Covariance cov5 = new Covariance(matrixaux5, false);

            rMatrix5 = cov5.getCovarianceMatrix();

            covMatrix5 = rMatrix5.getData();

            NumberFormat nf5 = NumberFormat.getInstance();
            nf5.setMaximumFractionDigits(2);

            System.out.print("Grupo 5" + "\n");

            for (int i = 0; i < covMatrix5.length; i++) {
                for (int k = 0; k < covMatrix5.length; k++) {
                    System.out.print(nf5.format(covMatrix5[i][k]) + "  ");
                }
                System.out.println();
            }

            //para el numero 6
            for (int u = 0; u < matrizValoresGrupo6.size(); u++) {
                for (int v = 0; v < matrizValoresGrupo6.get(u).size(); v++) {
                    matrixaux6[u][v] = matrizValoresGrupo6.get(u).get(v);
                }
            }

            Covariance cov6 = new Covariance(matrixaux6, false);

            rMatrix6 = cov6.getCovarianceMatrix();

            covMatrix6 = rMatrix6.getData();

            NumberFormat nf6 = NumberFormat.getInstance();
            nf6.setMaximumFractionDigits(2);

            System.out.print("Grupo 6" + "\n");

            for (int i = 0; i < covMatrix6.length; i++) {
                for (int k = 0; k < covMatrix6.length; k++) {
                    System.out.print(nf6.format(covMatrix6[i][k]) + "  ");
                }
                System.out.println();
            }

            //para el numero 7
            for (int u = 0; u < matrizValoresGrupo7.size(); u++) {
                for (int v = 0; v < matrizValoresGrupo7.get(u).size(); v++) {
                    matrixaux7[u][v] = matrizValoresGrupo7.get(u).get(v);
                }
            }

            Covariance cov7 = new Covariance(matrixaux7, false);

            rMatrix7 = cov7.getCovarianceMatrix();

            covMatrix7 = rMatrix7.getData();

            NumberFormat nf7 = NumberFormat.getInstance();
            nf7.setMaximumFractionDigits(2);

            System.out.print("Grupo 7" + "\n");

            for (int i = 0; i < covMatrix7.length; i++) {
                for (int k = 0; k < covMatrix7.length; k++) {
                    System.out.print(nf7.format(covMatrix7[i][k]) + "  ");
                }
                System.out.println();
            }

            //para el numero 8
            for (int u = 0; u < matrizValoresGrupo8.size(); u++) {
                for (int v = 0; v < matrizValoresGrupo8.get(u).size(); v++) {
                    matrixaux8[u][v] = matrizValoresGrupo8.get(u).get(v);
                }
            }

            Covariance cov8 = new Covariance(matrixaux8, false);

            rMatrix8 = cov8.getCovarianceMatrix();

            covMatrix8 = rMatrix8.getData();

            NumberFormat nf8 = NumberFormat.getInstance();
            nf8.setMaximumFractionDigits(2);

            System.out.print("Grupo 8" + "\n");

            for (int i = 0; i < covMatrix8.length; i++) {
                for (int k = 0; k < covMatrix8.length; k++) {
                    System.out.print(nf8.format(covMatrix8[i][k]) + "  ");
                }
                System.out.println();
            }

            //para el numero 9
            for (int u = 0; u < matrizValoresGrupo9.size(); u++) {
                for (int v = 0; v < matrizValoresGrupo9.get(u).size(); v++) {
                    matrixaux9[u][v] = matrizValoresGrupo9.get(u).get(v);
                }
            }

            Covariance cov9 = new Covariance(matrixaux9, false);

            rMatrix9 = cov9.getCovarianceMatrix();

            covMatrix9 = rMatrix9.getData();

            NumberFormat nf9 = NumberFormat.getInstance();
            nf9.setMaximumFractionDigits(2);

            System.out.print("Grupo 9" + "\n");

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
            ArrayList<String> output = new ArrayList<>(50);

            for (int i = 0; i < nroImagenes; i++) {
                byte[] imagen = imagenesArray.get(i);
                int[][] pixeles = new int[28][28];
                int t = 0;
                int num = labelsArray.get(i);

                for (int u = 0; u < 28; u++) {
                    for (int v = 0; v < 28; v++) {
                        int p = this.UnsignedToBytes(imagen[t]);
                        if (p != 0) {
                            p = 1;
                        }
                        pixeles[u][v] = p;
                        t++;
                    }
                }
                int[][] pixelesTrimeados = this.RemoverPadding4(pixeles);

                // <editor-fold defaultstate="collapsed" desc="EXTRAEMOS LAS CARACTERÍSTICAS DE LA IMÁGEN">
                listaCaracteristicas.clear();
                int car1 = CalcularCaracteristica1(pixelesTrimeados);
                listaCaracteristicas.add((double) car1);
                int car2 = SimetriaHorizontal(pixelesTrimeados);
                listaCaracteristicas.add((double) car2);
                int car3 = SimetriaVertical(pixelesTrimeados);
                listaCaracteristicas.add((double) car3);
                // </editor-fold>

                if (i < 50) {
                    //output.add("Label: " + num + " - 1s: " + car1 + " - SimV: " + car2 + " - SimH: " + car3);
                    output.add(num + "\t" + car1 + "\t" + car2 + "\t" + car3);
                }

                // <editor-fold defaultstate="collapsed" desc="ALMACENAMIENTO DE LAS NUEVAS MEDIAS">
                switch (num) {
                    case 0:
                        cantidadNumeros.set(num, cantidadNumeros.get(num) + 1);
                        valoresGrupoNumero0 = new ArrayList<>(nroCaracteristicas);
                        // PARA N-CARACTERÍSTICAS
                        for (int k = 0; k < nroCaracteristicas; k++) {
                            double valorCar = listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
                            mediasGrupoNumero0.set(k, (mediasGrupoNumero0.get(k) * (cantidadNumeros.get(num) - 1) + valorCar) / cantidadNumeros.get(num));
                            valoresGrupoNumero0.add((double) valorCar);
                        }
                        matrizValoresGrupo0.add(valoresGrupoNumero0);
                        break;
                    case 1:
                        cantidadNumeros.set(num, cantidadNumeros.get(num) + 1);
                        valoresGrupoNumero1 = new ArrayList<>(nroCaracteristicas);
                        // PARA N-CARACTERÍSTICAS
                        for (int k = 0; k < nroCaracteristicas; k++) {
                            double valorCar = listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
                            mediasGrupoNumero1.set(k, (mediasGrupoNumero1.get(k) * (cantidadNumeros.get(num) - 1) + valorCar) / cantidadNumeros.get(num));
                            valoresGrupoNumero1.add((double) valorCar);
                        }
                        matrizValoresGrupo1.add(valoresGrupoNumero1);
                        break;
                    case 2:
                        cantidadNumeros.set(num, cantidadNumeros.get(num) + 1);
                        valoresGrupoNumero2 = new ArrayList<>(nroCaracteristicas);
                        // PARA N-CARACTERÍSTICAS
                        for (int k = 0; k < nroCaracteristicas; k++) {
                            double valorCar = listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
                            mediasGrupoNumero2.set(k, (mediasGrupoNumero2.get(k) * (cantidadNumeros.get(num) - 1) + valorCar) / cantidadNumeros.get(num));
                            valoresGrupoNumero2.add((double) valorCar);
                        }
                        matrizValoresGrupo2.add(valoresGrupoNumero2);
                        break;
                    case 3:
                        cantidadNumeros.set(num, cantidadNumeros.get(num) + 1);
                        valoresGrupoNumero3 = new ArrayList<>(nroCaracteristicas);
                        // PARA N-CARACTERÍSTICAS
                        for (int k = 0; k < nroCaracteristicas; k++) {
                            double valorCar = listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
                            mediasGrupoNumero3.set(k, (mediasGrupoNumero3.get(k) * (cantidadNumeros.get(num) - 1) + valorCar) / cantidadNumeros.get(num));
                            valoresGrupoNumero3.add((double) valorCar);
                        }
                        matrizValoresGrupo3.add(valoresGrupoNumero3);
                        break;
                    case 4:
                        cantidadNumeros.set(num, cantidadNumeros.get(num) + 1);
                        valoresGrupoNumero4 = new ArrayList<>(nroCaracteristicas);
                        // PARA N-CARACTERÍSTICAS
                        for (int k = 0; k < nroCaracteristicas; k++) {
                            double valorCar = listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
                            mediasGrupoNumero4.set(k, (mediasGrupoNumero4.get(k) * (cantidadNumeros.get(num) - 1) + valorCar) / cantidadNumeros.get(num));
                            valoresGrupoNumero4.add((double) valorCar);
                        }
                        matrizValoresGrupo4.add(valoresGrupoNumero4);
                        break;
                    case 5:
                        cantidadNumeros.set(num, cantidadNumeros.get(num) + 1);
                        valoresGrupoNumero5 = new ArrayList<>(nroCaracteristicas);
                        // PARA N-CARACTERÍSTICAS
                        for (int k = 0; k < nroCaracteristicas; k++) {
                            double valorCar = listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
                            mediasGrupoNumero5.set(k, (mediasGrupoNumero5.get(k) * (cantidadNumeros.get(num) - 1) + valorCar) / cantidadNumeros.get(num));
                            valoresGrupoNumero5.add((double) valorCar);
                        }
                        matrizValoresGrupo5.add(valoresGrupoNumero5);
                        break;
                    case 6:
                        cantidadNumeros.set(num, cantidadNumeros.get(num) + 1);
                        valoresGrupoNumero6 = new ArrayList<>(nroCaracteristicas);
                        // PARA N-CARACTERÍSTICAS
                        for (int k = 0; k < nroCaracteristicas; k++) {
                            double valorCar = listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
                            mediasGrupoNumero6.set(k, (mediasGrupoNumero6.get(k) * (cantidadNumeros.get(num) - 1) + valorCar) / cantidadNumeros.get(num));
                            valoresGrupoNumero6.add((double) valorCar);
                        }
                        matrizValoresGrupo6.add(valoresGrupoNumero6);
                        break;
                    case 7:
                        cantidadNumeros.set(num, cantidadNumeros.get(num) + 1);
                        valoresGrupoNumero7 = new ArrayList<>(nroCaracteristicas);
                        // PARA N-CARACTERÍSTICAS
                        for (int k = 0; k < nroCaracteristicas; k++) {
                            double valorCar = listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
                            mediasGrupoNumero7.set(k, (mediasGrupoNumero7.get(k) * (cantidadNumeros.get(num) - 1) + valorCar) / cantidadNumeros.get(num));
                            valoresGrupoNumero7.add((double) valorCar);
                        }
                        matrizValoresGrupo7.add(valoresGrupoNumero7);
                        break;
                    case 8:
                        cantidadNumeros.set(num, cantidadNumeros.get(num) + 1);
                        valoresGrupoNumero8 = new ArrayList<>(nroCaracteristicas);
                        // PARA N-CARACTERÍSTICAS
                        for (int k = 0; k < nroCaracteristicas; k++) {
                            double valorCar = listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
                            mediasGrupoNumero8.set(k, (mediasGrupoNumero8.get(k) * (cantidadNumeros.get(num) - 1) + valorCar) / cantidadNumeros.get(num));
                            valoresGrupoNumero8.add((double) valorCar);
                        }
                        matrizValoresGrupo8.add(valoresGrupoNumero8);
                        break;
                    case 9:
                        cantidadNumeros.set(num, cantidadNumeros.get(num) + 1);
                        valoresGrupoNumero9 = new ArrayList<>(nroCaracteristicas);
                        // PARA N-CARACTERÍSTICAS
                        for (int k = 0; k < nroCaracteristicas; k++) {
                            double valorCar = listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
                            mediasGrupoNumero9.set(k, (mediasGrupoNumero9.get(k) * (cantidadNumeros.get(num) - 1) + valorCar) / cantidadNumeros.get(num));
                            valoresGrupoNumero9.add((double) valorCar);
                        }
                        matrizValoresGrupo9.add(valoresGrupoNumero9);
                        break;
                }
                // </editor-fold>
            }
            Collections.sort(output);
            System.out.println("Label\t1's\tSimV\tSimH");
            for (String s : output) {
                System.out.println(s);
            }
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
