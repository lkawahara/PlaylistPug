package dependencyInjector;

public interface DependencyInjectorTemplate<K,T>
{
	K add(K key, T data);
	
	T get(K key);
	
	T remove(K key);
}
