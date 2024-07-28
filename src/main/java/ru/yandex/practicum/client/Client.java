package ru.yandex.practicum.client;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class Client {
    private static final String DEFAULT_BASE_URI = "http://qa-scooter.praktikum-services.ru";
    private static final String DEFAULT_BASE_PATH = "/api/v1";
    private static final String CONTENT_TYPE = "application/json";

    private final String baseUri;
    private final String basePath;

    public Client() {
        this.baseUri = System.getenv().getOrDefault("BASE_URI", DEFAULT_BASE_URI);
        this.basePath = System.getenv().getOrDefault("BASE_PATH", DEFAULT_BASE_PATH);
    }

    public RequestSpecification spec() {
        return given()
                .contentType(CONTENT_TYPE)
                .baseUri(baseUri)
                .basePath(basePath);
    }
}
