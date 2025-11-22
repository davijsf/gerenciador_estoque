package Interfaces;
import Models.Funcionario;

import java.util.List;

public interface FuncionarioDAO {
    // Inserção de funcionário:
    void inserir(Funcionario funcionario);

    // Busca de funcionário por ID:
    Funcionario buscarPorId(int id);

    // CheckList de todos os funcionários:
    List<Funcionario> listarTodos();

    // Update funcionário:
    void atualizar(Funcionario funcionario);

    // Delete funcionário:
    void deletar(int id);
}
