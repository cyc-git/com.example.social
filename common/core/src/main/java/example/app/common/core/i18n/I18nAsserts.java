package example.app.common.core.i18n;

import example.app.common.core.i18n.exception.I18nIllegalArgumentException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class I18nAsserts {

    void notNull(Object object, String i18nKey) {
        if (object == null) {
            throw new I18nIllegalArgumentException(i18nKey);
        }
    }

    void isTrue(boolean expression, String i18nKey) {
        if (!expression) {
            throw new I18nIllegalArgumentException(i18nKey);
        }
    }
}
