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
public class FuncionarioDAO {

    CargoDAO objCargoDAO = new CargoDAO();

    public List<FuncionarioJA> getLista() {

        String mysql = "SELECT * FROM funcionario";
        List<FuncionarioJA> listaFuncionarios = new ArrayList<>();

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                FuncionarioJA f = new FuncionarioJA();

                f.setCodFuncionario(rs.getInt("codFuncionario"));
                f.setNomeFuncionario(rs.getString("nomeFuncionario"));
                f.setCpf(rs.getString("cpf"));
                f.setSalarioAtual(rs.getDouble("salarioAtual"));
                f.setRg(rs.getString("rg"));
                f.setTelefone(rs.getString("telefone"));
                f.setCep(rs.getString("cep"));
                f.setDataNascimento(rs.getDate("dataNascimento"));
                f.setStatus(rs.getInt("status"));
                f.setEmail(rs.getString("email"));

                int codCargo = rs.getInt("cargo_codcargo");
                f.setObjCargo(objCargoDAO.localizarCargo(codCargo));

                listaFuncionarios.add(f);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                "Erro de SQL na classe FuncionarioDAO no método getLista: " + ex.getMessage());
        }

        return listaFuncionarios;
    }

    public boolean inserir(FuncionarioJA f) {
        String mysql = "INSERT INTO funcionario (nomeFuncionario, cpf, salarioAtual, rg, telefone, cep, dataNascimento, status, email, cargo_codcargo) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);

            pst.setString(1, f.getNomeFuncionario());
            pst.setString(2, f.getCpf());
            pst.setDouble(3, f.getSalarioAtual());
            pst.setString(4, f.getRg());
            pst.setString(5, f.getTelefone());
            pst.setString(6, f.getCep());
            pst.setDate(7, new java.sql.Date(f.getDataNascimento().getTime()));
            pst.setInt(8, f.getStatus());
            pst.setString(9, f.getEmail());
            pst.setInt(10, f.getObjCargo().getCodCargo());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Funcionário não cadastrado!");
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                "Erro de SQL na classe FuncionarioDAO no método inserir: " + ex.getMessage());
            return false;
        }
    }

    public boolean alterar(FuncionarioJA f) {
        String mysql = "UPDATE funcionario SET nomeFuncionario=?, cpf=?, salarioAtual=?, rg=?, telefone=?, cep=?, dataNascimento=?, status=?, email=?, cargo_codcargo=? "
                     + "WHERE codFuncionario=?";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);

            pst.setString(1, f.getNomeFuncionario());
            pst.setString(2, f.getCpf());
            pst.setDouble(3, f.getSalarioAtual());
            pst.setString(4, f.getRg());
            pst.setString(5, f.getTelefone());
            pst.setString(6, f.getCep());
            pst.setDate(7, new java.sql.Date(f.getDataNascimento().getTime()));
            pst.setInt(8, f.getStatus());
            pst.setString(9, f.getEmail());
            pst.setInt(10, f.getObjCargo().getCodCargo());
            pst.setInt(11, f.getCodFuncionario());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Funcionário alterado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro: funcionário não encontrado para alteração!");
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                "Erro de SQL na classe FuncionarioDAO no método alterar: " + ex.getMessage());
            return false;
        }
    }

    public boolean salvar(FuncionarioJA f) {
        if (f.getCodFuncionario() == null || f.getCodFuncionario() == 0) {
            return inserir(f);
        } else {
            return alterar(f);
        }
    }

    public boolean remover(FuncionarioJA f) {
        String mysql = "DELETE FROM funcionario WHERE codFuncionario=?";

        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            pst.setInt(1, f.getCodFuncionario());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Funcionário removido com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro: funcionário não encontrado para remoção!");
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                "Erro de SQL na classe FuncionarioDAO no método remover: " + ex.getMessage());
            return false;
        }
    }
}
