package tinario9954.gmail.com.crudTeste2.DTOS;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import tinario9954.gmail.com.crudTeste2.Models.User;

@Getter
@Setter
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    Set<RoleDTOS> rolesDTO = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        entity.getRoles().forEach(x -> this.rolesDTO.add(new RoleDTOS(x)));
    }

    public UserDTO(Integer id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


    
}
