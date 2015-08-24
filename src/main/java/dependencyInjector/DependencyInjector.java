package dependencyInjector;

import java.util.HashMap;
import java.util.Map;

public class DependencyInjector implements DependencyInjectorTemplate<String, Object>
{
	/*Singleton*/
	private static DependencyInjector instance = new DependencyInjector();
	
	private DependencyInjector()
	{
		
	}
	
	public static DependencyInjector getInstance()
	{
		return instance;
	}
	/**/

	Map<String, Object> storage = new HashMap<String, Object>();
	
	@Override
	public String add(String key, Object data) 
	{
		storage.put(key, data);
		return key;
	}

	@Override
	public Object get(String key) 
	{
		return storage.get(key);
	}

	@Override
	public Object remove(String key)
	{
		return storage.remove(key);
	}
	
}
