package entidade;

import java.time.LocalDate;

public class Tarefa {
	private String titulo;
	private String descricao;
	private Status statusTarefa;
	private String dataInicio;
	private String dataTermino;
	
	public Tarefa() {}
	public Tarefa(String titulo, String descricao) {
		this.titulo = titulo;
		this.descricao = descricao;
		statusTarefa = Status.NAO_INICIADO;
	}
	public Tarefa(String titulo, String descricao, String status, String dataInicio, String dataTermino) {
		this.titulo = titulo;
		this.descricao = descricao;
		statusTarefa = Status.stringParaStatus(status);
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
	}
	
	public String getTitulo() { return titulo; }
	public void setTitulo(String titulo) { this.titulo = titulo; }
	
	public String getDescricao() { return descricao; }
	public void setDescricao(String descricao) { this.descricao = descricao; }
	
	public Status getStatusTarefa() { return statusTarefa; }
	
	public String getDataInicio() { return dataInicio; }
	
	public String getDataTermino() { return dataTermino; }
	
	public void modificarStatusTarefa() {
		LocalDate dataLocal = LocalDate.now();
		String mesAno;
		
		mesAno = String.format("%d/%d", dataLocal.getMonthValue(), dataLocal.getYear());
		
		if(getStatusTarefa() == Status.NAO_INICIADO) {
			this.statusTarefa = Status.EM_ANDAMENTO;
			this.dataInicio = mesAno;
		} else if(getStatusTarefa() == Status.EM_ANDAMENTO) {
			this.statusTarefa = Status.FINALIZADO;
			this.dataTermino = mesAno;
		} else {
			System.out.println("Não pode ser modificado depois de finalizada a tarefa.");
		}
	}
	
	@Override
	public String toString() {
		return "Titulo: "+getTitulo()+"\nDescrição: "+getDescricao()+"\nStatus: "+getStatusTarefa()+"\nData Inicio: "+getDataInicio()+"\nData Termino: "+getDataTermino();
	}
}
