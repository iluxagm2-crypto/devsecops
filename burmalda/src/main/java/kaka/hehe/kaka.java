package kaka.hehe;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", port), 0);

        server.createContext("/health", exchange -> {
            if (!"GET".equals(exchange.getRequestMethod())) {
                send(exchange, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }
            send(exchange, 200, "{\"status\":\"ok\",\"service\":\"IdeaForge API\"}");
        });

        server.createContext("/projects/idea", exchange -> {
            if (!"GET".equals(exchange.getRequestMethod())) {
                send(exchange, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }
            String json = """
                {
                  "project": "IdeaForge",
                  "title": "Планировщик учебных задач",
                  "description": "Приложение помогает студентам планировать учебные проекты и отслеживать выполнение задач.",
                  "goal": "Организовать работу над учебным проектом",
                  "steps": [
                    "Определить цель проекта",
                    "Составить список задач",
                    "Установить сроки",
                    "Проверить результат"
                  ]
                }
                """;
            send(exchange, 200, json);
        });

        server.createContext("/", exchange ->
            send(exchange, 200, "{\"message\":\"IdeaForge API работает\",\"endpoints\":[\"GET /health\",\"GET /projects/idea\"]}")
        );

        server.start();
        System.out.println("IdeaForge API запущено на порту " + port);
        System.out.println("Доступные endpoint: GET /health, GET /projects/idea");
    }

    private static void send(HttpExchange exchange, int status, String body) throws IOException {
        byte[] response = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.getResponseHeaders().set("X-Content-Type-Options", "nosniff");
        exchange.sendResponseHeaders(status, response.length);
        try (var output = exchange.getResponseBody()) {
            output.write(response);
        }
    }
}
