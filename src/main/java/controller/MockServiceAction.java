package controller;

import com.alibaba.fastjson.JSONObject;
import dao.service.MockService;
import dao.vo.MockParamVO;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zejunweizj on 2017/3/23.
 */

@Controller
@RequestMapping("/**")
public class MockServiceAction {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MockServiceAction.class);

    @Resource
    public MockService mockService;

    // TODO: 2018-04-12  service 实现，避免controller代码冗余 
    @RequestMapping
    @ResponseBody
    public Object mockServiceDO(HttpServletRequest request, HttpServletResponse response) throws Exception{


        String callUrl = request.getRequestURI();
        Enumeration param = request.getParameterNames();
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> urlMap = new HashMap<>();
        boolean flag1 = false ;
        boolean flag2 = false ;

        List<MockParamVO> mockParamVOs = mockService.getDataByMockUrlPath(callUrl);

        while (param.hasMoreElements()) {
            String urlKey = param.nextElement().toString();
            String urlValue = ServletRequestUtils.getRequiredStringParameter(
                    request, urlKey);
            urlMap.put(urlKey,urlValue);
        }

        String responseStr = "{}";
        JSONObject jsonObject = new JSONObject();
        if(mockParamVOs != null){
            for(MockParamVO mockParamVO : mockParamVOs){
                String params = mockParamVO.getMockParams();
                responseStr = mockParamVO.getMockResponse();
                jsonObject = JSONObject.parseObject(responseStr);
                if (params != null) {
                    //DB mockParams不为空，校验参数再返回
                    JSONObject mockParams = JSONObject.parseObject(params);
                    for(Object obj : mockParams.keySet()) {
                        String key = (String) obj;
                        String value = (String) mockParams.get(key);
                        flag1 = urlMap.containsKey(key) ;
                        if(flag1){
                            flag2 = urlMap.get(key).equals(value);
                        }

//                        else if(flag1 && !flag2){
//                            map.put("status","-1");
//                            map.put("msg","此参数无对应结果");
//                            return map;
                        }
                        if(flag1&&flag2){
                            break;
                        }
                    }
                }
                if(flag1&&flag2){
                    return jsonObject;
                }else {
                    map.put("status","-1");
                    map.put("msg","请求参数错误");
                    return map;
                }
            }
        else{
            callUrl = callUrl+"?";
            String key = "";
            while(param.hasMoreElements()){
                key = param.nextElement().toString();
                String value = ServletRequestUtils.getRequiredStringParameter(
                        request, key);

                callUrl = callUrl + key + "=" + value + "&";

            }

//            HttpSession sessionName = request.getSession();
//            String path = (String) sessionName.getAttribute("hostsSet");
            String path = null;
            String encoding = "UTF-8";
            File hostsSetFile = new File("controller/hostsSetFile.txt");
            if (hostsSetFile.isFile() && hostsSetFile.exists()) {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(hostsSetFile), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    System.out.println(lineTxt);
                    path = lineTxt;
                }
                read.close();

            }

            if(!path.contains("http") & !path.contains("https")){
                path= "http://" + path;
            }
            callUrl = callUrl.substring(0,callUrl.length()-1);
            response.setHeader("Location",path);
            response.sendRedirect(path+callUrl);

        }


        return map;
        }


    }



