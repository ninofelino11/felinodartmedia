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
@CDIView("CouponIssue")
public class CouponIssueView extends MVerticalLayout implements View {
//CouponIssueSvc data=new CouponIssueSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Promotion Term <p:calendar id='frDate'/> ~ <p:calendar id='toDate'/>Benefit <p:selectOneMenu id='do_type'></p:selectOneMenu>//All, Gift, Point, Discount, Free Delivery, Coupon, Lottery, Interest Free Installment - SQL NOT FOUNDApplication Type <p:selectOneMenu id='app_type'></p:selectOneMenu>//All, Item, Amount, Credit Card - Sql NOT FOUNDPromotion Code/Name <p:inputText id='use_code'/>//select promo_no, promo_name from tpromom where coupon_yn = 1Use <p:selectOneMenu id=''></p:selectOneMenu>//All, Use, Suspension - SQL Not Found**/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append(" SELECT /* coupon.xml : manage.coupon.selectCoupon by CouponIssue */");
sb.append("                B.ROWID,   ");

sb.append("                A.PROMO_NO,   ");

sb.append("                A.PROMO_NAME, ");
sb.append("                TO_CHAR(A.PROMO_BDATE, 'YYYY/MM/DD HH24:MI:SS') AS BDATE,  ");
sb.append("                TO_CHAR(A.PROMO_EDATE, 'YYYY/MM/DD HH24:MI:SS') AS EDATE,  ");
sb.append("                DECODE(A.USE_CODE, '00', '0', '1')  AS STOP_YN,   ");
sb.append("                D.CODE_NAME AS APP_TYPE,  ");
sb.append("                B.CODE_NAME AS DO_TYPE,   ");
sb.append("                C.CODE_NAME AS USE_CODE,  ");
sb.append("                A.REMARK   ");
sb.append("           FROM TPROMOM A, ");
sb.append("                TCODE  B,  ");
sb.append("                TCODE  C,  ");
sb.append("                TCODE  D   ");
sb.append("          WHERE A.DO_TYPE  = B.CODE_MGROUP   ");
sb.append("            AND A.USE_CODE = C.CODE_MGROUP   ");
sb.append("            AND A.APP_TYPE = D.CODE_MGROUP   ");
sb.append("            AND A.PROMO_EDATE >= TO_DATE('2014-01-01','YYYY/MM/DD HH24:MI:SS')  ");
sb.append("            AND A.PROMO_BDATE <= TO_DATE('2014-07-15','YYYY/MM/DD HH24:MI:SS')+1 ");
sb.append("            AND A.APP_TYPE LIKE ''||'%'  ");
sb.append("            AND A.DO_TYPE  LIKE ''||'%'  ");
sb.append("            AND A.USE_CODE LIKE '0'||'%'  ");
sb.append("            AND A.COUPON_YN = '1' ");
sb.append("            AND B.CODE_LGROUP = 'B007' ");
sb.append("            AND C.CODE_LGROUP = 'B064' ");
sb.append("            AND D.CODE_LGROUP = 'B008' ");
sb.append("          ORDER BY A.PROMO_NO DESC");
sb1.append("            AND ROWCOUNT()<10 ");

sb.append("          ");

sb1.append("SELECT /* coupon.xml : manage.coupon.selectPromoNo by CouponIssue */");
sb1.append("                A.PROMO_NO,   ");
sb1.append("                A.PROMO_NAME, ");
sb1.append("                TO_CHAR(A.PROMO_BDATE, 'YYYY/MM/DD HH24:MI:SS') AS BDATE,  ");
sb1.append("                TO_CHAR(A.PROMO_EDATE, 'YYYY/MM/DD HH24:MI:SS') AS EDATE,  ");
sb1.append("                DECODE(A.USE_CODE, '00', '0', '1')  AS STOP_YN,   ");
sb1.append("                D.CODE_NAME AS APP_TYPE,  ");
sb1.append("                B.CODE_NAME AS DO_TYPE,   ");
sb1.append("                C.CODE_NAME AS USE_CODE,  ");
sb1.append("                A.REMARK   ");
sb1.append("           FROM TPROMOM A, ");
sb1.append("                TCODE  B,  ");
sb1.append("                TCODE  C,  ");
sb1.append("                TCODE  D   ");
sb1.append("          WHERE A.DO_TYPE  = B.CODE_MGROUP   ");
sb1.append("            AND A.USE_CODE = C.CODE_MGROUP   ");
sb1.append("            AND A.APP_TYPE = D.CODE_MGROUP   ");
sb1.append("            AND A.PROMO_NO = '201406130003'");
sb1.append("            AND A.COUPON_YN = '1' ");
sb1.append("            AND B.CODE_LGROUP = 'B007' ");
sb1.append("            AND C.CODE_LGROUP = 'B064' ");
sb1.append("            AND D.CODE_LGROUP = 'B008' ");
sb1.append("            AND ROWCOUNT()<10 ");

sb1.append("          ORDER BY A.PROMO_NO DESC ");
sb1.append("                                   ");

sb1.append("SELECT B.PROMO_NO, /* coupon.xml : manage.coupon.selectProvidedCouponList by CouponIssue */ ");
sb1.append("               A.PROMO_NAME,                                                                        ");
sb1.append("               A.APP_TYPE,                                                                          ");
sb1.append("               A.DO_TYPE,                                                                           ");
sb1.append("               A.USE_CODE,                                                                          ");
sb1.append("               A.REMARK,                                                                            ");
sb1.append("               A.PROMO_BDATE,                                                                       ");
sb1.append("               A.PROMO_EDATE,                                                                       ");
sb1.append("               B.CUST_NO,                                                                           ");
sb1.append("               B.GET_ORDER_NO,                                                                      ");
sb1.append("               B.USE_YN,                                                                            ");
sb1.append("               B.USE_ORDER_NO,                                                                      ");
sb1.append("               TO_CHAR(B.INSERT_DATE, 'YYYY/MM/DD') ISSUE_DATE,                                     ");
sb1.append("               B.CANCEL_YN   ISSUE_CANCEL_YN,                                                       ");
sb1.append("               DECODE(B.CANCEL_YN, '1',TO_CHAR(B.MODIFY_DATE, 'YYYY/MM/DD'), '' ) ISSUE_CANCEL_DATE ");
sb1.append("          FROM TPROMOM      A,                                                                      ");
sb1.append("               TCOUPONISSUE B                                                                       ");
sb1.append("         WHERE B.CUST_NO = '201402059961'");
sb1.append("           AND B.PROMO_NO = A.PROMO_NO ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Promotion Term"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new ComboBox("Benefit"));
toolbar.addComponent(new ComboBox("Application Type"));
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new TextField("Promotion Code/Name"));
toolbar.addComponent(new ComboBox("Use"));
//-- No,Promotion Code,Promotion Name,Suspend


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
                new Header("Coupon Management (CouponIssue)"
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
             "oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@localhost:1521/XE",
             "dartmedia", "dartmedia",2,5);
             SQLContainer container;
              container = new SQLContainer(new FreeformQuery(
              sb.toString(),connectionPool,"PROMO_NO"));
             // MTable table= new MTable("MENU",container);
               TreeTable table = new TreeTable("Menu", container);
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
