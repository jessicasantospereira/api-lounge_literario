package com.fatec.les.loungeliterarioapi.usecase.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@SqlGroup({
        @Sql(value = "classpath:db/admin/insert-admin.sql",
                executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS),
        @Sql(value = "classpath:db/admin/delete-admin.sql",
                executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS),
})
class ListarProdutosVendidosPorDataTest {

    @Autowired
    private ListarProdutosVendidosPorData usecase;

    @Test
    void deve_listar_vendas_por_data() {
        var result = usecase.execute("2025-03-01", "2025-03-30");
        assertFalse(result.isEmpty());
        assertEquals(5, result.size());
    }

}