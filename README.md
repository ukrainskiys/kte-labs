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

#### Ендпоинты
REST - **/rest** (В том-же порядке, что и в ТЗ)
1. GET /client/all
2. POST /client/discounts
3. GET /product/all
4. POST /product/info
5. POST /sale/calculate
6. POST /sale/registration
7. POST /product/evaluation
8. POST /sale/statistic

SOAP - **/soap**
1. /client
   * all
   * discounts
2. /product
    * all
    * info
    * evaluation
3. /sale
    * calculate
    * registration
    * statistic
