package vn.plusplus.lms.exceptions;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    SUCCESS(HttpStatus.OK, "LMS-200", "Thành công"),
    GENERAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "LMS-500", "Thất bại"),
    USER_HAS_NO_PERMISSION(HttpStatus.FORBIDDEN, "LMS-40001", "Bạn không có quyền thực hiện hành động này"),
    AUTHORIZATION_FIELD_MISSING(HttpStatus.FORBIDDEN, "LMS-40011", "Yêu cầu đăng nhập"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "LMS-40012", "Mã truy cập không hợp lệ"),
    ACCESS_TOKEN_EXPIRE(HttpStatus.UNAUTHORIZED, "LMS-40013", "Mã truy cập hết hạn"),
    API_NOT_FOUND(HttpStatus.FORBIDDEN, "LMS-40014", "Truy cập không hợp lệ"),
    USER_NOT_VALID(HttpStatus.BAD_REQUEST, "LMS-40016", "User không hợp lệ"),
    ENTITY_NOT_EXIST(HttpStatus.BAD_REQUEST, "LMS-40017", "Không tìm thấy thực thể"),
    UPLOAD_FILE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "LMS-40019", "Có lỗi khi upload file"),
    UNSUPPORT_FILE_EXTENSION(HttpStatus.BAD_REQUEST, "LMS-40020", "Không hỗ trợ định dạng file này"),
    USER_EXISTED(HttpStatus.BAD_REQUEST, "LMS-40021", "User đã tồn tại"),
    NOT_OWNER_PERMISSION(HttpStatus.BAD_REQUEST, "LMS-40022", "User không có quyền"),
    EMAIL_EXISTED(HttpStatus.BAD_REQUEST, "LMS-40031", "Email đã tồn tại"),
    UNVERIFIED(HttpStatus.BAD_REQUEST, "LMS-40032", "Tài khoản chưa được xác minh"),
    EMAIL_MUST_REQUIRED(HttpStatus.BAD_REQUEST, "LMS-40033", "Bạn chưa nhập email"),
    FULLNAME_MUST_REQUIRED(HttpStatus.BAD_REQUEST, "LMS-40034", "Bạn chưa nhập tên đầy đủ"),
    PASS_MUST_REQUIRED(HttpStatus.BAD_REQUEST, "LMS-40035", "Bạn chưa nhập mật khẩu"),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "LMS-40036", "Người dùng không tồn tại"),
    USERNAME_INVALID(HttpStatus.BAD_REQUEST, "LMS-40037", "Username không hợp lệ"),
    EMAIL_INVALID(HttpStatus.BAD_REQUEST, "LMS-40038", "Email không hợp lệ");


    private final HttpStatus status;
    private String code;
    private String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public HttpStatus status() {
        return status;
    }

    public String message() {
        return message;
    }
}
