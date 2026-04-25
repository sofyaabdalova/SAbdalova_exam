import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import static io.restassured.RestAssured.given;

public class BookApiSteps {

    private static final String API_KEY = "bookstore-2026-secret";

    @Step("Шаг: Получить список всех книг")
    public static Response getAllBooks() {
        return given()
                .when()
                .get("/books");
    }

    @Step("Шаг: Создать новую книгу с ISBN {isbn}")
    public static int createBookAndGetId(String isbn, String title, String author, int price) {
        JSONObject body = new JSONObject();
        body.put("isbn", isbn);
        body.put("title", title);
        body.put("author", author);
        body.put("price", price);

        return given()
                .header("X-API-Key", API_KEY)
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .post("/books")
                .then()
                .statusCode(201)
                .extract().path("id");
    }

    @Step("Шаг: Получить книгу по ID {id}")
    public static Response getBookById(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .get("/books/{id}");
    }

    @Step("Шаг: Получить книгу по ISBN {isbn}")
    public static Response getBookByIsbn(String isbn) {
        return given()
                .pathParam("isbn", isbn)
                .when()
                .get("/books/isbn/{isbn}");
    }
}