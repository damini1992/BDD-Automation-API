package db

import excel.ExcelManager
import common.ConfigManager

class QueryManager {

    static String getQuery(String key) {
        return ExcelManager.getQuery(key)
    }
}