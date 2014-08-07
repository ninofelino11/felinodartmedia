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
@CDIView("MultiBroadAgree")
public class MultiBroadAgreeView extends MVerticalLayout implements View {
//MultiBroadAgreeSvc data=new MultiBroadAgreeSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/****/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("");
sb.append("/* PGM Date, Channel, Start Time, End Time, PGM Type, Program Code, Program Name, Tape Code, Target Order Amt, PGM Status");
sb.append("    Start, Close, Item Code, Item Name, Sale Price Sale Type, Status, Confirm, Delivery, MD Name, Vendor Name, Main, L Grp, Special Matter");
sb.append("*/");
sb.append("SELECT /* schedule.xml : broadcast.schedule.selectMultiBroadAgreeList by MultiBroadAgreeController */");
sb.append("            TO_CHAR(KK.BD_DATE, 'YYYY/MM/DD')          AS BD_DATE,");
sb.append("           TO_CHAR(KK.BD_BTIME, 'YYYY/MM/DD HH24:MI') AS BD_BTIME,");
sb.append("           TO_CHAR(KK.BD_ETIME, 'YYYY/MM/DD HH24:MI') AS BD_ETIME,");
sb.append("           KK.PROG_CODE,");
sb.append("           KK.PROG_NAME,");
sb.append("           KK.SALE_TG_QTY    AS SALE_TG_QTY,");
sb.append("           KK.MD_SALE_TG_QTY AS MD_SALE_TG_QTY,");
sb.append("           KK.CNT1           AS CNT1,");
sb.append("           KK.CNT2           AS CNT2,");
sb.append("           KK.TAPE_CODE,");
sb.append("           TCODE_NAME('C098', KK.LIVE_FLAG)  LIVE_NAME,");
sb.append("           KK.SEQ_FRAME_NO,");
sb.append("           KK.MEDIA_CODE,");
sb.append("           MM.MEDIA_NAME");
sb.append("       FROM    (");
sb.append("                 SELECT MAX(A.BD_DATE)   BD_DATE,");
sb.append("                    MAX(A.BD_BTIME)  BD_BTIME,");
sb.append("                    MAX(A.BD_ETIME)  BD_ETIME,");
sb.append("                    MAX(A.PROG_CODE) PROG_CODE,");
sb.append("                    MAX(B.PROG_NAME) PROG_NAME,");
sb.append("                    MAX(A.PLAN_AMT)   AS SALE_TG_QTY,");
sb.append("                    SUM(DECODE(C.BD_FLAG,'00',0,C.SALE_TG_QTY * D.SALE_PRICE ))   AS MD_SALE_TG_QTY,");
sb.append("                         COUNT(*)                        AS CNT1,");
sb.append("                    SUM(DECODE(C.BD_FLAG,'00',0,1)) AS CNT2,");
sb.append("                    MAX(A.TAPE_CODE)   AS TAPE_CODE,");
sb.append("                     MAX(A.LIVE_FLAG )  AS LIVE_FLAG,");
sb.append("                     A.SEQ_FRAME_NO,");
sb.append("                     MAX(A.MEDIA_CODE)  AS MEDIA_CODE");
sb.append("               FROM     TMULTIFRAMESCHE  A,");
sb.append("                    TPROGRAM         B,");
sb.append("                    TMULTIDTBROAD    C,");
sb.append("                      TGOODSPRICE      D");
sb.append("                WHERE A.SEQ_FRAME_NO = C.SEQ_FRAME_NO");
sb.append("                     AND A.PROG_CODE    = B.PROG_CODE");
sb.append("                AND C.GOODS_CODE   = D.GOODS_CODE");
sb.append("                AND (D.GOODS_CODE, D.APPLY_DATE) = ( SELECT X.GOODS_CODE,");
sb.append("                                     MAX(X.APPLY_DATE)");
sb.append("                                      FROM TGOODSPRICE  X");
sb.append("                                     WHERE X.GOODS_CODE = C.GOODS_CODE");
sb.append("                                      AND X.APPLY_DATE <= C.BD_DATE");
sb.append("                                     GROUP BY X.GOODS_CODE )");
sb.append("               AND A.BD_DATE     >= TO_DATE('2014-01-01', 'YYYY/MM/DD')");
sb.append("               AND A.BD_DATE     <=  TO_DATE('2014-01-01', 'YYYY/MM/DD')");
sb.append("            AND A.MEDIA_CODE         LIKE 'media_code'||'%'");
sb.append("               AND A.PROG_CODE       LIKE 'program_code' || '%'");
sb.append("               GROUP BY  A.SEQ_FRAME_NO )  KK,");
sb.append("               TMEDIA   MM");
sb.append("      WHERE KK.MEDIA_CODE = MM.MEDIA_CODE");
sb.append("      ORDER BY KK.BD_BTIME, KK.PROG_CODE    ;");
sb.append("      ");
sb.append("      ");
sb.append("SELECT /* schedule.xml : broadcast.schedule.selectMultiBroadAgreeDetailList by MultiBroadAgreeController */");
sb.append("            NVL( TO_CHAR(A.BTIME, 'HH24:MI'),'00:00')         AS BTIME,");
sb.append("           NVL( TO_CHAR(A.ETIME, 'HH24:MI'),'00:00')         AS ETIME,");
sb.append("           DECODE(C.SET_YN, '1', C.GOODS_CODE, '') AS SET_GOODS_CODE,");
sb.append("           DECODE(C.SET_YN, '1', C.GOODS_NAME, '') AS SET_GOODS_NAME,");
sb.append("           I.GOODS_CODE    AS GOODS_CODE,");
sb.append("           I.GOODS_NAME    AS GOODS_NAME,");
sb.append("           DECODE(C.SET_YN, '1', H.SALE_PRICE, E.SALE_PRICE) AS SALE_PRICE,");
sb.append("             A.SALE_TG_QTY   AS SALE_TG_QTY,");
sb.append("             A.SALE_TG_QTY  * DECODE(C.SET_YN, '1', H.SALE_PRICE, E.SALE_PRICE) AS PLAN_TG_SALE_PRICE,");
sb.append("           C.SALE_GB       AS SALE_GB,");
sb.append("           C.SQC_GB        AS SQC_GB,");
sb.append("           A.BD_FLAG       AS BD_FLAG,");
sb.append("           DECODE( NVL(A.BD_FLAG,'00'), '00','0','1')  AS SIGN_YN,");
sb.append("           I.DELY_TYPE     AS DELY_TYPE,");
sb.append("             C.MD_CODE       AS MD_CODE,");
sb.append("           D.MD_NAME       AS MD_NAME,");
sb.append("           I.ENTP_CODE     AS ENTP_CODE,");
sb.append("             F.ENTP_NAME     AS ENTP_NAME,");
sb.append("           A.SPEC_NOTE     AS SPEC_NOTE,");
sb.append("           A.MAIN_YN  ,");
sb.append("           C.LGROUP        AS LGROUP,");
sb.append("           TCODE_NAME('B053',C.LGROUP)  AS LGROUP_NAME,");
sb.append("           A.SEQ_FRAME_NO,");
sb.append("             A.SEQ_NO         AS SEQ_NO,");
sb.append("           ''              AS BROAD_SIGN,");
sb.append("           C.SET_YN        AS SET_YN");
sb.append("      FROM TMULTIDTBROAD A,");
sb.append("             TPROGRAM B,");
sb.append("           TGOODS C,");
sb.append("           TMD D,");
sb.append("           TGOODSPRICE E,");
sb.append("           TENTERPRISE F,");
sb.append("           TSETGOODS H,");
sb.append("           TGOODS I");
sb.append("       WHERE A.PROG_CODE     = B.PROG_CODE");
sb.append("       AND A.GOODS_CODE    = C.GOODS_CODE");
sb.append("       AND C.MD_CODE       = D.MD_CODE");
sb.append("       AND C.GOODS_CODE    = E.GOODS_CODE");
sb.append("       AND I.ENTP_CODE     = F.ENTP_CODE");
sb.append("       AND C.GOODS_CODE    = H.GOODS_CODE(+)");
sb.append("       AND DECODE(C.SET_YN, '1', H.IN_GOODS_CODE, C.GOODS_CODE) = I.GOODS_CODE");
sb.append("       AND (E.GOODS_CODE, E.APPLY_DATE) = ( SELECT X.GOODS_CODE,");
sb.append("                                                  MAX(X.APPLY_DATE)");
sb.append("                                               FROM TGOODSPRICE  X");
sb.append("                                            WHERE X.GOODS_CODE = A.GOODS_CODE");
sb.append("                                              AND X.APPLY_DATE <= A.BD_DATE");
sb.append("                                              GROUP BY X.GOODS_CODE )");
sb.append("       AND A.SEQ_FRAME_NO = 101");
sb.append("     ORDER BY A.BD_BTIME, A.SEQ_NO ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Pgm Date    "));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new ComboBox("Chanel"));
toolbar.addComponent(new TextField("Program Name"));


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
                new Header("Approve Schedule (MultiBroadAgree)"
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
