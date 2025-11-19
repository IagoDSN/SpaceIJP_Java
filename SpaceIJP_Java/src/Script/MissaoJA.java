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

public class MissaoJA {

    private Integer codMissao;
    private String nomeMissao;
    private String objetivoMissao;
    private Date dataInicio;
    private Date dataFim;
    private String status;

    private DestinoJA objDestino;

    public Integer getCodMissao() {
        return codMissao;
    }

    public void setCodMissao(Integer codMissao) {
        this.codMissao = codMissao;
    }

    public String getNomeMissao() {
        return nomeMissao;
    }

    public void setNomeMissao(String nomeMissao) {
        this.nomeMissao = nomeMissao;
    }

    public String getObjetivoMissao() {
        return objetivoMissao;
    }

    public void setObjetivoMissao(String objetivoMissao) {
        this.objetivoMissao = objetivoMissao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DestinoJA getObjDestino() {
        return objDestino;
    }

    public void setObjDestino(DestinoJA objDestino) {
        this.objDestino = objDestino;
    }

    @Override
    public String toString() {
        return nomeMissao;
    }
}
