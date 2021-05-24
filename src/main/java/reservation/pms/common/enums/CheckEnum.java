package reservation.pms.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CheckEnum {
    Y("Yes"),
    N("No");

    private String description;
}