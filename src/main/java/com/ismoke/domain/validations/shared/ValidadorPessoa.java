package com.ismoke.domain.validations.shared;

public class ValidadorPessoa {
    private ValidadorPessoa() {
    }

    public static boolean emailValido(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }

    public static boolean cpfValido(String cpf) {
        String regex = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|^\\d{11}$";
        return cpf.matches(regex);
    }

    public static boolean CNPJValido(String cnpj) {
        String regex = "^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$|^\\d{14}$";
        return cnpj.matches(regex);
    }

    public static boolean validarNomeSemPalavroes(String nome) {
        String regex = "(?i)(fuck)"; // Substitua pelos palavrões reais
        return !nome.matches(".*" + regex + ".*");
    }
}
