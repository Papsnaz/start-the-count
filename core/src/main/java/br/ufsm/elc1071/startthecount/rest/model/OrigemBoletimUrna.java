package br.ufsm.elc1071.startthecount.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "OrigemBoletimUrna")
@Table(name = "origem_boletim_urna", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class OrigemBoletimUrna {

    @Id
    @Column(name = "id_origem_boletim_urna", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", unique = true, length = 31)
    private String nome;

    @Column(name = "abreviacao", unique = true, length = 7)
    private String abreviacao;

    @OneToMany(mappedBy = "origem", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<BoletimUrna> boletinsUrna;

    public OrigemBoletimUrna() {
        this.boletinsUrna = new LinkedHashSet<>();
    }

    public OrigemBoletimUrna(String nome, String abreviacao) {
        this();
        this.nome = nome;
        this.abreviacao = abreviacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.abreviacao);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (Objects.isNull(object)) return false;

        if (this.getClass() != object.getClass()) return false;

        OrigemBoletimUrna origemBoletimUrna = (OrigemBoletimUrna) object;

        return Objects.equals(this.abreviacao, origemBoletimUrna.abreviacao);
    }
}
