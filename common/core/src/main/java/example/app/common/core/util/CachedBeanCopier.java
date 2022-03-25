package example.app.common.core.util;

import lombok.experimental.UtilityClass;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@UtilityClass
public class CachedBeanCopier {
    private final ConcurrentHashMap<Class<?>, ConcurrentHashMap<Class<?>, BeanCopier>> copierMap = new ConcurrentHashMap<>();

    public void copy(Object source, Object target) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        getCopier(source.getClass(), target.getClass())
                .copy(source, target, null);
    }

    private BeanCopier getCopier(Class<?> sourceClass, Class<?> targetClass) {
        return copierMap.computeIfAbsent(sourceClass, __ -> new ConcurrentHashMap<>())
                .computeIfAbsent(targetClass, __ -> BeanCopier.create(sourceClass, targetClass, false));
    }
}
