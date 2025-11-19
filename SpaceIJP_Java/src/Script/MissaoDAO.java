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
public class MissaoDAO {

    DestinoDAO objDestinoDao = new DestinoDAO();

    public List<MissaoJA> getLista() {
        String sql = "SELECT * FROM missoes";
        List<MissaoJA> lista = new ArrayList<>();

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                MissaoJA m = new MissaoJA();

                m.setCodMissao(rs.getInt("codMissao"));
                m.setNomeMissao(rs.getString("nomeMissao"));
                m.setObjetivoMissao(rs.getString("objetivoMissao"));
                m.setDataInicio(rs.getDate("dataInicio"));
                m.setDataFim(rs.getDate("dataFim"));
                m.setStatus(rs.getString("status"));

                int codDestino = rs.getInt("Destino_codDestino");
                m.setObjDestino(objDestinoDao.localizarDestino(codDestino));

                lista.add(m);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro de SQL no método getLista da classe MissaoDAO: " + e.getMessage());
        }

        return lista;
    }

    public boolean inserir(MissaoJA m) {
        String sql = "INSERT INTO missoes (nomeMissao, objetivoMissao, dataInicio, dataFim, status, Destino_codDestino) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(sql);

            pst.setString(1, m.getNomeMissao());
            pst.setString(2, m.getObjetivoMissao());
            pst.setDate(3, new java.sql.Date(m.getDataInicio().getTime()));
            pst.setDate(4, new java.sql.Date(m.getDataFim().getTime()));
            pst.setString(5, m.getStatus());
            pst.setInt(6, m.getObjDestino().getCodDestino());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Missão cadastrada com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Missão não cadastrada!");
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro de SQL no método inserir da classe MissaoDAO: " + e.getMessage());
            return false;
        }
    }

    public boolean alterar(MissaoJA m) {

        String sql = "UPDATE missoes SET nomeMissao=?, objetivoMissao=?, dataInicio=?, dataFim=?, status=?, Destino_codDestino=? "
                   + "WHERE codMissao=?";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(sql);

            pst.setString(1, m.getNomeMissao());
            pst.setString(2, m.getObjetivoMissao());
            pst.setDate(3, new java.sql.Date(m.getDataInicio().getTime()));
            pst.setDate(4, new java.sql.Date(m.getDataFim().getTime()));
            pst.setString(5, m.getStatus());
            pst.setInt(6, m.getObjDestino().getCodDestino());
            pst.setInt(7, m.getCodMissao());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Missão alterada com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro: missão não encontrada!");
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro de SQL no método alterar da classe MissaoDAO: " + e.getMessage());
            return false;
        }
    }

    public boolean salvar(MissaoJA m) {
        if (m.getCodMissao() == null || m.getCodMissao() == 0) {
            return inserir(m);
        } else {
            return alterar(m);
        }
    }

    public boolean remover(MissaoJA m) {
        String sql = "DELETE FROM missoes WHERE codMissao=?";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(sql);
            pst.setInt(1, m.getCodMissao());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Missão removida com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro: missão não encontrada!");
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro de SQL no método remover da classe MissaoDAO: " + e.getMessage());
            return false;
        }
    }
}
