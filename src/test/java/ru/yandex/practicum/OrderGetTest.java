package ru.yandex.practicum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;
import ru.yandex.practicum.client.order.OrderClient;
import ru.yandex.practicum.model.order.Order;
import ru.yandex.practicum.service.OrderGenerator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

@Slf4j
public class OrderGetTest {
    private static final String RESPONSE = "Получен ответ от сервера: {}";
    private static final String FIELD_MESSAGE = "message";
    private static final String MESSAGE_ORDER_NOT_FOUND = "Заказ не найден";
    private static final String MESSAGE_NOT_ENOUGH_DATA = "Недостаточно данных для поиска";
    private static final String FIELD_TRACK = "track";

    private final OrderClient orderClient = new OrderClient();
    private final OrderGenerator generator = new OrderGenerator();
    private final String firstName = "Имя";
    private final String lastName = "Фамилия";
    private final String address = "Адрес";
    private final String metroStation = "Красные ворота";
    private final String phone = "79991231212";
    private final Integer rentTime = 2;
    private final String deliveryDate = "2023-07-17";
    private final String comment = "комментарий?";
    private final String[] color = new String[] {"black"};
    private final UtilMethods util = new UtilMethods();
    private Order order;
    private Integer trackId;

    @After
    public void delete() {
        if (trackId != null && trackId > 0) {
            log.info("Отмена заказа с номером: {}", trackId);
            util.cancelOrder(trackId);
            log.info("Заказ с номером {} успешно отменен", trackId);
        } else {
            log.info("Нет активных заказов для отмены");
        }
    }

    @Test
    @DisplayName("Получение заказа по номеру")
    public void getOrderByNumber() {
        log.info("Создание заказа для проверки");
        createOrder();
        log.info("Создан заказ с номером: {}", trackId);

        Response response = orderClient.getOrderByNumber(trackId);
        log.info(RESPONSE, response.body().asString());

        Order responseOrder = response.body().jsonPath().getObject("order", Order.class);
        log.info("Создан объект заказа: {}", responseOrder);

        assertEquals("Полученный заказ не соответствует запрошенному", order, responseOrder);
    }

    @Test
    @DisplayName("Получение заказа по несуществующему номеру")
    public void getOrderByNonExistentNumber() {
        Integer failTrackId = Integer.MAX_VALUE;
        log.info("Попытка получения заказа с несуществующим номером: {}", failTrackId);

        Response response = orderClient.getOrderByNumber(failTrackId);
        log.info(RESPONSE, response.body().asString());

        response.then().statusCode(HttpStatus.SC_NOT_FOUND)
                .and().body(FIELD_MESSAGE, equalTo(MESSAGE_ORDER_NOT_FOUND));
    }

    @Test
    @DisplayName("Получение заказа без указания номера")
    public void getOrderWithoutNumber() {
        log.info("Попытка получения заказа без указания номера");

        Response response = orderClient.getOrderByNumberWithoutNumber();
        log.info(RESPONSE, response.body().asString());

        response.then().statusCode(HttpStatus.SC_BAD_REQUEST)
                .and().body(FIELD_MESSAGE, equalTo(MESSAGE_NOT_ENOUGH_DATA));
    }

    private void createOrder() {
        order = generator.getOrder(firstName, lastName, address, metroStation, phone,
                rentTime, deliveryDate, comment, color);
        log.info("Создание заказа с данными: {}", order);

        Response response = orderClient.createOrder(order);
        trackId = response.body().path(FIELD_TRACK);
        log.info("Номер созданного заказа: {}", trackId);
    }
}
