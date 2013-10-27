/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_reconocimientopatrones;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Christian
 */
public class LecturaMNIST {

    private String archivo_imagenes;
    private String archivo_labels;
    private static final int MAGIC_OFFSET = 0;
    private static final int OFFSET_SIZE = 4; //in bytes
    private static final int LABEL_MAGIC = 2049;
    private static final int IMAGE_MAGIC = 2051;
    private static final int NUMBER_ITEMS_OFFSET = 4;
    private static final int ITEMS_SIZE = 4;
    private static final int NUMBER_OF_ROWS_OFFSET = 8;
    private static final int ROWS_SIZE = 4;
    private static final int ROWS = 28;
    private static final int NUMBER_OF_COLUMNS_OFFSET = 12;
    private static final int COLUMNS_SIZE = 4;
    private static final int COLUMNS = 28;
    private static final int IMAGE_OFFSET = 16;
    private static final int IMAGE_SIZE = ROWS * COLUMNS;
    private ArrayList<byte[]> imagenesArray;
    private ArrayList<Integer> labelsArray;

    public LecturaMNIST(String ARCHIVO_LABELS, String ARCHIVO_IMAGENES) {
        archivo_labels = ARCHIVO_LABELS;
        archivo_imagenes = ARCHIVO_IMAGENES;
    }

    public ArrayList<byte[]> getImagenesArray() {
        return imagenesArray;
    }

    public ArrayList<Integer> getLabelsArray() {
        return labelsArray;
    }

    public void LeerMNIST() {
        imagenesArray = new ArrayList<>();
        labelsArray = new ArrayList<>();
        try {
            ByteArrayOutputStream labelBuffer = new ByteArrayOutputStream();
            ByteArrayOutputStream imageBuffer = new ByteArrayOutputStream();

            InputStream labelInputStream = new FileInputStream(archivo_labels);
            InputStream imageInputStream = new FileInputStream(archivo_imagenes);

            int read;
            byte[] buffer = new byte[16384];

            while ((read = labelInputStream.read(buffer, 0, buffer.length)) != -1) {
                labelBuffer.write(buffer, 0, read);
            }

            labelBuffer.flush();

            while ((read = imageInputStream.read(buffer, 0, buffer.length)) != -1) {
                imageBuffer.write(buffer, 0, read);
            }

            imageBuffer.flush();

            byte[] labelBytes = labelBuffer.toByteArray();
            byte[] imageBytes = imageBuffer.toByteArray();

            byte[] labelMagic = Arrays.copyOfRange(labelBytes, 0, OFFSET_SIZE);
            byte[] imageMagic = Arrays.copyOfRange(imageBytes, 0, OFFSET_SIZE);

            if (ByteBuffer.wrap(labelMagic).getInt() != LABEL_MAGIC) {
                throw new IOException("Bad magic number in label file!");
            }

            if (ByteBuffer.wrap(imageMagic).getInt() != IMAGE_MAGIC) {
                throw new IOException("Bad magic number in image file!");
            }

            int numberOfLabels = ByteBuffer.wrap(Arrays.copyOfRange(labelBytes, NUMBER_ITEMS_OFFSET, NUMBER_ITEMS_OFFSET + ITEMS_SIZE)).getInt();
            int numberOfImages = ByteBuffer.wrap(Arrays.copyOfRange(imageBytes, NUMBER_ITEMS_OFFSET, NUMBER_ITEMS_OFFSET + ITEMS_SIZE)).getInt();

            if (numberOfImages != numberOfLabels) {
                throw new IOException("The number of labels and images do not match!");
            }

            int numRows = ByteBuffer.wrap(Arrays.copyOfRange(imageBytes, NUMBER_OF_ROWS_OFFSET, NUMBER_OF_ROWS_OFFSET + ROWS_SIZE)).getInt();
            int numCols = ByteBuffer.wrap(Arrays.copyOfRange(imageBytes, NUMBER_OF_COLUMNS_OFFSET, NUMBER_OF_COLUMNS_OFFSET + COLUMNS_SIZE)).getInt();

            //System.out.println(numRows + "x" + numCols);            

            if (numRows != ROWS && numRows != COLUMNS) {
                throw new IOException("Bad image. Rows and columns do not equal " + ROWS + "x" + COLUMNS);
            }
            //numberOfLabels = 10;
            for (int i = 0; i < numberOfLabels; i++) {
                int label = labelBytes[OFFSET_SIZE + ITEMS_SIZE + i];
                labelsArray.add(label);
//            System.out.println("Label: " + label);
                byte[] imageData = Arrays.copyOfRange(imageBytes, (i * IMAGE_SIZE) + IMAGE_OFFSET, (i * IMAGE_SIZE) + IMAGE_OFFSET + IMAGE_SIZE);
//            System.out.println(imageData.length);
                imagenesArray.add(imageData);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}
