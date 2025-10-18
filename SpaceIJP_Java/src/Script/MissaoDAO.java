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
 * @author Iagod
 */

public class MissaoDAO {

    public boolean inserir(MissaoJA m) {
        String sql = "INSERT INTO missoes (nomeMissao, objetivoMissao, dataInicio, dataFim, status, Destino_codDestino) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = ConexaoBD.getPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, m.getNomeMissao());
            pst.setString(2, m.getObjetivoMissao());
            pst.setDate(3, m.getDataInicio() != null ? new java.sql.Date(m.getDataInicio().getTime()) : null);
            pst.setDate(4, m.getDataFim() != null ? new java.sql.Date(m.getDataFim().getTime()) : null);
            pst.setString(5, m.getStatus());
            pst.setInt(6, m.getDestinoCodDestino());

            if (pst.executeUpdate() > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    m.setCodMissao(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir missão: " + e.getMessage());
        }
        return false;
    }

    public boolean alterar(MissaoJA m) {
        String sql = "UPDATE missoes SET nomeMissao=?, objetivoMissao=?, dataInicio=?, dataFim=?, status=?, Destino_codDestino=? "
                   + "WHERE codMissao=?";
        try (PreparedStatement pst = ConexaoBD.getPreparedStatement(sql)) {
            pst.setString(1, m.getNomeMissao());
            pst.setString(2, m.getObjetivoMissao());
            pst.setDate(3, m.getDataInicio() != null ? new java.sql.Date(m.getDataInicio().getTime()) : null);
            pst.setDate(4, m.getDataFim() != null ? new java.sql.Date(m.getDataFim().getTime()) : null);
            pst.setString(5, m.getStatus());
            pst.setInt(6, m.getDestinoCodDestino());
            pst.setInt(7, m.getCodMissao());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar missão: " + e.getMessage());
        }
        return false;
    }

    public boolean remover(int cod) {
        String sql = "DELETE FROM missoes WHERE codMissao=?";
        try (PreparedStatement pst = ConexaoBD.getPreparedStatement(sql)) {
            pst.setInt(1, cod);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover missão: " + e.getMessage());
        }
        return false;
    }

    public List<MissaoJA> getLista() {
        List<MissaoJA> lista = new ArrayList<>();
        String sql = "SELECT m.*, d.nomeLocal FROM missoes m "
                   + "INNER JOIN destino d ON m.Destino_codDestino = d.codDestino";

        try (PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                MissaoJA m = new MissaoJA();
                m.setCodMissao(rs.getInt("codMissao"));
                m.setNomeMissao(rs.getString("nomeMissao"));
                m.setObjetivoMissao(rs.getString("objetivoMissao"));
                m.setDataInicio(rs.getDate("dataInicio"));
                m.setDataFim(rs.getDate("dataFim"));
                m.setStatus(rs.getString("status"));
                m.setDestinoCodDestino(rs.getInt("Destino_codDestino"));
                m.setDestinoNome(rs.getString("nomeLocal"));
                lista.add(m);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar missões: " + e.getMessage());
        }
        return lista;
    }
}

