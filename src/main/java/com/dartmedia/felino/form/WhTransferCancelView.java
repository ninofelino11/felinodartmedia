package com.dartmedia.felino.form;
import com.cware.back.common.BaseEntity;
import com.vaadin.cdi.CDIView;
import com.vaadin.event.LayoutEvents;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.navigator.View;
import com.vaadin.ui.RichTextArea;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import javax.annotation.PostConstruct;
import java.sql.SQLException;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.fields.MValueChangeEvent;
import org.vaadin.maddon.fields.MValueChangeListener;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("WhTransferCancel")
public class WhTransferCancelView extends MVerticalLayout implements View {
//WhTransferCancelSvc data=new WhTransferCancelSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Move out no <p:inputText />                                                     Move order date <p:calendar></p:calendar>**/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("SELECT /* warehousemove.xml : logistics.warehousemove.selectWhTransferCancelHd by WhTransferCancel */");
sb.append("               MOVE_OUT_SEQ,");
sb.append("               WH_IN_CODE,");
sb.append("               WH_OUT_CODE,");
sb.append("               TO_CHAR(REQUEST_DATE, 'YYYY/MM/DD') AS REQUEST_DATE,");
sb.append("               REQUEST_REASON,");
sb.append("               CANCEL_REASON,");
sb.append("               DO_FLAG,");
sb.append("               '0' AS CANCEL_YN");
sb.append("          FROM TWHMOVEOUTM                               ");
sb.append("         WHERE 1 = 1");
sb.append("/*         <if test='request_no ");
sb.append("=");
sb.append("null");
sb.append("and");
sb.append("request_no");
//sb.append("= ""'>");
sb.append("           AND MOVE_OUT_SEQ = #{request_no, jdbcType=VARCHAR}");
sb.append("         </if> ");
sb.append("         <if test='request_date_fr ");
sb.append("=");
sb.append("null");
sb.append("and");
sb.append("request_date_fr");
//sb.append("= ""'> */");
sb.append("           AND REQUEST_DATE >= TO_DATE('2013/11/01', 'YYYY/MM/DD')");
sb.append("           AND REQUEST_DATE < TO_DATE('2013/11/30', 'YYYY/MM/DD') + 1");
sb.append("           AND DO_FLAG IN ('15')      ");
sb.append("/*         </if>          */  ");
sb.append("         ORDER BY MOVE_OUT_SEQ DESC, WH_OUT_CODE                ;");
sb.append("        ");
sb.append("SELECT /* warehousemove.xml : logistics.warehousemove.selectWhTransferCancel by WhTransferCancel */");
sb.append("               A.GOODS_CODE  AS GOODS_CODE,             ");
sb.append("               A.GOODSDT_CODE,                         ");
sb.append("               B.GOODS_NAME,                         ");
sb.append("               B.GOODSDT_INFO,                         ");
sb.append("               A.REQUEST_AQTY,                         ");
sb.append("               A.REQUEST_BQTY                          ");
sb.append("          FROM TWHMOVEOUTDT      A,                     ");
sb.append("               TGOODSDT         B                      ");
sb.append("         WHERE A.GOODS_CODE     = B.GOODS_CODE         ");
sb.append("           AND A.GOODSDT_CODE   = B.GOODSDT_CODE     ");
sb.append("           AND A.MOVE_OUT_SEQ   = 201311040023 ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new CheckBox("Indv.Query"));
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new PopupDateField("~"));


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
                new Header("Wh Transfer Order Cancel (WhTransferCancel)"
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
             BaseEntity.user,BaseEntity.pass,2,5);
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
