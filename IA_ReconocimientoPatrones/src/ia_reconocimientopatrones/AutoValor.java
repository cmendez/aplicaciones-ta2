/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_reconocimientopatrones;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Christian
 */
public class AutoValor implements Comparable<AutoValor> {

    private double valor;
    private int indice;

    public AutoValor() {
    }

    public AutoValor(double valor, int indice) {
        this.valor = valor;
        this.indice = indice;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    @Override
    public int compareTo(AutoValor autoValor) {
        return (int) (autoValor.getValor() - this.valor);
    }
}
