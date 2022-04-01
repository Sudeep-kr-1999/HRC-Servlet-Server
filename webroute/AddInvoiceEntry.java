package sudeep.servlet.hrcproject.webroute;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import sudeep.servlet.hrcproject.database.DatabaseConnection;

@WebServlet("/AddInvoiceEntry")
public class AddInvoiceEntry extends HttpServlet {
	private static Connection connection;
	private PreparedStatement preparedstatement;
	private Statement statement;
	private ResultSet rs;
	private static final long serialVersionUID = 1L;
	private static final String DB_URL = DatabaseConnection.getDbUrl();
	private static final String PASSWORD = DatabaseConnection.getPassword();
	private static final String USER = DatabaseConnection.getUser();
	private static final String DRIVER = DatabaseConnection.getDriver();
	private String business_code;
	private String cust_number;
	private String clear_date;
	private String business_year;
	private String doc_id;
	private String posting_date;
	private String document_create_date;
	private String document_create_date1;
	private String due_in_date;
	private String invoice_currency;
	private String document_type;
	private String posting_id;
	private String total_open_amount;
	private String baseline_create_date;
	private String cust_payment_terms;
	private String invoice_id;
	private String sl_no;
	private String area_business;

	@Override
	public void init() throws ServletException {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addData(JsonObject jsondata) throws SQLException {
//		storing data into variables 
		this.business_code = jsondata.get("business_code").getAsString();
		this.cust_number = jsondata.get("cust_number").getAsString();
		this.clear_date = jsondata.get("clear_date").getAsString();
		this.business_year = jsondata.get("business_year").getAsString();
		this.doc_id = jsondata.get("doc_id").getAsString();
		this.posting_date = jsondata.get("posting_date").getAsString();
		this.document_create_date = jsondata.get("document_create_date").getAsString();
		this.due_in_date = jsondata.get("due_in_date").getAsString();
		this.invoice_currency = jsondata.get("invoice_currency").getAsString();
		this.document_type = jsondata.get("document_type").getAsString();
		this.posting_id = jsondata.get("posting_id").getAsString();
		this.total_open_amount = jsondata.get("total_open_amount").getAsString();
		this.baseline_create_date = jsondata.get("baseline_create_date").getAsString();
		this.cust_payment_terms = jsondata.get("cust_payment_terms").getAsString();
		this.invoice_id = jsondata.get("invoice_id").getAsString();
		this.document_create_date1 = this.document_create_date;
		this.area_business = "";

		try {
			int maxValue = 0;
			String maxQuery = "SELECT MAX(sl_no) AS max FROM hrcdatabase.winter_internship";
			this.statement = connection.createStatement();
			rs = this.statement.executeQuery(maxQuery);
			while (rs.next()) {
				maxValue = rs.getInt("max");
			}
			this.sl_no = Integer.toString(maxValue + 1);
			String postQuery = "INSERT INTO hrcdatabase.winter_internship(sl_no,business_code,cust_number,clear_date,"
					+ "buisness_year,doc_id,posting_date,document_create_date,document_create_date1,due_in_date,"
					+ "invoice_currency,document_type,posting_id,area_business,total_open_amount,baseline_create_date,"
					+ "cust_payment_terms,invoice_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			this.preparedstatement = connection.prepareStatement(postQuery);
			this.preparedstatement.setString(1, this.sl_no);
			this.preparedstatement.setString(2, this.business_code);
			this.preparedstatement.setString(3, this.cust_number);
			this.preparedstatement.setString(4, this.clear_date);
			this.preparedstatement.setString(5, this.business_year);
			this.preparedstatement.setString(6, this.doc_id);
			this.preparedstatement.setString(7, this.posting_date);
			this.preparedstatement.setString(8, this.document_create_date);
			this.preparedstatement.setString(9, this.document_create_date1);
			this.preparedstatement.setString(10, this.due_in_date);
			this.preparedstatement.setString(11, this.invoice_currency);
			this.preparedstatement.setString(12, this.document_type);
			this.preparedstatement.setString(13, this.posting_id);
			this.preparedstatement.setString(14, this.area_business);
			this.preparedstatement.setString(15, this.total_open_amount);
			this.preparedstatement.setString(16, this.baseline_create_date);
			this.preparedstatement.setString(17, this.cust_payment_terms);
			this.preparedstatement.setString(18, this.invoice_id);
			this.preparedstatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.preparedstatement.close();
		}

	}

	protected void doPost(HttpServletRequest serverrequest, HttpServletResponse serverresponse)
			throws ServletException, IOException {
//		getting body from the server request in post method 
		JsonObject data = new Gson().fromJson(serverrequest.getReader(), JsonObject.class);
		try {
			this.addData(data);
			serverresponse.setStatus(HttpServletResponse.SC_OK);
		} catch (SQLException e1) {
			serverresponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Not Found");
			e1.printStackTrace();
		}finally {
			serverresponse.getWriter().flush();
		}
	}
}
