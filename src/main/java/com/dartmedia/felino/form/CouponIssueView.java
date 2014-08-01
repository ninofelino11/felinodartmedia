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
@CDIView("CouponIssue")
public class CouponIssueView extends MVerticalLayout implements View {
//CouponIssueSvc data=new CouponIssueSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Promotion Term <p:calendar id='frDate'/> ~ <p:calendar id='toDate'/>Benefit <p:selectOneMenu id='do_type'></p:selectOneMenu>//All, Gift, Point, Discount, Free Delivery, Coupon, Lottery, Interest Free Installment - SQL NOT FOUNDApplication Type <p:selectOneMenu id='app_type'></p:selectOneMenu>//All, Item, Amount, Credit Card - Sql NOT FOUNDPromotion Code/Name <p:inputText id='use_code'/>//select promo_no, promo_name from tpromom where coupon_yn = 1Use <p:selectOneMenu id=''></p:selectOneMenu>//All, Use, Suspension - SQL Not Found**/
StringBuffer sb = new StringBuffer();
sb.append(" SELECT /* coupon.xml : manage.coupon.selectCoupon by CouponIssue */");
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
sb.append("          ORDER BY A.PROMO_NO DESC;");
sb.append("          ");
sb.append("SELECT /* coupon.xml : manage.coupon.selectPromoNo by CouponIssue */");
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
sb.append("            AND A.PROMO_NO = '201406130003'");
sb.append("            AND A.COUPON_YN = '1' ");
sb.append("            AND B.CODE_LGROUP = 'B007' ");
sb.append("            AND C.CODE_LGROUP = 'B064' ");
sb.append("            AND D.CODE_LGROUP = 'B008' ");
sb.append("          ORDER BY A.PROMO_NO DESC;  ");
sb.append("SELECT B.PROMO_NO, /* coupon.xml : manage.coupon.selectProvidedCouponList by CouponIssue */ ");
sb.append("               A.PROMO_NAME,                                                                        ");
sb.append("               A.APP_TYPE,                                                                          ");
sb.append("               A.DO_TYPE,                                                                           ");
sb.append("               A.USE_CODE,                                                                          ");
sb.append("               A.REMARK,                                                                            ");
sb.append("               A.PROMO_BDATE,                                                                       ");
sb.append("               A.PROMO_EDATE,                                                                       ");
sb.append("               B.CUST_NO,                                                                           ");
sb.append("               B.GET_ORDER_NO,                                                                      ");
sb.append("               B.USE_YN,                                                                            ");
sb.append("               B.USE_ORDER_NO,                                                                      ");
sb.append("               TO_CHAR(B.INSERT_DATE, 'YYYY/MM/DD') ISSUE_DATE,                                     ");
sb.append("               B.CANCEL_YN   ISSUE_CANCEL_YN,                                                       ");
sb.append("               DECODE(B.CANCEL_YN, '1',TO_CHAR(B.MODIFY_DATE, 'YYYY/MM/DD'), '' ) ISSUE_CANCEL_DATE ");
sb.append("          FROM TPROMOM      A,                                                                      ");
sb.append("               TCOUPONISSUE B                                                                       ");
sb.append("         WHERE B.CUST_NO = '201402059961'");
sb.append("           AND B.PROMO_NO = A.PROMO_NO ;  ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
//-------------------- Header  ------------------------------

toolbar.addComponent(new PopupDateField("Promotion Term"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new ComboBox("Benefit"));
toolbar.addComponent(new ComboBox("Application Type"));
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new TextField("Promotion Code/Name"));
toolbar.addComponent(new ComboBox("Use"));


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
        addComponents(isicontents);
MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
//-- No,Promotion Code,Promotion Name,Suspend

table.addContainerProperty("No", String.class,  null);
table.addContainerProperty("Promotion Code", String.class,  null);
table.addContainerProperty("Promotion Name", String.class,  null);
table.addContainerProperty("Suspend", String.class,  null);


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
