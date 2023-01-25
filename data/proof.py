import csv


boletins_urna = list()


with open('.\\dados_boletins_urna_tse.csv', 'r', encoding = 'latin-1') as file:
    rows = csv.reader(file, delimiter = ',')

    cod = 1

    for row in rows:
        if cod == 1:
            cod += 1
            continue

        boletins_urna.append({
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


apuracao_votos_candidatura = dict()

for boletim_urna in boletins_urna:
    key = f"{boletim_urna['numero_votavel']}-{boletim_urna['codigo_cargo_pergunta']}"

    if boletim_urna['codigo_tipo_votavel'] == 1:
        if key not in apuracao_votos_candidatura:
            apuracao_votos_candidatura[key] = 0
            
        apuracao_votos_candidatura[key] = apuracao_votos_candidatura.get(key) + boletim_urna['quantidade_votos']

apuracao_votos_candidatura = dict(sorted(apuracao_votos_candidatura.items()))


print('\napuração de votos dos candidatos à presidência\n')

print('{:<16} {:>5}'.format('candidato', 'votos'))

for key, value in apuracao_votos_candidatura.items():
    if int(key.split('-')[1]) == 1:
        print('{:<16} {:>5}'.format(key.split('-')[0], value))

print()
