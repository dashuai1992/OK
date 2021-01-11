package com.very.ok;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MybatisPlusGenerator {

    /**
     * 数据库信息
     */
    private static final String DB_URL="jdbc:mysql://localhost:3307/yds?serverTimezone=GMT%2B8";
    private static final String DB_DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String DB_USER="root";
    private static final String DB_PWD="dadada";
    private static DbType DB_TYPE=DbType.MYSQL;

	/**
	 * 要生成代码的表
     * , 逗号分隔
	 */
	private static final String TABLE_NAMES="sys_role,sys_user,sys_user_role";

	/**
	 * 这些个表的分组模块
	 */
    private static String MODULE_NAME="sys";

	/**
	 * 通过修改该参数，生成到不同的工程模块中
	 */
    // private static String PROJECT_NAME="repository/";
    private static String PROJECT_NAME="";

    /**
     * 父路径
     */
    private static String PARENT="com.very.ok";

    /**
     * 作者，类注释
     */
    private static final String AUTHOR = "YDS";


	
	public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        gc.setFileOverride(true);
        mpg.setGlobalConfig(gc);

        // 代码生成，只要 entity,mapper,xml,service 其它不要，并且不使用默认的
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        templateConfig.setXml(null);
        templateConfig.setMapper(null);
        templateConfig.setEntity(null);
        templateConfig.setEntityKt(null);
        mpg.setTemplate(templateConfig);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DB_URL);
        dsc.setDriverName(DB_DRIVER);
        dsc.setUsername(DB_USER);
        dsc.setPassword(DB_PWD);
        dsc.setDbType(DB_TYPE);
        mpg.setDataSource(dsc);
        
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(MODULE_NAME); //模块名
        pc.setParent(PARENT);
        pc.setController("");
        mpg.setPackageInfo(pc);
        
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {}

            @Override
            public void initTableMap(TableInfo tableInfo) {
                tableInfo.setServiceImplName(tableInfo.getEntityName() + "Service");
            }
        };

        // 这样写是为了方便自定义路径
        List<FileOutConfig> focList = new ArrayList<>();
        String work_space = System.getProperty("user.dir");
        // xml
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return work_space + "/"+PROJECT_NAME+"/src/main/resources" + "/mapper/" + MODULE_NAME + "/" + tableInfo.getXmlName() + StringPool.DOT_XML;
            }
        });

        // entity
        focList.add(new FileOutConfig("/templates/entity.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                    return work_space +PROJECT_NAME+"/src/main/java/"+PARENT.replace(".","/")+"/"+MODULE_NAME+"/entity/" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        });

        // mapper
        focList.add(new FileOutConfig("/templates/mapper.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                   // return work_space + "/"+PROJECT_NAME+"/src/main/java/"+PARENT.replace(".","/")+"/mapper/" + MODULE_NAME + "/"  + tableInfo.getMapperName() + StringPool.DOT_JAVA;
            	 return work_space +PROJECT_NAME+"/src/main/java/"+PARENT.replace(".","/")+"/"+MODULE_NAME+"/mapper/" + tableInfo.getMapperName() + StringPool.DOT_JAVA;
            }
        });

        // service , 使用自定义模板，比较默认模板区别：非IService - ServiceImpl
        focList.add(new FileOutConfig("/templates/service.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                    //return work_space + "/"+PROJECT_NAME+"/src/main/java/"+PARENT.replace(".","/")+"/service/" + MODULE_NAME + "/" + tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
            	return work_space +PROJECT_NAME+"/src/main/java/"+PARENT.replace(".","/")+"/"+MODULE_NAME +"/service/" + tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(focList);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(TABLE_NAMES.split(","));//对那一张表生成代码
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.no_change);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true);
        mpg.setStrategy(strategy);

        // 重写了ConfigBuilder类中getPackageInfo的方法，只是为了路径可以更容易自定义些
        ConfigBuilder configBuilder = new ConfigBuilder(pc, dsc, strategy, templateConfig, gc) {
            @Override
            public Map<String, String> getPackageInfo() {
                Map<String, String> packageInfo = super.getPackageInfo();
                // packageInfo.put("Entity",PARENT+".entity."+pc.getModuleName());
                // packageInfo.put("Mapper",PARENT+".mapper."+pc.getModuleName());
                // packageInfo.put("Service",PARENT+".service."+pc.getModuleName());
                return packageInfo;
            }
        };
        cfg.setConfig(configBuilder);

        mpg.setCfg(cfg);
        mpg.setConfig(configBuilder);

        // 执行
        mpg.execute();
	}

}

