package tinario9954.gmail.com.crudTeste2.DTOS;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInsertDTO  extends UserDTO {
    private static final long serialVersionUID = 1L;

    private String password;


    UserInsertDTO(){
        super();
    }
}
