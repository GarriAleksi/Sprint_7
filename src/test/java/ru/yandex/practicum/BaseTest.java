package ru.yandex.practicum;

import org.junit.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.practicum.model.courier.Courier;

public class BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected Courier courier;
    private final UtilMethods util = new UtilMethods();

    @After
    public void deleteCourier() {
        if (courier != null) {
            try {
                util.deleteCourier(courier);
                logger.info("Courier with login {} was successfully deleted.", courier.getLogin());
            } catch (Exception e) {
                logger.error("Failed to delete courier with login {}: {}", courier.getLogin(), e.getMessage());
            }
        } else {
            logger.info("No courier to delete.");
        }
    }
}
