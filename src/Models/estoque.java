package Models;

import javax.xml.crypto.Data;

public class estoque {
    private int id;
    private int id_produto;
    private int quantidade;
    private String tipo_estoque;
    private Data data_recebimento;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo_estoque() {
        return tipo_estoque;
    }

    public void setTipo_estoque(String tipo_estoque) {
        this.tipo_estoque = tipo_estoque;
    }

    public Data getData_recebimento() {
        return data_recebimento;
    }

    public void setData_recebimento(Data data_recebimento) {
        this.data_recebimento = data_recebimento;
    }
}
