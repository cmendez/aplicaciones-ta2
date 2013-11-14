/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_reconocimientopatrones;

/**
 *
 * @author Christian
 */
public class Determinant {

    /**
     * Determinant of a matrix using Laplace's formula with expanding along the
     * 0th row. It is not checked whether the matrix is quadratic!
     *     
* @param m Matrix
     * @return determinant
     */
    public static double det(double[][] m) {
        int n = m.length;
        if (n == 1) {
            return m[0][0];
        } else {
            double det = 0;
            for (int j = 0; j < n; j++) {
                det += Math.pow(-1, j) * m[0][j] * det(minor(m, 0, j));
            }
            return det;
        }
    }

    /**
     * Computing the minor of the matrix m without the i-th row and the j-th
     * column
     *     
* @param m input matrix
     * @param i removing the i-th row of m
     * @param j removing the j-th column of m
     * @return minor of m
     */
    private static double[][] minor(final double[][] m, final int i, final int j) {
        int n = m.length;
        double[][] minor = new double[n - 1][n - 1];
// index for minor matrix position:
        int r = 0, s = 0;
        for (int k = 0; k < n; k++) {
            double[] row = m[k];
            if (k != i) {
                for (int l = 0; l < row.length; l++) {
                    if (l != j) {
                        minor[r][s++] = row[l];
                    }
                }
                r++;
                s = 0;
            }
        }
        return minor;
    }

    private static void printMatrix(double[][] m) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m.length; i++) {
            double[] row = m[i];
            sb.append("[");
            for (int j = 0; j < row.length; j++) {
                sb.append(" ");
                sb.append(row[j]);
            }
            sb.append(" ]\n");
        }
        sb.deleteCharAt(sb.length() - 1);

        System.out.println(sb.toString());
    }

    public static double getDecDet(double[][] a) {
        int n = a.length - 1;
        if (n < 0) {
            return 0;
        }
        double M[][][] = new double[n + 1][][];

        M[n] = a;  // init first, largest, M to a

        // create working arrays
        for (int i = 0; i < n; i++) {
            M[i] = new double[i + 1][i + 1];
        }

        return getDecDet(M, n);
    } // end method getDecDet double [][] parameter

    public static double getDecDet(double[][][] M, int m) {
        if (m == 0) {
            return M[0][0][0];
        }
        int e = 1;

        // init subarray to upper left mxm submatrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                M[m - 1][i][j] = M[m][i][j];
            }
        }
        double sum = M[m][m][m] * getDecDet(M, m - 1);

        // walk through rest of rows of M
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                M[m - 1][i][j] = M[m][i + 1][j];
            }
            e = -e;
            sum += e * M[m][i][m] * getDecDet(M, m - 1);
        } // end for each row of matrix

        return sum;
    } // end getDecDet double [][][], int

    public static double determinant(double[][] matrix) { //method sig. takes a matrix (two dimensional array), returns determinant.
        int sum = 0;
        int s;
        if (matrix.length == 1) {  //bottom case of recursion. size 1 matrix determinant is itself.
            return (matrix[0][0]);
        }
        for (int i = 0; i < matrix.length; i++) { //finds determinant using row-by-row expansion
            double[][] smaller = new double[matrix.length - 1][matrix.length - 1]; //creates smaller matrix- values not in same row, column
            for (int a = 1; a < matrix.length; a++) {
                for (int b = 0; b < matrix.length; b++) {
                    if (b < i) {
                        smaller[a - 1][b] = matrix[a][b];
                    } else if (b > i) {
                        smaller[a - 1][b - 1] = matrix[a][b];
                    }
                }
            }
            if (i % 2 == 0) { //sign changes based on i
                s = 1;
            } else {
                s = -1;
            }
            sum += s * matrix[0][i] * (determinant(smaller));
        }
        return (sum); //returns determinant value. once stack is finished, returns final determinant.
    }

}
