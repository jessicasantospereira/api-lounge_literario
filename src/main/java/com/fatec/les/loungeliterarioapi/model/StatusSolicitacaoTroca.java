package com.fatec.les.loungeliterarioapi.model;

public enum StatusSolicitacaoTroca {

    EM_TROCA,
    TROCA_AUTORIZADA,
    TROCA_RECUSADA,
    ITENS_RECEBIDOS,
    TROCA_EFETUADA;

    private StatusSolicitacaoTroca() {
    }

    public static StatusSolicitacaoTroca getStatusSolicitacaoTroca(String status) {
        for(StatusSolicitacaoTroca status1 : StatusSolicitacaoTroca.values()) {
            if(status.equals(status1.getStatusSolicitacaoTroca())) {
                return status1;
            }
        }
        return null;
    }

    public String getStatusSolicitacaoTroca() {
        return this.name();
    }
}
