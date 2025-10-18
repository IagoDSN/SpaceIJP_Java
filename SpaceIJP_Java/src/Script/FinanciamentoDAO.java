/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Script;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import DAObd.ConexaoBD;

/**
 *
 * @author Iagod
 */

public class FinanciamentoDAO {

    public static List<FinanciamentoJA> getLista() {
        List<FinanciamentoJA> lista = new ArrayList<>();
        String sql = "SELECT f.*, m.nomeMissao FROM financiamento f JOIN missoes m ON f.Missoes_codMissao = m.codMissao";
        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                FinanciamentoJA f = new FinanciamentoJA();
                f.setCodFinanciamento(rs.getInt("codFinanciamento"));
                f.setPatrocinador(rs.getString("patrocinador"));
                f.setValor(rs.getDouble("valor"));
                f.setMissoesCodMissao(rs.getInt("Missoes_codMissao"));
                f.setNomeMissao(rs.getString("nomeMissao"));
                lista.add(f);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar financiamentos: " + e.getMessage());
        }
        return lista;
    }

    public static boolean inserir(FinanciamentoJA f) {
        String sql = "INSERT INTO financiamento (patrocinador, valor, Missoes_codMissao) VALUES (?, ?, ?)";
        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, f.getPatrocinador());
            pst.setDouble(2, f.getValor());
            pst.setInt(3, f.getMissoesCodMissao());

            int linhas = pst.executeUpdate();
            if (linhas > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    f.setCodFinanciamento(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir financiamento: " + e.getMessage());
        }
        return false;
    }

    public static boolean alterar(FinanciamentoJA f) {
        String sql = "UPDATE financiamento SET patrocinador=?, valor=?, Missoes_codMissao=? WHERE codFinanciamento=?";
        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
            pst.setString(1, f.getPatrocinador());
            pst.setDouble(2, f.getValor());
            pst.setInt(3, f.getMissoesCodMissao());
            pst.setInt(4, f.getCodFinanciamento());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar financiamento: " + e.getMessage());
        }
        return false;
    }

    public static boolean remover(int codFinanciamento) {
        String sql = "DELETE FROM financiamento WHERE codFinanciamento=?";
        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
            pst.setInt(1, codFinanciamento);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover financiamento: " + e.getMessage());
        }
        return false;
    }
}

