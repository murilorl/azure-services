#Spring Cloud Function in Azure

## Dependencies
* Java 1.8
* Maven 3+
* Azure Functions Core Tools 3+
* Azure subscription and Azure CLI (should you want to deploy in Azure)

## Run the Function locally

Before you deploy your application to Azure Function, let's first test it locally.

First you need to package your application into a Jar file:

```shell
$ mvn clean package -Dspring.profiles.active=prod
```

Now that the application is packaged, you can run it using the azure-functions Maven plugin:


```shell
$ mvn azure-functions:run
```

The Azure Function should now be available on your localhost, using port 7071. You can test the function by sending it a POST request, with a User object in JSON format. For example, using cURL:

```shell
curl http://localhost:7071/api/users -d "{\"name\":\"MyName\"}"
```
or

```shell
curl http://localhost:7071/api/users?name?name=MyName
```

### Debug the Function locally
If you need to debug you Azure function locally, provide the argument `-DenableDebug` to the `mvn azure-functions:run` command. If you need get even more verbosity you can also provide the argument `-X`. The entire command is `mvn azure-functions:run -DenableDebug [-X]`.
By default it will start the app on it's normal ports, 7071 for HTTP requests and 5005 for debug. Once it's listening on 5005, you can use your prefered IDE to attach a remote debug to this port.

Note: in my environment (Java Spring Boot on Windows) I get the following error when I start the function app with debug enabled:
 
```
Azure Functions Core Tools (3.0.2245 Commit hash: 1d094e2f3ef79b9a478a1621ea7ec3f93ac1910d)
Function Runtime Version: 3.0.13139.0
Can't determine project language from files. Please use one of [--csharp, --javascript, --typescript, --java, --python, --powershell]
Can't determine project language from files. Please use one of [--csharp, --javascript, --typescript, --java, --python, --powershell]
String cannot be of zero length. (Parameter 'variable')
[ERROR]
[ERROR] Failed to run Azure Functions. Please checkout console output.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
```

To start the function app with debug enabled I had to change to the staging directory () and run the following command:

```
func host start --language-worker -- "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005" --java
```

## Deploy the Function to Azure Functions

Now you are going to publish the Azure Function to production. Remember that the `<functionAppName>`, `<functionAppRegion>`, `<functionResourceGroup>`, and `<appServicePlanName>` properties you have defined in your pom.xml will be used to configure your function.

Run Maven to deploy your function automatically:

```shell
mvn clean package -Dspring.profiles.active=prod
$ mvn azure-functions:deploy
```

If the Maven deploy errors out with a message `Failed to execute goal com.microsoft.azure:azure-functions-maven-plugin:1.4.1:deploy (default-cli) on project azfunc-spring-app: Cannot auth to azure: No available subscription found in current account.`, you can try the following alternative:
change directory to where Maven has generated the function. For example `~my-project\target\azure-functions\my-func-app` and run the following command:

```shell
$ cd ../target/azure-functions/<my-func-app>
$ func azure functionapp publish <my-func-app> [--java]
```
