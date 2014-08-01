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
@CDIView("ClaimAcceptanceReport")
public class ClaimAcceptanceReportView extends MVerticalLayout implements View {
//ClaimAcceptanceReportSvc data=new ClaimAcceptanceReportSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/****/
StringBuffer sb = new StringBuffer();
sb.append("SELECT /* claiminfo.xml : custcenter.claiminfo.selectClaimAcceptanceReport by ClaimAcceptanceReport */");
sb.append("                 A.ORDER_NO    || '-' ||");
sb.append("                 A.ORDER_G_SEQ || '-' ||");
sb.append("                 A.ORDER_D_SEQ || '-' ||");
sb.append("                 A.ORDER_W_SEQ            AS ORDER_NO,");
sb.append("                 A.CLAIM_GB,");
sb.append("                 A.DO_FLAG,");
sb.append("                 FUN_GET_PROC_DATE(A.ORDER_NO, A.ORDER_G_SEQ, A.ORDER_D_SEQ, A.ORDER_W_SEQ, '50') AS RETURN_ORDER_DATE,");
sb.append("                 TO_CHAR(H.OUT_CLOSE_DATE, 'YYYY/MM/DD')  AS OUT_CLOSE_DATE,");
sb.append("                 H.SLIP_NO,");
sb.append("                 TCODE_NAME(DECODE(A.CLAIM_GB,'30','J013','J014'), A.CLAIM_CODE) AS CODE_NAME,");
sb.append("                 A.MSG,");
sb.append("                 A.GOODS_CODE,");
sb.append("                 C.GOODS_NAME,");
sb.append("                 A.GOODSDT_CODE,");
sb.append("                 C.GOODS_NAME || ' ' || C.GOODSDT_INFO AS GOODS_INFO,");
sb.append("                 E.RECEIVER                     AS CUST_NAME,");
sb.append("                 F.RECEIVER                     AS RECEIVER,");
sb.append("                 A.SYSLAST,");
sb.append("                 A.SYSLAST_AMT,");
sb.append("                 I.DELY_QTY + I.RETURN_QTY      AS DELY_QTY");
sb.append("            FROM TCLAIMDT      A,");
sb.append("                 TGOODSDT      C,");
sb.append("                 TRECEIVER     E,");
sb.append("                 TRECEIVER     F,");
sb.append("                 TPOST         G,");
sb.append("                 TSLIPM        H,");
sb.append("                 TSLIPDT       I");
sb.append("           WHERE A.GOODS_CODE        = C.GOODS_CODE");
sb.append("             AND A.GOODSDT_CODE      = C.GOODSDT_CODE");
sb.append("             AND A.CUST_NO           = E.CUST_NO");
sb.append("             AND A.CUST_NO           = F.CUST_NO");
sb.append("             AND A.RECEIVER_SEQ      = F.RECEIVER_SEQ");
sb.append("             AND F.RECEIVER_POST     = G.POST_NO");
sb.append("             AND F.RECEIVER_POST_SEQ = G.POST_SEQ");
sb.append("             AND A.ORDER_NO          = I.ORDER_NO(+)");
sb.append("             AND A.ORDER_G_SEQ       = I.ORDER_G_SEQ(+)");
sb.append("             AND A.ORDER_D_SEQ       = I.ORDER_D_SEQ(+)");
sb.append("             AND A.ORDER_W_SEQ       = I.ORDER_W_SEQ(+)");
sb.append("             AND I.SLIP_I_NO         = H.SLIP_I_NO(+)");
sb.append("             AND E.DEFAULT_YN        = '1'");
sb.append("             AND A.SYSLAST           >  0");
sb.append("             AND A.CLAIM_DATE       >= TO_DATE(TO_CHAR('2014-01-01'), 'YYYY/MM/DD')");
sb.append("             AND A.CLAIM_DATE  < TO_DATE(TO_CHAR('2014-01-01'), 'YYYY/MM/DD') + 1");
sb.append("             AND A.CLAIM_GB    IN ('30','45')");
sb.append("             AND A.CLAIM_GB  = '30'                 ");
sb.append("             AND A.DO_FLAG = '60'");
sb.append("             AND A.WH_CODE       LIKE 'wh_code' || '%'");
sb.append("             AND A.DELY_GB       LIKE 'dely_gb' || '%'");
sb.append("             AND G.AREA_GB       LIKE 'area_gb' || '%'");
sb.append("             AND A.GOODS_CODE    = 'goods_code'");
sb.append("           ORDER BY ORDER_NO ");
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
                new Header("C/S Collection Report (ClaimAcceptanceReport)"
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
