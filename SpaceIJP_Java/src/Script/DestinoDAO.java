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
public class DestinoDAO {

    public List<DestinoJA> getLista() {
        String mysql = "select * from destino";
        List<DestinoJA> listaDestino = new ArrayList<>();

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                DestinoJA objDestino = new DestinoJA();
                objDestino.setCodDestino(rs.getInt("codDestino"));
                objDestino.setNomeLocal(rs.getString("nomeLocal"));
                objDestino.setDistancia(rs.getFloat("distancia"));
                objDestino.setPressao(rs.getDouble("pressao"));
                objDestino.setAceleracaoGravidade(rs.getDouble("aceleracaoGravidade"));
                objDestino.setTipo(rs.getString("tipo"));
                listaDestino.add(objDestino);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de SQL " + ex.getMessage());
        }

        return listaDestino;
    }

    public boolean inserir(DestinoJA d) {
        String mysql = "INSERT INTO destino (nomeLocal, distancia, pressao, aceleracaoGravidade, tipo) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            pst.setString(1, d.getNomeLocal());
            pst.setFloat(2, d.getDistancia());
            pst.setDouble(3, d.getPressao());
            pst.setDouble(4, d.getAceleracaoGravidade());
            pst.setString(5, d.getTipo());

            int affected = pst.executeUpdate();

            pst.close(); // FECHA SEM ITAR KEYS

            if (affected > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir destino: " + ex.getMessage());
            return false;
        }
    }

    public boolean alterar(DestinoJA objDestino) {
        String mysql = "update destino set nomeLocal = ?, distancia = ?, pressao = ?, "
                + "aceleracaoGravidade = ?, tipo = ? where codDestino = ?";

        try {
            PreparedStatement prt = ConexaoBD.getPreparableStatement(mysql);
            prt.setString(1, objDestino.getNomeLocal());
            prt.setFloat(2, objDestino.getDistancia());
            prt.setDouble(3, objDestino.getPressao());
            prt.setDouble(4, objDestino.getAceleracaoGravidade());
            prt.setString(5, objDestino.getTipo());
            prt.setInt(6, objDestino.getCodDestino());

            if (prt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Destino alterado com sucesso!");
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

    public boolean salvar(DestinoJA objDestino) {
        if (objDestino.getCodDestino() == 0) {
            return inserir(objDestino);
        } else {
            return alterar(objDestino);
        }
    }

    public boolean remover(DestinoJA objDestino) {
        String mysql = "delete from destino where codDestino = ?";

        try {
            PreparedStatement prt = ConexaoBD.getPreparableStatement(mysql);
            prt.setInt(1, objDestino.getCodDestino());

            if (prt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Destino apagado com sucesso!");
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

    public DestinoJA localizarDestino(int codigoDestino) {
        String mysql = "select * from destino where codDestino = ?";
        DestinoJA objDestino = new DestinoJA();

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            pst.setInt(1, codigoDestino);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                objDestino.setCodDestino(rs.getInt("codDestino"));
                objDestino.setNomeLocal(rs.getString("nomeLocal"));
                objDestino.setDistancia(rs.getFloat("distancia"));
                objDestino.setPressao(rs.getDouble("pressao"));
                objDestino.setAceleracaoGravidade(rs.getDouble("aceleracaoGravidade"));
                objDestino.setTipo(rs.getString("tipo"));
                return objDestino;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de SQL na classe DestinoDAO no método localizarDestino " + ex.getMessage());
        }

        return null;
    }
}
