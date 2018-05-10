package mockWebService.impl;

import com.alibaba.fastjson.JSONObject;
import dao.vo.MockParamVO;
import mockWebService.mockWebService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenpei on 2018-04-12.
 */
public class mockWebServiceImpl implements mockWebService {
    @Override
    public Map<String, Object> getMockResult(Map<String, Object> urlMap, List<MockParamVO> mockParamVOs) {
        Map<String,Object> map = new HashMap<>();
        boolean flag1 = false,flag2 = false;
        for(MockParamVO mockParamVO : mockParamVOs){
            String params = mockParamVO.getMockParams();
            String responseStr = mockParamVO.getMockResponse();
            JSONObject jsonObject = JSONObject.parseObject(responseStr);
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
                    if(flag1&&flag2){
                        break;
                    }else if(flag1 && !flag2){
                        map.put("status","-1");
                        map.put("msg","此参数无对应结果");
                        return map;
                    }
                }
            }
            if(flag1&&flag2){
                map = jsonObject;
                break;
            }else {
                map.put("status","-1");
                map.put("msg","请求参数错误");
                return map;
            }
        }
        return map;
    }

}
