package sudeep.servlet.hrcproject.webroute;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

@WebServlet("/EditInvoice")
public class EditInvoice extends HttpServlet {
	private static Connection connection;
	private Statement statement;
	private static final long serialVersionUID = 1L;
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

	private void editData(JsonObject jsonData) throws SQLException {
		String sl_no = jsonData.get("sl_no").toString();
		String new_invoice_currency = jsonData.get("new_invoice_currency").toString();
		String new_cust_payment_terms = jsonData.get("new_cust_payment_terms").toString();
		try {
			this.statement = connection.createStatement();
			String updateQuery = "UPDATE hrcdatabase.winter_internship SET hrcdatabase.winter_internship.invoice_currency="
					+ new_invoice_currency + "," + " hrcdatabase.winter_internship.cust_payment_terms="
					+ new_cust_payment_terms + "WHERE sl_no=" + sl_no;
			this.statement.executeUpdate(updateQuery);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.statement.close();
		}

	}

	@Override
	protected void doPost(HttpServletRequest serverrequest, HttpServletResponse serverresponse)
			throws ServletException, IOException {
		JsonObject data = new Gson().fromJson(serverrequest.getReader(), JsonObject.class);
		try {
			this.editData(data);
			serverresponse.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e1) {
			serverresponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Not Found");
			e1.printStackTrace();
		} finally {
			serverresponse.getWriter().flush();
		}
	}
}
