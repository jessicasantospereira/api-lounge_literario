package com.fatec.les.loungeliterarioapi.projection;

import java.time.LocalDate;

public interface VendasProjection {

    String getNome();

    LocalDate getMes();

    Long getQuantidade();

}
