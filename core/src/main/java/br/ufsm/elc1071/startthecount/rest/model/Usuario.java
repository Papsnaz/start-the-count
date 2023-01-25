package br.ufsm.elc1071.startthecount.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

import java.util.*;

@Entity(name = "Usuario")
@Table(name = "usuario", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class Usuario implements UserDetails {

    @Id
    @Column(name = "id_usuario", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", unique = true, nullable = false, length = 31)
    private String username;

    @Column(name = "senha", nullable = false, length = 72, columnDefinition = "bpchar")
    private String senha;

    @Column(name = "nome", length = 127)
    private String nome;

    @Column(name = "sobrenome", length = 127)
    private String sobrenome;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_tipo_usuario", nullable = false)
    private TipoUsuario tipo;

    @Transient
    private Boolean bloqueado;

    @Transient
    private Boolean habilitado;

    @Column(name = "criado_em", insertable = false, updatable = false, nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", insertable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime atualizadoEm;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<BoletimUrna> boletinsUrna;

    public Usuario() {
        this.boletinsUrna = new LinkedHashSet<>();
    }

    public Usuario(String username, String senha, String nome, String sobrenome, TipoUsuario tipo) {
        this();
        this.username = username;
        this.senha = senha;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.tipo = tipo;
    }

    public void update(Usuario usuario) {
        if (Objects.isNull(usuario)) return;

        this.username = usuario.username;
        this.senha = usuario.senha;
        this.nome = usuario.nome;
        this.sobrenome = usuario.sobrenome;
        this.tipo = usuario.tipo;
        this.bloqueado = usuario.bloqueado;
        this.habilitado = usuario.habilitado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.username);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (Objects.isNull(object)) return false;

        if (this.getClass() != object.getClass()) return false;

        Usuario usuario = (Usuario) object;

        return Objects.equals(this.username, usuario.username);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_".concat(this.tipo.getNome())));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
