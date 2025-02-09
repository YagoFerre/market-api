package yago.ferreira.marketapi.domain.model;

public class FileInput {
    private String nome;
    private byte[] conteudo;

    public FileInput() {
    }

    public FileInput(String nome, byte[] conteudo) {
        this.nome = nome;
        this.conteudo = conteudo;
    }
}
