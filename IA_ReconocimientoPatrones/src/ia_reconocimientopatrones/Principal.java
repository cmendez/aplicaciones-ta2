package ia_reconocimientopatrones;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal {

    private static final String ARCHIVO_LABELS = "C:/Users/Christian/Documents/PUCP/2013-2/"
            + "APLICACIONES DE CIENCIAS DE LA COMPUTACIÓN/Tarea Académica 2/train-labels.idx1-ubyte";
    private static final String ARCHIVO_IMAGENES = "C:/Users/Christian/Documents/PUCP/2013-2/"
            + "APLICACIONES DE CIENCIAS DE LA COMPUTACIÓN/Tarea Académica 2/train-images.idx3-ubyte";
    private static final String RUTA_DE_GUARDADO = "./Imágenes Generadas/";

    public static void main(String[] args) {
        try {
            LecturaMNIST lector = new LecturaMNIST(ARCHIVO_LABELS, ARCHIVO_IMAGENES);
            lector.LeerMNIST();

            ArrayList<byte[]> imagenesArray = lector.getImagenesArray();
            ArrayList<Integer> labelsArray = lector.getLabelsArray();

            GeneradorImagenes generador = new GeneradorImagenes(imagenesArray, labelsArray, RUTA_DE_GUARDADO);
            int nroImagenes = 30; //limite: 26000
            //generador.GenerarImagenes(10);
            
            AlgoritmoBayesiano algoritmo = new AlgoritmoBayesiano();
            algoritmo.Entrenar_Modelo(nroImagenes, imagenesArray, labelsArray);
            //algoritmo.Testear_Modelo(imagenesArray, 10, labelsArray);
            //algoritmo.ExtraerMatricesCovarianzas();
            //algoritmo.ProcesarIrisFlowersDataSet();
            //algoritmo.Entrenamiento_IrisDataSet();            
            
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
