package student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
public class StudentDao {
	
   public static boolean insertStudentToDB(Student st)
   {
	   boolean f=false;
	   try
	   {
		   
		   Connection con=ConnectionProvider.createConnection();
		   String q="insert into student(sname,sphone,scity)values(?,?,?)";
		   student.PreparedStatement pstmt=(student.PreparedStatement) con.prepareStatement(q);
		   
		   //set value of parameter
		   pstmt.setString(1,st.getStudentName());
		   pstmt.setString(2,st.getStudentPhone());
		   pstmt.setString(3,st.getStudentCity());
		   
		   pstmt.executeUpdate();
		   f=true;
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   return f;
   }

public void deleteStudentFromDB(int studentId) {
	// TODO Auto-generated method stub
	
}

public List<Student> getAllStudents() {
	// TODO Auto-generated method stub
	return null;
}

public void updateStudentInDB(Student student) {
	// TODO Auto-generated method stub
	
}

public Student getStudentById(int studentId) {
	// TODO Auto-generated method stub
	return null;
}
}
