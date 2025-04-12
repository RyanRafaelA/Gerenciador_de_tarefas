package entidade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Tarefa {
	private SimpleDateFormat sdf = new SimpleDateFormat("MM/yyy");
	
	private String titulo;
	private String descricao;
	private Status statusTarefa;
	private Date dataInicio;
	private Date dataTermino;
	
	public Tarefa() {}
	public Tarefa(String titulo, String descricao) {
		this.titulo = titulo;
		this.descricao = descricao;
		statusTarefa = Status.NAO_INICIADO;
	}
	public Tarefa(String titulo, String descricao, String dataInicio) throws ParseException {
		this.titulo = titulo;
		this.descricao = descricao;
		statusTarefa = Status.EM_ANDAMENTO;
		this.dataInicio = sdf.parse(dataInicio);
	}
	public Tarefa(String titulo, String descricao, String dataInicio, String dataTermino) throws ParseException {
		this.titulo = titulo;
		this.descricao = descricao;
		statusTarefa = Status.FINALIZADO;
		this.dataInicio = sdf.parse(dataInicio);
		this.dataTermino = sdf.parse(dataTermino);
	}
	
	public String getTitulo() { return titulo; }
	public void setTitulo(String titulo) { this.titulo = titulo; }
	
	public String getDescricao() { return descricao; }
	public void setDescricao(String descricao) { this.descricao = descricao; }
	
	public Status getStatusTarefa() { return statusTarefa; }
	
	public Date getDataInicio() { return dataInicio; }
	
	public Date getDataTermino() { return dataTermino; }
	
	public void modificarStatusTarefa() throws ParseException {
		LocalDate dataLocal = LocalDate.now();
		String mesAno;
		
		mesAno = String.format("%i/%i", dataLocal.getMonthValue(), dataLocal.getYear());
		
		if(getStatusTarefa() == Status.EM_ANDAMENTO) {
			this.statusTarefa = Status.EM_ANDAMENTO;
			this.dataInicio = sdf.parse(mesAno);
		} else if(getStatusTarefa() == Status.EM_ANDAMENTO) {
			this.statusTarefa = Status.FINALIZADO;
			this.dataInicio = sdf.parse(mesAno);
		} else {
			System.out.println("Não pode ser modificado depois de finalizada a tarefa.");
		}
	}
	
	@Override
	public String toString() {
		return "Titulo: "+getTitulo()+"\nDescrição: "+getDescricao()+"\nStatus: "+getStatusTarefa()+"\nData Inicio: "+getDataInicio()+"\nData Termino: "+getDataTermino();
	}
}
