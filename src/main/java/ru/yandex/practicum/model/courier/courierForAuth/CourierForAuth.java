package ru.yandex.practicum.model.courier.courierForAuth;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CourierForAuth {

    /** Логин курьера */
    private String login;

    /** Пароль курьера */
    private String password;
}
