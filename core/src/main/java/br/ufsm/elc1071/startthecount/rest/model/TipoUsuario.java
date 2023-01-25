package br.ufsm.elc1071.startthecount.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "TipoUsuario")
@Table(name = "tipo_usuario", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class TipoUsuario {

    @Id
    @Column(name = "id_tipo_usuario", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", unique = true, nullable = false, length = 7)
    private String nome;

    @Column(name = "descricao", unique = true, nullable = false, length = 15)
    private String descricao;

    @OneToMany(mappedBy = "tipo", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<Usuario> usuarios;

    @OneToMany(mappedBy = "tipoUsuario", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<PermissaoTipoUsuario> permissoes;

    public TipoUsuario() {
        this.usuarios = new LinkedHashSet<>();
        this.permissoes = new LinkedHashSet<>();
    }

    public TipoUsuario(String nome, String descricao) {
        this();
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.nome);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (Objects.isNull(object)) return false;

        if (this.getClass() != object.getClass()) return false;

        TipoUsuario tipoUsuario = (TipoUsuario) object;

        return Objects.equals(this.nome, tipoUsuario.nome);
    }
}
