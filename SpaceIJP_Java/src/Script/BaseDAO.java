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

    public List<BaseJA> getLista() {
        String mysql = "select * from baselancamento";
        List<BaseJA> listaBase = new ArrayList<>();

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                BaseJA objBase = new BaseJA();
                objBase.setCodBaseLancamento(rs.getInt("codbaseLancamento"));
                objBase.setNomeBase(rs.getString("nomebase"));
                objBase.setPaisBase(rs.getString("paisbase"));
                objBase.setPrecoConstrucao(rs.getDouble("precoConstrucao"));
                listaBase.add(objBase);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de SQL " + ex.getMessage());
        }

        return listaBase;
    }

    public boolean inserir(BaseJA b) {
    String mysql = "INSERT INTO baselancamento (nomebase, paisbase, precoConstrucao) VALUES (?, ?, ?)";
    try {
        PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
        pst.setString(1, b.getNomeBase());
        pst.setString(2, b.getPaisBase());
        pst.setDouble(3, b.getPrecoConstrucao());

        int affected = pst.executeUpdate();
        if (affected > 0) {
            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    b.setCodBaseLancamento(rs.getInt(1));
                }
            }
            pst.close();
            return true;
        } else {
            pst.close();
            return false;
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao inserir base: " + ex.getMessage());
        return false;
    }
}

    public boolean alterar(BaseJA objBase) {
        String mysql = "update baselancamento set nomebase = ?, paisbase = ?, precoConstrucao = ? where codbaseLancamento = ?";

        try {
            PreparedStatement prt = ConexaoBD.getPreparableStatement(mysql);
            prt.setString(1, objBase.getNomeBase());
            prt.setString(2, objBase.getPaisBase());
            prt.setDouble(3, objBase.getPrecoConstrucao());
            prt.setInt(4, objBase.getCodBaseLancamento());

            if (prt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Base de lançamento alterada com sucesso!");
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

    public boolean salvar(BaseJA objBase) {
        if (objBase.getCodBaseLancamento() == 0) {
            return inserir(objBase);
        } else {
            return alterar(objBase);
        }
    }

    public boolean remover(BaseJA objBase) {
        String mysql = "delete from baselancamento where codbaseLancamento = ?";

        try {
            PreparedStatement prt = ConexaoBD.getPreparableStatement(mysql);
            prt.setInt(1, objBase.getCodBaseLancamento());

            if (prt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Base de lançamento apagada com sucesso!");
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

    public BaseJA localizarBase(int codigoBase) {
        String mysql = "select * from baselancamento where codbaseLancamento = ?";
        BaseJA objBase = new BaseJA();

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            pst.setInt(1, codigoBase);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                objBase.setCodBaseLancamento(rs.getInt("codbaseLancamento"));
                objBase.setNomeBase(rs.getString("nomebase"));
                objBase.setPaisBase(rs.getString("paisbase"));
                objBase.setPrecoConstrucao(rs.getDouble("precoConstrucao"));
                return objBase;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de SQL na classe BaseDAO no método localizarBase " + ex.getMessage());
        }

        return null;
    }
}
