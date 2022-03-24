package example.social.common.web.dto;

import lombok.Data;

@Data
public class ResponseDto<T> {
    private final T data;

    public static <D> ResponseDto<D> of(D data) {
        return new ResponseDto<>(data);
    }
}
