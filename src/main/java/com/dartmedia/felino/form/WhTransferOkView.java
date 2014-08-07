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
@CDIView("WhTransferOk")
public class WhTransferOkView extends MVerticalLayout implements View {
//WhTransferOkSvc data=new WhTransferOkSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Request no <p:inputText />                                       **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("/* MASIH SALAH */");
sb.append("            SELECT /* warehousemove.xml : logistics.warehousemove.selectWhTransferSearchDetail by WhTransferSearch */");
sb.append("                OD.GOODS_CODE,");
sb.append("                OD.GOODSDT_CODE,");
sb.append("                G.GOODS_NAME,");
sb.append("                G.GOODSDT_INFO,");
sb.append("                OD.REQUEST_AQTY,");
sb.append("                OD.REQUEST_BQTY,");
sb.append("                NVL(OD.OUT_AQTY, 0) AS OUT_AQTY,");
sb.append("                NVL(OD.OUT_BQTY, 0) AS OUT_BQTY,");
sb.append("                NVL(ID.IN_AQTY , 0) AS IN_AQTY,");
sb.append("                NVL(ID.IN_BQTY , 0) AS IN_BQTY,");
sb.append("                OD.MOVE_OUT_SEQ ");
sb.append("            FROM ");
sb.append("                TWHMOVEOUTDT OD,");
sb.append("                TGOODSDT      G,");
sb.append("                (");
sb.append("                    SELECT ");
sb.append("                        AA.GOODS_CODE,");
sb.append("                        AA.GOODSDT_CODE,");
sb.append("                        AA.MOVE_IN_SEQ,");
sb.append("                        SUM(AA.IN_AQTY) AS IN_AQTY,");
sb.append("                        SUM(AA.IN_BQTY) AS IN_BQTY,");
sb.append("                        BB.MOVE_OUT_SEQ");
sb.append("                    FROM ");
sb.append("                        TWHMOVEINDT AA,");
sb.append("                        TWHMOVEINM BB");
sb.append("                    WHERE ");
sb.append("                        AA.MOVE_IN_SEQ = BB.MOVE_IN_SEQ");
sb.append("                    AND BB.MOVE_IN_SEQ = 201212060215");
sb.append("                    GROUP BY ");
sb.append("                        AA.GOODS_CODE,");
sb.append("                        AA.GOODSDT_CODE,");
sb.append("                        AA.MOVE_IN_SEQ,");
sb.append("                        BB.MOVE_OUT_SEQ");
sb.append("                ) ID");
sb.append("            WHERE ");
sb.append("                OD.MOVE_OUT_SEQ = Id.MOVE_OUT_SEQ(+) ");
sb.append("            AND OD.GOODS_CODE   = G.GOODS_CODE");
sb.append("            AND OD.GOODSDT_CODE = G.GOODSDT_CODE");
sb.append("            AND ID.GOODS_CODE(+)   = OD.GOODS_CODE");
sb.append("            AND ID.GOODSDT_CODE(+) = OD.GOODSDT_CODE");
sb.append("            AND OD.MOVE_OUT_SEQ = 201212060215        ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new TextField("Promo Name"));


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
                new Header("Wh Transfer Out (WhTransferOk)"
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
