import com.github.seratch.yahooapis.setsuden.SetsudenYahooApiClient
import com.github.seratch.yahooapis.setsuden.fields.Area
import com.github.seratch.yahooapis.setsuden.request.ElectricPowerForecastRequestParameters
import com.github.seratch.yahooapis.setsuden.request.ElectricPowerUsageRequestParameters

def props = new Properties()
props.load(this.getClass().getClassLoader().getResourceAsStream("yahoo-developer.properties"))
def applicationId = props.getProperty("applicationId")

def client = new SetsudenYahooApiClient(applicationId)

// 最新の電力使用状況を取得する
def recent = client.getLatestPowerUsage()

// 日時などを指定して電力使用状況を取得する
def params = new ElectricPowerUsageRequestParameters(Area.tokyo, "2011032901")
def response = client.getLatestPowerUsage(params)

println response.statusCode
response.headers.each {
    print it.key + "->"
    it.value.each {
        print it + ","
    }
    println ""
}
println response.electricPowerUsage.toString()

// 最新の電気予報を10件取得する
def recentForecasts = client.getElectricPowerForecasts()

// 日時などを指定して電力予報を取得する
def params4Forecasts = new ElectricPowerForecastRequestParameters(Area.tokyo, "20110427")
def responseForecasts = client.getElectricPowerForecasts(params4Forecasts)

println responseForecasts.statusCode
responseForecasts.headers.each {
    print it.key + "->"
    it.value.each {
        print it + ","
    }
    println ""
}
for (def each: responseForecasts.electricPowerForcasts)
    println each.toString()

