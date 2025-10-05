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
public class CargoJA {
    private int codCargo;
    private String nomeCargo;
    private Float salarioInicial;

    // Getters e Setters
    public int getCodCargo() {
        return codCargo;
    }

    public void setCodCargo(int codCargo) {
        this.codCargo = codCargo;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    public Float getSalarioInicial() {
        return salarioInicial;
    }

    public void setSalarioInicial(Float salarioInicial) {
        this.salarioInicial = salarioInicial;
    }

    @Override
    public String toString() {
        return nomeCargo;
    }
}
