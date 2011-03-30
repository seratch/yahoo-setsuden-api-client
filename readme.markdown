# Yahoo!デベロッパーネットワーク 電力使用状況API クライアント（Java/Scala）

Yahoo!デベロッパーネットワーク 電力使用状況APIのJavaとScalaでのクライアント実装です。

http://developer.yahoo.co.jp/webapi/shinsai/setsuden/v1/latestpowerusage.html

## インストール方法

### ダウンロード

    ./download/yahoo-setsuden-api-client-1.0.jar

### Maven2経由

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

    <dependency>
      <groupId>com.github.seratch</groupId>
      <artifactId>yahoo-setsuden-api-client</artifactId>
      <version>1.0</version>
    </dependency>

## 実装サンプル

### Java版

    String applicationId = "...";
    SetsudenYahooApiClient client = new SetsudenYahooApiClient(applicationId);

    // 最新の電力使用状況を取得する
    ElectricPowerUsageResponse recent = client.getLatestPowerUsage();

    // 日時などを指定して電力使用状況を取得する
    RequestParameters params = new RequestParameters(Area.tokyo, "2011032901");
    ElectricPowerUsageResponse response = client.getLatestPowerUsage(params);

    System.out.println(response.getElectricPowerUsage());
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

