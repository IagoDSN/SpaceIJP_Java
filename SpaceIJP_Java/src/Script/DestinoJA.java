/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Script;

/**
 *
 * @author 13410289682
 */

public class DestinoJA {

    private int codDestino;
    private String nomeLocal;
    private float distancia;
    private double pressao;
    private double aceleracaoGravidade;
    private String tipo;

    public int getCodDestino() {
        return codDestino;
    }

    public void setCodDestino(int codDestino) {
        this.codDestino = codDestino;
    }

    public String getNomeLocal() {
        return nomeLocal;
    }

    public void setNomeLocal(String nomeLocal) {
        this.nomeLocal = nomeLocal;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public double getPressao() {
        return pressao;
    }

    public void setPressao(double pressao) {
        this.pressao = pressao;
    }

    public double getAceleracaoGravidade() {
        return aceleracaoGravidade;
    }

    public void setAceleracaoGravidade(double aceleracaoGravidade) {
        this.aceleracaoGravidade = aceleracaoGravidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return nomeLocal;
    }
}

