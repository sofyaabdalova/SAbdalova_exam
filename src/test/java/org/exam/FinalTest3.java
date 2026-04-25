package org.exam;

import org.junit.jupiter.api.Test;
import java.util.Map;

public class FinalTest3 {
    protected static ApiMethods api = new ApiMethods();

    @Test
    public void scenario3() {
        // Фильтр по жанру
        api.getBooks(Map.of("genre", "Classic")).then().statusCode(200);

        // Пагинация
        api.getBooks(Map.of("page", 0, "size", 5)).then().statusCode(200);

        // Фильтр по цене
        api.getBooks(Map.of("minPrice", 400, "maxPrice", 600)).then().statusCode(200);
    }
}