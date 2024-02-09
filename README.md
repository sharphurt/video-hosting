# Сервис хранения и обработки видео

*Тестовое задание ММВС | Java test task*

### Описание

Сервис для загрузки и обработки видео на сервер. 
Позволяет изменять размер загруженного видео, получать информацию о файле 
и удалять файл из хранилища.

*Использованы технологии:*
* Spring Framework 6.1.3
* MySQL 8.3.0
* FFMPEG 6.1



### Запуск

Для запуска приложения необходимо собрать контейнер с помощью docker-compose. Файл ``docker-compose.yml`` находится в корне проекта. 
[Как использовать docker-compose](https://docs.docker.com/compose/gettingstarted/#step-4-build-and-run-your-app-with-compose)

* URL приложения по умолчанию: `localhost:8181`  
* URL базы данных по умолчанию: `localhost:33061`
* Swagger UI: `localhost:8181/swagger-ui/index.html`
