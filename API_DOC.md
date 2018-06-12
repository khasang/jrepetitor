# Документация к API JRepetitor 

## Содержание:
 
* [/users](#users)
  * [User json Example](#user_json)
  * [Profile json example](#profile_json)
  * [Creation Profile Status Response JSON](#profile_creation_json)
  * [Creation User Status Response JSON](#user_creationd_json)
  * [/add](#add)
  * [/all](#all)    
  * [/get/{id}](#getbyid)
  * [/delete](#delete)
  * [/profile](#profile)
  * [/create](#create) 
  * [/authorized](#authorized) 
* [/quiz](#quiz)    
  * [Quiz JSON example](#quiz_json_example)     
  * [Quiz Preview JSON exmple](#quiz_preview_json_example)  
  * [Add quiz by group id wrapper JSON](#add_quiz_by_group_id_wrapper)
  * [/add](#add_quiz)    
  * [/all](#quiz_all)  
  * [/get/{id}](#quiz_get)  
  * [/delete](#quiz_delete)  
  * [/preview/all](#quiz_preview_all)  
  * [/preview/get/{id}](#quiz_preview_by_id)  
  * [/add_by_group_id](#quiz_add_by_group_id)     
* [/question](#question)
  * [Question JSON Example](#question_json_example)
  * [Add Question By Quiz ID wrapper JSON Example](#add_question_by_quiz_id_wrapper)    
  * [/add](#add_question)  
  * [/all](#get_all_questions) 
  * [/get/{id}](#get_question_by_id)  
  * [/delete](#delete_question_by_id)  
  * [/add_by_quiz_id](#add_question_by_quiz_id)
* [/item](#item)
  * [Item JSON Example](#item_json_example)    
  * [Add Item By question Id JSON wrapper](#add_item_by_question_id_json_example)    
  * [/add](#add_item)  
  * [/all](#all_item)
  * [/get/{id}](#get_by_id_item)
  * [/delete](#delete_item)  
  * [/add_by_question_id](#add_by_question_id_item)            
 
## /users <a name="/users"></a> ##
API для работы с пользователями JRepetitor

### USER json example <a name="user_json"></a> ###
```json
{
    "name": "user_name",
    "login": "user",
    "password": "user",
    "roleName": "ROLE_USER",
    "profile": {
	"name": "user_name",
        "middlename": "user_middlename",
        "surname": "user_surname",
        "email": "user_email",
        "phoneNumber": "user_phone_number"
    }
}
```

### Profile json example <a name="profile_json"></a> ###
```json
{
    "name": "user_name",
    "middlename": "user_middlename",
    "surname": "user_surname",
    "email": "user_email",
    "phoneNumber": "user_phone_number"
}
```

### Creation Profile Status Response JSON <a name="profile_creation_json"></a> ###
````json
{
    "ok": true,
    "emailExist": false,
    "phoneExist": false
}
````
**"ok":true** - если Profile обновлен,  
**"emailExist":true** - если email уже используется,  
**"phoneExist": false** - если телефоный номер уже используется.

### Creation User Status Response JSON <a name="user_creationd_json"></a> ###

```json
{
    "ok": true,
    "emailExist": false,
    "loginExist": false,
    "phoneExist": false
}
```
**"ok": true** - если пользоваьель успешно создан  
**"emailExist": true** - если пользователь с таким email существует  
**"loginExist": true** - если пользователь с таким login существует  
**"phoneExist": true** - если пользователь с таким телефоном существует  

### /add <a name="add"></a> ###
#### Описание: ####
Добавить пользвователя.    
Возвращает добавленного пользователя.
#### Параметры: ####   
**Content-type:** application-json,  
**RequestMethod:** POST,  
**Url:** /users/add,   
**Content-type:** application-json,  
**Body:** USER json,  
**Response:** User json  

### /all <a name="all"></a> ### 
#### Описание: ####
Добавить пользвователя.  
Возвращает все записи которые есть в базе    
#### Параметры: #### 
**RequestMethod:** GET,  
**Url:** /users/all,  
**Response:** USER json array  

### /get/{id} <a name="#getbyid"></a> ###
#### Описание: ####
Возвращает пользователя c запрошенным id  
#### Параметры: ####
**RequestMethod:**  GET,  
**Url:**  /users/get/{id},    
**id:**  Request Param user id,  
**Response:**  User json , HTTP 200 - OK,    
**Если пользователь не найден:** - HTTP 404 - NOT_FOUND 

### /delete <a name="#delete"></a> ###
#### Описание: ####
Удаление пользователя по заданному id,  
#### Параметры: ####
**Headers:** Content-type - application-json, 
**RequestMethod:** DELETE,  
**Url:** /users/delete,  
**Response:**  User json , HTTP 200 - OK,  
**Если пользователь не найден:**  HTTP 404 - NOT_FOUND  

### /profile <a name="#profile"></a> ###
#### Описание: ####
Возвращает Profile JSON авторизованного пользователя.  
#### Параметры: ####
**RequestMethod:** GET,   
**Url:** /users/profile,  
**Response:** Profile JSON, HTTP 200 - OK,  
**Если пользователь не авторизован:** HTTP 401 UNAUTHORIZED
### Описание: ###
Сохраняет Profile JSON в профиль авторизованного пользователя.  
### Параметры: ####
**RequestMethod:** POST,  
**Headers:** Content-type - application-json,  
**Url:** /users/profile,  
**Response:** Creation Profile Status Response JSON,  
**Если пользователь не авторизован:** HTTP 401 UNAUTHORIZED  

### /create <a name="#create"></a> ###
#### Описание: ####
Создает пользователя с полями из User JSON  
Возвращает USER CREATION STATUS JSON
#### Параметры: ####
**RequestMethod:** POST,  
**Headers:** Content-type - application-json,  
**Url:** /users/create,  
**Response:** USER CREATION STATUS JSON, HTTP 200 - OK

### /authorized <a name="authorized"></a> ### 
#### Описание: ####
Возвращает login авторизованного пользователя.  
Если пользователь не авторизован возвращает anonymousUser
#### Параметры: ####
**RequestMethod:** GET,  
**Url:**/users/authorized,   
**Response** authorized user login or "anonymousUser"

## /quiz <a name="quiz"></a> ## 
API для работы с тестами(quiz), добавление, вывод всех quiz в базе,  
удаление, выборка по id, вывод превью теста (без вывыода вопросов),  
вывод превью всех тестов в базе, добавление теста по id группы.
### Quiz JSON example <a name="quiz_json_example"></a> ###
````json
{
   "id": 1,
   "name": "name_1",
   "questions": [ ],
   "group": { },
   "level": 1
}
````
**"id"** - id теста  
**"name"** - название теста  
**"questions"** - список вопросов [Question JSON](#question_json_example)  
**"group"** - [Group JSON](#group_json_example)  
**"level"** - уровень теста 

### Quiz Preview JSON example <a name="quiz_preview_json_example"></a> ###
```json
{
  "id": 1,
  "name": "name_1",
  "level": 1
}
```
**"id"** - id теста  
**"name"** - название теста  
**"level"** - уровень теста 

###  Add quiz by group id wrapper JSON <a name="add_quiz_by_group_id_wrapper"></a> ###
```json
{
    "id": 1,
    "quiz": {
      "name:":"name_1", 
      "level:":"1"
    }
}  
```
**"id"** - group id  
**"name"** - quiz name  
**"level"** - quiz level
### /add <a name="add_quiz"></a> 
#### Описание: ####
Добавление quiz'a
Возвращает созданный Quiz JSON  
#### Параметры: ####
**Headers:** Content-type - application-json,  
**RequestMethod:** POST,  
**Url:** /quiz/add,
**Response:** [Quiz JSON](#quiz_json_example), HTTP 200 - OK

### /all <a name="quiz_all"></a> ###
#### Описание: ####
Выбрать все quiz'ы из бызы на сервере.  
Возвращает массив Quiz JSON или пустой массив
#### Параметры: ####
**RequestMethod:** GET,  
**Url:** /quiz/all,
**Response:** [Quiz JSON](#quiz_json_example) array, HTTP 200 - OK

### /get/{id} <a name="quiz_get"></a> ###
#### Описание: ####
Возвращает Quiz по заданному  id 
#### Параметры: ####
**RequestMethod:**  GET,  
**Url:**  /quiz/get/{id},    
**id:**  Request Param quiz id,  
**Response:**  [Quiz json](#quiz_json_example) , HTTP 200 - OK,    
**Если quiz c id не найден:** - HTTP 404 - NOT_FOUND 

### /delete  <a name="quiz_delete"></a> ###
#### Описание: ####
Удаление quiz'a по заданному id,  
#### Параметры: ####
**RequestMethod:** DELETE,  
**RequestParam:** id,  
**Url:** /quiz/delete,  
**Response:**  [Quiz json](#quiz_json_example) , HTTP 200 - OK,  
**Если quiz id  не найден:**  HTTP 404 - NOT_FOUND 

### /preview/all <a name="quiz_preview_all"></a> ###
#### Описание: ####
Возвращает массив [Quiz](#quiz_json_example) preview JSON,  
#### Параметры: ####
**RequestMethod:** GET,  
**Url:** /quiz/preview/all,  
**Response:** Quiz preview JSON array, HTTP 200 - OK

### /preview/get/{id} <a name="quiz_preview_by_id"></a> ###
#### Описание: ####
Возвращает [Quiz preview JSON](#quiz_preview_json_example) по заданному  id 
#### Параметры: ####
**RequestMethod:**  GET,  
**Url:**  /quiz/preview/get/{id},    
**id:**  Request Param quiz id,  
**Response:**  [Quiz preview json](#quiz_preview_json_example) , HTTP 200 - OK,    
**Если quiz c id не найден:** - HTTP 404 - NOT_FOUND 

### /add_by_group_id <a name="quiz_add_by_group_id"></a> ###
### Описание: ###
Добавляет quiz в группу с id 
#### Параметры: ####
**Headers:** Content-type - application-json, 
**RequestMethod:** POST,  
**Url:**  /quiz/preview/get/{id},    
**Request Data:**[Add quiz by group id wrapper JSON](#add_quiz_by_group_id_wrapper)  
**Response:**  Quiz preview json , HTTP 200 - OK,    
**Если quiz c id не найден:** - HTTP 404 - NOT_FOUND 
 
## /question <a name="question"></a> ##

### Question JSON Example <a name="question_json_example"></a> ###   
```json
{
    "id": 1,
    "content": "text_question_1",
    "type": "Checkbox",
    "items": [],
    "quiz": {},
    "explanation": "explanation_question_1"
}
```
**"id"** - id вопроса  
**"content"** - содержание вопроса  
**"type"** - "Checkbox" - мультивыбор или "Radio" - 1 вариант   
**"items"** -  Массив [Item JSON](#item_json_example)   
**"quiz"**  - [Quiz JSON](#quiz_json_example)  
**"explanation:"** - объяснение вопроса
 
### Add Question By Quiz ID wrapper JSON Example <a name="add_question_by_quiz_id_wrapper"></a> ###
```json
{
    "id":"1",
    "question": {
        "content":"text_question_1",
        "type":"Checkbox",
        "explanation":"explanation_question_1"
    }
}
```   
**"id"** - id quiz'a в который добавляем вопрос  
**"content"** - содержание вопроса  
**"type"** - "Checkbox" - мультивыбор или "Radio" - 1 вариант  
**"explanation"** - объяснение вопроса

### /add <a name="add_question"></a> ###
#### Описание: ####
Добавить вопрос
Возвращает созданый вопрос 
#### Параметры: ####
**Headers:** Content-type - application-json, 
**RequestMethod:** POST,  
**Url:**  /question/add,    
**Request Data:** [Question JSON](#question_json_example)  
**Response:**  created [Question JSON](#question_json_example) , HTTP 200 - OK,    
### /all <a name="get_all_questions"></a> ###
#### Описание: ####
Возвращает все вопросы которые есть в базе
#### Параметры: ####
**RequestMethod:** GET,  
**Url:**  /question/all,    
**Response:**  all [Question JSON](#question_json_example) in base , HTTP 200 - OK,
### /get/{id} <a name="get_question_by_id"></a> ###
#### Описание: ####
Возвращает question по заданному id, или 404 если нет в базе
#### Параметры: ####
**RequestMethod:**  GET,  
**Url:**  /question/get/{id},    
**id:**  Request Param quiz id,  
**Response:**  [Question json](#question_json_example) , HTTP 200 - OK,    
**Если question c id не найден:** - HTTP 404 - NOT_FOUND 
### /delete <a name="delete_question_by_id"></a>  ###
#### Описание: ####
Удалить вопрос по заданому id, 404 если такого нет в базе
#### Параметры: ####
**RequestMethod:** DELETE,  
**RequestParam:** id,  
**Url:** /question/delete,  
**Response:**  [Question json](#question_json_example) , HTTP 200 - OK,  
**Если question c id не найден:**  HTTP 404 - NOT_FOUND 
### /add_by_quiz_id <a name="add_question_by_quiz_id"></a> ###
#### Описание: ####
Добавляет вопрос в quiz по quiz id
#### Параметры: ####
**Headers:** Content-type - application-json,   
**RequestMethod:** POST,  
**Url:**  /quiz/preview/get/{id},    
**Request Data:**[Add question by quiz id wrapper JSON](#add_question_by_quiz_id_wrapper)  
**Response:**  [Question json](#question_json_example) , HTTP 200 - OK,    
**Если quiz c id не найден:** - HTTP 404 - NOT_FOUND 

## /item <a name="item"></a> ##
### Item JSON Example  <a name="item_json_example"></a> ###
```json
{
    "id": 1,
    "content": "answer1 question1 correct",
    "correct": 1,
    "question": {}
}
```
**"id"** - id ответа(item)  
**"content"** - текст ответа  
**"correct"** - 1 если правильный, 0 - неправильный  
**"question"** - [Question JSON](#question_json_example)  

### Add Item By question Id JSON wrapper <a name="add_item_by_question_id_json_example"></a> ###
```json
{
    "id":"1",
    "item":{
        "content":"answer1 question1",
        "correct":"0"
    }
}
```
**"id"** - id вопроса  
**"content"** - текст ответа  
**"correct"** - 1 если правильный, 0 - неправильный

### /add <a name="add_item"></a> ###
#### Описание: ####
Добавляет ответ.
#### Параметры: ####
**Headers:** Content-type - application-json, 
**RequestMethod:** POST,  
**Url:**  /item/add,    
**Request Data:** [Item JSON](#item_json_example)  
**Response:**  created [Item JSON](#item_json_example) , HTTP 200 - OK,    

### /all <a name="all_item"></a> ###
#### Описание: ####
Выводит все ответы которые есть в базе.
#### Параметры: ####
*RequestMethod:** GET,  
**Url:**  /item/all,    
**Response:**  all [Item JSON](#item_json_example) in base , HTTP 200 - OK,

### /get/{id} <a name="/get_by_id_item"></a> ###
#### Описание: ####
Выводит ответ по заданному id, 404 если нет такого в базе.
#### Параметры: ####
**RequestMethod:**  GET,  
**Url:**  /item/get/{id},    
**id:**  Request Param question id,  
**Response:**  [Item JSON](#item_json_example) , HTTP 200 - OK,    
**Если item c id не найден:** - HTTP 404 - NOT_FOUND 
### /delete <a name="delete_item"></a> ###
#### Описание: ####
Удаляет item по заданному id 
#### Параметры: ####
**RequestMethod:** DELETE,  
**RequestParam:** id,  
**Url:** /item/delete,  
**Response:**  [Item JSON](#item_json_example) , HTTP 200 - OK,  
**Если item c id не найден:**  HTTP 404 - NOT_FOUND 
### /add_by_question_id <a name="add_by_question_id_item"></a> ###
#### Описание: ####
Добавить ответ(item) в question c заданным id
#### Параметры: ####
**Headers:** Content-type - application-json,   
**RequestMethod:** POST,  
**Url:**  /question/add_by_question_id},    
**Request Data:**[Add Item By question Id JSON wrapper](#add_item_by_question_id_json_example)  
**Response:**  [Item JSON](#item_json_example) , HTTP 200 - OK,    
**Если question c id не найден:** - HTTP 404 - NOT_FOUND 