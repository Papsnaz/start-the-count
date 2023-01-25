package br.ufsm.elc1071.startthecount.rest.repository;

import br.ufsm.elc1071.startthecount.rest.dto.retrieval.ApuracaoVotosCargoRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.model.ApuracaoVotosCargoBoletimUrna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ApuracaoVotosCargoBoletimUrnaRepository extends JpaRepository<ApuracaoVotosCargoBoletimUrna, Integer> {

    boolean existsByCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(Integer codigoCargoTSE, Integer codigoEleicaoTSE, String username, Integer codigoPleitoTSE, Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    Optional<ApuracaoVotosCargoBoletimUrna> findByCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(Integer codigoCargoTSE, Integer codigoEleicaoTSE, String username, Integer codigoPleitoTSE, Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    @Query(
        value = """
            SELECT
                cargo.nome AS "nomeCargo",
                eleicao.nome AS "nomeEleicao",
                pleito.nome AS "nomePleito",
                SUM(qtde_votos_nominais) AS "quantidadeVotosNominais",
                SUM(qtde_votos_de_legenda) AS "quantidadeVotosDeLegenda",
                SUM(qtde_votos_em_branco) AS "quantidadeVotosEmBranco",
                SUM(qtde_votos_nulos) AS "quantidadeVotosNulos",
                SUM(total_votos_apurados) AS "totalVotosApurados"
            FROM
                apuracao_votos_cargo_boletim_urna NATURAL JOIN
                cargo_eleicao NATURAL JOIN
                cargo INNER JOIN
                eleicao USING (id_eleicao) INNER JOIN
                pleito USING (id_pleito)
            GROUP BY "nomeCargo", "nomeEleicao", "nomePleito"
            ORDER BY "totalVotosApurados" DESC;
        """,
        nativeQuery = true
    )
    List<ApuracaoVotosCargoRetrievalDTO> findApuracaoVotosCargos();

    @Modifying
    @Transactional
    void deleteByCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(Integer codigoCargoTSE, Integer codigoEleicaoTSE, String username, Integer codigoPleitoTSE, Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    @Modifying
    @Transactional
    void deleteByBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(String username, Integer codigoPleitoTSE, Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    @Modifying
    @Transactional
    void deleteByCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSE(Integer codigoCargoTSE, Integer codigoEleicaoTSE);
}
