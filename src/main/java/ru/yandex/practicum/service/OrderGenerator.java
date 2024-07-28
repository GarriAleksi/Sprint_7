package ru.yandex.practicum.service;

import ru.yandex.practicum.model.order.Order;

/**
 * Класс для генерации объектов заказа.
 */
public class OrderGenerator {

    /**
     * Создает заказ с указанными параметрами.
     *
     * @param firstName     имя клиента
     * @param lastName      фамилия клиента
     * @param address       адрес доставки
     * @param metroStation  ближайшая станция метро
     * @param phone         телефон клиента
     * @param rentTime      время аренды
     * @param deliveryDate  дата доставки
     * @param comment       комментарий к заказу
     * @param color         цвет самоката
     * @return объект заказа
     */
    public Order getOrder(String firstName, String lastName, String address, String metroStation, String phone,
                          Integer rentTime, String deliveryDate, String comment, String[] color) {
        return Order.builder()
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .metroStation(metroStation)
                .phone(phone)
                .rentTime(rentTime)
                .deliveryDate(deliveryDate)
                .comment(comment)
                .color(color)
                .build();
    }

    /**
     * Создает заказ с предустановленными значениями для быстрого тестирования.
     *
     * @return объект заказа с предустановленными значениями
     */
    public Order getDefaultOrder() {
        return Order.builder()
                .firstName("Default")
                .lastName("User")
                .address("Default Address")
                .metroStation("Default Metro")
                .phone("+1234567890")
                .rentTime(1)
                .deliveryDate("2024-12-31")
                .comment("Default comment")
                .color(new String[]{"BLACK"})
                .build();
    }
}
