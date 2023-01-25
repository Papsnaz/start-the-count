package br.ufsm.elc1071.startthecount.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "OrigemConfiguracaoProcessoEleitoral")
@Table(name = "origem_configuracao_processo_eleitoral", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class OrigemConfiguracaoProcessoEleitoral {

    @Id
    @Column(name = "id_origem_configuracao_processo_eleitoral", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    @Column(name = "abreviacao", unique = true)
    private String abreviacao;

    @OneToMany(mappedBy = "origemConfiguracao", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<ProcessoEleitoral> processosEleitorais;

    public OrigemConfiguracaoProcessoEleitoral() {
        this.processosEleitorais = new LinkedHashSet<>();
    }

    public OrigemConfiguracaoProcessoEleitoral(String nome, String abreviacao) {
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

        OrigemConfiguracaoProcessoEleitoral origemConfiguracaoProcessoEleitoral = (OrigemConfiguracaoProcessoEleitoral) object;

        return Objects.equals(this.abreviacao, origemConfiguracaoProcessoEleitoral.abreviacao);
    }
}
