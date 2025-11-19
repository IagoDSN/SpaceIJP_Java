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

    private Integer codFinanciamento;
    private String patrocinador;
    private Double valor;

    private MissaoJA objMissao;

    public FinanciamentoJA() {
    }

    public Integer getCodFinanciamento() {
        return codFinanciamento;
    }

    public void setCodFinanciamento(Integer codFinanciamento) {
        this.codFinanciamento = codFinanciamento;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public MissaoJA getObjMissao() {
        return objMissao;
    }

    public void setObjMissao(MissaoJA objMissao) {
        this.objMissao = objMissao;
    }

    @Override
    public String toString() {
        return patrocinador;
    }
}
