package com.fatec.les.loungeliterarioapi.model;

public enum StatusVenda {
    EM_PROCESSAMENTO,
    APROVADO,
    EM_TRANSITO,
    ENTREGUE,
    EM_TROCA,
    TROCA_AUTORIZADA,
    CANCELADO
    ;

    private StatusVenda() {
    }

    public String getStatusVenda() {
        return this.name();
    }

    public static StatusVenda getStatusVenda(String statusVenda) {
        for(StatusVenda status : StatusVenda.values()) {
            if(statusVenda.equals(status.getStatusVenda())) {
                return status;
            }
        }
        return null;
    }

    public static StatusVenda getStatusVenda(int statusVenda) {
        for(StatusVenda status : StatusVenda.values()) {
            if(statusVenda == status.ordinal()) {
                return status;
            }
        }
        return null;
    }

    public static StatusVenda getStatusVenda(StatusVenda statusVenda) {
        for(StatusVenda status : StatusVenda.values()) {
            if(statusVenda.equals(status)) {
                return status;
            }
        }
        return null;
    }

    public static StatusVenda getStatusVenda(StatusVenda statusVenda, int status) {
        for(StatusVenda status1 : StatusVenda.values()) {
            if(statusVenda.equals(status1) && status == status1.ordinal()) {
                return status1;
            }
        }
        return null;
    }

    public static StatusVenda getStatusVenda(StatusVenda statusVenda, String status) {
        for(StatusVenda status1 : StatusVenda.values()) {
            if(statusVenda.equals(status1) && status.equals(status1.getStatusVenda())) {
                return status1;
            }
        }
        return null;
    }

}
