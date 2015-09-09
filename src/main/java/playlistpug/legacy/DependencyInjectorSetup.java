package playlistpug.legacy;

//import database.DummyDALpug;
//
//public class DependencyInjectorSetup implements ISetup 
//{
//	private boolean hasBeenRun = false;
//	
//	/*Singleton*/
//	private static DependencyInjectorSetup instance = new DependencyInjectorSetup();
//	
//	private DependencyInjectorSetup()
//	{
//		
//	}
//	
//	public static DependencyInjectorSetup getInstance()
//	{
//		return instance;
//	}
//	/**/
//	
//	//run setup
//	@Override
//	public void run() 
//	{
//		if(!hasBeenRun)//this is to make sure that it is only ran once
//		{
//			hasBeenRun = true;
//			setup();
//		}
//	}
//
//	//Has it already been run 
//	@Override
//	public boolean hasBeenRun()
//	{
//		return hasBeenRun;
//	}
//	
//	//What to do
//	private void setup()
//	{
//		DependencyInjector dependencyInjector = DependencyInjector.getInstance();
//		
//		dependencyInjector.add("IDALpug", new DummyDALpug());
//	}
//
//}
