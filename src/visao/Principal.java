package visao;

import aplicacao.*;
import persistencia.Conexao;
import persistencia.ContatoDAO;

import java.util.Scanner;
import java.util.ArrayList;

public class Principal {
	public static void main(String[] args) {
		int op, op2, idBusc;
		String nome, telefone, email, nomeBusc;
		ArrayList<Contato> listaContatos = new ArrayList<Contato>();
		Conexao conexao = new Conexao();
		ContatoDAO.setConexao(conexao);
		Contato c1 = new Contato();

		try (Scanner teclado = new Scanner(System.in)) {
            do {
            	System.out.println("-------------------------");
            	System.out.println("\tMENU PRINCIPAL");
            	System.out.println("-------------------------");
            	System.out.println("1 - Buscar Contato");
            	System.out.println("2 - Incluir Contato");
            	System.out.println("3 - Alterar Contato");
            	System.out.println("4 - Excluir Contato");
            	System.out.println("5 - Relatorio de Contatos");
            	System.out.println("6 - Sair do Sistema");
            	System.out.println("\nDigite a opcao que deseja");

            	op = teclado.nextInt();
            	teclado.nextLine();

            	switch (op) {
            		case 1:
            			do {
            				System.out.println("-------------------------");
            				System.out.println("\tMENU SECUNDARIO");
            				System.out.println("-------------------------");
            				System.out.println("1 - Buscar pelo ID");
            				System.out.println("2 - Incluir Pelo Nome");
            				System.out.println("3 - Voltar ao Menu Principal");
            				System.out.println("\nDigite a opcao que deseja");

            				op2 = teclado.nextInt();
            				teclado.nextLine();

            				switch (op2) {
            					case 1:
									System.out.println("DIGITE O ID QUE DESEJA BUSCAR: ");
									idBusc = teclado.nextInt();
									teclado.nextLine();
            						c1 = ContatoDAO.buscarContato(idBusc);
									if (c1 != null) {
										System.out.println("NOME: " + c1.getNome());
										System.out.println("TELEFONE: " + c1.getTelefone());
										System.out.println("EMAIL: " + c1.getEmail());
									} else{
										System.out.println("CONTATO INEXISTENTE!");
									}
            						break;
            				
								case 2:
									System.out.println("DIGITE O NOME QUE DESEJA BUSCAR: ");
									nomeBusc = teclado.nextLine();
									listaContatos = ContatoDAO.retornarLista(nomeBusc);
									for (int i = 0; i < listaContatos.size(); i++) {
										System.out.println("ID: " + listaContatos.get(i).getId());
										System.out.println("NOME: " + listaContatos.get(i).getNome());
										System.out.println("TELEFONE: " + listaContatos.get(i).getTelefone());
										System.out.println("EMAIL: " + listaContatos.get(i).getEmail());
									}

									break;

								case 3:
									System.out.println("SAINDO DO MENU SECUNDARIO");
									break;
            					default:
									System.out.println("OPCAO INVALIDA!");
            						break;
            				}
            			} while (op2 != 3);
            			break;

					case 2:
						System.out.println("DIGITE O NOME DO CONTATO: ");
						nome = teclado.nextLine();
						System.out.println("DIGITE O TELEFONE DO CONTATO: ");
						telefone = teclado.nextLine();
						System.out.println("DIGITE O EMAIL DO CONTATO: ");
						email = teclado.nextLine();
						c1.setNome(nome);
						c1.setTelefone(telefone);
						c1.setEmail(email);
						ContatoDAO.inserirContato(c1);
						break;

					case 3:
						System.out.println("DIGITE O ID QUE DESEJA BUSCAR: ");
						idBusc = teclado.nextInt();
						teclado.nextLine();
						c1 = ContatoDAO.buscarContato(idBusc);
						if (c1 != null) {
							System.out.println("NOME: " + c1.getNome());
							System.out.println("TELEFONE: " + c1.getTelefone());
							System.out.println("EMAIL: " + c1.getEmail() + "\n");

							System.out.println("DIGITE O NOVO NOME DO CONTATO: ");
							nome = teclado.nextLine();
							System.out.println("DIGITE O NOVO TELEFONE DO CONTATO: ");
							telefone = teclado.nextLine();
							System.out.println("DIGITE O NOVO EMAIL DO CONTATO: ");
							email = teclado.nextLine();
							c1.setNome(nome);
							c1.setTelefone(telefone);
							c1.setEmail(email);
							ContatoDAO.alterarContato(idBusc, c1);

							System.out.println("\nCONTATO ALTERADO COM SUCESSO!");

						} else{
							System.out.println("CONTATO INEXISTENTE!");
						}
						break;

					case 4:
						System.out.println("DIGITE O ID QUE DESEJA BUSCAR: ");
						idBusc = teclado.nextInt();
						teclado.nextLine();
						ContatoDAO.deletarContato(idBusc);
						break;
					
					case 5:
						listaContatos = ContatoDAO.retornarListaCompleta();
						for (int i = 0; i < listaContatos.size(); i++) {
							System.out.println("ID: " + listaContatos.get(i).getId());
							System.out.println("NOME: " + listaContatos.get(i).getNome());
							System.out.println("TELEFONE: " + listaContatos.get(i).getTelefone());
							System.out.println("EMAIL: " + listaContatos.get(i).getEmail());
						}
						break;
					case 6:
						System.out.println("SAINDO DO PROGRAMA...");
						break;
            		default:
						System.out.println("OPCAO INVALIDA!");
            			break;
            	}
            } while (op != 6);
        }
	}
}