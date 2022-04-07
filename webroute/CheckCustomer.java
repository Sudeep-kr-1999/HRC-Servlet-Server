package sudeep.servlet.hrcproject.webroute;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import sudeep.servlet.hrcproject.database.DatabaseConnection;

@WebServlet("/CheckCustomer")
public class CheckCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DB_URL = DatabaseConnection.getDbUrl();
	private static final String PASSWORD = DatabaseConnection.getPassword();
	private static final String USER = DatabaseConnection.getUser();
	private static final String DRIVER = DatabaseConnection.getDriver();
	private static Connection connection;
	private Statement statement;
	private ResultSet rs;

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

	private Map<String, Integer> checkCustomer(String customerNumber) throws SQLException {
		String cust_number = customerNumber;
		Map<String, Integer> responseMap = new HashMap<String, Integer>();
		try {
			String query = "SELECT COUNT(*) AS count FROM hrcdatabase.customer WHERE cust_number=" + cust_number;
			this.statement = connection.createStatement();
			this.rs = this.statement.executeQuery(query);
			while (rs.next()) {
				if (rs.getInt("count") > 0) {
					responseMap.put("isCustomerExists", 1);
				} else {
					responseMap.put("isCustomerExists", 0);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.statement.close();
		}
		return responseMap;
	}

	protected void doGet(HttpServletRequest serverrequest, HttpServletResponse serverresponse)
			throws ServletException, IOException {
		String customer_number = serverrequest.getParameter("cust_number");
		Map<String, Integer> response = new HashMap<String, Integer>();
		try {
			response = this.checkCustomer(customer_number);
			serverresponse.setContentType("application/json");
			serverresponse.setCharacterEncoding("UTF-8");
			Gson gson = new Gson();
			String resData = gson.toJson(response);
			serverresponse.getWriter().print(resData);
			serverresponse.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e1) {
			serverresponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Not Found");
			e1.printStackTrace();
		} finally {
			serverresponse.getWriter().flush();
		}
	}
}
