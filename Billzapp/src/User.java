import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//This class is to add new user or tenant
public class User {
	private String name,email;

	public void AddUser() throws IOException
	{	
		System.out.println("Want to add new User?");
		System.out.print("Name & email id : ");
		BufferedReader conn = new BufferedReader(new InputStreamReader(System.in));
		name=conn.readLine();
		email=conn.readLine();
		JDBCConn con = new JDBCConn();
		con.Adduser(name,email);
		System.out.print("Welcome "+name);
	}
}
