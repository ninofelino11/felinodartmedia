package com.dartmedia.felino.form;
import com.cware.back.common.BaseEntity;
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
@CDIView("GoodsClaimList")
public class GoodsClaimListView extends MVerticalLayout implements View {
//GoodsClaimListSvc data=new GoodsClaimListSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/****/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("");
sb.append("SELECT /* claiminfo.xml : custcenter.claiminfo.selectGoodsClaimList by GoodsClaimList */");
sb.append("               A.GOODS_CODE   AS GOODS_CODE,     ");
sb.append("               B.GOODS_NAME   AS GOODS_NAME,     ");
sb.append("               C.ENTP_NAME    AS ENTP_NAME,      ");
sb.append("               D.MD_NAME     AS MD_NAME,         ");
sb.append("               A.ORDER_NO    AS ORDER_NO,");
//sb.append("               <choose>");
//sb.append("                       <when test='cs_gubun == "10"'>");
sb.append("                            TO_CHAR(A.CANCEL_DATE, 'YYYY/MM/DD')  AS CANCEL_DATE,");
sb.append("                            A.CANCEL_QTY     AS CANCEL_QTY,      ");
sb.append("                            A.CANCEL_AMT       AS CANCEL_AMT,      ");
sb.append("                            A.CANCEL_CODE    AS CANCEL_CODE,  ");
//sb.append("                       </when>");
//sb.append("                       <otherwise>");
sb.append("                           TO_CHAR(A.CLAIM_DATE, 'YYYY/MM/DD')  AS CANCEL_DATE,");
sb.append("                           A.SYSLAST     AS CANCEL_QTY,      ");
sb.append("                           A.SYSLAST_AMT AS CANCEL_AMT,      ");
sb.append("                           A.CLAIM_CODE  AS CANCEL_CODE,   ");
//sb.append("                       </otherwise>");
//sb.append("               </choose>     ");
sb.append("                 ");
sb.append("               E.CODE_NAME   AS CODE_NAME        ");
sb.append("          FROM TGOODS B,                         ");
sb.append("               TENTERPRISE C,                    ");
sb.append("               TMD D,                            ");
sb.append("               TCODE E,                          ");
sb.append("               TORDERM F,");
//sb.append("               <choose>");
//sb.append("                       <when test='cs_gubun == "10"'>");
sb.append("                           TCANCELDT A");
//sb.append("                       </when>");
//sb.append("                       <otherwise>");
sb.append("                           TCLAIMDT A ");
//sb.append("                       </otherwise>");
sb.append("               </choose>                        ");
sb.append("         WHERE A.ORDER_NO    = F.ORDER_NO        ");
sb.append("           AND A.GOODS_CODE  = B.GOODS_CODE      ");
sb.append("           AND B.ENTP_CODE   = C.ENTP_CODE       ");
sb.append("           AND A.MD_CODE     = D.MD_CODE");
//sb.append("           <choose>");
//sb.append("                   <when test='cs_gubun == "10"'>");
sb.append("                       AND A.CANCEL_CODE  = E.CODE_MGROUP");
sb.append("                       AND E.CODE_LGROUP = 'J012'");
sb.append("                       AND A.CANCEL_DATE BETWEEN TO_DATE('2014-01-01' ,'YYYY/MM/DD')                        ");
sb.append("                                AND TO_DATE('2014-01-01' || '23:59:59','YYYY/MM/DD hh24:mi:ss')       ");
//sb.append("                   </when>");
//sb.append("                   <otherwise>");
sb.append("                   AND A.CLAIM_CODE  = E.CODE_MGROUP");
//sb.append("                   <choose>");
//sb.append("                           <when test='cs_gubun == "20"'>");
sb.append("                               AND E.CODE_LGROUP = 'J013'");
//sb.append("                           </when>");
//sb.append("                           <when test='cs_gubun == "30"'>");
sb.append("                               AND E.CODE_LGROUP = 'J014' AND A.CLAIM_GB = '40'");
//sb.append("                           </when>");
//sb.append("                           <otherwise>");
sb.append("                                  AND E.CODE_LGROUP = 'J029'");
//sb.append("                           </otherwise>");
//sb.append("                   </choose>     ");
sb.append("                      AND A.CLAIM_DATE BETWEEN TO_DATE('2014-01-01' ,'YYYY/MM/DD')                        ");
sb.append("                                AND TO_DATE('2014-01-01' || '23:59:59','YYYY/MM/DD hh24:mi:ss')              ");
//sb.append("                   </otherwise>");
//sb.append("           </choose>         ");
sb.append("           AND A.GOODS_CODE LIKE 'goods_code'                     ");
sb.append("           AND B.LGROUP BETWEEN 'fr_group'  AND 'to_group'");
sb.append("           AND C.ENTP_CODE LIKE 'entp_code'");
sb.append("           AND A.MD_CODE LIKE  'md_code'");
sb.append("           AND A.MEDIA_GB LIKE 'media_gb'        ");
sb.append("           AND F.ORDER_MEDIA LIKE 'order_media'  ");
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
toolmenu.addComponent(new Button("Ret"));
toolmenu.addComponent(new Button("Ins"));
toolmenu.addComponent(new Button("Del"));
toolmenu.addComponent(new Button("Save"));
toolmenu.addComponent(new Button("Print"));
toolmenu.addComponent(new Button("XLS"));
        addComponents(
                new Header("C/S Item Report (GoodsClaimList)"
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
              sb.toString(),connectionPool,"AD_MENU_ID"));
             // MTable table= new MTable("MENU",container);
               MTable table = new MTable();
               table.setContainerDataSource(container);
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
