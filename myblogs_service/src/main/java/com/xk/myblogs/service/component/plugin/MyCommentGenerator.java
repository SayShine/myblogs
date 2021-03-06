package com.xk.myblogs.service.component.plugin;

import com.xk.myblogs.service.config.dynamicDataSource.DataSourceType;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;

import javax.xml.bind.DatatypeConverter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

/**
 * @author xiongkai
 * @date 2020年01月12日 23时43分22秒
 **/
public class MyCommentGenerator implements CommentGenerator {
    private Properties properties = new Properties();
    private boolean suppressDate = false;
    private boolean suppressAllComments = false;
    private boolean addRemarkComments = false;
    private SimpleDateFormat dateFormat;
    private static final String EXAMPLE_SUFFIX="Example";
    private static final String MODEL_SUFFIX="entity";
    private static final String MAPPER_SUFFIX="mapper";
    private static final String API_MODEL_PROPERTY_FULL_CLASS_NAME="io.swagger.annotations.ApiModelProperty";
    private static final String ANNOTATION_DATASOURCE_FULL_CLASS_NAME="com.xk.myblogs.common.annotion.DataSource";

    public MyCommentGenerator() {
    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        //处于entity包内并且不是example的实体类
        if(compilationUnit.getType().getFullyQualifiedName().contains(MODEL_SUFFIX)
                &&!compilationUnit.getType().getFullyQualifiedName().contains(EXAMPLE_SUFFIX)){
            compilationUnit.addImportedType(new FullyQualifiedJavaType(API_MODEL_PROPERTY_FULL_CLASS_NAME));
        }

        //处于mapper包内  并且是interface接口的  添加数据源注解
        if(compilationUnit.getType().getFullyQualifiedName().contains(MAPPER_SUFFIX)
                && compilationUnit.getClass().toString().contains("Interface")){
            compilationUnit.addImportedType(new FullyQualifiedJavaType(ANNOTATION_DATASOURCE_FULL_CLASS_NAME));
        }



    }

    @Override
    public void addComment(XmlElement xmlElement) {
        if (!this.suppressAllComments) {
            xmlElement.addElement(new TextElement("<!--"));
            StringBuilder sb = new StringBuilder();
            sb.append("  WARNING - ");
            sb.append("@mbg.generated");
            sb.append(" MyBatis Generator Create");
            xmlElement.addElement(new TextElement(sb.toString()));
            String s = this.getDateString();
            if (s != null) {
                sb.setLength(0);
                sb.append("  Create Date  ");
                sb.append(s);
                sb.append('.');
                xmlElement.addElement(new TextElement(sb.toString()));
            }

            xmlElement.addElement(new TextElement("-->"));
        }
    }

