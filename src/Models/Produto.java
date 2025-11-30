package Models;
import java.sql.Date;

public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private String categoria;
    private Date validade;
    private String status;
    private Double preco;
    private Fornecedor fornecedor;

    public Produto() {
    }

    public Produto(String nome, String descricao, String categoria,
                   java.sql.Date validade, String status, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.validade = validade;
        this.status = status;
        this.preco = preco;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public java.sql.Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

}
