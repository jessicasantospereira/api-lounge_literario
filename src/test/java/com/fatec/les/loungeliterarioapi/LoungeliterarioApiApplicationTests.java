package com.fatec.les.loungeliterarioapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LoungeliterarioApiApplicationTests {

    @Test
    @DisplayName("Deve executar método main")
    void deve_executar_metodo_main() {
        assertDoesNotThrow(() -> LoungeliterarioApiApplication.main(new String[] { "--spring.profiles.active=test" }));
    }
}
