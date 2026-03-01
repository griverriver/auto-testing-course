package ru.courses;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTests {
    int id = 1;

    @BeforeEach
    public void prepare() {
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .pathParam("id", id)
                .when()
                .delete("/student/{id}")
                .then();
    }

    //1. get /student/{id} возвращает JSON студента с указанным ID и заполненным именем, если такой есть в базе, код 200.
    @Test
    void getJsonStudent200() {
        Student student = new Student(id, "Raily");
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(student)
                .when()
                .post("/student")
                .then()
                .statusCode(201);
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .pathParam("id", id)
                .when()
                .get("/student/{id}")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(id))
                .body("name", Matchers.notNullValue());
    }

    //2. get /student/{id} возвращает код 404, если студента с данным ID в базе нет.
    @Test
    void getJsonStudent404() {
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .pathParam("id", id)
                .when()
                .get("/student/{id}")
                .then()
                .statusCode(404);
    }

    //3. post /student добавляет студента в базу, если студента с таким ID ранее не было, при этом имя заполнено, код 201.
    @Test
    void postJsonAddStudent201() {
        Student student = new Student(id, "Raily");
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(student)
                .when()
                .post("/student")
                .then()
                .statusCode(201);
    }

    //4. post /student обновляет студента в базе, если студент с таким ID ранее был, при этом имя заполнено, код 201.
    @Test
    void postJsonUpdateStudent201() {
        Student student = new Student(id, "Ashlan");
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(student)
                .when()
                .post("/student")
                .then()
                .statusCode(201);
        student.setName("Naomi");
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(student)
                .when()
                .post("/student")
                .then()
                .statusCode(201);
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .pathParam("id", id)
                .when()
                .get("/student/{id}")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(id))
                .body("name", Matchers.equalTo("Naomi"));
    }

    //5. post /student добавляет студента в базу, если ID null, то возвращается назначенный ID, код 201.
    @Test
    void postJsonAddStudentWithNullId201() {
        String response = RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body("""
                        {
                        "id": null,
                        "name": "Sarah"
                        }
                        """)
                .when()
                .post("/student")
                .then()
                .statusCode(201)
                .extract()
                .asString();
        int newId = Integer.parseInt(response.trim());
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .pathParam("id", newId)
                .when()
                .delete("/student/{id}");
    }

    //6. post /student возвращает код 400, если имя не заполнено.
    @Test
    void postJsonAddStudentWithoutName400() {
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body("""
                        {
                        "id": 1,
                        "name": null
                        }
                        """)
                .when()
                .post("/student")
                .then()
                .statusCode(400);
//        RestAssured.given()
//                .baseUri("http://localhost:8080")
//                .contentType("application/json")
//                .body("""
//                        {
//                        "id": 1,
//                        "name": ""
//                        }
//                        """)
//                .when()
//                .post("/student")
//                .then()
//                .statusCode(400);
    }

    //7. delete /student/{id} удаляет студента с указанным ID из базы, код 200.
    @Test
    void deleteStudent200() {
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body("""
                        {
                        "id": 1,
                        "name": "Monika"
                        }
                        """)
                .when()
                .post("/student")
                .then()
                .statusCode(201);
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .pathParam("id", id)
                .when()
                .delete("/student/{id}")
                .then()
                .statusCode(200);
    }

    //8. delete /student/{id} возвращает код 404, если студента с таким ID в базе нет.
    @Test
    void deleteNonExistentStudent404() {
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .pathParam("id", id)
                .when()
                .delete("/student/{id}")
                .then()
                .statusCode(404);
    }

    //9. get /topStudent код 200 и пустое тело, если студентов в базе нет.
    @Test
    void getTopStudentWithNoStudents200() {
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .when()
                .get("/topStudent")
                .then()
                .statusCode(200)
                .body(Matchers.emptyString());
    }

    //10. get /topStudent код 200 и пустое тело, если ни у кого из студентов в базе нет оценок.
    @Test
    void getTopStudentWithNoMarks200() {
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body("""
                        {
                        "id": 1,
                        "name": "Robin",
                        "marks": []
                        }
                        """)
                .when()
                .post("/student")
                .then()
                .statusCode(201);
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .when()
                .get("/topStudent")
                .then()
                .statusCode(200)
                .body(Matchers.emptyString());
    }

    //11. get /topStudent код 200 и один студент, если у него максимальная средняя оценка,
    // либо же среди всех студентов с максимальной средней у него их больше всего.
    @Test
    void getTopStudentWithMoreMarks200() {
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body("""
                        {
                        "id": 10,
                        "name": "Kevin",
                        "marks": [4]
                        }
                        """)
                .when()
                .post("/student")
                .then()
                .statusCode(201);
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body("""
                        {
                        "id": 50,
                        "name": "Emily",
                        "marks": [5]
                        }
                        """)
                .when()
                .post("/student")
                .then()
                .statusCode(201);
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body("""
                        {
                        "id": 1,
                        "name": "John",
                        "marks": [5,5,5,5,5,5,5]
                        }
                        """)
                .when()
                .post("/student")
                .then()
                .statusCode(201);
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body("""
                        {
                        "id": 34,
                        "name": "Lucky",
                        "marks": [5,5,5]
                        }
                        """)
                .when()
                .post("/student")
                .then()
                .statusCode(201);
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .when()
                .get("/topStudent")
                .then()
                .statusCode(200)
                .body("[0].name", Matchers.equalTo("John"));
        List.of(10, 50, 1, 34).forEach(id ->
                RestAssured.given()
                        .pathParam("id", id)
                        .delete("/student/{id}")
        );
    }

    //12. get /topStudent код 200 и несколько студентов, если у них всех эта оценка максимальная
    // и при этом они равны по количеству оценок.
    @Test
    void getTopStudentWithEqualMarks200() {
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body("""
                        {
                        "id": 1,
                        "name": "Kevin",
                        "marks": [5,5,5]
                        }
                        """)
                .when()
                .post("/student")
                .then()
                .statusCode(201);
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body("""
                        {
                        "id": 2,
                        "name": "Emily",
                        "marks": [5,5,5,5]
                        }
                        """)
                .when()
                .post("/student")
                .then()
                .statusCode(201);
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body("""
                        {
                        "id": 3,
                        "name": "John",
                        "marks": [5,5,4]
                        }
                        """)
                .when()
                .post("/student")
                .then()
                .statusCode(201);
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body("""
                        {
                        "id": 4,
                        "name": "Lucky",
                        "marks": [5,5,5,5]
                        }
                        """)
                .when()
                .post("/student")
                .then()
                .statusCode(201);
        RestAssured.given()
                .baseUri("http://localhost:8080")
                .when()
                .get("/topStudent")
                .then()
                .statusCode(200)
                .body("$", Matchers.hasSize(2))
                .body("name", Matchers.hasItems("Lucky", "Emily"));
        List.of(1, 2, 3, 4).forEach(id ->
                RestAssured.given()
                        .pathParam("id", id)
                        .delete("/student/{id}")
        );
    }
}