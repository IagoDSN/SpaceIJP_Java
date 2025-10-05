/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Script;

/**
 *
 * @author Iagod
 */
public class BaseJA {
    private int codBaseLancamento;
    private String nomeBase;
    private String paisBase;
    private double precoConstrucao;

    public int getCodBaseLancamento() {
        return codBaseLancamento;
    }

    public void setCodBaseLancamento(int codBaseLancamento) {
        this.codBaseLancamento = codBaseLancamento;
    }

    public String getNomeBase() {
        return nomeBase;
    }

    public void setNomeBase(String nomeBase) {
        this.nomeBase = nomeBase;
    }

    public String getPaisBase() {
        return paisBase;
    }

    public void setPaisBase(String paisBase) {
        this.paisBase = paisBase;
    }

    public double getPrecoConstrucao() {
        return precoConstrucao;
    }

    public void setPrecoConstrucao(double precoConstrucao) {
        this.precoConstrucao = precoConstrucao;
    }

    @Override
    public String toString() {
        return nomeBase;
    }
}
