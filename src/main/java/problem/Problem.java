package problem;

/**
 * @author guoyao
 * @create 2020/5/20
 */
//实体类，每个Problem对象就对应到oj_table中的一条记录
public class Problem {
    //题号
    private int id;
    //题目标题
    private String title;
    //难度
    private String level;
    //题目描述
    private String description;
    //题目代码框架
    private String templateCode;
    //题目测试代码
    private String testCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", level='" + level + '\'' +
                ", description='" + description + '\'' +
                ", templateCode='" + templateCode + '\'' +
                ", testCode='" + testCode + '\'' +
                '}';
    }
}
