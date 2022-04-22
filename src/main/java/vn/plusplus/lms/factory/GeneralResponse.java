package vn.plusplus.lms.factory;

import java.io.Serializable;

public class GeneralResponse<T> implements Serializable {

    private ResponseStatus status;

    private T data;

    public GeneralResponse() {}

    public GeneralResponse(ResponseStatus status) {
        this.status = status;
    }

    public GeneralResponse(ResponseStatus status, T data) {
        this.status = status;
        this.data = data;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
