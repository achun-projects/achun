package site.achun.support.api.response;

import java.util.List;

/**
 * @Author: SunAo
 * @Date: 2021/7/9 下午8:23
 */
public class RspPage<T> extends Rsp {

    private static final long serialVersionUID = 6989962323304566323L;
    /**
     * 每页显示记录数
     */
    private int size = 20;
    /**
     * 当前页
     */
    private int page = 1;
    /**
     * 总记录数
     */
    private long total = 0;
    /**
     * 总页数
     */
    private long pages = 0;
    /**
     * 记录集合
     */
    private List<T> rows;

    public int getSize() {
        if(size <= 0){
            return 20;
        }
        return size;
    }
    public void setSize(int pageSize) {
        this.size = pageSize;
    }
    public int getPage() {
        if(page < 0){
            return 1;
        }
        return page;
    }
    public void setPage(int page){
        this.page = page;
    }
    public long getPages(){
        if (this.total % this.size == 0) {
            this.pages = this.total / this.size;
        } else {
            this.pages = this.total / this.size + 1;
        }
        return pages;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setTotalPage(int pages) {
        this.pages = pages;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public <V> RspPage<V> copyTo(){
        RspPage<V> to = new RspPage<>();
        to.setPage(this.getPage());
        to.setSize(this.getSize());
        to.setTotal(this.getTotal());
        return to;
    }

}
