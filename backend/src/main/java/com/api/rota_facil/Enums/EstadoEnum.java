package com.api.rota_facil.Enums;

public enum EstadoEnum {
    AC("Acre", "AC"),
    AL("Alagoas", "AL"),
    AP("Amapá", "AP"),
    AM("Amazonas", "AM"),
    BA("Bahia", "BA"),
    CE("Ceará", "CE"),
    DF("Distrito Federal", "DF"),
    ES("Espírito Santo", "ES"),
    GO("Goiás", "GO"),
    MA("Maranhão", "MA"),
    MT("Mato Grosso", "MT"),
    MS("Mato Grosso do Sul", "MS"),
    MG("Minas Gerais", "MG"),
    PA("Pará", "PA"),
    PB("Paraíba", "PB"),
    PR("Paraná", "PR"),
    PE("Pernambuco", "PE"),
    PI("Piauí", "PI"),
    RJ("Rio de Janeiro", "RJ"),
    RN("Rio Grande do Norte", "RN"),
    RS("Rio Grande do Sul", "RS"),
    RO("Rondônia", "RO"),
    RR("Roraima", "RR"),
    SC("Santa Catarina", "SC"),
    SP("São Paulo", "SP"),
    SE("Sergipe", "SE"),
    TO("Tocantins", "TO");

    private final String nome;
    private final String sigla;

    EstadoEnum(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public static EstadoEnum fromSigla(String sigla) {
        for (EstadoEnum estado : EstadoEnum.values()) {
            if (estado.getSigla().equalsIgnoreCase(sigla)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado não encontrado para a sigla: " + sigla);
    }

    public static EstadoEnum fromNome(String nome) {
        for (EstadoEnum estado : EstadoEnum.values()) {
            if (estado.getNome().equalsIgnoreCase(nome)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado não encontrado para o nome: " + nome);
    }
}
