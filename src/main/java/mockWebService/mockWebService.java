package mockWebService;

import dao.vo.MockParamVO;

import java.util.List;
import java.util.Map;

/**
 * @author chenpei
 * @date 2018-04-12
 */
public interface mockWebService {

    Map<String,Object> getMockResult(Map<String,Object> urlMap, List<MockParamVO> mockParamVOs);

}
