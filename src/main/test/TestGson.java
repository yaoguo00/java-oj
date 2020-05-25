import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author guoyao
 * @create 2020/5/20
 */
public class TestGson {
    static class Hero{
        public String name;
        public String skill1;
        public String skill2;
        public String skill3;
        public String skill4;
    }

    public static void main(String[] args) {

        //把对象构造成json格式的字符串
        //把json格式的字符串转换为对象
        Hero hero = new Hero();
        hero.name = "曹操";
        hero.skill1 = "三段跳";
        hero.skill2 = "剑气";
        hero.skill3 = "加攻击并且吸血";
        hero.skill4 = "每次释放技能都能加攻速";
        Gson gson = new GsonBuilder().create();
        String jsonString = gson.toJson(hero);
//        System.out.println(jsonString);
        Hero hero2 = gson.fromJson(jsonString,Hero.class);
        System.out.println(hero2.name);
        System.out.println(hero2.skill1);
        System.out.println(hero2.skill2);
        System.out.println(hero2.skill3);
        System.out.println(hero2.skill4);

    }
}
