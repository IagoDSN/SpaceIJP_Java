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
    private Integer codCarga;
    private String tipo;
    private Integer quantidade;
    private Double peso;
    private String descricao;

    private FogueteJA objFoguete;

    public CargaJA() {
    }

    public Integer getCodCarga() {
        return codCarga;
    }

    public void setCodCarga(Integer codCarga) {
        this.codCarga = codCarga;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public FogueteJA getObjFoguete() {
        return objFoguete;
    }

    public void setObjFoguete(FogueteJA objFoguete) {
        this.objFoguete = objFoguete;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
