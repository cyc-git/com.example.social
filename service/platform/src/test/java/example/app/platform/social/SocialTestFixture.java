package example.app.platform.social;


import example.app.domain.social.article.ArticleVo;
import example.app.domain.social.poster.PosterVo;
import example.app.domain.social.poster.command.CreatePosterVo;
import example.app.domain.social.poster.favorite.PosterFavoriteVo;
import example.app.domain.social.poster.follow.PosterFollowVo;
import example.app.platform.social.application.dto.output.ArticleDto;
import example.app.platform.social.application.dto.output.PosterDto;
import net.bytebuddy.utility.RandomString;

import java.util.Random;
import java.util.function.Consumer;

/**
 * Simply copy from domain for demo purpose.
 * In a real world usage should consider using some trick such as gradle "testFixturesImplementation" to reduce duplicate code.
 */
public class SocialTestFixture {

    private static final Random random = new Random();

    public static PosterVo posterVo(Consumer<PosterVo> setup) {
        final var vo = new PosterVo();
        vo.setId(random.nextLong());
        vo.setName(RandomString.make(50));
        vo.setAccount(RandomString.make(100));
        vo.setFavoritedCount(random.nextInt(100));
        vo.setFollowerCount(random.nextInt(100));
        vo.setFollowedCount(random.nextInt(100));
        setup.accept(vo);
        return vo;
    }

    public static CreatePosterVo createPosterVo(Consumer<CreatePosterVo> setup) {
        final var vo = new CreatePosterVo();
        vo.setId(random.nextLong());
        vo.setName(RandomString.make(50));
        vo.setAccount(RandomString.make(100));
        setup.accept(vo);
        return vo;
    }

    public static PosterFavoriteVo posterFavoriteVo(Consumer<PosterFavoriteVo> setup) {
        final var vo = new PosterFavoriteVo();
        vo.setPosterId(random.nextLong());
        vo.setArticleId(random.nextLong());
        vo.setFavoritedAt(random.nextLong());
        setup.accept(vo);
        return vo;
    }

    public static PosterFollowVo posterFollowVo(Consumer<PosterFollowVo> setup) {
        final var vo = new PosterFollowVo();
        vo.setPosterId(random.nextLong());
        vo.setFollowedBy(random.nextLong());
        vo.setFollowedAt(random.nextLong());
        setup.accept(vo);
        return vo;
    }

    public static ArticleVo articleVo(Consumer<ArticleVo> setup) {
        final var vo = new ArticleVo();
        vo.setId(random.nextLong());
        vo.setPosterId(random.nextLong());
        vo.setContent(RandomString.make(100));
        vo.setPostedAt(random.nextLong());
        vo.setUpdatedAt(random.nextLong());
        vo.setStarCount(random.nextInt(100));
        vo.setReplyCount(random.nextInt(100));
        vo.setStarCount(random.nextInt(100));
        setup.accept(vo);
        return vo;
    }

    public static ArticleDto articleDto(Consumer<ArticleDto> setup) {
        final var dto = new ArticleDto(articleVo(a -> {
        }));
        setup.accept(dto);
        return dto;
    }

    public static PosterDto posterDto(Consumer<PosterDto> setup) {
        final var dto = new PosterDto(posterVo(p -> {
        }));
        setup.accept(dto);
        return dto;
    }
}
