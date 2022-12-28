package payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {

    private boolean isSuccess;
    private String errorMessage;
}
