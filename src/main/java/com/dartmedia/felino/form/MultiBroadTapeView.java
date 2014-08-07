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
@CDIView("MultiBroadTape")
public class MultiBroadTapeView extends MVerticalLayout implements View {
//MultiBroadTapeSvc data=new MultiBroadTapeSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/****/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("/*");
sb.append("Item");
sb.append("Code,");
sb.append("Item");
sb.append("Name,");
sb.append("Tape");
sb.append("Code,");
sb.append("Tape");
sb.append("Name,");
sb.append("Status,");
sb.append("Main,");
sb.append("Sale");
sb.append("Price,");
sb.append("Tape");
sb.append("Made");
sb.append("Date,");
sb.append("Special");
sb.append("Matter");
sb.append("*/");
sb.append("SELECT");
sb.append("/*");
sb.append("schedule.xml");
sb.append(":");
sb.append("broadcast.schedule.selectMultiBroadTapeList");
sb.append("by");
sb.append("MultiBroadTapeController");
sb.append("*/");
sb.append("TO_CHAR(A.BD_DATE,");
sb.append("'YYYY/MM/DD')");
sb.append("AS");
sb.append("BD_DATE,");
sb.append("A.MEDIA_CODE,");
sb.append("M.MEDIA_NAME,");
sb.append("TO_CHAR(A.BD_BTIME,");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("AS");
sb.append("BD_BTIME,");
sb.append("TO_CHAR(A.SALE_END_TIME,");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("AS");
sb.append("SALE_END_TIME,");
sb.append("A.PROG_CODE,");
sb.append("A.GOODS_CODE,");
sb.append("B.GOODS_NAME,");
sb.append("C.SALE_PRICE,");
sb.append("A.SALE_TG_QTY,");
sb.append("C.SALE_PRICE");
sb.append("*");
sb.append("A.SALE_TG_QTY");
sb.append("AS");
sb.append("PLAN_TG_SALE_PRICE,");
sb.append("A.SPEC_NOTE,");
sb.append("A.SEQ_NO,");
sb.append("A.MAIN_YN,");
sb.append("B.SQC_GB,");
sb.append("SYSDATE-1");
sb.append("AS");
sb.append("CON_DATE,");
sb.append("A.BD_FLAG,");
sb.append("''");
sb.append("AS");
sb.append("SIGN_CHECK,");
sb.append("B.MD_CODE,");
sb.append("TO_CHAR(A.BTIME,");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("AS");
sb.append("BTIME,");
sb.append("TO_CHAR(A.ETIME,");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("AS");
sb.append("ETIME,");
sb.append("F.TAPE_CODE,");
sb.append("D.TAPE_NAME,");
sb.append("F.SEQ_FRAME_NO,");
sb.append("TO_CHAR(D.MAKE_DATE,");
sb.append("'YYYY/MM/DD')");
sb.append("AS");
sb.append("MAKE_DATE,");
sb.append("D.SNO,");
sb.append("D.SNO1");
sb.append("FROM");
sb.append("TMULTIDTBROAD");
sb.append("A,");
sb.append("TGOODS");
sb.append("B,");
sb.append("TMULTIFRAMESCHE");
sb.append("F,");
sb.append("TGOODSPRICE");
sb.append("C,");
sb.append("TMEDIA");
sb.append("M,");
sb.append("TPGMTAPE");
sb.append("D");
sb.append("WHERE");
sb.append("A.GOODS_CODE");
sb.append("=");
sb.append("B.GOODS_CODE");
sb.append("AND");
sb.append("A.GOODS_CODE");
sb.append("=");
sb.append("C.GOODS_CODE");
sb.append("AND");
sb.append("A.SEQ_FRAME_NO");
sb.append("=");
sb.append("F.SEQ_FRAME_NO");
sb.append("AND");
sb.append("A.MEDIA_CODE");
sb.append("=");
sb.append("M.MEDIA_CODE");
sb.append("AND");
sb.append("F.TAPE_CODE");
sb.append("=");
sb.append("D.TAPE_CODE");
sb.append("AND");
sb.append("F.SEQ_FRAME_NO");
sb.append("=");
sb.append("101");
sb.append("AND");
sb.append("C.APPLY_DATE");
sb.append("=");
sb.append("(");
sb.append("SELECT");
sb.append("MAX(X.APPLY_DATE)");
sb.append("FROM");
sb.append("TGOODSPRICE");
sb.append("X");
sb.append("WHERE");
sb.append("X.GOODS_CODE");
sb.append("=");
sb.append("A.GOODS_CODE");
sb.append("AND");
sb.append("X.APPLY_DATE");
sb.append("GROUP");
sb.append("BY");
sb.append("X.GOODS_CODE");
sb.append(")");
sb.append("ORDER");
sb.append("BY");
sb.append("A.MAIN_YN");
sb.append("DESC,");
sb.append("A.GOODS_CODE");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new ComboBox("MNC SHOP"));
toolbar.addComponent(new Button("Broadcast Programing Table Coppying"));
toolbar.addComponent(new Button("Print"));



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
                new Header("Item Management(Tape) (MultiBroadTape)"
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
