package ru.yandex.practicum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.Test;
import ru.yandex.practicum.client.courier.CourierClient;
import ru.yandex.practicum.model.courier.Courier;
import ru.yandex.practicum.model.courier.CourierWithoutLogin;
import ru.yandex.practicum.model.courier.CourierWithoutPassword;
import ru.yandex.practicum.model.order.Order;
import ru.yandex.practicum.service.CourierGenerator;

import static org.hamcrest.CoreMatchers.equalTo;

@Slf4j
public class CourierCreateTest extends BaseTest {
    private static final String FIELD_MESSAGE = "message";
    private static final String FIELD_OK = "ok";
    private static final String MESSAGE_EXISTING_LOGIN = "Этот логин уже используется. Попробуйте другой.";
    private static final String MESSAGE_WITHOUT_REQUIRED_FIELDS = "Недостаточно данных для создания учетной записи";
    private static final String RESPONSE = "Получен ответ от сервера: {}";
    private static final String CREATE_COURIER = "Создание курьера с логином {}";

    private final CourierGenerator generator = new CourierGenerator();
    private final CourierClient courierClient = new CourierClient();

    @Test
    @DisplayName("Создание курьера с параметрами логин, пароль, имя")
    public void createCourier() {
        courier = generator.getCourier();
        log.info(CREATE_COURIER, courier.getLogin());

        Response response = courierClient.create(courier);
        log.info(RESPONSE, response.body().asString());

        response.then().assertThat().statusCode(HttpStatus.SC_CREATED)
                .and().body(FIELD_OK, equalTo(true));
    }

    @Test
    @DisplayName("Создание курьера с существующим логином")
    public void createCourierWithExistingLogin() {
        courier = generator.getCourier();
        log.info(CREATE_COURIER, courier.getLogin());

        Response response = courierClient.create(courier);
        log.info(RESPONSE, response.body().asString());

        log.info("Повторное создание курьера с логином {}", courier.getLogin());
        Response conflictResponse = courierClient.create(courier);
        log.info(RESPONSE, conflictResponse.body().asString());

        conflictResponse.then().statusCode(HttpStatus.SC_CONFLICT)
                .and().body(FIELD_MESSAGE, equalTo(MESSAGE_EXISTING_LOGIN));
    }

    @Test
    @DisplayName("Создание курьера без поля password")
    public void createCourierWithoutPassword() {
        CourierWithoutPassword courierWithoutPassword = generator.getCourierWithoutPassword();
        log.info(CREATE_COURIER, courierWithoutPassword);

        Response response = courierClient.createWithoutPassword(courierWithoutPassword);
        log.info(RESPONSE, response.body().asString());

        response.then().statusCode(HttpStatus.SC_BAD_REQUEST)
                .and().body(FIELD_MESSAGE, equalTo(MESSAGE_WITHOUT_REQUIRED_FIELDS));
    }

    // Другие тесты аналогично
}