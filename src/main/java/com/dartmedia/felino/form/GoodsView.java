package com.dartmedia.felino.form;
import com.cware.back.common.BaseEntity;
import com.dartmedia.felino.TgoodsForm;
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
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.fields.MValueChangeEvent;
import org.vaadin.maddon.fields.MValueChangeListener;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("Goods")
public class GoodsView extends MVerticalLayout implements View {
//GoodsSvc data=new GoodsSvc();
//@Inject   TenterpriseFacade cf;
@Inject  TgoodsForm form;
    @PostConstruct
    public void initComponent() {
/**Item Reg Date <p:calendar id='fromDate'/> - <p:calendar id='toDate'/>Inquiry Type <p:selectOneMenu>              <f:selectItem itemLabel="REG" itemValue="REG" />              <f:selectItem itemLabel="Item Code" itemValue="Item Code" />              <f:selectItem itemLabel="Master Code" itemValue="Master Code" />              <f:selectItem itemLabel="MD" itemValue="MD" />          </p:selectOneMenu> <p:inputText />//REG => select DISTINCT A.INSERT_ID from TGOODS A        //Item Code => select DISTINCT A.GOODS_CODE from TGOODS A//Master Code => select DISTINCT A.MASTER_CODE from TGOODS A//MD => select DISTINCT A.MD_CODE from TGOODS A**/
//@include fonticons;

StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
StringBuffer sb2 = new StringBuffer();
StringBuffer sb3 = new StringBuffer();

sb.append("");
sb.append("select /* goods.xml : manage.goods.selectGoods by Goods */");
sb.append("                DISTINCT A.GOODS_CODE,");
sb.append("                A.GOODS_NAME,");
sb.append("                A.LMSD_CODE,");
sb.append("                A.ENTP_CODE");
sb.append("           from TGOODS A");
sb.append("          where A.SET_YN = '0' and rownum<100 ");
//sb.append("          /* pilih Item Code */");
//sb.append("          AND A.GOODS_CODE  LIKE  '' || '%'");
//sb.append("          AND INSERT_DATE BETWEEN TO_DATE('2012-01-01', 'YYYY/MM/DD') AND TO_DATE('2014-01-01', 'YYYY/MM/DD') + 0.99999");
//sb.append("          /* pilih REG */");
//sb.append("          and A.INSERT_ID   LIKE  '' || '%' ");
//sb.append("          /* pilih Master Code */");
//sb.append("          and A.MASTER_CODE LIKE  '' || '%'");
//sb.append("          /* pilih MD */");
//sb.append("          and A.MD_CODE LIKE  '' || '%'");
//sb.append("          order by lpad(A.GOODS_CODE, 10, '0') DESC");
sb.append("");


sb1.append("select /* goods.xml : manage.goods.selectGoodsDt1 by Goods */");
sb1.append("                  A.GOODS_CODE,");
sb1.append("                  A.GOODS_NAME,");
sb1.append("                  A.LGROUP,");
sb1.append("                  B.LGROUP_NAME,");
sb1.append("                  A.MGROUP,");
sb1.append("                  B.MGROUP_NAME,");
sb1.append("                  A.SGROUP,");
sb1.append("                  B.SGROUP_NAME,");
sb1.append("                  A.DGROUP,");
sb1.append("                  B.DGROUP_NAME,");
sb1.append("                  A.QC_LGROUP,");
sb1.append("                  '' QC_LGROUP_NAME, /*Q.QC_LGROUP_NAME,*/");
sb1.append("                  A.QC_MGROUP,");
sb1.append("                  '' QC_MGROUP_NAME, /*Q.QC_MGROUP_NAME,*/");
sb1.append("                  A.SALE_GB,");
sb1.append("                  A.SIGN_GB AS FORM_SIGN_GB,");
sb1.append("                  A.ENTP_CODE,");
sb1.append("                  C.ENTP_NAME,");
sb1.append("                  A.ENTP_MAN_SEQ,");
sb1.append("                  I.ENTP_MAN_NAME AS ENTP_MAN_SEQ_NAME,");
sb1.append("                  A.ACCOUNT_GB,");
sb1.append("                  A.MD_CODE,");
sb1.append("                  D.MD_NAME,");
sb1.append("                  A.KEYWORD,");
sb1.append("                  A.BUY_MED,");
sb1.append("                  A.DELY_TYPE,");
sb1.append("                  A.WH_CODE,");
sb1.append("                  A.POST_YN,");
sb1.append("                  A.TAX_YN,");
sb1.append("                  A.VAT_RATE,");
sb1.append("                  A.COST_TAX_YN,");
sb1.append("                  A.COST_VAT_RATE,");
sb1.append("                  A.MAKECO_CODE,");
sb1.append("                  A.ORIGIN_CODE,");
sb1.append("                  A.BRAND_CODE,");
sb1.append("                  A.HANDLE_CODE,");
sb1.append("                  A.MIXPACK_YN,");
sb1.append("                  A.DM_YN,");
sb1.append("                  A.PACK_BOX,");
sb1.append("                  A.SQC_YN,");
sb1.append("                  A.SQC_GB,");
sb1.append("                  A.SET_YN,");
sb1.append("                  A.SET_GOODS_YN,");
sb1.append("                  A.GIFT_YN,");
sb1.append("                  A.PAY_YN,");
sb1.append("                  A.GIFT_RETURN_YN,");
sb1.append("                  A.EXCH_YN,");
sb1.append("                  A.RETURN_YN,");
sb1.append("                  A.AS_YN,");
sb1.append("                  A.OUT_STOCK_YN,");
sb1.append("                  A.ORDER_MIN_QTY,");
sb1.append("                  A.STOCK_CHK_PLACE,");
sb1.append("                  A.ORDER_MEDIA_ALL_YN,");
sb1.append("                  A.ORDER_MEDIA,");
sb1.append("                  FUN_GET_ORDER_MEDIA_NAME('J001',A.ORDER_MEDIA) AS ORDER_MEDIA_NAME,");
sb1.append("                  A.ARS_NAME,");
sb1.append("                  A.PARENT_GOODS_CODE,");
sb1.append("                  A.BASE_GOODS_CODE,");
sb1.append("                  A.NOREST_ALLOT_YN,");
sb1.append("                  A.NOREST_ALLOT_MONTHS,");
sb1.append("                  TO_CHAR(A.INSERT_DATE,'YYYY/MM/DD') AS INSERT_DATE,");
sb1.append("                  A.INSERT_ID,");
sb1.append("                  TO_CHAR(A.MODIFY_DATE,'YYYY/MM/DD') AS MODIFY_DATE,");
sb1.append("                  A.MODIFY_ID,");
sb1.append("                  A.WH_FIX_YN,");
sb1.append("                  A.ADVC_REFUND_YN,");
sb1.append("                  A.ORDER_MAKE_YN,");
sb1.append("                  A.INSTALL_YN,");
sb1.append("                  A.DIRECT_SHIP_YN,");
sb1.append("                  A.SHIP_MAN_SEQ,");
sb1.append("                  I_1.ENTP_MAN_NAME AS SHIP_MAN_NAME,");
sb1.append("                  (CASE WHEN I_1.ENTP_MAN_DDD IS NULL  THEN '' ELSE I_1.ENTP_MAN_DDD||'-'||I_1.ENTP_MAN_TEL1||'-'||I_1.ENTP_MAN_TEL2 END) || (CASE WHEN I_1.ENTP_MAN_TEL3 IS NULL THEN '' ELSE '['||I_1.ENTP_MAN_TEL3||']' END) AS SHIP_MAN_TEL,");
sb1.append("                  FUN_ADD_POSTADDR(I_1.POST_NO, I_1.POST_SEQ, I_1.ADDR) AS SHIP_MAN_ADDR,");
sb1.append("                  A.DIRECT_RETURN_YN,");
sb1.append("                  A.RETURN_MAN_SEQ,");
sb1.append("                  I_2.ENTP_MAN_NAME AS RETURN_MAN_NAME,");
sb1.append("                  (CASE WHEN I_2.ENTP_MAN_DDD IS NULL  THEN '' ELSE I_2.ENTP_MAN_DDD||'-'||I_2.ENTP_MAN_TEL1||'-'||I_2.ENTP_MAN_TEL2 END) || (CASE WHEN I_2.ENTP_MAN_TEL3 IS NULL THEN '' ELSE '['||I_2.ENTP_MAN_TEL3||']' END) AS RETURN_MAN_TEL,");
sb1.append("                  FUN_ADD_POSTADDR(I_2.POST_NO, I_2.POST_SEQ, I_2.ADDR) AS RETURN_MAN_ADDR,");
sb1.append("                  SUBSTR(A.NOREST_ALLOT_MONTHS, 1, 1) as ifi_1,");
sb1.append("                  SUBSTR(A.NOREST_ALLOT_MONTHS, 2, 1) as ifi_2,");
sb1.append("                  SUBSTR(A.NOREST_ALLOT_MONTHS, 3, 1) as ifi_3,");
sb1.append("                  SUBSTR(A.NOREST_ALLOT_MONTHS, 4, 1) as ifi_4,");
sb1.append("                  SUBSTR(A.NOREST_ALLOT_MONTHS, 5, 1) as ifi_5,");
sb1.append("                  SUBSTR(A.NOREST_ALLOT_MONTHS, 6, 1) as ifi_6,");
sb1.append("                  SUBSTR(A.NOREST_ALLOT_MONTHS, 7, 1) as ifi_7,");
sb1.append("                  SUBSTR(A.NOREST_ALLOT_MONTHS, 8, 1) as ifi_8,");
sb1.append("                  SUBSTR(A.NOREST_ALLOT_MONTHS, 9, 1) as ifi_9,");
sb1.append("                  SUBSTR(A.NOREST_ALLOT_MONTHS, 10, 1) as ifi_10,");
sb1.append("                  SUBSTR(A.NOREST_ALLOT_MONTHS, 11, 1) as ifi_11,");
sb1.append("                  SUBSTR(A.NOREST_ALLOT_MONTHS, 12, 1) as ifi_12,");
sb1.append("                  E.MAKECO_NAME,");
sb1.append("                  F.BRAND_NAME,");
sb1.append("                  G.CODE_NAME AS ORIGIN_NAME,");
sb1.append("                  A.CUST_DC_YN,");
sb1.append("                  A.SAMPLE_YN,");
sb1.append("                  TO_CHAR(A.SALE_START_DATE ,'YYYY/MM/DD HH24:MI:SS') AS SALE_START_DATE,");
sb1.append("                  A.V_VOLUME,");
sb1.append("                  A.MASTER_CODE,");
sb1.append("                  H.GOODS_NAME AS MASTER_NAME,");
sb1.append("                  A.IN_UNIT,");
sb1.append("                  A.IN_UNIT_QTY,");
sb1.append("                  A.COMMENT_CNT,");
sb1.append("                  A.WEIGHT,");
sb1.append("                  A.FORM_CODE AS FORM_GROUP,");
sb1.append("                  J.CSPF_DESC AS FORM_GROUP_NAME,");
sb1.append("                  A.SIZE_CODE AS SIZE_GROUP,");
sb1.append("                  K.CSPF_DESC AS SIZE_GROUP_NAME,                                                                    ");
sb1.append("                  A.DELY_BOX_QTY,");
sb1.append("                  NVL(C.TAX_RATE, (SELECT VAL FROM TCONFIG CF WHERE CF.ITEM = 'DEF_VAT_BUY_RATE')) AS DEF_BUY_VAT_RATE,");
sb1.append("                  DECODE((SELECT COUNT(GOODS_CODE) FROM TORDERSTOCK WHERE GOODS_CODE = A.GOODS_CODE AND TOT_SALE_QTY > 0), 0, 0, 1) AS ORDERSTOCK_SALE_QTY,");
sb1.append("                  A.WMS_WIDTH,");
sb1.append("                   A.WMS_LENGTH,");
sb1.append("                  A.WMS_HEIGHT,");
sb1.append("                  A.WMS_VOLUME,");
sb1.append("                  A.FREE_DELIVERY_YN");
sb1.append("             from TGOODS A,");
sb1.append("                  TGOODSKINDS B,");
sb1.append("                  TENTERPRISE C,");
sb1.append("                  TMD D,");
sb1.append("                  /*TQCKINDS Q,     */");
sb1.append("                  TMAKECOMP E,");
sb1.append("                  TBRAND F,");
sb1.append("                  TCODE G,");
sb1.append("                  TGOODS H,");
sb1.append("                  TENTPUSER I,");
sb1.append("                  TENTPUSER I_1,");
sb1.append("                  TENTPUSER I_2,");
sb1.append("                  TGOODSINFOM J,");
sb1.append("                  TGOODSINFOM K");
sb1.append("            where A.LGROUP = B.LGROUP");
sb1.append("              and A.MGROUP = B.MGROUP");
sb1.append("              and A.SGROUP = B.SGROUP");
sb1.append("              and A.DGROUP = B.DGROUP");
sb1.append("              and A.ENTP_CODE = C.ENTP_CODE");
sb1.append("              and A.MD_CODE = D.MD_CODE");
sb1.append("              /*and A.QC_LGROUP    = Q.QC_LGROUP(+)");
sb1.append("              and A.QC_MGROUP    = Q.QC_MGROUP(+)*/");
sb1.append("              and A.MAKECO_CODE = E.MAKECO_CODE");
sb1.append("              and A.BRAND_CODE = F.BRAND_CODE");
sb1.append("              and A.ORIGIN_CODE = G.CODE_MGROUP");
sb1.append("              and G.CODE_LGROUP = 'B023'");
sb1.append("              and H.GOODS_CODE = A.MASTER_CODE");
sb1.append("              and A.ENTP_CODE  = I.ENTP_CODE");
sb1.append("              and A.ENTP_MAN_SEQ  = I.ENTP_MAN_SEQ");
sb1.append("              and A.ENTP_CODE  = I_1.ENTP_CODE(+)");
sb1.append("              and A.SHIP_MAN_SEQ  = I_1.ENTP_MAN_SEQ(+)");
sb1.append("              and A.ENTP_CODE  = I_2.ENTP_CODE(+)");
sb1.append("              and A.RETURN_MAN_SEQ  = I_2.ENTP_MAN_SEQ(+)");
sb1.append("              and A.FORM_CODE  = J.CSPF_GROUP");
sb1.append("              and A.SIZE_CODE  = K.CSPF_GROUP");
sb1.append("              and A.GOODS_CODE = '104764'");

sb1.append("");
sb2.append("select /* goods.xml : manage.goods.selectGoodsDt4 by Goods */");
sb2.append("                  A.GOODS_CODE,");
sb2.append("                  A.SIGN_SEQ,");
sb2.append("                  to_char(A.APPLY_DATE,'YYYY/MM/DD') as APPLY_DATE,");
sb2.append("                  NVL(A.BUY_PRICE,0) AS BUY_PRICE,");
sb2.append("                  A.BUY_COST,");
sb2.append("                  NVL(A.BUY_VAT,0) AS BUY_VAT,");
sb2.append("                  A.BUY_VAT_RATE,");
sb2.append("                  NVL(A.SALE_PRICE,0) AS SALE_PRICE,");
sb2.append("                  NVL(A.SALE_VAT,0) AS SALE_VAT,");
sb2.append("                  A.SALE_VAT_RATE,");
sb2.append("                  NVL(A.CUST_PRICE,0) AS CUST_PRICE,");
sb2.append("                  NVL(A.SAVEAMT_RATE,0) AS SAVEAMT_RATE,");
sb2.append("                  A.SAVEAMT,");
sb2.append("                  A.SIGN_GB,");
sb2.append("                  to_char(A.INSERT_DATE,'YYYY/MM/DD') as INSERT_DATE,");
sb2.append("                  A.INSERT_ID,");
sb2.append("                  A.SIGN_NO_CODE,");
sb2.append("                  A.SIGN_NO_NOTE,");
sb2.append("                  DECODE(CUST_PRICE,0,0,ROUND(((CUST_PRICE-SALE_PRICE)/CUST_PRICE*100),2)) AS DC_RATE,");
sb2.append("                  DECODE(SALE_PRICE,0,0,ROUND(((SALE_PRICE-SALE_VAT)-(BUY_PRICE-BUY_VAT))/(SALE_PRICE-SALE_VAT)*100,2)) AS MARGIN_RATE,");
sb2.append("                  DECODE(SALE_PRICE,0,0,FUN_REVISE_AMT(SALE_PRICE-SALE_VAT,'V')) AS SALE_REAL_PRICE");
sb2.append("             from TGOODSSIGN A,");
sb2.append("                  TGOODS B");
sb2.append("            where A.GOODS_CODE = B.GOODS_CODE");
sb2.append("              and B.GOODS_CODE = '104764'");

sb3.append("select /* goods.xml : manage.goods.selectGoodsDt5 by Goods */");
sb3.append("                  A.GOODS_CODE,");
sb3.append("                  A.GOODS_NAME,");
sb3.append("                  A.GOODSDT_CODE,");
sb3.append("                  A.BARCODE,");
sb3.append("                  A.COLOR_CODE,");
sb3.append("                  A.COLOR_NAME,");
sb3.append("                  A.COLOR_NAME COMP_COLOR_NAME,");
sb3.append("                  A.SIZE_CODE,");
sb3.append("                  A.SIZE_NAME,");
sb3.append("                  A.SIZE_NAME COMP_SIZE_NAME,");
sb3.append("                  A.PATTERN_CODE,");
sb3.append("                  A.PATTERN_NAME,");
sb3.append("                  A.PATTERN_NAME COMP_PATTERN_NAME,");
sb3.append("                  A.FORM_CODE,");
sb3.append("                  A.FORM_NAME,");
sb3.append("                  A.FORM_NAME COMP_FORM_NAME,");
sb3.append("                  A.OTHER_TEXT,");
sb3.append("                  A.GOODSDT_INFO,");
sb3.append("                  A.SALE_GB,");
sb3.append("                  to_char(A.INSERT_DATE,'YYYY/MM/DD') as INSERT_DATE,");
sb3.append("                  A.INSERT_ID,");
sb3.append("                  to_char(A.MODIFY_DATE,'YYYY/MM/DD') as MODIFY_DATE,");
sb3.append("                  A.MODIFY_ID");
sb3.append("             from TGOODSDT A");
sb3.append("            where A.GOODSDT_CODE > '000'");
sb3.append("              and A.GOODS_CODE = '104764'");
sb3.append("              ");
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
Button ret=new Button();
//ret.setIcon(FontAwesome.USER);
Button ins=new Button();
//ins.setIcon(FontAwesome.ANDROID);

toolmenu.addComponent(ret);
toolmenu.addComponent(ins);
toolmenu.addComponent(new Button("Del"));
toolmenu.addComponent(new Button("Save"));
toolmenu.addComponent(new Button("Print"));
toolmenu.addComponent(new Button("XLS"));
        addComponents(
                new Header("Item Management (Goods)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
        
        
              VerticalLayout tab1 = new VerticalLayout();
              VerticalLayout tab2 = new VerticalLayout();
              VerticalLayout tab3 = new VerticalLayout();
        
         TabSheet tabsheet = new TabSheet();
        tabsheet.addTab(tab1).setCaption("Item Information");
              tab1.addComponent(form);
              tabsheet.addTab(tab2).setCaption("Price Information");
                       tab2.addComponent(new Button("Input"));
                       tab2.addComponent(new Button("Delete"));
                       
              
        
        
        

try{
            SimpleJDBCConnectionPool connectionPool = new SimpleJDBCConnectionPool(
             "oracle.jdbc.OracleDriver",BaseEntity.jdbc,
             BaseEntity.user,BaseEntity.pass,2,5);
             SQLContainer container;
             container = new SQLContainer(new FreeformQuery(
             sb.toString(),connectionPool,"GOODS_CODE"));
             MTable table = new MTable();              
             table.setContainerDataSource(container);
             
             
             container = new SQLContainer(new FreeformQuery(
             sb1.toString(),connectionPool,"GOODS_CODE"));
             MTable table1 = new MTable();              
             table1.setContainerDataSource(container);
             tab2.addComponent(table1);
             
             container = new SQLContainer(new FreeformQuery(
             sb2.toString(),connectionPool,"GOODS_CODE"));
             MTable table2 = new MTable();              
             table2.setContainerDataSource(container);
             tab3.addComponent(table2);

             
             container = new SQLContainer(new FreeformQuery(
             sb3.toString(),connectionPool,"GOODS_CODE"));
             MTable table3 = new MTable();              
             table3.setContainerDataSource(container);
             tab3.addComponent(table3);

             table.addMValueChangeListener(new MValueChangeListener() {
             @Override
                      public void valueChange(MValueChangeEvent event) {
                      Notification.show("ss");
                      }
              });
              GridLayout gridtoolbar = new GridLayout(4, 4); 
              addComponents(gridtoolbar);
              gridtoolbar.addComponents(table);
             
              gridtoolbar.addComponents(tabsheet);
              
                       
                               
              
              
              tabsheet.addTab(tab3).setCaption("Unit  Information");
              
 } catch (SQLException e) {
     e.printStackTrace();
  Notification.show(e.getMessage());
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
