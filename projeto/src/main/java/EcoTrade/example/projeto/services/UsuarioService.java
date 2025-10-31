package ecotrade.example.projeto.services;

import ecotrade.example.projeto.model.Role;
import ecotrade.example.projeto.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ecotrade.example.projeto.repository.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarProdutor(Usuario usuario) {
        usuario.setRole(Role.PRODUCER);
        return criarProdutor(usuario);
    }

    public Usuario criarEmpresa(Usuario usuario) {
        usuario.setRole(Role.COMPANY);
        return criarEmpresa(usuario);
    }

    public List<Usuario> listarProdutores() {
        return usuarioRepository.findAll().stream()
                .filter(u -> u.getRole() == Role.PRODUCER)
                .collect(Collectors.toList());
    }


    public List<Usuario> listarEmpresas() {
        return usuarioRepository.findAll().stream()
                .filter(u -> u.getRole() == Role.COMPANY)
                .collect(Collectors.toList());
    }
}