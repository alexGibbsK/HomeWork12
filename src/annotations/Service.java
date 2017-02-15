package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ПК on 15.02.2017.
 */

@Target(value  = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Service {
    String type() default "Annotation name";
}
