import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Statement {
	private String to,by;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}
	
	public void getStatement() throws IOException
	{
		System.out.println("to ?");
		System.out.print("Name : ");
		BufferedReader conn = new BufferedReader(new InputStreamReader(System.in));
		to=conn.readLine();
		System.out.println("by ?");
		System.out.print("Name : ");
		by=conn.readLine();
		JDBCConn con = new JDBCConn();
		float amount = con.Statement(to,by);
		System.out.print(by+" has to give "+amount+" to "+to);
	}
}
