package photon.controller.modelo;

public enum TipoMovimento {

	ENTRADA("ENTRADA"), SAIDA("SAIDA");

	public String nome;

	private TipoMovimento(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nome;
	}

}
