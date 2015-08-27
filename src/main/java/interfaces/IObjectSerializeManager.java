package interfaces;

import java.util.Map;

public interface IObjectSerializeManager<T>
{
	Map<String, Object> SerializeObject(T object);
	
	
}
