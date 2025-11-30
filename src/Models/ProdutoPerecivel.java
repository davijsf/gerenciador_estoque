package Models;

import java.sql.Date;

public class ProdutoPerecivel extends Produto {

    // Campo extra (se quiser separar da validade geral)
    private Date dataFabricacao;

    public ProdutoPerecivel() {
        super();
    }

    public ProdutoPerecivel(String nome, String descricao, String categoria,
                            Date validade, String status, double preco, Date dataFabricacao) {
        super(nome, descricao, categoria, validade, status, preco);
        this.dataFabricacao = dataFabricacao;
    }

    public Date getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(Date dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public boolean estaVencido() {
        Date hoje = new Date(System.currentTimeMillis());
        return getValidade() != null && getValidade().before(hoje);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", dataFabricacao=" + dataFabricacao +
                ", vencido=" + (estaVencido() ? "SIM" : "NÃO");
    }
}
