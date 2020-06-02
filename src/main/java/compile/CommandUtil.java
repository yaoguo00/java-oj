package compile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author guoyao
 * @create 2020/5/19
 */

//借助这个类，让java代码能够去执行一个具体的指令，
    //多进程编程exec执行一个命令
// 例如javac Test.java
public class CommandUtil {
    //cmd表示要执行的命令
    //stdoutFile表示标准输出结果重定向到那个文件中，如果为null不需要重定向
    //stderrFile表示标准错误结果重定向到那个文件
    public static int run(String cmd,String stdoutFile,String stderrFile)
            throws IOException, InterruptedException {
        //1.获取Runtime对象,Runtime对象是一个单例的
        Runtime runtime=Runtime.getRuntime();
        //2.通过Runtime对象中的exec方法来执行一个指令
        //相当于在命令行中输入cmd命令并执行
        Process process=runtime.exec(cmd);
        //3.针对标准输出进行重定向
        if(stdoutFile!=null){
            //进程的标准输出中的结果就可以通过这个InputStream获取到
            InputStream stdoutFrom=process.getInputStream();
            OutputStream stdoutTo=new FileOutputStream(stdoutFile);
            int ch=-1;
            while((ch=stdoutFrom.read())!= -1){
                stdoutTo.write(ch);
            }
            stdoutFrom.close();
            stdoutTo.close();
        }
        //4.针对标准错误也进行重定向
        if(stderrFile!=null){
            InputStream stderrFrom =process.getErrorStream();
            OutputStream stderrTo=new FileOutputStream(stderrFile);
            int ch=-1;
            while((ch=stderrFrom.read())!=-1){
                stderrTo.write(ch);
            }
            stderrFrom.close();
            stderrTo.close();

        }
        //5.为了确保子进程先执行完，需要加上进程等待
        //  父进程会在waitfor阻塞等待，直到子进程执行结束，再继续往下执行
       int exitCode= process.waitFor();
        return exitCode;
    }


    public static void main(String[] args) throws IOException, InterruptedException {
       run("javac","G:\\java_project/stdout.txt","G:\\java_project/stderr.txt");
    }
}
