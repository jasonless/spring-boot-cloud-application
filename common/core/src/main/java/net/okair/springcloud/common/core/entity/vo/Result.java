package net.okair.springcloud.common.core.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import net.okair.springcloud.common.core.exception.BaseException;
import net.okair.springcloud.common.core.exception.IErrorType;
import net.okair.springcloud.common.core.exception.SystemErrorEnum;

import java.time.Instant;
import java.time.ZonedDateTime;

/**
 * @author starrk
 * Created on 2019/12/27
 */
@Getter
@ApiModel(description = "rest 请求的返回模型，所有rest正常都返回该类对象")
public class Result<T> {

    public static final String SUCCESSFUL_CODE = "000000";
    public static final String SUCCESSFUL_MSG = "成功";
    @ApiModelProperty(value = "处理结果code",required = true)
    private String code;
    @ApiModelProperty(value = "处理结果描述信息")
    private String msg;
    @ApiModelProperty(value = "请求结果生成时间戳")
    private Instant time;
    @ApiModelProperty(value = "处理结果数据信息")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public Result(){
        this.time = ZonedDateTime.now().toInstant();
    }

    public Result(IErrorType errorType){
        this.code=errorType.getCode();
        this.msg=errorType.getMsg();
        this.time = ZonedDateTime.now().toInstant();
    }

    public Result(IErrorType errorType, T data){
        this(errorType);
        this.data=data;
    }

    /**
     * 构造成功结果
     * @param code
     * @param msg
     * @param data
     */
    private Result(String code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.time = ZonedDateTime.now().toInstant();

    }

    /**
     * 快速创建成功结果并返回结果数据
     * @param data
     * @return
     */
    public static Result success(Object data){
        return new Result<>(SUCCESSFUL_CODE,SUCCESSFUL_MSG,data);
    }

    /**
     * 快速创建成功结果
     * @return
     */
    public static Result success(){
        return success(null);
    }

    /**
     * 系统异常，没有返回数据
     * @return
     */
    public static Result fail(){
        return new Result(SystemErrorEnum.SYSTEM_ERROR);
    }

    /**
     * 系统异常类并返回结果数据
     * @param baseException
     * @return
     */
    public static Result fail(BaseException baseException){
        return fail(baseException,null);
    }

    /**
     * 系统异常类并返回结果
     * @param baseException
     * @param data
     * @return
     */
    public static Result fail(BaseException baseException,Object data){
        return new Result<>(baseException.getErrorType(),data);
    }

    public static Result fail(IErrorType errorType,Object data){
        return new Result<>(errorType,data);
    }

    /**
     * 系统异常并返回结果
     * @param errorType
     * @return
     */
    public static Result fail(IErrorType errorType){
        return Result.fail(errorType);
    }

    public static Result fail(Object data){
        return new Result<>(SystemErrorEnum.SYSTEM_ERROR,data);
    }

    /**
     * 成功 code = 000000
     * @return
     */
    @JsonIgnore
    public boolean isSuccess(){
        return SUCCESSFUL_CODE.equals(this.code);
    }

    /**
     * 失败
     * @return
     */
    public boolean isFail(){
        return !isSuccess();
    }


}
