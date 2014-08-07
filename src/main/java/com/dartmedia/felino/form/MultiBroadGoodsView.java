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
@CDIView("MultiBroadGoods")
public class MultiBroadGoodsView extends MVerticalLayout implements View {
//MultiBroadGoodsSvc data=new MultiBroadGoodsSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/****/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("");
sb.append("/* Item Code, Item Name, Schedule Type, Seq, Main, Sale Price, Special Matter");
sb.append("*/");
sb.append("SELECT /* schedule.xml : broadcast.schedule.selectMultiBroadGoodsList by MultiBroadGoodsController */");
sb.append("                    A.GOODS_CODE,");
sb.append("                    B.GOODS_NAME,");
sb.append("                    A.BD_FLAG,");
sb.append("                    A.MAIN_YN,");
sb.append("                    A.SEQ_NO,");
sb.append("                    C.SALE_PRICE,");
sb.append("                    A.SALE_TG_QTY,");
sb.append("                    C.SALE_PRICE * A.SALE_TG_QTY AS PLAN_TG_SALE_PRICE,");
sb.append("                    A.SPEC_NOTE,");
sb.append("                    TO_CHAR(A.BD_DATE, 'YYYY/MM/DD')             AS BD_DATE,");
sb.append("                    TO_CHAR(A.BD_BTIME, 'YYYY/MM/DD HH24:MI:SS') AS BD_BTIME,");
sb.append("                    TO_CHAR(A.BTIME, 'YYYY/MM/DD HH24:MI:SS')    AS BTIME,");
sb.append("                    TO_CHAR(A.ETIME, 'YYYY/MM/DD HH24:MI:SS')    AS ETIME,");
sb.append("                    A.PROG_CODE,");
sb.append("                    A.SEQ_FRAME_NO,");
sb.append("                    A.MEDIA_CODE,");
sb.append("                    DECODE(SIGN(A.BTIME - (SYSDATE + 10/1440)),-1,0,1) AS MODI_YN");
sb.append("               FROM TMULTIDTBROAD A,");
sb.append("                    TGOODS B,");
sb.append("                    TGOODSPRICE C");
sb.append("              WHERE A.GOODS_CODE = B.GOODS_CODE");
sb.append("                AND A.GOODS_CODE = C.GOODS_CODE");
sb.append("                AND C.APPLY_DATE <> A.BD_BTIME");
sb.append("                AND (C.GOODS_CODE, C.APPLY_DATE) = ( SELECT X.GOODS_CODE, MAX(X.APPLY_DATE)");
sb.append("                                                       FROM TGOODSPRICE  X");
sb.append("                                                      WHERE X.GOODS_CODE = A.GOODS_CODE");
sb.append("                                                        AND X.APPLY_DATE <> A.BD_BTIME");
sb.append("                                                      GROUP BY X.GOODS_CODE )");
sb.append("                AND A.SEQ_FRAME_NO = 'seq_frame_no'");
sb.append("                      ORDER BY A.MAIN_YN DESC, A.SEQ_NO   ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new PopupDateField("Pgm Date"));
toolbar.addComponent(new ComboBox("My ComboBox"));
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new Button("Program Selection"));



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
                new Header("Item Management(Live) (MultiBroadGoods)"
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
