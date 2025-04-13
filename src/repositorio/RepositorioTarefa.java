package repositorio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import entidade.Tarefa;

public class RepositorioTarefa {
	private static final String CAMINHO_ARQUIVO = Paths.get(System.getProperty("user.dir"), "tarefas.txt").toString();
	private static List<Tarefa> listaTarefas = new ArrayList<>();
	
	public static void salvarTarefas(Tarefa novaTarefa) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))){
			bw.write(novaTarefa.toString());
		} 
		catch (IOException e) {
			System.err.println("Erro no caminho do arquivo. "+e.getMessage());
		}
	}
	
	public static List<Tarefa> lerTarefas() {
		String linha;
		String titulo = null, dataInicio = null, dataTermino = null, descricao = null;
		
		
		try(BufferedReader br = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))){
			while((linha = br.readLine()) != null) {
				if(linha.startsWith("Titulo: ")) {
	                titulo = linha.substring(8);
	            } else if(linha.startsWith("Descrição: ")) {
	                descricao = linha.substring(11);
	            } else if(linha.startsWith("Data Inicio: ")) {
	                dataInicio = linha.substring(13);
	            } else if(linha.startsWith("Data Termino: ")) {
	                dataTermino = linha.substring(13);
	            }
				
				if(dataInicio == null && dataInicio.equals("null")) {
					listaTarefas.add(new Tarefa(titulo, descricao));
				} else if(dataTermino == null && dataTermino.equals("null")) {
                	listaTarefas.add(new Tarefa(titulo, descricao, dataInicio));
                } else {
                	listaTarefas.add(new Tarefa(titulo, descricao, dataInicio, dataTermino));
                }
			}
		}
		catch(IOException e) {
			System.err.println("Erro no caminho do arquivo. "+e.getMessage());
		}
		catch(ParseException e1) {
			System.err.println("Erro na formatação da data. "+e1.getMessage());
		}
		
		return listaTarefas;
	}
}
