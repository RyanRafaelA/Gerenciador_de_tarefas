package repositorio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import entidade.Tarefa;

public class RepositorioTarefa {
	private static final String CAMINHO_ARQUIVO = Paths.get(System.getProperty("user.dir"), "tarefas.txt").toString();
	
	public static void salvarTarefas(Tarefa novaTarefa) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))){
			bw.write(novaTarefa.toString());
		} 
		catch (IOException e) {
			System.out.println("Erro no caminho do arquivo. "+e.getMessage());
		}
	}

}
