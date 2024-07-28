package ru.yandex.practicum.model.courier;

import lombok.*;

/**
 * Представляет сущность курьера с необходимыми полями для аутентификации и персональной информации.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Courier {
    /**
     * Логин курьера, используемый для аутентификации.
     */
    private String login;

    /**
     * Пароль курьера, используемый для аутентификации.
     */
    private String password;

    /**
     * Имя курьера.
     */
    private String firstName;
}
