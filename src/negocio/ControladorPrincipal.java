package negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidade.Tarefa;
import repositorio.RepositorioTarefa;

public class ControladorPrincipal {
	private static Scanner sc = new Scanner(System.in);
	
	public static void principal() {
		int opcoes;
		
		do{
			menu();
			opcoes = sc.nextInt();
			System.out.println("--------------------------------------------------------------");
			
			switch(opcoes) {
			case 1:
				sc.nextLine();
				novaTarefa();
				break;
			case 2:
				lerTodasTarefas();
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			default:
				System.out.println("Até a proxima!");
			}
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
	
	private static void novaTarefa() {
		String titulo;
		String descricao;
		
		System.out.print("Titulo: ");
		titulo = sc.nextLine();
		System.out.print("Descrição: ");
		descricao = sc.nextLine();
		
		Tarefa novaTarefa = new Tarefa(titulo, descricao);
		
		RepositorioTarefa.salvarTarefas(novaTarefa);
	}
	
	private static void lerTodasTarefas() {
		List<Tarefa> listaTarefas = new ArrayList<>(RepositorioTarefa.lerTarefas());
		
		for(Tarefa tarefas: listaTarefas) {
			System.out.println(tarefas+"\n");
		}
	}
}
