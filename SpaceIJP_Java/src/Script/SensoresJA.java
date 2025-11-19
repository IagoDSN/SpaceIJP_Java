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
public class SensoresJA {

    private Integer codSensores;
    private String tipo;
    private String unidade;
    private String position;

    private FogueteJA objFoguete;

    public Integer getCodSensores() {
        return codSensores;
    }

    public void setCodSensores(Integer codSensores) {
        this.codSensores = codSensores;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public FogueteJA getObjFoguete() {
        return objFoguete;
    }

    public void setObjFoguete(FogueteJA objFoguete) {
        this.objFoguete = objFoguete;
    }

    @Override
    public String toString() {
        return tipo + " - " + unidade;
    }
}
