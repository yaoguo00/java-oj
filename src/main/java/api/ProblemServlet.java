package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import problem.Problem;
import problem.ProblemDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author guoyao
 * @create 2020/5/20
 */
public class ProblemServlet extends HttpServlet{

    private Gson gson = new GsonBuilder().create();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id == null || "".equals(id)){
            //没有id这个参数，执行“查找全部”逻辑
            selectAll(resp);
        }else{
            //存在id这个参数，执行“查找指定题目”的逻辑
            selectOne(Integer.parseInt(id),resp);
        }
    }

    //获取所有题目列表
    private void selectAll(HttpServletResponse resp) throws IOException {
        ProblemDAO problemDAO = new ProblemDAO();
        List<Problem> problems = problemDAO.selectAll();
        //把结果组织成json结构，
        //注意：需要把problems中的有些字段去掉
        String jsonString = gson.toJson(problems);
        resp.getWriter().write(jsonString);
    }


    private void selectOne(int i, HttpServletResponse resp){

    }


}
