package dbutils

import java.sql.Connection
import java.sql.DriverManager
import internal.GlobalVariable as GV

public class DataBaseConnection {


	private static Connection connection

	def driver
	def url


	def static getConnection(String driver,String url,String user,String password) {

		if (connection != null && !connection.isClosed()) {
			return connection
		}

		switch (GV.DB_TYPE) {

			case "SQLSERVER":
				driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
				url = "jdbc:sqlserver://${GV.DB_HOST}:${GV.DB_PORT};databaseName=${GV.DB_NAME};encrypt=true;trustServerCertificate=true"
				break

			case "MYSQL":
				driver = "com.mysql.cj.jdbc.Driver"
				url = "jdbc:mysql://${GV.DB_HOST}:${GV.DB_PORT}/${GV.DB_NAME}"
				break

			case "ORACLE":
				driver = "oracle.jdbc.driver.OracleDriver"
				url = "jdbc:oracle:thin:@${GV.DB_HOST}:${GV.DB_PORT}:${GV.DB_NAME}"
				break

			case "POSTGRES":
				driver = "org.postgresql.Driver"
				url = "jdbc:postgresql://${GV.DB_HOST}:${GV.DB_PORT}/${GV.DB_NAME}"
				break

			case "SQLITE":
				driver = "org.sqlite.JDBC"
				url = "jdbc:sqlite:${GV.DB_NAME}"
				break

			default:
				throw new Exception("Unsupported DB_TYPE: " + GV.DB_TYPE)
		}


		Class.forName(driver)
		connection = DriverManager.getConnection(url, GV.DB_USER, GV.DB_PASSWORD)

		println("✅ Connected to ${GV.DB_TYPE} database")

		return connection
	}


	static void closeConnection() {
		if (connection != null && !connection.isClosed()) {
			connection.close()
			println("✅ Connection closed")
		}
	}
}
