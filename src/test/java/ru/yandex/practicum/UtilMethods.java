package ru.yandex.practicum;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import ru.yandex.practicum.client.courier.CourierClient;
import ru.yandex.practicum.client.order.OrderClient;
import ru.yandex.practicum.model.courier.Courier;
import ru.yandex.practicum.model.courier.courierForAuth.CourierForAuth;
import ru.yandex.practicum.service.CourierGenerator;

import static org.hamcrest.CoreMatchers.equalTo;

@Slf4j
public class UtilMethods {
    public static final String FIELD_ID = "id";
    private static final String FIELD_OK = "ok";
    private static final String RESPONSE = "Получен ответ от сервера: {}";
    private final CourierClient courierClient = new CourierClient();
    private final OrderClient orderClient = new OrderClient();
    private final CourierGenerator generator = new CourierGenerator();

    /**
     * Получает ID курьера по его данным.
     *
     * @param courier объект курьера с данными для авторизации
     * @return ID авторизованного курьера
     */
    public Integer getCourierId(Courier courier) {
        CourierForAuth courierForAuth = generator.getCourierForAuth(courier);
        log.info("Авторизация курьера с логином \"{}\" и паролем \"{}\" на сервере",
                courierForAuth.getLogin(), courierForAuth.getPassword());

        Response response = courierClient.login(courierForAuth);
        log.info(RESPONSE, response.body().asString());
        Integer courierId = response.body().path(FIELD_ID);
        log.info("Курьер с id = {} успешно авторизован", courierId);
        return courierId;
    }

    /**
     * Удаляет курьера по его объекту.
     *
     * @param courier объект курьера, который нужно удалить
     */
    public void deleteCourier(Courier courier) {
        Integer courierId = getCourierId(courier);
        log.info("Удаление созданного ранее курьера с id = {}", courierId);
        Response response = courierClient.deleteCourier(courierId);
        log.info(RESPONSE, response.body().asString());

        response.then().assertThat().body(FIELD_OK, equalTo(true))
                .and().statusCode(HttpStatus.SC_OK);
        log.info("Курьер с id = {} успешно удален\n", courierId);
    }

    /**
     * Отменяет заказ по его ID.
     *
     * @param id ID заказа, который нужно отменить
     */
    public void cancelOrder(Integer id) {
        Response response = orderClient.cancelOrder(id);

        response.then().assertThat().body(FIELD_OK, equalTo(true))
                .and().statusCode(HttpStatus.SC_OK);
        log.info("Заказ с id = {} успешно отменен\n", id);
    }
}
