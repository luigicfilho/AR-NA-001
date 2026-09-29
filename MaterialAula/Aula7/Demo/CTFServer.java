import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;

public class CTFServer {

    private static final String JWT_SECRET = "super-secret-chave-nao-segura";

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/auth/login", new LoginHandler());
            server.createContext("/admin/flag", new FlagHandler());
            server.setExecutor(null);
            server.start();
            System.out.println("SERVIDOR ONLINE em http://localhost:8080");
            System.out.println("Instruções no README-CTF.txt");
        } catch (Exception e) {
            System.err.println("ERRO FATAL AO INICIAR SERVIDOR:");
            e.printStackTrace();
            System.exit(1);
        }
    }

    static class LoginHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) {
        
            try {
                if (!"POST".equals(exchange.getRequestMethod())) {
                    sendResponse(exchange, 405, "Apenas POST permitido");
                    return;
                }

                byte[] bodyBytes = exchange.getRequestBody().readAllBytes();
                String body = new String(bodyBytes, "UTF-8");

                if (body.contains("alice") && body.contains("senha123")) {
                    String token = JWT.create()
                        .withSubject("alice")
                        .withClaim("role", "user")
                        .sign(com.auth0.jwt.algorithms.Algorithm.HMAC256(JWT_SECRET));
                    sendResponse(exchange, 200, token);
                } else {
                    sendResponse(exchange, 401, "Credenciais inválidas");
                }
            } catch (Throwable e) {  
                e.printStackTrace();
                try {
                    sendResponse(exchange, 500, "Erro interno: " + e.getMessage());
                } catch (IOException ignored) {}
            }
        }
    }

    static class FlagHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) {
  
            try {
                String auth = exchange.getRequestHeaders().getFirst("Authorization");

                if (auth == null || !auth.startsWith("Bearer ")) {
                    sendResponse(exchange, 401, "Token não fornecido");
                    return;
                }

                String token = auth.substring(7);
                // VULNERABILIDADE
                var decoded = JWT.decode(token);
                // CORRECAO
                //var verifier = JWT.require(Algorithm.HMAC256("super-secret-chave-nao-segura"))
                //          .build();
                //var decoded = verifier.verify(token);

                String role = decoded.getClaim("role").asString();

                if ("admin".equals(role)) {
                    sendResponse(exchange, 200, "CTF{Vulnerabilidade_Decodificao_JWT_Sem_Verificacao_Explorada!}");
                } else {
                    sendResponse(exchange, 403, "Você não é admin.");
                }
            } catch (JWTDecodeException e) {
                e.printStackTrace();
                try {
                    sendResponse(exchange, 400, "Token inválido");
                } catch (IOException ignored) {}
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    sendResponse(exchange, 500, "Erro interno");
                } catch (IOException ignored) {}
            }
        }
    }

    private static void sendResponse(HttpExchange exchange, int code, String body) throws IOException {
        System.out.println(" Enviando resposta: " + code + " - " + body);
        byte[] bytes = body.getBytes("UTF-8");
        exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=utf-8");
        exchange.sendResponseHeaders(code, bytes.length);
        exchange.getResponseBody().write(bytes);
        exchange.close();
    }
}