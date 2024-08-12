package tinario9954.gmail.com.crudTeste2.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import tinario9954.gmail.com.crudTeste2.DTOS.ProductDTO;
import tinario9954.gmail.com.crudTeste2.DTOS.categoryDTO;
import tinario9954.gmail.com.crudTeste2.Models.Category;
import tinario9954.gmail.com.crudTeste2.Models.Product;
import tinario9954.gmail.com.crudTeste2.Repository.CategoryRepository;
import tinario9954.gmail.com.crudTeste2.Repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository _produtoctrepositorio;

    @Autowired
    private CategoryRepository _categoryRepository;

    @Transactional
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {

        Page<Product> resulte = _produtoctrepositorio.findAll(pageRequest);
        return resulte.map(x -> new ProductDTO(x));
    }

    // findById

    @Transactional
    public ProductDTO findById(Integer id) {
        Optional<Product> obj = _produtoctrepositorio.findById(id);
        Product entity = obj.orElseThrow(() -> new EntityNotFoundException("Entidade nao encontrada"));
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO entity) {
        Product _entity = new Product();
        copyDtoEntity(entity, _entity);
        _entity = _produtoctrepositorio.save(_entity);
        return new ProductDTO(_entity);
    }

    public ProductDTO update(ProductDTO entity, Integer id) {
        try {
            // atualizar os dados
            Product _entity = _produtoctrepositorio.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Produto/os nao Encontrada "));
            copyDtoEntity(entity, _entity);
            _entity = _produtoctrepositorio.save(_entity);
            return new ProductDTO(_entity);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Erro ao atualizar categoria: " + e.getMessage());
        }

    }

    public void delectar(Integer id) {
        try {

            _produtoctrepositorio.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            // Lança uma exceção personalizada se o cliente não for encontrado
            throw new UnsupportedOperationException("Unimplemented method 'deletar'");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integridade inválida");
        }
    }

    public void copyDtoEntity(ProductDTO entity, Product _entity) {
        entity.setId(entity.getId());
        _entity.setName(entity.getName());
        _entity.setDescription(entity.getDescription());
        _entity.setPrice(entity.getPrice());

        _entity.getCategories().clear();
        for (categoryDTO catDTO : entity.get_categoryDTOs()) {
            Category _categoria = _categoryRepository.getReferenceById(catDTO.getId());
            _entity.getCategories().add(_categoria);
        }
    }
}
