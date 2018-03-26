  import org.json.simple.JSONObject;
  import org.json.simple.parser.JSONParser;
  import org.json.simple.parser.ParseException;
  
  public class LivyClintJobData{
    public Long id;
    public String state;
    public String appId;
    public String appInfo;
    public String log;
    
    LivyClientJobData(String jobDataJsonStr){
      SetJsonString(jobDataJsonStr);
    }
    
    public void SetJsonSring(String jobDataJsonStr){
      if(jobDataJsonStr != null){
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        try{
          jsonObject = (JSONObject)parser.parse(jobDataJsonStr);
          this.id = (Long)jsonObject.get("id");
          this.state = (String)jsonObject.get("state");
        } catch (ParseException e){
          e.printStackTrace();  
        }
      }
    }
    
  }
