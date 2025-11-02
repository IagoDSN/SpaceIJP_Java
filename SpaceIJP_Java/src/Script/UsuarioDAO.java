/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Script;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import DAObd.ConexaoBD;
import java.math.BigDecimal;

/**
 *
 * @author Iagod
 */
public class UsuarioDAO {
  public void inserirUsuario(UsuarioJA usuario) {
        String sql = "INSERT INTO usuario (NomeUsuario, Dinheiro, primeiraVez) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNomeUsuario());
            stmt.setBigDecimal(2, usuario.getDinheiro());
            stmt.setInt(3, usuario.getPrimeiraVez());
            stmt.executeUpdate();
            System.out.println("Usuário inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar todos os usuários
    public List<UsuarioJA> listarUsuarios() {
        List<UsuarioJA> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                UsuarioJA u = new UsuarioJA();
                u.setCodUsuario(rs.getInt("codUsuario"));
                u.setNomeUsuario(rs.getString("NomeUsuario"));
                u.setDinheiro(rs.getBigDecimal("Dinheiro"));
                u.setPrimeiraVez(rs.getInt("primeiraVez"));
                lista.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Atualizar usuário
    public void atualizarUsuario(UsuarioJA usuario) {
        String sql = "UPDATE usuario SET NomeUsuario=?, Dinheiro=?, primeiraVez=? WHERE codUsuario=?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNomeUsuario());
            stmt.setBigDecimal(2, usuario.getDinheiro());
            stmt.setInt(3, usuario.getPrimeiraVez());
            stmt.setInt(4, usuario.getCodUsuario());
            stmt.executeUpdate();
            System.out.println("Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Deletar usuário
    public void deletarUsuario(int codUsuario) {
        String sql = "DELETE FROM usuario WHERE codUsuario=?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codUsuario);
            stmt.executeUpdate();
            System.out.println("Usuário deletado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Buscar usuário por ID
    public UsuarioJA buscarPorId(int codUsuario) {
        String sql = "SELECT * FROM usuario WHERE codUsuario=?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UsuarioJA(
                        rs.getInt("codUsuario"),
                        rs.getString("NomeUsuario"),
                        rs.getBigDecimal("Dinheiro"),
                        rs.getInt("primeiraVez")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}  
