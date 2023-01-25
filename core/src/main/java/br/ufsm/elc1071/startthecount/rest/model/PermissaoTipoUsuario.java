package br.ufsm.elc1071.startthecount.rest.model;

import jakarta.persistence.*;

import lombok.*;

import java.util.Objects;

@Entity(name = "PermissaoTipoUsuario")
@Table(name = "permissao_tipo_usuario", schema = "public", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_permissao", "id_tipo_usuario"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class PermissaoTipoUsuario {

    @Id
    @Column(name = "id_permissao_tipo_usuario", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_permissao", nullable = false)
    private Permissao permissao;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;

    @Override
    public int hashCode() {
        return Objects.hash(
            this.permissao.getNome(),
            this.tipoUsuario.getNome()
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        PermissaoTipoUsuario permissaoTipoUsuario = (PermissaoTipoUsuario) object;

        return
            Objects.equals(this.permissao.getNome(), permissaoTipoUsuario.permissao.getNome()) &&
            Objects.equals(this.tipoUsuario.getNome(), permissaoTipoUsuario.tipoUsuario.getNome());
    }
}
