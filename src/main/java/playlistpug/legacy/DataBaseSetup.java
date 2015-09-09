<<<<<<< HEAD:src/main/java/playlistpug/legacy/DataBaseSetup.java
package playlistpug.legacy;
//
//import database.PostgresDataBaseManager;
//import interfaces.ISetup;
//
//public class DataBaseSetup implements ISetup 
//{
//	private boolean hasBeenRun = false;
//	
//	private boolean startFromScratch = true;
//	
//	/*Singleton*/
//	private static DataBaseSetup instance = new DataBaseSetup();
//	
//	private DataBaseSetup()
//	{
//		
//	}
//	
//	public static DataBaseSetup getInstance()
//	{
//		return instance;
//	}
//	/**/
//	
//	//run setup if it hasn't
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
//		StringBuilder tableStructure = new StringBuilder();
//		String tableName = "";
//		
//		//create Table for songs
//		tableName = "songlist";
//		tableStructure.append(tableName);
//		tableStructure.append("(");
//		tableStructure.append("\"id\" SERIAL PRIMARY KEY"); tableStructure.append(", ");
//		tableStructure.append("\"title\" VARCHAR(256)");	tableStructure.append(", ");
//		tableStructure.append("\"lyrics\" VARCHAR(1701)");	tableStructure.append(", ");
//		tableStructure.append("\"tags\" VARCHAR(1701)");	tableStructure.append(", ");
//		tableStructure.append("\"songPath\" VARCHAR(500)");     //tableStructure.append(", ");
//		tableStructure.append(")");
//		
//		buildTable(tableName, tableStructure);
//		tableStructure.setLength(0);
//		
//		//create table for users
//		/*
//		tableName = "userList";
//		tableStructure.append(tableName);
//		tableStructure.append("(");
//		tableStructure.append("Name VARCHAR(250) PRIMARY KEY"); tableStructure.append(", ");
//		tableStructure.append("Title VARCHAR(256)");	tableStructure.append(", ");
//		tableStructure.append("Lyrics VARCHAR(1701)");	tableStructure.append(", ");
//		tableStructure.append("Tags VARCHAR(1701)");	tableStructure.append(", ");
//		tableStructure.append("Song bytea");			//tableStructure.append(", ");
//		tableStructure.append(")");
//		
//		buildTable(tableName, tableStructure);
//		tableStructure.setLength(0);
//		*/
//	}
//	
//	private void buildTable(String tableName, StringBuilder tableStructure)
//	{
//		PostgresDataBaseManager dataBaseManager = PostgresDataBaseManager.getInstance();
//		
//		if(startFromScratch)
//		{
//			dataBaseManager.deletetable(tableName);
//			dataBaseManager.createTable(tableStructure.toString());
//		}
//		else
//		{
//			dataBaseManager.createTable(tableStructure.toString());
//		}
//	}
//}
=======
package setup;

import database.PostgresDataBaseManager;
import interfaces.ISetup;

public class DataBaseSetup implements ISetup 
{
	private boolean hasBeenRun = false;
	
	private boolean startFromScratch = true;
	
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
		StringBuilder tableStructure = new StringBuilder();
		String tableName = "";
		
		//create Table for songs
		tableName = "songlist";
		tableStructure.append(tableName);
		tableStructure.append("(");
		tableStructure.append("\"id\" SERIAL PRIMARY KEY"); tableStructure.append(", ");
		tableStructure.append("\"title\" VARCHAR(256)");	tableStructure.append(", ");
		tableStructure.append("\"lyrics\" VARCHAR(1701)");	tableStructure.append(", ");
		tableStructure.append("\"tags\" VARCHAR(1701)");	tableStructure.append(", ");
		tableStructure.append("\"songPath\" VARCHAR(500)");     //tableStructure.append(", ");
		tableStructure.append(")");
		
		buildTable(tableName, tableStructure);
		tableStructure.setLength(0);
		
		//create table for users
		/*
		tableName = "userList";
		tableStructure.append(tableName);
		tableStructure.append("(");
		tableStructure.append("Name VARCHAR(250) PRIMARY KEY"); tableStructure.append(", ");
		tableStructure.append("Title VARCHAR(256)");	tableStructure.append(", ");
		tableStructure.append("Lyrics VARCHAR(1701)");	tableStructure.append(", ");
		tableStructure.append("Tags VARCHAR(1701)");	tableStructure.append(", ");
		tableStructure.append("Song bytea");			//tableStructure.append(", ");
		tableStructure.append(")");
		
		buildTable(tableName, tableStructure);
		tableStructure.setLength(0);
		*/
	}
	
	private void buildTable(String tableName, StringBuilder tableStructure)
	{
		PostgresDataBaseManager dataBaseManager = PostgresDataBaseManager.getInstance();
		
		if(startFromScratch)
		{
			dataBaseManager.deletetable(tableName);
			dataBaseManager.createTable(tableStructure.toString());
		}
		else
		{
			dataBaseManager.createTable(tableStructure.toString());
		}
	}
}
>>>>>>> ed4b4f84caf5893707e1f1b30ad1adf99ccbbf0b:src/main/java/setup/DataBaseSetup.java
