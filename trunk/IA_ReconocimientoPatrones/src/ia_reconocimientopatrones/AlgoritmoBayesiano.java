package ia_reconocimientopatrones;
//import org.apache.commons.math3.stat.correlation.Covariance;
import Jama.EigenvalueDecomposition;
import Jama.Matrix;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;

public class AlgoritmoBayesiano {

    // <editor-fold defaultstate="collapsed" desc="ATRIBUTOS">                
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
//    private ArrayList<Integer> valoresGrupoNumero0;
//    private ArrayList<Integer> valoresGrupoNumero1;
//    private ArrayList<Integer> valoresGrupoNumero2;
//    private ArrayList<Integer> valoresGrupoNumero3;
//    private ArrayList<Integer> valoresGrupoNumero4;
//    private ArrayList<Integer> valoresGrupoNumero5;
//    private ArrayList<Integer> valoresGrupoNumero6;
//    private ArrayList<Integer> valoresGrupoNumero7;
//    private ArrayList<Integer> valoresGrupoNumero8;
//    private ArrayList<Integer> valoresGrupoNumero9;
    private ArrayList<ArrayList<Integer>> matrizValoresGrupo0;
    private ArrayList<ArrayList<Integer>> matrizValoresGrupo1;
    private ArrayList<ArrayList<Integer>> matrizValoresGrupo2;
    private ArrayList<ArrayList<Integer>> matrizValoresGrupo3;
    private ArrayList<ArrayList<Integer>> matrizValoresGrupo4;
    private ArrayList<ArrayList<Integer>> matrizValoresGrupo5;
    private ArrayList<ArrayList<Integer>> matrizValoresGrupo6;
    private ArrayList<ArrayList<Integer>> matrizValoresGrupo7;
    private ArrayList<ArrayList<Integer>> matrizValoresGrupo8;
    private ArrayList<ArrayList<Integer>> matrizValoresGrupo9;
    private ArrayList<Integer> cantidadNumeros;
    private int nroCaracteristicas;
    private ArrayList<Integer> listaCaracteristicas;
    private ArrayList<Double> listaCaracteristicas2;
    private double[][] covMatrix0;
    private double[][] covMatrix1;
    private double[][] covMatrix2;
    private double[][] covMatrix3;
    private double[][] covMatrix4;
    private double[][] covMatrix5;
    private double[][] covMatrix6;
    private double[][] covMatrix7;
    private double[][] covMatrix8;
    private double[][] covMatrix9;
    private double[][] sigmaInversaGrupo0;
    private double[][] sigmaInversaGrupo1;
    private double[][] sigmaInversaGrupo2;
    private double[][] sigmaInversaGrupo3;
    private double[][] sigmaInversaGrupo4;
    private double[][] sigmaInversaGrupo5;
    private double[][] sigmaInversaGrupo6;
    private double[][] sigmaInversaGrupo7;
    private double[][] sigmaInversaGrupo8;
    private double[][] sigmaInversaGrupo9;
    private double determinanteGrupo0;
    private double determinanteGrupo1;
    private double determinanteGrupo2;
    private double determinanteGrupo3;
    private double determinanteGrupo4;
    private double determinanteGrupo5;
    private double determinanteGrupo6;
    private double determinanteGrupo7;
    private double determinanteGrupo8;
    private double determinanteGrupo9;
    private Helpers helper;
    ArrayList<ArrayList<Double>> matrizValoresGrupoSetosa;
    ArrayList<ArrayList<Double>> matrizValoresGrupoVersicolor;
    ArrayList<ArrayList<Double>> matrizValoresGrupoVirginica;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="CONSTRUCTOR">

    public AlgoritmoBayesiano() {
        helper = new Helpers();
        nroCaracteristicas = 8 * 8; //20x14 --> 280 | 20x20 --> 400
        listaCaracteristicas = new ArrayList<>(nroCaracteristicas);
        listaCaracteristicas2 = new ArrayList<>(nroCaracteristicas);

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
            listaCaracteristicas.add(0);
            listaCaracteristicas2.add(0.0);
        }

