package ru.yandex.practicum.model.order;

import lombok.*;

/**
 * Класс, представляющий заказ.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "deliveryDate")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    /**
     * Имя заказчика.
     */
    private String firstName;

    /**
     * Фамилия заказчика.
     */
    private String lastName;

    /**
     * Адрес доставки.
     */
    private String address;

    /**
     * Ближайшая станция метро к адресу доставки.
     */
    private String metroStation;

    /**
     * Телефонный номер заказчика.
     */
    private String phone;

    /**
     * Время аренды в днях.
     */
    private Integer rentTime;

    /**
     * Дата доставки.
     */
    private String deliveryDate;

    /**
     * Комментарий к заказу.
     */
    private String comment;

    /**
     * Цвета самоката, указанные в заказе.
     */
    private String[] color;
}
