package com.dartmedia.felino.form;
import com.cware.back.common.BaseEntity;
import com.dartmedia.felino.Tdescribecode;
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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.fields.MValueChangeEvent;
import org.vaadin.maddon.fields.MValueChangeListener;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("BaljuSearch")
public class BaljuSearchView extends MVerticalLayout implements View {
//BaljuSearchSvc data=new BaljuSearchSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**PO No <p:inputText />Term <p:calendar /> ~ <p:calendar />    MD <p:inputText />                                    Vendor <p:inputText /> ~ <p:inputText />             Step <p:selectOneListbox id="basic"> </p:selectOneListbox>                                                          **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append(" select /* sql_logistics.entporder.base.xml : logistics.entporder.base.selectBaljuSearch by BaljuSearch */");
sb.append("                   A.BALJU_NO,");
sb.append("                 TO_CHAR(A.BALJU_DATE, 'YYYY/MM/DD')  BALJU_DATE,");
sb.append("                 A.MD_CODE,");
sb.append("                 G.MD_NAME,");
sb.append("                 A.ENTP_CODE,");
sb.append("                 A.ENTP_BALJU_SEQ,");
sb.append("                 B.ENTP_NAME,");
sb.append("                 A.WH_CODE,");
sb.append("                 TO_CHAR(A.CTRL_IN_DATE, 'YYYY/MM/DD')  CTRL_IN_DATE,");
sb.append("                 TO_CHAR(A.LOOK_DATE,    'YYYY/MM/DD')  LOOK_DATE,");
sb.append("                 TO_CHAR(A.IN_END_DATE,  'YYYY/MM/DD')  IN_END_DATE,");
sb.append("                 C.IN_MAN_ID,");
sb.append("                 C.IN_NO,");
sb.append("                 D.USER_NAME,");
sb.append("                 A.LOOK_YN,");
sb.append("                 A.IN_END_YN,");
sb.append("                 A.CANCEL_YN,");
sb.append("                 0 AS SEND_FLAG,");
sb.append("                 DECODE(F.ENTP_MAN_TEL1, NULL, B.ENTP_DDD,  F.ENTP_MAN_DDD) AS DDD,");
sb.append("                 DECODE(F.ENTP_MAN_TEL1, NULL, B.ENTP_TEL1, F.ENTP_MAN_TEL1) AS TEL1,");
sb.append("                 DECODE(F.ENTP_MAN_TEL1, NULL, B.ENTP_TEL2, F.ENTP_MAN_TEL2) AS TEL2,");
sb.append("                 DECODE(F.ENTP_MAN_FAX1, NULL, B.ENTP_FAX1, F.ENTP_MAN_FAX1) AS FAX1,");
sb.append("                 DECODE(F.ENTP_MAN_FAX1, NULL, B.ENTP_FAX2, F.ENTP_MAN_FAX2) AS FAX2,");
sb.append("                 DECODE(F.ENTP_MAN_FAX1, NULL, B.ENTP_FAX3, F.ENTP_MAN_FAX3) AS FAX3,");
sb.append("                 B.S_IDNO,");
sb.append("                 A.OLD_BALJU_NO,");
sb.append("                 A.BALJU_SEQ,");
sb.append("                 H.USER_NAME AS BALJU_NAME,");
sb.append("                 F.EMAIL_ADDR,");
sb.append("                 A.AUTO_YN");
sb.append("            from TBALJUM     A,");
sb.append("                 TENTERPRISE B,");
sb.append("                 TINM        C,");
sb.append("                 TUSER       D,");
sb.append("                 TENTPUSER   F,");
sb.append("                 TMD         G,");
sb.append("                 TUSER       H");
sb.append("          where A.ENTP_CODE    = B.ENTP_CODE");

sb.append("            and A.BALJU_NO     = C.BALJU_NO");
sb.append("            and C.IN_MAN_ID    = D.USER_ID");
sb.append("            and A.ENTP_CODE    = F.ENTP_CODE");
sb.append("            and A.ENTP_MAN_SEQ = F.ENTP_MAN_SEQ");
sb.append("            and A.MD_CODE      = G.MD_CODE");
sb.append("            and A.INSERT_ID    = H.USER_ID");
sb.append("            and A.BALJU_DATE between to_date('2014/01/01', 'YYYY/MM/DD') and to_date('2014/08/01', 'YYYY/MM/DD')");
//sb.append("            and A.WH_CODE   like '%%'");
//sb.append("            and A.MD_CODE   like '%%'");
//sb.append("            and A.ENTP_CODE like '%%'  ");
sb1.append(" select /* sql_logistics.entporder.base.xml : logistics.entporder.base.selectBaljuDetail by BaljuSearch */");
sb1.append("                 E.LGROUP,");
sb1.append("                 E.MGROUP,");
sb1.append("                 A.GOODS_CODE,");
sb1.append("                 A.GOODSDT_CODE,");
sb1.append("                 B.GOODS_NAME,");
sb1.append("                 B.GOODSDT_INFO,");
sb1.append("                 A.BALJU_QTY,");
sb1.append("                 C.BUY_PRICE,");
sb1.append("                 A.ENTP_CONF_QTY,");
sb1.append("                 NVL(D.IN_QTY, 0) AS IN_QTY,");
sb1.append("                 C.BUY_PRICE * A.BALJU_QTY AS BALJU_AMT,");
sb1.append("                 B.BARCODE");
sb1.append("            from TBALJUDT       A,");
sb1.append("                 TGOODSDT       B,");
sb1.append("                 TGOODSPRICE    C,");
sb1.append("                 TINDT          D,");
sb1.append("                 TGOODS         E");
sb1.append("           where A.GOODS_CODE   = B.GOODS_CODE");
sb1.append("             and A.GOODSDT_CODE = B.GOODSDT_CODE");
sb1.append("             and A.GOODS_CODE   = C.GOODS_CODE");
sb1.append("             and B.GOODS_CODE   = E.GOODS_CODE");
sb1.append("             and A.BALJU_NO    = 201403171305");
sb1.append("             and C.APPLY_DATE   = ( select MAX(F.APPLY_DATE)");
sb1.append("                                      from TGOODSPRICE F");
sb1.append("                                     where F.GOODS_CODE  = A.GOODS_CODE");
sb1.append("                                       and F.APPLY_DATE <= to_date('2014/03/17', 'YYYY/MM/DD')");
sb1.append("                                   )");
sb1.append("             and A.GOODS_CODE   = D.GOODS_CODE(+)");
sb1.append("             and A.GOODSDT_CODE = D.GOODSDT_CODE(+)");
sb1.append("             and D.IN_NO(+)   = 201403171305 ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();

//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new CheckBox(""));
toolbar.addComponent(new TextField("Po No"));
toolbar.addComponent(new PopupDateField("Term"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new TextField("MD"));
toolbar.addComponent(new TextField(""));


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
                new Header("PO Process (BaljuSearch)"
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
             // MTable table= new MTable("MENU",container);
               MTable table = new MTable("Menu", container);
          //     table.withColumnHeaders("BALJU_NO");
               table.setContainerDataSource(container);
          //     table.addMValueChangeListener(new MValueChangeListenerImpl());
            // "No.","PO No","Seq","Vendor Name","Receipt WareHouse","Exprcted Date",""Receipt Date","Step"
             // ,"Condition","Print","Auto","MD Name","PO ENTRY","Staff Email"
               
table.addMValueChangeListener(new MValueChangeListener() {
    @Override
    public void valueChange(MValueChangeEvent event) {
    Notification.show("ss");
//    form.setEntity(event.getValue());
    }
   });
               
              addComponents(table);
 } catch (SQLException e) {
     e.printStackTrace();
     RichTextArea rtarea = new RichTextArea();
     rtarea.setValue(sb.toString());
      addComponents(rtarea);
}


try{
            
            SimpleJDBCConnectionPool connectionPool1 = new SimpleJDBCConnectionPool(
             "oracle.jdbc.OracleDriver",BaseEntity.jdbc,
             "dartmedia", "dartmedia",2,5);
             SQLContainer container1;
              container1 = new SQLContainer(new FreeformQuery(
              sb1.toString(),connectionPool1,"LGROUP"));
             // MTable table= new MTable("MENU",container);
               MTable table1 = new MTable("Menu", container1);
          //     table.withColumnHeaders("BALJU_NO");
               table1.setContainerDataSource(container1);
               //"No","L Grp","M Grp","Item Code","Item Name","Unit","Unit info","PO QTY","PO Unit price","PO Amount","Receipt"
              addComponents(table1);
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
