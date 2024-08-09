package tinario9954.gmail.com.crudTeste2.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tinario9954.gmail.com.crudTeste2.Models.Category;
import tinario9954.gmail.com.crudTeste2.Services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class categoriControllers {

    @Autowired
    private CategoryService _categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> resultado = _categoryService.findAll();
        return ResponseEntity.ok().body(resultado);
    }
}
