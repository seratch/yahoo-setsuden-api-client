# Yahoo!デベロッパーネットワーク 節電情報 API クライアント（Java/Scala）

Yahoo!デベロッパーネットワーク 震災関連情報Web APIのJava/Scalaのクライアント実装です。

http://developer.yahoo.co.jp/webapi/shinsai/

以下のAPIに対応しています。

・電力使用状況API

・電気予報API

## インストール方法

### ダウンロード

    ./download/yahoo-setsuden-api-client-1.1.jar

### Maven

    <repositories>
      ...
      <repository>
        <id>yahoo-setsuden-api-client-releases</id>
        <url>https://github.com/seratch/yahoo-setsuden-api-client/raw/master/mvn-repo/releases</url>
      </repository>
      <repository>
        <id>yahoo-setsuden-api-client-snapshots</id>
        <url>https://github.com/seratch/yahoo-setsuden-api-client/raw/master/mvn-repo/snapshots</url>
      </repository>
      ...
    </repositories>

    <dependencies>
      ...
      <dependency>
        <groupId>com.github.seratch</groupId>
        <artifactId>yahoo-setsuden-api-client</artifactId>
        <version>1.1</version>
      </dependency>
      ...
    </dependencies>

## 実装サンプル(電力使用状況API)

### Java版

    String applicationId = "...";
    SetsudenYahooApiClient client = new SetsudenYahooApiClient(applicationId);

    // 最新の電力使用状況を取得する
    ElectricPowerUsageResponse recent = client.getLatestPowerUsage();

    // 日時などを指定して電力使用状況を取得する
    ElectricPowerUsageRequestParameters params = new ElectricPowerUsageRequestParameters(Area.tokyo, "2011032901");
    ElectricPowerUsageResponse response = client.getLatestPowerUsage(params);

    System.out.println(response.getElectricPowerUsage());
    // ElectricPowerUsage [area:tokyo,usageKilowattPerHour:27300000,capacityKilowattPerHour:38500000,date:2011-03-29,hour:1]

### Groovy版

    def applicationId = "..."
    def client = new SetsudenYahooApiClient(applicationId)

    // 最新の電力使用状況を取得する
    def recent  = client.getLatestPowerUsage()

    // 日時などを指定して電力使用状況を取得する
    def params = new ElectricPowerUsageRequestParameters(Area.tokyo, "2011032901")
    def response = client.getLatestPowerUsage(params)

    println response.electricPowerUsage.toString()
    // ElectricPowerUsage [area:tokyo,usageKilowattPerHour:27300000,capacityKilowattPerHour:38500000,date:2011-03-29,hour:1]

### Scala版

    val applicationId = "..."
    val client = new SetsudenYahooApiClient(applicationId)

    // 最新の電力使用状況を取得する
    val recent = client.getLatestPowerUsage();

    // 日時などを指定して電力使用状況を取得する
    val response = client.getLatestPowerUsage(RequestParameters(yyyymmddhh = "2011032901"))

    println(response.electricPowerUsage)
    // ElectricPowerUsage [area:tokyo,usageKilowattPerHour:27300000,capacityKilowattPerHour:38500000,date:2011-03-29,hour:1]

## 実装サンプル(電気予報API)

### Java版

    String applicationId = "...";
    SetsudenYahooApiClient client = new SetsudenYahooApiClient(applicationId);

    // 最新の電気予報を10件取得する
    ElectricPowerForecastResponse rencent = client.getElectricPowerForecasts();

    // 日時などを指定して電力予報を取得する
    ElectricPowerForecastRequestParameters params = new ElectricPowerForecastRequestParameters(Area.tokyo, "20110429");
    ElectricPowerForecastResponse response = client.getElectricPowerForecasts(params);

    for (ElectricPowerForecast forecast : response.getElectricPowerForcasts()) {
        System.out.println(forecast.toString());
    }
    // ElectricPowerForecast [area:tokyo,usageKilowattPerHour:32200000,capacityKilowattPerHour:40000000,date:2011-04-27,hour:17]
    // ElectricPowerForecast [area:tokyo,usageKilowattPerHour:33750000,capacityKilowattPerHour:40000000,date:2011-04-27,hour:18]
    // ElectricPowerForecast [area:tokyo,usageKilowattPerHour:33780000,capacityKilowattPerHour:40000000,date:2011-04-27,hour:19]
    // ...

### Groovy版

    def applicationId = "..."
    def client = new SetsudenYahooApiClient(applicationId)

    // 最新の電気予報を10件取得する
    def recent = client.getElectricPowerForecasts()

    // 日時などを指定して電力予報を取得する
    def params = new ElectricPowerForecastRequestParameters(Area.tokyo, "20110429")
    def responseForecasts = client.getElectricPowerForecasts(params)

    for (def each: responseForecasts.electricPowerForcasts) {
        println each.toString()
    }
    // ElectricPowerForecast [area:tokyo,usageKilowattPerHour:32200000,capacityKilowattPerHour:40000000,date:2011-04-27,hour:17]
    // ElectricPowerForecast [area:tokyo,usageKilowattPerHour:33750000,capacityKilowattPerHour:40000000,date:2011-04-27,hour:18]
    // ElectricPowerForecast [area:tokyo,usageKilowattPerHour:33780000,capacityKilowattPerHour:40000000,date:2011-04-27,hour:19]
    // ...

### Scala版

    val applicationId = "..."
    val client = new SetsudenYahooApiClient(applicationId)

    // 最新の電気予報を10件取得する
    val recent = client.getElectricPowerForecasts();

    // 日時などを指定して電力予報を取得する
    val response = client.getElectricPowerForecasts(ElectricPowerForecastResponse(yyyymmdd = "20110429"))

    response.electricPowerForecasts foreach { each =>
        println(each.toString)
    }
    // ElectricPowerForecast [area:tokyo,usageKilowattPerHour:32200000,capacityKilowattPerHour:40000000,date:2011-04-27,hour:17]
    // ElectricPowerForecast [area:tokyo,usageKilowattPerHour:33750000,capacityKilowattPerHour:40000000,date:2011-04-27,hour:18]
    // ElectricPowerForecast [area:tokyo,usageKilowattPerHour:33780000,capacityKilowattPerHour:40000000,date:2011-04-27,hour:19]
    // ...

