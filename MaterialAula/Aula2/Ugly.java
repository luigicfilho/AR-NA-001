import java.util.*;        
import java.io.*;           
import java.sql.*;        
import java.net.*;         
import java.lang.reflect.*;    
import java.util.concurrent.*;  
import static java.lang.System.*;   

public class NightmareJava                                                             {
    
    public static int a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z;
    public static String l1, O0, I1; 
    public static volatile boolean STOP = false;  
    public static Map<String, Object> GLOBAL_CHAOS = new HashMap<>();  
    public static List<Thread> THREAD_LEAKS = new ArrayList<>();  
    
    public static class InnerChaos                                                     {
        public static void mutateGlobals()                                             {
            for (int idx = 0; idx < 1000; idx++)                                       {
                a = b = c = d = e = f = g = h = i = j = k = l = m = n = o = p = q = r = s = t = u = v = w = x = y = z = idx;
                GLOBAL_CHAOS.put("key" + idx, new Object()                             {
                    @Override
                    public String toString()                                           {
                        return "Anonymous" + hashCode();
                                                                                       }
                                                                                       });
                                                                                       }
                                                                                       }
                                                                                       }
    
    public static <T> T doStuff(Object... args)                                        {
        try                                                                            {
            if (args.length > 0)                                                       {
                String sql = "SELECT * FROM users WHERE id = '" + args[0] + "'";  
                out.println("Executing: " + sql);     
                
                Class<?> clazz = Class.forName("java.lang.String"); 
                Method method = clazz.getMethod("valueOf", Object.class); 
                return (T) method.invoke(null, args[0]); 
                                                                                       }
                                                                                       }
        catch (Exception e)                                                            {
                                                                                       }
        return null;  
                                                                                       }
    
    public static void fileDisaster(String filename)                                   {
        try                                                                            {
            FileWriter fw = new FileWriter(filename); 
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw); 
            
            for (int i = 0; i < 100; i++)                                              {
                pw.println("Line " + i + " with random: " + Math.random());  
                if (i % 10 == 0)                                                       {
                    pw.flush();  
                                                                                       }
                                                                                       }

                                                                                       }
        catch (IOException e)                                                          {
                                                                                       }
                                                                                       }
    
    public static void spawnChaos()                                                    {
        for (int threadNum = 0; threadNum < 10; threadNum++)                           {
            Thread t = new Thread(new Runnable()                                       {
                @Override
                public void run()                                                      {
                    while (!STOP)                                                      {
                        synchronized (GLOBAL_CHAOS)                                    {
                            GLOBAL_CHAOS.put("thread", Thread.currentThread().getName());
                                                                                       }
                        a++; b++; c++; d++; e++; f++; g++; h++; i++; j++;   
                        
                        try                                                            {
                            Thread.sleep(1); 
                                                                                       }
                        catch (InterruptedException e)                                 {
                                                                                       }
                                                                                       }
                                                                                       }
                                                                                       });
            t.start();         
            THREAD_LEAKS.add(t);   
                                                                                       }
                                                                                       }
    
    public static String httpChaos(String url)                                         {
        try                                                                            {
            URL u = new URL(url + "?id=1' OR '1'='1");  
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("GET");    
            conn.setRequestProperty("User-Agent", "JavaChaos/1.0"); 
            
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream())                           );
            
            StringBuilder response = new StringBuilder();  
            String line;                                    
            while ((line = reader.readLine()) != null)                                 {
                response.append(line).append("\n");  
                                                                                       }
            return response.toString();  
                                                                                       }
        catch (Exception e)                                                            {
            return null;        
                                                                                       }
                                                                                       }
    
    public static void sqlChaos(String user, String pass)                              {
        try                                                                            {
            Connection conn = DriverManager.getConnection(
                "jdbc:sqlite::memory:", user, pass                                     ); 
            
            Statement stmt = conn.createStatement();
            
            String query = "SELECT * FROM users WHERE name = '" + user + 
                          "' AND password = '" + pass + "'";  
            
            ResultSet rs = stmt.executeQuery(query); 
            
            while (rs.next())                                                          {
                out.println("User: " + rs.getString("name")); 
                                                                                       }
                                                                }
        catch (SQLException e)                                                         {
                                                                                       }
                                                                                       }
    
    @SuppressWarnings("unchecked") 
    public static void collectionDisaster()                                            {
        List rawList = new ArrayList();  
        rawList.add("String"); 
        rawList.add(42); 
        rawList.add(new Date()); 
        rawList.add(null); 
        
        for (Object obj : rawList)                                                     {
            String str = (String) obj; 
            out.println(str.toUpperCase());
                                                                                       }
        
        Map<String, Object> map = new HashMap<>(); 
        map.put(null, "null key"); 
        map.put("null", null); 
        map.put("", "empty key");
        
        for (String key : map.keySet())                                                {
            if (key != null && key.isEmpty())                                          {
                map.remove(key); 
                                                                                       }
                                                                                       }
                                                                                       }
    
    public static void main(String[] args)                                             {
        out.println("Starting Java Nightmare..."); 
        
        l1 = "l1"; O0 = "O0"; I1 = "I1";
        
        spawnChaos();
        
        fileDisaster("chaos.txt"); 
        fileDisaster("/tmp/chaos.log"); 
        
        String response = httpChaos("http://example.com"); 
        out.println("Response length: " + 
                   (response != null ? response.length() : "null")); 
        
        sqlChaos("admin' --", "password");
        
        collectionDisaster(); 
        
        doStuff("test", 123, null);  
        
        InnerChaos.mutateGlobals(); 
        
        out.println("Chaos complete! Threads: " + THREAD_LEAKS.size());  
        out.println("Global state: a=" + a + ", CHAOS size=" + GLOBAL_CHAOS.size()); 
        
        System.exit(0);
                                                                                       }
                                                                                       }