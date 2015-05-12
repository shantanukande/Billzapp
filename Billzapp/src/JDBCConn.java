import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class JDBCConn {
	
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "Shaan@123";
   
  
   
   public void Adduser(String name, String email) {
   Connection conn = null;
   Statement stmt = null;
   String username = name;
   String email_id=email;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
      
      //STEP 4: Execute a query
      System.out.println("Inserting records into the table...");
     
     stmt = conn.createStatement();
      
      String sql = "call billzapp.adduser('"+username+"','"+email_id+"')";
      stmt.executeQuery(sql);
     
     
      System.out.println("Inserted records into the table...");

   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
   }//end try
   System.out.println("Goodbye!");
}



public void AddTransaction(Transaction trans) {
	   Connection conn = null;
	   Statement stmt = null;
	   
	   try{
		 //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      System.out.println("Inserting records into the table...");
		     
		     stmt = conn.createStatement();
		      
		      String sql = "call billzapp.addTransaction('"+trans.getPaidby()+"','"+trans.getPaidto()+"','"+trans.getTransaction_date()+"',"+trans.getAmount()+","+trans.getNo_of_contributors()+","+trans.getShare()+")";
		      stmt.executeQuery(sql);
		      //System.out.println(sql);
		     
		     
		      System.out.println("Inserted records into the table...");
	     
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		   }//end try
		   System.out.println("Goodbye!");
		
}

public void AddContributors(String contributor,
		Transaction trans) {
		Connection conn = null;
	    Statement stmt = null;
	    Statement stmt1 = null;
	    Statement stmt2 = null;
	    Statement stmt3 = null;
	   
	   try{
		 //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      
		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      System.out.println("Inserting records into the table...");
		     
		      int id = 0,id1=0,id2 = 0;
		      stmt = conn.createStatement();
		      String sql = "select id from billzapp.user where name='"+trans.getPaidby()+"'";
		      //stmt.executeQuery(sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      if(rs.next()){
		      id= rs.getInt("id");
		      }
		      
		      stmt1 = conn.createStatement();
		      String sql1 = "select id from billzapp.user where name='"+contributor+"'";
		      //stmt.executeQuery(sql);
		      ResultSet rs1 = stmt1.executeQuery(sql1);
		      if(rs1.next()){
		      id1= rs1.getInt("id");
		      }
		      
		      stmt2 = conn.createStatement();
		      String sql2 = "select max(id) as id from billzapp.transaction";
		      //stmt.executeQuery(sql);
		      ResultSet rs2 = stmt2.executeQuery(sql2);
		      if(rs2.next()){
		      id2= rs2.getInt("id");
		      }
		      
		      stmt3 = conn.createStatement();
		      String sql3 = "insert into billzapp.contributor(id,paid_by_id,contributor,amount,transaction_date) values ("+id2+","+id+","+id1+","+trans.getShare()+",'"+trans.getTransaction_date()+"')";
		      stmt3.executeUpdate(sql3);
		      
		      System.out.println("Inserted records into the table...");
	     
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		   }//end try
		   System.out.println("Goodbye!");
	
}



public float Statement(String to, String by) {
	Connection conn = null;
    Statement stmt = null;
    Statement stmt1 = null;
    float amount1=0f, amount2=0f;
    
   try{
	 //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");
	      
	      //STEP 3: Open a connection
	      System.out.println("Connecting to a selected database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      System.out.println("Connected database successfully...");
	      
	      //STEP 4: Execute a query
	      System.out.println("Inserting records into the table...");
	     
	      stmt = conn.createStatement();
	      String sql = "select sum(amount) as amount from billzapp.contributor where paid_by_id in (select id from billzapp.user where name='"+to+"') and contributor in (select id from billzapp.user where name='"+by+"');";
	      //stmt.executeQuery(sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      if(rs.next()){
	      amount1= rs.getFloat("amount");
	      }
	      
	      stmt1 = conn.createStatement();
	      String sql1 = "select sum(amount) as amount from billzapp.contributor where paid_by_id in (select id from billzapp.user where name='"+by+"') and contributor in (select id from billzapp.user where name='"+to+"');";
	      //stmt.executeQuery(sql);
	      ResultSet rs1 = stmt1.executeQuery(sql1);
	      if(rs1.next()){
	      amount2= rs1.getFloat("amount");
	      }
     
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	   }//end try
	return amount1-amount2;
}
}//end JDBCExample