    @Override
    public void addRootComment(XmlElement rootElement) {
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);
        this.suppressDate = StringUtility.isTrue(properties.getProperty("suppressDate"));
        this.suppressAllComments = StringUtility.isTrue(properties.getProperty("suppressAllComments"));
        this.addRemarkComments = StringUtility.isTrue(properties.getProperty("addRemarkComments"));
        String dateFormatString = properties.getProperty("dateFormat");
        if (StringUtility.stringHasValue(dateFormatString)) {
            this.dateFormat = new SimpleDateFormat(dateFormatString);
        }

    }

    protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
        StringBuilder sb = new StringBuilder(" * ");
        sb.append("@mbg.generated");
        if (markAsDoNotDelete) {
            sb.append(" MyBatis Generator 自动生成Bean，自定义属性会覆盖");
        }

        String s = this.getDateString();
        if (s != null) {
            sb.append(' ');
            sb.append(s);
        }

        javaElement.addJavaDocLine(sb.toString());
    }

    protected String getDateString() {
        if (this.suppressDate) {
            return null;
        } else {
            return this.dateFormat != null ? this.dateFormat.format(new Date()) : (new Date()).toString();
        }
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        if (!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            innerClass.addJavaDocLine("/**");
            sb.append(" * TABLE ");
            sb.append(introspectedTable.getFullyQualifiedTable());
            innerClass.addJavaDocLine(sb.toString());
            this.addJavadocTag(innerClass, false);
            innerClass.addJavaDocLine(" */");
        }
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        if (!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            innerClass.addJavaDocLine("/**");
            sb.append(" * TABLE ");
            sb.append(introspectedTable.getFullyQualifiedTable());
            innerClass.addJavaDocLine(sb.toString());
            this.addJavadocTag(innerClass, markAsDoNotDelete);
            innerClass.addJavaDocLine(" */");
        }
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (!this.suppressAllComments && this.addRemarkComments) {
            topLevelClass.addJavaDocLine("/**");
            String remarks = introspectedTable.getRemarks();
            if (this.addRemarkComments && StringUtility.stringHasValue(remarks)) {
                topLevelClass.addJavaDocLine(" * ");
                String[] remarkLines = remarks.split(System.getProperty("line.separator"));
                String[] var5 = remarkLines;
                int var6 = remarkLines.length;

                for (int var7 = 0; var7 < var6; ++var7) {
                    String remarkLine = var5[var7];
                    topLevelClass.addJavaDocLine(" *   " + remarkLine);
                }
            }

            topLevelClass.addJavaDocLine(" *");
            StringBuilder sb = new StringBuilder();
            sb.append(" * TABLE  ");
            sb.append(introspectedTable.getFullyQualifiedTable());
            topLevelClass.addJavaDocLine(sb.toString());
            topLevelClass.addJavaDocLine(" * MyBatis Generator Create");
            topLevelClass.addJavaDocLine(" */");
        }
    }

    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
        if (!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            innerEnum.addJavaDocLine("/**");
            sb.append(" *  ");
            sb.append(introspectedTable.getFullyQualifiedTable());
            innerEnum.addJavaDocLine(sb.toString());
            this.addJavadocTag(innerEnum, false);
            innerEnum.addJavaDocLine(" */");
        }
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
//        if (!this.suppressAllComments) {
//            field.addJavaDocLine("/**");
//            field.addJavaDocLine(" * " + introspectedColumn.getActualColumnName());
//            String remarks = introspectedColumn.getRemarks();
//            if (this.addRemarkComments && StringUtility.stringHasValue(remarks)) {
//                String[] remarkLines = remarks.split(System.getProperty("line.separator"));
//                String[] var6 = remarkLines;
//                int var7 = remarkLines.length;
//
//                for (int var8 = 0; var8 < var7; ++var8) {
//                    String remarkLine = var6[var8];
//                    field.addJavaDocLine(" * " + remarkLine);
//                }
//            }
//
//            field.addJavaDocLine(" * ");
//            StringBuilder sb = new StringBuilder();
//            sb.append(" * ");
//            sb.append("WARNING - ");
//            sb.append("@mbg.generated");
//            sb.append(" MyBatis Generator Create");
//            field.addJavaDocLine(sb.toString());
//            field.addJavaDocLine(" */");
//
//        }
        if (!this.suppressAllComments) {
            String remarks = introspectedColumn.getRemarks();
            if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
                if(remarks.contains("\"")){
                    remarks = remarks.replace("\"","'");
                }
                //给model的字段添加swagger注解
                field.addJavaDocLine("@ApiModelProperty(value = \""+remarks+"\")");
            }
        }

    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {

        String msg = this.createMapperAnnotation(method);
        if (!StringUtils.isBlank(msg)) {
            method.addJavaDocLine("/**");
            method.addJavaDocLine(" * " + msg);
            method.addJavaDocLine(" * ");
            StringBuilder sb = new StringBuilder();
            sb.append(" * ");
            sb.append("WARNING - ");
            sb.append("@mbg.generated");
            sb.append(" MyBatis Generator Create");
            method.addJavaDocLine(sb.toString());
            method.addJavaDocLine(" */");

            String value = DataSourceType.getDataBaseType().getValue();
            method.addJavaDocLine("@DataSource(\""+value+"\")");
        }
    }

    private String createMapperAnnotation(Method method) {
        String name = method.getName();
        String msg = "";
        byte var5 = -1;
        switch (name.hashCode()) {
            case -1973096654:
                if ("updateByExampleSelective".equals(name)) {
                    var5 = 8;
                }
                break;
            case -1333471280:
                if ("selectByPrimaryKey".equals(name)) {
                    var5 = 7;
                }
                break;
            case -1183792455:
                if ("insert".equals(name)) {
                    var5 = 3;
                }
                break;
            case -812178569:
                if ("selectByExample".equals(name)) {
                    var5 = 5;
                }
                break;
            case -88340525:
                if ("updateByPrimaryKeyWithBLOBs".equals(name)) {
                    var5 = 13;
                }
                break;
            case 252811705:
                if ("selectByExampleWithBLOBs".equals(name)) {
                    var5 = 6;
                }
                break;
            case 513681320:
                if ("deleteByExample".equals(name)) {
                    var5 = 1;
                }
                break;
            case 627582022:
                if ("updateByExampleWithBLOBs".equals(name)) {
                    var5 = 10;
                }
                break;
            case 646369187:
                if ("insertSelective".equals(name)) {
                    var5 = 4;
                }
                break;
            case 839227903:
                if ("deleteByPrimaryKey".equals(name)) {
                    var5 = 2;
                }
                break;
            case 936973898:
                if (name.equals("updateByExample")) {
                    var5 = 9;
                }
                break;
            case 1124438045:
                if (name.equals("updateByPrimaryKey")) {
                    var5 = 12;
                }
                break;
            case 1522658500:
                if (name.equals("countByExample")) {
                    var5 = 0;
                }
                break;
            case 1605948095:
                if (name.equals("updateByPrimaryKeySelective")) {
                    var5 = 11;
                }
                break;
            default:
                break;
        }
        switch (var5) {
            case 0:
                msg = "按条件计数\n\t * @param example 条件";
                break;
            case 1:
                msg = "按条件删除\n\t * @param example 条件";
                break;
            case 2:
                msg = "按主键删除";
                break;
            case 3:
                msg = "插入数据 所有字段\n     * @param record 操作 实体 bean 对象";
                break;
            case 4:
                msg = "插入数据 值不为 null 的字段\n     * @param record 操作 实体 bean 对象";
                break;
            case 5:
                msg = "按条件查询\n\t * @param example 条件";
                break;
            case 6:
                msg = "按条件查询（返回包含BOLD字段）\n\t * @param example 条件";
                break;
            case 7:
                msg = "按主键查询";
                break;
            case 8:
                msg = "按条件更新值不为 null 的字段\n     * @param record 操作 实体 bean 对象\n\t * @param example 条件";
                break;
            case 9:
                msg = "按条件更新所有字段\n     * @param record 操作 实体 bean 对象\n\t * @param example 条件";
                break;
            case 10:
                msg = "按条件更新所有字段（更新包含BOLD字段）\n     * @param record 操作 实体 bean 对象\n\t * @param example 条件";
                break;
            case 11:
                msg = "按主键更新值不为 null 的字段\n     * @param record 操作 实体 bean 对象";
                break;
            case 12:
                msg = "按主键更新所有字段\n     * @param record 操作 实体 bean 对象";
                break;
            case 13:
                msg = "按主键更新所有字段（更新包含BLOB字段）\n     * @param record 操作 实体 bean 对象";
                break;
            default:
                break;

        }

        return msg;
    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
        imports.add(new FullyQualifiedJavaType("javax.annotation.Generated"));
        String comment = "Source Table: " + introspectedTable.getFullyQualifiedTable().toString();
        method.addAnnotation(this.getGeneratedAnnotation(comment));
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
        imports.add(new FullyQualifiedJavaType("javax.annotation.Generated"));
        String comment = "Source field: " + introspectedTable.getFullyQualifiedTable().toString() + "." + introspectedColumn.getActualColumnName();
        method.addAnnotation(this.getGeneratedAnnotation(comment));
    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
        imports.add(new FullyQualifiedJavaType("javax.annotation.Generated"));
        String comment = "Source Table: " + introspectedTable.getFullyQualifiedTable().toString();
        field.addAnnotation(this.getGeneratedAnnotation(comment));
    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
        imports.add(new FullyQualifiedJavaType("javax.annotation.Generated"));
        String comment = "Source field: " + introspectedTable.getFullyQualifiedTable().toString() + "." + introspectedColumn.getActualColumnName();
        field.addAnnotation(this.getGeneratedAnnotation(comment));
    }

    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
        imports.add(new FullyQualifiedJavaType("javax.annotation.Generated"));
        String comment = "Source Table: " + introspectedTable.getFullyQualifiedTable().toString();
        innerClass.addAnnotation(this.getGeneratedAnnotation(comment));
    }

    private String getGeneratedAnnotation(String comment) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("@Generated(");
        if (this.suppressAllComments) {
            buffer.append('"');
        } else {
            buffer.append("value=\"");
        }

        buffer.append(MyBatisGenerator.class.getName());
        buffer.append('"');
        if (!this.suppressDate && !this.suppressAllComments) {
            buffer.append(", date=\"");
            buffer.append(DatatypeConverter.printDateTime(Calendar.getInstance()));
            buffer.append('"');
        }

        if (!this.suppressAllComments) {
            buffer.append(", comments=\"");
            buffer.append(comment);
            buffer.append('"');
        }

        buffer.append(')');
        return buffer.toString();
    }
}