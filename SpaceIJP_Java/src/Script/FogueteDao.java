package Script;

// imports necessários
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FogueteDao {

    // INSERT que pega chave gerada
    public boolean inserir(FogueteJA objFoguete) {
        String sql = "INSERT INTO Foguete(nomeFoguete, maximoCombustivel, quantCombustivel, velocidade, status) VALUES (?, ?, ?, ?, ?)";
        try {
            // Preferível: obter Connection e criar PreparedStatement com RETURN_GENERATED_KEYS
            Connection conn = ConexaoBD.getConnection(); // ajuste se seu ConexaoBD tiver outro nome
            PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, objFoguete.getNomeFoguete());
            pst.setFloat(2, objFoguete.getMaximoCombustivel());
            pst.setFloat(3, objFoguete.getQuantCombustivel()); // normalmente 0 no cadastro
            pst.setDouble(4, objFoguete.getVelocidade());
            pst.setInt(5, objFoguete.getStatus()); // normalmente 0 no cadastro

            int linhas = pst.executeUpdate();
            if (linhas > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        objFoguete.setCodFoguete(rs.getInt(1)); // atualiza o objeto com o id do BD
                    }
                }
                pst.close();
                JOptionPane.showMessageDialog(null, "Foguete cadastrado com sucesso!");
                return true;
            } else {
                pst.close();
                JOptionPane.showMessageDialog(null, "Foguete não cadastrado!");
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de SQL ao inserir: " + ex.getMessage());
            return false;
        }
    }

    // UPDATE — atualiza apenas os campos editáveis (não altera quantCombustivel nem status)
    public boolean alterar(FogueteJA objFoguete) {
        String sql = "UPDATE Foguete SET nomeFoguete=?, maximoCombustivel=?, velocidade=? WHERE codFoguete=?";
        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
            pst.setString(1, objFoguete.getNomeFoguete());
            pst.setFloat(2, objFoguete.getMaximoCombustivel());
            pst.setDouble(3, objFoguete.getVelocidade());
            pst.setInt(4, objFoguete.getCodFoguete());

            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Foguete alterado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Foguete não alterado!");
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de SQL ao alterar: " + ex.getMessage());
            return false;
        }
    }

    // DELETE por codigo
    public boolean deletar(int codFoguete) {
        String sql = "DELETE FROM Foguete WHERE codFoguete = ?";
        try {
            PreparedStatement pst = ConexaoBD.getPreparedStatement(sql);
            pst.setInt(1, codFoguete);
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Foguete deletado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Foguete não encontrado!");
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de SQL ao deletar: " + ex.getMessage());
            return false;
        }
    }

    // getLista (exemplo)
    public List<FogueteJA> getLista() {
        String mysql = "SELECT * FROM Foguete";
        List<FogueteJA> listaFoguete = new ArrayList<>();
        try {
            PreparedStatement pst = ConexaoBD.getPreparableStatement(mysql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                FogueteJA obj = new FogueteJA();
                obj.setCodFoguete(rs.getInt("codFoguete"));
                obj.setNomeFoguete(rs.getString("nomeFoguete"));
                obj.setMaximoCombustivel(rs.getFloat("maximoCombustivel"));
                obj.setQuantCombustivel(rs.getFloat("quantCombustivel"));
                obj.setVelocidade(rs.getDouble("velocidade"));
                obj.setStatus(rs.getInt("status"));
                listaFoguete.add(obj);
            }
            rs.close();
            // pst.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de SQL (getLista): " + ex.getMessage());
        }
        return listaFoguete;
    }
}
