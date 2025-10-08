/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Script;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import DAObd.ConexaoBD;

/**
 *
 * @author 13410289682
 */

public class DestinoDAO {

    // Inserir novo destino
    public boolean inserir(DestinoJA d) {
        String sql = "INSERT INTO destino (nomeLocal, distancia, pressao, aceleracaoGravidade, tipo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pst = ConexaoBD.getPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, d.getNomeLocal());
            pst.setFloat(2, d.getDistancia());
            pst.setDouble(3, d.getPressao());
            pst.setDouble(4, d.getAceleracaoGravidade());
            pst.setString(5, d.getTipo());

            int linhas = pst.executeUpdate();
            if (linhas > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    d.setCodDestino(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir destino: " + e.getMessage());
        }
        return false;
    }

    // Alterar destino existente
    public boolean alterar(DestinoJA d) {
        String sql = "UPDATE destino SET nomeLocal=?, distancia=?, pressao=?, aceleracaoGravidade=?, tipo=? WHERE codDestino=?";
        try (PreparedStatement pst = ConexaoBD.getPreparedStatement(sql)) {
            pst.setString(1, d.getNomeLocal());
            pst.setFloat(2, d.getDistancia());
            pst.setDouble(3, d.getPressao());
            pst.setDouble(4, d.getAceleracaoGravidade());
            pst.setString(5, d.getTipo());
            pst.setInt(6, d.getCodDestino());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar destino: " + e.getMessage());
        }
        return false;
    }

    // Remover destino
    public boolean remover(int cod) {
        String sql = "DELETE FROM destino WHERE codDestino=?";
        try (PreparedStatement pst = ConexaoBD.getPreparedStatement(sql)) {
            pst.setInt(1, cod);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover destino: " + e.getMessage());
        }
        return false;
    }

    // Listar todos os destinos
    public List<DestinoJA> getLista() {
        List<DestinoJA> lista = new ArrayList<>();
        String sql = "SELECT * FROM destino";
        try (PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                DestinoJA d = new DestinoJA();
                d.setCodDestino(rs.getInt("codDestino"));
                d.setNomeLocal(rs.getString("nomeLocal"));
                d.setDistancia(rs.getFloat("distancia"));
                d.setPressao(rs.getDouble("pressao"));
                d.setAceleracaoGravidade(rs.getDouble("aceleracaoGravidade"));
                d.setTipo(rs.getString("tipo"));
                lista.add(d);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar destinos: " + e.getMessage());
        }
        return lista;
    }
}
