package vn.plusplus.lms.factory;


import java.io.Serializable;
import java.util.List;

public class GeneralPageResponse<T> implements Serializable {

    private ResponseStatus status;
    private List<T> data;

    private PageResponse page;

    public GeneralPageResponse(ResponseStatus status, List<T> data, PageResponse pageResponse) {
        this.status = status;
        this.data = data;
        this.page = pageResponse;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public PageResponse getPage() {
        return page;
    }

    public void setPage(PageResponse page) {
        this.page = page;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }
}
