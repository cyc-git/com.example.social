package example.social.domain.user.infrastructure.data.repository;

import example.social.domain.user.UserRepository;
import example.social.domain.user.UserVo;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static example.social.domain.user.infrastructure.data.schema.Tables.USER;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
    private final DSLContext ctx;

    @Override
    public Optional<UserVo> findByAccount(String account) {
        return Optional.ofNullable(
                ctx.selectFrom(USER)
                        .where(USER.ACCOUNT.eq(account))
                        .and(USER.DELETED_AT.isNull())
                        .fetchOneInto(UserVo.class)
        );
    }
}
