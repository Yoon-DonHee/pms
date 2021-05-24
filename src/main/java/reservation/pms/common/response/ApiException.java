package reservation.pms.common.response;

import lombok.Getter;
import reservation.pms.common.enums.ExceptionEnum;

@Getter
public class ApiException extends RuntimeException {
    private ExceptionEnum error;

    public ApiException(ExceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }

    public ApiException(ExceptionEnum e, String msg) {
        super(msg);
        this.error = e;
    }
}
