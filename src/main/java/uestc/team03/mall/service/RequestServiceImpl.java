package uestc.team03.mall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uestc.team03.mall.common.domain.Request;
import uestc.team03.mall.common.domain.RequestExample;
import uestc.team03.mall.mapper.RequestMapper;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService{
    @Autowired
    private RequestMapper requestMapper;

    @Override
    public PageInfo<Request> findRequest(int pageNo, int pageSize, Request request){
        PageHelper.startPage(pageNo,pageSize);
        RequestExample requestExample = new RequestExample();
        RequestExample.Criteria criteria = requestExample.createCriteria();
        if(request != null){
            if(request.getLoginname() != null && request.getLoginname().trim().length()>0){
                criteria.andLoginnameLike("%"+request.getLoginname()+"%");
            }
            if(request.getRemark() != null && request.getRemark().trim().length()>0){
                criteria.andRemarkLike("%"+request.getRemark()+"%");
            }
        }
        List<Request> requestList = requestMapper.selectByExample(requestExample);
        PageInfo<Request> pageInfo = new PageInfo<>(requestList);
        return pageInfo;
    }

    @Override
    public void deleteRequest(int id){
        requestMapper.deleteByPrimaryKey(id);
    }
}
