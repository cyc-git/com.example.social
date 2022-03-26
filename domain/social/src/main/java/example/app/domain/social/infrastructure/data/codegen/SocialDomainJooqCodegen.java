package example.app.domain.social.infrastructure.data.codegen;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;
import org.jooq.meta.mysql.MySQLDatabase;

public class SocialDomainJooqCodegen {

    public static void main(String[] args) throws Exception {
        final var config = new Configuration()
                .withJdbc(jdbc())
                .withGenerator(generator());
        GenerationTool.generate(config);
    }

    static Generator generator() {
        return new Generator()
                .withDatabase(
                        new Database()
                                .withName(MySQLDatabase.class.getName())
                                .withInputSchema("test")
                                .withIncludes(String.join("|",
                                        "article",
                                        "article_reply",
                                        "article_star",
                                        "article_favorite",
                                        "poster",
                                        "poster_follow"
                                ))
                )
                .withTarget(
                        new Target()
                                .withPackageName("example.app.domain.social.infrastructure.data.schema")
                                .withDirectory("domain/social/src/main/java")
                                .withEncoding("UTF-8")
                )
                .withGenerate(
                        new Generate()
                                .withPojos(true)
                                .withDaos(true)
                );
    }

    static Jdbc jdbc() {
        return new Jdbc()
                .withUrl("jdbc:mysql://localhost:3306/")
                .withUser("admin")
                .withPassword("123456");
    }
}
