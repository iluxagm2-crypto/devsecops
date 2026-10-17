# Используем официальный образ JDK для компиляции и запуска приложения.
FROM eclipse-temurin:21-jdk-alpine

# Рабочая папка внутри контейнера.
WORKDIR /app

# Копируем исходный код в контейнер.
COPY src ./src

# Компилируем Java-приложение.
RUN javac -encoding UTF-8 -d out src/main/java/ru/ideaforge/Main.java

# Документируем порт, который использует API.
EXPOSE 8080

# Запускаем приложение.
CMD ["java", "-cp", "out", "ru.ideaforge.Main"]
