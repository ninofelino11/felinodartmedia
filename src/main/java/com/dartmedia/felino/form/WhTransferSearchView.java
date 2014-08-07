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
@CDIView("WhTransferSearch")
public class WhTransferSearchView extends MVerticalLayout implements View {
//WhTransferSearchSvc data=new WhTransferSearchSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Move out no <p:inputText></p:inputText>Inquiry <p:selectOneListbox>  </p:selectOneListbox>Term <p:calendar></p:calendar>Proc step     <p:selectOneListbox>  </p:selectOneListbox>                         Shipment warehouse <p:selectOneListbox>  </p:selectOneListbox>   Item code  <p:inputText></p:inputText> Receipt warehouse <p:selectOneListbox></p:selectOneListbox>                   **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("              SELECT /* warehousemove.xml : logistics.warehousemove.selectWhTransferSearch by WhTransferSearch */");
sb.append("                        OM.MOVE_OUT_SEQ,");
sb.append("                        IM.MOVE_IN_SEQ,");
sb.append("                        OM.WH_IN_CODE,");
sb.append("                        OM.WH_OUT_CODE,");
sb.append("                        OM.DO_FLAG,");
sb.append("                        TO_CHAR(OM.REQUEST_DATE, 'YYYY/MM/DD') AS REQUEST_DATE,");
sb.append("                        TO_CHAR(OM.OUT_DATE, 'YYYY/MM/DD') AS OUT_DATE,");
sb.append("                        TO_CHAR(IM.IN_DATE, 'YYYY/MM/DD') AS IN_DATE,");
sb.append("                        TO_CHAR(OM.CANCEL_DATE, 'YYYY/MM/DD') AS CANCEL_DATE,");
sb.append("                        OM.REQUEST_REASON,");
sb.append("                        OM.CANCEL_REASON");
sb.append("                    FROM");
sb.append("                        TWHMOVEOUTM OM,");
sb.append("                        TWHMOVEINM  IM,");
sb.append("                        TWHMOVEOUTDT OT");
sb.append("                  WHERE OM.MOVE_OUT_SEQ = IM.MOVE_OUT_SEQ(+)");
sb.append("                    AND OM.MOVE_OUT_SEQ = OT.MOVE_OUT_SEQ(+)");
sb.append("                    AND OT.GOODS_CODE LIKE '%%'    ");
sb.append("                                    ");
sb.append("                    AND OM.REQUEST_DATE >= TO_DATE('2013/11/01', 'YYYY/MM/DD')");
sb.append("                    AND OM.REQUEST_DATE <= TO_DATE('2013/11/05', 'YYYY/MM/DD')");
sb.append("                AND OM.WH_OUT_CODE = '001';          ");
sb.append("                 ");
sb.append("/* KALAU DIBAWAH INI DIPAKAI, TIGA BARIS DIATAS DIHAPUS");
//sb.append("<choose>");
//sb.append("<when");
//sb.append("test='query_gb");
sb.append("==");
//sb.append(""10"'>");
//sb.append("<![CDATA[");
sb.append("AND");
sb.append("OM.REQUEST_DATE");
sb.append(">=");
sb.append("TO_DATE(#{request_date_fr,");
sb.append("jdbcType=VARCHAR},");
sb.append("'YYYY/MM/DD')");
sb.append("AND");
sb.append("OM.REQUEST_DATE");
sb.append("<=");
sb.append("TO_DATE(#{request_date_to,");
sb.append("jdbcType=VARCHAR},");//
sb.append("'YYYY/MM/DD')");
//sb.append("]]>");
//sb.append("</when>");
//sb.append("<when");
//sb.append("test='query_gb");
//sb.append("==");
//sb.append(""40"'>");
//sb.append("<![CDATA[");
//sb.append("AND");
//sb.append("OM.OUT_DATE");
//sb.append(">=");
//sb.append("TO_DATE(#{request_date_fr,");
//sb.append("jdbcType=VARCHAR},");
//sb.append("'YYYY/MM/DD')");
//sb.append("AND");
//sb.append("OM.OUT_DATE");
//sb.append("<=");
//sb.append("TO_DATE(#{request_date_to,");
//sb.append("jdbcType=VARCHAR},");
//sb.append("'YYYY/MM/DD')");
//sb.append("]]>");
//sb.append("</when>");
//sb.append("<when");
//sb.append("test='query_gb");
//sb.append("==");
//sb.append(""50"'>");
//sb.append("<![CDATA[");
//sb.append("AND");
//sb.append("IM.IN_DATE");
//sb.append(">=");
//sb.append("TO_DATE(#{request_date_fr,");
//sb.append("jdbcType=VARCHAR},");
//sb.append("'YYYY/MM/DD')");
//sb.append("AND");
//sb.append("IM.IN_DATE");/
//sb.append("<=");
//sb.append("TO_DATE(#{request_date_to,");
//sb.append("jdbcType=VARCHAR},");
//sb.append("'YYYY/MM/DD')");
//sb.append("]]>");
//sb.append("</when>");
//sb.append("<otherwise>");
//sb.append("<![CDATA[");
//sb.append("AND");
//sb.append("OM.CANCEL_DATE");
//sb.append(">=");
sb.append("TO_DATE(#{request_date_fr,");
sb.append("jdbcType=VARCHAR},");
sb.append("'YYYY/MM/DD')");
sb.append("AND");
sb.append("OM.CANCEL_DATE");
sb.append("<=");
sb.append("TO_DATE(#{request_date_to,");
sb.append("jdbcType=VARCHAR},");
sb.append("'YYYY/MM/DD')");
sb.append("]]>");
sb.append("</otherwise>");
sb.append("</choose>");
sb.append("<if");
//sb.append("test="do_flag");
//sb.append("= ''">");
sb.append("                <if test='do_flag ");
sb.append("=");
//sb.append(""10"'>");
sb.append("AND");
sb.append("OM.DO_FLAG");
sb.append("=");
sb.append("#{do_flag,");
sb.append("jdbcType=VARCHAR}");
sb.append("</if>");
sb.append("<if");
sb.append("test='do_flag");
sb.append("==");
//sb.append(""10"'>");
sb.append("AND");
sb.append("OM.DO_FLAG");
sb.append("IN");
sb.append("(#{do_flag,");
sb.append("jdbcType=VARCHAR},'15')");
sb.append("</if>");
sb.append("</if>");
sb.append("<if");
//sb.append("test="wh_code_out");
//sb.append("= ''">");
sb.append("                AND OM.WH_OUT_CODE = #{wh_code_out, jdbcType=VARCHAR}");
sb.append("            </if>");
//sb.append("            <if test="wh_code_in ");
sb.append("=");
//sb.append("''">");
sb.append("AND");
sb.append("OM.WH_IN_CODE");
sb.append("=");
sb.append("#{wh_code_in,");
sb.append("jdbcType=VARCHAR}");
sb.append("</if>");
sb.append("*/");
sb.append("SELECT");
sb.append("/*");
sb.append("warehousemove.xml");
sb.append(":");
sb.append("logistics.warehousemove.selectWhTransferSearchDetail");
sb.append("by");
sb.append("WhTransferSearch");
sb.append("*/");
sb.append("OD.GOODS_CODE,");
sb.append("OD.GOODSDT_CODE,");
sb.append("G.GOODS_NAME,");
sb.append("G.GOODSDT_INFO,");
sb.append("OD.REQUEST_AQTY,");
sb.append("OD.REQUEST_BQTY,");
sb.append("NVL(OD.OUT_AQTY,");
sb.append("0)");
sb.append("AS");
sb.append("OUT_AQTY,");
sb.append("NVL(OD.OUT_BQTY,");
sb.append("0)");
sb.append("AS");
sb.append("OUT_BQTY,");
sb.append("NVL(ID.IN_AQTY");
sb.append(",");
sb.append("0)");
sb.append("AS");
sb.append("IN_AQTY,");
sb.append("NVL(ID.IN_BQTY");
sb.append(",");
sb.append("0)");
sb.append("AS");
sb.append("IN_BQTY,");
sb.append("OD.MOVE_OUT_SEQ");
sb.append("FROM");
sb.append("TWHMOVEOUTDT");
sb.append("OD,");
sb.append("TGOODSDT");
sb.append("G,");
sb.append("(");
sb.append("SELECT");
sb.append("AA.GOODS_CODE,");
sb.append("AA.GOODSDT_CODE,");
sb.append("AA.MOVE_IN_SEQ,");
sb.append("SUM(AA.IN_AQTY)");
sb.append("AS");
sb.append("IN_AQTY,");
sb.append("SUM(AA.IN_BQTY)");
sb.append("AS");
sb.append("IN_BQTY,");
sb.append("BB.MOVE_OUT_SEQ");
sb.append("FROM");
sb.append("TWHMOVEINDT");
sb.append("AA,");
sb.append("TWHMOVEINM");
sb.append("BB");
sb.append("WHERE");
sb.append("AA.MOVE_IN_SEQ");
sb.append("=");
sb.append("BB.MOVE_IN_SEQ");
sb.append("AND");
sb.append("BB.MOVE_IN_SEQ");
sb.append("=");
sb.append("201311010001");
sb.append("GROUP");
sb.append("BY");
sb.append("AA.GOODS_CODE,");
sb.append("AA.GOODSDT_CODE,");
sb.append("AA.MOVE_IN_SEQ,");
sb.append("BB.MOVE_OUT_SEQ");
sb.append(")");
sb.append("ID");
sb.append("WHERE");
sb.append("OD.MOVE_OUT_SEQ");
sb.append("=");
sb.append("Id.MOVE_OUT_SEQ(+)");
sb.append("AND");
sb.append("OD.GOODS_CODE");
sb.append("=");
sb.append("G.GOODS_CODE");
sb.append("AND");
sb.append("OD.GOODSDT_CODE");
sb.append("=");
sb.append("G.GOODSDT_CODE");
sb.append("AND");
sb.append("ID.GOODS_CODE(+)");
sb.append("=");
sb.append("OD.GOODS_CODE");
sb.append("AND");
sb.append("ID.GOODSDT_CODE(+)");
sb.append("=");
sb.append("OD.GOODSDT_CODE");
sb.append("AND");
sb.append("OD.MOVE_OUT_SEQ");
sb.append("=");
sb.append("201311010001");
sb.append(";         ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new TextField("Out No"));
toolbar.addComponent(new ComboBox("Inquiry"));
toolbar.addComponent(new PopupDateField("Term"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new ComboBox("Proc. Step"));
toolbar.addComponent(new ComboBox("Warehouse"));
toolbar.addComponent(new TextField("Item Code"));
toolbar.addComponent(new CheckBox("Receipt WareHouse"));


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
                new Header("Wh Transfer Report (WhTransferSearch)"
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
