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

public class BaseDAO {

    public boolean inserir(BaseJA b) {
        String sql = "INSERT INTO baselancamento (nomebase, paisbase, precoConstrucao) VALUES (?, ?, ?)";
        try (PreparedStatement pst = ConexaoBD.getPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, b.getNomeBase());
            pst.setString(2, b.getPaisBase());
            pst.setDouble(3, b.getPrecoConstrucao());

            if (pst.executeUpdate() > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    b.setCodBaseLancamento(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir base de lançamento: " + e.getMessage());
        }
        return false;
    }

    public boolean alterar(BaseJA b) {
        String sql = "UPDATE baselancamento SET nomebase=?, paisbase=?, precoConstrucao=? WHERE codbaseLancamento=?";
        try (PreparedStatement pst = ConexaoBD.getPreparedStatement(sql)) {
            pst.setString(1, b.getNomeBase());
            pst.setString(2, b.getPaisBase());
            pst.setDouble(3, b.getPrecoConstrucao());
            pst.setInt(4, b.getCodBaseLancamento());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar base de lançamento: " + e.getMessage());
        }
        return false;
    }

    public boolean remover(int cod) {
        String sql = "DELETE FROM baselancamento WHERE codbaseLancamento=?";
        try (PreparedStatement pst = ConexaoBD.getPreparedStatement(sql)) {
            pst.setInt(1, cod);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover base de lançamento: " + e.getMessage());
        }
        return false;
    }

    public List<BaseJA> getLista() {
        List<BaseJA> lista = new ArrayList<>();
        String sql = "SELECT * FROM baselancamento";
        try (PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                BaseJA b = new BaseJA();
                b.setCodBaseLancamento(rs.getInt("codbaseLancamento"));
                b.setNomeBase(rs.getString("nomebase"));
                b.setPaisBase(rs.getString("paisbase"));
                b.setPrecoConstrucao(rs.getDouble("precoConstrucao"));
                lista.add(b);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar bases de lançamento: " + e.getMessage());
        }
        return lista;
    }
}
