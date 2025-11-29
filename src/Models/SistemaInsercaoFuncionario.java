package Models;

import Interfaces.FuncionarioDAO;
import JDBC.FuncionarioDAOJDBC;
import java.util.Scanner;

public class SistemaInsercaoFuncionario {
    public static void exibirMenuInsercaoFuncionario(Scanner in) {
        FuncionarioDAO f = new FuncionarioDAOJDBC(Conexao.conectar());

        System.out.println("--- Inserção de funcionario ---");
        System.out.print("Nome: ");
        String nome = in.next();

        System.out.print("CPF sem [-] e [.]: ");
        String cpf = in.next();

        System.out.print("Salário: R$ ");
        Double salario = in.nextDouble();
        in.nextLine();

        System.out.print("Tipo funcionário [cargo]: ");
        String tipo_funcionario = in.next();

        System.out.print("Email: ");
        String email = in.next();

        System.out.println("Senha: ");
        String senha = in.next();

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
