package example.social.domain.user.infrastructure.data.repository;

import example.social.domain.user.auth.UserAuthRepository;
import example.social.domain.user.infrastructure.data.schema.tables.daos.UserPasswordDao;
import example.social.domain.user.infrastructure.data.schema.tables.pojos.UserPassword;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserAuthRepositoryImpl extends UserPasswordDao implements UserAuthRepository {
    private final DSLContext ctx;

    @PostConstruct
    void init() {
        this.setConfiguration(ctx.configuration());
    }

    @Override
    public Optional<String> findPassword(long userId) {
        return Optional.ofNullable(this.findById(userId))
                .map(UserPassword::getPassword);
    }
}
