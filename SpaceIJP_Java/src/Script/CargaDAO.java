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
public class CargaDAO {

    FogueteDao objFogueteDao = new FogueteDao();

    public List<CargaJA> getLista() {
        String mysql = "SELECT * FROM carga";
        List<CargaJA> listaCargas = new ArrayList<>();

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                CargaJA objCarga = new CargaJA();

                objCarga.setCodCarga(rs.getInt("codCarga"));
                objCarga.setTipo(rs.getString("tipo"));
                objCarga.setQuantidade(rs.getInt("quant"));
                objCarga.setPeso(rs.getDouble("peso"));
                objCarga.setDescricao(rs.getString("descricao"));

                int codFoguete = rs.getInt("Foguete_codFoguete");
                objCarga.setObjFoguete(objFogueteDao.localizarFoguete(codFoguete));

                listaCargas.add(objCarga);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Erro de SQL na classe CargaDAO no método getLista: " + ex.getMessage());
        }

        return listaCargas;
    }

    public boolean inserir(CargaJA c) {
        String mysql = "INSERT INTO carga (tipo, quant, peso, descricao, Foguete_codFoguete) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);

            pst.setString(1, c.getTipo());
            pst.setInt(2, c.getQuantidade());
            pst.setDouble(3, c.getPeso());
            pst.setString(4, c.getDescricao());
            pst.setInt(5, c.getObjFoguete().getCodFoguete());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Carga cadastrada com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Carga não cadastrada!");
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Erro de SQL na classe CargaDAO no método inserir: " + ex.getMessage());
            return false;
        }
    }

    public boolean alterar(CargaJA c) {
        String mysql = "UPDATE carga SET tipo=?, quant=?, peso=?, descricao=?, Foguete_codFoguete=? "
                + "WHERE codCarga=?";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);

            pst.setString(1, c.getTipo());
            pst.setInt(2, c.getQuantidade());
            pst.setDouble(3, c.getPeso());
            pst.setString(4, c.getDescricao());
            pst.setInt(5, c.getObjFoguete().getCodFoguete());
            pst.setInt(6, c.getCodCarga());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Carga alterada com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro: carga não encontrada para alteração!");
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Erro de SQL na classe CargaDAO no método alterar: " + ex.getMessage());
            return false;
        }
    }

    public boolean salvar(CargaJA c) {
        if (c.getCodCarga() == null || c.getCodCarga() == 0) {
            return inserir(c);
        } else {
            return alterar(c);
        }
    }

    public boolean remover(CargaJA c) {
        String mysql = "DELETE FROM carga WHERE codCarga=?";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            pst.setInt(1, c.getCodCarga());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Carga removida com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro: carga não encontrada para remoção!");
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Erro de SQL na classe CargaDAO no método remover: " + ex.getMessage());
            return false;
        }
    }
}
