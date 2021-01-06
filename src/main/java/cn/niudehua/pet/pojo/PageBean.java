package cn.niudehua.pet.pojo;

import java.util.List;

public class PageBean {

    private int currPage;// 当前页
    private int pageSize; // 每页记录条数
    private int totalPage; // 总页数
    private int totalCount; // 总记录数
    private List<User> list;// list集合封装数据

    public int getCurrPage() {
        return currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<User> getList() {
        return list;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setList(List<User> list) {
        this.list = list;
    }


}
