package com.ismoke.domain.models;

import com.ismoke.domain.validations.Validacao;
import com.ismoke.domain.validations.Validador;
import com.ismoke.domain.validations.vendedor.ValidaCnpj;
import com.ismoke.domain.validations.vendedor.ValidaEmail;
import com.ismoke.domain.validations.vendedor.ValidaNomeSemPalavroes;
import com.ismoke.domain.validations.vendedor.ValidacaoNome;
import java.util.List;

public class Vendedor {
    private final String cnpj;
    private final String nome;
    private final String email;

    public Vendedor(String cnpj, String nome, String email) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.email = email;

        List<Validacao<Vendedor>> validacoes = List.of(
            new ValidacaoNome(),
            new ValidaCnpj(),
            new ValidaEmail(),
            new ValidaNomeSemPalavroes()
        );

        Validador<Vendedor> validadorVendedor = new Validador<>(validacoes);
        validadorVendedor.validar(this);
    }

    // Getters
    public String getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
