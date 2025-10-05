/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Script;

import DAObd.ConexaoBD;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Iagod
 */
public class CargaDAO {

    public boolean inserir(CargaJA c) {
        String sql = "INSERT INTO carga (tipo, peso, descricao, Foguete_codFoguete) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
            pst.setString(1, c.getTipo());
            pst.setDouble(2, c.getPeso());
            pst.setString(3, c.getDescricao());
            pst.setInt(4, c.getFogueteCodFoguete());

            if (pst.executeUpdate() > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    c.setCodCarga(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir carga: " + e.getMessage());
        }
        return false;
    }

    public boolean alterar(CargaJA c) {
        String sql = "UPDATE carga SET tipo=?, peso=?, descricao=?, Foguete_codFoguete=? WHERE codcarga=?";
        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
            pst.setString(1, c.getTipo());
            pst.setDouble(2, c.getPeso());
            pst.setString(3, c.getDescricao());
            pst.setInt(4, c.getFogueteCodFoguete());
            pst.setInt(5, c.getCodCarga());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar carga: " + e.getMessage());
        }
        return false;
    }

    public boolean remover(int codCarga) {
        String sql = "DELETE FROM carga WHERE codcarga=?";
        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
            pst.setInt(1, codCarga);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover carga: " + e.getMessage());
            return false;
        }
    }

    public List<CargaJA> getLista() {
        List<CargaJA> lista = new ArrayList<>();
        String sql = "SELECT c.codcarga, c.tipo, c.peso, c.descricao, "
                + "c.Foguete_codFoguete, f.nomeFoguete "
                + "FROM carga c "
                + "INNER JOIN foguete f ON c.Foguete_codFoguete = f.codFoguete";

        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                CargaJA c = new CargaJA();
                c.setCodCarga(rs.getInt("codcarga"));
                c.setTipo(rs.getString("tipo"));
                c.setPeso(rs.getDouble("peso"));
                c.setDescricao(rs.getString("descricao"));
                c.setFogueteCodFoguete(rs.getInt("Foguete_codFoguete"));
                c.setFogueteNome(rs.getString("nomeFoguete"));
                lista.add(c);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar cargas: " + e.getMessage());
        }

        return lista;
    }
}
