package JDBC;

import Interfaces.ProdutoDAO;
import Models.Produto;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAOJDBC {
    private final Connection conn;

    public ProdutoDAOJDBC(Connection conn) { this.conn = conn; }

    public void inserir(Produto produto) {
        String sql = "INSERT INTO produto (nome, descricao, categoria, validade, status, preco) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setString(3, produto.getCategoria());
            // Se produto.getValidade() for null, pode usar setNull:
            if (produto.getValidade() != null) {
                stmt.setDate(4, produto.getValidade());
            } else {
                stmt.setNull(4, java.sql.Types.DATE);
            }
            stmt.setString(5, produto.getStatus());
            stmt.setDouble(6, produto.getPreco());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM produto WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setCategoria(rs.getString("categoria"));
                p.setValidade(rs.getDate("validade"));
                p.setStatus(rs.getString("status"));
                p.setPreco(rs.getDouble("preco"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Produto produto) {
        StringBuilder sql = new StringBuilder("UPDATE produto SET ");
        List<Object> params = new ArrayList<>();

        // Em produto, podemos atualizar apenas o preco dele.
        // ...
    }


}
