package ru.yandex.practicum.model.courier;

import lombok.*;

/**
 * Представляет сущность курьера без пароля, используется для определенных операций, где пароль не требуется.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CourierWithoutPassword {
    /**
     * Логин курьера, используемый для идентификации.
     */
    private String login;
}
