package com.liqingdong.core.tools;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 注意控制台输入表名时需保持与数据库表大小写一致，否则会报表不存在的错误，但不影响代码生成结果。
 * 另外生成的Entity类引入了些许未使用的类，生成后需要删除多余的无效引入
 *
 * @author liqingdong
 * @since 2019/01/29 12:26
 */
public class AutoGeneratorCodeTools {

    private static final String PROJECT_PATH = System.getProperty("user.dir");

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());// 默认使用 VelocityTemplateEngine

        mpg.setGlobalConfig(globalConfig());

        mpg.setDataSource(dataSourceConfig());

        mpg.setPackageInfo(packageConfig());

        mpg.setCfg(injectionConfig());

        mpg.setTemplate(templateConfig());

        mpg.setStrategy(strategyConfig());

        mpg.execute();// 执行
    }

    // 1、全局配置 globalConfig
    private static GlobalConfig globalConfig() {
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(PROJECT_PATH + "/src/main/java");
        gc.setAuthor("auto generate");
        gc.setFileOverride(true);
        gc.setEnableCache(false);
        gc.setActiveRecord(true);
        gc.setKotlin(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(false);
        gc.setIdType(IdType.AUTO);
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper.xml");
        gc.setOpen(false);
        return gc;
    }

    // 2、数据源配置 dataSource
    private static DataSourceConfig dataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root123");
        dsc.setDbType(DbType.MYSQL);
        return dsc;
    }

    // 3、包配置 packageInfo
    private static PackageConfig packageConfig() {
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.liqingdong");
        pc.setEntity("core.entity");
        pc.setService("core.service");
        pc.setServiceImpl("core.service.impl");
        pc.setController("web.controller");
        pc.setMapper("core.mapper");
        return pc;
    }

    // 4、注入配置 injectionConfig
    // 由于项目生成的 *mapper.xml需放入classpath:resources/mapper 下，因此使用注入配置是实现
    private static InjectionConfig injectionConfig() {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // do nothing
            }
        };

        String templatePath = "/templates/mapper.xml.ftl";
        List<FileOutConfig> focList = Collections.singletonList(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return PROJECT_PATH + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + ".xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    // 5、配置模板 template
    private static TemplateConfig templateConfig() {

        TemplateConfig tc = new TemplateConfig();

        // 配置自定义输出模板 注：不带模板后缀.ftl/.vm
        tc.setEntity("/templates/entity.java");
        tc.setMapper("/templates/mapper.java");
        tc.setService("/templates/service.java");
        tc.setServiceImpl("/templates/serviceImpl.java");
        tc.setController("/templates/controller.java");
//        tc.setXml("/templates/mapper.xml");// 此处被InjectionConfig设置覆盖，可不用设置

        return tc;
    }

    // 6、策略配置 strategy
    private static StrategyConfig strategyConfig() {
        StrategyConfig sc = new StrategyConfig();
        sc.setTablePrefix("t_", "quartz_");
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        sc.setSuperEntityClass("com.liqingdong.core.entity.BaseEntity");
        sc.setSuperEntityColumns("CREATE_BY","CREATE_DATE","UPDATE_BY","UPDATE_DATE");
        sc.setEntityBooleanColumnRemoveIsPrefix(true);
        sc.setSkipView(true);
        sc.setEntityLombokModel(true);
        sc.setInclude(scanner("表名"));
        sc.setRestControllerStyle(false);
        sc.setControllerMappingHyphenStyle(true);
        return sc;
    }

    // 控制台读取
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(("请输入" + tip + "："));
        if (scanner.hasNext()) {
            String input = scanner.next();
            if (StringUtils.isNotEmpty(input)) return input;
        }

        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

}
