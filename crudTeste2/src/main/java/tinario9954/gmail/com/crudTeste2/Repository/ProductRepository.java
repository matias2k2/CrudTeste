package tinario9954.gmail.com.crudTeste2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tinario9954.gmail.com.crudTeste2.Models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
