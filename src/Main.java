import JDBC.FuncionarioDAOJDBC;
import java.util.Scanner;

import Models.Conexao;
import Models.Funcionario;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        FuncionarioDAOJDBC dao = new FuncionarioDAOJDBC(Conexao.conectar());

        int op = 1;
        System.out.println("ESTOQUES - SYSTEM CD");
        do {
            System.out.println("-- Login --");
            System.out.print("Email: ");
            String email = in.nextLine();

            System.out.print("Senha: ");
            String senha = in.next();

            // Validação LOGIN:
            Funcionario funcionarioLogado = dao.autenticar(email, senha);

            if (funcionarioLogado != null) {
                System.out.println("Login realizado: " + funcionarioLogado.getNome());

                // Menu principal do sistema
                System.out.println("-- MENU -- ");
                System.out.println("1 - Ver produto.");
                System.out.println("2 - Ver estoque.");

                // Só um funcionario com cargo 'estoquista' pode realizar tais operações:
                if("estoquista".equalsIgnoreCase(funcionarioLogado.getTipoFuncionario())) {
                    System.out.println("3 - Adicionar produto.");
                    System.out.println("4 - Remover produto.");
                    System.out.println("5 - Ver movimentação do estoque.");
                }
                // Uma verificação se o funcionário é do tipo gerente,
                // ele poder remover/atualizar um funcionário.
                if("gerente".equalsIgnoreCase(funcionarioLogado.getTipoFuncionario())) {
                    System.out.println("6 - Remover funcionário.");
                    System.out.println("7 - Atualizar dados do funcionário.");
                }



            } else {
                System.out.println("Email ou senha incorretos!\n");
            }


        } while(op != 0);
    }
}
