import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Transaction {
	private String paidby,paidto;
	private java.sql.Date  transaction_date;
	private float amount;
	private int no_of_contributors;
	private float share;
	
	public float getShare() {
		return share;
	}

	public void setShare(float share) {
		this.share = share;
	}

	public int getNo_of_contributors() {
		return no_of_contributors;
	}

	public void setNo_of_contributors(int no_of_contributors) {
		this.no_of_contributors = no_of_contributors;
	}

	public String getPaidby() {
		return paidby;
	}

	public void setPaidby(String paidby) {
		this.paidby = paidby;
	}

	public String getPaidto() {
		return paidto;
	}

	public void setPaidto(String paidto) {
		this.paidto = paidto;
	}

	public java.sql.Date getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(java.sql.Date transaction_date) {
		this.transaction_date = transaction_date;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public void addTransaction() throws IOException, ParseException
	{
		Transaction trans = new Transaction();
		System.out.println("Want to add new Transaction?");
		System.out.println("Name :");
		BufferedReader conn = new BufferedReader(new InputStreamReader(System.in));
		trans.paidby=conn.readLine();
		
		System.out.println("Where :");
		trans.paidto=conn.readLine();
		
		System.out.println("Date of billing :");
		String date = conn.readLine();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        java.util.Date parsed = format.parse(date);
        trans.transaction_date = new java.sql.Date(parsed.getTime());
        
		System.out.println("Amount :");
		trans.amount=Float.parseFloat(conn.readLine());
		
		System.out.println("Number of contributors :");
		trans.no_of_contributors=Integer.parseInt(conn.readLine());
		
		trans.share=trans.amount/trans.no_of_contributors;
		
		JDBCConn con = new JDBCConn();
		con.AddTransaction(trans);
		
		//Add Contributors list
		Contributors cont = new Contributors();
		cont.addContributors(trans);
	}

}
