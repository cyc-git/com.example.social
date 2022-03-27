INSERT INTO user (id, name, account, created_at, updated_at, deleted_at) VALUES (1, 'test1', 'test1', 1648307731133, 1648307731133, null);
INSERT INTO user (id, name, account, created_at, updated_at, deleted_at) VALUES (2, 'test2', 'test2', 1648307737763, 1648307737763, null);
INSERT INTO user (id, name, account, created_at, updated_at, deleted_at) VALUES (3, 'test3', 'test3', 1648307742400, 1648307742400, null);
INSERT INTO user (id, name, account, created_at, updated_at, deleted_at) VALUES (4, 'test4', 'test4', 1648307745918, 1648307745918, null);

INSERT INTO user_password (user_id, password) VALUES (1, '$2a$10$YGpB6Iq/pZLfLJquenWpK.SjJ7SN98jlS4QmiomDQwCVtHtcjJXyS');
INSERT INTO user_password (user_id, password) VALUES (2, '$2a$10$B482b8IdiT6fliPbKsdqhuI6sZGchN8INIkuPtzapVHIEOqqtTnjC');
INSERT INTO user_password (user_id, password) VALUES (3, '$2a$10$u/Qqrbv74mEnpu2XRfwod.9yc5qvw5mRE6AdLSD3lGkm3.dmZeHla');
INSERT INTO user_password (user_id, password) VALUES (4, '$2a$10$pXwMu13rh/DrUL0rFf7Rnu/GAT6HJOhSPpqmOxR2NQWmnHWfkiOaC');

INSERT INTO poster (id, name, account, deleted_at) VALUES (1, 'test1', 'test1', null);
INSERT INTO poster (id, name, account, deleted_at) VALUES (2, 'test2', 'test2', null);
INSERT INTO poster (id, name, account, deleted_at) VALUES (3, 'test3', 'test3', null);
INSERT INTO poster (id, name, account, deleted_at) VALUES (4, 'test4', 'test4', null);

INSERT INTO poster_follow (poster_id, followed_by, followed_at) VALUES (1, 2, 1648307875049);
INSERT INTO poster_follow (poster_id, followed_by, followed_at) VALUES (2, 1, 1648307809145);
INSERT INTO poster_follow (poster_id, followed_by, followed_at) VALUES (3, 1, 1648307810509);
INSERT INTO poster_follow (poster_id, followed_by, followed_at) VALUES (4, 2, 1648324270363);

INSERT INTO article (id, poster_id, content, posted_at, updated_at, sharing_article_id) VALUES (1, 1, 'an apple a day', 1648310883920, 1648310883920, null);
INSERT INTO article (id, poster_id, content, posted_at, updated_at, sharing_article_id) VALUES (2, 1, 'an apple a dayyyyyyyyyyy', 1648310883997, 1648310883997, null);
INSERT INTO article (id, poster_id, content, posted_at, updated_at, sharing_article_id) VALUES (3, 1, 'keep doctor away', 1648310929968, 1648310929968, 1);
INSERT INTO article (id, poster_id, content, posted_at, updated_at, sharing_article_id) VALUES (4, 2, 'keep doctor awayyyyyyyyyyy', 1648310929988, 1648310929988, 1);
INSERT INTO article (id, poster_id, content, posted_at, updated_at, sharing_article_id) VALUES (5, 4, 'You should use the verb favourite and not favour. Favour means show an approval or preference for, while favourite means record to enable quick access. It is true that you favourite a video on a website if you like it, but the sense you want to convey here is not that you like the video but that you mark the video in some way. Of course, the past tense of favourite is favourited.', 1648322990324, 1648322990324, null);

INSERT INTO poster_favorite (poster_id, article_id, favorited_at) VALUES (2, 1, 1648324608172);
INSERT INTO poster_favorite (poster_id, article_id, favorited_at) VALUES (2, 2, 1648324572388);
INSERT INTO poster_favorite (poster_id, article_id, favorited_at) VALUES (2, 5, 1648324586059);
INSERT INTO poster_favorite (poster_id, article_id, favorited_at) VALUES (3, 5, 1648323064514);

INSERT INTO article_star (article_id, stared_by, stared_at) VALUES (1, 1, 1648313484608);
INSERT INTO article_star (article_id, stared_by, stared_at) VALUES (1, 3, 1648313454847);
INSERT INTO article_star (article_id, stared_by, stared_at) VALUES (1, 4, 1648313472356);
INSERT INTO article_star (article_id, stared_by, stared_at) VALUES (5, 2, 1648324302818);
INSERT INTO article_star (article_id, stared_by, stared_at) VALUES (5, 3, 1648323083066);

INSERT INTO article_reply (id, article_id, content, replied_by, replied_at) VALUES (1, 1, 'No appleeeeeeeeee', 3, 1648312624826);
INSERT INTO article_reply (id, article_id, content, replied_by, replied_at) VALUES (2, 1, 'Bad appleeeeeeeeee!', 3, 1648312691644);
INSERT INTO article_reply (id, article_id, content, replied_by, replied_at) VALUES (3, 5, 'The linguistic twits at Wiktionary also gave into cultural necrosis, and listed favorite as a verb there – with (gasp!) two meanings, not just one. As the internet becomes more ubiquitous and an integral part of our daily lives, it only follows that language would evolve. (When I was a kid, cookies were something you ate for desert, and viruses were caught on the playground.) Not so long ago, spell checkers would put red lines under newfangled jargon, like website. Now, if you''ll excuse me, I''m going to favorite this question. :^)', 3, 1648323125109);
