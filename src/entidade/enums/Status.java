package entidade.enums;

public enum Status {
	NAO_INICIADO("NAO_INICIADO"),
	EM_ANDAMENTO("EM_ANDAMENTO"),
	FINALIZADO("FINALIZADO");

	private String valor;
	
	Status(String valor){
		this.valor = valor;
	}
	
	private String getValor() { return this.valor; }
	
	public static Status stringParaStatus(String statusString) {
		for(Status status: Status.values()) {
			if(status.getValor().equalsIgnoreCase(statusString)) {
				return status;
			}
		}
		throw new IllegalArgumentException("'"+statusString+"' não é um valor valido para um enum de Status.");
	}
}
