package example.app.core.i18n;

import example.app.core.i18n.exception.I18nIllegalArgumentException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class I18nAsserts {

    public void notNull(Object object, String i18nKey) {
        if (object == null) {
            throw new I18nIllegalArgumentException(i18nKey);
        }
    }

    public void isTrue(boolean expression, String i18nKey) {
        if (!expression) {
            throw new I18nIllegalArgumentException(i18nKey);
        }
    }

    public void isFalse(boolean expression, String i18nKey) {
        if (expression) {
            throw new I18nIllegalArgumentException(i18nKey);
        }
    }
}
