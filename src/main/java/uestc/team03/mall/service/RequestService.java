package uestc.team03.mall.service;

import com.github.pagehelper.PageInfo;
import uestc.team03.mall.common.domain.Request;

public interface RequestService {
    public PageInfo<Request> findRequest(int pageNo, int pageSize, Request request);

    void deleteRequest(int id);
}
