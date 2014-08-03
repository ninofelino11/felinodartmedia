package com.dartmedia.felino.form;
import com.dartmedia.felino.fgetsql;
import com.dartmedia.felino.gSqlContainer;
import com.dartmedia.felino.gSqlContainer;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("AnalysisPromo")
public class AnalysisPromoView extends MVerticalLayout implements View {
//AnalysisPromoSvc data=new AnalysisPromoSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Term <p:calendar id='fromDate'/> ~ <p:calendar id='toDate'/>Item <p:inputText id='goods_code'/>//select goods_code, goods_name, TCODE_NAME('B032', SALE_GB) AS SALE_NAME from TGOODS Promo Code/Name <p:inputText id='promo_no'/>//select promo_no, promo_name from tpromom;**/
StringBuffer sb = new StringBuffer();
sb.append("SELECT A.PROMO_NO       AS PROMO_NO2,                                                        ");
sb.append("                 A.GOODS_CODE,                                                                        ");
sb.append("                 B.PROMO_NAME,                                                                        ");
sb.append("                 C.GOODS_NAME,                                                                        ");
sb.append("                 SUM(A.ORDER_QTY) AS ORDER_QTY,                                                       ");
sb.append("                 SUM(A.ORDER_AMT) AS ORDER_AMT,                                                       ");
sb.append("                 SUM(A.CANCEL_QTY) AS CANCEL_QTY,                                                     ");
sb.append("                 SUM(A.CANCEL_AMT) AS CANCEL_AMT,                                                     ");
sb.append("                 SUM(A.CLAIM_QTY) AS CLAIM_QTY,                                                       ");
sb.append("                 SUM(A.CLAIM_AMT) AS CLAIM_AMT,                                                       ");
sb.append("                 SUM(A.CLAIM_CAN_QTY) AS CLAIM_CAN_QTY,                                               ");
sb.append("                 SUM(A.CLAIM_CAN_AMT) AS CLAIM_CAN_AMT,                                               ");
sb.append("                 SUM(A.ORDER_QTY - A.CANCEL_QTY - A.CLAIM_QTY + A.CLAIM_CAN_QTY) AS REAL_ORDER_QTY,   ");
sb.append("                 SUM(A.ORDER_AMT - A.CANCEL_AMT - A.CLAIM_AMT + A.CLAIM_CAN_AMT) AS REAL_ORDER_AMT    ");
sb.append("            FROM(                                                                                     ");
sb.append("                                                                                                      ");
sb.append("                  SELECT B.PROMO_NO  AS PROMO_NO,                                                             ");
sb.append("                                 DECODE( A.GOODS_GB, '20', A.SET_GOODS_CODE, A.GOODS_CODE) AS GOODS_CODE,     ");
sb.append("                                 SUM(A.ORDER_QTY) AS ORDER_QTY,                                               ");
sb.append("                                 SUM(A.RSALE_AMT) AS ORDER_AMT,                                               ");
sb.append("                                 0 AS CANCEL_QTY,                                                             ");
sb.append("                                 0 AS CANCEL_AMT,                                                             ");
sb.append("                                 0 AS CLAIM_QTY,                                                              ");
sb.append("                                 0 AS CLAIM_AMT,                                                              ");
sb.append("                                 0 AS CLAIM_CAN_QTY,                                                          ");
sb.append("                                 0 AS CLAIM_CAN_AMT                                                           ");
sb.append("                            FROM TORDERDT A,                                                                  ");
sb.append("                                 TORDERPROMO B                                                                ");
sb.append("                           WHERE A.ORDER_NO = B.ORDER_NO                                                      ");
sb.append("                             AND NVL(B.ORDER_G_SEQ,'001') = A.ORDER_G_SEQ                                     ");
sb.append("                             AND A.ORDER_DATE >= TO_DATE('2014/01/01','YYYY/MM/DD')                                        ");
sb.append("                             AND A.ORDER_DATE <  TO_DATE('2014/07/15' , 'YYYY/MM/DD') + 1                                ");
sb.append("                             AND A.GOODS_GB <= '20'                                                           ");
sb.append("                             AND B.PROMO_NO LIKE '201401210001' || '%'                                                     ");
sb.append("                           GROUP BY B.PROMO_NO, DECODE( A.GOODS_GB, '20', A.SET_GOODS_CODE, A.GOODS_CODE)     ");
sb.append("                                                                                                              ");
sb.append("                           UNION ALL                                                                          ");
sb.append("                           SELECT B.PROMO_NO  AS PROMO_NO,                                                    ");
sb.append("                                 DECODE( A.GOODS_GB, '20', A.SET_GOODS_CODE, A.GOODS_CODE) AS GOODS_CODE,     ");
sb.append("                                 0 AS ORDER_QTY,                                                              ");
sb.append("                                 0 AS ORDER_AMT,                                                              ");
sb.append("                                 SUM(CANCEL_QTY)  AS CANCEL_QTY,                                              ");
sb.append("                                 SUM(RSALE_AMT)  AS CANCEL_AMT,                                               ");
sb.append("                                 0 AS CLAIM_QTY,                                                              ");
sb.append("                                 0 AS CLAIM_AMT,                                                              ");
sb.append("                                 0 AS CLAIM_CAN_QTY,                                                          ");
sb.append("                                 0 AS CLAIM_CAN_AMT                                                           ");
sb.append("                            FROM TCANCELDT A,                                                                 ");
sb.append("                                 TORDERPROMO B                                                                ");
sb.append("                           WHERE A.ORDER_NO = B.ORDER_NO                                                      ");
sb.append("                             AND NVL(B.ORDER_G_SEQ,'001') = A.ORDER_G_SEQ                                     ");
sb.append("                             AND A.CANCEL_DATE >= TO_DATE('2014/01/01','YYYY/MM/DD')                                       ");
sb.append("                             AND A.CANCEL_DATE <  TO_DATE('2014/07/15', 'YYYY/MM/DD') + 1                                  ");
sb.append("                             AND A.GOODS_GB <= '20'                                                           ");
sb.append("                             AND B.PROMO_NO LIKE '201401210001' || '%'                                                     ");
sb.append("                           GROUP BY B.PROMO_NO, DECODE( A.GOODS_GB, '20', A.SET_GOODS_CODE, A.GOODS_CODE)     ");
sb.append("                                                                                                              ");
sb.append("                           UNION ALL                                                                          ");
sb.append("                           SELECT B.PROMO_NO  AS PROMO_NO,                                                    ");
sb.append("                                 DECODE( A.GOODS_GB, '20', A.SET_GOODS_CODE, A.GOODS_CODE) AS GOODS_CODE,     ");
sb.append("                                 0  AS ORDER_QTY,                                                             ");
sb.append("                                 0  AS ORDER_AMT,                                                             ");
sb.append("                                 0  AS CANCEL_QTY,                                                            ");
sb.append("                                 0  AS CANCEL_AMT,                                                            ");
sb.append("                                 SUM(DECODE(A.CLAIM_GB,'30',CLAIM_QTY,0))  AS CLAIM_QTY,                      ");
sb.append("                                 SUM(DECODE(A.CLAIM_GB,'30',RSALE_AMT,0))  AS CLAIM_AMT,                      ");
sb.append("                                 SUM(DECODE(A.CLAIM_GB,'31',CLAIM_QTY,0))  AS CLAIM_CAN_QTY,                  ");
sb.append("                                 SUM(DECODE(A.CLAIM_GB,'31',RSALE_AMT,0)) AS CLAIM_CAN_AMT                    ");
sb.append("                            FROM TCLAIMDT A,                                                                  ");
sb.append("                                 TORDERPROMO B                                                                ");
sb.append("                           WHERE A.ORDER_NO = B.ORDER_NO                                                      ");
sb.append("                             AND NVL(B.ORDER_G_SEQ,'001') = A.ORDER_G_SEQ                                     ");
sb.append("                             AND A.CLAIM_GB IN ('30','31')                                                            ");
sb.append("                             AND A.CLAIM_DATE >= TO_DATE('2014/01/01','YYYY/MM/DD')                                        ");
sb.append("                             AND A.CLAIM_DATE <  TO_DATE('2014/07/15' , 'YYYY/MM/DD') + 1                                  ");
sb.append("                             AND A.GOODS_GB <= '20'                                                           ");
sb.append("                             AND B.PROMO_NO LIKE '201401210001' || '%'                                                     ");
sb.append("                           GROUP BY B.PROMO_NO, DECODE( A.GOODS_GB, '20', A.SET_GOODS_CODE, A.GOODS_CODE)     ");
sb.append("                           UNION ALL                                                                          ");
sb.append("                           SELECT A.PROMO_NO,                                                                 ");
sb.append("                                    B.GOODS_CODE,                                                             ");
sb.append("                                    0 AS ORDER_QTY,                                                           ");
sb.append("                                    0 AS ORDER_AMT,                                                           ");
sb.append("                                    0 AS CANCEL_QTY,                                                          ");
sb.append("                                    0 AS CANCEL_AMT,                                                          ");
sb.append("                                    0 AS CLAIM_QTY,                                                           ");
sb.append("                                    0 AS CLAIM_AMT,                                                           ");
sb.append("                                    0 AS CLAIM_CAN_QTY,                                                       ");
sb.append("                                    0 AS CLAIM_CAN_AMT                                                        ");
sb.append("                             FROM TPROMOM A,                                                                  ");
sb.append("                                  TPROMOTARGET B                                                              ");
sb.append("                            WHERE A.PROMO_BDATE >= TO_DATE('2014/01/01','YYYY/MM/DD')                                      ");
sb.append("                              AND A.PROMO_EDATE <  TO_DATE('2014/07/15' , 'YYYY/MM/DD') + 1                                ");
sb.append("                              AND A.PROMO_NO LIKE '201401210001' || '%'                                                    ");
sb.append("                              AND A.PROMO_NO = B.PROMO_NO                                                     ");
sb.append("                  ) A,                                                                                        ");
sb.append("               TPROMOM B,                                                                             ");
sb.append("               TGOODS       C                                                                         ");
sb.append("            WHERE A.PROMO_NO = B.PROMO_NO                                                             ");
sb.append("              AND A.GOODS_CODE = C.GOODS_CODE                                                         ");
sb.append("              AND A.GOODS_CODE LIKE '104649' || '%'                                                          ");
sb.append("           GROUP BY A.PROMO_NO, A.GOODS_CODE, B.PROMO_NAME, C.GOODS_NAME                              ");
sb.append("           ORDER BY A.PROMO_NO, A.GOODS_CODE, B.PROMO_NAME, C.GOODS_NAME                               ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new TextField("Item"));
toolbar.addComponent(new TextField("Promo Code/Name"));
//-- No.Promotion Code,Promotion Name,Item Code,Item Name,Order Qty,Order Amt,CancelQTy,Cancel Amt,Return


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
                new Header("Promotion Order Report  (AnalysisPromo)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
        addComponents(isicontents);

        
String fsql=sb.toString();      

try{
            SimpleJDBCConnectionPool connectionPool = new SimpleJDBCConnectionPool(
             "oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@localhost:1521/XE",
             "dartmedia", "dartmedia",2,5);        
             SQLContainer container;
              container = new SQLContainer(new FreeformQuery(
              sb.toString(),connectionPool,"AD_MENU_ID"));
             // MTable table= new MTable("MENU",container);    
               TreeTable table = new TreeTable("Menu", container);
              addComponents(table);
 } catch (SQLException e) {
     e.printStackTrace();
     RichTextArea rtarea = new RichTextArea();
     rtarea.setValue(fsql);
      addComponents(rtarea);
}
               
//-------------------- Header Table ---judul untuk table----------

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
