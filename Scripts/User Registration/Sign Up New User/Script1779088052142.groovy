import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonOutput as JsonOutput
import stepdefination.Hooks as Hooks

RequestObject requestObject = findTestObject('Object Repository/Create New User')

String endpoint = requestObject.getRestUrl()

// For REST requests)
Hooks.scenario.write('API Endpoint: ')

Hooks.scenario.write(endpoint)

Map payload = [('rv_name') : mv_name, ('rv_email') : mv_email, ('rv_password') : mv_password, ('rv_title') : mv_title, ('rv_birth_date') : mv_birth_date
    , ('rv_birth_month') : mv_birth_month, ('rv_birth_year') : mv_birth_year, ('rv_firstname') : mv_firstname, ('rv_lastname') : mv_lastname
    , ('rv_company') : mv_company, ('rv_address1') : mv_address1, ('rv_address2') : mv_address2, ('rv_country') : mv_country
    , ('rv_zipcode') : mv_zipcode, ('rv_state') : mv_state, ('rv_city') : mv_city, ('rv_mobile_number') : mv_mobile_number]

def jsonPretty = JsonOutput.prettyPrint(JsonOutput.toJson(payload))

Hooks.scenario.write('REQUEST')

Hooks.scenario.write(jsonPretty)

def response = WS.sendRequest(findTestObject('Create New User', [('rv_name') : mv_name, ('rv_email') : mv_email, ('rv_password') : mv_password
            , ('rv_title') : mv_title, ('rv_birth_date') : mv_birth_date, ('rv_birth_month') : mv_birth_month, ('rv_birth_year') : mv_birth_year
            , ('rv_firstname') : mv_firstname, ('rv_lastname') : mv_lastname, ('rv_company') : mv_company, ('rv_address1') : mv_address1
            , ('rv_address2') : mv_address2, ('rv_country') : mv_country, ('rv_zipcode') : mv_zipcode, ('rv_state') : mv_state
            , ('rv_city') : mv_city, ('rv_mobile_number') : mv_mobile_number]))

