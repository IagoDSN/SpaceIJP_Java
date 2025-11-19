/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Script;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import DAObd.ConexaoBD;

/**
 *
 * @author Iagod
 */
public class FinanciamentoDAO {

    MissaoDAO objMissoesDao = new MissaoDAO();

    public List<FinanciamentoJA> getLista() {
        String mysql = "SELECT * FROM financiamento";
        List<FinanciamentoJA> listaFinanciamentos = new ArrayList<>();

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                FinanciamentoJA objFin = new FinanciamentoJA();

                objFin.setCodFinanciamento(rs.getInt("codFinanciamento"));
                objFin.setPatrocinador(rs.getString("patrocinador"));
                objFin.setValor(rs.getDouble("valor"));

                int codMissao = rs.getInt("Missoes_codMissao");
                objFin.setObjMissao(objMissoesDao.localizarMissao(codMissao));

                listaFinanciamentos.add(objFin);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Erro de SQL na classe FinanciamentoDAO no método getLista: " + ex.getMessage());
        }

        return listaFinanciamentos;
    }

    public boolean inserir(FinanciamentoJA f) {
        String mysql = "INSERT INTO financiamento (patrocinador, valor, Missoes_codMissao) "
                + "VALUES (?, ?, ?)";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);

            pst.setString(1, f.getPatrocinador());
            pst.setDouble(2, f.getValor());
            pst.setInt(3, f.getObjMissao().getCodMissao());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Financiamento cadastrado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Financiamento não cadastrado!");
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Erro de SQL na classe FinanciamentoDAO no método inserir: " + ex.getMessage());
            return false;
        }
    }

    public boolean alterar(FinanciamentoJA f) {
        String mysql = "UPDATE financiamento SET patrocinador=?, valor=?, Missoes_codMissao=? "
                + "WHERE codFinanciamento=?";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);

            pst.setString(1, f.getPatrocinador());
            pst.setDouble(2, f.getValor());
            pst.setInt(3, f.getObjMissao().getCodMissao());
            pst.setInt(4, f.getCodFinanciamento());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Financiamento alterado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro: financiamento não encontrado para alteração!");
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Erro de SQL na classe FinanciamentoDAO no método alterar: " + ex.getMessage());
            return false;
        }
    }

    public boolean salvar(FinanciamentoJA f) {
        if (f.getCodFinanciamento() == null || f.getCodFinanciamento() == 0) {
            return inserir(f);
        } else {
            return alterar(f);
        }
    }

    public boolean remover(FinanciamentoJA f) {
        String mysql = "DELETE FROM financiamento WHERE codFinanciamento=?";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            pst.setInt(1, f.getCodFinanciamento());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Financiamento removido com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro: financiamento não encontrado para remoção!");
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Erro de SQL na classe FinanciamentoDAO no método remover: " + ex.getMessage());
            return false;
        }
    }
}
