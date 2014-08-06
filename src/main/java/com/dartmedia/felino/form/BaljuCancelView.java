package com.dartmedia.felino.form;
import com.cware.back.common.BaseEntity;
import com.vaadin.cdi.CDIView;
import com.vaadin.event.LayoutEvents;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.PopupDateField;
import com.vaadin.navigator.View;
import com.vaadin.ui.RichTextArea;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.ui.Notification;
import javax.annotation.PostConstruct;
import java.sql.SQLException;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.fields.MValueChangeEvent;
import org.vaadin.maddon.fields.MValueChangeListener;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("BaljuCancel")
public class BaljuCancelView extends MVerticalLayout implements View {
//BaljuCancelSvc data=new BaljuCancelSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**PO No <p:inputText />Term <p:calendar /> ~ <p:calendar />    MD <p:inputText />                                    Vendor <p:inputText /> ~ <p:inputText />**/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append(" select /* sql_logistics.entporder.base.xml : logistics.entporder.base.selectBaljuCancel by BaljuCancel */");
sb.append("                 A.BALJU_NO,");
sb.append("                 A.ENTP_BALJU_SEQ,");
sb.append("                 B.ENTP_NAME,");
sb.append("                 TO_CHAR(A.CTRL_IN_DATE, 'YYYY/MM/DD') AS CTRL_IN_DATE,");
sb.append("                 TO_CHAR(A.LOOK_DATE, 'YYYY/MM/DD') AS LOOK_DATE,");
sb.append("                 A.CANCEL_CODE,");
sb.append("                 A.CANCEL_NOTE,");
sb.append("                 C.ENTP_MAN_NAME,");
sb.append("                 DECODE(C.ENTP_MAN_TEL1, NULL, B.ENTP_DDD,   C.ENTP_MAN_DDD)   AS ENTP_MAN_DDD ,");
sb.append("                 DECODE(C.ENTP_MAN_TEL1, NULL, B.ENTP_TEL1,  C.ENTP_MAN_TEL1)  AS ENTP_MAN_TEL1,");
sb.append("                 DECODE(C.ENTP_MAN_TEL1, NULL, B.ENTP_TEL2,  C.ENTP_MAN_TEL2)  AS ENTP_MAN_TEL2,");
sb.append("                 DECODE(C.ENTP_MAN_TEL1, NULL, B.ENTP_TEL3,  C.ENTP_MAN_TEL3)  AS ENTP_MAN_TEL3,");
sb.append("                 A.CANCEL_YN,");
sb.append("                 A.CANCEL_ID,");
sb.append("                 TO_CHAR(A.CANCEL_DATE, 'YYYY/MM/DD') AS CANCEL_DATE,");
sb.append("                 A.IN_END_YN,");
sb.append("                 A.WH_CODE");
sb.append("            from TBALJUM     A,");
sb.append("                 TENTERPRISE B,");
sb.append("                 TENTPUSER   C");
sb.append("           where A.ENTP_CODE    = B.ENTP_CODE");
sb.append("             and A.ENTP_CODE    = C.ENTP_CODE");
sb.append("             and A.ENTP_MAN_SEQ = C.ENTP_MAN_SEQ");
sb.append("             and A.CANCEL_YN = '0' AND A.IN_END_YN = '0'");
sb.append("             and A.BALJU_DATE BETWEEN TO_DATE('2014/01/01','YYYY/MM/DD') AND TO_DATE('2014/08/01','YYYY/MM/DD')");
sb.append("             and A.MD_CODE    like '%%'");
sb.append("             and A.ENTP_CODE  like '%%' ");
sb.append("                                         ");

sb1.append(" select /* sql_logistics.entporder.base.xml : logistics.entporder.base.selectBaljuCancelDt by BaljuCancel */");
sb1.append("                   E.LGROUP,");
sb1.append("                 E.MGROUP,");
sb1.append("                 B.GOODS_CODE,");
sb1.append("                 B.GOODSDT_CODE,");
sb1.append("                 C.GOODS_NAME,");
sb1.append("                 C.GOODSDT_INFO,");
sb1.append("                 B.BALJU_QTY,");
sb1.append("                 D.BUY_PRICE,");
sb1.append("                 B.BALJU_QTY * D.BUY_PRICE AS BALJU_AMT,");
sb1.append("                 B.ENTP_CONF_QTY");
sb1.append("            from TBALJUM     A,");
sb1.append("                 TBALJUDT    B,");
sb1.append("                 TGOODSDT    C,");
sb1.append("                 TGOODSPRICE D,");
sb1.append("                 TGOODS      E");
sb1.append("           where A.BALJU_NO     = 201403071297");
sb1.append("             and A.BALJU_NO     = B.BALJU_NO");
sb1.append("             and B.GOODS_CODE   = C.GOODS_CODE");
sb1.append("             and B.GOODS_CODE   = E.GOODS_CODE");
sb1.append("             and B.GOODSDT_CODE = C.GOODSDT_CODE");
sb1.append("             and B.GOODS_CODE   = D.GOODS_CODE");
sb1.append("             and D.APPLY_DATE   = ( select MAX(E.APPLY_DATE)");
sb1.append("                                      from TGOODSPRICE E");
sb1.append("                                     where E.GOODS_CODE  = B.GOODS_CODE");
sb1.append("                                       and E.APPLY_DATE <= A.BALJU_DATE");
sb1.append("                                      )  ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new CheckBox(""));
toolbar.addComponent(new TextField("Po No."));
toolbar.addComponent(new PopupDateField("Term"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new TextField("Md"));// MD Search No MD Code,Md Name
// Table "No","PO No","Seq.","Vendor Name","Expected Date","Cancel","Cancel Reason","Cancell Reason List";

toolbar.addComponent(new TextField("Vendor")); //Vendor Query "NO",VENDOR NAME bUSSINES REGIONAL", "Rep Name"
//---
toolbar.addComponent(new TextField("Vendor Rep"));
toolbar.addComponent(new TextField("Staff Telp"));
// "No.","L Grp.","M Grp","Item Code","Unit","Item Name","Unit Info","PO Qty","PO Unit Price","PO Amount"


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
                new Header("PO Cancel (BaljuCancel)"
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
             "oracle.jdbc.OracleDriver",BaseEntity.jdbc,
             "dartmedia", "dartmedia",2,5);
             SQLContainer container;
              container = new SQLContainer(new FreeformQuery(
              sb.toString(),connectionPool,"BALJU_NO"));
             MTable table= new MTable("MENU",container);
               table.setContainerDataSource(container); 
              addComponents(table);
              //"No","PO No.","Seq.","Vendor Name","Expected Date","Cancell","Cancel Reason","Cancel Reason List"
            table.addMValueChangeListener(new MValueChangeListener() {
    @Override
    public void valueChange(MValueChangeEvent event) {
    Notification.show("ss");
//    form.setEntity(event.getValue());
    }
   });
          
 } catch (SQLException e) {
     e.printStackTrace();
     RichTextArea rtarea = new RichTextArea();
     rtarea.setValue(sb.toString());
      addComponents(rtarea);
}

//Grid grid = new Grid(c);
  //      grid.setWidth("100%");
    //    grid.setHeight("100%");

// Vendor Rep, Staff TeL
try{
            SimpleJDBCConnectionPool connectionPool = new SimpleJDBCConnectionPool(
             "oracle.jdbc.OracleDriver",BaseEntity.jdbc,
             "dartmedia", "dartmedia",2,5);
             SQLContainer container1;
              container1 = new SQLContainer(new FreeformQuery(
              sb1.toString(),connectionPool,"BALJU_NO"));
             MTable table1= new MTable("MENU",container1);
               table1.setContainerDataSource(container1);
               // "No","L GRP","M GRP","Item Code","Unit","Item Name","Unit info.","PO qty","PO Unit Price","PO Amount"
             
               
               
               
               addComponents(table1);
 } catch (SQLException e) {
     e.printStackTrace();
     RichTextArea rtarea = new RichTextArea();
     rtarea.setValue(sb.toString());
      addComponents(rtarea);
}
// 
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
