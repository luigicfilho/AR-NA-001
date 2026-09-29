import java.sql.*;
import java.util.Scanner;

public class SqlInjectionDemo {
    public static void main(String[] args) throws Exception {
        // Criar DB em memória
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE users(id INT AUTO_INCREMENT, username VARCHAR(50), password VARCHAR(50))");
        stmt.execute("INSERT INTO users(username, password) VALUES('admin','1234'),('user','abcd')");

        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            System.out.print("Digite o nome de usuário (ou 'sair'): ");
            String user = sc.nextLine();

            if (user.equalsIgnoreCase("sair")) {
                continuar = false;
                continue;
            }

            System.out.print("Digite a senha: ");
            String pass = sc.nextLine();

            String sqlVulneravel = "SELECT * FROM users WHERE username='" + user + "' AND password='" + pass + "'";
            
            //System.out.println("\n[DEBUG] Query: " + sqlVulneravel);
            
            ResultSet rsVulneravel = stmt.executeQuery(sqlVulneravel);

            if (rsVulneravel.next()) {
                System.out.println("Login bem-sucedido!");
                //printResultSet(rsVulneravel);
            } else {
                System.out.println("Falha no login!");
            }
        }
        conn.close();
        sc.close();
    }

    public static void printResultSet(ResultSet rs) throws SQLException {
    ResultSetMetaData meta = rs.getMetaData();
    int colCount = meta.getColumnCount();
    
    //for (int i = 1; i <= colCount; i++) {
    //    System.out.printf("%-15s", meta.getColumnName(i).toUpperCase());
    //}
    System.out.println("\n" + "=".repeat(colCount * 15));
    
    do {
        for (int i = 1; i <= colCount; i++) {
            System.out.printf("%-15s", rs.getString(i));
        }
        System.out.println();
    } while (rs.next());
    }
}