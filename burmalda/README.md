# IdeaForge — DevSecOps: API в Docker

## Описание
IdeaForge — генератор и помощник для учебных проектов. В рамках работы создано небольшое Java API, которое можно запустить в Docker-контейнере.

## Endpoint
- `GET /` — информация о сервисе и список endpoint.
- `GET /health` — проверка состояния API.
- `GET /projects/idea` — пример учебной идеи проекта, связанный с темой IdeaForge.

## Требования
- Docker Desktop или Docker Engine
- Терминал
- Браузер или `curl`

## Запуск локально без Docker
Нужен JDK 21 или новее.

Из корня проекта выполните:

```bash
mkdir -p out
javac -encoding UTF-8 -d out src/main/java/ru/ideaforge/Main.java
java -cp out ru.ideaforge.Main
```

В другом терминале проверьте API:

```bash
curl http://localhost:8080/health
curl http://localhost:8080/projects/idea
```

Остановить приложение: `Ctrl+C`.

## Сборка Docker-образа
Из папки, где находится Dockerfile:

```bash
docker build -t ideaforge-api:1.0 .
```

## Запуск контейнера

```bash
docker run -d --name ideaforge-api -p 8080:8080 ideaforge-api:1.0
```

Параметр `-d` запускает контейнер в фоне. Запись `-p 8080:8080` связывает порт 8080 компьютера с портом 8080 внутри контейнера.

## Проверка результата

```bash
docker ps
curl http://localhost:8080/health
curl http://localhost:8080/projects/idea
docker logs ideaforge-api
```

Можно открыть в браузере:
- http://localhost:8080/health
- http://localhost:8080/projects/idea

## Остановка и удаление контейнера

```bash
docker stop ideaforge-api
docker rm ideaforge-api
```

Если порт 8080 занят, остановите приложение, которое его использует, или поменяйте внешний порт, например `-p 8081:8080`, а запросы отправляйте на `http://localhost:8081`.

## Пример ожидаемого ответа
Запрос `GET /health`:

```json
{"status":"ok","service":"IdeaForge API"}
```

Запрос `GET /projects/idea` возвращает название проекта, описание, цель и список шагов.

## Что показать преподавателю
1. `docker build -t ideaforge-api:1.0 .`
2. `docker run -d --name ideaforge-api -p 8080:8080 ideaforge-api:1.0`
3. `docker ps`
4. `curl http://localhost:8080/health`
5. `curl http://localhost:8080/projects/idea`
6. `docker logs ideaforge-api`

Примечание: вывод `docker ps`, запросов и логов нужно получить на своём компьютере после запуска. В проекте приведены команды для воспроизведения проверки.
