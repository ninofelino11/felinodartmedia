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
@CDIView("MultiGoodsBroadList")
public class MultiGoodsBroadListView extends MVerticalLayout implements View {
//MultiGoodsBroadListSvc data=new MultiGoodsBroadListSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/****/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("");
sb.append("/* PGM Date, Program, PGM Type, Item Code, Sale Type, MD Name, Speciall Matter, Start - Close, PD/SH, Main, Item Name, Stock, L Grp, Vendor Name, Guest/Model");
sb.append("   */  ");
sb.append("   SELECT  /* schedule.xml : broadcast.schedule.selectMultiGoodsBroadList by MultiGoodsBroadList */");
sb.append("                TO_CHAR(A.BD_DATE,'YYYY/MM/DD')   AS BD_DATE,");
sb.append("                TO_CHAR(A.BD_BTIME,'HH24:MI')     AS BD_BTIME,");
sb.append("                TO_CHAR(A.BTIME,'HH24:MI')        AS BTIME,");
sb.append("                TO_CHAR(A.ETIME,'HH24:MI')        AS ETIME,");
sb.append("                A.PROG_CODE         AS PROG_CODE,");
sb.append("                A.PROG_NAME         AS PROG_NAME,");
sb.append("                MAX(A.PD)           AS PD,");
sb.append("                MAX(A.SHOWHOST)     AS SHOW_HOST,");
sb.append("                MAX(A.LIVE_NAME)    AS LIVE_NAME,");
sb.append("                MAX(A.MAIN_YN)      AS MAIN_YN,");
sb.append("                A.SET_GOODS_CODE    AS SET_GOODS_CODE,");
sb.append("                A.SET_GOODS_NAME    AS SET_GOODS_NAME,");
sb.append("                A.GOODS_CODE        AS GOODS_CODE,");
sb.append("                MAX(K.GOODS_NAME)   AS GOODS_NAME,");
sb.append("                DECODE(MAX(A.SET_YN), '1', MAX(A.IN_GOODS_PRICE), MAX(F.SALE_PRICE)) AS SALE_PRICE,");
sb.append("                DECODE(MAX(K.DELY_TYPE), '10', SUM(NVL(J.AQTY,0)) - SUM(NVL(I.ORDER_QTY,0)) - SUM(NVL(I.OUT_PLAN_QTY,0)), 0) AS STOCK,");
sb.append("                MAX(A.SALE_TG_QTY)  AS SALE_TG_QTY,");
sb.append("                MAX(A.SALE_TG_QTY) * DECODE(MAX(A.SET_YN), '1', MAX(A.IN_GOODS_PRICE), MAX(F.SALE_PRICE))  AS SALE_TG_PRICE,");
sb.append("                MAX(A.SALE_GB)      AS SALE_GB,");
sb.append("                MAX(A.LGROUP)       AS LGROUP,");
sb.append("                MAX(A.LGROUP_NAME)  AS LGROUP_NAME,");
sb.append("                MAX(B.MD_NAME)      AS MD_NAME,");
sb.append("                MAX(H.ENTP_NAME)    AS ENTP_NAME,");
sb.append("                MAX(A.SPEC_NOTE)    AS SPEC_NOTE,");
sb.append("                MAX(A.GUEST)        AS GUEST,");
sb.append("                MAX(A.MODEL)        AS MODEL,");
sb.append("                A.SEQ_NO            AS SEQ_NO,");
sb.append("                MAX(A.SQC_GB)       AS SQC_GB,");
sb.append("                MAX(K.DELY_TYPE)    AS DELY_TYPE,");
sb.append("                MAX(A.BROAD_YN)     AS BROAD_YN,");
sb.append("                MAX(A.PDIN_YN)      AS PDIN_YN,");
sb.append("                MAX(A.SET_YN)       AS SET_YN");
sb.append("        FROM (");
sb.append("          SELECT  A.BD_DATE       AS BD_DATE,");
sb.append("                  A.BD_BTIME      AS BD_BTIME,");
sb.append("                  G.BD_BTIME         AS BTIME,");
sb.append("                  G.BD_ETIME         AS ETIME,");
sb.append("                  A.PROG_CODE     AS PROG_CODE,");
sb.append("                  B.PROG_NAME     AS PROG_NAME,");
sb.append("                  U1.USER_NAME||' '||NVL(U2.USER_NAME,'') AS PD,");
sb.append("                  FUN_STAFF_NAME('06', G.SEQ_FRAME_NO) SHOWHOST,");
sb.append("                  A.SEQ_NO        AS SEQ_NO,");
sb.append("                  TCODE_NAME('C098', G.LIVE_FLAG) LIVE_NAME,");
sb.append("                  A.MAIN_YN       AS MAIN_YN,");
sb.append("                  DECODE(C.SET_YN, '1', C.GOODS_CODE, '')    AS SET_GOODS_CODE,");
sb.append("                  DECODE(C.SET_YN, '1', C.GOODS_NAME, '')    AS SET_GOODS_NAME,");
sb.append("                  DECODE(C.SET_YN, '1', D.IN_GOODS_CODE, C.GOODS_CODE)    AS GOODS_CODE,");
sb.append("                  A.SALE_TG_QTY   AS SALE_TG_QTY,");
sb.append("                  C.SQC_GB        AS SQC_GB,");
sb.append("                  C.SALE_GB       AS SALE_GB,");
sb.append("                  C.MD_CODE       AS MD_CODE,");
sb.append("                  C.LGROUP        AS LGROUP,");
sb.append("                  TCODE_NAME('B053',C.LGROUP) AS LGROUP_NAME,");
sb.append("                  FUN_STAFF_NAME('10', G.SEQ_FRAME_NO) MODEL,");
sb.append("                  FUN_STAFF_NAME('20', G.SEQ_FRAME_NO) GUEST,");
sb.append("                  A.SPEC_NOTE     AS SPEC_NOTE,");
sb.append("                  A.BROAD_YN      AS BROAD_YN,");
sb.append("                  A.PDIN_YN       AS PDIN_YN,");
sb.append("                  C.SET_YN        AS SET_YN,");
sb.append("                  DECODE(C.SET_YN, '1', D.SALE_PRICE, 0) AS IN_GOODS_PRICE");
sb.append("            FROM  TMULTIFRAMESCHE  G,");
sb.append("                  TMULTIDTBROAD    A,");
sb.append("                  TPROGRAM         B,");
sb.append("                  TGOODS           C,");
sb.append("                  TSETGOODS        D,");
sb.append("                  TUSER            U1,");
sb.append("                  TUSER            U2");
sb.append("            WHERE G.SEQ_FRAME_NO =       A.SEQ_FRAME_NO");
sb.append("            AND   G.PROG_CODE    =       B.PROG_CODE");
sb.append("            AND   A.GOODS_CODE   =       C.GOODS_CODE");
sb.append("            AND   C.GOODS_CODE   =       D.GOODS_CODE(+)");
sb.append("            AND   G.MAIN_PD      =       U1.USER_ID");
sb.append("            AND   G.SUB_PD       =       U2.USER_ID(+)");
sb.append("            AND   A.BD_DATE     >=       TO_DATE('2013/11/01', 'YYYY/MM/DD')");
sb.append("            AND   A.BD_DATE     <  TO_DATE('2013/11/01', 'YYYY/MM/DD') + 1");
sb.append("            AND   A.MEDIA_CODE  LIKE '%%'");
sb.append("            AND   A.PROG_CODE   LIKE '%%'");
sb.append("            AND   C.GOODS_CODE  LIKE '%%'");
sb.append("            AND   C.MD_CODE     LIKE '%%'");
sb.append("            AND   C.ENTP_CODE   LIKE '%%'");
sb.append("            )       A,");
sb.append("        TMD         B,");
sb.append("        TGOODSPRICE F,");
sb.append("        TENTERPRISE H,");
sb.append("        TORDERSTOCK I,");
sb.append("        TSTOCK J,");
sb.append("        TGOODS K");
sb.append("        WHERE A.MD_CODE    = B.MD_CODE");
sb.append("        AND K.ENTP_CODE    = H.ENTP_CODE");
sb.append("        AND A.GOODS_CODE   = I.GOODS_CODE");
sb.append("        AND I.GOODS_CODE   = J.GOODS_CODE(+)");
sb.append("        AND I.GOODSDT_CODE = J.GOODSDT_CODE(+)");
sb.append("        AND A.GOODS_CODE   = K.GOODS_CODE");
sb.append("        AND (F.GOODS_CODE, F.APPLY_DATE) = (  SELECT X.GOODS_CODE,");
sb.append("                                                     MAX(X.APPLY_DATE)");
sb.append("                                                   FROM TGOODSPRICE  X");
sb.append("                                                 WHERE X.GOODS_CODE     = A.GOODS_CODE");
sb.append("                                                 AND X.APPLY_DATE <= A.BD_DATE");
sb.append("                                            GROUP BY X.GOODS_CODE )");
sb.append("        GROUP BY TO_CHAR(A.BD_DATE,'YYYY/MM/DD'),");
sb.append("          TO_CHAR(A.BD_BTIME,'HH24:MI'),");
sb.append("          TO_CHAR(A.BTIME,'HH24:MI'),");
sb.append("          TO_CHAR(A.ETIME,'HH24:MI'),");
sb.append("          A.PROG_CODE,");
sb.append("          A.PROG_NAME,");
sb.append("          A.SEQ_NO,");
sb.append("          A.SET_GOODS_CODE,");
sb.append("          A.SET_GOODS_NAME,");
sb.append("          A.GOODS_CODE");
sb.append("        ORDER BY TO_CHAR(A.BD_DATE,'YYYY/MM/DD'), TO_CHAR(A.BD_BTIME,'HH24:MI'), A.PROG_CODE  ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("PGM Date"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new ComboBox("Chanel"));
toolbar.addComponent(new TextField("Item"));
toolbar.addComponent(new TextField("Program Code/Name"));
toolbar.addComponent(new TextField("MD"));
toolbar.addComponent(new TextField("Vendor"));




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
                new Header("Program Item Report (MultiGoodsBroadList)"
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
