package br.ufsm.elc1071.startthecount.rest.repository;

import br.ufsm.elc1071.startthecount.rest.dto.retrieval.ApuracaoVotosCandidaturaRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.model.ApuracaoVotosCandidaturaBoletimUrna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ApuracaoVotosCandidaturaBoletimUrnaRepository extends JpaRepository<ApuracaoVotosCandidaturaBoletimUrna, Integer> {

    boolean existsByCandidaturaCandidatoNumeroTSEAndCandidaturaCargoEleicaoCargoCodigoTSEAndCandidaturaCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(Integer numeroCandidatoTSE, Integer codigoCargoTSE, Integer codigoEleicaoTSE, String username, Integer codigoPleitoTSE, Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    Optional<ApuracaoVotosCandidaturaBoletimUrna> findByCandidaturaCandidatoNumeroTSEAndCandidaturaCargoEleicaoCargoCodigoTSEAndCandidaturaCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(Integer numeroCandidatoTSE, Integer codigoCargoTSE, Integer codigoEleicaoTSE, String username, Integer codigoPleitoTSE, Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    @Query(
        value = """
            SELECT
                num_candidato_tse AS "numeroCandidato",
                candidato.nome AS "nomeCandidato",
                cargo.nome AS "nomeCargo",
                eleicao.nome AS "nomeEleicao",
                pleito.nome AS "nomePleito",
                SUM(total_votos_apurados) AS "totalVotosApurados"
            FROM
                apuracao_votos_candidatura_boletim_urna NATURAL JOIN
                candidatura NATURAL JOIN
                candidato NATURAL JOIN
                cargo_eleicao INNER JOIN
                cargo USING (id_cargo) INNER JOIN
                eleicao USING (id_eleicao) INNER JOIN
                pleito USING (id_pleito)
            GROUP BY "numeroCandidato", "nomeCandidato", "nomeCargo", "nomeEleicao", "nomePleito"
            ORDER BY "totalVotosApurados" DESC;
        """,
        nativeQuery = true
    )
    List<ApuracaoVotosCandidaturaRetrievalDTO> findApuracaoVotosCandidaturas();

    @Modifying
    @Transactional
    void deleteByCandidaturaCandidatoNumeroTSEAndCandidaturaCargoEleicaoCargoCodigoTSEAndCandidaturaCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(Integer numeroCandidatoTSE, Integer codigoCargoTSE, Integer codigoEleicaoTSE, String username, Integer codigoPleitoTSE, Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    @Modifying
    @Transactional
    void deleteByBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(String username, Integer codigoPleitoTSE, Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    @Modifying
    @Transactional
    void deleteByCandidaturaCandidatoNumeroTSEAndCandidaturaCargoEleicaoCargoCodigoTSEAndCandidaturaCargoEleicaoEleicaoCodigoTSE(Integer numeroCandidatoTSE, Integer codigoCargoTSE, Integer codigoEleicaoTSE);
}
