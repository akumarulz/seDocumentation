package com.codelight.dbservice.service.impl.archiving;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codelight.dbservice.propertiesclasses.MysqlProperties;
import com.codelight.dbservice.service.interf.archiving.DbServiceArchivingManualInterf;
import com.michael.documentation.resources.model.archiving.ArchiveEntry;

@Service
public class DbServiceArchivingManualImpl implements DbServiceArchivingManualInterf {
	
	@Autowired
	private MysqlProperties mysqlProperties;

	@Override
	public List<ArchiveEntry> getEntries(Integer limit, Integer offset) {
		if(limit == null) {
			limit = Integer.MAX_VALUE; // basically no limit
		}
		if(offset == null) {
			offset = 0; // no offset
		}
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<ArchiveEntry> archiveList = new ArrayList<>();
		try {
			conn = getConnection();
			st = conn.createStatement();
			String query = String.format("SELECT * FROM %s LIMIT %d OFFSET %d", mysqlProperties.getArchiveTable(),limit,offset);
			rs = st.executeQuery(query);
			
			while(rs.next()){
				archiveList.add(new ArchiveEntry(rs.getLong("id"), rs.getString("archive_entry")));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}finally {
			closeConnections(conn, rs, st);
		}
		return archiveList;
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection(mysqlProperties.getUrl(), mysqlProperties.getUsername(), mysqlProperties.getPassword());
		
		return connection;
	}
	
	private void closeConnections(Connection conn, ResultSet rs, Statement st) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
