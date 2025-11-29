package Interfaces;
import java.util.List;
import Models.Estoque;

public interface EstoqueDAO {
    // Remover estoque
    void removerEstoque(int id);

    // Atualizar estoque
    void atualizarEstoque(Estoque estoque);

    // Buscar estoque por ID
    Estoque buscarEstoquePorID(int id);

    // Listar todos os estoques
    List<Estoque> listarTodosEstoques();
}
