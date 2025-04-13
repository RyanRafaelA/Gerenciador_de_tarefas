package negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import entidade.Tarefa;
import repositorio.RepositorioTarefa;

public class ControladorPrincipal {
	private static Scanner sc = new Scanner(System.in);
	private static List<Tarefa> listaTarefas = new ArrayList<>(RepositorioTarefa.lerTarefas());
	
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
				sc.nextLine();
				pesquisarTarefa();
				break;
			case 4:
				sc.nextLine();
				atualizarTarefa();
				break;
			case 5:
				sc.nextLine();
				deletarTarefa();
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
		
		listaTarefas.add(new Tarefa(titulo, descricao));
		
		RepositorioTarefa.salvarTarefas(listaTarefas);
	}
	
	private static void lerTodasTarefas() {
		List<Tarefa> listaTarefas = new ArrayList<>(RepositorioTarefa.lerTarefas());
		
		for(Tarefa tarefas: listaTarefas) {
			System.out.println("\n"+tarefas+"\n");
		}
	}
	
	private static Tarefa pesquisarTarefa() {
		Tarefa tarefa = null;
		
		String tituloPesquisa;
		
		System.out.print("Digite o titulo da tarefa: ");
		tituloPesquisa = sc.nextLine();
		
		for(Tarefa tarefas: listaTarefas) {
			if(tarefas.getTitulo().equalsIgnoreCase(tituloPesquisa)) {
				tarefa = tarefas;
				System.out.println("\n"+tarefas+"\n");
			}
		}
		
		if(tarefa == null) {
			System.out.println("Essa tarefa não existe.");
		}
		
		return tarefa;
	}
	
	private static Tarefa deletarTarefa() {
		Tarefa tarefaDeletar = pesquisarTarefa();
		
		if(tarefaDeletar != null) {
			listaTarefas.remove(tarefaDeletar);
			RepositorioTarefa.salvarTarefas(listaTarefas);
			return tarefaDeletar;
		}
		
		return null;
	}
	
	private static void atualizarTarefa() {
		int opcao;
		Tarefa tarefaAtualizar = deletarTarefa();
		
		if(tarefaAtualizar != null) {
			Map<Integer, Runnable> opcoesAtualizacao = new HashMap<>();
			opcoesAtualizacao.put(1, () -> atualizarTitulo(tarefaAtualizar));
			opcoesAtualizacao.put(2, () -> atualizarDescricao(tarefaAtualizar));
			opcoesAtualizacao.put(3, tarefaAtualizar::modificarStatusTarefa);
			
			System.out.print("[1] Atualizar título\n[2] Atualizar descrição\n[3] Modificar status\nEscolha uma opção: ");
	        opcao = sc.nextInt();
	        sc.nextLine();
	        
	        Runnable acao = opcoesAtualizacao.get(opcao);
	        
	        if(acao != null) {
	        	acao.run();
	        	System.out.println("Tarefa atualizada com sucesso!");
	        	listaTarefas.add(tarefaAtualizar);
	        	RepositorioTarefa.salvarTarefas(listaTarefas);
	        } else {
	        	System.out.println("Opçao invalida.");
	        }
		}
	}
	
	private static void atualizarTitulo(Tarefa tarefa) {
	    System.out.print("Digite o novo título: ");
	    tarefa.setTitulo(sc.nextLine());
	}

	private static void atualizarDescricao(Tarefa tarefa) {
	    System.out.print("Digite a nova descrição: ");
	    tarefa.setDescricao(sc.nextLine());
	}

}