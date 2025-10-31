package ecotrade.example.projeto.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "app_user")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String Email;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String hashDaSenha;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Usuario(){

    }

    public Usuario(Long id, String Email, String nome, String hashDaSenha, Role role){

        this.id = id;
        this.Email = Email;
        this.nome = nome;
        this.hashDaSenha = hashDaSenha;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHashDaSenha() {
        return hashDaSenha;
    }

    public void setHashDaSenha(String hashDaSenha) {
        this.hashDaSenha = hashDaSenha;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(Email, usuario.Email) && Objects.equals(nome, usuario.nome) && Objects.equals(hashDaSenha, usuario.hashDaSenha) && role == usuario.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Email, nome, hashDaSenha, role);
    }
}
