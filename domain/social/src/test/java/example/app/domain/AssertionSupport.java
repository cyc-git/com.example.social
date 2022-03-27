package example.app.domain;

import org.assertj.core.api.BDDAssertions;
import org.assertj.core.api.ThrowableAssert;

import javax.validation.ConstraintViolationException;

public class AssertionSupport {

    public static void thenThrowConstraintViolation(ThrowableAssert.ThrowingCallable throwingCallable) {
        BDDAssertions.thenThrownBy(throwingCallable)
                .isInstanceOf(ConstraintViolationException.class);
    }
}
