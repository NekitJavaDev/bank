## REST-API Spring Boot Project в рамках стажировки в компании Bell Integrator

#### Автор: Н. Хмыров
#### E-mail: 969haki@mail.com


#### Дано: 

* организации с офисами и сотрудниками
* справочники с типами документов и кодами стран

#### REST архитектура:

Все описанные возвращаемые типы данных находятся в параметре data. В случае ошибки возвращается параметр error.

Например, в случае, если запрос корректно отработает, бэк возвращает в body ответа:

`{
    “data”:{
        //то, что в параметре out
    }
 }`

А в случае ошибки возвращает 

`{
    “error”:”текст ошибки”
}`

Везде, где не написан метод, использовать POST
 
1. ***api/organization/list***

    In (фильтр):

    `{
  “name”:””, //обязательный параметр
  “inn”:””,
  “isActive”:””
}`

    Out:

    `[
  {
    “id”:””,
    “name”:””,
    “isActive”:”true” //пример
  },
  ...
]`

2. ***api/organization/{id}***

    method:GET

    Out:

    `{
  “id”:””,
  “name”:””,
  “fullName”:””,
  “inn”:””,
  “kpp”:””,
  “address”:””,
  “phone”:””,
  “isActive”:”true”
}`

3. ***api/organization/update***

    In:
    
    `{
  “id”:””, //обязательный параметр
  “name”:””, //обязательный параметр
  “fullName”:””, //обязательный параметр
  “inn”:””, //обязательный параметр
  “kpp”:””, //обязательный параметр
  “address”:””, //обязательный параметр
  “phone”:””,
  “isActive”:”true” 
}`

    Out:
    
    `{
    “result”:”success”
}`

4. ***api/organization/save***

    In:

    `{
  “name”:””, //обязательный параметр
  “fullName”:””, //обязательный параметр
  “inn”:””, //обязательный параметр
  “kpp”:””, //обязательный параметр
  “address”:””, //обязательный параметр
  “phone”:””,
  “isActive”:”true”
}`

    Out:
    
    `{
    “result”:”success”
}`

5. ***api/office/list/{orgId}***

    In (фильтр):

    `{
  “orgId”:””, //обязательный параметр
  “name”:””,
  “phone”:””,
  “isActive” 
}`

    Out:

    `[
  {
    “id”:””,
    “name”:””,
    “isActive”:”true”
  },
  ...
]`

6. ***api/office/{id}***

    method:GET

    Out:
    
    `{
  “id”:””,
  “name”:””,
  “address”:””,
  “phone”:””,
  “isActive”:”true”
}`

7. ***api/office/update***

    In:

    `{
  “orgId”:””,
  “id”:””, //обязательный параметр
  “name”:””, //обязательный параметр
  “address”:””, //обязательный параметр
  “phone”:””,
  “isActive”:”true”
}`

    Out:
    
    `{
    “result”:”success”
}`

8. ***api/office/save***

    In:

    `{
  “orgId”:””, //обязательный параметр	
  “name”:””, //обязательный параметр
  “address”:””, //обязательный параметр
  “phone”:””, 
  “isActive”:”true”
}`

    Out:

   `{
    “result”:”success”
}`

9. ***api/user/list***

    In (фильтр):

    `{
  “officeId”:””, //обязательный параметр
  “firstName”:””,
  “lastName”:””,
  “middleName”:””,
  “position”:””,
  “docCode”:””,
  “citizenshipCode”:””
}`

    Out:

    `{
  “id”:””,
  “firstName”:””,
  “secondName”:””,
  “middleName”:””,
  “position”:””
}`

10. ***api/user/{id}***

    method:GET

    Out:
    
    `{
  “id”:””,
  “firstName”:””,
  “secondName”:””,
  “middleName”:””,
  “position”:””,
  “phone”:””,
  “docName”:””,
  “docNumber”:””,
  “docDate”:””,
  “citizenshipName”:””,
  “citizenshipCode”:””,
  “isIdentified”:”true”
}`


11. ***api/user/update*** 

    In:

    `{
  “officeId”:””,
  “id”:””, //обязательный параметр
  “firstName”:””, //обязательный параметр
  “secondName”:””,
  “middleName”:””,
  “position”:””, //обязательный параметр
  “phone”:””,
  “docName”:””,
  “docNumber”:””,
  “docDate”:””,
  “citizenshipName”:””,
  “citizenshipCode”:””,
  “isIdentified”:”true”
}`

    Out:

    `{
    “result”:”success”
}`


12. ***api/user/save*** 

    In:

    `{
  “officeId”:””, //обязательный параметр	
  “firstName”:””, //обязательный параметр
  “secondName”:””,
  “middleName”:””,
  “position”:””, //обязательный параметр
  “phone”:””,
  “docCode”:””,
  “docName”:””,
  “docNumber”:””,
  “docDate”:””,
  “citizenshipName”:””,
  “citizenshipCode”:””,
  “isIdentified”:”true”
}`



13. ***api/docs***

    Справочники:

    `[
  {
    “name”:“Паспорт гражданина РФ”,
    “code”:”21”
  },
  ...
]`



14. ***api/countries***

    Виды документов, удостоверяющих личность физического лица

    `[
  {
    “name”:“Российская Федерация”,
    “code”:”643”
  },
  ...
]`

Репозиторий с исходным примером:
-
git - https://github.com/azEsm/empty_project