package ru.yandex.practicum.model.courier.courierForAuth;

import lombok.*;
import ru.yandex.practicum.model.courier.Courier;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CourierForAuthWithoutPassword {
    private String login;
    private String password = ""; // Устанавливаем пустую строку по умолчанию

    // Конструктор, который инициализирует поле login из объекта Courier
    public CourierForAuthWithoutPassword(Courier courier) {
        this.login = courier.getLogin();
        // Поле password намеренно оставлено пустым
    }
}
