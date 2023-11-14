package com.example.nuonuo.entity.Ekanya.EsingleUser;

import com.example.nuonuo.entity.Ekanya.EsingleUser.EuserSingleData;

public class EuserSingle {

    private EuserSingleData data;

    private boolean success;

    private Error error;

    public EuserSingleData getData() {
        return data;
    }

    public void setData(EuserSingleData data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
