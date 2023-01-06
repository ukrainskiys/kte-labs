# kte-labs
### Тестовое задание для KTE Labs

#### Порядок запуска
* Поднять бд в докере [ docker-compose.yml ]
* Запуск миграции liquibase [ shop-migration ]
* Запуск сервиса [ shop ]
* Запуск теста JMeter [ shop-jmeter ]
```
<path to bin/jmeter> -n -t sale-calculate_test.jmx -l sale-calculate_result.csv
```
##### Swagger
`
http://localhost:8080/swagger-ui/index.html#/
`
