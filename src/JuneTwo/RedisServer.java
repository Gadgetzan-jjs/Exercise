import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class RedisServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6379);
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                byte[] bytes = new byte[1024];
                int a = inputStream.read(bytes);
                String context = null;
                if (a != -1) {
                    context = new String(bytes, 0,a);
                }
                String[] arry = context.split("\r\n");
                for(int i=0;i<arry.length;i++){
                    System.out.println(arry[i]);
                }
                switch (arry[0]) {
                    case "*3":
                        outputStream.write(Map.Set(arry).getBytes());
                        outputStream.flush();
                        break;
                    case "*2":
                        outputStream.write(Map.Get(arry).getBytes());
                        outputStream.flush();
                        break;
                    default:
                        outputStream.write("-错误\r\n".getBytes());
                        outputStream.flush();
                }

                inputStream.close();
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Map{
     private static HashMap hashMap=new HashMap();
   public static String Set(String[] string){
       hashMap.put(string[4],string[6]);
       return "+ok\r\n";
   }
   public static String Get(String[] string){
       return "+"+String.valueOf(hashMap.get(string[4]))+"\r\n";
   }
}

