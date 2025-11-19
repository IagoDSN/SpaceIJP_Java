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
public class LancamentosDAO {

    FogueteDao objFogueteDao = new FogueteDao();
    MissaoDAO objMissaoDao = new MissaoDAO();

    public List<LancamentosJA> getLista() {
        String mysql = "SELECT * FROM lancamentos";
        List<LancamentosJA> listaLancamentos = new ArrayList<>();

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                LancamentosJA objLan = new LancamentosJA();

                objLan.setCodLancamento(rs.getInt("codLancamentos"));
                objLan.setDataLancamento(rs.getDate("dataLancamento"));
                objLan.setResultado(rs.getString("resultado"));

                int codFoguete = rs.getInt("Foguete_codFoguete");
                objLan.setObjFoguete(objFogueteDao.localizarFoguete(codFoguete));

                int codMissao = rs.getInt("Missoes_codMissao");
                objLan.setObjMissao(objMissaoDao.localizarMissao(codMissao));

                listaLancamentos.add(objLan);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                "Erro de SQL na classe LancamentosDAO no método getLista: " + ex.getMessage());
        }

        return listaLancamentos;
    }

    public boolean inserir(LancamentosJA l) {
        String mysql = "INSERT INTO lancamentos (dataLancamento, resultado, "
                + "Foguete_codFoguete, Missoes_codMissao) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);

            pst.setDate(1, new java.sql.Date(l.getDataLancamento().getTime()));
            pst.setString(2, l.getResultado());
            pst.setInt(3, l.getObjFoguete().getCodFoguete());
            pst.setInt(4, l.getObjMissao().getCodMissao());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Lançamento cadastrado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Lançamento não cadastrado!");
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                "Erro de SQL na classe LancamentosDAO no método inserir: " + ex.getMessage());
            return false;
        }
    }

    public boolean alterar(LancamentosJA l) {
        String mysql = "UPDATE lancamentos SET dataLancamento=?, resultado=?, "
                + "Foguete_codFoguete=?, Missoes_codMissao=? WHERE codLancamentos=?";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);

            pst.setDate(1, new java.sql.Date(l.getDataLancamento().getTime()));
            pst.setString(2, l.getResultado());
            pst.setInt(3, l.getObjFoguete().getCodFoguete());
            pst.setInt(4, l.getObjMissao().getCodMissao());
            pst.setInt(5, l.getCodLancamento());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Lançamento alterado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro: lançamento não encontrado para alteração!");
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                "Erro de SQL na classe LancamentosDAO no método alterar: " + ex.getMessage());
            return false;
        }
    }

    public boolean salvar(LancamentosJA l) {
        if (l.getCodLancamento() == null || l.getCodLancamento() == 0) {
            return inserir(l);
        } else {
            return alterar(l);
        }
    }

    public boolean remover(LancamentosJA l) {
        String mysql = "DELETE FROM lancamentos WHERE codLancamentos=?";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            pst.setInt(1, l.getCodLancamento());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Lançamento removido com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro: lançamento não encontrado para remoção!");
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                "Erro de SQL na classe LancamentosDAO no método remover: " + ex.getMessage());
            return false;
        }
    }
}
