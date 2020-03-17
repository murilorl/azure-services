#Spring Cloud Function in Azure

## Run the Function locally

Before you deploy your application to Azure Function, let's first test it locally.

First you need to package your application into a Jar file:

```shell
$ mvn package
```

Now that the application is packaged, you can run it using the azure-functions Maven plugin:


```shell
$ mvn azure-functions:run
```

The Azure Function should now be available on your localhost, using port 7071. You can test the function by sending it a POST request, with a User object in JSON format. For example, using cURL:

```shell
curl http://localhost:7071/api/HttpExample -d "{\"name\":\"Azure\"}"
```
or

```shell
curl http://localhost:7071/api/HttpExample?name=MyName
```

## Deploy the Function to Azure Functions

Now you are going to publish the Azure Function to production. Remember that the `<functionAppName>`, `<functionAppRegion>` and `<functionResourceGroup>` properties you have defined in your pom.xml will be used to configure your function.

Run Maven to deploy your function automatically:

```shell
$ mvn azure-functions:deploy
```

If the Maven deploy errors out with a message `Failed to execute goal com.microsoft.azure:azure-functions-maven-plugin:1.4.1:deploy (default-cli) on project azfunc-spring-app: Cannot auth to azure: No available subscription found in current account.`, you can try the following alternative:
change directory to where Maven has generated the function. For example `~my-project\target\azure-functions\my-func-app` and run the following command:

```shell
$ func azure functionapp publish my-func-app
```
