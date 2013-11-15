/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_reconocimientopatrones;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Christian
 */
public class GeneradorImagenes {

    private ArrayList<byte[]> imagenesArray;
    private ArrayList<Integer> labelsArray;
    private String ruta_guardado;

    public GeneradorImagenes(ArrayList<byte[]> imagenesArray, ArrayList<Integer> labelsArray, String ruta_guardado) {
        this.imagenesArray = imagenesArray;
        this.labelsArray = labelsArray;
        this.ruta_guardado = ruta_guardado;
    }

    public int UnsignedToBytes(byte b) {
        return b & 0xFF;
    }

    public void ExportImageToFile(String fileName, RenderedImage image) throws IOException {
        File file = new File(fileName);
        //to export to png, change 2 parameter to "png"
        ImageIO.write(image, "jpg", file);
    }

    public BufferedImage ConvertRGBImage(int[][] rgbValue) {
        int height = rgbValue.length;
        int width = rgbValue[0].length;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                bufferedImage.setRGB(x, y, rgbValue[y][x]);
            }
        }
        return bufferedImage;
    }

    public BufferedImage ConvertRGBImageWithHeader(int[][] rgbValue, String strHeader) {
        //We add extra pixels on top of the image for the strHeader
        int headerHeight = 13;
        int height = rgbValue.length + headerHeight;
        int width = rgbValue[0].length;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //we either have to loop through all values, or convert to 1-d array
        for (int y = headerHeight; y < height; y++) {
            for (int x = 0; x < width; x++) {
                bufferedImage.setRGB(x, y, rgbValue[y - headerHeight][x]);
            }
        }
        //Draw the text
        Graphics2D g = bufferedImage.createGraphics();
        g.setFont(new Font("Monospaced", Font.BOLD, 14));
        g.setColor(Color.white);
        g.drawString(strHeader, 0, 10);

        return bufferedImage;
    }

    public void GenerarImagenes(int nroImagenes) throws IOException {
        //try {
        //create the filtering object
        HaarFilter haar = new HaarFilter(32);
        //adjust its parameters as desired
        haar.setFractionalBits(0);
        haar.setIterations(2);

        for (int i = 0; i < nroImagenes; i++) {
            byte[] imagen = imagenesArray.get(i);

            byte[] imagen32x32 = Convertir_A_Imagen32x32(imagen);
            int[] filter = haar.filter(imagen32x32, null);
            int[][] filtermatriz = this.Vector_a_Matriz(filter);
            int[][] filtergrande = this.AgrandarPorFactor(filtermatriz, 6);
            BufferedImage bffiltergrande = this.ConvertRGBImage(filtergrande);
            //byte[] regenerado = haar.invert(filter, null);
            BufferedImage bfimagefilter = haar.filterToImage(filter);
            BufferedImage bfimage32x32 = haar.valuesToImage(imagen32x32);

            int[][] pixeles0y1 = new int[28][28];
            int[][] pixeles255 = new int[28][28];
            int k = 0;
            for (int u = 0; u < 28; u++) {
                for (int v = 0; v < 28; v++) {
                    int p = this.UnsignedToBytes(imagen[k]);
                    pixeles255[u][v] = p;
                    if (p != 0) {
                        p = 1;
                    }
                    pixeles0y1[u][v] = p;
                    k++;
                    //System.out.print(pixel[u][v] + " ");
                }
                //System.out.println();
            }
            int[][] pixelesTrimeados0y1 = this.RemoverPadding4(pixeles0y1);
            int[][] pixelesTrimeados255 = this.RemoverPadding4(pixeles255);
            int[][] pixelesImagen = this.AgrandarPorFactor(pixelesTrimeados255, 6);
            String nombreArchivo = labelsArray.get(i) + "_img" + i;
            BufferedImage image = this.ConvertRGBImage(pixelesImagen);
            //this.ExportImageToFile(this.ruta_guardado + nombreArchivo + ".jpg", image);

            this.ExportImageToFile(this.ruta_guardado + nombreArchivo + "_16x16.jpg", image);
            this.ExportImageToFile(this.ruta_guardado + nombreArchivo + "_bffiltergrande.jpg", bffiltergrande);
            this.ExportImageToFile(this.ruta_guardado + nombreArchivo + "_bfimagefilter.jpg", bfimagefilter);
            this.ExportImageToFile(this.ruta_guardado + nombreArchivo + "_bfimage32x32.jpg", bfimage32x32);

            this.GenerarArchivo(pixelesTrimeados0y1, labelsArray.get(i), nombreArchivo);
        }
//        } catch (Exception e) {
//            System.out.println("GenerarImagenes "+e.toString());
//        }
    }

    private void GenerarArchivo(int[][] pixelesTrimeados, int label, String nombreArchivo) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(this.ruta_guardado + nombreArchivo + ".txt"));
            int height = pixelesTrimeados.length;
            int width = pixelesTrimeados[0].length;
