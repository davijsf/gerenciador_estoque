package Models;

import JDBC.ProdutoDAOJDBC;
import java.util.Scanner;

public class SistemaInsercaoProduto {

    public static void exibirMenuInsercaoProduto() {
        Scanner in = new Scanner(System.in);
        ProdutoDAOJDBC p = new ProdutoDAOJDBC(Conexao.conectar());

        System.out.println("--- Inserção de produto ---");

        Produto newProd = new Produto();

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

        System.out.print("Preço: ");
        while (!in.hasNextDouble()) {
            System.out.println("Preço inválido! Digite um número (use ponto, não vírgula):");
            in.next();
        }
        newProd.setPreco(in.nextDouble());
        in.nextLine();

        p.inserir(newProd);
        System.out.println("Produto cadastrado com sucesso!");
    }
}

