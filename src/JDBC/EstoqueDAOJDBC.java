package JDBC;

import Interfaces.EstoqueDAO;
import Models.Estoque;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAOJDBC implements EstoqueDAO {
    private final Connection conn;
    public EstoqueDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    public void adicionarEstoque(Estoque estoque) {
        String sql = "INSERT INTO estoque (produto_id, quantidade, " +
                "tipo_estoque, data_recebimento)" +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, estoque.getId_produto());
            stmt.setInt(2, estoque.getQuantidade());
            stmt.setString(3, estoque.getTipo_estoque());

            if (estoque.getData_recebimento() != null) {
                stmt.setDate(4, new Date(estoque.getData_recebimento().getTime()));
            } else {
                stmt.setNull(4, Types.DATE);
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Remover estoque
    public void removerEstoque(int id) {
        String sql = "DELETE FROM estoque WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Atualizar estoque
    public void atualizarEstoque(Estoque estoque) {
        String sql = "UPDATE estoque SET id_produto = ?, quantidade = ?," +
                "tipo_estoque = ?, data_recebimento = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, estoque.getId_produto());
            stmt.setInt(2, estoque.getQuantidade());
            stmt.setString(3, estoque.getTipo_estoque());

            if(estoque.getData_recebimento() != null) {
                stmt.setDate(4, new Date(estoque.getData_recebimento().getTime()));
            } else {
                stmt.setNull(4, Types.DATE);
            }

            stmt.setInt(5, estoque.getId());
            stmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // Buscar estoque por id
    public Estoque buscarEstoquePorID(int id) {
        String sql = "SELECT * FROM estoque WHERE produto_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Estoque e = new Estoque();
                e.setId(rs.getInt("id"));
                e.setId_produto(rs.getInt("produto_id"));
                e.setQuantidade(rs.getInt("quantidade"));
                e.setTipo_estoque(rs.getString("tipo_estoque"));
                e.setData_recebimento(rs.getDate("data_recebimento"));

                System.out.println("--- ESTOQUE ---");
                System.out.println("ID: " + e.getId());
                System.out.println("ID_produto: " + e.getId_produto());
                System.out.println("Quantidade: " + e.getQuantidade());
                System.out.println("Tipo_estoque: " + e.getTipo_estoque());
                System.out.println("Data_recebimento: " + e.getData_recebimento());

                return e;
            } else {
                System.out.println("Produto não encontrado no estoque!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // Listar todos os estoques
    @Override
    public List<Estoque> listarTodosEstoques() {
        String sql = "SELECT * FROM estoque";
        List<Estoque> lista = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Estoque e = new Estoque();
                e.setId(rs.getInt("id"));
                e.setId_produto(rs.getInt("id_produto"));
                e.setQuantidade(rs.getInt("quantidade"));
                e.setTipo_estoque(rs.getString("tipo_estoque"));
                e.setData_recebimento(rs.getDate("data_recebimento"));
                lista.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }



}
