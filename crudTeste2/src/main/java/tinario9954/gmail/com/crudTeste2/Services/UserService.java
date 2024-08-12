package tinario9954.gmail.com.crudTeste2.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import tinario9954.gmail.com.crudTeste2.DTOS.RoleDTOS;
import tinario9954.gmail.com.crudTeste2.DTOS.UserDTO;
import tinario9954.gmail.com.crudTeste2.DTOS.UserInsertDTO;
import tinario9954.gmail.com.crudTeste2.Models.Role;
import tinario9954.gmail.com.crudTeste2.Models.User;
import tinario9954.gmail.com.crudTeste2.Repository.RoleRepository;
import tinario9954.gmail.com.crudTeste2.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private RoleRepository _roleRepository;

    @Transactional
    public Page<UserDTO> findAllPaged(PageRequest  pageRequest) {

        Page<User> resulte = _userRepository.findAll(pageRequest);
        return resulte.map(x -> new UserDTO(x));
    }

    // findById

    @Transactional
    public UserDTO findById(Integer id) {
        Optional<User> obj = _userRepository.findById(id);
        User entity = obj.orElseThrow(() -> new EntityNotFoundException("Entidade nao encontrada"));
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO insert(UserInsertDTO entity) {
        User _entity = new User();
        copyDtoEntity(entity, _entity);
        _entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        _entity = _userRepository.save(_entity);
        return new UserDTO(_entity);
    }

    public UserDTO update(UserDTO entity, Integer id) {
        try {
            // atualizar os dados
            User _entity = _userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Produto/os nao Encontrada "));
            copyDtoEntity(entity, _entity);
            _entity = _userRepository.save(_entity);
            return new UserDTO(_entity);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Erro ao atualizar categoria: " + e.getMessage());
        }

    }

    public void delectar(Integer id) {
        try {
            _userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // Lança uma exceção personalizada se o cliente não for encontrado
            throw new UnsupportedOperationException("Unimplemented method 'deletar'");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integridade inválida");
        }
    }

    public void copyDtoEntity(UserDTO entity, User _entity) {
        entity.setId(entity.getId());
        _entity.setFirstName(entity.getFirstName());
        _entity.setLastName(entity.getLastName());
        _entity.setEmail(entity.getEmail());
        _entity.setPassword(entity.getPassword());

        for (RoleDTOS roleDTOS : entity.getRolesDTO()) {
            Role roles = _roleRepository.getReferenceById(roleDTOS.getId());
            _entity.getRoles().add(roles);
        }
    }
}
