import Interfaces.FuncionarioDAO;
import JDBC.FuncionarioDAOJDBC;
import Models.Conexao;
import Models.Funcionario;

import java.util.Scanner;

public class SistemaAtualizacaoFuncionario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FuncionarioDAO dao = new FuncionarioDAOJDBC(Conexao.conectar());

        System.out.print("ID do funcionário que deseja atualizar: ");
        int id = scanner.nextInt(); scanner.nextLine();

        Funcionario f = dao.buscarPorId(id);

        if (f == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        System.out.println("Escolha o campo para atualizar:");
        System.out.println("1 - Nome");
        System.out.println("2 - CPF");
        System.out.println("3 - Salário");
        System.out.println("4 - Tipo de funcionário");
        System.out.println("5 - E-mail");
        System.out.println("6 - Senha");

        int opcao = scanner.nextInt(); scanner.nextLine();

        switch (opcao) {
            case 1:
                System.out.print("Novo nome: ");
                f.setNome(scanner.nextLine());
                break;
            case 2:
                System.out.print("Novo CPF: ");
                f.setCpf(scanner.nextLine());
                break;
            case 3:
                System.out.print("Novo salário: ");
                f.setSalario(scanner.nextDouble()); scanner.nextLine();
                break;
            case 4:
                System.out.print("Novo tipo de funcionário: ");
                f.setTipoFuncionario(scanner.nextLine());
                break;
            case 5:
                System.out.print("Novo e-mail: ");
                f.setEmail(scanner.nextLine());
                break;
            case 6:
                System.out.print("Nova senha: ");
                f.setSenha(scanner.nextLine());
                break;
        }

        dao.atualizar(f);
        System.out.println("Atualização realizada.");
    }
}

