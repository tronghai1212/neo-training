package vn.plusplus.lms.factory;

public class ResponseStatus {

    public static String SUCCESS_CODE = "LMS-200";
    public static String SUCCESS_MESSAGE = "Success";
    public static String GENERAL_ERROR_MESSAGE = "Error happened";
    public static String GENERAL_ERROR_CODE = "LMS-40000";

    public static final ResponseStatus SUCCESS_STATUS = new ResponseStatus(SUCCESS_CODE, SUCCESS_MESSAGE);

    private String code;
    private String message;

    public ResponseStatus() {
    }

    public ResponseStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
