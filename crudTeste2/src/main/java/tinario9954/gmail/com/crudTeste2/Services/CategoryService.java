package tinario9954.gmail.com.crudTeste2.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import tinario9954.gmail.com.crudTeste2.DTOS.categoryDTO;
import tinario9954.gmail.com.crudTeste2.Models.Category;
import tinario9954.gmail.com.crudTeste2.Repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository _categoryRepository;

    @Transactional
    public List<categoryDTO> findAll() {
        if (null == _categoryRepository) {
            throw new IllegalStateException("CategoryRepository não foi injetado corretamente");
        }
        List<Category> resulte = _categoryRepository.findAll();
        return resulte.stream().map(x -> new categoryDTO(x)).collect(Collectors.toList());
    }

    // findById

    @Transactional
    public categoryDTO findById(Integer id) {
        Optional<Category> obj = _categoryRepository.findById(id);
        Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entidade nao encontrada"));
        return new categoryDTO(entity);
    }

    @Transactional
    public categoryDTO insert(categoryDTO entity) {
        Category _entity = new Category();
        _entity.setId(entity.getId());
        _entity.setName(entity.getName());
        _entity = _categoryRepository.save(_entity);
        return new categoryDTO(_entity);
    }

    public categoryDTO update(categoryDTO entity, Integer id) {
        try {
            // atualizar os dados
            Category _entity = _categoryRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Categoria nao Encontrada "));
            _entity.setId(entity.getId());
            _entity.setName(entity.getName());
            _entity = _categoryRepository.save(_entity);
            return new categoryDTO(_entity);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Erro ao atualizar categoria: " + e.getMessage());
        }

    }

    public void delectar(Integer id) {
        try {

            _categoryRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            // Lança uma exceção personalizada se o cliente não for encontrado
            throw new UnsupportedOperationException("Unimplemented method 'deletar'");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integridade inválida");
        }
    }

}
