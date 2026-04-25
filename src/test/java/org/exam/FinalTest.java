package org.exam;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.hamcrest.Matchers.*;

public class FinalTest {
    protected static ApiMethods api = new ApiMethods();

    @Test
    public void scenario1() {
        ModelBook myBook = new ModelBook("ISBN-"+System.currentTimeMillis(), "Lifecycle", "Writer", "Classic", 2026, 500, 10, 300);

        // POST
        Response res = api.create(myBook);
        res.then().statusCode(201);
        int id = res.jsonPath().getInt("id");

        // GET by ID, PATCH, STOCK, DELETE
        api.getById(id).then().statusCode(200);
        api.updatePartial(id, Map.of("price", 599)).then().statusCode(200);
        api.checkStock(id).then().statusCode(200).body("inStock", is(true));
        api.delete(id).then().statusCode(204);
    }
}