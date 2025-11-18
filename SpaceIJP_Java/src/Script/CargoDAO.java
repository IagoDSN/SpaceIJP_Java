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
public class CargoDAO {

    public List<CargoJA> getLista() {
        String mysql = "select * from cargo";
        List<CargoJA> listaCargo = new ArrayList<>();

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                CargoJA objCargo = new CargoJA();
                objCargo.setCodCargo(rs.getInt("codcargo"));
                objCargo.setNomeCargo(rs.getString("nomeCargo"));
                objCargo.setSalarioInicial(rs.getFloat("salarioInicial"));
                listaCargo.add(objCargo);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de SQL " + ex.getMessage());
        }

        return listaCargo;
    }

    public boolean inserir(CargoJA c) {
        String mysql = "INSERT INTO cargo (nomeCargo, salarioInicial) VALUES (?, ?)";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            pst.setString(1, c.getNomeCargo());
            pst.setFloat(2, c.getSalarioInicial());

            int affected = pst.executeUpdate();
            if (affected > 0) {
                pst.close();
                return true;
            } else {
                pst.close();
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir cargo: " + ex.getMessage());
            return false;
        }
    }

    public boolean alterar(CargoJA c) {
        String mysql = "update cargo set nomeCargo = ?, salarioInicial = ? where codcargo = ?";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            pst.setString(1, c.getNomeCargo());
            pst.setFloat(2, c.getSalarioInicial());
            pst.setInt(3, c.getCodCargo());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cargo alterado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro: chave não encontrada!");
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em MySQL " + ex.getMessage());
            return false;
        }
    }

    public boolean salvar(CargoJA objCargo) {
        if (objCargo.getCodCargo() == 0) {
            return inserir(objCargo);
        } else {
            return alterar(objCargo);
        }
    }

    public boolean remover(CargoJA objCargo) {
        String mysql = "delete from cargo where codcargo = ?";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            pst.setInt(1, objCargo.getCodCargo());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cargo removido com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro: chave não encontrada!");
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em MySQL " + ex.getMessage());
            return false;
        }
    }

    public CargoJA localizarCargo(int codCargo) {
        String mysql = "select * from cargo where codcargo = ?";
        CargoJA objCargo = new CargoJA();

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            pst.setInt(1, codCargo);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                objCargo.setCodCargo(rs.getInt("codcargo"));
                objCargo.setNomeCargo(rs.getString("nomeCargo"));
                objCargo.setSalarioInicial(rs.getFloat("salarioInicial"));
                return objCargo;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método localizarCargo " + ex.getMessage());
        }

        return null;
    }
}
