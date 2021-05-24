package reservation.pms.common.enums;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class EnumNamePatternValidator implements ConstraintValidator<EnumNamePattern, Enum<?>> {

    private Pattern pattern;
    private Boolean nullAble = false;

    @Override
    public void initialize(EnumNamePattern annotation) {
        try {

            nullAble = annotation.nullAble();

            StringBuilder sb = new StringBuilder();

            sb.append( annotation.enumCodes()[0] );
            for( int i=1, len = annotation.enumCodes().length; i<len; i++ ) {
                sb.append( "|" ).append( annotation.enumCodes()[i] );
            }
            pattern = Pattern.compile( sb.toString() );

        } catch ( PatternSyntaxException e ) {
            throw new IllegalArgumentException("Given regex is invalid", e);
        }
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if (value == null) {
            return nullAble;
        }

        Matcher m = pattern.matcher(value.name());
        return m.matches();
    }
}
