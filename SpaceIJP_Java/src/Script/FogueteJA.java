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
public class FogueteJA {
    
    private int codFoguete;
    private String nomeFoguete;
    private float maximoCombustivel;
    private float quantCombustivel;
    private double velocidade;
    private int status;

    public FogueteJA (){
        
    }

    public int getCodFoguete() { return codFoguete; }
    public void setCodFoguete(int codFoguete) { this.codFoguete = codFoguete; }
    public String getNomeFoguete() { return nomeFoguete; }
    public void setNomeFoguete(String nomeFoguete) { this.nomeFoguete = nomeFoguete; }
    public float getMaximoCombustivel() { return maximoCombustivel; }
    public void setMaximoCombustivel(float maximoCombustivel) { this.maximoCombustivel = maximoCombustivel; }
    public float getQuantCombustivel() { return quantCombustivel; }
    public void setQuantCombustivel(float quantCombustivel) { this.quantCombustivel = quantCombustivel; }
    public double getVelocidade() { return velocidade; }
    public void setVelocidade(double velocidade) { this.velocidade = velocidade; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    
    @Override
    public String toString() {
        return getNomeFoguete();
    }
}


