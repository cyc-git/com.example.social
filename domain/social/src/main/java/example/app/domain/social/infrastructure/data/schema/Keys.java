/*
 * This file is generated by jOOQ.
 */
package example.app.domain.social.infrastructure.data.schema;


import example.app.domain.social.infrastructure.data.schema.tables.Article;
import example.app.domain.social.infrastructure.data.schema.tables.ArticleFavorite;
import example.app.domain.social.infrastructure.data.schema.tables.ArticleReply;
import example.app.domain.social.infrastructure.data.schema.tables.ArticleStar;
import example.app.domain.social.infrastructure.data.schema.tables.Poster;
import example.app.domain.social.infrastructure.data.schema.tables.PosterFollow;
import example.app.domain.social.infrastructure.data.schema.tables.records.ArticleFavoriteRecord;
import example.app.domain.social.infrastructure.data.schema.tables.records.ArticleRecord;
import example.app.domain.social.infrastructure.data.schema.tables.records.ArticleReplyRecord;
import example.app.domain.social.infrastructure.data.schema.tables.records.ArticleStarRecord;
import example.app.domain.social.infrastructure.data.schema.tables.records.PosterFollowRecord;
import example.app.domain.social.infrastructure.data.schema.tables.records.PosterRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in 
 * test.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ArticleRecord> KEY_ARTICLE_PRIMARY = Internal.createUniqueKey(Article.ARTICLE, DSL.name("KEY_article_PRIMARY"), new TableField[] { Article.ARTICLE.ID }, true);
    public static final UniqueKey<ArticleFavoriteRecord> KEY_ARTICLE_FAVORITE_PRIMARY = Internal.createUniqueKey(ArticleFavorite.ARTICLE_FAVORITE, DSL.name("KEY_article_favorite_PRIMARY"), new TableField[] { ArticleFavorite.ARTICLE_FAVORITE.ARTICLE_ID, ArticleFavorite.ARTICLE_FAVORITE.FAVORITED_BY }, true);
    public static final UniqueKey<ArticleReplyRecord> KEY_ARTICLE_REPLY_PRIMARY = Internal.createUniqueKey(ArticleReply.ARTICLE_REPLY, DSL.name("KEY_article_reply_PRIMARY"), new TableField[] { ArticleReply.ARTICLE_REPLY.ID }, true);
    public static final UniqueKey<ArticleStarRecord> KEY_ARTICLE_STAR_PRIMARY = Internal.createUniqueKey(ArticleStar.ARTICLE_STAR, DSL.name("KEY_article_star_PRIMARY"), new TableField[] { ArticleStar.ARTICLE_STAR.ARTICLE_ID, ArticleStar.ARTICLE_STAR.STARED_BY }, true);
    public static final UniqueKey<PosterRecord> KEY_POSTER_POSTER_UN = Internal.createUniqueKey(Poster.POSTER, DSL.name("KEY_poster_poster_un"), new TableField[] { Poster.POSTER.ACCOUNT }, true);
    public static final UniqueKey<PosterRecord> KEY_POSTER_PRIMARY = Internal.createUniqueKey(Poster.POSTER, DSL.name("KEY_poster_PRIMARY"), new TableField[] { Poster.POSTER.ID }, true);
    public static final UniqueKey<PosterFollowRecord> KEY_POSTER_FOLLOW_PRIMARY = Internal.createUniqueKey(PosterFollow.POSTER_FOLLOW, DSL.name("KEY_poster_follow_PRIMARY"), new TableField[] { PosterFollow.POSTER_FOLLOW.POSTER_ID, PosterFollow.POSTER_FOLLOW.FOLLOWED_BY }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<ArticleRecord, PosterRecord> ARTICLE_FK = Internal.createForeignKey(Article.ARTICLE, DSL.name("article_FK"), new TableField[] { Article.ARTICLE.POSTER_ID }, Keys.KEY_POSTER_PRIMARY, new TableField[] { Poster.POSTER.ID }, true);
    public static final ForeignKey<ArticleRecord, ArticleRecord> ARTICLE_FK_1 = Internal.createForeignKey(Article.ARTICLE, DSL.name("article_FK_1"), new TableField[] { Article.ARTICLE.SHARING_ARTICLE_ID }, Keys.KEY_ARTICLE_PRIMARY, new TableField[] { Article.ARTICLE.ID }, true);
    public static final ForeignKey<ArticleFavoriteRecord, ArticleRecord> ARTICLE_FAVORITE_FK = Internal.createForeignKey(ArticleFavorite.ARTICLE_FAVORITE, DSL.name("article_favorite_FK"), new TableField[] { ArticleFavorite.ARTICLE_FAVORITE.ARTICLE_ID }, Keys.KEY_ARTICLE_PRIMARY, new TableField[] { Article.ARTICLE.ID }, true);
    public static final ForeignKey<ArticleFavoriteRecord, PosterRecord> ARTICLE_FAVORITE_FK_1 = Internal.createForeignKey(ArticleFavorite.ARTICLE_FAVORITE, DSL.name("article_favorite_FK_1"), new TableField[] { ArticleFavorite.ARTICLE_FAVORITE.FAVORITED_BY }, Keys.KEY_POSTER_PRIMARY, new TableField[] { Poster.POSTER.ID }, true);
    public static final ForeignKey<ArticleReplyRecord, ArticleRecord> ARTICLE_REPLY_FK = Internal.createForeignKey(ArticleReply.ARTICLE_REPLY, DSL.name("article_reply_FK"), new TableField[] { ArticleReply.ARTICLE_REPLY.ARTICLE_ID }, Keys.KEY_ARTICLE_PRIMARY, new TableField[] { Article.ARTICLE.ID }, true);
    public static final ForeignKey<ArticleReplyRecord, PosterRecord> ARTICLE_REPLY_FK_1 = Internal.createForeignKey(ArticleReply.ARTICLE_REPLY, DSL.name("article_reply_FK_1"), new TableField[] { ArticleReply.ARTICLE_REPLY.REPLIED_BY }, Keys.KEY_POSTER_PRIMARY, new TableField[] { Poster.POSTER.ID }, true);
    public static final ForeignKey<ArticleStarRecord, ArticleRecord> ARTICLE_STAR_FK = Internal.createForeignKey(ArticleStar.ARTICLE_STAR, DSL.name("article_star_FK"), new TableField[] { ArticleStar.ARTICLE_STAR.ARTICLE_ID }, Keys.KEY_ARTICLE_PRIMARY, new TableField[] { Article.ARTICLE.ID }, true);
    public static final ForeignKey<ArticleStarRecord, PosterRecord> ARTICLE_STAR_FK_1 = Internal.createForeignKey(ArticleStar.ARTICLE_STAR, DSL.name("article_star_FK_1"), new TableField[] { ArticleStar.ARTICLE_STAR.STARED_BY }, Keys.KEY_POSTER_PRIMARY, new TableField[] { Poster.POSTER.ID }, true);
    public static final ForeignKey<PosterFollowRecord, PosterRecord> POSTER_FOLLOWER_FK = Internal.createForeignKey(PosterFollow.POSTER_FOLLOW, DSL.name("poster_follower_FK"), new TableField[] { PosterFollow.POSTER_FOLLOW.POSTER_ID }, Keys.KEY_POSTER_PRIMARY, new TableField[] { Poster.POSTER.ID }, true);
    public static final ForeignKey<PosterFollowRecord, PosterRecord> POSTER_FOLLOWER_FK_1 = Internal.createForeignKey(PosterFollow.POSTER_FOLLOW, DSL.name("poster_follower_FK_1"), new TableField[] { PosterFollow.POSTER_FOLLOW.FOLLOWED_BY }, Keys.KEY_POSTER_PRIMARY, new TableField[] { Poster.POSTER.ID }, true);
}
