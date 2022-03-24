package example.social.common.core.i18n.exception;

public class I18nForbiddenException extends I18nRuntimeException {
    public I18nForbiddenException(String i18nKey) {
        super(i18nKey);
    }
}
