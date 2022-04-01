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
import com.google.gson.JsonObject;
import sudeep.servlet.hrcproject.database.DatabaseConnection;

@WebServlet("/CheckInvoiceidAndDocId")
public class CheckInvoiceidAndDocId extends HttpServlet {
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

	private Map<String, Integer> checkData(JsonObject jsonData) throws SQLException {
		String doc_id = jsonData.get("doc_id").toString();
		String invoice_id = jsonData.get("invoice_id").toString();
		Map<String, Integer> responseMap = new HashMap<String, Integer>();
		try {
			String query = "SELECT COUNT(*) AS count FROM hrcdatabase.winter_internship WHERE doc_id=" + doc_id
					+ " OR invoice_id=" + invoice_id;
			this.statement = connection.createStatement();
			this.rs = this.statement.executeQuery(query);
			while (rs.next()) {
				if (rs.getInt("count") > 0) {
					responseMap.put("isInvoiceOrDocExist", 1);
				} else {
					responseMap.put("isInvoiceOrDocExist", 0);
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
		JsonObject data = new Gson().fromJson(serverrequest.getReader(), JsonObject.class);
		Map<String, Integer> response = new HashMap<String, Integer>();
		try {
			response = this.checkData(data);
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
