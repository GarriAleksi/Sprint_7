package ru.yandex.practicum.client.order;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.practicum.client.Client;
import ru.yandex.practicum.model.order.Order;

public class OrderClient extends Client {
    private static final String ROOT = "/orders";
    private static final String CANCEL = "/cancel";
    private static final String TRACK = "/track";

    @Step("Создание заказа")
    public Response createOrder(final Order order) {
        return postRequest(ROOT, order);
    }

    @Step("Завершение заказа")
    public Response cancelOrder(final Integer id) {
        return spec()
                .queryParam("track", id)
                .put(ROOT + CANCEL);
    }

    @Step("Получение списка заказов")
    public Response getOrders() {
        return getRequest(ROOT);
    }

    @Step("Получение заказа по номеру")
    public Response getOrderByNumber(final Integer trackId) {
        return spec()
                .queryParam("t", trackId)
                .get(ROOT + TRACK);
    }

    @Step("Получение заказа без номера")
    public Response getOrderByNumberWithoutNumber() {
        return getRequest(ROOT + TRACK);
    }

    private Response postRequest(final String url, final Object body) {
        return spec()
                .body(body)
                .when()
                .post(url);
    }

    private Response getRequest(final String url) {
        return spec()
                .get(url);
    }
}
