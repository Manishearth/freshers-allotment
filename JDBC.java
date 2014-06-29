import java.sql.*;

public class JDBC{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/publications";
	static final String USER = "root";
	static final String PASSWORD = "password";
	
	public static void main(String args[]){
		Connection conn = null;
		Statement stmnt = null;
		try{
			Class.forName(JDBC_DRIVER);
			System.out.println("Connnecting to the database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
			
			System.out.println("Creating Statement...");
			stmnt = conn.createStatement();
			String sql = "SELECT * FROM practice ";
			ResultSet rs = stmnt.executeQuery(sql);
			
			while(rs.next()){
				System.out.println(rs.getString("sex"));
				System.out.println(rs.getInt("regst_no"));
				System.out.println(rs.getString("category"));
				System.out.println();
			}
			conn.close();
			stmnt.close();
			rs.close();
		}
			
			catch(SQLException e){
				e.printStackTrace();
			}
			catch(Exception e){
				e.printStackTrace();
				
			}
		}
	}

