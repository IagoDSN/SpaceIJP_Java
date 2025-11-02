/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Script;

import java.math.BigDecimal;

/**
 *
 * @author Iagod
 */
public class UsuarioJA {
    private int codUsuario;
    private String nomeUsuario;
    private BigDecimal dinheiro;
    private int primeiraVez; // 0 ou 1

    public UsuarioJA() {
        System.out.println("Usuario Criado");
    }
    public UsuarioJA(int codUsuario, String nomeUsuario, BigDecimal dinheiro, int primeiraVez) {
        this.codUsuario = codUsuario;
        this.nomeUsuario = nomeUsuario;
        this.dinheiro = dinheiro;
        this.primeiraVez = primeiraVez;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public BigDecimal getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(BigDecimal dinheiro) {
        this.dinheiro = dinheiro;
    }

    public int getPrimeiraVez() {
        return primeiraVez;
    }

    public void setPrimeiraVez(int primeiraVez) {
        this.primeiraVez = primeiraVez;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "codUsuario=" + codUsuario +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", dinheiro=" + dinheiro +
                ", primeiraVez=" + primeiraVez +
                '}';
    }
}   
