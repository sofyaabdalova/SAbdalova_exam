import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import static org.hamcrest.Matchers.*;

@Epic("Экзамен")
@Feature("Тесты BookStore API")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookStoreAllTests {

    private static int createdId;
    private static final String TEST_ISBN = "978-TEST-" + System.currentTimeMillis();

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://10.82.196.214";
        RestAssured.port = 8085;
        // Чтобы в Allure были видны детали запросов
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    @Order(1)
    @DisplayName("Проверка получения списка всех книг")
    void testGetAllBooks() {
        BookApiSteps.getAllBooks()
                .then()
                .statusCode(200)
                .body("books", notNullValue());
    }

    @Test
    @Order(2)
    @DisplayName("Создание книги и сохранение её ID")
    void testCreateBook() {
        createdId = BookApiSteps.createBookAndGetId(TEST_ISBN, "Java Testing", "Muravev", 500);
        Assertions.assertTrue(createdId > 0, "ID должен быть больше нуля");
    }

    @Test
    @Order(3)
    @DisplayName("Поиск созданной книги по ID")
    void testGetBookById() {
        BookApiSteps.getBookById(createdId)
                .then()
                .statusCode(200)
                .body("id", equalTo(createdId))
                .body("isbn", equalTo(TEST_ISBN));
    }

    @Test
    @Order(4)
    @DisplayName("Поиск книги по ISBN")
    void testGetBookByIsbn() {
        BookApiSteps.getBookByIsbn(TEST_ISBN)
                .then()
                .statusCode(200)
                .body("isbn", equalTo(TEST_ISBN));
    }
}