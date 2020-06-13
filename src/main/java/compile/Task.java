package compile;

import common.FileUtil;

import java.io.File;
import java.io.IOException;

/**
 * @author guoyao
 * @create 2020/5/19
 */
//借助这个类来描述一次编译运行的过程
public class Task {

    //编译运行过程中依赖了一些临时文件，需要约定临时文件的名字
    //所有的临时文件都放在tmp目录中
    //这些临时文件就是为了把执行过程中涉及到的各种中间结果记录下来
    //方便后面调试
    private  static final String WORK_DIR = "./tmp/";
    //要编译的代码的类名
    private  static final String CLASS = "Solution";
    //要编译的代码对应的文件，需要和类名一致
    private  static final String CODE = WORK_DIR + "Solution.java";
    //标准输入对应的文件（其实没用到）
    private  static final String STDIN = WORK_DIR + "stdin.txt";
    //标准输出对应的文件（编译执行的代码的结果保存在到这个文件中）
    private  static final String STDOUT = WORK_DIR + "stdout.text";
    //标准错误对应的文件（编译执行的代码的结果保存在到这个文件中）
    private  static final String STDERR = WORK_DIR + "stderr.txt";
    //编译错误对应的文件（编译出错时的具体原因）
    private  static final String COMPILE_ERROR = WORK_DIR + "compile_error.txt";



    public  static Answer compileAndRun(Question question) throws IOException, InterruptedException {
        Answer answer = new Answer();
        //0.先创建好存放临时文件的目录
        File workDir = new File(WORK_DIR);
        if(!workDir.exists()){
            workDir.mkdirs();
        }
        //1.根据Question对象，构造一些临时文件
        FileUtil.writeFile(CODE,question.getCode());
        FileUtil.writeFile(STDIN,question.getStdin());
        //2.构造编译命令，并执行
        //  编译命令形如 javac -encoding utf8  ./tmp/Solution.java -d ./tmp/
        //  直接通过字符串拼接，有的时候如果太复杂，容易出错，尤其是命令选项多的时候，很容易少空格之类的
        // String cmd = "javac -encoding utf8" + CODE + "-d" + WORK_DIR;
        String cmd = String.format(
                "javac -encoding utf8 %s -d %s",CODE,WORK_DIR
        );

        System.out.println("编译命令:"+cmd);
        CommandUtil.run(cmd,null,COMPILE_ERROR);
        //还需要判定一下编译是否出错，如果出错了，那就不需要继续运行了
        //认为COMPILE_ERROR文件为空，就表示编译顺利，如果非空就表示编译出错
        String compileError = FileUtil.readFile(COMPILE_ERROR);
        if(!"".equals(compileError)){
            //编译出错
            System.out.println("编译出错");
            answer.setError(1);
            answer.setReason(compileError);
            return answer;
        }
        //3.构造运行命令，并执行
        //  运行命令形式如：java -classpath ./tmp/ Solution
        //  为了能让java命令正确找到类对应的 .class文件，需要指定加载路径， -classpath选项来指定
        cmd = String.format(
                "java -classpath %s %s",WORK_DIR,CLASS
        );
        System.out.println("运行命令："+cmd);
        CommandUtil.run(cmd,STDOUT,STDERR);
        // 判定运行是否出错（是否存在异常），查看STDERR是否为空
        String stdErr = FileUtil.readFile(STDERR);
        if(!"".equals(stdErr)){
            System.out.println("运行出错");
            answer.setError(2);
            answer.setReason(stdErr);
            return answer;
        }
        //4.将最终的运行结果包装到Answer中
        answer.setError(0);
        answer.setStdout(FileUtil.readFile(STDOUT));
        return answer;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //验证Task是否能否正确运行
        Question question = new Question();
        question.setCode(
                "public class Solution {\n" +
                "   public static void main(String[] args){\n" +
                "       System.out.println(\"hello\");\n"+
                "    }\n"+
                "}\n"
        );
        question.setStdin("");
        Task task = new Task();
        Answer answer = task.compileAndRun(question);
        System.out.println(answer);
    }
}
