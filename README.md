# Description
Spring boot Application 
### Software Instalation
- Maven
- JDK 1.8

# How to Run This Application
### download
```
git clone https://github.com/mugeeshh/acmebank.git
```
### build
```
mvn install
```
### Test
```
mvn test
```
### Running
```
java -jar target/acmebank.jar
```
### Data
load data data_insert.sql and data_table.sql

### Swagger-UI
Testing and validation :
  http://localhost:8080/acmanager/swagger-ui.html
### API Test
**Account Manager : API for balance checking**, check account number balance like
```accountId:12345678 or accountId:88888888```
**Account Transfer : API for acount transfer**, tranfer amount in one account to another like this 
```
{
  "accountFromId": 12345678,
  "accountToId": 88888888,
  "amount": 2000
}
```

