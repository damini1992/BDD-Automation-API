package dbutils

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException

import internal.GlobalVariable as GV
public class DatabaseManager {
	
	
	/**
	 * ✅ Execute UPDATE / INSERT / DELETE query
	 */
	static int executeUpdate(String query) {
		Connection conn = DataBaseConnection.getConnection(GV.DB_NAME, query, GV.DB_USER, GV.DB_PASSWORD)
		PreparedStatement stmt = null
		int rowsAffected = 0

		try {
			stmt = conn.prepareStatement(query)
			rowsAffected = stmt.executeUpdate()
			println("✅ Query executed successfully. Rows affected: " + rowsAffected)

		} catch (SQLException e) {
			println("❌ Error executing query: " + e.getMessage())

		} finally {
			stmt?.close()
		}

		return rowsAffected
	}

	/**
	 * ✅ Execute parameterized UPDATE/DELETE (Recommended)
	 */
	static int executeUpdate(String query, List params) {
		Connection conn = DataBaseConnection.getDataBaseConnection(query, query, query, query)
		PreparedStatement stmt = null
		int rowsAffected = 0

		try {
			stmt = conn.prepareStatement(query)

			// ✅ Bind parameters
			for (int i = 0; i < params.size(); i++) {
				stmt.setObject(i + 1, params[i])
			}

			rowsAffected = stmt.executeUpdate()
			println("✅ Parameterized query executed. Rows affected: " + rowsAffected)

		} catch (SQLException e) {
			println("❌ Error executing parameterized query: " + e.getMessage())

		} finally {
			stmt?.close()
		}

		return rowsAffected
	}

	

}
