import com.github.seratch.yahooapis.setsuden.SetsudenYahooApiClient
import com.github.seratch.yahooapis.setsuden.request.RequestParameters
import com.github.seratch.yahooapis.setsuden.fields.Area

def props = new Properties()
props.load(this.getClass().getClassLoader().getResourceAsStream("yahoo-developer.properties"))
def applicationId = props.getProperty("applicationId")

def client = new SetsudenYahooApiClient(applicationId)

// 最新の電力使用状況を取得する
def recent = client.getLatestPowerUsage()

// 日時などを指定して電力使用状況を取得する
def params = new RequestParameters(Area.tokyo, "2011032901")
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