        cantidadNumeros = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            cantidadNumeros.add(0);
        }

        matrizValoresGrupo9 = new ArrayList<>(5949);
        matrizValoresGrupo8 = new ArrayList<>(5851);
        matrizValoresGrupo7 = new ArrayList<>(6265);
        matrizValoresGrupo6 = new ArrayList<>(5918);
        matrizValoresGrupo5 = new ArrayList<>(5421);
        matrizValoresGrupo4 = new ArrayList<>(5842);
        matrizValoresGrupo3 = new ArrayList<>(6131);
        matrizValoresGrupo2 = new ArrayList<>(5958);
        matrizValoresGrupo1 = new ArrayList<>(6742);
        matrizValoresGrupo0 = new ArrayList<>(5923); //5923

        for (int i = 0; i < 5923; i++) {
            ArrayList<Integer> lista = new ArrayList<>(nroCaracteristicas);
            for (int j = 0; j < nroCaracteristicas; j++) {
                lista.add(0);
            }
            matrizValoresGrupo0.add(lista);
        }

        for (int i = 0; i < 6742; i++) {
            ArrayList<Integer> lista = new ArrayList<>(nroCaracteristicas);
            for (int j = 0; j < nroCaracteristicas; j++) {
                lista.add(0);
            }
            matrizValoresGrupo1.add(lista);
        }

        for (int i = 0; i < 5958; i++) {
            ArrayList<Integer> lista = new ArrayList<>(nroCaracteristicas);
            for (int j = 0; j < nroCaracteristicas; j++) {
                lista.add(0);
            }
            matrizValoresGrupo2.add(lista);
        }

        for (int i = 0; i < 6131; i++) {
            ArrayList<Integer> lista = new ArrayList<>(nroCaracteristicas);
            for (int j = 0; j < nroCaracteristicas; j++) {
                lista.add(0);
            }
            matrizValoresGrupo3.add(lista);
        }

        for (int i = 0; i < 5842; i++) {
            ArrayList<Integer> lista = new ArrayList<>(nroCaracteristicas);
            for (int j = 0; j < nroCaracteristicas; j++) {
                lista.add(0);
            }
            matrizValoresGrupo4.add(lista);
        }

        for (int i = 0; i < 5421; i++) {
            ArrayList<Integer> lista = new ArrayList<>(nroCaracteristicas);
            for (int j = 0; j < nroCaracteristicas; j++) {
                lista.add(0);
            }
            matrizValoresGrupo5.add(lista);
        }

        for (int i = 0; i < 5918; i++) {
            ArrayList<Integer> lista = new ArrayList<>(nroCaracteristicas);
            for (int j = 0; j < nroCaracteristicas; j++) {
                lista.add(0);
            }
            matrizValoresGrupo6.add(lista);
        }

        for (int i = 0; i < 6265; i++) {
            ArrayList<Integer> lista = new ArrayList<>(nroCaracteristicas);
            for (int j = 0; j < nroCaracteristicas; j++) {
                lista.add(0);
            }
            matrizValoresGrupo7.add(lista);
        }

        for (int i = 0; i < 5851; i++) {
            ArrayList<Integer> lista = new ArrayList<>(nroCaracteristicas);
            for (int j = 0; j < nroCaracteristicas; j++) {
                lista.add(0);
            }
            matrizValoresGrupo8.add(lista);
        }

        for (int i = 0; i < 5949; i++) {
            ArrayList<Integer> lista = new ArrayList<>(nroCaracteristicas);
            for (int j = 0; j < nroCaracteristicas; j++) {
                lista.add(0);
            }
            matrizValoresGrupo9.add(lista);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ENTRENAMIENTO DEL MODELO">
    public void Entrenar_Modelo(int nroImagenes, ArrayList<byte[]> imagenesArray, ArrayList<Integer> labelsArray) {
        //try {
        //ArrayList<String> output = new ArrayList<>(50);
        HaarFilter haar = new HaarFilter(16);
        haar.setFractionalBits(0);
        haar.setIterations(1);

        for (int M = 0; M < 3; M++) {
            int INICIO = nroImagenes / 3 * M;
            int FIN = INICIO + ((nroImagenes / 3) - 1);
            for (int i = INICIO; i <= FIN; i++) { //0..19999 - 20k..39999 - 40k..59999
                byte[] imagen = imagenesArray.get(i); // original: 28x28
                byte[] imagen16x16 = GeneradorImagenes.Convertir_A_Imagen16x16(imagen); //nueva: 16x16 --> 256
                int[] filter = haar.filter(imagen16x16, null); //size: 256; 1er cuadrante img reducida; los otros tienen las formas
                //int[] imgReducida = this.ObtenerPrimerCuadrante(filter);
                int[] promediosTresCuadrantes = this.PromedioOtrosTresCuadrantes(filter); //8x8
                int t = 0;
                int num = labelsArray.get(i);

                //helper.ImprimirVectorEnFormaMatriz(promediosTresCuadrantes, 8);
                for (int k = 0; k < nroCaracteristicas; k++) {
                    listaCaracteristicas.set(k, promediosTresCuadrantes[k]);
                }

//                for (int u = 0; u < 28; u++) {
//                    if ((u > 5) && (u < 22)) { //5,22: ignorar 6 filas arriba y abajo. Total 16 filas
//                        for (int v = 0; v < 28; v++) {
//                            if ((v > 5) && (v < 22)) { //6,21: ignorar 7 columnas izq y der. Total 14 columnas
//                                int p = helper.UnsignedToBytes(imagen[t]);
////                                if (p == 0) {
////                                    p = 1;
////                                }
//                                //pixeles[u - 4][v - 4] = p;
//                                listaCaracteristicas.set(t, p);
//                                t++;
//                            }
//                        }
//                    }
//               }
                //int[][] pixelesTrimeados = helper.RemoverPadding4(pixeles);

                // <editor-fold defaultstate="collapsed" desc="EXTRAEMOS LAS CARACTERÍSTICAS DE LA IMÁGEN">
//                listaCaracteristicas.clear();
//                int car1 = CalcularCaracteristica1(pixelesTrimeados);
//                listaCaracteristicas.add((double) car1);
//                int car2 = SimetriaHorizontal(pixelesTrimeados);
//                listaCaracteristicas.add((double) car2);
//                int car3 = SimetriaVertical(pixelesTrimeados);
//                listaCaracteristicas.add((double) car3);
                // </editor-fold>

                //if (i < 50) {
                //output.add("Label: " + num + " - 1s: " + car1 + " - SimV: " + car2 + " - SimH: " + car3);
                //output.add(num + "\t" + car1 + "\t" + car2 + "\t" + car3);
                //}

                // <editor-fold defaultstate="collapsed" desc="ALMACENAMIENTO DE LAS MEDIAS y VALORES">
                switch (num) {
                    case 0:
                        //matrizValoresGrupo0.add(null);
                        int n = cantidadNumeros.get(num);
                        cantidadNumeros.set(num, n + 1);
                        //valoresGrupoNumero0 = new ArrayList<>(nroCaracteristicas);
                        // PARA N-CARACTERÍSTICAS
                        for (int k = 0; k < nroCaracteristicas; k++) {
                            int valorCar = listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
                            mediasGrupoNumero0.set(k, (mediasGrupoNumero0.get(k) * (cantidadNumeros.get(num) - 1) + valorCar) / cantidadNumeros.get(num));
                            //valoresGrupoNumero0.add((double) valorCar);
                            matrizValoresGrupo0.get(n).set(k, valorCar);
                        }
                        //matrizValoresGrupo0.add(valoresGrupoNumero0);
                        //valoresGrupoNumero0.clear();
                        break;
                    case 1:
                        n = cantidadNumeros.get(num);
                        //System.out.println(n);
                        cantidadNumeros.set(num, n + 1);
                        //valoresGrupoNumero1 = new ArrayList<>(nroCaracteristicas);
                        // PARA N-CARACTERÍSTICAS
                        for (int k = 0; k < nroCaracteristicas; k++) {
                            int valorCar = listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
                            mediasGrupoNumero1.set(k, (mediasGrupoNumero1.get(k) * (cantidadNumeros.get(num) - 1) + valorCar) / cantidadNumeros.get(num));
                            //valoresGrupoNumero1.add((double) valorCar);
                            matrizValoresGrupo1.get(n).set(k, valorCar);
                        }
                        //matrizValoresGrupo1.add(valoresGrupoNumero1);
                        //valoresGrupoNumero1.clear();
                        break;
                    case 2:
                        n = cantidadNumeros.get(num);
                        cantidadNumeros.set(num, n + 1);
                        //valoresGrupoNumero2 = new ArrayList<>(nroCaracteristicas);
                        // PARA N-CARACTERÍSTICAS
                        for (int k = 0; k < nroCaracteristicas; k++) {
                            int valorCar = listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
                            mediasGrupoNumero2.set(k, (mediasGrupoNumero2.get(k) * (cantidadNumeros.get(num) - 1) + valorCar) / cantidadNumeros.get(num));
                            //valoresGrupoNumero2.add((double) valorCar);
                            matrizValoresGrupo2.get(n).set(k, valorCar);
                        }
                        //matrizValoresGrupo2.add(valoresGrupoNumero2);
                        //valoresGrupoNumero2.clear();
                        break;
                    case 3:
                        n = cantidadNumeros.get(num);
                        cantidadNumeros.set(num, n + 1);
                        //valoresGrupoNumero3 = new ArrayList<>(nroCaracteristicas);
                        // PARA N-CARACTERÍSTICAS
                        for (int k = 0; k < nroCaracteristicas; k++) {
                            int valorCar = listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
                            mediasGrupoNumero3.set(k, (mediasGrupoNumero3.get(k) * (cantidadNumeros.get(num) - 1) + valorCar) / cantidadNumeros.get(num));
                            //valoresGrupoNumero3.add((double) valorCar);
                            matrizValoresGrupo3.get(n).set(k, valorCar);
                        }
                        //matrizValoresGrupo3.add(valoresGrupoNumero3);
                        //valoresGrupoNumero3.clear();
                        break;
                    case 4:
                        n = cantidadNumeros.get(num);
                        cantidadNumeros.set(num, n + 1);
                        //valoresGrupoNumero4 = new ArrayList<>(nroCaracteristicas);
                        // PARA N-CARACTERÍSTICAS
                        for (int k = 0; k < nroCaracteristicas; k++) {
                            int valorCar = listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
                            mediasGrupoNumero4.set(k, (mediasGrupoNumero4.get(k) * (cantidadNumeros.get(num) - 1) + valorCar) / cantidadNumeros.get(num));
                            //valoresGrupoNumero4.add((double) valorCar);
                            matrizValoresGrupo4.get(n).set(k, valorCar);
                        }
                        //matrizValoresGrupo4.add(valoresGrupoNumero4);
                        //valoresGrupoNumero4.clear();
                        break;
                    case 5:
                        n = cantidadNumeros.get(num);
                        cantidadNumeros.set(num, n + 1);
                        //valoresGrupoNumero5 = new ArrayList<>(nroCaracteristicas);
                        // PARA N-CARACTERÍSTICAS
                        for (int k = 0; k < nroCaracteristicas; k++) {
                            int valorCar = listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
                            //System.out.print(valorCar+ " ");
                            mediasGrupoNumero5.set(k, (mediasGrupoNumero5.get(k) * (cantidadNumeros.get(num) - 1) + valorCar) / cantidadNumeros.get(num));
                            //valoresGrupoNumero5.add((double) valorCar);
                            matrizValoresGrupo5.get(n).set(k, valorCar);
                        }
                        //System.out.println("");
                        //matrizValoresGrupo5.add(valoresGrupoNumero5);
                        //valoresGrupoNumero5.clear();
                        break;
                    case 6:
                        n = cantidadNumeros.get(num);
                        cantidadNumeros.set(num, n + 1);
                        //valoresGrupoNumero6 = new ArrayList<>(nroCaracteristicas);
                        // PARA N-CARACTERÍSTICAS
                        for (int k = 0; k < nroCaracteristicas; k++) {
                            int valorCar = listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
                            mediasGrupoNumero6.set(k, (mediasGrupoNumero6.get(k) * (cantidadNumeros.get(num) - 1) + valorCar) / cantidadNumeros.get(num));
                            //valoresGrupoNumero6.add((double) valorCar);
                            matrizValoresGrupo6.get(n).set(k, valorCar);
                        }
                        //matrizValoresGrupo6.add(valoresGrupoNumero6);
                        //valoresGrupoNumero6.clear();
                        break;
                    case 7:
                        n = cantidadNumeros.get(num);
                        cantidadNumeros.set(num, n + 1);
                        //valoresGrupoNumero7 = new ArrayList<>(nroCaracteristicas);
                        // PARA N-CARACTERÍSTICAS
                        for (int k = 0; k < nroCaracteristicas; k++) {
                            int valorCar = listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
                            mediasGrupoNumero7.set(k, (mediasGrupoNumero7.get(k) * (cantidadNumeros.get(num) - 1) + valorCar) / cantidadNumeros.get(num));
                            //valoresGrupoNumero7.add((double) valorCar);
                            matrizValoresGrupo7.get(n).set(k, valorCar);
                        }
                        // matrizValoresGrupo7.add(valoresGrupoNumero7);
                        // valoresGrupoNumero7.clear();
                        break;
                    case 8:
                        n = cantidadNumeros.get(num);
                        cantidadNumeros.set(num, n + 1);
                        //valoresGrupoNumero8 = new ArrayList<>(nroCaracteristicas);
                        // PARA N-CARACTERÍSTICAS
                        for (int k = 0; k < nroCaracteristicas; k++) {
                            int valorCar = listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
                            mediasGrupoNumero8.set(k, (mediasGrupoNumero8.get(k) * (cantidadNumeros.get(num) - 1) + valorCar) / cantidadNumeros.get(num));
                            //valoresGrupoNumero8.add((double) valorCar);
                            matrizValoresGrupo8.get(n).set(k, valorCar);
                        }
                        //  matrizValoresGrupo8.add(valoresGrupoNumero8);
                        //  valoresGrupoNumero8.clear();
                        break;
                    case 9:
                        n = cantidadNumeros.get(num);
                        cantidadNumeros.set(num, n + 1);
                        //valoresGrupoNumero9 = new ArrayList<>(nroCaracteristicas);
                        // PARA N-CARACTERÍSTICAS
                        for (int k = 0; k < nroCaracteristicas; k++) {
                            int valorCar = listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
                            mediasGrupoNumero9.set(k, (mediasGrupoNumero9.get(k) * (cantidadNumeros.get(num) - 1) + valorCar) / cantidadNumeros.get(num));
                            //valoresGrupoNumero9.add((double) valorCar);
                            matrizValoresGrupo9.get(n).set(k, valorCar);
                        }
                        // matrizValoresGrupo9.add(valoresGrupoNumero9);
                        // valoresGrupoNumero9.clear();
                        break;
                }
                // </editor-fold>
            }
        }

        //Para aplicar PCA - reducir dimensionalidad
        //GenerarMatrizCorrelacionyAutovalores();

        //Para los valores que se usan en FuncionDiscriminante_Precalculada
        //GenerarMatricesCovarianzas();
        //GenerarSigmasInversas();
        //GenerarDeterminantes();

        //otra es generar sigmas y det. e inmediatamente liberar la mem. usada por las covarianzas para no ocupar tanta memoria
        GenerarCovarianzas_SigmasInversas_Determinantes();

//            System.out.println("Label\t1's\tSimV\tSimH");        
//            Collections.sort(output);
//            System.out.println("Label\t1's\tSimV\tSimH");
//            for (String s : output) {
//                System.out.println(s);
//            }
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
    }

    public void GenerarMatricesCovarianzas() {
        try {
            covMatrix0 = MatrizCovarianza(matrizValoresGrupo0);
            helper.ImprimirMatriz(covMatrix0, "Grupo 0");

            covMatrix1 = MatrizCovarianza(matrizValoresGrupo1);
            helper.ImprimirMatriz(covMatrix1, "Grupo 1");

            covMatrix2 = MatrizCovarianza(matrizValoresGrupo2);
            helper.ImprimirMatriz(covMatrix2, "Grupo 2");

            covMatrix3 = MatrizCovarianza(matrizValoresGrupo3);
            helper.ImprimirMatriz(covMatrix3, "Grupo 3");

            covMatrix4 = MatrizCovarianza(matrizValoresGrupo4);
            helper.ImprimirMatriz(covMatrix4, "Grupo 4");

            covMatrix5 = MatrizCovarianza(matrizValoresGrupo5);
            helper.ImprimirMatriz(covMatrix5, "Grupo 5");

            covMatrix6 = MatrizCovarianza(matrizValoresGrupo6);
            helper.ImprimirMatriz(covMatrix6, "Grupo 6");

            covMatrix7 = MatrizCovarianza(matrizValoresGrupo7);
            helper.ImprimirMatriz(covMatrix7, "Grupo 7");

            covMatrix8 = MatrizCovarianza(matrizValoresGrupo8);
            helper.ImprimirMatriz(covMatrix8, "Grupo 8");

            covMatrix9 = MatrizCovarianza(matrizValoresGrupo9);
            helper.ImprimirMatriz(covMatrix9, "Grupo 9");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void GenerarSigmasInversas() {
        try {
            sigmaInversaGrupo0 = Inversa(covMatrix0);
            sigmaInversaGrupo1 = Inversa(covMatrix1);
            sigmaInversaGrupo2 = Inversa(covMatrix2);
            sigmaInversaGrupo3 = Inversa(covMatrix3);
            sigmaInversaGrupo4 = Inversa(covMatrix4);
            sigmaInversaGrupo5 = Inversa(covMatrix5);
            sigmaInversaGrupo6 = Inversa(covMatrix6);
            sigmaInversaGrupo7 = Inversa(covMatrix7);
            sigmaInversaGrupo8 = Inversa(covMatrix8);
            sigmaInversaGrupo9 = Inversa(covMatrix9);
        } catch (Exception e) {
        }
    }

    public void GenerarDeterminantes() {
        try {
            determinanteGrupo0 = Determinante(covMatrix0);
            determinanteGrupo1 = Determinante(covMatrix1);
            determinanteGrupo2 = Determinante(covMatrix2);
            determinanteGrupo3 = Determinante(covMatrix3);
            determinanteGrupo4 = Determinante(covMatrix4);
            determinanteGrupo5 = Determinante(covMatrix5);
            determinanteGrupo6 = Determinante(covMatrix6);
            determinanteGrupo7 = Determinante(covMatrix7);
            determinanteGrupo8 = Determinante(covMatrix8);
            determinanteGrupo9 = Determinante(covMatrix9);
        } catch (Exception e) {
        }
    }

    public void GenerarCovarianzas_SigmasInversas_Determinantes() {
        try {

            covMatrix0 = MatrizCovarianza(matrizValoresGrupo0);
            matrizValoresGrupo0 = null;
            //helper.ImprimirMatriz(covMatrix0, "Grupo 0");
            Matrix m = new Matrix(covMatrix0);
            //sigmaInversaGrupo0 = Inversa(covMatrix0);
            determinanteGrupo0 = m.det();
            //System.out.println(determinanteGrupo0);
            sigmaInversaGrupo0 = m.inverse().getArray();
            //determinanteGrupo0 = Determinante(covMatrix0);
            //determinanteGrupo0 = Determinant.det(covMatrix0);
            //determinanteGrupo0 = Determinant.getDecDet(covMatrix0);
            //determinanteGrupo0 = Determinant.determinant(covMatrix0); 
            covMatrix0 = null; // para liberar la mem usada

            covMatrix1 = MatrizCovarianza(matrizValoresGrupo1);
            matrizValoresGrupo1 = null;
            //helper.ImprimirMatriz(covMatrix1, "Grupo 1");
            m = new Matrix(covMatrix1);
            //sigmaInversaGrupo1 = Inversa(covMatrix1);
            sigmaInversaGrupo1 = m.inverse().getArray();
            //determinanteGrupo1 = Determinante(covMatrix1);
            determinanteGrupo1 = m.det();
            covMatrix1 = null; // para liberar la mem usada

            covMatrix2 = MatrizCovarianza(matrizValoresGrupo2);
            matrizValoresGrupo2 = null;
            //helper.ImprimirMatriz(covMatrix2, "Grupo 2");
            m = new Matrix(covMatrix2);
            //sigmaInversaGrupo2 = Inversa(covMatrix2);
            sigmaInversaGrupo2 = m.inverse().getArray();
            //determinanteGrupo2 = Determinante(covMatrix2);
            determinanteGrupo2 = m.det();
            covMatrix2 = null; // para liberar la mem usada

            covMatrix3 = MatrizCovarianza(matrizValoresGrupo3);
            matrizValoresGrupo3 = null;
            //helper.ImprimirMatriz(covMatrix3, "Grupo 3");
            m = new Matrix(covMatrix3);
            //sigmaInversaGrupo3 = Inversa(covMatrix3);
            sigmaInversaGrupo3 = m.inverse().getArray();
            //determinanteGrupo3 = Determinante(covMatrix3);
            determinanteGrupo3 = m.det();
            covMatrix3 = null; // para liberar la mem usada

            covMatrix4 = MatrizCovarianza(matrizValoresGrupo4);
            matrizValoresGrupo4 = null;
            //helper.ImprimirMatriz(covMatrix4, "Grupo 4");
            m = new Matrix(covMatrix4);
            //sigmaInversaGrupo4 = Inversa(covMatrix4);
            sigmaInversaGrupo4 = m.inverse().getArray();
            //determinanteGrupo4 = Determinante(covMatrix4);
            determinanteGrupo4 = m.det();
            covMatrix4 = null; // para liberar la mem usada

            //helper.ImprimirValores(matrizValoresGrupo5, -1, "matrizValoresGrupo5");
            covMatrix5 = MatrizCovarianza(matrizValoresGrupo5);
            matrizValoresGrupo5 = null;
            //helper.ImprimirMatriz(covMatrix5, "Grupo 5");
            m = new Matrix(covMatrix5);
            //sigmaInversaGrupo0 = Inversa(covMatrix0);
            sigmaInversaGrupo5 = m.inverse().getArray();
            //determinanteGrupo5 = Determinante(covMatrix5);
            determinanteGrupo5 = m.det();
            covMatrix5 = null; // para liberar la mem usada

            covMatrix6 = MatrizCovarianza(matrizValoresGrupo6);
            matrizValoresGrupo6 = null;
            //helper.ImprimirMatriz(covMatrix6, "Grupo 6");
            m = new Matrix(covMatrix6);
            //sigmaInversaGrupo6 = Inversa(covMatrix6);
            sigmaInversaGrupo6 = m.inverse().getArray();
            //determinanteGrupo6 = Determinante(covMatrix6);
            determinanteGrupo6 = m.det();
            covMatrix6 = null; // para liberar la mem usada

            covMatrix7 = MatrizCovarianza(matrizValoresGrupo7);
            matrizValoresGrupo7 = null;
            //helper.ImprimirMatriz(covMatrix7, "Grupo 7");
            m = new Matrix(covMatrix7);
            //sigmaInversaGrupo7 = Inversa(covMatrix7);
            sigmaInversaGrupo7 = m.inverse().getArray();
            //determinanteGrupo7 = Determinante(covMatrix7);
            determinanteGrupo7 = m.det();
            covMatrix7 = null; // para liberar la mem usada

            covMatrix8 = MatrizCovarianza(matrizValoresGrupo8);
            matrizValoresGrupo8 = null;
            //helper.ImprimirMatriz(covMatrix8, "Grupo 8");
            m = new Matrix(covMatrix8);
            //sigmaInversaGrupo8 = Inversa(covMatrix8);
            sigmaInversaGrupo8 = m.inverse().getArray();
            //determinanteGrupo8 = Determinante(covMatrix8);
            determinanteGrupo8 = m.det();
            covMatrix8 = null; // para liberar la mem usada

            covMatrix9 = MatrizCovarianza(matrizValoresGrupo9);
            matrizValoresGrupo9 = null;
            //helper.ImprimirMatriz(covMatrix9, "Grupo 9");
            m = new Matrix(covMatrix9);
            //sigmaInversaGrupo9 = Inversa(covMatrix9);
            sigmaInversaGrupo9 = m.inverse().getArray();
            //determinanteGrupo9 = Determinante(covMatrix9);
            determinanteGrupo9 = m.det();
            covMatrix9 = null; // para liberar la mem usada

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public int[] PromedioOtrosTresCuadrantes(int[] filter) { //16x16
        int[] promedios = new int[nroCaracteristicas]; //8x8 -> 64
        int t = 0;
        /*
         __________
         |    |    |
         | A  | B  |
         -----------
         | C  | D  |
         |____|____|
         */

        //cuadrante B
        for (int fil = 0; fil < 16; fil++) {
            if ((fil >= 0) && (fil <= 7)) {
                for (int col = 0; col < 16; col++) {
                    if ((col >= 8) && (col <= 15)) {
                        //m[i][j] = imagen[k];
                        promedios[t] = filter[fil * 16 + col];
                        t++;
                    }
                }
            }
        }
        t = 0;
        //cuadrante C
        for (int fil = 0; fil < 16; fil++) {
            if ((fil >= 8) && (fil <= 15)) {
                for (int col = 0; col < 16; col++) {
                    if ((col >= 0) && (col <= 7)) {
                        //m[i][j] = imagen[k];
                        promedios[t] = (promedios[t] + filter[fil * 16 + col]) / 2;
                        t++;
                    }
                }
            }
        }
        t = 0;
        //cuadrante D
        for (int fil = 0; fil < 16; fil++) {
            if ((fil >= 8) && (fil <= 15)) {
                for (int col = 0; col < 16; col++) {
                    if ((col >= 8) && (col <= 15)) {
                        //m[i][j] = imagen[k];
                        promedios[t] = (promedios[t] * 2 + filter[fil * 16 + col]) / 3;
                        t++;
                    }
                }
            }
        }

        return promedios;
    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="FUNCIONES DISCRIMINANTES g(x)">
    public double FuncionDiscriminante_CalculaAlEjecutar(ArrayList<ArrayList<Double>> matrizValores,
            ArrayList<Double> arregloMedias, double[][] matrizCovarianzas,
            ArrayList<Double> arregloIncognita) {
        double g = -1;
        try {
            double[][] XmenosU = RestarMatrices(arregloIncognita, arregloMedias);
            double[][] sigmaInversa = Inversa(matrizCovarianzas);
            double[][] XmenosUtraspuesto = Traspuesta(XmenosU);
            double determinante = Determinante(matrizCovarianzas);
            double d = arregloMedias.size();
//            helper.ImprimirVector_ArrayList(arregloIncognita, "arregloIncognita");
//            helper.ImprimirVector_ArrayList(arregloMedias, "arregloMedias");
//            helper.ImprimirMatriz(matrizCovarianzas, "matrizCovarianzas");
//            helper.ImprimirMatriz(sigmaInversa, "sigmaInversa");
//            helper.ImprimirMatriz(XmenosU, "XmenosU");
//            helper.ImprimirMatriz(XmenosUtraspuesto, "XmenosUtraspuesto");
//            System.out.println("Determinante: " + determinante);
            double[][] producto = CalcularProducto(XmenosU, sigmaInversa, XmenosUtraspuesto);
            double prod = producto[0][0];
            g = (-0.5 * prod) - (d * 0.5 * Math.log(2 * Math.PI)) - (0.5 * Math.log(determinante));
            //System.out.println("g(x): " + g);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return g;
    }

    //Cada clase tendrá una función discriminante con los valores de
    //sigmasinversas y determinantes ya calculadas, lista para recibir valores
    public double FuncionDiscriminante_Precalculada(ArrayList<Double> arregloMedias,
            ArrayList<Double> arregloIncognita, double[][] sigmaInversa, double determinante) {
        double g = -1; //un valor cualquiera
        try {
            double[][] XmenosU = RestarMatrices(arregloIncognita, arregloMedias);

            //podriamos guardar las sigmasInversas en atributos de la clase
            //y asi hacemos que el proceso de testing sea mas rapido
            //double[][] sigmaInversa = Inversa(matrizCovarianzas); 

            double[][] XmenosUtraspuesto = Traspuesta(XmenosU);

            //podriamos guardar las determinantes en atributos de la clase
            //y asi hacemos que el proceso de testing sea mas rapido
            //double determinante = Determinante(matrizCovarianzas);

            double d = arregloMedias.size();
//            helper.ImprimirVector_ArrayList(arregloIncognita, "arregloIncognita");
//            helper.ImprimirVector_ArrayList(arregloMedias, "arregloMedias");
//            helper.ImprimirMatriz(matrizCovarianzas, "matrizCovarianzas");
//            helper.ImprimirMatriz(sigmaInversa, "sigmaInversa");
//            helper.ImprimirMatriz(XmenosU, "XmenosU");
//            helper.ImprimirMatriz(XmenosUtraspuesto, "XmenosUtraspuesto");
//            System.out.println("Determinante: " + determinante);

            double[][] producto = CalcularProducto(XmenosU, sigmaInversa, XmenosUtraspuesto);
            double prod = producto[0][0];
            g = (-0.5 * prod) - (d * 0.5 * Math.log(2 * Math.PI)) - (0.5 * Math.log(determinante));
            //System.out.println("g(x): " + g);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return g;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="FUNCIONES AUXILIARES DE MATRICES">
    public double[][] Cofactor(double[][] A) throws Exception {
        int m = A.length;
        int n = A[0].length;
        double[][] C = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int aux1 = 0;
                int aux2 = 0;
                //aux1=1;
                aux2 = 1;

                if (i % 2 == 0) {
                    // even
                    aux2 = 1;
                } else {
                    aux2 = -1;
                    // odd
                }

                if (j % 2 == 0) {
                    // even
                    aux1 = 1;
                } else {
                    aux1 = -1;
                    // odd
                }

                //le cambio el signo al i, j
                double[][] D = createSubMatrix(A, i, j);

                double F = Determinante(createSubMatrix(A, i, j));

                C[i][j] = aux1 * aux2 * Determinante(createSubMatrix(A, i, j));
            }
        }

        return C;
    }

    public double[][] createSubMatrix(double[][] C, int excluding_row, int excluding_col) {

        int m = C.length;
        int n = C[0].length;

        double[][] D = new double[m - 1][n - 1];

        int r = -1;

        for (int i = 0; i < m; i++) {
            if (i == excluding_row) { //revisar si las llaves abarcan solo continue o r++ y c=-1
                continue;
            }
            r++;
            int c = -1;
            for (int j = 0; j < n; j++) {
                if (j == excluding_col) {
                    continue;
                }
                D[r][++c] = C[i][j];
            }
        }
        return D;
    }

    public double[][] Traspuesta(double[][] C) {
        int m = C.length;
        int n = C[0].length;
        double[][] D = new double[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                D[j][i] = C[i][j];
                //D[i][j]=C[j][i];
            }
        }
        return D;
    }

    public double Determinante(double[][] C) throws Exception {
        int m = C.length;
        int n = C[0].length;

        if (m != n) {
            throw new Exception("matrix need to be square.");
        }

        if ((m == 1) && (n == 1)) {
            return C[0][0];
        }
        if ((m == 2) && (n == 2)) {
            return (C[0][0] * C[1][1]) - (C[0][1] * C[1][0]);
        }
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            int aux = 0;
            //le cambio el signo al i

            if (i % 2 == 0) {
                // even
                aux = 1;
            } else {
                aux = -1;
                // odd
            }

            sum += aux * C[0][i] * Determinante(createSubMatrix(C, 0, i));
        }
        return sum;
    }

    public double[][] multiplyByConstant(double[][] C, double A) {
        int m = C.length;
        int n = C[0].length;
        double[][] D = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                D[i][j] = C[i][j] * A;
            }
        }
        return D;
    }

    public double[][] Inversa(double[][] C) throws Exception {
        return multiplyByConstant(Traspuesta(Cofactor(C)), (1.0 / Determinante(C)));
    }

    private double[][] MatrizCovarianza(ArrayList<ArrayList<Integer>> matrizValores) {
        double[][] matrixAux = new double[matrizValores.size()][matrizValores.get(0).size()];
        RealMatrix realMatrix;

        for (int u = 0; u < matrizValores.size(); u++) {
            for (int v = 0; v < matrizValores.get(u).size(); v++) {
                matrixAux[u][v] = matrizValores.get(u).get(v);
            }
        }
        Covariance covariance = new Covariance(matrixAux, false);
        realMatrix = covariance.getCovarianceMatrix();
        return realMatrix.getData();
    }

    public double[][] RestarMatrices(ArrayList<Double> A, ArrayList<Double> B) {
        int m = A.size();
        double[][] resta = new double[1][m]; //[][][]

        for (int i = 0; i < m; i++) {
            resta[0][i] = A.get(i) - B.get(i);
        }
        return resta;
    }

    public double[][] subtract(double[][] A, double[][] B) {
        int m = A.length;
        int n = A[0].length;
        double[][] C = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    public double[][] multiply(double[][] A, double[][] B) {
        int mA = A.length;
        int nA = A[0].length;
        int mB = B.length;
        int nB = B[0].length;
        if (nA != mB) {
            throw new RuntimeException("Illegal matrix dimensions.");
        }
        double[][] C = new double[mA][nB];
        for (int i = 0; i < mA; i++) {
            for (int j = 0; j < nB; j++) {
                for (int k = 0; k < nA; k++) {
                    C[i][j] += (A[i][k] * B[k][j]);
                }
            }
        }
        return C;
    }

    public double[][] CalcularProducto(double[][] A, double[][] B, double[][] C) {
        int mA = A.length;
        int nA = A[0].length;
        int mB = B.length;
        int nB = B[0].length;
        if (nA != mB) {
            throw new RuntimeException("Illegal matrix dimensions.");
        }
        double[][] D = new double[mA][nB];
        for (int i = 0; i < mA; i++) {
            for (int j = 0; j < nB; j++) {
                for (int k = 0; k < nA; k++) {
                    D[i][j] += (A[i][k] * B[k][j]);
                }
            }
        }

        //helper.ImprimirMatriz(D, "D");
        A = D;
        B = C;
        mA = A.length;
        nA = A[0].length;
        mB = B.length;
        nB = B[0].length;
        if (nA != mB) {
            throw new RuntimeException("Illegal matrix dimensions.");
        }
        D = new double[mA][nB];
        for (int i = 0; i < mA; i++) {
            for (int j = 0; j < nB; j++) {
                for (int k = 0; k < nA; k++) {
                    D[i][j] += (A[i][k] * B[k][j]);
                }
            }
        }
        //helper.ImprimirMatriz(D, "D");
        return D;
    }

    public double[] multiply2(double[][] A, double[] x) {
        int m = A.length;
        int n = A[0].length;
        if (x.length != n) {
            throw new RuntimeException("Illegal matrix dimensions.");
        }
        double[] y = new double[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                y[i] += (A[i][j] * x[j]);
            }
        }
        return y;
    }

    private ArrayList<ArrayList<Double>> ObtenerMatrizValoresRandom(ArrayList<ArrayList<Double>> matriz) {
        ArrayList<ArrayList<Double>> matrizCombinada = new ArrayList<>(50);
        ArrayList<Integer> auxiliar = new ArrayList<>(50);
        int DESDE = 0;
        int HASTA = 49;

        //int finicial=(int)(Math.random()*(HASTA-DESDE+1)+DESDE); 
        //int ffinal=(int)(Math.random()*(HASTA-DESDE+1)+DESDE); 

        do {
            int finicial = (int) (Math.random() * (HASTA - DESDE + 1) + DESDE);

            if (!auxiliar.contains(finicial)) {
                auxiliar.add(finicial);
                matrizCombinada.add(matriz.get(finicial));//=matriz.get(finicial);
            }

        } while (auxiliar.size() < 50);

        return matrizCombinada;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="PCA - REDUCCIÓN DE DIMENSIONALIDAD">
    public void GenerarMatrizCorrelacionyAutovalores() {
        //GenerarMatrizCorrelacionyAutovalores_Grupo0();

        PearsonsCorrelation p = new PearsonsCorrelation();
        double[][] todosValores = CombinarMatricesValores();
        matrizValoresGrupo0 = matrizValoresGrupo1 = matrizValoresGrupo2 =
                matrizValoresGrupo3 = matrizValoresGrupo4 = matrizValoresGrupo5 =
                matrizValoresGrupo6 = matrizValoresGrupo7 = matrizValoresGrupo8 = matrizValoresGrupo9 = null;

        //helper.ImprimirMatriz(todosValores, "todosValores");
        RealMatrix RM = p.computeCorrelationMatrix(todosValores);
        double[][] R = RM.getData();
        //helper.ImprimirMatriz(R, "R");
        Matrix A = new Matrix(R);
        R = null;
        EigenvalueDecomposition E = A.eig();
        A = null;
        //Matrix V = E.getV();
        Matrix D = E.getD();
        Autovalores_Mayores(D.getArray());
        helper.ImprimirMatriz(D.getArray(), "AUTOVALORES");
    }

    public void GenerarMatrizCorrelacionyAutovalores_Grupo0() {
        SpearmansCorrelation s = new SpearmansCorrelation();
        PearsonsCorrelation p = new PearsonsCorrelation();
        int nroFilas = matrizValoresGrupo0.size();
        double[][] matrizValores = new double[nroFilas][nroCaracteristicas]; //cada matriz es de 20x14 -> 280
        for (int i = 0; i < nroFilas; i++) {
            for (int j = 0; j < matrizValoresGrupo0.get(i).size(); j++) {
                int x = matrizValoresGrupo0.get(i).get(j);
                double d = (double) matrizValoresGrupo0.get(i).get(j);
                if (x == 0) {
                    d = (double) (Math.random());//*(HASTA-DESDE+1)+DESDE);
                }
                matrizValores[i][j] = d;
            }
        }
        matrizValoresGrupo0 = null;
        //helper.ImprimirMatriz(matrizValores, "matrizValores"); 
        RealMatrix RM = p.computeCorrelationMatrix(matrizValores);
        double[][] R = RM.getData();
        //helper.ImprimirMatriz(R, "R");
        Matrix A = new Matrix(R);
        R = null;
        EigenvalueDecomposition E = A.eig();
        A = null;
        Matrix D = E.getD();
        helper.ImprimirMatriz(D.getArray(), "AUTOVALORES GRUPO 0");
    }

    public double[][] CombinarMatricesValores() {
        int t = 0;
        int n = 0;//5000;
        double[][] Matriz2 = new double[60000 - n * 10][nroCaracteristicas]; //cada matriz es de 20x14
        for (int i = 0; i < matrizValoresGrupo0.size() - n; i++) {
            for (int j = 0; j < matrizValoresGrupo0.get(i).size(); j++) {
                int x = matrizValoresGrupo0.get(i).get(j);
                double d = (double) matrizValoresGrupo0.get(i).get(j);
                if (x == 0) {
                    d = (double) (Math.random());
                }
                Matriz2[t][j] = d;
            }
            t++;
        }

        for (int i = 0; i < matrizValoresGrupo1.size() - n; i++) {
            for (int j = 0; j < matrizValoresGrupo1.get(i).size(); j++) {
                int x = matrizValoresGrupo1.get(i).get(j);
                double d = (double) matrizValoresGrupo1.get(i).get(j);
                if (x == 0) {
                    d = (double) (Math.random());
                }
                Matriz2[t][j] = d;
            }
            t++;
        }
        for (int i = 0; i < matrizValoresGrupo2.size() - n; i++) {
            for (int j = 0; j < matrizValoresGrupo2.get(i).size(); j++) {
                int x = matrizValoresGrupo2.get(i).get(j);
                double d = (double) matrizValoresGrupo2.get(i).get(j);
                if (x == 0) {
                    d = (double) (Math.random());
                }
                Matriz2[t][j] = d;
            }
            t++;
        }
        for (int i = 0; i < matrizValoresGrupo3.size() - n; i++) {
            for (int j = 0; j < matrizValoresGrupo3.get(i).size(); j++) {
                int x = matrizValoresGrupo3.get(i).get(j);
                double d = (double) matrizValoresGrupo3.get(i).get(j);
                if (x == 0) {
                    d = (double) (Math.random());
                }
                Matriz2[t][j] = d;
            }
            t++;
        }
        for (int i = 0; i < matrizValoresGrupo4.size() - n; i++) {
            for (int j = 0; j < matrizValoresGrupo4.get(i).size(); j++) {
                int x = matrizValoresGrupo4.get(i).get(j);
                double d = (double) matrizValoresGrupo4.get(i).get(j);
                if (x == 0) {
                    d = (double) (Math.random());
                }
                Matriz2[t][j] = d;
            }
            t++;
        }
        for (int i = 0; i < matrizValoresGrupo5.size() - n; i++) {
            for (int j = 0; j < matrizValoresGrupo5.get(i).size(); j++) {
                int x = matrizValoresGrupo5.get(i).get(j);
                double d = (double) matrizValoresGrupo5.get(i).get(j);
                if (x == 0) {
                    d = (double) (Math.random());
                }
                Matriz2[t][j] = d;
            }
            t++;
        }
        for (int i = 0; i < matrizValoresGrupo6.size() - n; i++) {
            for (int j = 0; j < matrizValoresGrupo6.get(i).size(); j++) {
                int x = matrizValoresGrupo6.get(i).get(j);
                double d = (double) matrizValoresGrupo6.get(i).get(j);
                if (x == 0) {
                    d = (double) (Math.random());
                }
                Matriz2[t][j] = d;
            }
            t++;
        }
        for (int i = 0; i < matrizValoresGrupo7.size() - n; i++) {
            for (int j = 0; j < matrizValoresGrupo7.get(i).size(); j++) {
                int x = matrizValoresGrupo7.get(i).get(j);
                double d = (double) matrizValoresGrupo7.get(i).get(j);
                if (x == 0) {
                    d = (double) (Math.random());
                }
                Matriz2[t][j] = d;
            }
            t++;
        }
        for (int i = 0; i < matrizValoresGrupo8.size() - n; i++) {
            for (int j = 0; j < matrizValoresGrupo8.get(i).size(); j++) {
                int x = matrizValoresGrupo8.get(i).get(j);
                double d = (double) matrizValoresGrupo8.get(i).get(j);
                if (x == 0) {
                    d = (double) (Math.random());
                }
                Matriz2[t][j] = d;
            }
            t++;
        }
        for (int i = 0; i < matrizValoresGrupo9.size() - n; i++) {
            for (int j = 0; j < matrizValoresGrupo9.get(i).size(); j++) {
                int x = matrizValoresGrupo9.get(i).get(j);
                double d = (double) matrizValoresGrupo9.get(i).get(j);
                if (x == 0) {
                    d = (double) (Math.random());
                }
                Matriz2[t][j] = d;
            }
            t++;
        }

        return Matriz2;
    }

    public void Autovalores_Mayores(double[][] matriz) {
        List<AutoValor> listaAutoValores = new LinkedList<AutoValor>();
        SortedSet<Map.Entry<String, Double>> sortedset = new TreeSet<Map.Entry<String, Double>>(
                new Comparator<Map.Entry<String, Double>>() {
                    @Override
                    public int compare(Map.Entry<String, Double> e1,
                            Map.Entry<String, Double> e2) {
                        return e2.getValue().compareTo(e1.getValue());
                    }
                });
        SortedMap<String, Double> myMap = new TreeMap<String, Double>();

        NumberFormat nf = NumberFormat.getInstance();
        NumberFormat nf2 = NumberFormat.getNumberInstance(Locale.GERMAN);
        nf2.setMaximumFractionDigits(4);
        DecimalFormat df = (DecimalFormat) nf2;

        int t = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int k = 0; k < matriz[0].length; k++) {
                if (i == k) {
                    AutoValor autoValor = new AutoValor(matriz[i][k], i);
                    listaAutoValores.add(autoValor);
                    //myMap.put("" + i, matriz[i][k]);
                    t++;
                    //System.out.print(nf.format(matriz[i][k]) + "\t");
                }
            }
        }

//        sortedset.addAll(myMap.entrySet());
//        System.out.println(sortedset);

        Collections.sort(listaAutoValores);
        for (AutoValor autovalor : listaAutoValores) {
            System.out.print("[" + autovalor.getIndice() + "]= " + df.format(autovalor.getValor()) + " - ");
        }

        System.out.println();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="IRIS FLOWERS DATA SET">
    /*public void ProcesarIrisFlowersDataSet() {
     //Son 3 especies de flores
     ArrayList<Double> mediasEspecie1 = new ArrayList<>(4);
     ArrayList<Double> mediasEspecie2 = new ArrayList<>(4);
     ArrayList<Double> mediasEspecie3 = new ArrayList<>(4);
     ArrayList<Double> listaCaracteristicas = new ArrayList<>(4);
     ArrayList<Double> valoresGrupoSetosa;
     ArrayList<Double> valoresGrupoVersicolor;
     ArrayList<Double> valoresGrupoVirginica;
     matrizValoresGrupoSetosa = new ArrayList<>(50);
     matrizValoresGrupoVersicolor = new ArrayList<>(50);
     matrizValoresGrupoVirginica = new ArrayList<>(50);
     double[][] matrizCovGrupoSetosa;
     double[][] matrizCovGrupoVersicolor;
     double[][] matrizCovGrupoVirginica;

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
     valoresGrupoSetosa = new ArrayList<>(4);
     // PARA N-CARACTERÍSTICAS
     for (int i = 0; i < nroCaracteristicas; i++) {
     int valorCar= listaCaracteristicas.get(i); //obtener el valor de la i-ésima característica
     mediasEspecie1.set(i, (mediasEspecie1.get(i) * (cantidadFloresXTipo.get(0) - 1) + valorCar) / cantidadFloresXTipo.get(0));
     valoresGrupoSetosa.add((double) valorCar);
     }
     matrizValoresGrupoSetosa.add(valoresGrupoSetosa);
     break;
     case "I. versicolor":
     cantidadFloresXTipo.set(1, cantidadFloresXTipo.get(1) + 1);
     valoresGrupoVersicolor = new ArrayList<>(4);
     // PARA N-CARACTERÍSTICAS
     for (int i = 0; i < nroCaracteristicas; i++) {
     int valorCar= listaCaracteristicas.get(i); //obtener el valor de la i-ésima característica
     mediasEspecie2.set(i, (mediasEspecie2.get(i) * (cantidadFloresXTipo.get(1) - 1) + valorCar) / cantidadFloresXTipo.get(1));
     valoresGrupoVersicolor.add((double) valorCar);
     }
     matrizValoresGrupoVersicolor.add(valoresGrupoVersicolor);
     break;
     case "I. virginica":
     cantidadFloresXTipo.set(2, cantidadFloresXTipo.get(2) + 1);
     valoresGrupoVirginica = new ArrayList<>(4);
     // PARA N-CARACTERÍSTICAS
     for (int i = 0; i < nroCaracteristicas; i++) {
     int valorCar= listaCaracteristicas.get(i); //obtener el valor de la i-ésima característica
     mediasEspecie3.set(i, (mediasEspecie3.get(i) * (cantidadFloresXTipo.get(2) - 1) + valorCar) / cantidadFloresXTipo.get(2));
     valoresGrupoVirginica.add((double) valorCar);
     }
     matrizValoresGrupoVirginica.add(valoresGrupoVirginica);
     break;
     default:
     break;
     }
     }
     //            System.out.print("Medias Especie 1");
     //            for (Double media : mediasEspecie1) {
     //                System.out.print(media + " ");
     //            }
     //            System.out.println();
     //            System.out.print("Medias Especie 2");
     //            for (Double media : mediasEspecie2) {
     //                System.out.print(media + " ");
     //            }
     //            System.out.println();
     //            System.out.print("Medias Especie 3");
     //            for (Double media : mediasEspecie3) {
     //                System.out.print(media + " ");
     //            }
     //            System.out.println();

     matrizCovGrupoSetosa = MatrizCovarianza(matrizValoresGrupoSetosa);
     matrizCovGrupoVersicolor = MatrizCovarianza(matrizValoresGrupoVersicolor);
     matrizCovGrupoVirginica = MatrizCovarianza(matrizValoresGrupoVirginica);

     double g3 = FuncionDiscriminante(matrizValoresGrupoVirginica, mediasEspecie3, matrizCovGrupoVirginica, listaCaracteristicas);

     } catch (IOException | NumberFormatException e) {
     System.out.println(e.toString());
     }
     }

     public void Entrenamiento_IrisDataSet() {
     ProcesarIrisFlowersDataSet();

     //Son 3 especies de flores
     ArrayList<Double> mediasEspecie1 = new ArrayList<>(4);
     ArrayList<Double> mediasEspecie2 = new ArrayList<>(4);
     ArrayList<Double> mediasEspecie3 = new ArrayList<>(4);
     ArrayList<Double> listaCaracteristicas = new ArrayList<>(4);
     ArrayList<ArrayList<Double>> valoresGrupoSetosa = new ArrayList<>(45);
     ArrayList<ArrayList<Double>> valoresGrupoVersicolor = new ArrayList<>(45);
     ArrayList<ArrayList<Double>> valoresGrupoVirginica = new ArrayList<>(45);
     ArrayList<ArrayList<Double>> matrizValoresRandomSetosa;
     ArrayList<ArrayList<Double>> matrizValoresRandomVersicolor;
     ArrayList<ArrayList<Double>> matrizValoresRandomVirginica;
     double[][] matrizCovGrupoSetosa;
     double[][] matrizCovGrupoVersicolor;
     double[][] matrizCovGrupoVirginica;
     int kfolder = 0;
     int kfolderMax = 10;
     for (int i = 0; i < 4; i++) {
     mediasEspecie1.add(0.0);
     mediasEspecie2.add(0.0);
     mediasEspecie3.add(0.0);
     }
     try {
     while (kfolder < kfolderMax) {
     System.out.println("******************************* K-FOLDER NRO: " + kfolder + "*****************************");
     matrizValoresRandomSetosa = ObtenerMatrizValoresRandom(matrizValoresGrupoSetosa);
     matrizValoresRandomVersicolor = ObtenerMatrizValoresRandom(matrizValoresGrupoVersicolor);
     matrizValoresRandomVirginica = ObtenerMatrizValoresRandom(matrizValoresGrupoVirginica);

     //PARA GRUPO SETOSA
     //Llenamos el modelo 
     for (int i = 0; i < matrizValoresRandomSetosa.size() - 5; i++) {
     //Los primeros 45 son los de entrenamiento
     ArrayList<Double> valoresIteracion = new ArrayList<>(4);
     for (int j = 0; j < matrizValoresRandomSetosa.get(i).size(); j++) {
     double valor = matrizValoresRandomSetosa.get(i).get(j);
     valoresIteracion.add(valor);
     mediasEspecie1.set(j, (mediasEspecie1.get(j) * i + valor) / (i + 1));
     }
     valoresGrupoSetosa.add(valoresIteracion);
     }
     matrizCovGrupoSetosa = MatrizCovarianza(valoresGrupoSetosa);
     //                helper.ImprimirMatriz_ArrayList(valoresGrupoSetosa, "valoresGrupoSetosa");
     //                //helper.ImprimirVector_ArrayList(mediasEspecie1, "mediasEspecie1");
     //                helper.ImprimirMatriz(matrizCovGrupoSetosa, "matrizCovGrupoSetosa");


     //PARA GRUPO Versicolor
     //Llenamos el modelo 
     for (int i = 0; i < matrizValoresRandomVersicolor.size() - 5; i++) {
     //Los primeros 45 son los de entrenamiento
     ArrayList<Double> valoresIteracion = new ArrayList<>(4);
     for (int j = 0; j < matrizValoresRandomVersicolor.get(i).size(); j++) {
     double valor = matrizValoresRandomVersicolor.get(i).get(j);
     valoresIteracion.add(valor);
     mediasEspecie2.set(j, (mediasEspecie2.get(j) * i + valor) / (i + 1));
     }
     valoresGrupoVersicolor.add(valoresIteracion);
     }
     matrizCovGrupoVersicolor = MatrizCovarianza(valoresGrupoVersicolor);

     //PARA GRUPO Virginica
     //Llenamos el modelo 
     for (int i = 0; i < matrizValoresRandomVirginica.size() - 5; i++) {
     //Los primeros 45 son los de entrenamiento
     ArrayList<Double> valoresIteracion = new ArrayList<>(4);
     for (int j = 0; j < matrizValoresRandomVirginica.get(i).size(); j++) {
     double valor = matrizValoresRandomVirginica.get(i).get(j);
     valoresIteracion.add(valor);
     mediasEspecie3.set(j, (mediasEspecie3.get(j) * i + valor) / (i + 1));
     }
     valoresGrupoVirginica.add(valoresIteracion);
     }
     matrizCovGrupoVirginica = MatrizCovarianza(valoresGrupoVirginica);


     //Ahora llamamos a la funcion discriminante para las pruebas
     //Grupo Setosa
     for (int i = 45; i < 50; i++) {
     ArrayList<Double> arregloIncognita = matrizValoresRandomSetosa.get(i);
     double g1 = FuncionDiscriminante(valoresGrupoSetosa, mediasEspecie1,
     matrizCovGrupoSetosa, arregloIncognita);
     double g2 = FuncionDiscriminante(valoresGrupoVersicolor, mediasEspecie2,
     matrizCovGrupoVersicolor, arregloIncognita);
     double g3 = FuncionDiscriminante(valoresGrupoVirginica, mediasEspecie3,
     matrizCovGrupoVirginica, arregloIncognita);
     System.out.println("Setosa g1: " + g1 + "\t\t" + "g2: " + g2 + "\t\t" + "g3: " + g3);
     }
     System.out.println();
     //Grupo Versicolor
     for (int i = 45; i < 50; i++) {
     ArrayList<Double> arregloIncognita = matrizValoresRandomVersicolor.get(i);
     double g1 = FuncionDiscriminante(valoresGrupoSetosa, mediasEspecie1,
     matrizCovGrupoSetosa, arregloIncognita);
     double g2 = FuncionDiscriminante(valoresGrupoVersicolor, mediasEspecie2,
     matrizCovGrupoVersicolor, arregloIncognita);
     double g3 = FuncionDiscriminante(valoresGrupoVirginica, mediasEspecie3,
     matrizCovGrupoVirginica, arregloIncognita);
     System.out.println("Versicolor g1: " + g1 + "\t\t" + "g2: " + g2 + "\t\t" + "g3: " + g3);
     }
     System.out.println();
     //Grupo Virginica
     for (int i = 45; i < 50; i++) {
     ArrayList<Double> arregloIncognita = matrizValoresRandomVirginica.get(i);
     double g1 = FuncionDiscriminante(valoresGrupoSetosa, mediasEspecie1,
     matrizCovGrupoSetosa, arregloIncognita);
     double g2 = FuncionDiscriminante(valoresGrupoVersicolor, mediasEspecie2,
     matrizCovGrupoVersicolor, arregloIncognita);
     double g3 = FuncionDiscriminante(valoresGrupoVirginica, mediasEspecie3,
     matrizCovGrupoVirginica, arregloIncognita);
     System.out.println("Virginica g1: " + g1 + "\t\t" + "g2: " + g2 + "\t\t" + "g3: " + g3);
     }

     kfolder++;
     }

     } catch (Exception e) {
     System.out.println(e.toString());
     }
     }
     public void Random(ArrayList<ArrayList<Double>> matriz) {

     ArrayList<ArrayList<Double>> matrizCombinada = new ArrayList<>(50);

     ArrayList<Integer> auxiliar = new ArrayList<>(50);

     int DESDE = 1;
     int HASTA = 50;

     //int finicial=(int)(Math.random()*(HASTA-DESDE+1)+DESDE); 
     //int ffinal=(int)(Math.random()*(HASTA-DESDE+1)+DESDE); 

     do {
     int finicial = (int) (Math.random() * (HASTA - DESDE + 1) + DESDE);

     if (!auxiliar.contains(finicial)) {
     auxiliar.add(finicial);
     matrizCombinada.add(auxiliar.size(), matriz.get(finicial));//=matriz.get(finicial);
     }

     } while (auxiliar.size() < 50);

     }
     * 
     */
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="CARACTERISTICAS PROPUESTAS PARA LOS DIGITOS">
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="TESTEAR MODELO">
    public void Testear_Modelo(ArrayList<byte[]> imagenesArray, int nroimagenes, ArrayList<Integer> labelsArray) {

        double g_grupo0 = 0.00;
        double g_grupo1 = 0.00;
        double g_grupo2 = 0.00;
        double g_grupo3 = 0.00;
        double g_grupo4 = 0.00;
        double g_grupo5 = 0.00;
        double g_grupo6 = 0.00;
        double g_grupo7 = 0.00;
        double g_grupo8 = 0.00;
        double g_grupo9 = 0.00;

        ArrayList<String> mensaje = new ArrayList<>(50);

        for (int i = 0; i <= nroimagenes; i++) {
            byte[] imagen = imagenesArray.get(i);
            int t = 0;
            int num = labelsArray.get(i);

            for (int u = 0; u < 28; u++) {
                if ((u > 3) && (u < 24)) { //3,4: ignorar 4 filas arriba y abajo. Total 20 filas
                    for (int v = 0; v < 28; v++) {
                        if ((v > 6) && (v < 21)) { //6,21: ignorar 7 columnas izq y der. Total 14 columnas
                            int p = helper.UnsignedToBytes(imagen[t]);
                            listaCaracteristicas2.set(t, (double) p);
                            t++;
                        }
                    }
                }
            }

            g_grupo0 = FuncionDiscriminante_Precalculada(mediasGrupoNumero0, listaCaracteristicas2, sigmaInversaGrupo0, determinanteGrupo0);
            g_grupo1 = FuncionDiscriminante_Precalculada(mediasGrupoNumero1, listaCaracteristicas2, sigmaInversaGrupo1, determinanteGrupo1);
            g_grupo2 = FuncionDiscriminante_Precalculada(mediasGrupoNumero2, listaCaracteristicas2, sigmaInversaGrupo2, determinanteGrupo2);
            g_grupo3 = FuncionDiscriminante_Precalculada(mediasGrupoNumero3, listaCaracteristicas2, sigmaInversaGrupo3, determinanteGrupo3);
            g_grupo4 = FuncionDiscriminante_Precalculada(mediasGrupoNumero4, listaCaracteristicas2, sigmaInversaGrupo4, determinanteGrupo4);
            g_grupo5 = FuncionDiscriminante_Precalculada(mediasGrupoNumero5, listaCaracteristicas2, sigmaInversaGrupo5, determinanteGrupo5);
            g_grupo6 = FuncionDiscriminante_Precalculada(mediasGrupoNumero6, listaCaracteristicas2, sigmaInversaGrupo6, determinanteGrupo6);
            g_grupo7 = FuncionDiscriminante_Precalculada(mediasGrupoNumero7, listaCaracteristicas2, sigmaInversaGrupo7, determinanteGrupo7);
            g_grupo8 = FuncionDiscriminante_Precalculada(mediasGrupoNumero8, listaCaracteristicas2, sigmaInversaGrupo8, determinanteGrupo8);
            g_grupo9 = FuncionDiscriminante_Precalculada(mediasGrupoNumero9, listaCaracteristicas2, sigmaInversaGrupo9, determinanteGrupo9);

            System.out.println(g_grupo0 + "\n");
            System.out.println(g_grupo1 + "\n");
            System.out.println(g_grupo2 + "\n");
            System.out.println(g_grupo3 + "\n");
            System.out.println(g_grupo4 + "\n");
            System.out.println(g_grupo5 + "\n");
            System.out.println(g_grupo6 + "\n");
            System.out.println(g_grupo7 + "\n");
            System.out.println(g_grupo8 + "\n");
            System.out.println(g_grupo9 + "\n");

            ArrayList<Double> listaux;
            listaux = new ArrayList<>(10);

            listaux.add(g_grupo0);
            listaux.add(g_grupo1);
            listaux.add(g_grupo2);
            listaux.add(g_grupo3);
            listaux.add(g_grupo4);
            listaux.add(g_grupo5);
            listaux.add(g_grupo6);
            listaux.add(g_grupo7);
            listaux.add(g_grupo8);
            listaux.add(g_grupo9);

            double valaux = 0.00;
            int flag = 0;

            for (int z = 0; z < 10; z++) {
                if (valaux < listaux.get(z)) {
                    valaux = listaux.get(z);
                    flag = z;
                }
            }

            if (num == flag) {
                mensaje.add("Correcto");
            } else {
                mensaje.add("Incorrecto");
            }

            System.out.println("Numero" + " " + num + " grupo segun formula: " + flag + "  " + "Estado:" + mensaje + "\n");
            mensaje.clear();
        }
    }
    // </editor-fold>    
}
