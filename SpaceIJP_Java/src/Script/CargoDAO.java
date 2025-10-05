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
public class CargoDAO {

    public List<CargoJA> getLista() {
        List<CargoJA> lista = new ArrayList<>();
        String sql = "SELECT * FROM cargo";

        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                CargoJA c = new CargoJA();
                c.setCodCargo(rs.getInt("codcargo"));
                c.setNomeCargo(rs.getString("nomeCargo"));
                c.setSalarioInicial(rs.getFloat("salarioInicial"));
                lista.add(c);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar cargos: " + e.getMessage());
        }

        return lista;
    }

    public boolean inserir(CargoJA c) {
        String sql = "INSERT INTO cargo (nomeCargo, salarioInicial) VALUES (?, ?)";
        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, c.getNomeCargo());
            pst.setFloat(2, c.getSalarioInicial());

            if (pst.executeUpdate() > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    c.setCodCargo(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir cargo: " + e.getMessage());
        }
        return false;
    }

    public boolean alterar(CargoJA c) {
        String sql = "UPDATE cargo SET nomeCargo=?, salarioInicial=? WHERE codcargo=?";
        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
            pst.setString(1, c.getNomeCargo());
            pst.setFloat(2, c.getSalarioInicial());
            pst.setInt(3, c.getCodCargo());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar cargo: " + e.getMessage());
        }
        return false;
    }

    public static boolean remover(int codCargo) {
    String sql = "DELETE FROM cargo WHERE codcargo = ?";
        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
            pst.setInt(1, codCargo);
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cargo deletado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Cargo não encontrado!");
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de SQL ao deletar: " + ex.getMessage());
            return false;
        }
    }
    // Chave Estrangeira
    public List<String> listarNomesCargos() {
        List<String> nomes = new ArrayList<>();
        String sql = "SELECT nomeCargo FROM cargo ORDER BY nomeCargo";

        try (Connection conn = ConexaoBD.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                nomes.add(rs.getString("nomeCargo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nomes;
    }

    public Integer buscarCodPorNome(String nomeFoguete) {
        String sql = "SELECT codCargo FROM cargo WHERE nomeCargo = ?";
        try (Connection conn = ConexaoBD.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nomeFoguete);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("codCargo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
