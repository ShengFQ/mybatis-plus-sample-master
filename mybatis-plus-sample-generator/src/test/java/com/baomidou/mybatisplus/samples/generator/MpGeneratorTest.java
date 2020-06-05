package com.baomidou.mybatisplus.samples.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;

/**
 * <p>
 * 通过junit test 生成代码
 * 演示：自定义代码模板
 * 默认不会覆盖已有文件，如果需要覆盖，配置GlobalConfig.setFileOverride(true)
 * </p>
 *
 * @author yuxiaobin
 * @date 2018/11/29
 */
public class MpGeneratorTest {

    @Test
    public void generateCode() {
        generate("zwow_iot","zw_alipay_freeze_customer");
    }

    private void generate(String moduleName, String... tableNamesInclude) {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //TODO 更换为你的目录
        gc.setOutputDir("/Users/sheng/workspace/github/mybatis/mybatisplus/codeGen/src/main/java");
        gc.setAuthor("shengfq");
        gc.setOpen(false);
        //默认不覆盖，如果文件存在，将不会再生成，配置true就是覆盖
        gc.setFileOverride(true);
        //add by shengfq
        gc.setBaseResultMap(true);
        gc.setDateType(DateType.TIME_PACK);
        gc.setEntityName("%s");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://47.112.133.108:3306/zwow_iot?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("iot");
        dsc.setPassword("Zwow@0I6O9T0.com");
        //add by shengfq
        dsc.setDbType(DbType.MYSQL);
        dsc.setSchemaName("zwow_iot");
        mpg.setDataSource(dsc);
        //数据库表配置

        // 包配置
        PackageConfig pc = new PackageConfig();
        //TODO 更换为你的包名
        pc.setParent("com.geer2.zwow.alipay");
        mpg.setPackageInfo(pc);

        // 策略配置 数据表
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        //TODO 更换为你的controller超类
        strategy.setSuperControllerClass("com.geer2.base.model.AbstructBaseController");
        strategy.setInclude(tableNamesInclude);
//        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        strategy.entityTableFieldAnnotationEnable(true);

        strategy.isCapitalMode();
        strategy.setSkipView(true);
        strategy.setTablePrefix("zw_");
        //TODO 更换为你的service超类
        strategy.setSuperServiceClass("com.geer2.base.model.IBaseService");
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        configCustomizedCodeTemplate(mpg);
        //configInjection(mpg,pc);

        mpg.execute();

        generateVo(moduleName, dsc,pc,tableNamesInclude);
    }

    /**
     * 自动生成VObean
     * */
    private void generateVo(String moduleName, DataSourceConfig dsc, PackageConfig pc,String... tableNamesInclude) {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //TODO 更换为你的目录
        gc.setOutputDir("/Users/sheng/workspace/github/mybatis/mybatisplus/codeGen/src/main/java");
        gc.setAuthor("shengfq");
        gc.setOpen(false);
        //默认不覆盖，如果文件存在，将不会再生成，配置true就是覆盖
        gc.setFileOverride(true);
        gc.setBaseResultMap(true);
        gc.setDateType(DateType.TIME_PACK);
        gc.setEntityName("%s" + "Vo");
        mpg.setGlobalConfig(gc);
        mpg.setDataSource(dsc);
        //数据库表配置
        // 包配置
        mpg.setPackageInfo(pc);
        // 策略配置 数据表
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setInclude(tableNamesInclude);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.entityTableFieldAnnotationEnable(true);

        strategy.isCapitalMode();
        strategy.setSkipView(true);
        strategy.setTablePrefix("zw_");
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        configCustomizedCodeTemplateVo(mpg);

        mpg.execute();

    }

    /**
     * 自定义VO类模板
     *
     * @param mpg
     */
    private void configCustomizedCodeTemplateVo(AutoGenerator mpg) {
        //配置 自定义模板
        TemplateConfig templateConfig = new TemplateConfig()
                .setEntity("templates/entityVo.java")
                .setController(null)
                .setMapper(null)
                .setService(null)
                .setServiceImpl(null)
                .setXml(null);//不生成xml;
        mpg.setTemplate(templateConfig);
    }

    /**
     * 自定义其他类模板
     *
     * @param mpg
     */
    private void configCustomizedCodeTemplate(AutoGenerator mpg) {
        //配置 自定义模板
        TemplateConfig templateConfig = new TemplateConfig()
                .setController("templates/controller.java")//指定Entity生成使用自定义模板
                .setEntity("templates/entity.java")
                .setMapper("templates/mapper.java")
                .setService("templates/service.java")
                .setServiceImpl("templates/serviceImpl.java")
                .setXml("templates/mapper.xml");//不生成xml

        mpg.setTemplate(templateConfig);
    }

}
