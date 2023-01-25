import csv
import os

municipios = list()
zonas = list()
secoes = list()

with os.scandir(".\\pdf\\") as entries:
    for entry in entries:
        municipio_zona_secao = entry.name.split('_')[1].split('.')[0]

        municipios.append(int(municipio_zona_secao.split('-')[0]))
        zonas.append(int(municipio_zona_secao.split('-')[1]))
        secoes.append(int(municipio_zona_secao.split('-')[2]))


dados_primeiro_turno = list()

with open('.\\tse\\bweb_1t_RS_051020221321\\bweb_1t_RS_051020221321.csv', 'r', encoding = 'latin-1') as file:
    rows = csv.reader(file, delimiter = ';')

    cod = 1

    for row in rows:
        if cod == 1:
            cod += 1
            continue

        dados_primeiro_turno.append({
            'data_geracao': str(row[0]).strip(),
            'hora_geracao': str(row[1]).strip(),
            'ano_eleicao': int(row[2]),
            'codigo_tipo_eleicao': int(row[3]),
            'nome_tipo_eleicao': str(row[4]).strip(),
            'codigo_pleito': int(row[5]),
            'data_pleito': str(row[6]).strip(),
            'numero_turno': int(row[7]),
            'codigo_eleicao': int(row[8]),
            'descricao_eleicao': str(row[9]).strip(),
            'sigla_uf': str(row[10]).strip(),
            'codigo_municipio': int(row[11]),
            'nome_municipio': str(row[12]).strip(),
            'numero_zona': int(row[13]),
            'numero_secao': int(row[14]),
            'numero_local_votacao': int(row[15]),
            'codigo_cargo_pergunta': int(row[16]),
            'descricao_cargo_pergunta': str(row[17]).strip(),
            'numero_partido': int(row[18]),
            'sigla_partido': str(row[19]).strip(),
            'nome_partido': str(row[20]).strip(),
            'data_boletim_urna_recebido': str(row[21]).strip(),
            'quantidade_eleitores_aptos': int(row[22]),
            'quantidade_eleitores_comparecentes': int(row[23]),
            'quantidade_eleitores_faltantes': int(row[24]),
            'codigo_tipo_urna': int(row[25]),
            'descricao_tipo_urna': str(row[26]).strip(),
            'codigo_tipo_votavel': int(row[27]),
            'descricao_tipo_votavel': str(row[28]).strip(),
            'numero_votavel': int(row[29]),
            'nome_votavel': str(row[30]).strip(),
            'quantidade_votos': int(row[31]),
            'numero_urna_efetivada': int(row[32]),
            'codigo_carga_1_urna_efetivada': str(row[33]).strip(),
            'codigo_carga_2_urna_efetivada': str(row[34]).strip(),
            'codigo_flashcard_urna_efetivada': str(row[35]).strip(),
            'data_carga_urna_efetivada': str(row[36]).strip(),
            'descricao_cargo_pergunta_secao': str(row[37]).strip(),
            'descricao_secoes_agregadas': str(row[38]).strip(),
            'data_abertura': str(row[39]).strip(),
            'data_encerramento': str(row[40]).strip(),
            'quantidade_eleitores_habilitados_por_ano_nascimento': int(row[41]),
            'data_emissao_boletim_urna': str(row[42]).strip(),
            'numero_junta_apuradora': int(row[43]),
            'numero_turma_apuradora': int(row[44])
        })

        cod += 1

dados_primeiro_turno = [dado for dado in dados_primeiro_turno if dado['codigo_municipio'] in municipios]
dados_primeiro_turno = [dado for dado in dados_primeiro_turno if dado['numero_zona'] in zonas]
dados_primeiro_turno = [dado for dado in dados_primeiro_turno if dado['numero_secao'] in secoes]

dados_segundo_turno = list()

with open('.\\tse\\bweb_2t_RS_311020221535\\bweb_2t_RS_311020221535.csv', 'r', encoding = 'latin-1') as file:
    rows = csv.reader(file, delimiter = ';')

    cod = 1

    for row in rows:
        if cod == 1:
            cod += 1
            continue

        dados_segundo_turno.append({
            'data_geracao': str(row[0]).strip(),
            'hora_geracao': str(row[1]).strip(),
            'ano_eleicao': int(row[2]),
            'codigo_tipo_eleicao': int(row[3]),
            'nome_tipo_eleicao': str(row[4]).strip(),
            'codigo_pleito': int(row[5]),
            'data_pleito': str(row[6]).strip(),
            'numero_turno': int(row[7]),
            'codigo_eleicao': int(row[8]),
            'descricao_eleicao': str(row[9]).strip(),
            'sigla_uf': str(row[10]).strip(),
            'codigo_municipio': int(row[11]),
            'nome_municipio': str(row[12]).strip(),
            'numero_zona': int(row[13]),
            'numero_secao': int(row[14]),
            'numero_local_votacao': int(row[15]),
            'codigo_cargo_pergunta': int(row[16]),
            'descricao_cargo_pergunta': str(row[17]).strip(),
            'numero_partido': int(row[18]),
            'sigla_partido': str(row[19]).strip(),
            'nome_partido': str(row[20]).strip(),
            'data_boletim_urna_recebido': str(row[21]).strip(),
            'quantidade_eleitores_aptos': int(row[22]),
            'quantidade_eleitores_comparecentes': int(row[23]),
            'quantidade_eleitores_faltantes': int(row[24]),
            'codigo_tipo_urna': int(row[25]),
            'descricao_tipo_urna': str(row[26]).strip(),
            'codigo_tipo_votavel': int(row[27]),
            'descricao_tipo_votavel': str(row[28]).strip(),
            'numero_votavel': int(row[29]),
            'nome_votavel': str(row[30]).strip(),
            'quantidade_votos': int(row[31]),
            'numero_urna_efetivada': int(row[32]),
            'codigo_carga_1_urna_efetivada': str(row[33]).strip(),
            'codigo_carga_2_urna_efetivada': str(row[34]).strip(),
            'codigo_flashcard_urna_efetivada': str(row[35]).strip(),
            'data_carga_urna_efetivada': str(row[36]).strip(),
            'descricao_cargo_pergunta_secao': str(row[37]).strip(),
            'descricao_secoes_agregadas': str(row[38]).strip(),
            'data_abertura': str(row[39]).strip(),
            'data_encerramento': str(row[40]).strip(),
            'quantidade_eleitores_habilitados_por_ano_nascimento': int(row[41]),
            'data_emissao_boletim_urna': str(row[42]).strip(),
            'numero_junta_apuradora': int(row[43]),
            'numero_turma_apuradora': int(row[44])
        })

        cod += 1

dados_segundo_turno = [dado for dado in dados_segundo_turno if dado['codigo_municipio'] in municipios]
dados_segundo_turno = [dado for dado in dados_segundo_turno if dado['numero_zona'] in zonas]
dados_segundo_turno = [dado for dado in dados_segundo_turno if dado['numero_secao'] in secoes]

dados = dados_primeiro_turno + dados_segundo_turno

with open('.\\dados_boletins_urna.csv', 'w', encoding = 'latin-1', newline = '') as file:
    writer = csv.writer(file, quoting = csv.QUOTE_NONNUMERIC)

    writer.writerow(dados[0].keys())

    for dado in dados:
        writer.writerow(dado.values())
