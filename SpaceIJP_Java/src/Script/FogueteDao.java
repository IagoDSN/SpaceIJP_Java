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
public class FogueteDao {

    public List<FogueteJA> getLista() {
        String mysql = "select * from foguete";
        List<FogueteJA> listaFoguete = new ArrayList<>();

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                FogueteJA objFoguete = new FogueteJA();
                objFoguete.setCodFoguete(rs.getInt("codFoguete"));
                objFoguete.setNomeFoguete(rs.getString("nomeFoguete"));
                objFoguete.setMaximoCombustivel(rs.getFloat("maximoCombustivel"));
                objFoguete.setQuantCombustivel(rs.getFloat("quantCombustivel"));
                objFoguete.setVelocidade(rs.getDouble("velocidade"));
                objFoguete.setStatus(rs.getInt("status"));
                listaFoguete.add(objFoguete);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de SQL " + ex.getMessage());
        }

        return listaFoguete;
    }

    public boolean inserir(FogueteJA f) {
        String mysql = "INSERT INTO foguete (nomeFoguete, maximoCombustivel, quantCombustivel, velocidade, status) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);

            pst.setString(1, f.getNomeFoguete());
            pst.setFloat(2, f.getMaximoCombustivel());
            pst.setFloat(3, f.getQuantCombustivel());
            pst.setDouble(4, f.getVelocidade());
            pst.setInt(5, f.getStatus());

            int affected = pst.executeUpdate();
            pst.close();

            return affected > 0;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir foguete: " + ex.getMessage());
            return false;
        }
    }

    public boolean alterar(FogueteJA f) {
        String mysql = "update foguete set nomeFoguete = ?, maximoCombustivel = ?, quantCombustivel = ?, velocidade = ?, status = ? "
                + "where codFoguete = ?";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            pst.setString(1, f.getNomeFoguete());
            pst.setFloat(2, f.getMaximoCombustivel());
            pst.setFloat(3, f.getQuantCombustivel());
            pst.setDouble(4, f.getVelocidade());
            pst.setInt(5, f.getStatus());
            pst.setInt(6, f.getCodFoguete());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Foguete alterado com sucesso!");
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

    public boolean salvar(FogueteJA objFoguete) {
        if (objFoguete.getCodFoguete() == 0) {
            return inserir(objFoguete);
        } else {
            return alterar(objFoguete);
        }
    }

    public boolean remover(FogueteJA objFoguete) {
        String mysql = "delete from foguete where codFoguete = ?";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            pst.setInt(1, objFoguete.getCodFoguete());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Foguete apagado com sucesso!");
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

    public FogueteJA localizarFoguete(int codigoFoguete) {
        String mysql = "select * from foguete where codFoguete = ?";
        FogueteJA objFoguete = new FogueteJA();

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            pst.setInt(1, codigoFoguete);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                objFoguete.setCodFoguete(rs.getInt("codFoguete"));
                objFoguete.setNomeFoguete(rs.getString("nomeFoguete"));
                objFoguete.setMaximoCombustivel(rs.getFloat("maximoCombustivel"));
                objFoguete.setQuantCombustivel(rs.getFloat("quantCombustivel"));
                objFoguete.setVelocidade(rs.getDouble("velocidade"));
                objFoguete.setStatus(rs.getInt("status"));
                return objFoguete;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método localizarFoguete " + ex.getMessage());
        }

        return null;
    }

}
