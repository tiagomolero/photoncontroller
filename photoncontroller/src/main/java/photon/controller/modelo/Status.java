package photon.controller.modelo;

public enum Status {
	
	ATIVO("ATIVO"),
	INATIVO("INATIVO");
	
	public String nome;
	
	private Status(String nome) {
		this.nome=nome;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nome;
	}
	

}
