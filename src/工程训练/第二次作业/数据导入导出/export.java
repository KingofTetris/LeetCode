package 工程训练.第二次作业.数据导入导出;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class export {

	public static void main(String[] args) throws ClassNotFoundException,SQLException,IOException, ParseException{

		String tableName="sys_role";
		String createTable= "create table "+ tableName +"(\n" ;
		List<String> columnNames=new ArrayList<String>();
		List<String> columnTypes=new ArrayList<String>();
		List<String> insertSQList=new ArrayList<String>();
		final String URL="jdbc:mysql://127.0.0.1:3306/testdb";
   		final String NAME="root";
   		final String PASSWORD="root";
   		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(URL,NAME,PASSWORD);
		PreparedStatement ps = conn.prepareStatement("select * from sys_role");
		ResultSet rs=ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		for(int i = 1; i<=rsmd.getColumnCount(); i++) {
			columnTypes.add(rsmd.getColumnTypeName(i));
			columnNames.add(rsmd.getColumnName(i));
		}
		int len= columnNames.size();
		for (int i = 0; i < len-1; i++) {
			createTable=createTable+columnNames.get(i)+" "+columnTypes.get(i)+",\n";
		}
		createTable=createTable+columnNames.get(len-1)+" "+columnTypes.get(len-1)+"\n);\n";
		
		while(rs.next()){
			List<Object> datas=new ArrayList<Object>();
			for(int i=0;i<rsmd.getColumnCount();i++) {
				Object data=rs.getObject(columnNames.get(i));
				datas.add(data);
			}
			String insetSQL="insert into "+tableName+" value(";
			for (int i = 0; i < datas.size()-1; i++) {
				if(!columnTypes.get(i).equalsIgnoreCase("int")&&!columnTypes.get(i).equalsIgnoreCase("float")) {
					if(datas.get(i)==null)
						insetSQL=insetSQL+datas.get(i)+",";
					else 
						insetSQL=insetSQL+"'"+datas.get(i)+"',";
				}
				else
					insetSQL=insetSQL+datas.get(i)+",";
			}
			if(!columnTypes.get(datas.size()-1).equalsIgnoreCase("int")&&!columnTypes.get(datas.size()-1).equalsIgnoreCase("float")) {
				if(datas.get(datas.size()-1)==null)
					insetSQL=insetSQL+datas.get(datas.size()-1)+");";
				else 
					insetSQL=insetSQL+"'"+datas.get(datas.size()-1)+"');";
			}
			else
				insetSQL=insetSQL+datas.get(datas.size()-1)+");";
			insertSQList.add(insetSQL);
			
		}
		
		ps.close();
		conn.close();
		BufferedWriter fw = new BufferedWriter(new FileWriter("./export.txt"));
		fw.write(createTable);	
		fw.newLine();
//		System.out.println(createTable);
		for (int i = 0; i < insertSQList.size(); i++) {
//			System.out.println(insertSQList.get(i));
			fw.write(insertSQList.get(i));	
			fw.newLine();
		}
		fw.close();
	}
}
