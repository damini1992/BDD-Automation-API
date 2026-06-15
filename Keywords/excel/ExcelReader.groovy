package excel

import org.apache.poi.ss.usermodel.*

class ExcelReader {

	static String getQuery(String queryName) {

		Sheet sheet =
				ExcelManager.getSheet("Sheet1")

		if (sheet == null) {
			throw new Exception("Sheet1 not found in Excel")
		}

		Row headerRow = sheet.getRow(0)

		if (headerRow == null) {
			throw new Exception("Header row is empty in Excel")
		}

		int lastCol = headerRow.getLastCellNum()

		int queryNameCol = -1
		int queryCol = -1

		for (int i = 0; i < lastCol; i++) {

			String header =
					headerRow.getCell(i)?.toString()?.trim()

			if (header == null) continue

			if (header.equalsIgnoreCase("QueryName")) {
				queryNameCol = i
			}

			if (header.equalsIgnoreCase("Query")) {
				queryCol = i
			}
		}

		if (queryNameCol == -1 || queryCol == -1) {
			throw new Exception("Invalid Excel headers")
		}

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {

			Row row = sheet.getRow(i)

			if (row == null) continue

			String key =
					row.getCell(queryNameCol)?.toString()?.trim()

			String value =
					row.getCell(queryCol)?.toString()?.trim()

			if (key == null || value == null) continue

			if (key.equalsIgnoreCase(queryName.trim())) {

				println("Query Found => " + value)

				return value
			}
		}

		throw new Exception(
				"Query not found in Excel: " + queryName)
	}
}