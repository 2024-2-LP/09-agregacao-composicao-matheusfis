package school.sptech;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Biblioteca {
    private String nome;
    private List<Livro> livros;

    public Biblioteca() {
        livros = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarLivro(Livro livro) {
        if (livro != null &&
                livro.getTitulo() != null &&
                !livro.getTitulo().isBlank() &&
                livro.getAutor() != null &&
                !livro.getAutor().isBlank() &&
                livro.getDataPublicacao() != null) {
            livros.add(livro);
        }
    }

    public void removerLivroPorTitulo(String titulo) {
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                livros.remove(i);
            }
        }
    }

    public Livro buscarLivroPorTitulo(String titulo) {

        for (Livro livro : livros) {
            if (titulo != null && livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public Integer contarLivros() {
        return livros.size();
    }

    public List<Livro> obterLivrosAteAno(Integer ano) {
        List<Livro> livrosFiltrados = new ArrayList<>();

        for (Livro livro : livros) {
            if (livro.getDataPublicacao().getYear() <= ano) {
                livrosFiltrados.add(livro);
            }
        }

        return livrosFiltrados;
    }

    public List<Livro> retornarTopCincoLivros() {
        Livro aux;
        int n = livros.size();

        // Bubble Sort para ordenar a lista de livros com base na média de avaliações
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                double mediaLivroAtual = livros.get(j).getAvaliacoes().stream().mapToDouble(Avaliacao::getQtdEstrelas).average().orElse(0);
                double mediaProximoLivro = livros.get(j + 1).getAvaliacoes().stream().mapToDouble(Avaliacao::getQtdEstrelas).average().orElse(0);

                // Verifica se o livro atual tem média menor que o próximo livro
                if (mediaLivroAtual < mediaProximoLivro) {
                    // Faz a troca
                    aux = livros.get(j);
                    livros.set(j, livros.get(j + 1));
                    livros.set(j + 1, aux);
                }
            }
        }

        // Retorna os cinco primeiros livros, ou todos se houver menos de 5
        return livros.size() > 5 ? livros.subList(0, 5) : livros;
    }

}
//        return livros.stream()
//                .sorted((l1, l2) -> Double.compare(l2.calcularMediaAvaliacoes(),
//                        l1.calcularMediaAvaliacoes()))
//                .limit(5)
//                .collect(Collectors.toList());
