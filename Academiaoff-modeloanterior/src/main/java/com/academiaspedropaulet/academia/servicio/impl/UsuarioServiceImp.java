package com.academiaspedropaulet.academia.servicio.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.academiaspedropaulet.academia.dtos.UsuarioDTO;
import com.academiaspedropaulet.academia.excepcion.ModelNotFoundException;
import com.academiaspedropaulet.academia.mappers.UsuarioMapper;
import com.academiaspedropaulet.academia.modelo.Rol;
import com.academiaspedropaulet.academia.modelo.Usuario;
import com.academiaspedropaulet.academia.modelo.UsuarioRol;
import com.academiaspedropaulet.academia.repositorio.ICrudGenericoRepository;
import com.academiaspedropaulet.academia.repositorio.IUsuarioRepository;
import com.academiaspedropaulet.academia.servicio.IRolService;
import com.academiaspedropaulet.academia.servicio.IUsuarioRolService;
import com.academiaspedropaulet.academia.servicio.IUsuarioService;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServiceImp extends CrudGenericoServiceImp<Usuario, Long> implements IUsuarioService {
    private final IUsuarioRepository repo;

    private final IRolService rolService;
    private final IUsuarioRolService iurService;

    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper userMapper;

    @Override
    protected ICrudGenericoRepository<Usuario, Long> getRepo() {
        return repo;
    }



    @Override
    public UsuarioDTO login(UsuarioDTO.CredencialesDto credentialsDto) {
        Usuario user = repo.findOneByUser(credentialsDto.user())
                .orElseThrow(() -> new ModelNotFoundException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.clave()), user.getClave())) {
            return userMapper.toDTO(user);
        }

        throw new ModelNotFoundException("Invalid password", HttpStatus.BAD_REQUEST);
    }



    @Override
    public UsuarioDTO register(UsuarioDTO.UsuarioCrearDto userDto) {
        // Verifica si ya existe el usuario
        Optional<Usuario> optionalUser = repo.findOneByUser(userDto.user());
        if (optionalUser.isPresent()) {
            throw new ModelNotFoundException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        // Convierte el DTO a entidad
        Usuario user = userMapper.toEntityFromCADTO(userDto);
        user.setClave(passwordEncoder.encode(CharBuffer.wrap(userDto.clave())));
        Usuario savedUser = repo.save(user);

        // Busca el rol por ID (usando el ID proporcionado en el DTO)
        Rol r = rolService.getById(userDto.rol()) // Ahora busca por ID
                .orElseThrow(() -> new ModelNotFoundException("Role not found", HttpStatus.BAD_REQUEST));

        // Crea la relación entre usuario y rol
        iurService.save(UsuarioRol.builder()
                .usuario(savedUser)
                .rol(r)
                .build());

        return userMapper.toDTO(savedUser);
    }
}

