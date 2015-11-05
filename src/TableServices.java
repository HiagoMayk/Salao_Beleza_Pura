import java.util.HashMap;
import java.util.Map;
  
public class TableServices 
{
	Map<String,Float> services;
	
	public TableServices()
	{
		services = new HashMap<String,Float>();
		
		//Services default 
		services.put("Penteado", new Float(50.0));
		services.put("Corte", new Float(30.0));
		services.put("Corte e Penteado", new Float(40.0));
		services.put("Lavagem", new Float(0.0));
		services.put("Pedicure", new Float(30.0));
		services.put("Depilação", new Float(40.0));
		services.put("Massagem", new Float(20.0));
	}
	
	public void addService(String service, float value)
	{
		services.put(service, value);
	}
	
	public float searchPrice(String service)
	{
		if(services.containsKey(service)) 
		{
            return services.get(service);            
        }
		return -1;
	}
	
	public void listServices()
	{
		for (String service : services.keySet()) 
		{
            float value = services.get(service);
            System.out.println(service + " = " + value);
		}
	}
	
	/*//Testes 
	public static void main (String args[]){
        
		TableServices services = new TableServices();
	       
		services.listServices();
		
		System.out.println(services.searchPrice("Corte"));
	               
	}
	*/
	     
}


