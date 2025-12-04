package Models;

import java.util.Scanner;
import Interfaces.ProdutoDAO;
import DAO.DAOFactory;

public class SistemaAtualizacaoProduto {

    public static void sistemaUpdateProduto(Scanner in) {

        ProdutoDAO dao = DAOFactory.createProdutoDAO();

        System.out.println("--- Atualização de preço de produto ---");

        // Lê ID do produto
        Integer id = null;
        while (id == null) {
            System.out.print("Digite o id do produto: ");
            if (in.hasNextInt()) {
                id = in.nextInt();
            } else {
                System.out.println("Id inválido! Digite um número inteiro válido.");
                in.next(); // descarta o token inválido
            }
        }
        in.nextLine(); // limpa o '\n' pendente

        // Verifica se o produto existe
        Produto produtoExistente = dao.buscarPorID(id);
        if (produtoExistente == null) {
            System.out.println("Produto com ID " + id + " não encontrado.");
            return; // sai do método, não faz update
        }

        // Lê e valida o novo preço
        Double preco = null;
        while (preco == null) {
            System.out.print("Novo preço: R$ ");
            String entrada = in.nextLine();   // lê a linha toda
            try {
                preco = Double.parseDouble(entrada.replace(',', '.')); // aceita vírgula ou ponto [web:29]
                if (preco < 0) {
                    System.out.println("O preço deve ser positivo. Tente novamente.");
                    preco = null;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Preço inválido! Digite um número (ex: 10.50).");
            }
        }

        // Cria objeto apenas com campos necessários
        Produto novoProd = new Produto();
        novoProd.setId(id);
        novoProd.setPreco(preco);

        dao.update(novoProd);
        System.out.println("Preço do produto atualizado.");
    }
}
