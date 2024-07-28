package ru.yandex.practicum.model.courier.courierForAuth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import ru.yandex.practicum.model.courier.Courier;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CourierForAuthWithoutLogin {

    /** Пароль курьера */
    private String password;

    /**
     * Конструктор, инициализирующий объект без логина, но с паролем из объекта Courier.
     *
     * @param courier объект Courier, содержащий пароль
     */
    public CourierForAuthWithoutLogin(Courier courier) {
        this.password = courier.getPassword();
    }
}
