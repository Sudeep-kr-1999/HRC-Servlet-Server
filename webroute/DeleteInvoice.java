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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import sudeep.servlet.hrcproject.database.DatabaseConnection;

@WebServlet("/DeleteInvoice")
public class DeleteInvoice extends HttpServlet {
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

	private void deleteData(JsonObject data) throws SQLException {
		JsonArray deletearray = data.getAsJsonArray("delete_list");
		try {
			this.statement = connection.createStatement();
			String deleteQuery = "DELETE FROM hrcdatabase.winter_internship WHERE sl_no IN (";
			String appendQuery="";
			if (deletearray != null) {
				for (int i = 0; i < deletearray.size(); i++) {
					appendQuery=appendQuery+ deletearray.get(i).toString()+",";
				}
				appendQuery=appendQuery.substring(0,appendQuery.length()-1);
				appendQuery=appendQuery+")";
				String finalQuery=deleteQuery+appendQuery;
				this.statement.executeUpdate(finalQuery);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.statement.close();
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest serverrequest, HttpServletResponse serverresponse)
			throws ServletException, IOException {
		JsonObject data = new Gson().fromJson(serverrequest.getReader(), JsonObject.class);
		try {
			this.deleteData(data);
			serverresponse.setStatus(HttpServletResponse.SC_OK);
		} catch (SQLException e) {
			serverresponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}finally {
			serverresponse.getWriter().flush();
		}
	}
}
