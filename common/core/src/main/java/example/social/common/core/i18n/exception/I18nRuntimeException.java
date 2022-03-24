package example.social.common.core.i18n.exception;

import lombok.Getter;

public class I18nRuntimeException extends RuntimeException {
    @Getter
    private final String i18nKey;

    public I18nRuntimeException(String i18nKey) {
        super(i18nKey);
        this.i18nKey = i18nKey;
    }
}
