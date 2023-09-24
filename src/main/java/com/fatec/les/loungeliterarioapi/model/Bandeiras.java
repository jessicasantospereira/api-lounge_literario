package com.fatec.les.loungeliterarioapi.model;

public enum Bandeiras {
    VISA("Visa"),
    MASTERCARD("Mastercard"),
    ELO("Elo"),
    AMEX("American Express"),
    HIPERCARD("Hipercard"),
    DINERS("Diners Club");

    private String descricao;
    Bandeiras(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
