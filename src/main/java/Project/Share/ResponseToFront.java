package Project.Share;

import java.util.Objects;

public class ResponseToFront  {
   private String msg;
    public ResponseToFront(String msg) {
        this.setMsg(msg);
    }
   public void setMsg(String msg){
        this.msg= msg;
    }
    public String getMsg() {
        return msg;
    }
}
