package dao.impl;

import dao.BatisBase;
import dao.service.MockService;
import dao.vo.MockParamVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zejunweizj on 2017/3/22.
 */

@Service
@Transactional
public class MockDataServiceImpl implements MockService {


    @Resource
    private BatisBase batisBase;


    @Override
    public int insertParam(MockParamVO mockParamVO){


        return batisBase.DBexecuteInsert("mock_data.insert",mockParamVO);

    }


    @Override
    public List<MockParamVO> getparamList(){

        MockParamVO mockParamVO = new MockParamVO();

        List<Object> lists =  batisBase.DBexecuteList("mock_data.getdatelist",mockParamVO);

        List<MockParamVO> newlist = new ArrayList<MockParamVO>();

        for(int i = 0 ;i< lists.size(); i++){
            mockParamVO = (MockParamVO)lists.get(i);
            newlist.add(i,mockParamVO);
        }

        return newlist;
    }


    @Override
    public MockParamVO getDataByID (int id){
        MockParamVO mockParamVO = new MockParamVO();
        mockParamVO.setId(id);
        mockParamVO = (MockParamVO) batisBase.DBexecute("mock_data.getDateById",mockParamVO);

        return mockParamVO;
    }

    @Override
    public List<MockParamVO> getDataByMockUrlPath(String mockUrlPath) {
        MockParamVO mockParamVO = new MockParamVO();

        mockParamVO.setMockUrlPath(mockUrlPath);

        List<Object> lists =  batisBase.DBexecuteList("mock_data.getDataByMockUrlPath",mockParamVO);

        List<MockParamVO> list = new ArrayList<MockParamVO>();

        for(int i = 0 ;i< lists.size(); i++){
            mockParamVO = (MockParamVO)lists.get(i);
            list.add(i,mockParamVO);
        }

        return list;
    }


//    @Override
//    public MockParamVO getDataByMockUrlPath(String mockUrlPath){
//
//        MockParamVO mockParamVO = new MockParamVO();
//
//        mockParamVO.setMockUrlPath(mockUrlPath);
//
//        mockParamVO = (MockParamVO) batisBase.DBexecute("mock_data.getDataByMockUrlPath",mockParamVO);
//
//
//        return mockParamVO;
//
//    }

    @Override
    public int getCountByMockUrlPath(String mockUrlPath){

        MockParamVO mockParamVO = new MockParamVO();

        mockParamVO.setMockUrlPath(mockUrlPath);

        int count = (int) batisBase.DBexecute("mock_data.getCountByMockUrlPath",mockParamVO);


        return count;

    }


    @Override
    public void updateDataByPath(MockParamVO mockParamVO){

        batisBase.DBexecute("mock_data.updateDataByPath",mockParamVO);


    }


    @Override
    public int deleteById(int id){

        MockParamVO mockParamVO  = new MockParamVO();
        mockParamVO.setId(id);
        return batisBase.DBexecuteDelete("mock_data.deleteById",mockParamVO);

    }

    @Override
    public List<MockParamVO> getListByMockUrlPath(String mockUrlPath) {
        MockParamVO mockParamVO = new MockParamVO();

        mockParamVO.setMockUrlPath(mockUrlPath);

        List<Object> lists =  batisBase.DBexecuteList("mock_data.getDataByMockUrlPath",mockParamVO);

        List<MockParamVO> newlist = new ArrayList<MockParamVO>();

        for(int i = 0 ;i< lists.size(); i++){
            mockParamVO = (MockParamVO)lists.get(i);
            newlist.add(i,mockParamVO);
        }

        return newlist;
    }


}
