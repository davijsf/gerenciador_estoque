package JDBC;

import Models.Fornecedor;
import Models.Produto;

import java.sql.*;

public class ProdutoDAOJDBC {
    private final Connection conn;

    public ProdutoDAOJDBC(Connection conn) { this.conn = conn; }

    public void inserir(Produto produto) {
         String sql = "INSERT INTO produto (nome, descricao, categoria, validade, status, preco, fornecedor_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {

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

            
            if (produto.getFornecedor() != null) {
                stmt.setInt(7, produto.getFornecedor().getId());
            } else {
                stmt.setNull(7, java.sql.Types.INTEGER);
            }

            int rows = stmt.executeUpdate();
            if(rows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        produto.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Produto buscarPorId(int id) {
         String sql = "SELECT p.*, f.id AS f_id, f.nome AS f_nome, f.cnpj AS f_cnpj, f.telefone AS f_telefone " +
                 "FROM produto p LEFT JOIN fornecedor f ON p.fornecedor_id = f.id WHERE p.id = ?";

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
                
                int fid = rs.getInt("f_id");
                    if (!rs.wasNull()) {
                        Fornecedor f = new Fornecedor();
                        f.setId(fid);
                        f.setNome(rs.getString("f_nome"));
                        f.setCnpj(rs.getString("f_cnpj"));
                        f.setTelefone(rs.getString("f_telefone"));
                        p.setFornecedor(f);
                    } else {
                        p.setFornecedor(null);
                    }
                // Mostra os dados do produto no terminal
                System.out.println("--- Produto Encontrado ---");
                System.out.println("ID: " + p.getId());
                System.out.println("Nome: " + p.getNome());
                System.out.println("Descrição: " + p.getDescricao());
                System.out.println("Categoria: " + p.getCategoria());
                System.out.println("Validade: " + p.getValidade());
                System.out.println("Status: " + p.getStatus());
                System.out.println("Preço: R$ " + p.getPreco());
                System.out.println("Fornecedor: " + (p.getFornecedor() != null ? p.getFornecedor().getNome() : "Nenhum"));
                System.out.println("-------------------------");
                return p;
            } else {
                System.out.println("Produto não encontrado para o ID informado!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Produto produto) {
        String sql = "UPDATE produto SET preco = ? WHERE id = ?";
        // Em produto, podemos atualizar apenas o preco dele.
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, produto.getPreco());
            stmt.setInt(2, produto.getId());
            stmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        // Deletando primeiro o estoque do produto
        String sqlDeleteEstoque = "DELETE FROM estoque WHERE produto_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sqlDeleteEstoque)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Deletando o produto, finalmente
        String sql = "DELETE FROM produto WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if(linhasAfetadas > 0) {
                System.out.println("Produto removido com sucesso!");
            } else {
                System.out.println("Nenhum produto encontrado com o ID informado!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
