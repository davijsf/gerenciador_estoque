package Interfaces;
import java.util.List;
import Models.Fornecedor;

public interface  FornecedorDAO {
    // Adicionar fornecedor
    void adicionarFornecedor(Fornecedor fornecedor);

    // Remover fornecedor
    void removerFornecedor(int id);

    // Atualizar fornecedor
    void atualizarFornecedor(Fornecedor fornecedor);

    // Buscar fornecedor por ID
    Fornecedor buscarFornecedorPorID(int id);

    // Listar todos os fornecedores
    List<Fornecedor> listarTodosFornecedores();
}
