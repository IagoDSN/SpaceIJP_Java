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
public class CargaJA {
    private int codCarga;
    private String tipo;
    private int quantidade;
    private double peso;
    private String descricao;
    private int fogueteCodFoguete;
    private String fogueteNome;

    // Getters e Setters
    public int getCodCarga() {
        return codCarga;
    }

    public void setCodCarga(int codCarga) {
        this.codCarga = codCarga;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getFogueteCodFoguete() {
        return fogueteCodFoguete;
    }

    public void setFogueteCodFoguete(int fogueteCodFoguete) {
        this.fogueteCodFoguete = fogueteCodFoguete;
    }

    public String getFogueteNome() {
        return fogueteNome;
    }

    public void setFogueteNome(String fogueteNome) {
        this.fogueteNome = fogueteNome;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
