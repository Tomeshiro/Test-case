## Что это такое?
Это HTTP API микросервис для хранения файлов в формате base64. 

## Что реализовано?
* Метод, создающий файл в базе данных по JSON
* Метод, возвращающий файл по id.
* Метод, возвращающий все файлы, сортируя по убыванию даты (сначала новые). Возвращение файлов происходит по страницам (реализована пагинация).
* Контейнеризация.

## Что не реализовано?
* Валидация данных
* покрытие JUnit тестами

## Как?
По условию мы храним файлы в формате base64 с атрибутами. В базе данных поле base64 будет типа TEXT, чтобы не было ограничения на кол-во символов. Также дату создания (creation_date) храним в типе данных TIMESTAMP WITHOUT TIME ZONE, чтобы были и часы, и месяцы. Дальше идёт стандартный для Java Spring RESTFUL API, но обрезанный под наше удобство до пары методов. В папке docker сгенерированный jar файл, который используется для контейнеризации.

## Запуск микросервиса

1. Установить Docker на компьютер.
2. Клонируем репозиторий на компьютер.
```BASH
   git clone https://github.com/Tomeshiro/Test-case.git
   ```
3. Собираем проект, зайдя в папку docker внутри репозитория и введя команду `build`.
```BASH
   cd <имя репозитория>/docker
   docker build .
   ```
4. Запускаем микросервис.
```BASH
   docker-compose up
   ```

## Проверка POST
Пример входа
```JSON
{
    "content": "Content of file in base64 format",
    "title": "some String",
    "creationDate": "date in format like: 2021-10-10 12:12:00",
    "description": "some String"
}
```
Проверять работу будем с помощью Postman.
На рисунке ниже мы отправили json файл (форматирование даты в формате "yyyy-MM-dd HH:mm:ss") и нам вернулось его id в таблице.
![image](https://github.com/user-attachments/assets/21eae694-2c66-4b2b-ae20-e029b3150e19)
Добавим ещё 2 документа
![image](https://github.com/user-attachments/assets/ce54665a-48cf-4a30-984f-ba8e17ce4a4f)
![image](https://github.com/user-attachments/assets/00b18fa7-0438-4975-885e-0c295d802b65)
Как видно в примере выше, база данных спокойно работает с файлами в формате base64.

## Проверка GET

Проверим, сможем ли мы получить обратно в формате JSON тот большой файл?
![image](https://github.com/user-attachments/assets/9ed296ea-2b63-4af2-ac17-91c0c5619cd3)
Также, если файла по id не существует, то вернёт сообщение об ошибке.
![image](https://github.com/user-attachments/assets/11c7388c-1849-453c-a69d-b50e3120cf87)

## Проверка GET ALL

Метод типа GET на /api/all возвращает страницу отсортированного по убыванию даты списка всех файлов. Номре страницы и кол-во файлов на странице указываются в запросе (по умолчанию, page = 0, size = 10).
У нас было 3 файла, в 2021, в 2009 и 2002 годах. С параметрами page = 0 и size = 2 нам показало 2021 и 2009 года.
![image](https://github.com/user-attachments/assets/6a026bce-40c5-4a36-a61d-3aa6aac6ede2)

## Тестовые случаи для самостоятельной проверки

`POST http://127.0.0.1:8080/api`
```JSON
{
    "content": "AC,APSOFascm",
    "title": "PDF",
    "creationDate": "2021-10-10 12:12:00",
    "description": "PDF file about a cat"
}
```

```JSON
{
    "content": "AVcs23csVAASDscsaASd",
    "title": "Word file",
    "creationDate": "2009-12-01 10:00:00",
    "description": "Word file with image of a cat"
}
```

```JSON
{
    "content": "SPAIMVDSPIAFPEICGJARPSGVJDSAPIVGJDASIPEWIDSPFJXPIDASJGPIEWRJAVIPGERJGFADSJOIGJSDAOIFJXEAIOWJ43RJCT80A24J0182JR082A3JCT80JA32085TJCRATJ302A8JCT083A2JVT038A2VTJ0328ATVJ0A28J4X032A8J0",
    "title": "image",
    "creationDate": "2002-10-10 23:00:00",
    "description": "Image of a cat"
}
```
`GET http://127.0.0.1:8080/api?id=1`
`GET http://127.0.0.1:8080/api?id=5`

`GET http://127.0.0.1:8080/api/all`
`GET http://127.0.0.1:8080/api/all?page=0&size=2`
