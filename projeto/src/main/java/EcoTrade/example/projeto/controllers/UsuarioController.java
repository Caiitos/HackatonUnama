package ecotrade.example.projeto.controllers;

import ecotrade.example.projeto.model.Usuario;
import ecotrade.example.projeto.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/produtor")
    public ResponseEntity<Usuario> criarProdutor(@RequestBody Usuario usuario) {
        try {
            Usuario novoProdutor = usuarioService.criarProdutor(usuario);
            return ResponseEntity.ok(novoProdutor);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/empresa")
    public ResponseEntity<Usuario> criarEmpresa(@RequestBody Usuario usuario) {
        try {
            Usuario novaEmpresa = usuarioService.criarEmpresa(usuario);
            return ResponseEntity.ok(novaEmpresa);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/produtores")
    public List<Usuario> listarProdutores() {
        return usuarioService.listarProdutores();
    }

    @GetMapping("/empresas")
    public List<Usuario> listarEmpresas() {
        return usuarioService.listarEmpresas();
    }
}
