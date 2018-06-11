# Документация к API JRepetitor 

## Содержание:
 
* [/USERS](#users)
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
## /users <a name="/users"></a> ##

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

### /create <a name="#create"></a>###
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

  
   
    
   