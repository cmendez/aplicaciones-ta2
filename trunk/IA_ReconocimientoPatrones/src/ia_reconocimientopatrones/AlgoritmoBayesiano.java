package ia_reconocimientopatrones;
//import org.apache.commons.math3.stat.correlation.Covariance;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.Covariance;

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
    private Helpers helper;
    ArrayList<ArrayList<Double>> matrizValoresGrupoSetosa;
    ArrayList<ArrayList<Double>> matrizValoresGrupoVersicolor;
    ArrayList<ArrayList<Double>> matrizValoresGrupoVirginica;
    // </editor-fold>

    public AlgoritmoBayesiano() {
        helper = new Helpers();
        nroCaracteristicas = 400; //3 características: nros 1's, simetría horizontal, y simetría vertical
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
            listaCaracteristicas.add(0);
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
        matrizValoresGrupo0 = new ArrayList<>(5923);

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
        

        
//        for (int i = 0; i < 6000; i++) {
//            matrizValoresGrupo0.get(i);
//        }
    }

    public void ExtraerMatricesCovarianzas() {
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

    public void ProcesarDatos(int nroImagenes, ArrayList<byte[]> imagenesArray, ArrayList<Integer> labelsArray) {
        //try {
            ArrayList<String> output = new ArrayList<>(50);

            for (int M = 0; M < 3; M++) {
                int INICIO = nroImagenes / 3 * M;
                int FIN = INICIO + ((nroImagenes / 3) - 1);
                for (int i = INICIO; i <= FIN; i++) { //0..19999 - 20k..39999 - 40k..59999
                    byte[] imagen = imagenesArray.get(i);
                    //int[][] pixeles = new int[20][20];
                    int t = 0;
                    int num = labelsArray.get(i);

                    for (int u = 0; u < 28; u++) {
                        if ((u > 3) && (u < 24)) {
                            for (int v = 0; v < 28; v++) {
                                if ((v > 3) && (v < 24)) {
                                    int p = helper.UnsignedToBytes(imagen[t]);
//                        if (p != 0) {
//                            p = 1;
//                        }
                                    //pixeles[u - 4][v - 4] = p;
                                    listaCaracteristicas.set(t, p);
                                    t++;
                                }
                            }
                        }
                    }
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

                    // <editor-fold defaultstate="collapsed" desc="ALMACENAMIENTO DE LAS MEDIAS">
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
                                int valorCar= listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
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
                                int valorCar= listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
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
                                int valorCar= listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
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
                                int valorCar= listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
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
                                int valorCar= listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
                                mediasGrupoNumero5.set(k, (mediasGrupoNumero5.get(k) * (cantidadNumeros.get(num) - 1) + valorCar) / cantidadNumeros.get(num));
                                //valoresGrupoNumero5.add((double) valorCar);
                                matrizValoresGrupo5.get(n).set(k, valorCar);
                            }
                            //matrizValoresGrupo5.add(valoresGrupoNumero5);
                            //valoresGrupoNumero5.clear();
                            break;
                        case 6:
                            n = cantidadNumeros.get(num);
                            cantidadNumeros.set(num, n + 1);
                            //valoresGrupoNumero6 = new ArrayList<>(nroCaracteristicas);
                            // PARA N-CARACTERÍSTICAS
                            for (int k = 0; k < nroCaracteristicas; k++) {
                                int valorCar= listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
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
                                int valorCar= listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
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
                                int valorCar= listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
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
                                int valorCar= listaCaracteristicas.get(k); //obtener el valor de la k-ésima característica
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
            System.out.println("Label\t1's\tSimV\tSimH");
            ExtraerMatricesCovarianzas();
            
//            Collections.sort(output);
//            System.out.println("Label\t1's\tSimV\tSimH");
//            for (String s : output) {
//                System.out.println(s);
//            }
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
    }

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
    }*/
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="CARACTERISTICAS">
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

    // <editor-fold defaultstate="collapsed" desc="FUNCION DISCRIMINANTE g(x)">
    public double FuncionDiscriminante(ArrayList<ArrayList<Double>> matrizValores,
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

    public void Formula() throws Exception {

        ArrayList<Double> auxgruponumero0 = mediasGrupoNumero0;
        ArrayList<Double> auxgruponumero1 = mediasGrupoNumero1;
        //tengo el arreglo con las medias del grupo 0
        double parteEntera = 0.0;
        double phi = 3.1415;
        int d = 3;

        double[][] matrixAuxnum0 = new double[auxgruponumero0.size()][auxgruponumero0.size()];
        double[][] matrixAuxnum1 = new double[auxgruponumero1.size()][auxgruponumero1.size()];


        double[][] matrixDataInput = new double[auxgruponumero0.size()][auxgruponumero0.size()];

        //en matrixAuxInput tengo las caracteristicas que saco de la imagen que ingresa
        //esta se usara en la formula para cada subgrupo.

        /*
         matrixDataInput[0][0]=100;
         matrixDataInput[1][0]=100;
         matrixDataInput[2][0]=100;*/

        //medias de grupo 0, 188.0, 10.5, 25.0
        matrixDataInput[0][0] = 180.0;
        matrixDataInput[1][0] = 9.5;
        matrixDataInput[2][0] = 24;


        //medias del grupo 1 , 89.85, 2.71,19.0
        for (int u = 0; u < auxgruponumero0.size(); u++) {
            for (int v = 0; v < auxgruponumero0.size(); v++) {
                if (v != 0) {
                    matrixDataInput[u][v] = 0;
                }
            }
        }

        //primera exponencial en parte entera
        double partEnt1 = Math.pow(2 * phi, d / 2);

        //segunda exponencial en parte entera
        double partEnt2 = Math.pow(9, 1 / 2);

        //se mantiene para todas las ecuaciones, es la misma
        parteEntera = (1 / (partEnt1 * partEnt2));

        //Para el numero 0, el grupo 0        

        //paso los datos del arreglo en una matrix
        //en matrixaux tengo una matrix con los valores de la media del grupo 0
        for (int u = 0; u < auxgruponumero0.size(); u++) {
            for (int v = 0; v < auxgruponumero0.size(); v++) {
                if (v == 0) {
                    matrixAuxnum0[u][v] = auxgruponumero0.get(u);
                } else {
                    matrixAuxnum0[u][v] = 0;
                }
            }
        }

        double[][] matrixRestaNum0 = subtract(matrixDataInput, matrixAuxnum0);
        double[][] matrixTranspuestaNum0 = Traspuesta(matrixRestaNum0);
        double[] arregloMultMatrixNum0 = new double[auxgruponumero0.size()];

        //los valores de la matrixtranspuesta los paso a un array, para poder hacer la multiplicacion de matrices
        for (int v = 0; v < auxgruponumero0.size(); v++) {
            arregloMultMatrixNum0[v] = matrixTranspuestaNum0[0][v];
        }

        //asigno la matrix de covarianza
        double[][] matrixCovarianzaNum0 = covMatrix0;
        //le hago la inversa a la matriz de covarianza
        double[][] matrixCovarianzaInvertidaNum0 = Inversa(matrixCovarianzaNum0);


        double[] arregloNum0 = multiply2(matrixCovarianzaInvertidaNum0, arregloMultMatrixNum0);
        double[][] primMultVectNum0 = new double[auxgruponumero0.size()][auxgruponumero0.size()];

        //convierto el arreglo en una matriz para multiplicar matrizes
        for (int u = 0; u < auxgruponumero0.size(); u++) {
            for (int v = 0; v < auxgruponumero0.size(); v++) {
                if (u == 0) {
                    primMultVectNum0[u][v] = arregloNum0[u];
                } else {
                    primMultVectNum0[u][v] = 0;
                }
            }
        }

        double[][] segMultVectNum0 = multiply(primMultVectNum0, matrixRestaNum0);//   matrixResta
        //al multiplicar las matrizes la multiplicacion se coloca en segMultVect[0][0]        

        double partexponencial0 = Math.exp((-0.5) * segMultVectNum0[0][0]);

        double ValorFormulaNum0 = parteEntera * (Math.exp((-0.5) * segMultVectNum0[0][0]));

        System.out.println(segMultVectNum0[0][0] + "\n");

        System.out.println(partexponencial0 + "\n");

        System.out.println(ValorFormulaNum0 + "\n");

        //Para el numero 1, grupo 1

        for (int u = 0; u < auxgruponumero1.size(); u++) {
            for (int v = 0; v < auxgruponumero1.size(); v++) {
                if (v == 0) {
                    matrixAuxnum1[u][v] = auxgruponumero1.get(u);
                } else {
                    matrixAuxnum1[u][v] = 0;
                }
            }
        }

        double[][] matrixRestaNum1 = subtract(matrixDataInput, matrixAuxnum1);
        double[][] matrixTranspuestaNum1 = Traspuesta(matrixRestaNum1);
        double[] arregloMultMatrixNum1 = new double[auxgruponumero1.size()];

        for (int v = 0; v < auxgruponumero1.size(); v++) {
            arregloMultMatrixNum1[v] = matrixTranspuestaNum1[0][v];
        }

        //asigno la matrix de covarianza
        double[][] matrixCovarianzaNum1 = covMatrix1;
        //le hago la inversa a la matriz de covarianza
        double[][] matrixCovarianzaInvertidaNum1 = Inversa(matrixCovarianzaNum1);

        double[] arregloNum1 = multiply2(matrixCovarianzaInvertidaNum1, arregloMultMatrixNum1);
        double[][] primMultVectNum1 = new double[auxgruponumero1.size()][auxgruponumero1.size()];

        //convierto el arreglo en una matriz para multiplicar matrizes
        for (int u = 0; u < auxgruponumero1.size(); u++) {
            for (int v = 0; v < auxgruponumero1.size(); v++) {
                if (u == 0) {
                    primMultVectNum1[u][v] = arregloNum1[u];
                } else {
                    primMultVectNum1[u][v] = 0;
                }
            }
        }

        double[][] segMultVectNum1 = multiply(primMultVectNum1, matrixRestaNum1);//   matrixResta
        //al multiplicar las matrizes la multiplicacion se coloca en segMultVect[0][0]        

        double partexponencial1 = Math.exp((-0.5) * segMultVectNum1[0][0]);

        System.out.println(segMultVectNum1[0][0] + "\n");

        System.out.println(partexponencial1 + "\n");

        double ValorFormulaNum1 = parteEntera * (Math.exp((-0.5) * segMultVectNum1[0][0]));

        System.out.println(ValorFormulaNum1 + "\n");
    }
    
    
    
    	public void random(ArrayList<ArrayList<Double>> matriz) {
	
	ArrayList<ArrayList<Double>> matrizCombinada = new ArrayList<>(50);
	
        ArrayList<Integer> auxiliar = new ArrayList<>(50);
        
	int DESDE=1;
	int HASTA=50;
	
	//int finicial=(int)(Math.random()*(HASTA-DESDE+1)+DESDE); 
	//int ffinal=(int)(Math.random()*(HASTA-DESDE+1)+DESDE); 
	
        do{
            int finicial=(int)(Math.random()*(HASTA-DESDE+1)+DESDE);                 

            if (!auxiliar.contains(finicial)){
                auxiliar.add(finicial);
                matrizCombinada.add(auxiliar.size(),matriz.get(finicial));//=matriz.get(finicial);
                }            

            }            
         while (auxiliar.size()<50);                
	
	}
    
}
