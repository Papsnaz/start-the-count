package br.ufsm.elc1071.startthecount.rest.repository;

import br.ufsm.elc1071.startthecount.rest.dto.retrieval.ApuracaoVotosPartidoRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.model.ApuracaoVotosPartidoBoletimUrna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ApuracaoVotosPartidoBoletimUrnaRepository extends JpaRepository<ApuracaoVotosPartidoBoletimUrna, Integer> {

    boolean existsByPartidoNumeroTSEAndEleicaoCodigoTSEAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(Integer numeroPartidoTSE, Integer codigoEleicaoTSE, String username, Integer codigoPleitoTSE, Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    Optional<ApuracaoVotosPartidoBoletimUrna> findByPartidoNumeroTSEAndEleicaoCodigoTSEAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(Integer numeroPartidoTSE, Integer codigoEleicaoTSE, String username, Integer codigoPleitoTSE, Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    @Query(
        value = """
            SELECT
                num_partido_tse AS "numeroPartido",
                partido.nome AS "nomePartido",
                pleito.nome AS "nomePleito",
                SUM(qtde_votos_de_legenda) AS "quantidadeVotosDeLegenda",
                SUM(total_votos_apurados) AS "totalVotosApurados"
            FROM
                apuracao_votos_partido_boletim_urna NATURAL JOIN
                partido INNER JOIN
                eleicao USING (id_eleicao) INNER JOIN
                pleito USING (id_pleito)
            GROUP BY "numeroPartido", "nomePartido", "nomePleito"
            ORDER BY "totalVotosApurados" DESC;
        """,
        nativeQuery = true
    )
    List<ApuracaoVotosPartidoRetrievalDTO> findApuracaoVotosPartidos();

    @Modifying
    @Transactional
    void deleteByPartidoNumeroTSEAndEleicaoCodigoTSEAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(Integer numeroPartidoTSE, Integer codigoEleicaoTSE, String username, Integer codigoPleitoTSE, Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    @Modifying
    @Transactional
    void deleteByBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(String username, Integer codigoPleitoTSE, Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    @Modifying
    @Transactional
    void deleteByPartidoNumeroTSE(Integer numeroPartidoTSE);
}
