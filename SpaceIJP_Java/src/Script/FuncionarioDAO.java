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

public class FuncionarioDAO {

    public boolean inserir(FuncionarioJA f) {
        String sql = "INSERT INTO funcionario (nomeFuncionario, cpf, salarioAtual, rg, telefone, cep, dataNascimento, status, email, cargo_codcargo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, f.getNomeFuncionario());
            pst.setString(2, f.getCpf());
            pst.setDouble(3, f.getSalarioAtual());
            pst.setString(4, f.getRg());
            pst.setString(5, f.getTelefone());
            pst.setString(6, f.getCep());
            pst.setDate(7, new java.sql.Date(f.getDataNascimento().getTime()));
            pst.setInt(8, f.getStatus());
            pst.setString(9, f.getEmail());
            pst.setInt(10, f.getCargoCodCargo());

            if (pst.executeUpdate() > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    f.setCodFuncionario(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir funcionário: " + e.getMessage());
        }
        return false;
    }

    public boolean alterar(FuncionarioJA f) {
        String sql = "UPDATE funcionario SET nomeFuncionario=?, cpf=?, salarioAtual=?, rg=?, telefone=?, cep=?, dataNascimento=?, status=?, email=?, cargo_codcargo=? WHERE codFuncionario=?";
        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
            pst.setString(1, f.getNomeFuncionario());
            pst.setString(2, f.getCpf());
            pst.setDouble(3, f.getSalarioAtual());
            pst.setString(4, f.getRg());
            pst.setString(5, f.getTelefone());
            pst.setString(6, f.getCep());
            pst.setDate(7, new java.sql.Date(f.getDataNascimento().getTime()));
            pst.setInt(8, f.getStatus());
            pst.setString(9, f.getEmail());
            pst.setInt(10, f.getCargoCodCargo());
            pst.setInt(11, f.getCodFuncionario());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar funcionário: " + e.getMessage());
        }
        return false;
    }

    public boolean remover(FuncionarioJA f) {
        String sql = "DELETE FROM funcionario WHERE codFuncionario=?";
        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
            pst.setInt(1, f.getCodFuncionario());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover funcionário: " + e.getMessage());
        }
        return true;
    }

    public List<FuncionarioJA> getLista() {
        List<FuncionarioJA> lista = new ArrayList<>();
        String sql = "SELECT func.*, c.nomeCargo FROM funcionario func " +
                     "INNER JOIN cargo c ON func.cargo_codcargo = c.codcargo";

        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
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
                f.setCargoCodCargo(rs.getInt("cargo_codcargo"));
                f.setCargoNome(rs.getString("nomeCargo"));
                lista.add(f);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar funcionários: " + e.getMessage());
        }

        return lista;
    }
}

