package uestc.team03.mall.common.domain;

public class Request {
    private Integer reqNumber;

    private Integer id;

    private String loginname;

    private String remark;

    public Integer getReqNumber() {
        return reqNumber;
    }

    public void setReqNumber(Integer reqNumber) {
        this.reqNumber = reqNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}