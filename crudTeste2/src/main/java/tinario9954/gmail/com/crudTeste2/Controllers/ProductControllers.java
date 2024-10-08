package tinario9954.gmail.com.crudTeste2.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tinario9954.gmail.com.crudTeste2.DTOS.ProductDTO;
import tinario9954.gmail.com.crudTeste2.Services.ProductService;

@RestController
@RequestMapping(value = "/Products")
public class ProductControllers {
    @Autowired
    private ProductService _productService;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy

    ) {
        PageRequest _pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Page<ProductDTO> lista = _productService.findAllPaged(_pageRequest);
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Integer id) {
        ProductDTO resultado = _productService.findById(id);
        return ResponseEntity.ok().body(resultado);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO entity) {
        ProductDTO _entity = _productService.insert(entity);
        return ResponseEntity.ok().body(_entity);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Integer id, @Valid @RequestBody ProductDTO dto) {
        ProductDTO _entity = _productService.update(dto, id);
        return ResponseEntity.ok().body(_entity);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> delectar(@PathVariable Integer id) {
        _productService.delectar(id);
        return ResponseEntity.noContent().build();
    }
}
