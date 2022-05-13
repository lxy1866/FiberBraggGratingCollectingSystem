package top.kaluna.pipelineMonitor.req;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author Yuery
 * @date 2022/2/25/0025 - 11:16
 */
public class PageReq {
    @NotNull(message = "[页码]不能为空")
    private int page;

    @NotNull(message = "[每页条数]不能为空")
    @Max(value = 1000, message = "[每页条数]不能超过1000")
    private int size;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageReq{");
        sb.append("page=").append(page);
        sb.append(", size=").append(size);
        sb.append('}');
        return sb.toString();
    }
}
