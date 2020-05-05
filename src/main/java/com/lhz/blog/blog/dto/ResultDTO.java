package com.lhz.blog.blog.dto;

import com.lhz.blog.blog.message.CustomErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {
    /**
     * 返回给前端的错误码
     * */
    private Integer code;
    /**
     * 返回给前端的错误信息
     * */
    private String message;
    /**
     * 在发生错误的位置调用此方法
     * */
    public static ResultDTO errorOf(Integer code,String message){
        return new ResultDTO(code, message);
    }

    public static ResultDTO errorOf(CustomErrorMessage customErrorMessage){
        return new ResultDTO(customErrorMessage.getCode(),customErrorMessage.getMessage());
    }

    public static ResultDTO okOf(){
        return new ResultDTO(200,"请求成功");
    }
}
