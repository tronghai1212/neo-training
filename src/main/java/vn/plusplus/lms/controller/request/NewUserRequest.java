package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.plusplus.lms.controller.users.UserController;
import vn.plusplus.lms.exceptions.AppException;
import vn.plusplus.lms.exceptions.ErrorCode;

@Getter
@Setter
public class NewUserRequest {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private String fullName;
    private String email;
    private String userName;
    private String password;

    public void checkRegex(NewUserRequest newUserRequest){
        String userNameRegex = "^(?=.{4,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
            if (newUserRequest.getUserName().matches(userNameRegex)==false){
                logger.error("Username  invalid");
                throw new AppException(ErrorCode.USERNAME_INVALID);
            }
        String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        if (newUserRequest.getEmail().matches(emailRegex)==false){
            logger.error("Email  invalid");
            throw new AppException(ErrorCode.EMAIL_INVALID);
        }
    }
}
