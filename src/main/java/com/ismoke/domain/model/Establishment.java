package com.ismoke.domain.model;

public class Establishment {
    private final String id;
    private final String name;
    private final String cnpj;

    private Establishment(EstablishmentBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.cnpj = builder.cnpj;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public static EstablishmentBuilder builder() {
        return new EstablishmentBuilder();
    }

    public static class EstablishmentBuilder {
        private String id;
        private String name;
        private String cnpj;

        public EstablishmentBuilder id(String id) {
            this.id = id;
            return this;
        }

        public EstablishmentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public EstablishmentBuilder cnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        public Establishment build() {
            return new Establishment(this);
        }
    }
}