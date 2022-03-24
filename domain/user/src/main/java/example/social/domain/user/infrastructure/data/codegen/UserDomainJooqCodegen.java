package example.social.domain.user.infrastructure.data.codegen;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;
import org.jooq.meta.mysql.MySQLDatabase;

public class UserDomainJooqCodegen {

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
                )
                .withTarget(
                        new Target()
                                .withPackageName("example.social.domain.user.infrastructure.data.schema")
                                .withDirectory("domain/user/src/main/java")
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
