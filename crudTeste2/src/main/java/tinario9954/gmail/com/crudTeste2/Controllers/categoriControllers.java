package tinario9954.gmail.com.crudTeste2.Controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tinario9954.gmail.com.crudTeste2.DTOS.categoryDTO;

import tinario9954.gmail.com.crudTeste2.Services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class categoriControllers {

    @Autowired
    private CategoryService _categoryService;

    @GetMapping
    public ResponseEntity<List<categoryDTO>> findAll() {
        List<categoryDTO> resultado = _categoryService.findAll();
        return ResponseEntity.ok().body(resultado);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<categoryDTO> findById(@PathVariable Integer id) {
        categoryDTO resultado = _categoryService.findById(id);
        return ResponseEntity.ok().body(resultado);
    }

    @PostMapping
    public ResponseEntity<categoryDTO> insert(@RequestBody categoryDTO entity) {
        categoryDTO _entity = _categoryService.insert(entity);
        return ResponseEntity.ok().body(_entity);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<categoryDTO> update(@PathVariable Integer id, @RequestBody categoryDTO dto) {
        categoryDTO _entity = _categoryService.update(dto, id);
        return ResponseEntity.ok().body(_entity);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<categoryDTO> delectar(@PathVariable Integer id) {
        _categoryService.delectar(id);
        return ResponseEntity.noContent().build();
    }
}
