/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Script;

import java.util.Date;

/**
 *
 * @author Iagod
 */
public class LancamentosJA {

    private Integer codLancamento;
    private Date dataLancamento;
    private String resultado;

    private FogueteJA objFoguete;
    private MissaoJA objMissao;

    public Integer getCodLancamento() {
        return codLancamento;
    }

    public void setCodLancamento(Integer codLancamento) {
        this.codLancamento = codLancamento;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public FogueteJA getObjFoguete() {
        return objFoguete;
    }

    public void setObjFoguete(FogueteJA objFoguete) {
        this.objFoguete = objFoguete;
    }

    public MissaoJA getObjMissao() {
        return objMissao;
    }

    public void setObjMissao(MissaoJA objMissao) {
        this.objMissao = objMissao;
    }
}
