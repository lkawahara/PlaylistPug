package setup;

import interfaces.ISetup;

public class DataBaseSetup implements ISetup 
{
	private boolean hasBeenRun = false;
	
	/*Singleton*/
	private static DataBaseSetup instance = new DataBaseSetup();
	
	private DataBaseSetup()
	{
		
	}
	
	public static DataBaseSetup getInstance()
	{
		return instance;
	}
	/**/
	
	//run setup if it hasn't
	@Override
	public void run() 
	{
		if(!hasBeenRun)//this is to make sure that it is only ran once
		{
			hasBeenRun = true;
			setup();
		}
	}

	//Has it already been run 
	@Override
	public boolean hasBeenRun()
	{
		return hasBeenRun;
	}
	
	//What to do
	private void setup()
	{
		
		//create Table for songs
		
		//create table for users
	}
}
