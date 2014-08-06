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
@CDIView("ExamList")
public class ExamListView extends MVerticalLayout implements View {
//ExamListSvc data=new ExamListSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Exam date <p:calendar></p:calendar>Warehouse <p:selectOneListbox id="basic"> </p:selectOneListbox>Rack No <p:inputText />                                 In charge of rack <p:inputText />                                Item <p:inputText />                                                Exam code <p:inputText />                                Exam result <p:selectOneListbox id="basic"> </p:selectOneListbox>**/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("");
sb.append("SELECT");
sb.append("            TO_CHAR(SK.CHECK_DATE, 'YYYY/MM/DD') AS CHECK_DATE,");
sb.append("            SC.CHECK_GB,");
sb.append("            SK.GOODS_CODE,");
sb.append("            SK.GOODSDT_CODE,");
sb.append("            GD.GOODS_NAME,");
sb.append("            GD.GOODSDT_INFO,");
sb.append("            SK.WH_CODE,");
sb.append("            SUBSTR(SK.RACK_CODE, 4) AS RACK_CODE,");
sb.append("            SK.RACK_GRADE,");
sb.append("            RC.RACK_GB,");
sb.append("            SK.RACK_QTY,");
sb.append("            SK.STOCKCHECK_QTY,");
sb.append("            SK.STOCKCHECK_QTY - SK.RACK_QTY AS DIFF_QTY,");
sb.append("            SK.RESULT_CODE,");
sb.append("            SK.CHECK_CODE,");
sb.append("            SK.RESULT_NOTE,");
sb.append("            UR.USER_NAME,");
sb.append("            SK.CTRL_YN, SK.RESULT_CODE");
sb.append("        FROM");
sb.append("            TSTOCKCHECK SK,");
sb.append("            TSTOCKCHECKCODE SC,");
sb.append("            TRACKCODE RC,");
sb.append("            TUSER UR,");
sb.append("            TGOODS GS,");
sb.append("            TGOODSDT GD");
sb.append("        WHERE");
sb.append("            SK.CHECK_CODE   = SC.CHECK_CODE");
sb.append("        AND SK.RACK_CODE    = RC.RACK_CODE");
sb.append("        AND SK.GOODS_CODE   = GS.GOODS_CODE");
sb.append("        AND SK.GOODS_CODE   = GD.GOODS_CODE");
sb.append("        AND SK.GOODSDT_CODE = GD.GOODSDT_CODE");
sb.append("        AND RC.RACK_MAN_ID  = UR.USER_ID");
sb.append("        AND SK.CHECK_DATE  >= TO_DATE('2013/11/01', 'YYYY/MM/DD')");
sb.append("        AND SK.CHECK_DATE   < TO_DATE('2013/11/02', 'YYYY/MM/DD') + 1");
sb.append("/*        AND SK.CHECK_CODE   LIKE #{check_code, jdbcType=VARCHAR}||'%'");
sb.append("        AND SK.RACK_CODE    LIKE #{rack_code, jdbcType=VARCHAR}||'%'");
sb.append("        AND RC.RACK_MAN_ID  LIKE #{rack_man_id, jdbcType=VARCHAR}||'%'");
sb.append("        AND SK.RESULT_CODE  IN (#{result_code1, jdbcType=VARCHAR},");
sb.append("                                #{result_code2, jdbcType=VARCHAR},");
sb.append("                                #{result_code3, jdbcType=VARCHAR})");
sb.append("        AND GS.LGROUP       LIKE #{lgroup, jdbcType=VARCHAR}||'%'");
sb.append("        AND GS.MGROUP       LIKE #{mgroup, jdbcType=VARCHAR}||'%'");
sb.append("        AND GS.SGROUP       LIKE #{sgroup, jdbcType=VARCHAR}||'%'");
sb.append("        AND GS.DGROUP       LIKE #{dgroup, jdbcType=VARCHAR}||'%' */");
sb.append("        AND GS.GOODS_CODE   LIKE '%100001%'");
sb.append("   ORDER BY SK.GOODS_CODE , SK.GOODSDT_CODE , SK.WH_CODE ASC ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------

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
                new Header("Check Stock Report (ExamList)"
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
             "dartmedia", "dartmedia",2,5);
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
