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
@CDIView("OrderProc")
public class OrderProcView extends MVerticalLayout implements View {
//OrderProcSvc data=new OrderProcSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/****/
StringBuffer sb = new StringBuffer();
sb.append(" SELECT /* order.xml : custcenter.order.selectOrderProcList by OrderProc */");
sb.append("         A.ORDER_NO,");
sb.append("         A.ORDER_GB,");
sb.append("         A.GOODS_CODE,");
sb.append("         B.GOODS_NAME,");
sb.append("         A.ORDER_QTY,");
sb.append("         A.CANCEL_QTY,");
sb.append("         A.CLAIM_QTY,");
sb.append("         A.RETURN_QTY,");
sb.append("         A.EXCH_CNT,");
sb.append("         A.AS_CNT,");
sb.append("         A.SALE_PRICE,");
sb.append("         A.ORDER_G_SEQ,");
sb.append("         A.CLAIM_CAN_QTY,");
sb.append("         A.DC_AMT,");
sb.append("         A.ORDER_QTY - A.CANCEL_QTY - A.RETURN_QTY AS COMPNOWQTY,");
sb.append("         (A.ORDER_QTY - A.CANCEL_QTY - A.RETURN_QTY) * A.SALE_PRICE AS COMPTOTAMT,");
sb.append("         A.DC_AMT / A.ORDER_QTY * (A.ORDER_QTY - A.CANCEL_QTY - A.RETURN_QTY) AS COMPDCAMT");
sb.append("          FROM TGOODS B, TORDERGOODS A");
sb.append("         WHERE A.GOODS_CODE = B.GOODS_CODE");
sb.append("           AND A.CUST_NO = 'custNo'");
sb.append("           AND A.ORDER_DATE >= TO_DATE('2014-01-01', 'yyyy/mm/dd') /* fromDate */");
sb.append("           AND A.ORDER_DATE < TO_DATE('2014-01-01', 'yyyy/mm/dd') + 1 /* toDate */");
sb.append("           AND A.GOODS_CODE LIKE 'goodsCode' || '%'  ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------

toolbar.addComponent(new Button("Custumer"));
toolbar.addComponent(new Button("Order"));
toolbar.addComponent(new Button("Waybill"));
toolbar.addComponent(new Button("C/S Management"));
toolbar.addComponent(new Button("Invoice"));
toolbar.addComponent(new Button("Cust Detail"));
toolbar.addComponent(new Button("Counsel Receipt"));
toolbar.addComponent(new Button("Pay Receiving Detail"));
toolbar.addComponent(new Button("Delivery Fail Info"));

toolbar.addComponent(new Button("Print"));
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
                new Header("Order Progress (OrderProc)"
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
