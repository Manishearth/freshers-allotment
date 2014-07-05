import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


class Student{
	String name;												// public class Student with basic student details.
	String branch_code;
	String category;
	String regst_no;
	String sex;
	int region_code;											// region_code will be between 1 and 5 depending on whether the student
	
		Student(String nme,String branch,String categry,String rgstno,String sx ){
			branch_code = branch;
			category = categry;
			regst_no = rgstno;
			sex = sx;
			name = nme;
		}
}																//is from South,North,East,West,Central (still needs working out a little.


class Room{														// public class denoting a particular room.
	Student roomie1;
	Student roomie2;
	int room_no;
	String branch_code;
	Room(int rm_no,Student rmmate1,Student rmmate2,String brnchcde){
		roomie1 = rmmate1;
		roomie2 = rmmate2;
		room_no = rm_no;
		branch_code = brnchcde;
	}
}

public class JDBC{												// Connection with the Database, still in testing Phase.
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/publications";
	static final String USER = "root";
	static final String PASSWORD = "password";
	static Stack GeneralStack = new Stack();
	static Stack ReservedStack = new Stack();
	static Vector GeneralStudents = new Vector();
	static Vector ReservedStudents = new Vector();
	
	
	public static void fill_students(ResultSet res, Vector genstudents,Vector resstudents){
		
		try {
			if(res.getString("category").equals("GE")){
			try{
			genstudents.add(new Student(res.getString("name"),res.getString("branch_code"),"GE",res.getString("regst_no"),res.getString("sex")));
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			}
			else{
				try{
					resstudents.add(new Student(res.getString("name"),res.getString("branch_code"),res.getString("category"),res.getString("regst_no"),res.getString("sex")));
					}
					catch(SQLException e){
						e.printStackTrace();
					}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void assign_region_code(Student student){
		
		// Assign Region Code (1 -5);
		
	}
	

	
	
	
	
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
																				//the query can be modified so as to set region_codes here it self, (later).
			while(rs.next()){
				fill_students(rs,GeneralStudents,ReservedStudents);
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
		GeneralStudents.trimToSize();
		ReservedStudents.trimToSize();
		
		
		
		
		
		try{
			for(Object genstudent:GeneralStudents){
				if(genstudent instanceof Student){
					assign_region_code((Student) genstudent);
					GeneralStack.fill_stack((Student) genstudent);
				}
			}
			for(Object resstudent:ReservedStudents){
				if(resstudent instanceof Student){
					assign_region_code((Student) resstudent);
					ReservedStack.fill_stack((Student) resstudent);
				}
			}																							// This block will further go into a for loop for filling up Stacks.student1 and student2 are not initailised yet. 
		}
		catch(InvalidRegionCodeException e){
			e.printStackTrace();
		}
		
		}
	}

