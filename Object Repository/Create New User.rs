<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Create New User</name>
   <tag></tag>
   <elementGuidId>6c395033-0d98-46f3-a9ec-52a594f14c50</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <smartLocatorEnabled>false</smartLocatorEnabled>
   <useRalativeImagePath>false</useRalativeImagePath>
   <autoUpdateContent>true</autoUpdateContent>
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>true</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;contentType&quot;: &quot;application/x-www-form-urlencoded&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;,
  &quot;parameters&quot;: [
    {
      &quot;name&quot;: &quot;name&quot;,
      &quot;value&quot;: &quot;${rv_name}&quot;
    },
    {
      &quot;name&quot;: &quot;email&quot;,
      &quot;value&quot;: &quot;${rv_email}&quot;
    },
    {
      &quot;name&quot;: &quot;password&quot;,
      &quot;value&quot;: &quot;${rv_password}&quot;
    },
    {
      &quot;name&quot;: &quot;title&quot;,
      &quot;value&quot;: &quot;${rv_title}&quot;
    },
    {
      &quot;name&quot;: &quot;birth_date&quot;,
      &quot;value&quot;: &quot;${rv_birth_date}&quot;
    },
    {
      &quot;name&quot;: &quot;birth_month&quot;,
      &quot;value&quot;: &quot;${rv_birth_month}&quot;
    },
    {
      &quot;name&quot;: &quot;birth_year&quot;,
      &quot;value&quot;: &quot;${rv_birth_year}&quot;
    },
    {
      &quot;name&quot;: &quot;firstname&quot;,
      &quot;value&quot;: &quot;${rv_firstname}&quot;
    },
    {
      &quot;name&quot;: &quot;lastname&quot;,
      &quot;value&quot;: &quot;${rv_lastname}&quot;
    },
    {
      &quot;name&quot;: &quot;company&quot;,
      &quot;value&quot;: &quot;${rv_company}&quot;
    },
    {
      &quot;name&quot;: &quot;address1&quot;,
      &quot;value&quot;: &quot;${rv_address1}&quot;
    },
    {
      &quot;name&quot;: &quot;address2&quot;,
      &quot;value&quot;: &quot;${rv_address2}&quot;
    },
    {
      &quot;name&quot;: &quot;country&quot;,
      &quot;value&quot;: &quot;${rv_country}&quot;
    },
    {
      &quot;name&quot;: &quot;zipcode&quot;,
      &quot;value&quot;: &quot;${rv_zipcode}&quot;
    },
    {
      &quot;name&quot;: &quot;state&quot;,
      &quot;value&quot;: &quot;${rv_state}&quot;
    },
    {
      &quot;name&quot;: &quot;city&quot;,
      &quot;value&quot;: &quot;${rv_city}&quot;
    },
    {
      &quot;name&quot;: &quot;mobile_number&quot;,
      &quot;value&quot;: &quot;${rv_mobile_number}&quot;
    }
  ]
}</httpBodyContent>
   <httpBodyType>x-www-form-urlencoded</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/x-www-form-urlencoded</value>
      <webElementGuid>12b0db10-92e8-4178-ba9f-2cb818edfe03</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>11.1.3</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <path></path>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.gv_BaseUrl}/api/createAccount</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>0</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>ea2abd9c-e61d-45bd-9f39-f193fca11779</id>
      <masked>false</masked>
      <name>rv_name</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>792917f6-5c44-436f-a56f-2da5bc969a68</id>
      <masked>false</masked>
      <name>rv_email</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>ae1ab660-b7e2-41f4-a225-a92f507f425e</id>
      <masked>false</masked>
      <name>rv_password</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>a1201858-317e-4c37-8af3-b93f1c124ac6</id>
      <masked>false</masked>
      <name>rv_title</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>e5aec3e8-ec49-48b7-88c7-bb7417571770</id>
      <masked>false</masked>
      <name>rv_birth_date</name>
   </variables>
   <variables>
      <defaultValue>'10'</defaultValue>
      <description></description>
      <id>577368d8-b776-48a4-b290-16f2708a2cca</id>
      <masked>false</masked>
      <name>rv_birth_month</name>
   </variables>
   <variables>
      <defaultValue>'1998'</defaultValue>
      <description></description>
      <id>2ff298a1-27a0-40c9-8f87-9b0fab623fe6</id>
      <masked>false</masked>
      <name>rv_birth_year</name>
   </variables>
   <variables>
      <defaultValue>'Test 11'</defaultValue>
      <description></description>
      <id>8a42cb49-62dd-4760-a394-56012ad38f66</id>
      <masked>false</masked>
      <name>rv_firstname</name>
   </variables>
   <variables>
      <defaultValue>'Test 12'</defaultValue>
      <description></description>
      <id>7bc43459-ef7b-4ce8-bfe2-8652dec080aa</id>
      <masked>false</masked>
      <name>rv_lastname</name>
   </variables>
   <variables>
      <defaultValue>'TCS'</defaultValue>
      <description></description>
      <id>67004a24-a968-4310-a9a7-0e2396f69c61</id>
      <masked>false</masked>
      <name>rv_company</name>
   </variables>
   <variables>
      <defaultValue>'test'</defaultValue>
      <description></description>
      <id>377fa590-8290-42a6-8084-a559e42edc28</id>
      <masked>false</masked>
      <name>rv_address1</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>7c0494c9-461f-4d46-b5ae-1f15ac8128fc</id>
      <masked>false</masked>
      <name>rv_address2</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>6f79dec1-dab6-444d-adf2-e7547a4700e6</id>
      <masked>false</masked>
      <name>rv_country</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>ff1520b5-c5d1-49ad-bf62-ca4dd7a9f7e2</id>
      <masked>false</masked>
      <name>rv_zipcode</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>add19532-1f00-4ddb-9629-c5c838f13a3b</id>
      <masked>false</masked>
      <name>rv_state</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>5f93a722-e05a-4a5f-afd8-652bf5ebf61f</id>
      <masked>false</masked>
      <name>rv_city</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>da842447-db60-40d2-9bec-0ac9e793842e</id>
      <masked>false</masked>
      <name>rv_mobile_number</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
