ALTER TABLE `lms_plusplus`.`user`
ADD COLUMN `account_type` VARCHAR(45) NULL AFTER `type`;

INSERT INTO `lms_plusplus`.`api` (`name`, `http_method`, `pattern`, `permission_name`, `is_required_access_token`, `should_check_permission`, `status`) VALUES ('login social', 'POST', '/user/login-social', 'user.loginSocial', '0', '0', 'ACTIVE');
ALTER TABLE `lms_plusplus`.`user`
CHANGE COLUMN `password` `password` VARCHAR(255) NULL ;
