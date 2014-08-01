package com.dartmedia.felino.form;
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
import com.vaadin.ui.PopupDateField;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("ExamList")
public class ExamListView extends MVerticalLayout implements View {
//ExamListSvc data=new ExamListSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Exam date <p:calendar></p:calendar>Warehouse <p:selectOneListbox id="basic"> </p:selectOneListbox>Rack No <p:inputText />                                 In charge of rack <p:inputText />                                Item <p:inputText />                                                Exam code <p:inputText />                                Exam result <p:selectOneListbox id="basic"> </p:selectOneListbox>**/
StringBuffer sb = new StringBuffer();
sb.append("SELECT");
sb.append("TO_CHAR(SK.CHECK_DATE,");
sb.append("'YYYY/MM/DD')");
sb.append("AS");
sb.append("CHECK_DATE,");
sb.append("SC.CHECK_GB,");
sb.append("SK.GOODS_CODE,");
sb.append("SK.GOODSDT_CODE,");
sb.append("GD.GOODS_NAME,");
sb.append("GD.GOODSDT_INFO,");
sb.append("SK.WH_CODE,");
sb.append("SUBSTR(SK.RACK_CODE,");
sb.append("4)");
sb.append("AS");
sb.append("RACK_CODE,");
sb.append("SK.RACK_GRADE,");
sb.append("RC.RACK_GB,");
sb.append("SK.RACK_QTY,");
sb.append("SK.STOCKCHECK_QTY,");
sb.append("SK.STOCKCHECK_QTY");
sb.append("-");
sb.append("SK.RACK_QTY");
sb.append("AS");
sb.append("DIFF_QTY,");
sb.append("SK.RESULT_CODE,");
sb.append("SK.CHECK_CODE,");
sb.append("SK.RESULT_NOTE,");
sb.append("UR.USER_NAME,");
sb.append("SK.CTRL_YN,");
sb.append("SK.RESULT_CODE");
sb.append("FROM");
sb.append("TSTOCKCHECK");
sb.append("SK,");
sb.append("TSTOCKCHECKCODE");
sb.append("SC,");
sb.append("TRACKCODE");
sb.append("RC,");
sb.append("TUSER");
sb.append("UR,");
sb.append("TGOODS");
sb.append("GS,");
sb.append("TGOODSDT");
sb.append("GD");
sb.append("WHERE");
sb.append("SK.CHECK_CODE");
sb.append("=");
sb.append("SC.CHECK_CODE");
sb.append("AND");
sb.append("SK.RACK_CODE");
sb.append("=");
sb.append("RC.RACK_CODE");
sb.append("AND");
sb.append("SK.GOODS_CODE");
sb.append("=");
sb.append("GS.GOODS_CODE");
sb.append("AND");
sb.append("SK.GOODS_CODE");
sb.append("=");
sb.append("GD.GOODS_CODE");
sb.append("AND");
sb.append("SK.GOODSDT_CODE");
sb.append("=");
sb.append("GD.GOODSDT_CODE");
sb.append("AND");
sb.append("RC.RACK_MAN_ID");
sb.append("=");
sb.append("UR.USER_ID");
sb.append("AND");
sb.append("SK.CHECK_DATE");
sb.append(">=");
sb.append("TO_DATE('2013/11/01',");
sb.append("'YYYY/MM/DD')");
sb.append("AND");
sb.append("SK.CHECK_DATE");
sb.append("<");
sb.append("TO_DATE('2013/11/02',");
sb.append("'YYYY/MM/DD')");
sb.append("+");
sb.append("1");
sb.append("/*");
sb.append("AND");
sb.append("SK.CHECK_CODE");
sb.append("LIKE");
sb.append("#{check_code,");
sb.append("jdbcType=VARCHAR}||'%'");
sb.append("AND");
sb.append("SK.RACK_CODE");
sb.append("LIKE");
sb.append("#{rack_code,");
sb.append("jdbcType=VARCHAR}||'%'");
sb.append("AND");
sb.append("RC.RACK_MAN_ID");
sb.append("LIKE");
sb.append("#{rack_man_id,");
sb.append("jdbcType=VARCHAR}||'%'");
sb.append("AND");
sb.append("SK.RESULT_CODE");
sb.append("IN");
sb.append("(#{result_code1,");
sb.append("jdbcType=VARCHAR},");
sb.append("#{result_code2,");
sb.append("jdbcType=VARCHAR},");
sb.append("#{result_code3,");
sb.append("jdbcType=VARCHAR})");
sb.append("AND");
sb.append("GS.LGROUP");
sb.append("LIKE");
sb.append("#{lgroup,");
sb.append("jdbcType=VARCHAR}||'%'");
sb.append("AND");
sb.append("GS.MGROUP");
sb.append("LIKE");
sb.append("#{mgroup,");
sb.append("jdbcType=VARCHAR}||'%'");
sb.append("AND");
sb.append("GS.SGROUP");
sb.append("LIKE");
sb.append("#{sgroup,");
sb.append("jdbcType=VARCHAR}||'%'");
sb.append("AND");
sb.append("GS.DGROUP");
sb.append("LIKE");
sb.append("#{dgroup,");
sb.append("jdbcType=VARCHAR}||'%'");
sb.append("*/");
sb.append("AND");
sb.append("GS.GOODS_CODE");
sb.append("LIKE");
sb.append("'%100001%'");
sb.append("ORDER");
sb.append("BY");
sb.append("SK.GOODS_CODE");
sb.append(",");
sb.append("SK.GOODSDT_CODE");
sb.append(",");
sb.append("SK.WH_CODE");
sb.append("ASC");
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
        addComponents(isicontents);
MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
table.addContainerProperty("No", String.class,  null);
table.addContainerProperty("ITEM", String.class,  null);
table.addContainerProperty("No", String.class,  null);
//-------------------- Header Table ------------------------------
   isicontents.addComponents(table);
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
