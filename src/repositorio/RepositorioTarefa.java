package repositorio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import entidade.Tarefa;

public class RepositorioTarefa {
	private static final String CAMINHO_ARQUIVO = Paths.get(System.getProperty("user.dir"), "tarefas.txt").toString();
	
	public static void salvarTarefas(Tarefa novaTarefa) {
		List<Tarefa> listaTarefas = new ArrayList<>(lerTarefas());
		listaTarefas.add(novaTarefa);
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))){
			for(Tarefa tarefas: listaTarefas) {
				bw.write(tarefas.toString());
				bw.newLine();
			}
		} 
		catch (IOException e) {
			System.err.println("Erro no caminho do arquivo. "+e.getMessage());
		}
	}
	
	public static List<Tarefa> lerTarefas() {
		List<Tarefa> listaTarefas = new ArrayList<>();
		
		String titulo = "", dataInicio = "", dataTermino = "", descricao = "", status = "";
		
		
		try(BufferedReader br = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))){
			String linha;
			while((linha = br.readLine()) != null) {
				if(linha.startsWith("Titulo: ")) {
	                titulo = linha.split(": ")[1];
	            }
				if(linha.startsWith("Descrição: ")) {
	                descricao = linha.split(": ")[1];
	            }
				if(linha.startsWith("Status: ")) {
					status = linha.split(": ")[1];
				}
				if(linha.startsWith("Data Inicio: ")) {
	                dataInicio = linha.split(": ")[1];
	            } 
				if(linha.startsWith("Data Termino: ")) {
	                dataTermino = linha.split(": ")[1];
	            }
				
				if(!titulo.isEmpty() && !descricao.isEmpty() && !status.isEmpty() && !dataInicio.isEmpty() && !dataTermino.isEmpty()) {
					listaTarefas.add(new Tarefa(titulo, descricao, status, dataInicio, dataTermino));
					titulo = "";
					dataInicio = "";
					dataTermino = "";
					descricao = "";
					status = "";
				}
			}
		}
		catch(IOException e) {
			System.err.println("Erro no caminho do arquivo. "+e.getMessage());
		}
		
		return listaTarefas;
	}
}
