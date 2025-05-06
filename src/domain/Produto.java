package domain;

import java.io.Serializable;
import java.util.Objects;

public class Produto implements Serializable {
    private String nome;
    private double preco;
    private int quantidade ;
    private Produto() {
    }

    public static ProdutoBuilder builder() {
        return new ProdutoBuilder();
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        int larguraEntreAsColunas = 20 ;
        int tamanhoNome = this.nome.toCharArray().length;
        int tamanhoPreço = Double.toString(this.preco).toCharArray().length;
        int  tamanhoQuantidade = this.quantidade;
        String inicio = "| "+this.nome+"";
        StringBuilder sb = new StringBuilder(inicio);

        for (int i = 0; i < larguraEntreAsColunas - tamanhoNome; i++) {
            sb.append(" ");
        }
        sb.append("| "+"R$"+preco);
        for (int i = 0 ; i < larguraEntreAsColunas - tamanhoPreço ;i++){
            sb.append(" ");
        }
        sb.append("| "+quantidade);
        for (int i = 0; i < larguraEntreAsColunas - tamanhoQuantidade; i++) {
           sb.append(" ");
        }
        sb.append('|');
        sb.append('\n');
        for (int i = 0; i < 68; i++) {
            sb.append("_");
        }
        return sb.toString() ;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Double.compare(preco, produto.preco) == 0 && Objects.equals(nome, produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, preco);
    }

    public static final class ProdutoBuilder {
        private String nome;
        private double preco;
        private int quantidade ;
        private ProdutoBuilder() {
        }

        public static ProdutoBuilder builder() {
            return new ProdutoBuilder();
        }

        public ProdutoBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }
        public ProdutoBuilder quantidade(int quantidade){
            this.quantidade = quantidade;
            return this;
        }
        public ProdutoBuilder preco(double preco) {
            this.preco = preco;
            return this;
        }

        public Produto build() {
            Produto produto = new Produto();
            produto.nome = this.nome;
            produto.preco = this.preco;
            produto.quantidade = this.quantidade;
            return produto;
        }
    }
}
