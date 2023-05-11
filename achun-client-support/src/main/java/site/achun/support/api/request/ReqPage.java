package site.achun.support.api.request;

import site.achun.support.api.response.RspPage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: SunAo
 * @Date: 2021/7/9 下午8:23
 */
public class ReqPage implements Serializable {
    private static final long serialVersionUID = -4662786978142228573L;
    /**
     * 每页显示记录数
     */
    private int size = 10;
    /**
     * 当前页
     */
    private int page = 1;

    public static Builder builder(){
        return new Builder();
    }

    public static ReqPage of(int page,int size){
        return new ReqPage(size,page);
    }

    public ReqPage(int size, int page) {
        this.size = size;
        this.page = page;
    }

    public ReqPage() {
    }

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }

    public RspPage createPageRsp() {
        RspPage rsp = new RspPage();
        rsp.setPage(page);
        rsp.setSize(size);
        rsp.setRows(new ArrayList());
        return rsp ;
    }

    public <T> RspPage createPageRsp(List<T> rows, long total){
        RspPage rsp = createPageRsp();
        rsp.setRows(rows);
        rsp.setTotal(total);
        return rsp;
    }
    
    public static class Builder{
        private int size;
        private int page;
        public Builder page(int page){
            this.page = page;
            return this;
        }
        public Builder size(int size){
            this.size = size;
            return this;
        }
        public ReqPage build(){
            ReqPage reqPage = new ReqPage();
            reqPage.setPage(page);
            reqPage.setSize(size);
            return reqPage;
        }
    }
}
