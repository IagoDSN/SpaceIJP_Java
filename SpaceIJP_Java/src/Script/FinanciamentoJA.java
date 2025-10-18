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

public class FinanciamentoJA {
    private int codFinanciamento;
    private String patrocinador;
    private double valor;
    private int missoesCodMissao;
    private String nomeMissao;

    public FinanciamentoJA() {}

    public int getCodFinanciamento() {
        return codFinanciamento;
    }

    public void setCodFinanciamento(int codFinanciamento) {
        this.codFinanciamento = codFinanciamento;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getMissoesCodMissao() {
        return missoesCodMissao;
    }

    public void setMissoesCodMissao(int missoesCodMissao) {
        this.missoesCodMissao = missoesCodMissao;
    }

    public String getNomeMissao() {
        return nomeMissao;
    }

    public void setNomeMissao(String nomeMissao) {
        this.nomeMissao = nomeMissao;
    }
}