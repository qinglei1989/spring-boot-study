package com.rrc.generater;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.rrc.entry.base.BasePO;

/**
 * @ClassName MyBatisPlusGenerater
 * @Description MyBatis逆向生成实体类
 * @Author wang
 * @Date 2021/5/24 23:32
 * @Version 1.0
 **/
public class MyBatisPlusGenerater {
    static String packageName = "com.rrc"; // 当前包名
    static String author = "Wangql"; // 作者
    static String sqlUrl = "mysql://localhost:3306/"; // 数据库类型及地址
    static String sqlDb = "schoolmanage"; // 数据库名
    static String sqlUser = "root";
    static String sqlPassword = "900926";
    static String table = "sys_school"; // 表，用逗号隔开

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(false);
        // 配置时间类型策略（date类型），如果不配置会生成LocalDate类型
        gc.setDateType(DateType.ONLY_DATE);
        // 是否覆盖已有文件(默认值：false)
        gc.setFileOverride(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:" + sqlUrl + sqlDb + "?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(sqlUser);
        dsc.setPassword(sqlPassword);
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(packageName);
        //pc.setEntity("");
        //pc.setModuleName("school");
        mpg.setPackageInfo(pc);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null); // 不生成MapperXML
        //控制 不生成 controller  空字符串就行
        templateConfig.setController("");
        templateConfig.setServiceImpl("");
        templateConfig.setService("");

        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 数据库表映射到实体的命名策略:下划线转驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 实体是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(true);
        // 生成 @RestController 控制器
        strategy.setRestControllerStyle(false);
        // 实体类主键名称设置
        strategy.setSuperEntityColumns("id");
        //是否生成注解@TableField 默认false
        strategy.setEntityTableFieldAnnotationEnable(false);
        //实体类的基础父类。没有可以不配置。
        strategy.setSuperEntityClass(BasePO.class);
        //这里本来以为配置上 生成的实体类就没有父类的属性了，但其实不是。
        //如何去掉父类属性，下面有说明。
        //strategy.setSuperEntityColumns("serialVersionUID");
        strategy.setInclude(table.split(","));
        // 驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        // 表前缀
        strategy.setTablePrefix("sys_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
