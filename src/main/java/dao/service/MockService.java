package dao.service;

import dao.vo.MockParamVO;

import java.util.List;

/**
 * Created by zejunweizj on 2017/3/22.
 */
public interface MockService {

    //插入mock数据
    int insertParam(MockParamVO mockParamVO);

    //查询所有的list
    List<MockParamVO> getparamList();

    //根据id查询list
    MockParamVO getDataByID(int id);

    //通过路径去查询mock对象
    List<MockParamVO> getDataByMockUrlPath(String mockUrlPath);

    int getCountByMockUrlPath(String mockUrlPath);

    void updateDataByPath(MockParamVO mockParamVO);

    int deleteById(int id);

    List<MockParamVO> getListByMockUrlPath(String mockUrlPath);
}
