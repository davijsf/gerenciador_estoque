package Interfaces;
import Models.Produto;
import java.util.List;

public interface ProdutoDAO {
    // inserção de produto
    void inserir(Produto produto);

    //Busca de produto por ID:
    Produto buscarPorID(int id);

    // AtualizarProduto
    void update(Produto produto);

    // Deletar produto
    void deletar(int id);
}
