package ru.yandex.practicum.service;

import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex.practicum.model.courier.Courier;
import ru.yandex.practicum.model.courier.CourierWithoutLogin;
import ru.yandex.practicum.model.courier.CourierWithoutPassword;
import ru.yandex.practicum.model.courier.courierForAuth.CourierForAuth;
import ru.yandex.practicum.model.courier.courierForAuth.CourierForAuthWithoutLogin;
import ru.yandex.practicum.model.courier.courierForAuth.CourierForAuthWithoutPassword;

/**
 * Класс для генерации объектов курьеров и их вариаций для тестов.
 */
public class CourierGenerator {
    private final String password = "password";
    private final String firstName = "name";

    /**
     * Генерация курьера с случайным логином.
     *
     * @return новый объект курьера
     */
    public Courier getCourier() {
        return Courier.builder()
                .login(RandomStringUtils.randomAlphanumeric(10))
                .password(password)
                .firstName(firstName)
                .build();
    }

    /**
     * Генерация курьера без пароля.
     *
     * @return новый объект курьера без пароля
     */
    public CourierWithoutPassword getCourierWithoutPassword() {
        return new CourierWithoutPassword(RandomStringUtils.randomAlphanumeric(10));
    }

    /**
     * Генерация курьера с пустым паролем.
     *
     * @return новый объект курьера с пустым паролем
     */
    public Courier getCourierWithPasswordEmpty() {
        return Courier.builder()
                .login(RandomStringUtils.randomAlphanumeric(10))
                .password("") // Пустой пароль
                .firstName(firstName)
                .build();
    }

    /**
     * Генерация курьера без логина.
     *
     * @return новый объект курьера без логина
     */
    public CourierWithoutLogin getCourierWithoutLogin() {
        return new CourierWithoutLogin(password);
    }

    /**
     * Генерация курьера с логином равным null.
     *
     * @return новый объект курьера с null-логином
     */
    public Courier getCourierWithLoginNull() {
        return Courier.builder()
                .password(password)
                .build();
    }

    /**
     * Генерация данных для аутентификации курьера.
     *
     * @param courier курьер, для которого генерируются данные
     * @return объект данных для аутентификации курьера
     */
    public CourierForAuth getCourierForAuth(Courier courier) {
        return CourierForAuth.builder()
                .login(courier.getLogin())
                .password(courier.getPassword())
                .build();
    }

    /**
     * Генерация данных для аутентификации курьера без логина.
     *
     * @param courier курьер, для которого генерируются данные
     * @return объект данных для аутентификации курьера без логина
     */
    public CourierForAuthWithoutLogin getCourierForAuthWithoutLogin(Courier courier) {
        return new CourierForAuthWithoutLogin(courier);
    }

    /**
     * Генерация данных для аутентификации курьера без пароля.
     *
     * @param courier курьер, для которого генерируются данные
     * @return объект данных для аутентификации курьера без пароля
     */
    public CourierForAuthWithoutPassword getCourierForAuthWithoutPassword(Courier courier) {
        return new CourierForAuthWithoutPassword(courier);
    }

    /**
     * Генерация данных для аутентификации курьера с логином равным null.
     *
     * @param courier курьер, для которого генерируются данные
     * @return объект данных для аутентификации курьера с null-логином
     */
    public CourierForAuth getCourierForAuthWithLoginNull(Courier courier) {
        return CourierForAuth.builder()
                .password(courier.getPassword())
                .build();
    }

    /**
     * Генерация данных для аутентификации курьера с пустым паролем.
     *
     * @param courier курьер, для которого генерируются данные
     * @return объект данных для аутентификации курьера с пустым паролем
     */
    public CourierForAuth getCourierForAuthWithPasswordEmpty(Courier courier) {
        return CourierForAuth.builder()
                .login(courier.getLogin())
                .password("") // Пустой пароль
                .build();
    }
}
