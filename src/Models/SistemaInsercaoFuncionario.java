package Models;

import Interfaces.FuncionarioDAO;
import DAO.DAOFactory;
import java.util.Scanner;

public class SistemaInsercaoFuncionario {
    public static void exibirMenuInsercaoFuncionario(Scanner in) {

        FuncionarioDAO f = DAOFactory.createFuncionarioDAO();

        System.out.println("--- Inserção de funcionario ---");
        System.out.print("Nome: ");
        String nome = in.nextLine();

        System.out.print("CPF sem [-] e [.]: ");
        String cpf = in.nextLine();

        double salario;
        while (true) {
            System.out.print("Salário: R$ ");
            String linha = in.nextLine();
            try {
                salario = Double.parseDouble(linha.replace(',', '.')); // aceita , ou .
                if (salario < 0) {
                    System.out.println("O salário deve ser positivo.");
                    continue;
                }
                break; // salário válido
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Digite um número (use ponto ou vírgula).");
            }
        }

        System.out.print("Tipo funcionário [cargo]: ");
        String tipo_funcionario = in.nextLine();

        System.out.print("Email: ");
        String email = in.nextLine();

        System.out.print("Senha: ");
        String senha = in.nextLine();

        // Cria objeto Funcionario com os dados coletados
        Funcionario novo = new Funcionario();
        novo.setNome(nome);
        novo.setCpf(cpf);
        novo.setSalario(salario);
        novo.setTipoFuncionario(tipo_funcionario);
        novo.setEmail(email);
        novo.setSenha(senha);

        // Chama o método do DAO para inserir no banco
        f.inserir(novo);

        System.out.println("Funcionário inserido com sucesso!");
    }
}
