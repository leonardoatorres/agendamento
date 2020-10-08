## API Gerenciamento de Inspeção e Visita no CAV

### Subir app:

```dockerfile
docker-compose up --build --force-recreate
```

### Banco Mysql:
localhost:3306
user: root

Rodar o script Configuracao_Ambiente_DML_DDL.sql

### Requisições:

#### Retornar todos os carros:
```shell script
curl -i 'http://localhost:8080/car'
```

#### Adicionar carro:
```shell script
curl -i -X POST 'http://localhost:8080/car' \
--header 'Content-Type: application/json' \
--data-raw '{
    "brand":"Ford",
    "model":"Fiesta",
    "cav":"Botafogo"
}'
```

#### Remover carro:
```shell script
curl -i -X DELETE 'http://localhost:8080/car/{name}' \
--header 'Content-Type: application/json'
```

#### Retornar todos os cavs:
```shell script
curl -i 'http://localhost:8080/cav'
```

#### Adicionar cav:
```shell script
curl -i -X POST 'http://localhost:8080/cav' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"Copacabana"
}'
```

#### Remover cav:
```shell script
curl -i -X POST 'http://localhost:8080/cav/{name}' \
--header 'Content-Type: application/json'
}'
```

#### Retornar a agenda:
```shell script
curl -i 'http://localhost:8080/calendar'
```

#### Agendar:
```shell script
curl -i -X POST 'http://localhost:8080/calendar' \
--header 'Content-Type: application/json' \
--data-raw '{
    "date": "2019-08-17",
    "cav": "Barra da Tijuca",
    "tipo": "visit",
    "carro": "Cruze",
    "hour": 14
}'
```



