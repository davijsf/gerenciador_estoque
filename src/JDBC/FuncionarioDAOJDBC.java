package JDBC;

import Interfaces.FuncionarioDAO;
import Models.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FuncionarioDAOJDBC implements FuncionarioDAO {
    private final Connection conn;

    public FuncionarioDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nome, cpf, salario," +
                "tipo_funcionario, email, senha) values (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setDouble(3, funcionario.getSalario());
            stmt.setString(4, funcionario.getTipoFuncionario());
            stmt.setString(5, funcionario.getEmail());
            stmt.setString(6, funcionario.getSenha());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Funcionario buscarPorId(int id) {
        String sql = "SELECT * FROM funcionario WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setSalario(rs.getDouble("salario"));
                f.setTipoFuncionario(rs.getString("tipo_funcionario"));
                f.setEmail(rs.getString("email"));
                f.setSenha(rs.getString("senha"));

                System.out.println("==== Dados do Funcionário ====");
                System.out.println("ID: " + f.getId());
                System.out.println("Nome: " + f.getNome());
                System.out.println("CPF: " + f.getCpf());
                System.out.println("Salário: " + f.getSalario());
                System.out.println("Tipo: " + f.getTipoFuncionario());
                System.out.println("Email: " + f.getEmail());
                System.out.println("Senha: " + f.getSenha());
                System.out.println("=============================");
                return f;
            } else {
                System.out.println("Funcionário não encontrado para o ID informado!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Funcionario> listarTodos() {
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> lista = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setSalario(rs.getDouble("salario"));
                f.setTipoFuncionario(rs.getString("tipo_funcionario"));
                f.setEmail(rs.getString("email"));
                f.setSenha(rs.getString("senha"));
                lista.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
 }
    @Override
    public void atualizar(Funcionario funcionario) {
        StringBuilder sql = new StringBuilder("UPDATE funcionario SET ");
        List<Object> params = new ArrayList<>();

        if (funcionario.getNome() != null && !funcionario.getNome().isEmpty()) {
            sql.append("nome=?, ");
            params.add(funcionario.getNome());
        }
        if (funcionario.getCpf() != null && !funcionario.getCpf().isEmpty()) {
            sql.append("cpf=?, ");
            params.add(funcionario.getCpf());
        }
        if (funcionario.getSalario() != 0) {
            sql.append("salario=?, ");
            params.add(funcionario.getSalario());
        }
        if (funcionario.getTipoFuncionario() != null && !funcionario.getTipoFuncionario().isEmpty()) {
            sql.append("tipo_funcionario=?, ");
            params.add(funcionario.getTipoFuncionario());
        }
        if (funcionario.getEmail() != null && !funcionario.getEmail().isEmpty()) {
            sql.append("email=?, ");
            params.add(funcionario.getEmail());
        }
        if (funcionario.getSenha() != null && !funcionario.getSenha().isEmpty()) {
            sql.append("senha=?, ");
            params.add(funcionario.getSenha());
        }

        if (params.size() == 0) return; // nada para atualizar
        sql.setLength(sql.length() - 2);

        sql.append(" WHERE id=?");
        params.add(funcionario.getId());

        try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(int id) {
        // Deletando primeiro movimentacoes do funcionario
        String sqlDeleteMovimentacoes = "DELETE FROM movimentacao_estoque WHERE funcionario_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sqlDeleteMovimentacoes)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Deletando, agora, o funcionario
        String sql = "DELETE FROM funcionario WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Funcionário removido!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Funcionario autenticar(String email, String senha) {
        String sql = "SELECT * FROM funcionario WHERE email = ? AND senha = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setSalario(rs.getDouble("salario"));
                f.setTipoFuncionario(rs.getString("tipo_funcionario"));
                f.setEmail(rs.getString("email"));
                f.setSenha(rs.getString("senha"));
                return f;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
