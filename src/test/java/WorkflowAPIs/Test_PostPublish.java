package WorkflowAPIs;

import TestBase.BaseClass;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test_PostPublish extends BaseClass {
    public String baseUrl;
    public String basePath;
    public String url;
    public String workflowbase;
    public String workflowUrl;
    public String apikey;
    public String apisecret;

    Post_SubmitPublishReject publish;
    GetWorkflows GetWorkflow;

    @BeforeTest
    public void setupUrl()
    {
        baseUrl=prop.getProperty("BaseURL");
        basePath=prop.getProperty("PostPublishBasePath");
        workflowbase=prop.getProperty("GetWorkflowBasePath");
        apikey=prop.getProperty("Q4web_apikey");
        apisecret=prop.getProperty("Q4web_apisecret");
        url=baseUrl+basePath;
        workflowUrl=baseUrl+workflowbase;

        publish=new Post_SubmitPublishReject();
        GetWorkflow=new GetWorkflows();
    }

    @Test
    public void TestResponse()
    {
        Response resp_work=GetWorkflow.GetResponse(workflowUrl,16,apikey,apisecret);
        JSONObject input=publish.CreateBody(resp_work,"Publishing workflow");

        Response resp=publish.GetResponse(url,input,apikey,apisecret);
        //resp.prettyPrint();
        Assert.assertEquals((int)resp.body().jsonPath().get("data.succeeded"),1);

    }


}
