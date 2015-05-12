import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Contributors {
	private ArrayList<String> contributor_list= new ArrayList<String>();
	private String name;
	public void addContributors(Transaction trans) throws IOException{
	BufferedReader conn = new BufferedReader(new InputStreamReader(System.in));	
	for(int i=0; i<trans.getNo_of_contributors();i++)
	{
		System.out.println("Contributor name:");
		name=conn.readLine();
		contributor_list.add(name);
	}
	JDBCConn con = new JDBCConn();
	for(int i=0;i<contributor_list.size();i++)
	{
		if(!contributor_list.get(i).equalsIgnoreCase(trans.getPaidby()))
			con.AddContributors(contributor_list.get(i), trans);
	}
}
}

