package aystzh.com.base.core;

/**
 * 项目常量
 */
public final class ProjectConstant {
    public static final String BASE_PACKAGE = "aystzh.com.study";//生成代码所在的基础包名称，可根据自己公司的项目修改（注意：这个配置修改之后需要手工修改src目录项目默认的包路径，使其保持一致，不然会找不到类）

    public static final String CORE_PACKAGE = "aystzh.com";

    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".entity.security";//生成的Model所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";//生成的Mapper所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";//生成的Service所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";//生成的ServiceImpl所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller.security";//生成的Controller所在包

    public static final String MAPPER_INTERFACE_REFERENCE = CORE_PACKAGE + ".base.core.Mapper";//Mapper插件基础接口的完全限定名
}
