package example.app.domain.social.poster.command;

import example.app.core.i18n.exception.I18nIllegalArgumentException;
import net.bytebuddy.utility.RandomString;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.function.Consumer;

import static example.app.domain.AssertionSupport.thenThrowConstraintViolation;
import static example.app.domain.social.SocialTestFixture.createPosterVo;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.inOrder;

@SpringJUnitConfig(ValidationAutoConfiguration.class)
class PosterCommandServiceTest {
    @SpyBean
    private PosterCommandService unit;
    @MockBean
    private PosterCommandRepository posterCommandRepository;

    @Nested
    class when_create {
        @Disabled
        @Test
        void then_create() {
            final var createPosterVo = createPosterVo(c -> {
            });
            given(posterCommandRepository.isIdOrAccountUsed(anyLong(), anyString())).willReturn(false);
            BDDAssertions.thenNoException()
                    .isThrownBy(() -> unit.create(createPosterVo));
            final var inOrder = inOrder(posterCommandRepository, posterCommandRepository);
            then(posterCommandRepository).should(inOrder).isIdOrAccountUsed(eq(createPosterVo.getId()), eq(createPosterVo.getAccount()));
            then(posterCommandRepository).should(inOrder).create(eq(createPosterVo));
        }

        @Disabled
        @Test
        void given_id_or_account_used() {
            final var createPosterVo = createPosterVo(c -> {
            });
            given(posterCommandRepository.isIdOrAccountUsed(anyLong(), anyString())).willReturn(true);
            BDDAssertions.thenThrownBy(() -> unit.create(createPosterVo))
                    .isInstanceOf(I18nIllegalArgumentException.class)
                    .hasMessage(PosterCommandI18nKey.ALREADY_EXISTS);
            then(posterCommandRepository).should().isIdOrAccountUsed(eq(createPosterVo.getId()), eq(createPosterVo.getAccount()));
            then(posterCommandRepository).shouldHaveNoMoreInteractions();
        }

        @Test
        void given_null_create_poster_vo() {
            // noinspection ConstantConditions
            thenThrowConstraintViolation(() -> unit.create(null));
        }

        @Nested
        class given_invalid_create_poster_vo {
            void assertInvalid(Consumer<CreatePosterVo> setup) {
                thenThrowConstraintViolation(() ->
                        unit.create(createPosterVo(setup))
                );
            }

            @Test
            void given_null_id() {
                assertInvalid(c -> c.setId(null));
            }

            @Test
            void given_invalid_name() {
                assertInvalid(c -> c.setName(null));
                assertInvalid(c -> c.setName(RandomString.make(101)));
            }

            @Test
            void given_invalid_account() {
                assertInvalid(c -> c.setAccount(null));
                assertInvalid(c -> c.setAccount("   "));
                assertInvalid(c -> c.setAccount(RandomString.make(256)));
            }
        }
    }
}