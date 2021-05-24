package reservation.pms.common.enums;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumNamePatternValidator.class)
public @interface EnumNamePattern {
    String[] enumCodes();
    boolean nullAble();
    String message() default "Enum value is invalid.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
