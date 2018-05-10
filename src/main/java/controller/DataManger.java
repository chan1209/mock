package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.service.MockService;
import dao.vo.MockParamVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zejunweizj on 2017/3/22.
 */

@Controller
@RequestMapping("/dataManger")
public class DataManger {
    // TODO: 2018-04-12 insert接口设定param输入并存入db，若param已存在，提示异常

    @Resource
    public MockService mockService;

    @RequestMapping("insert")
    @ResponseBody
    public Object insert(HttpServletRequest request, HttpServletResponse response) throws Exception{

        Map<String, Object> map = new HashMap<String, Object>();

        String mockServiceName = ServletRequestUtils.getRequiredStringParameter(
                request, "mockServiceName");
        String mockUrlPath = ServletRequestUtils.getRequiredStringParameter(
                request, "mockUrlPath");
        String mockResponse = ServletRequestUtils.getRequiredStringParameter(
                request, "mockResponse");
        String mockParams = ServletRequestUtils.getRequiredStringParameter(
                request, "mockParams");
        JSONObject Params = JSONObject.parseObject(mockParams);

//        int count = mockWebService.getCountByMockUrlPath(mockUrlPath);
        List<MockParamVO> mockParamVOs = mockService.getDataByMockUrlPath(mockUrlPath);

        //若无数据，直接插入数据
        if(mockParamVOs.size()==0){
            if(mockServiceName != "" && mockUrlPath != "" && mockResponse !=""){
                MockParamVO mockParamVO = new MockParamVO();
                mockParamVO.setMockUrlPath(mockUrlPath);
                mockParamVO.setMockServiceName(mockServiceName);
                mockParamVO.setMockResponse(mockResponse);
                mockParamVO.setMockParams(mockParams);
                int i = mockService.insertParam(mockParamVO);

                boolean status = false;
                if(i == 1){
                    status= true;
                }

                map.put("status", status);
                map.put("data", mockServiceName + "\n"+ mockUrlPath + "\n" + mockResponse);
                return map;
            }else{
                map.put("notice","请输入数据");
                return map;
            }
        }else {
            //先判断已有数据的param是否一致，不一致时继续添加
            boolean flag = false;
            for (MockParamVO mockParamVO : mockParamVOs){
                JSONObject dbParams = JSON.parseObject(mockParamVO.getMockParams());
                for(Object obj : Params.keySet()){
                    String key = (String) obj;
                    String value = (String) Params.get(key);
                    if (dbParams.containsKey(key)&&dbParams.getString(key).equals(value)){
                        flag = true;
                    }
                }
                if (flag){
                    //判断存在key且value相同，且url一致，替换其他数据
                    map.put("插入的路径已存在!","将为您替换!");
                    mockParamVO = new MockParamVO();
                    mockParamVO.setMockUrlPath(mockUrlPath);
                    mockParamVO.setMockServiceName(mockServiceName);
                    mockParamVO.setMockResponse(mockResponse);
                    mockParamVO.setMockParams(mockParams);
                    mockService.updateDataByPath(mockParamVO);
                    map.put("data", mockServiceName + "\n"+ mockUrlPath + "\n" + mockResponse);
                    return map;
                }else {
                    //insert数据
                    mockParamVO = new MockParamVO();
                    mockParamVO.setMockUrlPath(mockUrlPath);
                    mockParamVO.setMockServiceName(mockServiceName);
                    mockParamVO.setMockResponse(mockResponse);
                    mockParamVO.setMockParams(mockParams);
                    int i = mockService.insertParam(mockParamVO);
                    boolean status = false;
                    if(i == 1){
                        status= true;
                    }
                    map.put("status", status);
                    map.put("data", mockServiceName + "\n"+ mockUrlPath + "\n" + mockResponse);
                    return map;
                }
            }

        }



        return map;
    }


    @RequestMapping("getAllMockList")
    public String queryMockDataistL(ModelMap modelMap) {

        Map<String, Object> mapResult = new HashMap<String, Object>();
        List<MockParamVO> lists = mockService.getparamList();
        int length=lists.size();
        for(int i= 0;i<lists.size();i++){
            mapResult.put(String.valueOf(i),lists.get(i));
        }
        modelMap.addAttribute("datalength",String.valueOf(length));
        modelMap.addAttribute("mocklist",mapResult);
        return "result";
    }

    @RequestMapping("deleteMockDataByID")
    @ResponseBody
    public Object deleteMockDataByID(HttpServletRequest request, HttpServletResponse response) throws Exception{

        Map<String, Object> map = new HashMap<String, Object>();

        if(ServletRequestUtils.getRequiredStringParameter(
                request, "id").isEmpty()){
            map.put("error:","请输入想要删除的对象id!");
            return map;
        }
        int id = Integer.valueOf(ServletRequestUtils.getRequiredStringParameter(
                request, "id"));

        int count = mockService.deleteById(id);

        if(count > 0 ){
            map.put("result","删除了"+ count + "条数据");
        }else{
            map.put("result","没有数据删除个毛线!!!");

        }

        return map;
    }


    public boolean objectIsNull(Object object) {
        boolean flag = false;
        if (null == object) {
            flag = true;
        } else {
            flag = StringUtils.isBlank(String.valueOf(object));
        }
        return flag;
    }



}
