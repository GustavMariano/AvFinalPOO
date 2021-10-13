package Modelo;

public class Produto {

    private int codigo;
    private String nome;
    private double valor;
    private int qtdEstoque;

   
    public void removerQuant(int quant) {
        qtdEstoque -= quant;
    }
    
    public Produto(String nome) {
        this.nome = nome;
    }

    public Produto(int codigo, String nome, double valor, int qtdEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.qtdEstoque = qtdEstoque;

    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public int getQtdEstoque() {
        return qtdEstoque;
    }
    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }
    
    @Override
    public String toString() {
        return "Produto [codigo=" + codigo + ", nome=" + nome + ", qtdEstoque=" + qtdEstoque + ", valor=" + valor + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + codigo;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + qtdEstoque;
        long temp;
        temp = Double.doubleToLongBits(valor);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produto other = (Produto) obj;
        if (codigo != other.codigo)
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (qtdEstoque != other.qtdEstoque)
            return false;
        if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
            return false;
        return true;
    }

    
    
}
