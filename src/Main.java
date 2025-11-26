import JDBC.FuncionarioDAOJDBC;
import java.util.Scanner;

import JDBC.ProdutoDAOJDBC;
import Models.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        FuncionarioDAOJDBC f = new FuncionarioDAOJDBC(Conexao.conectar());
        ProdutoDAOJDBC p = new ProdutoDAOJDBC(Conexao.conectar());

        while(true) {
            System.out.println("ESTOQUES - SYSTEM CD");
            System.out.println("-- Login --");
            System.out.print("Email: ");
            String email = in.nextLine();

            System.out.print("Senha: ");
            String senha = in.nextLine();

            // Validação LOGIN:
            Funcionario funcionarioLogado = f.autenticar(email, senha);

            if (funcionarioLogado != null) {
                System.out.println("Login realizado: " + funcionarioLogado.getNome());

                int op = 1;
                do {
                // Menu principal do sistema
                System.out.println("-- MENU -- ");
                System.out.println("1 - Ver produto."); // ok
                System.out.println("2 - Ver estoque.");
                System.out.println("3 - Adicionar produto."); // ok
                System.out.println("4 - Remover produto."); // ok
                System.out.println("5 - Ver movimentação do estoque.");
                System.out.println("6 - Remover funcionário."); // ok
                System.out.println("7 - Atualizar dados do funcionário."); // ok
                System.out.println("8 - Atualizar preço de produto."); // ok
                System.out.println("9 - Ver funcionário."); // ok
                System.out.println("10 - Adicionar funcionário."); // ok
                System.out.println("0 - Sair.");

                // Escolhendo opção...
                System.out.print("Digite: ");
                op = in.nextInt();
                in.nextLine();

                switch(op) {
                    case 0: {
                        String option;
                        System.out.print("Deseja continuar? [y/n]: ");
                        option = in.next();
                        in.nextLine();

                        if("y".equalsIgnoreCase(option)) {
                            System.out.println("~Indo para a tela de login...");
                            break;
                        } else if("n".equalsIgnoreCase(option)) {
                            System.out.println("~Encerrando [...]");
                            System.exit(0);
                        }
                    }
                    // Busca de produto
                    case 1: {
                        System.out.print("Digite o id do produto: ");
                        while (!in.hasNextInt()) {
                            System.out.println("Id inválido! Digite um número inteiro válido:");
                            in.next();
                        }
                        int id = in.nextInt();

                        // Limpa o buffer do teclado após nextInt()
                        in.nextLine();
                        p.buscarPorId(id);
                        break;
                    }

                    // INserção de produto
                    case 3: {
                        SistemaInsercaoProduto.exibirMenuInsercaoProduto();
                        break;
                    }

                    // Remoção de produto
                    case 4: {
                        System.out.println("--- Remoção de produto ---");
                        System.out.print("Digite o id do produto: ");
                        while (!in.hasNextInt()) {
                            System.out.println("Id inválido! Digite um número inteiro válido:");
                            in.next();
                        }
                        int id = in.nextInt();

                        // Limpa o buffer do teclado após nextInt()
                        in.nextLine();
                        p.deletar(id);
                        break;
                    }

                    // Remoção de funcionário
                    // Apenas um gerente pode remover um funcionário
                    case 6: {
                        if("gerente".equalsIgnoreCase(funcionarioLogado.getTipoFuncionario())) {
                            System.out.println("--- Remoção de funcionário---");
                            System.out.print("Digite o id do funcionário: ");
                            while (!in.hasNextInt()) {
                                System.out.println("Id inválido! Digite um número inteiro válido:");
                                in.next();
                            }
                            int id = in.nextInt();

                            // Limpa o buffer do teclado após nextInt()
                            in.nextLine();
                            f.deletar(id);
                        } else {
                            System.out.println("Apenas gerentes podem remover funcionários.");
                        }
                        break;
                    }

                    // Atualização de funcionário
                    case 7: {
                        SistemaAtualizacaoFuncionario.exibirMenuAtualizacaoFuncionario(funcionarioLogado);
                        break;
                    }

                    // Atualização de produto
                    case 8: {
                        System.out.println("--- Atualização de preço de produto --- ");
                        System.out.print("Digite o id do produto: ");
                        while (!in.hasNextInt()) {
                            System.out.println("Id inválido! Digite um número inteiro válido:");
                            in.next();
                        }
                        int id = in.nextInt();

                        // Limpa o buffer do teclado após nextInt()
                        in.nextLine();

                        Double preco = null;
                        do {
                            System.out.print("Novo preço: R$ ");
                            if (in.hasNextDouble()) {
                                preco = in.nextDouble();
                                if (preco < 0) {
                                    System.out.println("O preço deve ser positivo. Tente novamente.");
                                    preco = null;
                                }
                            } else {
                                System.out.println("Preço inválido! Digite um número (use ponto, não vírgula):");
                                in.next(); // Consome entrada inválida
                            }
                        } while (preco == null);

                        // Limpa o buffer
                        in.nextLine();

                        Produto novoProd = new Produto();
                        novoProd.setId(id);
                        novoProd.setPreco(preco);

                        p.update(novoProd); // Chama o método update do DAO
                        System.out.println("Preço do produto atualizado.");
                        break;
                    }

                    // Ver funcionário
                    // Somente gerente tem essa função
                    case 9: {
                        if("gerente".equalsIgnoreCase(funcionarioLogado.getTipoFuncionario())) {
                            System.out.print("Digite o id do funcionário: ");
                            while (!in.hasNextInt()) {
                                System.out.println("Id inválido! Digite um número inteiro válido:");
                                in.next();
                            }
                            int id = in.nextInt();

                            // Limpa o buffer do teclado após nextInt()
                            in.nextLine();
                            f.buscarPorId(id);
                        } else {
                            System.out.println("Somente gerentes têm essa permissão.");
                        }
                        break;
                    }

                    case 10: {
                        if("gerente".equalsIgnoreCase(funcionarioLogado.getTipoFuncionario())) {
                            SistemaInsercaoFuncionario.exibirMenuInsercaoFuncionario();
                        } else {
                            System.out.println("Somente gerentes têm essa permissão");
                        }
                        break;
                    }

                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } while(op != 0);

            } else {
                System.out.println("Email ou senha incorretos!\n");
            }

        }
    }
}
