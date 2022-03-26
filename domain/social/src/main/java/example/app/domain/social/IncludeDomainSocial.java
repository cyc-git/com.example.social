package example.app.domain.social;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

@ComponentScan(basePackageClasses = IncludeDomainSocial.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface IncludeDomainSocial {
}
