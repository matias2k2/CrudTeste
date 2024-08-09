package tinario9954.gmail.com.crudTeste2.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tinario9954.gmail.com.crudTeste2.Models.Category;
import tinario9954.gmail.com.crudTeste2.Repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository _categoryRepository;

    @Transactional
    public List<Category> findAll() {
        if(null == _categoryRepository){
            throw new IllegalStateException("CategoryRepository não foi injetado corretamente");
        }
            return _categoryRepository.findAll();
        
    }

}
