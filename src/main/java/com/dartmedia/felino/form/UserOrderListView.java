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
@CDIView("UserOrderList")
public class UserOrderListView extends MVerticalLayout implements View {
//UserOrderListSvc data=new UserOrderListSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/****/
StringBuffer sb = new StringBuffer();
sb.append("");
sb.append("SELECT /* orderreport.xml : custcenter.orderreport.selectUserOrderList by UserOrderList */");
sb.append("                A.ORDER_NO          AS ORDER_NO,  ");
sb.append("               B.CUST_NAME         AS CUST_NAME,  ");
sb.append("               F.GOODS_NAME        AS GOODS_NAME,  ");
sb.append("               A.ORDER_QTY         AS ORDER_QTY,  ");
sb.append("               A.RSALE_AMT         AS RSALE_AMT,  ");
sb.append("               TO_CHAR(A.LAST_PROC_DATE, 'YYYY/MM/DD')  AS OUT_DATE,  ");
sb.append("               E.RECEIVER          AS RECEIVER,  ");
sb.append("               E.TEL                AS RECEIVER_TEL,  ");
sb.append("               FUN_ADD_POSTADDR(E.RECEIVER_POST, E.RECEIVER_POST_SEQ, '') AS RECEIVER_ADDR,  ");
sb.append("               C.PROC_ID  ");
sb.append("          FROM TGOODS      F,  ");
sb.append("               TRECEIVER   E,  ");
sb.append("               TCUSTOMER   B,  ");
sb.append("               TORDERPROC  C,  ");
sb.append("               TORDERDT    A  ");
sb.append("         WHERE C.ORDER_NO      = A.ORDER_NO  ");
sb.append("           AND C.ORDER_G_SEQ   = A.ORDER_G_SEQ  ");
sb.append("           AND C.ORDER_D_SEQ   = A.ORDER_D_SEQ  ");
sb.append("           AND C.ORDER_W_SEQ   = A.ORDER_W_SEQ  ");
sb.append("           AND C.ORDER_P_SEQ   = '001'  ");
sb.append("           AND B.CUST_NO       = A.CUST_NO  ");
sb.append("           AND E.CUST_NO       = A.CUST_NO  ");
sb.append("           AND E.RECEIVER_SEQ  = A.RECEIVER_SEQ  ");
sb.append("           AND E.CUST_NO       = B.CUST_NO  ");
sb.append("           AND F.GOODS_CODE    = A.GOODS_CODE  ");
sb.append("           AND A.ORDER_DATE   >= TO_DATE('2014-01-01' || '00:00:00', 'yyyy/mm/dd hh24:mi:ss')  ");
sb.append("           AND TO_DATE('2014-01-01' || '23:59:59', 'yyyy/mm/dd hh24:mi:ss')  >= A.ORDER_DATE");
sb.append("           AND C.PROC_ID    LIKE 'user_id'||'%'      ");
sb.append(" ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Entry Date"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new TextField("Entry Person"));
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
                new Header("Agent Order List (UserOrderList)"
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
