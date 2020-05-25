package compile;

/**
 * @author guoyao
 * @create 2020/5/19
 */
//编译和执行过程中的结果
public class Answer {

    //通过error表示当前的错误类型
    //约定error为0表示没错误，error为1表示编译错误，error为2表示运行错误
    private int error;
    //表示具体的出错原因，可能是编译错误，也可能是运行错误（异常信息）
    private String reason;
    //执行时标准输出对应的内容
    private String stdout;
    //执行时标准错误对应的内容
    private String stderr;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }

    public String getStderr() {
        return stderr;
    }

    public void setStderr(String stderr) {
        this.stderr = stderr;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "error=" + error +
                ", reason='" + reason + '\'' +
                ", stdout='" + stdout + '\'' +
                ", stderr='" + stderr + '\'' +
                '}';
    }
}
