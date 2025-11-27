package Models;

import javax.xml.crypto.Data;
package Models;public class Movimentacao_estoque {
   private int id_produto;
    private int quantidade;
    private String tipo_movimentacao;
    private Data data_movimentacao;
    private int id_funcionario;

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

    public String getTipo_movimentacao() {
        return tipo_movimentacao;
    }

    public void setTipo_movimentacao(String tipo_movimentacao) {
        this.tipo_movimentacao = tipo_movimentacao;
    }

    public Data getData_movimentacao() {
        return data_movimentacao;
    }

    public void setData_movimentacao(Data data_movimentacao) {
        this.data_movimentacao = data_movimentacao;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }
}
