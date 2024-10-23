package school.sptech;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Livro {
    private String titulo;
    private String autor;
    private LocalDate dataPublicacao;
    private static List<Avaliacao> avaliacoes;

    public Livro() {
        avaliacoes = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void adicionarAvaliacao(String descricao, Double qtdEstrelas) {
        if (descricao != null &&
                !descricao.isBlank() &&
                qtdEstrelas != null &&
                (qtdEstrelas >= 0 && qtdEstrelas <= 5)) {
            Avaliacao avaliacao = new Avaliacao(descricao, qtdEstrelas);
            avaliacoes.add(avaliacao);
        }
    }

    public static Double calcularMediaAvaliacoes() {
        if (avaliacoes.isEmpty()) {
            return 0.0;
        }

        Double mediaAvaliacoes = 0.0;

        for (Avaliacao avaliacao : avaliacoes) {
            mediaAvaliacoes += avaliacao.getQtdEstrelas();
        }

        mediaAvaliacoes /= avaliacoes.size();
        return mediaAvaliacoes;
    }


    @Override
    public String toString() {
        return "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", avaliacoes=" + avaliacoes;
    }
}
