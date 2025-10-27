package modelsLibrary;

import java.math.BigDecimal;

public class OrderPayment {
	
	private int idPedido_Pagamento;
	private int id_pedido;
	private int id_pagamento;
	private BigDecimal valor_pago;
	//Atributo para pegar os dados do Metodo de Pagamento na busca do pedido
	private PaymentMethod metodoPagamento;
	
	public OrderPayment() {
		
	}
	
	public OrderPayment(int idPedido_Pagamento, int id_pedido, int id_pagamento, BigDecimal valor_pago) {
		this.idPedido_Pagamento = idPedido_Pagamento;
		this.id_pedido = id_pedido;
		this.id_pagamento = id_pagamento;
		this.valor_pago = valor_pago;
	}
	
	public int getIdPedido_Pagamento() {
		return idPedido_Pagamento;
	}
	public void setIdPedido_Pagamento(int idPedido_Pagamento) {
		this.idPedido_Pagamento = idPedido_Pagamento;
	}
	public int getId_pedido() {
		return id_pedido;
	}
	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}
	public int getId_pagamento() {
		return id_pagamento;
	}
	public void setId_pagamento(int id_pagamento) {
		this.id_pagamento = id_pagamento;
	}
	public BigDecimal getValor_pago() {
		return valor_pago;
	}
	public void setValor_pago(BigDecimal valor_pago) {
		this.valor_pago = valor_pago;
	}
	
	public PaymentMethod getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(PaymentMethod metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}
}
