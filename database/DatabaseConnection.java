package sudeep.servlet.hrcproject.database;
public class DatabaseConnection {
	private static final String DB_URL="jdbc:mysql://localhost:3306/hrcdatabase";
	private static final String PASSWORD="Sudeepkr@1999";
	private static final String USER="root";
	private static final String DRIVER="com.mysql.cj.jdbc.Driver";
	
	public static String getDriver() {
		return DRIVER;
	}
	public static String getDbUrl() {
		return DB_URL;
	}
	public static String getPassword() {
		return PASSWORD;
	}
	public static String getUser() {
		return USER;
	}
}
