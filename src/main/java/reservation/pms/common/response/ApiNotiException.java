package reservation.pms.common.response;

import lombok.Getter;
import reservation.pms.common.enums.ExceptionEnum;

@Getter
public class ApiNotiException extends RuntimeException {
    private ExceptionEnum error;

    public ApiNotiException(ExceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }

    public ApiNotiException(ExceptionEnum e, String msg) {
        super(msg);
        this.error = e;
    }
}
