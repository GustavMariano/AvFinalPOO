package Modelo;

import java.time.LocalDate;

public class Venda {
    

    private int quantVendida;
    private Produto produto;
    private LocalDate dataDeVenda;
    
    public Venda(int quantVendida, Produto produto, LocalDate dataDeVenda) {
        this.quantVendida = quantVendida;
        this.produto = produto;
        this.dataDeVenda = dataDeVenda;

    }

    public double Calcular() {
        return quantVendida * produto.getValor();
    }
    
    public int getQuantVendida() {
        return quantVendida;
    }
    public void setQuantVendida(int quantVendida) {
        this.quantVendida = quantVendida;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public LocalDate getDataDeVenda() {
        return dataDeVenda;
    }
    public void setDataDeVenda(LocalDate dataDeVenda) {
        this.dataDeVenda = dataDeVenda;
    }
   
    @Override
    public String toString() {
        return "Venda [dataDeVenda=" + dataDeVenda + ", produto=" + produto + ", quantVendida=" + quantVendida + "]";
    }

    

    
}
