package sudeep.servlet.hrcproject.webroute;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import sudeep.servlet.hrcproject.database.DatabaseConnection;
import sudeep.servlet.hrcproject.model.InvoiceUI;

@WebServlet("/GetSearchParameters")
public class GetSearchParameters extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection connection;
	private Statement statement;
	private ResultSet rs;
	private static final String DB_URL = DatabaseConnection.getDbUrl();
	private static final String PASSWORD = DatabaseConnection.getPassword();
	private static final String USER = DatabaseConnection.getUser();
	private static final String DRIVER = DatabaseConnection.getDriver();

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

	private ArrayList<Map<String, String>> getSearchedData(String docid, String invoiceId, String customerNumber,
			String businessYear) throws SQLException {
		String customer_id = customerNumber;
		String doc_id = docid;
		String invoice_id = invoiceId;
		String business_year = businessYear;
		ArrayList<Map<String, String>> invoiceList = new ArrayList<Map<String, String>>();
		try {
			String queryString = "SELECT hrcdatabase.winter_internship.sl_no,"
					+ "	hrcdatabase.winter_internship.business_code," + "hrcdatabase.winter_internship.cust_number,"
					+ " hrcdatabase.winter_internship.clear_date," + " hrcdatabase.winter_internship.buisness_year,"
					+ " hrcdatabase.winter_internship.doc_id," + " hrcdatabase.winter_internship.posting_date,"
					+ " hrcdatabase.winter_internship.document_create_date,"
					+ " hrcdatabase.winter_internship.due_in_date," + " hrcdatabase.winter_internship.invoice_currency,"
					+ " hrcdatabase.winter_internship.document_type," + " hrcdatabase.winter_internship.posting_id,"
					+ " hrcdatabase.winter_internship.total_open_amount,"
					+ " hrcdatabase.winter_internship.baseline_create_date,"
					+ " hrcdatabase.winter_internship.cust_payment_terms,"
					+ "hrcdatabase.winter_internship.aging_bucket,"
					+ " hrcdatabase.winter_internship.invoice_id FROM hrcdatabase.winter_internship WHERE ("
					+ "hrcdatabase.winter_internship.cust_number=" + customer_id + "AND "
					+ "hrcdatabase.winter_internship.doc_id=" + doc_id + "AND "
					+ "hrcdatabase.winter_internship.invoice_id=" + invoice_id + "AND "
					+ " hrcdatabase.winter_internship.buisness_year=" + business_year + ")" + "ORDER BY sl_no";
			this.statement = connection.createStatement();
			this.rs = this.statement.executeQuery(queryString);
			while (rs.next()) {
				InvoiceUI modelrow = new InvoiceUI();
				modelrow.setSlNumber(rs.getString("sl_no"));
				modelrow.setBusinessCode(rs.getString("business_code"));
				modelrow.setCustomerNumber(rs.getString("cust_number"));
				modelrow.setClearDate(rs.getString("clear_date"));
				modelrow.setBusinessYear(Integer.toString(rs.getInt("buisness_year")));
				modelrow.setDocID(rs.getString("doc_id"));
				modelrow.setPostingDate(rs.getString("posting_date"));
				modelrow.setDocumentCreateDate(rs.getString("document_create_date"));
				modelrow.setDueInDate(rs.getString("due_in_date"));
				modelrow.setInvoiceCurrency(rs.getString("invoice_currency"));
				modelrow.setDocumentType(rs.getString("document_type"));
				modelrow.setPostingID(rs.getString("posting_id"));
				modelrow.setTotalOpenAmount(rs.getString("total_open_amount"));
				modelrow.setBaselineCreateDate(rs.getString("baseline_create_date"));
				modelrow.setCustomerPaymentTerms(rs.getString("cust_payment_terms"));
				modelrow.setInvoiceID(rs.getString("invoice_id"));
				modelrow.setAgingBucket(rs.getString("aging_bucket") == null ? "" : rs.getString("aging_bucket"));
				invoiceList.add(modelrow.invoiceListMap());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.statement.close();
		}
		return invoiceList;

	}

	protected void doGet(HttpServletRequest serverrequest, HttpServletResponse serverresponse)
			throws ServletException, IOException {
		ArrayList<Map<String, String>> response = new ArrayList<Map<String, String>>();
		try {
			String doc_id = serverrequest.getParameter("doc_id");
			String invoice_id = serverrequest.getParameter("invoice_id");
			String customer_number = serverrequest.getParameter("customer_number");
			String business_year = serverrequest.getParameter("business_year");

			response = this.getSearchedData(doc_id, invoice_id, customer_number, business_year);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			serverresponse.setContentType("application/json");
			serverresponse.setCharacterEncoding("UTF-8");
			Gson gson = new Gson();
			String resData = gson.toJson(response);
			serverresponse.getWriter().print(resData);
			serverresponse.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			serverresponse.sendError(HttpServletResponse.SC_NOT_FOUND, "Not Found");
		} finally {
			serverresponse.getWriter().flush();
		}

	}

}
