package ia_reconocimientopatrones;

import java.util.ArrayList;

public class Principal {
    
    private static final String ARCHIVO_LABELS = "C:/Users/Christian/Documents/PUCP/2013-2/"
            + "APLICACIONES DE CIENCIAS DE LA COMPUTACIÓN/Tarea Académica 2/train-labels.idx1-ubyte";
    private static final String ARCHIVO_IMAGENES = "C:/Users/Christian/Documents/PUCP/2013-2/"
            + "APLICACIONES DE CIENCIAS DE LA COMPUTACIÓN/Tarea Académica 2/train-images.idx3-ubyte";
    private static final String RUTA_DE_GUARDADO = "./Imágenes Generadas/";
    
    public static void main(String[] args) {
        LecturaMNIST lector = new LecturaMNIST(ARCHIVO_LABELS, ARCHIVO_IMAGENES);
        lector.LeerMNIST();
        
        ArrayList<byte[]> imagenesArray = lector.getImagenesArray();
        ArrayList<Integer> labelsArray = lector.getLabelsArray();
        
        GeneradorImagenes generador = new GeneradorImagenes(imagenesArray, labelsArray, RUTA_DE_GUARDADO);
        int nroImagenes = 1000;
        //generador.GenerarImagenes(nroImagenes);
        
        AlgoritmoBayesiano algoritmo = new AlgoritmoBayesiano();
        algoritmo.ProcesarDatos(nroImagenes, imagenesArray, labelsArray);
    }
}
