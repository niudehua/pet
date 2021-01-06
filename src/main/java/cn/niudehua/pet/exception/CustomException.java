package cn.niudehua.pet.exception;

/**
 * 自定义异常类
 */
public class CustomException extends Exception {

    public CustomException() {
    }

    public CustomException(String message) {
        this.message = message;
    }

    // 异常信息
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
