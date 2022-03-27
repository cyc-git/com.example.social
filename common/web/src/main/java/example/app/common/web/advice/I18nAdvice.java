package example.app.common.web.advice;

import example.app.core.i18n.exception.I18nForbiddenException;
import example.app.core.i18n.exception.I18nIllegalArgumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
@ControllerAdvice
public class I18nAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(I18nIllegalArgumentException.class)
    public @ResponseBody
    Map<String, Object> handleIllegalArgument(I18nIllegalArgumentException e, ServletWebRequest request) {
        return toResponse(e.getI18nKey(), request, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(I18nForbiddenException.class)
    public @ResponseBody
    Map<String, Object> handleForbidden(I18nForbiddenException e, ServletWebRequest request) {
        return toResponse(e.getI18nKey(), request, HttpStatus.FORBIDDEN);
    }

    Map<String, Object> toResponse(String i18nKey, ServletWebRequest request, HttpStatus status) {
        final var result = new LinkedHashMap<String, Object>();
        result.put("timestamp", new Date());
        result.put("path", request.getRequest().getRequestURI());
        result.put("status", status.value());
        result.put("message", i18nKey); // current simply return i18n key
        return result;
    }
}
