package com.dartmedia.felino.form;
import com.cware.back.common.BaseEntity;
import com.dartmedia.felino.gSqlContainer;
import com.dartmedia.felino.fgetsql;
import com.dartmedia.felino.gSqlContainer;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import org.vaadin.maddon.fields.MTable;
import com.vaadin.event.LayoutEvents;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TreeTable;
import com.vaadin.navigator.View;
import com.vaadin.ui.RichTextArea;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.sql.SQLException;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.fields.MValueChangeEvent;
import org.vaadin.maddon.fields.MValueChangeListener;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("GoodsDescribe")
public class GoodsDescribeView extends MVerticalLayout implements View {
//GoodsDescribeSvc data=new GoodsDescribeSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Item: <p:inputText />//select goods_code, goods_name, TCODE_NAME('B032', SALE_GB) AS SALE_NAME from TGOODS **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("");
sb.append("SELECT    /* goods.xml : manage.goods.selectGoodsDescribe by GoodsDescribeController */");
sb.append("              B.DESCRIBE_CODE");
sb.append("             ,B.DESCRIBE_TITLE");
sb.append("             ,A.DESCRIBE_EXT");
sb.append("             ,A.WEB_FLAG");
sb.append("             ,A.REQUIRED_YN");
sb.append("             ,B.WEB_FLAG AS FIRST_WEB_FLAG");
sb.append("             ,A.GOODS_CODE");
sb.append("             ,B.SORT_SEQ");
sb.append("             ,TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD') AS INSERT_DATE");
sb.append("             ,A.INSERT_ID");
sb.append("             ,TO_CHAR(A.MODIFY_DATE, 'YYYY/MM/DD') AS MODIFY_DATE");
sb.append("             ,A.MODIFY_ID");
sb.append("        FROM TDESCRIBE A,");
sb.append("             TDESCRIBECODE B");
sb.append("       WHERE A.DESCRIBE_CODE = B.DESCRIBE_CODE");
sb.append("         AND A.GOODS_CODE    = '100013'");
sb.append("    ORDER BY A.DESCRIBE_CODE ASC, B.DESCRIBE_CODE;");
sb.append("    ");
sb.append("SELECT /* goods.xml : manage.goods.selectExistGoodsDescribe by GoodsDescribeController */");
sb.append("         GOODS_CODE,");
sb.append("         DESCRIBE_CODE,");
sb.append("         DESCRIBE_TITLE,");
sb.append("         DESCRIBE_NOTE,");
sb.append("         DESCRIBE_EXT,");
sb.append("         WEB_FLAG,");
sb.append("         REQUIRED_YN,");
sb.append("         INSERT_DATE");
sb.append("          FROM (SELECT GOODS_CODE,");
sb.append("                       DESCRIBE_CODE,");
sb.append("                       DESCRIBE_TITLE,");
sb.append("                       DESCRIBE_NOTE,");
sb.append("                       WEB_FLAG,");
sb.append("                       DESCRIBE_EXT,");
sb.append("                       REQUIRED_YN,");
sb.append("                       TO_CHAR(INSERT_DATE, 'YYYY/MM/DD') AS INSERT_DATE");
sb.append("                  FROM TDESCRIBE");
sb.append("                 WHERE GOODS_CODE = '100013'");
sb.append("                UNION ALL");
sb.append("                SELECT '100013' AS GOODS_CODE,");
sb.append("                       DESCRIBE_CODE,");
sb.append("                       DESCRIBE_TITLE,");
sb.append("                       '' AS DESCRIBE_NOTE,");
sb.append("                       WEB_FLAG,");
sb.append("                       TO_CLOB('') AS DESCRIBE_EXT,");
sb.append("                       REQUIRED_YN,");
sb.append("                       '' AS INSERT_DATE");
sb.append("                  FROM TDESCRIBECODE");
sb.append("                 WHERE USE_YN = '1'");
sb.append("                   AND DESCRIBE_CODE NOT IN");
sb.append("                       (SELECT DESCRIBE_CODE");
sb.append("                          FROM TDESCRIBE");
sb.append("                         WHERE GOODS_CODE = '100013'))");
sb.append("         ORDER BY DESCRIBE_CODE ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new TextField("Item"));
toolbar.addComponent(new Button("Description Copy"));


//-------------------- Header  ------------------------------
MHorizontalLayout toolmenu;
toolmenu = new MHorizontalLayout();
toolmenu.addComponent(new Button("Ret"));
toolmenu.addComponent(new Button("Ins"));
toolmenu.addComponent(new Button("Del"));
toolmenu.addComponent(new Button("Save"));
toolmenu.addComponent(new Button("Print"));
toolmenu.addComponent(new Button("XLS"));
        addComponents(
                new Header("Item Description (GoodsDescribe)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
//        addComponents(isicontents);
//MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
//List<Tenterprise> findAll = cf.findAll();
//MTable<Tenterprise> table=new MTable<Tenterprise>(Tenterprise.class).withProperties("entpName");
//table.setBeans(findAll);
//table.addMValueChangeListener(new MValueChangeListener<Tdescribecode>() {
//    @Override
//    public void valueChange(MValueChangeEvent<Tdescribecode> event) {
//    Notification.show("ss");
//    form.setEntity(event.getValue());
//    }
//    });
//table.addContainerProperty("No", String.class,  null);
//-------------------- Header Table ------------------------------
//   isicontents.addComponents(table);
try{
            SimpleJDBCConnectionPool connectionPool = new SimpleJDBCConnectionPool(
             "oracle.jdbc.OracleDriver",BaseEntity.jdbc,
             BaseEntity.user,BaseEntity.pass,2,5);
             SQLContainer container;
              container = new SQLContainer(new FreeformQuery(
              sb.toString(),connectionPool,"AD_MENU_ID"));
             // MTable table= new MTable("MENU",container);
               MTable table = new MTable();
               table.setContainerDataSource(container);
table.addMValueChangeListener(new MValueChangeListener() {
    @Override
    public void valueChange(MValueChangeEvent event) {
    Notification.show("ss");
//    form.setEntity(event.getValue());
    }
   });
              addComponents(table);
 } catch (SQLException e) {
     e.printStackTrace();
  Notification.show(e.getMessage());
     RichTextArea rtarea = new RichTextArea();
     rtarea.setValue(sb.toString());
      addComponents(rtarea);
}
        addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent event) {
            }
        });
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
