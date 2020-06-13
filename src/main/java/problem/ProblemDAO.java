package problem;

import common.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guoyao
 * @create 2020/5/20
 */
//数据访问层
public class ProblemDAO {

    //获取所有题目信息
    public List<Problem> selectAll(){
        List<Problem> result = new ArrayList<>();
        //1.获取数据库连接
        Connection connection = DBUtil.getConnection();
        //2.拼装SQL语句
        String sql= "select * from oj_table";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            //3执行SQL语句
            resultSet = statement.executeQuery();
            //4.遍历结果集
            while (resultSet.next()){
                Problem problem = new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setLevel(resultSet.getString("level"));

                //这几个字段暂时不需要
//                problem.setDescription(resultSet.getString("description"));
////                problem.setTemplateCode(resultSet.getString("templateCode"));
////                problem.setTestCode(resultSet.getString("testCode"));
                result.add(problem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.关闭释放资源
            DBUtil.close(connection,statement,resultSet);
        }
        return result;
    }

    //获取指定id的题目
    public Problem selectOne(int id){
        //1.建立数据库连接
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        //2.拼装SQL语句
        String sql = "select * from oj_table where id = ?";
        try {
            statement =connection.prepareStatement(sql);
            statement.setInt(1,id);
            //3.执行SQL语句
            resultSet = statement.executeQuery();
            //4.遍历结果集
            if(resultSet.next()){
                Problem problem =new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setLevel(resultSet.getString("level"));
                problem.setDescription(resultSet.getString("description"));
                problem.setTemplateCode(resultSet.getString("templateCode"));
                problem.setTestCode(resultSet.getString("testCode"));
                return problem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //5.关闭释放资源
            DBUtil.close(connection,statement,resultSet);
        }
        return null;

    }

    //新增一个题目到数据库
    public void insert(Problem problem){
        //1.获取数据库连接
        Connection connection = DBUtil.getConnection();
        //2.拼装SQL语句
        String sql = "insert into oj_table values(null, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,problem.getTitle());
            statement.setString(2,problem.getLevel());
            statement.setString(3,problem.getDescription());
            statement.setString(4,problem.getTemplateCode());
            statement.setString(5,problem.getTestCode());
            System.out.println("insert:"+statement);
            //3.执行SQL语句
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //4.关闭释放相关资源
            DBUtil.close(connection,statement,null);
        }


    }

    //删除指定信息
    public void delete(int id){

        //1.获取数据库连接
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement = null;
        //2.拼装SQL
        String sql = "delete from oj_table where id = ?";
        //3.执行SQL
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //4.关闭并释放资源
            DBUtil.close(connection,statement,null);
        }
    }

    public static void main(String[] args) {
        //1.验证insert操作
        //题目1
        Problem problem1 = new Problem();
        problem1.setTitle("各位相加");
        problem1.setLevel("简单");
        problem1.setDescription("给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。\n" +
                "\n" +
                "示例:\n" +
                "\n" +
                "输入: 38\n" +
                "输出: 2 \n" +
                "解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。\n" +
                "\n");
        problem1.setTemplateCode(
                "public class Solution {\n" +
                "    public int addDigits(int num) {\n" +
                "\n" +
                "    }\n" +
                "}");
        problem1.setTestCode(
                "    public static void main(String[] args) {\n" +
                "        Solution s = new Solution();\n" +
                "        if(s.addDigits(38) == 2) {\n" +
                "            System.out.println(\"test OK\");\n" +
                "        }else{\n" +
                "            System.out.println(\"testfailed\");\n" +
                "        }\n" +
                "\n" +
                "        if(s.addDigits(1) == 1) {\n" +
                "            System.out.println(\"test OK\");\n" +
                "        }else{\n" +
                "            System.out.println(\"testfailed\");\n" +
                "        }\n" +
                "    }\n");
        ProblemDAO problemDAO = new ProblemDAO();
        problemDAO.insert(problem1);
        System.out.println("insert ok");

        //题目2
        Problem problem2 = new Problem();
        problem2.setTitle("第一个只出现一次的字符");
        problem2.setLevel("简单");
        problem2.setDescription("在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。\n" +
                "\n" +
                "示例:\n" +
                "\n" +
                "s = \"abaccdeff\"\n" +
                "返回 \"b\"\n" +
                "\n" +
                "s = \"\" \n" +
                "返回 \" \"\n" +
                "\n");
        problem2.setTemplateCode("public class Solution {\n" +
                "    public char firstUniqChar(String s) {\n" +
                "\n" +
                "    }\n" +
                "}");
        problem2.setTestCode(
                "    public static void main(String[] args) {\n" +
                        "        Solution s = new Solution();\n" +
                        "        if(s.firstUniqChar('abaccdeff') == 'b') {\n" +
                        "            System.out.println(\"test OK\");\n" +
                        "        }else{\n" +
                        "            System.out.println(\"testfailed\");\n" +
                        "        }\n" +
                        "\n" +
                        "        if(s.firstUniqChar('adweewadf') == 'f') {\n" +
                        "            System.out.println(\"test OK\");\n" +
                        "        }else{\n" +
                        "            System.out.println(\"testfailed\");\n" +
                        "        }\n"+
                        "        if(s.firstUniqChar('') == ' ') {\n" +
                        "            System.out.println(\"test OK\");\n" +
                        "        }else{\n" +
                        "            System.out.println(\"testfailed\");\n" +
                        "        }\n"+
                        "    }\n");
        ProblemDAO problemDAO2 = new ProblemDAO();
        problemDAO2.insert(problem2);
        System.out.println("insert ok");




//        //2.测试selectAll
//        ProblemDAO problemDAO = new ProblemDAO();
//        List<Problem> problems = problemDAO.selectAll();
//        System.out.println("selctAll:" + problems);


//        //3.测试selectOne
//        ProblemDAO problemDAO = new ProblemDAO();
//        Problem problem = problemDAO.selectOne(1);
//        System.out.println("selectOne:"+problem);

//        //4.删除delete
//        ProblemDAO problemDAO = new ProblemDAO();
//        Problem problem =new Problem();
//        problemDAO.delete(1);
    }
}
