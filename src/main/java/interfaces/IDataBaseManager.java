package interfaces;

import java.sql.ResultSet;
import java.util.List;

public interface IDataBaseManager
{
	public boolean createTable(String tableStructure);
	
	public boolean deletetable(String tableName);
	
	public ResultSet Pulliteam(String tableName, String whatToPull, String where, String regex, String OrderBy);
	
	public int InsertIteam(String tableName, List<String> Values);
}
