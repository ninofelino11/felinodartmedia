package com.dartmedia.felino.form;
import com.vaadin.cdi.CDIView;
import org.vaadin.maddon.fields.MTable;
import com.vaadin.event.LayoutEvents;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.PopupDateField;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("CashMissMatch")
public class CashMissMatchView extends MVerticalLayout implements View {
//CashMissMatchSvc data=new CashMissMatchSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Sending date <p:calendar /> ~ <p:calendar />Pay receiving bank <p:inputText />**/
StringBuffer sb = new StringBuffer();
sb.append("  SELECT /* cash.xml : custcenter.cash.selectCashMissList by CashMissMatch */");
sb.append("                  '' AS CHK");
sb.append("               , A.CMS_NO");
sb.append("               , A.LINK_GB");
sb.append("               , A.SERIAL_NO");
sb.append("               , A.BANK_CODE");
sb.append("               , A.BANK_ACCOUNT");
sb.append("               , A.COMP_NAME");
sb.append("               , TO_CHAR(A.SEND_DATE, 'YYYY/MM/DD') AS SEND_DATE");
sb.append("               , A.SERV_CODE");
sb.append("               , A.DATA_NO");
sb.append("               , TO_CHAR(A.TRANS_DATE, 'YYYY/MM/DD HH24:MI:SS') AS TRANS_DATE");
sb.append("               , A.CUST_NAME");
sb.append("               , A.REF_NO1");
sb.append("               , A.REF_NO2");
sb.append("               , A.GIRO_CODE");
sb.append("               , A.PHONE_RESI_NO");
sb.append("               , A.TRANS_KINDS");
sb.append("               , A.TR_CODE");
sb.append("               , A.CHEQUE_NO");
sb.append("               , A.SEND_AMT");
sb.append("               , A.CHEQUE_TYPE");
sb.append("          FROM TCMS A");
sb.append("         WHERE A.TRANS_DATE >= TO_DATE('2014/01/01', 'YYYY/MM/DD')");
sb.append("           AND A.TRANS_DATE <  TO_DATE('2014/08/01', 'YYYY/MM/DD') + 1");
sb.append("           AND A.BANK_CODE LIKE '%%' ");
sb.append("           AND A.LINK_GB >= '01'");
sb.append("           AND A.LINK_GB < '04'");
sb.append("         ORDER BY A.CMS_NO DESC ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Sending Date"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new TextField("Pay. Reciving Bank"));
toolbar.addComponent(new TextField("Promo Name"));


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
                new Header("Money Transfer Mismatch (CashMissMatch)"
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
