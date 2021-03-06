package Project.ExceptionHandler;


public class CustomException extends RuntimeException {
   String error;
    String exception;
    String message;
    String customMsg;

    public CustomException(String customMsg) {
        this.customMsg = customMsg;
    }
    public CustomException(
            String error, String exception, String message, String customMsg) {
        this.error = error;
        this.exception= exception;
        this.message = message;
        this.customMsg = customMsg;
    }



    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getCustomMsg() {
        return customMsg;
    }

    public void setCustomMsg(String customMsg) {
        this.customMsg = customMsg;
    }

}