import java.sql.*;


class Student{													// public class Student with basic student details.
	String branch_code;
	String category;
	String regst_no;
	char sex;
	int region_code;											// region_code will be between 1 and 5 depending on whether the student
	
		Student(String branch,String categry,String rgstno,char sx,int regcode ){
			branch_code = branch;
			category = categry;
			regst_no = rgstno;
			sex = sx;
			region_code = regcode;
		}
}																//is from South,North,East,West,Central (still needs working out a little.


class Room{														// public class denoting a particular room.
	Student roomie1;
	Student roomie2;
	int room_no;
	Room(int rm_no,Student rmmate1,Student rmmate2){
		roomie1 = rmmate1;
		roomie2 = rmmate2;
		room_no = rm_no;
	}
}

public class JDBC{												// Connection with the Database, still in testing Phase.
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/publications";
	static final String USER = "root";
	static final String PASSWORD = "password";
	static Stack GeneralStack = new Stack();
	static Stack ReservedStack = new Stack();
	
	
	
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
				System.out.println(rs.getString("name"));
				System.out.println(rs.getInt("age"));
				System.out.println(rs.getString("college"));
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
		
		
		try{
		GeneralStack.fill_stack(student1);
		ReservedStack.fill_stack(student2);               // This block will further go into a for loop for filling up Stacks.student1 and student2 are not initailised yet. 
		}
		catch(InvalidRegionCodeException e){
			e.printStackTrace();
		}
		
		}
	}

