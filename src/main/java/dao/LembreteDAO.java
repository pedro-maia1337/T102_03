package dao;

//Classe DAO de Lembrete para manipulação baseada na UsuarioDAO 

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Lembrete;

public class LembreteDAO extends DAO {

    public LembreteDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }
    
    public boolean insert(Lembrete lembrete) {
        boolean status = false;
        String sql = "INSERT INTO lembrete (codigo, titulo, descricao, valor, data_lembrete) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, lembrete.getCodigo());
            pst.setString(2, lembrete.getTitulo());
            pst.setString(3, lembrete.getDescricao());
            pst.setDouble(4, lembrete.getValor());
            pst.setString(5, lembrete.getDataLembrete());
            pst.executeUpdate();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public Lembrete get(int codigo) {
        Lembrete lembrete = null;
        String sql = "SELECT * FROM lembrete WHERE codigo = ?";
        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                lembrete = new Lembrete(
                    rs.getInt("codigo"),
                    rs.getString("titulo"),
                    rs.getString("descricao"),
                    rs.getDouble("valor"),
                    rs.getString("data_lembrete")
                );
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return lembrete;
    }

    public List<Lembrete> get() {
        return get("");
    }
    
    public List<Lembrete> getOrderByTitulo() {
        return get("titulo");
    }
    
    public List<Lembrete> getOrderByValor() {
        return get("valor");
    }
    
    public List<Lembrete> getOrderByData() {
        return get("data_lembrete");
    }
    
    private List<Lembrete> get(String orderBy) {
        List<Lembrete> lembretes = new ArrayList<>();
        String sql = "SELECT * FROM lembrete" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
        try (Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Lembrete l = new Lembrete(
                    rs.getInt("codigo"),
                    rs.getString("titulo"),
                    rs.getString("descricao"),
                    rs.getDouble("valor"),
                    rs.getString("data_lembrete")
                );
                lembretes.add(l);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return lembretes;
    }
    
    public boolean update(Lembrete lembrete) {
        boolean status = false;
        String sql = "UPDATE lembrete SET titulo = ?, descricao = ?, valor = ?, data_lembrete = ? WHERE codigo = ?";
        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setString(1, lembrete.getTitulo());
            pst.setString(2, lembrete.getDescricao());
            pst.setDouble(3, lembrete.getValor());
            pst.setString(4, lembrete.getDataLembrete());
            pst.setInt(5, lembrete.getCodigo());
            pst.executeUpdate();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
    
    public boolean delete(int codigo) {
        boolean status = false;
        String sql = "DELETE FROM lembrete WHERE codigo = ?";
        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, codigo);
            pst.executeUpdate();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
}