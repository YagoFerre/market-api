package yago.ferreira.marketapi.adapters.in.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisterRequest {

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String senha;

    public RegisterRequest() {
    }

    public RegisterRequest(String email, String nome, String senha) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }

    public @NotNull @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@NotNull @NotBlank String email) {
        this.email = email;
    }

    public @NotNull @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotNull @NotBlank String nome) {
        this.nome = nome;
    }

    public @NotNull @NotBlank String getSenha() {
        return senha;
    }

    public void setSenha(@NotNull @NotBlank String senha) {
        this.senha = senha;
    }

}
