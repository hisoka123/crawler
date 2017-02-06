import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import com.crawler.htmlparser.AbstractParser;

public class GenerateSqlTest {
	
	public static void main(String[] args) throws IOException {
		generateComment(new File("E:/workspace-ec-upbase01/gsxt-jpa/sql/srdb/gsxt-srdb-comment.sql"), new File("C:/Users/Administrator/Desktop/comment.sql"));
	}
	
	public static void generateComment(File src, File dis) throws IOException {
		StringBuffer sb = new StringBuffer("--Generated SQL（version1.0）");
		String srcStr = FileUtils.readFileToString(src, "UTF-8");
		AbstractParser parser = new AbstractParser(){};
		
		List<String> resultList = parser.getSubStringByRegex(srcStr, "CREATE TABLE [\\w\\s\\(\n,\"'\\)]*");
		for (String table : resultList) {
			//表名
			String tableName = parser.getSubStringByRegex(table, "CREATE TABLE \\w* \\(").get(0).replaceAll("CREATE TABLE ", "").replaceAll(" \\(", "");
			sb.append("\n\n\n/*=====================================================\n " + tableName + "\n=====================================================*/\n\n");
			sb.append(table + "\n\n");
			sb.append("comment on table public."+tableName+" is '';\n");
			
			//字段名
			String[] columnNames = table.split("\n");
			if (columnNames!=null && columnNames.length>2) {
				for(int i=1; i<columnNames.length-1; i++) {
					String columnName = columnNames[i];
					columnName = columnName.trim();
					columnName = columnName.substring(0, columnName.indexOf(' '));
					sb.append("comment on column public."+tableName+"."+columnName+" is '';\n");
				}
			}
		}
		
		FileUtils.writeStringToFile(dis, sb.toString());
		System.out.println("DONE!");
	}
}
