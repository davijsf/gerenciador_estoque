package Models;

import DAO.DAOFactory;
import Interfaces.*;

import java.util.Date;
import java.util.Scanner;

public class SistemaInsercaoProduto {

    public static void exibirMenuInsercaoProduto(Scanner in) {

        ProdutoDAO p = DAOFactory.createProdutoDAO();
        FornecedorDAO fornecedorDAO = DAOFactory.createFornecedorDAO();
        EstoqueDAO estoqueDAO = DAOFactory.createEstoqueDAO();

        System.out.println("--- Inserção de produto ---");

        System.out.println("Esse produto é perecível? [s/n]: ");
        String perecivelResp = in.nextLine();
        boolean ehPerecivel = perecivelResp.equalsIgnoreCase("s");

        Produto newProd;
        if (ehPerecivel) {
            newProd = new ProdutoPerecivel();
        } else {
            newProd = new Produto();
        }

        System.out.print("Nome do produto: ");
        newProd.setNome(in.nextLine());

        System.out.print("Descrição: ");
        newProd.setDescricao(in.nextLine());

        System.out.print("Categoria: ");
        newProd.setCategoria(in.nextLine());


        System.out.print("Validade (yyyy-mm-dd ou deixe vazio): ");
        String validadeStr = in.nextLine();
        if (!validadeStr.isBlank()) {
            try {
                newProd.setValidade(java.sql.Date.valueOf(validadeStr));
            } catch (IllegalArgumentException e) {
                System.out.println("Data em formato inválido! Produto não cadastrado.");
                return;
            }
        } else {
            newProd.setValidade(null);
        }

        System.out.print("Status: ");
        newProd.setStatus(in.nextLine());

        double preco;
        while (true) {
            System.out.print("Preço: ");
            String linha = in.nextLine();     // sempre lê uma linha
            try {
                preco = Double.parseDouble(linha.replace(',', '.')); // aceita , ou .
                if (preco < 0) {
                    System.out.println("O preço deve ser positivo.");
                    continue;
                }
                break; // entrada válida, sai do while
            } catch (NumberFormatException ex) {
                System.out.println("Preço inválido! Digite um número (use ponto ou vírgula).");
            }
        }
        newProd.setPreco(preco);
        //Adicionando o fornecedor
        System.out.print("ID do fornecedor: ");
        int fornecedorId = Integer.parseInt(in.nextLine());
        Fornecedor fornecedor = fornecedorDAO.buscarFornecedorPorID(fornecedorId);

        if (fornecedor != null) {
            newProd.setFornecedor(fornecedor);
        } else {
            System.out.println("Fornecedor não encontrado! Produto não cadastrado.");
            return;
        }

        int qtd;
        while (true) {
            System.out.print("Quantidade recebida desse produto: ");
            String linha = in.nextLine();
            try {
                qtd = Integer.parseInt(linha);
                if (qtd < 0) {
                    System.out.println("Quantidade deve ser positiva.");
                    continue;
                }
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Quantidade inválida! Digite um número inteiro.");
            }
        }


        System.out.print("Tipo de estoque do produto\n-- [normal] | [segurança] | [sazonal]\nDigite: ");
        String tipo_est = in.nextLine();

        // Insere produto
        p.inserir(newProd);

        // Insere estoque
        Estoque e = new Estoque();
        e.setId_produto(newProd.getId());
        e.setQuantidade(qtd);
        e.setTipo_estoque(tipo_est);
        e.setData_recebimento(new Date(System.currentTimeMillis()));

        estoqueDAO.adicionarEstoque(e);

        System.out.println("Produto e estoque inicial cadastrados com sucesso!");
    }
}
