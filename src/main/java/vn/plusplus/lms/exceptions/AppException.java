package vn.plusplus.lms.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class AppException extends RuntimeException{

    private String code;

    private String message;

    private HttpStatus status;

    private Object data;

    public AppException() {
        super();
    }

    public AppException(ErrorCode code) {
        super();
        this.code = code.code();
        this.message = code.message();
        this.status = code.status();
    }

    public AppException(ErrorCode code, Map<?, ?> data) {
        super();
        this.code = code.code();
        this.message = code.message();
        this.status = code.status();
        this.data = data;
    }

    public AppException(ErrorCode code, String... errors) {
        super();
        this.code = code.code();
        this.message = code.message();
        this.status = code.status();

        if (errors != null) {
            Map<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < errors.length / 2; i++) {
                map.put(errors[i * 2], errors[i * 2 + 1]);
            }
            this.data = map;
        }
    }

}
