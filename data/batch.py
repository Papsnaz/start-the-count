import requests
import json


response = requests.post(
    'http://localhost:8080/usuarios/login',
    json = {'username': 'temp', 'senha': 'bananavermelha123'},
    headers = {'content-type': 'application/json'}
)

jwt = response.json()['token']

payloads = None

with open('.\\payloads.json', 'r') as file:
    payloads = json.load(file)

requests_body = list()

request_body = list()

last_file_read = None


for i in range(len(payloads) + 1):
    if i == len(payloads):
        requests_body.append({
            'payloads': request_body
        })

        break

    if i != 0 and payloads[i]['file'] != last_file_read:
        requests_body.append({
            'payloads': request_body
        })

        request_body = list()

    request_body.append(str(payloads[i]['payload']))

    last_file_read = payloads[i]['file']

request_number = 1

for body in requests_body:
    try:
        response = requests.post(
            'http://localhost:8080/boletins-urna',
            json = body,
            headers = {'content-type': 'application/json', 'Authorization': f'Bearer {jwt}'},
        )

        response.raise_for_status()
    except requests.exceptions.HTTPError as error:
        for erro in response.json()['errors']:
            print(f"\nErro: {erro['message']}\n")

        raise Exception('Não foi possível construir o boletim de urna a partir do(s) payload(s) de QR code(s) fornecidos.')

    response_body = response.json()

    boletim_urna = response_body['boletimUrna']
    usuario = boletim_urna['usuario']
    secao = boletim_urna['secaoPleito']['secao']
    zona = secao['zona']
    uf = zona['uf']


    print(f"{request_number}. O boletim de urna coletado pelo usuário \"{usuario['username']}\" na UF \"{uf['sigla']}\", zona {zona['numeroTSE']}, seção {secao['numeroTSE']} foi salvo com sucesso!")

    request_number += 1
