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
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("CustCouponList")
public class CustCouponListView extends MVerticalLayout implements View {
//CustCouponListSvc data=new CustCouponListSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Customer <p:inputText />
//
**/
StringBuffer sb = new StringBuffer();
sb.append("SELECT");
sb.append("/*");
sb.append("popup.xml");
sb.append(":");
sb.append("system.common.popup.selectCustCouponList");
sb.append("by");
sb.append("PopupController");
sb.append("*/");
sb.append("B.PROMO_NO");
sb.append(",");
sb.append("A.PROMO_NAME");
sb.append(",");
sb.append("A.APP_TYPE");
sb.append(",");
sb.append("A.DO_TYPE");
sb.append(",");
sb.append("A.USE_CODE");
sb.append(",");
sb.append("A.REMARK");
sb.append(",");
sb.append("A.PROMO_BDATE");
sb.append(",");
sb.append("A.PROMO_EDATE");
sb.append(",");
sb.append("B.CUST_NO");
sb.append(",");
sb.append("B.GET_ORDER_NO");
sb.append(",");
sb.append("B.USE_YN");
sb.append(",");
sb.append("B.USE_ORDER_NO");
sb.append(",");
sb.append("TO_CHAR(B.INSERT_DATE,");
sb.append("'YYYY/MM/DD')");
sb.append("ISSUE_DATE");
sb.append(",");
sb.append("B.CANCEL_YN");
sb.append("ISSUE_CANCEL_YN");
sb.append(",");
sb.append("DECODE(B.CANCEL_YN,");
sb.append("'1',TO_CHAR(B.MODIFY_DATE,");
sb.append("'YYYY/MM/DD'),");
sb.append("''");
sb.append(")");
sb.append("ISSUE_CANCEL_DATE");
sb.append(",");
sb.append("TO_CHAR(B.USE_START_DATE,");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("USE_START_DATE");
sb.append(",");
sb.append("TO_CHAR(B.USE_END_DATE,");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("USE_END_DATE");
sb.append("FROM");
sb.append("TPROMOM");
sb.append("A,");
sb.append("TCOUPONISSUE");
sb.append("B");
sb.append("WHERE");
sb.append("B.CUST_NO");
sb.append("like");
sb.append("'201302025649'");
sb.append("||");
sb.append("'%'");
sb.append("AND");
sb.append("B.PROMO_NO");
sb.append("=");
sb.append("A.PROMO_NO");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
//-toolbar.addComponent(new PopupDateField("My Date"));
//-toolbar.addComponent(new TextField("Indv.Query"));
//-toolbar.addComponent(new ComboBox("My ComboBox"));
//-toolbar.addComponent(new Button("My ComboBox"));
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
                new Header("Coupon Report (CustCouponList")
        );
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
