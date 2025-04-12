package negocio;

import java.util.Scanner;

public class ControladorPrincipal {
	private static Scanner sc = new Scanner(System.in);
	
	public static void principal() {
		int opcoes;
		
		do{
			menu();
			opcoes = sc.nextInt();
			System.out.println("--------------------------------------------------------------");
		} while(opcoes>=1 && opcoes<=5);
	}

	
	private static void menu() {
		System.out.print("[1]Criar um nova tarefa\n"
				+ "[2]Ler todas tarefa\n"
				+ "[3]Pesquisar tarefa\n"
				+ "[4]Atualizar\n"
				+ "[5]Deletar"
				+ "\nDigite uma das opções: ");
	}
}
