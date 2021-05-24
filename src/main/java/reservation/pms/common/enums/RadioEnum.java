package reservation.pms.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RadioEnum {
    OPTION1( 1 ),
    OPTION2( 2 ),
    OPTION3( 3 );

    private Integer colValue;
}