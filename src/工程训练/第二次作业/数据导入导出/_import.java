package 工程训练.第二次作业.数据导入导出;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class _import {

	public static void main(String[] args) throws ClassNotFoundException,SQLException,IOException, ParseException{
		List<TestRole> testRoles =new ArrayList<TestRole>();
		BufferedReader br=new BufferedReader(new FileReader("./import.txt"));
   		String line = "";
   		while ((line = br.readLine())!=null) {
   			if(!line.isEmpty()) {
   				String[] str0=line.split(";");
   				String[] str= {"","","","","","","",""};
   				for(int i=0;i<str0.length;i++) {
   					str[i]=str0[i];
   				}
   				TestRole testRole =new TestRole();
   				testRole.setId(str[0]);
   				testRole.setRoleName(str[1]);
   				testRole.setRoleCode(str[2]);
   				testRole.setDescription(str[3]);
   				testRole.setCreateBy(str[4]);
   				testRole.setCreateTime(str[5]);
   				testRole.setUpdateBy(str[6]);
   				testRole.setUpdateTime(str[7]);
   				testRoles.add(testRole);
   			}
   		}
   		final String URL="jdbc:mysql://127.0.0.1:3306/testdb";
   		final String NAME="root";
   		final String PASSWORD="root";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(URL,NAME,PASSWORD);
//		System.out.println("Connected");
		
		for(int i = 0; i< testRoles.size(); i++) {
			TestRole testRole = testRoles.get(i);
			PreparedStatement ps = conn.prepareStatement("insert into sys_role value(?,?,?,?,?,?,?,?)");
			ps.setString(1, testRole.getId());
			ps.setString(2, testRole.getRoleName());
			ps.setString(3, testRole.getRoleCode());
			ps.setString(4, testRole.getDescription());
			ps.setString(5, testRole.getCreateBy());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = null;
			Timestamp createtime=null;
			if(!testRole.getCreateTime().equals("")) {
				date = format.parse(testRole.getCreateTime());
				createtime = new Timestamp(date.getTime());
			}
			ps.setTimestamp(6,createtime);
			ps.setString(7, testRole.getUpdateBy());
			Timestamp updatetime=null;
			if(!testRole.getUpdateTime().equals("")) {
				date = format.parse(testRole.getUpdateTime());
				updatetime = new Timestamp(date.getTime());
			}
			ps.setTimestamp(8, updatetime);
			ps.executeUpdate();
			ps.close();
		}
		conn.close();
		
	}

}
