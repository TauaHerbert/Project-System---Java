package modelsLibrary;

import java.math.BigDecimal;

public class OrderItem {
	private int idItem;
	private int quantidade;
	private BigDecimal precoUnitario;
	private int idPedido;
	private int idProduto;
	//Atributo para pegar os dados do produto
	private Product produto;
	
	public OrderItem () {
		
	}
	
	public OrderItem (int quantidade, BigDecimal precoUnitario, int idPedido, int idProduto) {
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
		this.idPedido = idPedido;
		this.idProduto = idProduto;
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	
	public Product getProduto() {
		return produto;
	}

	public void setProduto(Product produto) {
		this.produto = produto;
	}
	

}
