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

public class  SensoresJA{
    private int codSensores;
    private String tipo;
    private String unidade;
    private String position;
    private int fogueteCodFoguete;
    private String fogueteNome;

    public int getCodSensores() {
        return codSensores;
    }

    public void setCodSensores(int codSensores) {
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

}
