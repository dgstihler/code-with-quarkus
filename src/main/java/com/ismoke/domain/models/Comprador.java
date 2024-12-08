package com.ismoke.domain.models;

import com.ismoke.domain.validations.Validacao;
import com.ismoke.domain.validations.Validador;
import com.ismoke.domain.validations.comprador.ValidaCpf;
import com.ismoke.domain.validations.comprador.ValidaEmail;
import com.ismoke.domain.validations.comprador.ValidaNomeSemPalavroes;
import com.ismoke.domain.validations.comprador.ValidacaoNome;
import java.util.List;

public class Comprador {
    private final String cpf;
    private final String nome;
    private final String email;

    public Comprador(String cpf, String nome, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;

        List<Validacao<Comprador>> validacoes = List.of(
            new ValidaCpf(),
            new ValidacaoNome(),
            new ValidaEmail(),
            new ValidaNomeSemPalavroes()
        );

        Validador<Comprador> validadorComprador = new Validador<>(validacoes);
        validadorComprador.validar(this);
    }

    // Getters
    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
