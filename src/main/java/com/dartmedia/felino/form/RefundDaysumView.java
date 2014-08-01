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
@CDIView("RefundDaysum")
public class RefundDaysumView extends MVerticalLayout implements View {
//RefundDaysumSvc data=new RefundDaysumSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/****/
StringBuffer sb = new StringBuffer();
sb.append(" SELECT /* refund.xml : custcenter.refund.selectRefundDaysum by RefundDaysum */");
sb.append("                   TO_CHAR(REPAY_DATE,'YYYY/MM/DD') AS REPAY_DATE,");
sb.append("                SUM(NVL(DECODE(SETTLE_GB, '01', DECODE(#{repay_flag,jdbcType=VARCHAR},'1',REPAY_AMT,'2', REAL_REPAY_AMT, REPAY_FEE)),0))  CARD_AMT,");
sb.append("                SUM(NVL(DECODE(SETTLE_GB, '02', DECODE(#{repay_flag,jdbcType=VARCHAR},'1',REPAY_AMT,'2', REAL_REPAY_AMT, REPAY_FEE)),0))  CASH_AMT,");
sb.append("                SUM(NVL(DECODE(SETTLE_GB, '03', DECODE(#{repay_flag,jdbcType=VARCHAR},'1',REPAY_AMT,'2', REAL_REPAY_AMT, REPAY_FEE)),0))  COD_AMT,");
sb.append("                SUM(NVL(DECODE(SETTLE_GB, '04', DECODE(#{repay_flag,jdbcType=VARCHAR},'1',REPAY_AMT,'2', REAL_REPAY_AMT, REPAY_FEE)),0))  MOB_AMT,");
sb.append("                SUM(NVL(DECODE(SETTLE_GB, '05', DECODE(#{repay_flag,jdbcType=VARCHAR},'1',REPAY_AMT,'2', REAL_REPAY_AMT, REPAY_FEE)),0))  SAVE_AMT,");
sb.append("                SUM(NVL(DECODE(SETTLE_GB, '06', DECODE(#{repay_flag,jdbcType=VARCHAR},'1',REPAY_AMT,'2', REAL_REPAY_AMT, REPAY_FEE)),0))  DEPOSIT_AMT,");
sb.append("                SUM(CASE WHEN SETTLE_GB &lt;= '06' THEN 0");
sb.append("                         ELSE  REPAY_AMT");
sb.append("                    END)   ETC_AMT");
sb.append("           FROM VSDREFUND");
sb.append("          WHERE REPAY_DATE BETWEEN TO_DATE('2014-01-01', 'yyyy/mm/dd')");
sb.append("            AND TO_DATE('2014-01-01', 'yyyy/mm/dd')");
sb.append("       GROUP BY REPAY_DATE");
sb.append("       ORDER BY REPAY_DATE     ");
sb.append("       ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Refund Complete"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new ComboBox("Refunf Amt"));


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
                new Header("Refund Report (RefundDaysum)"
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
