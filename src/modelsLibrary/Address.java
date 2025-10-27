package modelsLibrary;

import com.google.gson.annotations.SerializedName;

public class Address {
	private int idEndereco;
	@SerializedName("logradouro")
	private String rua;
	private String numero;
	@SerializedName("bairro")
	private String bairro;
	@SerializedName("localidade")
	private String cidade;
	@SerializedName("uf")
	private String estado;
	@SerializedName("cep")
	private String cep;
	private int idCliente;
	
	public Address() {
		
	}
	
	public Address(String rua, String numero, String bairro, String cidade, String estado, String cep, int idCliente) {
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.idCliente = idCliente;
	}

	public int getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	

}
