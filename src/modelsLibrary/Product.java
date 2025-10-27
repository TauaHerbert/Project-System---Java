package modelsLibrary;

import java.math.BigDecimal;

public class Product {
    private int idProduto;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private int idCategoria;

    public Product() {
    }

    public Product(String nome, String descricao, BigDecimal preco, int idCategoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.idCategoria = idCategoria;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
