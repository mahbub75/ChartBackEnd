package Project.Share;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.SimpleDateFormat;
@Service
public class CurrentDateTime {
    public String value(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy HH mm ss");
        Date date = new Date();
        return formatter.format(date);
    }
}
