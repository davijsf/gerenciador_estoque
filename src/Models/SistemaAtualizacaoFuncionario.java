package Models;

import Interfaces.FuncionarioDAO;
import DAO.DAOFactory;
import java.util.Scanner;

public class SistemaAtualizacaoFuncionario {

    public static void exibirMenuAtualizacaoFuncionario(Funcionario f, Scanner scanner) {

        FuncionarioDAO dao = DAOFactory.createFuncionarioDAO();


        if (!"gerente".equalsIgnoreCase(f.getTipoFuncionario())) {
            // Só permite alterar senha (case 4)
            System.out.println("Você não é gerente. Só pode alterar sua senha.");

            // Verificar se deseja alterar senha:
            System.out.print("Deseja alterar a senha? [s/n]: ");
            String opcao = scanner.nextLine();

            if (opcao.equalsIgnoreCase("s")) {
                System.out.print("Nova senha: ");
                f.setSenha(scanner.nextLine());
                dao.atualizar(f);
                System.out.println("Atualização realizada.");
            }
            return;
        }
        // Se chegou aqui, é gerente: pode acessar todas as opções
        System.out.print("ID do funcionário que deseja atualizar: ");
        int id = scanner.nextInt(); scanner.nextLine();

        Funcionario func = dao.buscarPorId(id);

        if (func == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        System.out.println("Escolha o campo para atualizar:");
        System.out.println("1 - Salário");
        System.out.println("2 - Tipo de funcionário");
        System.out.println("3 - E-mail");
        System.out.println("4 - Senha");

        int opcao = scanner.nextInt(); scanner.nextLine();

        switch (opcao) {
            case 1:
                System.out.print("Novo salário: ");
                func.setSalario(scanner.nextDouble());
                scanner.nextLine();
                break;
            case 2:
                System.out.print("Novo tipo de funcionário: ");
                func.setTipoFuncionario(scanner.nextLine());
                break;
            case 3:
                System.out.print("Novo e-mail: ");
                func.setEmail(scanner.nextLine());
                break;
            case 4:
                System.out.print("Nova senha: ");
                func.setSenha(scanner.nextLine());
                break;
            default:
                System.out.println("Opção incorreta!");
                break;
        }

        dao.atualizar(func);
        System.out.println("Atualização realizada.");
    }
}
