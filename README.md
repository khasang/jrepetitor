# jrepetitor

Запускать плагин Firefox REST Client

запрос "Выбрать всех"
Method:GET
http://localhost:8080/cat/all
Жмем SEND

запрос "добавить кота"
Method:POST
http://localhost:8080/cat/add
главное меню Headers -> Custom Header
Name:Content-Type
Attribute Value:application/json
Body:{"name":"Barsik"}
Жмем SEND

запрос "получить кота с id=24"
Method:GET
http://localhost:8080/cat/get/24
Жмем SEND

запрос "удалить кота с id=7"
Method:DELETE
http://localhost:8080/cat/delete?id=7
Жмем SEND

запрос "изменить имя кота с id=8 на Шмарсик"
Method:PUT
http://localhost:8080/cat/upd
Body:{"id":"8","name":"Шмарсик"}
Жмем SEND
