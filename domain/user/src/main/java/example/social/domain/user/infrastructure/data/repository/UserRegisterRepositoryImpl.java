package example.social.domain.user.infrastructure.data.repository;

import example.social.common.core.util.CachedBeanCopier;
import example.social.domain.user.UserVo;
import example.social.domain.user.infrastructure.data.schema.tables.daos.UserDao;
import example.social.domain.user.infrastructure.data.schema.tables.daos.UserPasswordDao;
import example.social.domain.user.infrastructure.data.schema.tables.pojos.User;
import example.social.domain.user.infrastructure.data.schema.tables.pojos.UserPassword;
import example.social.domain.user.register.RegisterUserVo;
import example.social.domain.user.register.UserRegisterRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import static example.social.domain.user.infrastructure.data.schema.Tables.USER;

@RequiredArgsConstructor
@Repository
public class UserRegisterRepositoryImpl implements UserRegisterRepository {
    private final DSLContext ctx;

    private UserDao userDao;
    private UserPasswordDao userPasswordDao;

    @PostConstruct
    void init() {
        userDao = new UserDao(ctx.configuration());
        userPasswordDao = new UserPasswordDao(ctx.configuration());
    }

    @Transactional
    @Override
    public UserVo register(RegisterUserVo registerUserVo, long time) {
        final var user = new User();
        CachedBeanCopier.copy(registerUserVo, user);
        user.setCreatedAt(time);
        user.setUpdatedAt(time);
        userDao.insert(user);
        final var userPassword = new UserPassword();
        userPassword.setUserId(user.getId());
        userPassword.setPassword(registerUserVo.getPassword());
        userPasswordDao.insert(userPassword);
        final var userVo = new UserVo();
        CachedBeanCopier.copy(user, userVo);
        return userVo;
    }

    @Override
    public boolean isAccountUsed(String account) {
        return ctx.fetchCount(
                ctx.select(USER.ACCOUNT)
                        .from(USER)
                        .where(USER.ACCOUNT.eq(account))
        ) != 0;
    }
}
