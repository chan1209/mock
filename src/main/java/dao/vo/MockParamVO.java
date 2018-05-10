package dao.vo;

/**
 * Created by zejunweizj on 2017/3/22.
 */
public class MockParamVO {

    public int id;

    public String mockServiceName;

    public String mockUrlPath;

    public String mockResponse;

    public String mockParams;

    public String getMockParams() {
        return mockParams;
    }

    public void setMockParams(String mockParams) {
        this.mockParams = mockParams;
    }



    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setMockResponse(String mockResponse) {
        this.mockResponse = mockResponse;
    }

    public void setMockServiceName(String mockServiceName) {
        this.mockServiceName = mockServiceName;
    }


    public void setMockUrlPath(String mockUrlPath) {
        this.mockUrlPath = mockUrlPath;
    }


    public String getMockResponse() {
        return mockResponse;
    }

    public String getMockServiceName() {
        return mockServiceName;
    }

    public String getMockUrlPath() {
        return mockUrlPath;
    }
}
