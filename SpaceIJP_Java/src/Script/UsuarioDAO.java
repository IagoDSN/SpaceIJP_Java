/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Script;

import DAObd.ConexaoBD;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.math.BigDecimal;
/**
 *
 * @author Iagod
 */
public class UsuarioDAO {
  public List<UsuarioJA> getLista() {
        String sql = "SELECT * FROM usuario";
        List<UsuarioJA> listaUsuario = new ArrayList<>();

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                UsuarioJA u = new UsuarioJA();
                u.setCodUsuario(rs.getInt("codUsuario"));
                u.setNomeUsuario(rs.getString("NomeUsuario"));
                u.setDinheiro(rs.getBigDecimal("Dinheiro"));
                u.setPrimeiraVez(rs.getInt("primeiraVez"));
                listaUsuario.add(u);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar usuários: " + ex.getMessage());
        }
        return listaUsuario;
    }

    public boolean inserir(UsuarioJA u) {
        String sql = "INSERT INTO usuario (NomeUsuario, Dinheiro, primeiraVez) VALUES (?, ?, ?)";
        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(sql);
            pst.setString(1, u.getNomeUsuario());
            pst.setBigDecimal(2, u.getDinheiro());
            pst.setInt(3, u.getPrimeiraVez());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir usuário: " + ex.getMessage());
            return false;
        }
    }

    public boolean alterar(UsuarioJA u) {
        String sql = "UPDATE usuario SET NomeUsuario=?, Dinheiro=?, primeiraVez=? WHERE codUsuario=?";
        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(sql);
            pst.setString(1, u.getNomeUsuario());
            pst.setBigDecimal(2, u.getDinheiro());
            pst.setInt(3, u.getPrimeiraVez());
            pst.setInt(4, u.getCodUsuario());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado para alteração!");
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar usuário: " + ex.getMessage());
            return false;
        }
    }

    public boolean remover(UsuarioJA u) {
        String sql = "DELETE FROM usuario WHERE codUsuario=?";
        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(sql);
            pst.setInt(1, u.getCodUsuario());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao remover usuário: " + ex.getMessage());
            return false;
        }
    }

    public UsuarioJA localizarUsuario(int codigoUsuario) {
        String sql = "SELECT * FROM usuario WHERE codUsuario=?";
        UsuarioJA u = new UsuarioJA();

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(sql);
            pst.setInt(1, codigoUsuario);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                u.setCodUsuario(rs.getInt("codUsuario"));
                u.setNomeUsuario(rs.getString("NomeUsuario"));
                u.setDinheiro(rs.getBigDecimal("Dinheiro"));
                u.setPrimeiraVez(rs.getInt("primeiraVez"));
                return u;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao localizar usuário: " + ex.getMessage());
        }

        return null;
    }
}  
