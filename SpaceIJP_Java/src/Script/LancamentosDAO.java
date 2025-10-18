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

public class LancamentosDAO {

    public boolean inserir(LancamentosJA l) {
        String sql = "INSERT INTO lancamentos (dataLancamento, resultado, Foguete_codFoguete, Missoes_codMissao) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setDate(1, new java.sql.Date(l.getDataLancamento().getTime()));
            pst.setString(2, l.getResultado());
            pst.setInt(3, l.getFogueteCodFoguete());
            pst.setInt(4, l.getMissoesCodMissao());

            int ok = pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                l.setCodLancamentos(rs.getInt(1));
            }

            return ok > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir lançamento: " + e.getMessage());
        }
        return false;
    }

    public boolean alterar(LancamentosJA l) {
        String sql = "UPDATE lancamentos SET dataLancamento=?, resultado=?, Foguete_codFoguete=?, Missoes_codMissao=? WHERE codLancamentos=?";
        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
            pst.setDate(1, new java.sql.Date(l.getDataLancamento().getTime()));
            pst.setString(2, l.getResultado());
            pst.setInt(3, l.getFogueteCodFoguete());
            pst.setInt(4, l.getMissoesCodMissao());
            pst.setInt(5, l.getCodLancamentos());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar lançamento: " + e.getMessage());
        }
        return false;
    }

    public boolean remover(int codLancamentos) {
        String sql = "DELETE FROM lancamentos WHERE codLancamentos=?";
        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
            pst.setInt(1, codLancamentos);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover lançamento: " + e.getMessage());
        }
        return false;
    }

    public List<LancamentosJA> getLista() {
        List<LancamentosJA> lista = new ArrayList<>();
        String sql = "SELECT l.*, f.nomeFoguete, m.nomeMissao FROM lancamentos l "
                   + "JOIN foguete f ON l.Foguete_codFoguete = f.codFoguete "
                   + "JOIN missoes m ON l.Missoes_codMissao = m.codMissao";

        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                LancamentosJA l = new LancamentosJA();
                l.setCodLancamentos(rs.getInt("codLancamentos"));
                l.setDataLancamento(rs.getDate("dataLancamento"));
                l.setResultado(rs.getString("resultado"));
                l.setFogueteCodFoguete(rs.getInt("Foguete_codFoguete"));
                l.setMissoesCodMissao(rs.getInt("Missoes_codMissao"));
                l.setFogueteNome(rs.getString("nomeFoguete"));
                l.setMissaoNome(rs.getString("nomeMissao"));
                lista.add(l);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar lançamentos: " + e.getMessage());
        }

        return lista;
    }
}