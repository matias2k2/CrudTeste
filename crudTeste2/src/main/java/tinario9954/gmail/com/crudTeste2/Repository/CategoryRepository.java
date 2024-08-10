package tinario9954.gmail.com.crudTeste2.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tinario9954.gmail.com.crudTeste2.Models.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
