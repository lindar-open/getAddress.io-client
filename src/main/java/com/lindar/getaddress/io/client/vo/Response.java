package com.lindar.getaddress.io.client.vo;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author iulian.dafinoiu
 * @param <T>
 */
@Data
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 1421365478974635128L;

    private int status;
    
    @SerializedName("result")
    private T data;
}
