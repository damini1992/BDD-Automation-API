package db

import java.sql.*


class DatabaseUtils {

	private static PreparedStatement statement = null
	private static Connection conn = DBConnectionManager.getConnection()
	private static Statement stmt = conn.createStatement()

	static ResultSet executeQuery(String query) {

		println("Executing Query => " + query)
		return stmt.executeQuery(query)
	}

	static int executeUpdate(String query) {

		println("Executing Update => " + query)

		return stmt.executeUpdate(query)
	}

	static List<Map<String, Object>> getData(String query) {

		//        Connection conn = DBConnectionManager.getConnection()

		ResultSet rs = stmt.executeQuery(query)

		List<Map<String, Object>> list = []

		ResultSetMetaData meta = rs.getMetaData()
		int colCount = meta.getColumnCount()

		while (rs.next()) {

			Map<String, Object> row = [:]

			for (int i = 1; i <= colCount; i++) {
				row[meta.getColumnName(i)] = rs.getObject(i)
			}

			list.add(row)
		}

		rs.close()
		stmt.close()

		return list
	}

	static void updateEmployeeName(String oldName, String newName, String query) {

		try {

			// Create prepared statement
			statement = conn.prepareStatement(query)

			// Set values
			statement.setString(1, newName)
			statement.setString(2, oldName)

			// Execute update
			int rowsUpdated = statement.executeUpdate()

			println(rowsUpdated + " record(s) updated successfully.")
		} catch (Exception e) {

			e.printStackTrace()
		} finally {

			// Close connections
			statement?.close()
		}
	}

	static List<Map<String, Object>> getData(String tableName, String... columns) {

		//					ensureConnection()

		List<Map<String, Object>> dataList = []

		Statement stmt = null
		ResultSet rs = null

		try {

			stmt = conn.createStatement()

			String columnQuery = columns ? columns.join(", ") : "*"

			String query = "SELECT ${columnQuery} FROM ${tableName}"

			println("Executing Query : " + query)

			rs = stmt.executeQuery(query)

			ResultSetMetaData metaData = rs.getMetaData()
			int columnCount = metaData.getColumnCount()

			while (rs.next()) {

				Map<String, Object> rowData = [:]

				for (int i = 1; i <= columnCount; i++) {

					String columnName = metaData.getColumnName(i)
					Object value = rs.getObject(i)

					rowData.put(columnName, value)
				}

				dataList.add(rowData)
			}
		} catch (Exception e) {

			e.printStackTrace()
		} finally {

			rs?.close()
			stmt?.close()
		}

		return dataList
	}
	
	
	
	static List<Map<String, Object>> getDataa(
			String tableName,
			List<String> columns = [],
			Map<String, Object> filters = [:]) {

		List<Map<String, Object>> dataList = []

		PreparedStatement pstmt = null
		ResultSet rs = null

		try {

			Connection conn = DBConnectionManager.getConnection()

			String columnQuery = (columns && !columns.isEmpty())
					? columns.join(", ")
					: "*"

			String query = "SELECT ${columnQuery} FROM ${tableName}"

			List<Object> values = []

			if (filters && !filters.isEmpty()) {

				List<String> conditions = []

				filters.each { key, value ->
					if (value != null && value.toString().trim()) {
						conditions.add("${key} = ?")
						values.add(value)
					}
				}

				if (!conditions.isEmpty()) {
					query += " WHERE " + conditions.join(" AND ")
				}
			}

			pstmt = conn.prepareStatement(query)

			values.eachWithIndex { value, index ->
				pstmt.setObject(index + 1, value)
			}

			rs = pstmt.executeQuery()

			ResultSetMetaData metaData = rs.getMetaData()
			int columnCount = metaData.getColumnCount()

			while (rs.next()) {

				Map<String, Object> rowData = [:]

				for (int i = 1; i <= columnCount; i++) {
					rowData.put(metaData.getColumnName(i), rs.getObject(i))
				}

				dataList.add(rowData)
			}

		} catch (Exception e) {
			throw new RuntimeException("DB Query Failed for table: " + tableName, e)

		} finally {
			try { rs?.close() } catch (ignored) {}
			try { pstmt?.close() } catch (ignored) {}
		}

		return dataList
	}
	/*static List<Map<String, Object>> getDataa(
			String tableName,
			List<String> columns = [],
			Map<String, Object> filters = [:]) {

		List<Map<String, Object>> dataList = []

		PreparedStatement pstmt = null
		ResultSet rs = null

		try {

			String columnQuery
			//					String columnQuery = columns ? columns.join(", ") : "*"
			if (columns != null && !columns.isEmpty()) {
				columnQuery = columns.join(", ")
			} else {
				columnQuery = "*"
			}

			StringBuilder query = new StringBuilder()
			 query.append("SELECT ${columnQuery} FROM ${tableName}")

			//					String query = "SELECT ${columnQuery} FROM ${tableName}"
			String query = "SELECT " + columnQuery + " FROM " + tableName

			List<Object> values = []

			// Dynamic WHERE clause
			if (filters && !filters.isEmpty()) {

				List<String> conditions = []

				filters.each { key, value ->

					// Ignore null or empty filter values
					if (value != null && value.toString().trim()) {
						conditions.add("${key} = ?")
						values.add(value)
					}
				}

				if (!conditions.isEmpty()) {
					query +=" WHERE "
					query+=conditions.join(" AND ")
				}
			}


			pstmt = conn.prepareStatement(query.toString())

			// Set PreparedStatement values
			values.eachWithIndex { value, index ->
				pstmt.setObject(index + 1, value)
			}

			rs = pstmt.executeQuery()

			ResultSetMetaData metaData = rs.getMetaData()
			int columnCount = metaData.getColumnCount()

			while (rs.next()) {

				Map<String, Object> rowData = [:]

				for (int i = 1; i <= columnCount; i++) {

					String columnName = metaData.getColumnName(i)
					Object value = rs.getObject(i)

					rowData.put(columnName, value)
				}

				dataList.add(rowData)
			}
		} catch (Exception e) {

			println("Error while fetching data from table: ${tableName}")
			e.printStackTrace()
		} finally {

			rs?.close()
			pstmt?.close()
		}

		return dataList
	}*/

	static void closeConnection() {
		DBConnectionManager.closeConnection()
	}
}