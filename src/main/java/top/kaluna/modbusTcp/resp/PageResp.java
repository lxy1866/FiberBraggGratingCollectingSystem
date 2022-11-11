package top.kaluna.modbusTcp.resp;

import java.util.List;

/**
 * @author Yuery
 * @date 2022/2/22/0022 - 12:13
 */
public class PageResp<T> {
    private Long total;

    private List<T> list;

    private long pageCount;

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageSize){
        if(pageSize == null){
            return;
        }
        if(total % pageSize == 0){
            this.pageCount = total / pageSize;
        }else{
            this.pageCount = total / pageSize + 1;
        }
    }
    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageResp{");
        sb.append("total=").append(total);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }

    public void setPageCount2(int pageSize) {
        this.pageCount = pageSize;
    }
}
