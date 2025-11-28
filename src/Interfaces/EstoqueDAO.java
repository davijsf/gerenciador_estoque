package Interfaces;
import java.util.List;
import Models.estoque;

public class EstoqueDAO {
    // Adicionar estoque
    void adicionarEstoque(estoque estoque);

    // Remover estoque
    void removerEstoque(int id);

    // Atualizar estoque
    void atualizarEstoque(estoque estoque);

    // Buscar estoque por ID
    estoque buscarEstoquePorID(int id);

    // Listar todos os estoques
    List<estoque> listarTodosEstoques();
}
