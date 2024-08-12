package tinario9954.gmail.com.crudTeste2.DTOS;

import lombok.Getter;
import lombok.Setter;
import tinario9954.gmail.com.crudTeste2.Models.Category;

@Getter
@Setter
public class    categoryDTO {

    private Integer id;
    private String name;

    public categoryDTO() {
    }

    public categoryDTO(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

}
