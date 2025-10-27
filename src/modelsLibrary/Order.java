package modelsLibrary;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
	private int idPedido;
	private LocalDateTime data;
	private BigDecimal total;
	private int idCliente;
	//Atributo adicionado para pegar o nome do cliente na busca do pedido
	private String nomeC;
	//Atributo para pegar os dados do cliente na busca do pedido
	private Client cliente;
	
	public Order() {
		
	}
	
	public Order (LocalDateTime data, BigDecimal total, int idCliente) {
		this.data = data;
		this.total = total;
		this.idCliente = idCliente;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public String getNomeC() {
		return nomeC;
	}

	public void setNomeC(String nomeC) {
		this.nomeC = nomeC;
	}
	
	public Client getCliente() {
		return cliente;
	}

	public void setCliente(Client cliente) {
		this.cliente = cliente;
	}
	

}
