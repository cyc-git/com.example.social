package example.app.core.i18n.exception;

public class I18nIllegalArgumentException extends I18nRuntimeException {
    public I18nIllegalArgumentException(String i18nKey) {
        super(i18nKey);
    }
}
