package ru.yandex.practicum.model.courier;

import lombok.*;

/**
 * Представляет сущность курьера без логина, используется для определенных операций, где логин не требуется.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CourierWithoutLogin {
    /**
     * Пароль курьера, используемый для аутентификации.
     */
    private String password;
}
