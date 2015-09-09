<<<<<<< HEAD:src/main/java/playlistpug/legacy/MasterSetup.java
package playlistpug.legacy;
//
//import interfaces.ISetup;
//
//public class MasterSetup implements ISetup {
//
//	private boolean hasBeenRun = false;
//	
//	/*Singleton*/
//	private static MasterSetup instance = new MasterSetup();
//	
//	private MasterSetup()
//	{
//		
//	}
//	
//	public static MasterSetup getInstance()
//	{
//		return instance;
//	}
//	/**/
//
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
//	@Override
//	public boolean hasBeenRun()
//	{
//		return hasBeenRun;
//	}
//	
//	private void setup()
//	{
//		DependencyInjectorSetup.getInstance().run();
//		DataBaseSetup.getInstance().run();
//	}
//
//}
=======
package setup;

import interfaces.ISetup;

public class MasterSetup implements ISetup {

	private boolean hasBeenRun = false;
	
	/*Singleton*/
	private static MasterSetup instance = new MasterSetup();
	
	private MasterSetup()
	{
		
	}
	
	public static MasterSetup getInstance()
	{
		return instance;
	}
	/**/

	@Override
	public void run() 
	{
		if(!hasBeenRun)//this is to make sure that it is only ran once
		{
			hasBeenRun = true;
			setup();
		}
	}

	@Override
	public boolean hasBeenRun()
	{
		return hasBeenRun;
	}
	
	private void setup()
	{
		DependencyInjectorSetup.getInstance().run();
		//DataBaseSetup.getInstance().run();
	}

}
>>>>>>> ed4b4f84caf5893707e1f1b30ad1adf99ccbbf0b:src/main/java/setup/MasterSetup.java
