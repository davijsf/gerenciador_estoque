package JDBC;

import Interfaces.FornecedorDAO;
import Models.Fornecedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAOJDBC implements FornecedorDAO {
    private final Connection connection;

    public FornecedorDAOJDBC(Connection connection) {
        this.connection = connection;
    }

    
    public void adicionarFornecedor(Fornecedor fornecedor) {
        String sql = "INSERT INTO Fornecedor (id, nome, cnpj, telefone) VALUES (?, ?, ?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, fornecedor.getId());
        stmt.setString(2, fornecedor.getNome());
        stmt.setString(3, fornecedor.getCnpj());
        stmt.setString(4, fornecedor.getTelefone());
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    
    public void removerFornecedor(int id) {
         String sql = "DELETE FROM Fornecedor WHERE id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, id);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    
    public void atualizarFornecedor(Fornecedor fornecedor) {
        String sql = "UPDATE Fornecedor SET nome = ?, cnpj = ?, telefone = ? WHERE id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, fornecedor.getNome());
        stmt.setString(2, fornecedor.getCnpj());
        stmt.setString(3, fornecedor.getTelefone());
        stmt.setInt(4, fornecedor.getId());
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
   }

    
    public Fornecedor buscarFornecedorPorID(int id) {
      String sql = "SELECT * FROM Fornecedor WHERE id = ?";
    Fornecedor fornecedor = null;
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            fornecedor = new Fornecedor();
            fornecedor.setId(rs.getInt("id"));
            fornecedor.setNome(rs.getString("nome"));
            fornecedor.setCnpj(rs.getString("cnpj"));
            fornecedor.setTelefone(rs.getString("telefone"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
     return fornecedor;
    }

    
    public List<Fornecedor> listarTodosFornecedores() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT * FROM Fornecedor";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedores.add(fornecedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fornecedores;
    }
    
}
