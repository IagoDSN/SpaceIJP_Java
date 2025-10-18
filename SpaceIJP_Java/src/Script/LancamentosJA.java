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

    private int codLancamentos;
    private Date dataLancamento;
    private String resultado;
    private int fogueteCodFoguete;
    private int missoesCodMissao;
    private String fogueteNome;
    private String missaoNome;

    public int getCodLancamentos() {
        return codLancamentos;
    }

    public void setCodLancamentos(int codLancamentos) {
        this.codLancamentos = codLancamentos;
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

    public int getFogueteCodFoguete() {
        return fogueteCodFoguete;
    }

    public void setFogueteCodFoguete(int fogueteCodFoguete) {
        this.fogueteCodFoguete = fogueteCodFoguete;
    }

    public int getMissoesCodMissao() {
        return missoesCodMissao;
    }

    public void setMissoesCodMissao(int missoesCodMissao) {
        this.missoesCodMissao = missoesCodMissao;
    }

    public String getFogueteNome() {
        return fogueteNome;
    }

    public void setFogueteNome(String fogueteNome) {
        this.fogueteNome = fogueteNome;
    }

    public String getMissaoNome() {
        return missaoNome;
    }

    public void setMissaoNome(String missaoNome) {
        this.missaoNome = missaoNome;
    }
}