package Application;

import java.util.Scanner;

import Models.*;

import DAO.DAOFactory;
import Interfaces.*;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        FuncionarioDAO f = DAOFactory.createFuncionarioDAO();
        ProdutoDAO p = DAOFactory.createProdutoDAO();
        EstoqueDAO e = DAOFactory.createEstoqueDAO();

        while(true) {
            System.out.println("ESTOQUES - VIRLOS SYSTEM");
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
                System.out.println("2 - Ver estoque."); // ok
                System.out.println("3 - Adicionar produto."); // ok
                System.out.println("4 - Remover produto."); // ok
                System.out.println("5 - Remover funcionário."); // ok
                System.out.println("6 - Atualizar dados do funcionário."); // ok
                System.out.println("7 - Atualizar preço de produto."); // ok
                System.out.println("8 - Ver funcionário."); // ok
                System.out.println("9 - Adicionar funcionário."); // ok
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
                        p.buscarPorID(id);
                        break;
                    }

                    case 2: {
                        System.out.print("Digite o id do produto: ");
                        while (!in.hasNextInt()) {
                            System.out.println("Id inválido! Digite um número inteiro válido:");
                            in.next();
                        }
                        int id = in.nextInt();

                        // Limpa o buffer do teclado após nextInt()
                        in.nextLine();
                        e.buscarEstoquePorID(id);
                        break;
                    }

                    // INserção de produto
                    case 3: {
                        SistemaInsercaoProduto.exibirMenuInsercaoProduto(in);
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
                    case 5: {
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
                    case 6: {
                        SistemaAtualizacaoFuncionario.exibirMenuAtualizacaoFuncionario(funcionarioLogado, in);
                        break;
                    }

                    // Atualização de produto
                    case 7: {
                        SistemaAtualizacaoProduto.sistemaUpdateProduto(in);
                        break;
                    }


                    // Ver funcionário
                    // Somente gerente tem essa função
                    case 8: {
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

                    case 9: {
                        if("gerente".equalsIgnoreCase(funcionarioLogado.getTipoFuncionario())) {
                            SistemaInsercaoFuncionario.exibirMenuInsercaoFuncionario(in);
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
