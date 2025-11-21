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

            System.out.print("\nSenha: ");
            String senha = in.next();

            // Validação LOGIN:
            Funcionario funcionarioLogado = dao.autenticar(email, senha);

            if (funcionarioLogado != null) {
                System.out.println("Login realizado: " +
                        funcionarioLogado.getNome());
                // Menu principal do sistema


            } else {
                System.out.println("Email ou senha incorretos!\n");
            }


        } while(op != 0);
    }
}
