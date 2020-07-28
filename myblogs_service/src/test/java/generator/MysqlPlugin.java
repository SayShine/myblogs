package generator;


import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MysqlPlugin {

    public static void main(String[] args) throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;

        //默认为空  自行设置
        File configFile = new File("");

        //生成myblog库下的表
//        File configFile = new File("E:\\IdeaProjects\\myblogs\\myblogs_service\\src\\test\\java\\generator\\mybatis-generator-myblog.xml");

        //生成tscxk库下的表
//        File configFile = new File("E:\\IdeaProjects\\myblogs\\myblogs_service\\src\\test\\java\\generator\\mybatis-generator-tscxk.xml");



        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        //执行生成代码
        myBatisGenerator.generate(null);

        //输出警告信息
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }

}
