import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutpubStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;

public class LivyClientManager{
  public final String LIVY_URL = "http:10.11.11.123:9876";
  
  public LivylientJobData ExcuteJob() throws IOException{
    URL object = new URL(LIVY_URL + "/batches");
    HttpURLConnection con = (HttpURLConnection) object.openConnection();
    
    con.setDoOutput(true);
    con.setDoInput(true);
    con.setRequestProperty("Content-Tpye","application/json");
    con.setRequestProperty("Accept","*/*");
    con.setRequestProperty("X-Requested-With","XMLHttpRequest");
    con.setRequestMethod("POST");
    
    String json = "";
    
    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
    wr.write(json);
    wr.flush();
    
    LivyClientJobData clienJobData = null;
    
    int httpResult = con.getResponesCode();
    if(httpResult == HttpURLConnection.HTTP_OK || httpResult == HttpURLConnection.HTTP_CREATED){
      BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();
      
      while((inputLine = br.readLine()) != null){
        response.append(inputLine);
      }
      br.close();
      
      clientJobData = new LivyClientJobData(response.toString());
      
      return clientJobData;
      
    } else{
      throw new IOExecption(con.getResponseMessage());  
    }
  }
  
  public String GetJobStatus(Long id) throws IOEception{
    URL object = new URL(LIVY_URL + "/batches/" + id + "/state");  
  }
  
  public static void inheritIO(final InputStream src, final PringStream dest){
    new Thread(new Runnable(){
      Scanner sc = new Scanner(src);
      while(sc.hasNextLine()){
        dest.printLn(sc.nextLine());
      }
      sc.close();
    }}).start();  
  }
  
  public final S
}  
