import java.io.IOException;

pulbic class LivyClient{
  static LivyClientManager livyClientManager = new LivyClientManager();
  
  public static void main(String{} args) throws Exception {
    try{
      //job
      livyClientJobData ClientJobData = livyClientManager.ExuteJob();
      
      boolean success = false;
      while (true){
        String status = livyClientManager.GetJobStatus(clientJobData.id);
        
        if(status.equals("success")){
          success = true;
          break;
        } else if(status.equals("starting") || status.equals("running")) {
          Thread.sleep(1000L);
          continue;
        } ekse {
          success = false;
          break;
        }
      }
      
      if(success){
        String resultData = livyClientManager.GetResult();
        livyClientManager.ClearResult();
      }
    }catch(IOException e){
      e.printStackTrace();
    }
  }
  
}
