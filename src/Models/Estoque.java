package Models;

import java.util.Date;

public class Estoque {
    private int id;
    private int id_produto;
    private int quantidade;
    private String tipo_estoque; // Pode ser: normal | segurança(reserva) | sazonal (datas específicas: natal, dia das maes...)
    private Date data_recebimento;

    
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

    public Date getData_recebimento() {
        return data_recebimento;
    }

    public void setData_recebimento(Date data_recebimento) {
        this.data_recebimento = data_recebimento;
    }
}
