package org.exam;

import org.junit.jupiter.api.Test;
import java.util.Map;

public class FinalTest2 {
    protected static ApiMethods api = new ApiMethods();

    @Test
    public void scenario2() {
        ModelBook b = new ModelBook("R-"+System.currentTimeMillis(), "Review Test", "Author", "Genre", 2026, 100, 5, 100);
        int id = api.create(b).jsonPath().getInt("id");

        // 1. Проверить наличие
        api.checkStock(id).then().statusCode(200);

        // 2. Добавить отзыв
        Map<String, Object> review = Map.of("rating", 5, "comment", "Excellent!", "reviewerName", "Student");
        api.addReview(id, review).then().statusCode(201);

        api.delete(id);
    }
}