//            writer.write("LABEL : " + label);
//            writer.newLine();
//            writer.newLine();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    writer.write(pixelesTrimeados[i][j] + " ");
                }
                if ((i + 1) < height) {
                    writer.newLine();
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
                System.out.println("GenerarArchivo" + e.toString());
            }
        }

    }

    private int[][] RemoverPadding4(int[][] pixeles) { //28 x 28
        //5,22: ignorar 6 filas arriba y abajo. Total 16 filas
        //6,21: ignorar 7 columnas izq y der. Total 14 columnas
        int[][] pixelesTrimeados = new int[16][16];
        int height = pixeles.length;
        int width = pixeles[0].length;
        for (int fila = 0; fila < height; fila++) {
            if ((fila > 5) && (fila < 22)) {
                for (int col = 0; col < width; col++) {
                    if ((col > 5) && (col < 22)) {
                        pixelesTrimeados[fila - 6][col - 6] = pixeles[fila][col];
                    }
                }
            }
        }
        return pixelesTrimeados;
    }

    private int[][] AgrandarPorFactor(int[][] pixeles, int factor) {
        int nfilas = pixeles.length;
        int ncolumnas = pixeles[0].length;
        int height = pixeles.length * factor;
        int width = pixeles[0].length * factor;
        int[][] nuevaMatriz = new int[height][width];

        for (int fila = 0; fila < height; fila++) {
            int i = fila / factor;
            for (int col = 0; col < width; col++) {
                int j = col / factor;
                nuevaMatriz[fila][col] = pixeles[i][j];
            }
        }
        return nuevaMatriz;
    }

    private byte[] Convertir_A_Imagen32x32(byte[] imagen) {
        byte[][] m = new byte[32][32];
        byte[] b = new byte[32 * 32]; //inicializado en ceros
        int t = 0;

        for (int i = 0; i < 32; i++) {
            if ((i >= 2) && (i <= 29)) { //primeras y ultimas 2 filas
                for (int j = 0; j < 32; j++) {
                    if ((j >= 2) && (j <= 29)) { //primeras y ultimas 2 columnas
                        m[i][j] = imagen[t];
                        t++;
                    }
                }
            }
        }
        t = 0;
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                b[t] = m[i][j];
                t++;
            }
        }

        return b;
    }

    private int[][] Vector_a_Matriz(int[] v) {
        int n = (int) Math.sqrt((double) v.length);
        int[][] m = new int[n][n];
        int t = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m[i][j] = v[t];
                t++;
            }
        }
        return m;
    }

    static byte[] Convertir_A_Imagen16x16(byte[] imagen) { //imagen: 28x28
        byte[][] m = new byte[28][28];
        byte[] b = new byte[16 * 16]; //inicializado en ceros
        int t = 0, k = 0;

        for (int fil = 0; fil < 28; fil++) {
            if ((fil > 5) && (fil < 22)) {
                for (int col = 0; col < 28; col++) {
                    if ((col > 5) && (col < 22)) {
                        //m[i][j] = imagen[k];
                        b[t] = imagen[fil * 28 + col];
                        t++;
                    }
                }
            }
        }
//        t = 0;
//        for (int i = 0; i < 28; i++) {
//            for (int j = 0; j < 28; j++) {
//                b[t] = m[i][j];
//                t++;
//            }
//        }

        return b;
    }

    public void CrearArchivoTextoLabels() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(this.ruta_guardado + "Labels.txt"));
            for (int i = 0; i < 10000; i++) {
                int label = labelsArray.get(i);
                writer.write("Label: "+ label);
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
                System.out.println("GenerarArchivo" + e.toString());
            }
        }
    }
}
