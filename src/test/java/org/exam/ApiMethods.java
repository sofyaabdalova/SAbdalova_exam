package org.exam;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class ApiMethods {
    private static final String BASE_URL = "http://localhost:8085";
    private static final String API_KEY = "bookstore-2026-secret";

    // 1. Фильтрация и пагинация (GET /books)
    public Response getBooks(Map<String, ?> params) {
        return given().baseUri(BASE_URL).queryParams(params).get("/books");
    }

    // 2. Получить по ID (GET /books/{id})
    public Response getById(int id) {
        return given().baseUri(BASE_URL).get("/books/{id}", id);
    }

    // 3. Получить по ISBN (GET /books/isbn/{isbn})
    public Response getByIsbn(String isbn) {
        return given().baseUri(BASE_URL).get("/books/isbn/{isbn}", isbn);
    }

    // 4. Создать книгу (POST /books)
    public Response create(ModelBook book) {
        return given().baseUri(BASE_URL).header("X-API-Key", API_KEY)
                .contentType(ContentType.JSON).body(book).post("/books");
    }

    // 5. Полное обновление (PUT /books/{id})
    public Response updateFull(int id, ModelBook book) {
        return given().baseUri(BASE_URL).header("X-API-Key", API_KEY)
                .contentType(ContentType.JSON).body(book).put("/books/{id}", id);
    }

    // 6. Частичное обновление (PATCH /books/{id})
    public Response updatePartial(int id, Map<String, ?> updates) {
        return given().baseUri(BASE_URL).header("X-API-Key", API_KEY)
                .contentType(ContentType.JSON).body(updates).patch("/books/{id}", id);
    }

    // 7. Удалить книгу (DELETE /books/{id})
    public Response delete(int id) {
        return given().baseUri(BASE_URL).header("X-API-Key", API_KEY).delete("/books/{id}", id);
    }

    // 8. Проверить наличие (GET /books/{id}/stock)
    public Response checkStock(int id) {
        return given().baseUri(BASE_URL).get("/books/{id}/stock", id);
    }

    // 9. Добавить отзыв (POST /books/{id}/reviews)
    public Response addReview(int id, Map<String, ?> review) {
        return given().baseUri(BASE_URL).contentType(ContentType.JSON).body(review).post("/books/{id}/reviews", id);
    }

    // 10. Получить отзывы (GET /books/{id}/reviews)
    public Response getReviews(int id) {
        return given().baseUri(BASE_URL).get("/books/{id}/reviews", id);
    }
}