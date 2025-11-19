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
public class SensorDAO {

    FogueteDao objFogueteDao = new FogueteDao();

    public List<SensoresJA> getLista() {
        String mysql = "SELECT * FROM sensores";
        List<SensoresJA> listaSensores = new ArrayList<>();

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                SensoresJA objSensor = new SensoresJA();

                objSensor.setCodSensores(rs.getInt("codSensores"));
                objSensor.setTipo(rs.getString("tipo"));
                objSensor.setUnidade(rs.getString("unidade"));
                objSensor.setPosition(rs.getString("position"));

                int codFoguete = rs.getInt("Foguete_codFoguete");
                objSensor.setObjFoguete(objFogueteDao.localizarFoguete(codFoguete));

                listaSensores.add(objSensor);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                "Erro de SQL na classe SensorDAO no método getLista: " + ex.getMessage());
        }

        return listaSensores;
    }

    public boolean inserir(SensoresJA s) {
        String mysql = "INSERT INTO sensores (tipo, unidade, position, Foguete_codFoguete) "
                + "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);

            pst.setString(1, s.getTipo());
            pst.setString(2, s.getUnidade());
            pst.setString(3, s.getPosition());
            pst.setInt(4, s.getObjFoguete().getCodFoguete());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Sensor cadastrado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Sensor não cadastrado!");
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                "Erro de SQL na classe SensorDAO no método inserir: " + ex.getMessage());
            return false;
        }
    }

    public boolean alterar(SensoresJA s) {
        String mysql = "UPDATE sensores SET tipo=?, unidade=?, position=?, Foguete_codFoguete=? "
                + "WHERE codSensores=?";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);

            pst.setString(1, s.getTipo());
            pst.setString(2, s.getUnidade());
            pst.setString(3, s.getPosition());
            pst.setInt(4, s.getObjFoguete().getCodFoguete());
            pst.setInt(5, s.getCodSensores());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Sensor alterado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro: sensor não encontrado para alteração!");
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                "Erro de SQL na classe SensorDAO no método alterar: " + ex.getMessage());
            return false;
        }
    }

    public boolean salvar(SensoresJA s) {
        if (s.getCodSensores() == null || s.getCodSensores() == 0) {
            return inserir(s);
        } else {
            return alterar(s);
        }
    }

    public boolean remover(SensoresJA s) {
        String mysql = "DELETE FROM sensores WHERE codSensores=?";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            pst.setInt(1, s.getCodSensores());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Sensor removido com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro: sensor não encontrado para remoção!");
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                "Erro de SQL na classe SensorDAO no método remover: " + ex.getMessage());
            return false;
        }
    }
}
