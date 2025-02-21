import java.io.*;
import java.net.Socket;
public class Client{

    Socket socket;
    BufferedReader br;
    PrintWriter out;
    public Client(){

      try{
        System.out.println("Client is running");
        socket = new Socket("127.0.0.1", 5000);
        System.out.println("Connected to server");


        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out = new PrintWriter(socket.getOutputStream(), true);

        startReading();
        startWriting();
      } catch(Exception e){
        System.out.println(e);
      }
      
    }
  // Start reading from client...
  public void startReading(){
      Runnable r1 = () -> {
          try {
              String line;
              while ((line = br.readLine()) != null) {
                  out.println("Server: " + line);
              }
          } catch (Exception e) {
              System.out.println(e);
          }
      };

      new Thread(r1).start();
  }
  // Start writing to client...
  public void startWriting(){
      Runnable r2 = () -> {
          System.out.println("Writer Started...");
          try{
              String line;
              while((line = br.readLine()) != null){
                  out.println("Server: " + line);
              }
          } catch (Exception e){
              System.out.println(e);
          }
      };
      new Thread(r2).start();
  }
      
    public static void main(String[] args){
        System.out.println("Client is running");
    }
}
