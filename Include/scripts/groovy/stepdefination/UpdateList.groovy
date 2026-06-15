package stepdefination

import com.kms.katalon.core.configuration.RunConfiguration

import db.DatabaseUtils
import excel.ExcelUtils
import internal.GlobalVariable
import io.cucumber.java.en.*

public class UpdateList {
	
	@Given("I fetch data from table {string} with columns {string} and filter {string}={string}")
//	public void i_fetch_data_from_table_with_columns_and_filter(String string, String string2, String string3, String string4) {
		def fetchData(String tableName,
                  String columns,
                  String filterColumn,
                  String filterValue) {

        List<String> columnList = columns.split(',')*.trim()

        Map<String, Object> filters = [
            (filterColumn): filterValue
        ]

        List<Map<String, Object>> result =
                DatabaseUtils.getDataa(tableName, columnList, filters)

        println(result)

        String excelPath = RunConfiguration.getProjectDir() +
                "/Data Files/write.xlsx"

        ExcelUtils.writeDataToSheet(
                excelPath,
                GlobalVariable.EXCEL_SHEET,
                result
        )
    }

}