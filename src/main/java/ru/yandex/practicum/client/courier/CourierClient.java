
package ru.yandex.practicum.client.courier;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.practicum.client.Client;
import ru.yandex.practicum.model.courier.Courier;
import ru.yandex.practicum.model.courier.CourierWithoutLogin;
import ru.yandex.practicum.model.courier.CourierWithoutPassword;
import ru.yandex.practicum.model.courier.courierForAuth.CourierForAuth;
import ru.yandex.practicum.model.courier.courierForAuth.CourierForAuthWithoutLogin;
import ru.yandex.practicum.model.courier.courierForAuth.CourierForAuthWithoutPassword;

public class CourierClient extends Client {
    private static final String ROOT = "/courier";
    private static final String LOGIN = "/login";

    @Step("Создание курьера")
    public Response create(final Courier courier) {
        return postRequest(ROOT, courier);
    }

    @Step("Создание курьера без поля password")
    public Response createWithoutPassword(final CourierWithoutPassword courierWithoutPassword) {
        return postRequest(ROOT, courierWithoutPassword);
    }

    @Step("Создание курьера без поля login")
    public Response createWithoutLogin(final CourierWithoutLogin courierWithoutLogin) {
        return postRequest(ROOT, courierWithoutLogin);
    }

    @Step("Авторизация курьера")
    public Response login(final CourierForAuth courierForAuth) {
        return postRequest(ROOT + LOGIN, courierForAuth);
    }

    @Step("Авторизация без поля login")
    public Response loginWithoutLogin(final CourierForAuthWithoutLogin courierForAuthWithoutLogin) {
        return postRequest(ROOT + LOGIN, courierForAuthWithoutLogin);
    }

    @Step("Авторизация без поля password")
    public Response loginWithoutPassword(final CourierForAuthWithoutPassword courierForAuthWithoutPassword) {
        return postRequest(ROOT + LOGIN, courierForAuthWithoutPassword);
    }

    @Step("Удаление курьера")
    public Response deleteCourier(final Integer courierId) {
        return spec()
                .delete(ROOT + String.format("/%d", courierId));
    }

    private Response postRequest(final String url, final Object body) {
        return spec()
                .body(body)
                .when()
                .post(url);
    }
}
