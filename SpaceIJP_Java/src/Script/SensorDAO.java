/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Script;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Iagod
 */

public class SensorDAO {

    public List<SensoresJA> getLista() {
        List<SensoresJA> lista = new ArrayList<>();
        String sql = "SELECT * FROM sensores";

        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                SensoresJA s = new SensoresJA();
                s.setCodSensores(rs.getInt("codSensores"));
                s.setTipo(rs.getString("tipo"));
                s.setUnidade(rs.getString("unidade"));
                s.setPosition(rs.getString("position"));
                s.setFogueteCodFoguete(rs.getInt("Foguete_codFoguete"));
                lista.add(s);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar sensores: " + e.getMessage());
        }

        return lista;
    }

    public boolean inserir(SensoresJA s) {
        String sql = "INSERT INTO sensores (tipo, unidade, position, Foguete_codFoguete) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
            pst.setString(1, s.getTipo());
            pst.setString(2, s.getUnidade());
            pst.setString(3, s.getPosition());
            pst.setInt(4, s.getFogueteCodFoguete());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Sensor cadastrado com sucesso!");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir sensor: " + e.getMessage());
        }
        return false;
    }

    public boolean alterar(SensoresJA s) {
        String sql = "UPDATE sensores SET tipo=?, unidade=?, position=?, Foguete_codFoguete=? WHERE codSensores=?";
        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
            pst.setString(1, s.getTipo());
            pst.setString(2, s.getUnidade());
            pst.setString(3, s.getPosition());
            pst.setInt(4, s.getFogueteCodFoguete());
            pst.setInt(5, s.getCodSensores());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Sensor alterado com sucesso!");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar sensor: " + e.getMessage());
        }
        return false;
    }

    public boolean deletar(int codSensores) {
        String sql = "DELETE FROM sensores WHERE codSensores=?";
        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
            pst.setInt(1, codSensores);
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Sensor deletado com sucesso!");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar sensor: " + e.getMessage());
        }
        return false;
    }
}
