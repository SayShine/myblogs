package com.xk.myblogs.service.config.dynamicDataSource;

/**
 * @author: tian
 * @date: 2020/7/28 23:54
 */
public class DataSourceType {

    //内部枚举类，用于选择特定的数据类型
    public enum DataBaseType{

        /**
         * myblog数据源
         */
        MYBLOG("myblog"),
        /**
         * tscxk数据源
         */
        TSCXK("tscxk"),
        /**
         * ruoyi数据源
         */
        RYVUE("ryvue");

        private String value;

        private DataBaseType(String ryvue) {
            this.value = ryvue;
        }

        public String getValue(){
            return value;

        }
    }

    //使用ThreadLocal保证线程安全
    private static final ThreadLocal<DataBaseType> TYPE = new ThreadLocal<>();


    //往当前线程设置数据源类型
    public static void setDataBaseType(DataBaseType dataBaseType){
        if(dataBaseType == null){
            throw new NullPointerException();
        }
        TYPE.set(dataBaseType);
    }

    //获取数据源类型
    public static DataBaseType getDataBaseType(){
        DataBaseType dataBaseType = TYPE.get() == null ? DataBaseType.MYBLOG : TYPE.get();
        return dataBaseType;
    }


    //清空数据源
    public static void clearDataBaseType(){
        TYPE.remove();
    }

}
