
запустить Postman, при первом запуске выбрать Sign in / Sign up through email instead внизу страницы, чтобы не логиниться с google-аккаунтом
)

POST http://localhost:8080/person/create
{
    "name": "bubu"
}

ответ 200
Проверяем наличие записи:
GET http://localhost:8080/person/get/all