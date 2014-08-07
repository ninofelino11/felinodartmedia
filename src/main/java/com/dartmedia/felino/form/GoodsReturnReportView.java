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
@CDIView("GoodsReturnReport")
public class GoodsReturnReportView extends MVerticalLayout implements View {
//GoodsReturnReportSvc data=new GoodsReturnReportSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Return order <p:calendar></p:calendar>     ~ <p:calendar></p:calendar>Return complete <p:calendar></p:calendar>     ~ <p:calendar></p:calendar>Warehouse <p:selectOneListbox></p:selectOneListbox> L grp <p:inputText></p:inputText> M grp <p:inputText></p:inputText> Item <p:inputText></p:inputText>C/S type <p:selectOneListbox></p:selectOneListbox> Vendor <p:inputText></p:inputText>                    **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("/* BEUH... QUERY APA JALAN TOL INI... PANJANG BENER...*/");
sb.append("        SELECT /*slipreturn.xml : logistics.slipreturn.selectGoodsReturnReport by GoodsReturnReport*/");
sb.append("            E.ENTP_CODE,");
sb.append("            F.ENTP_NAME,");
sb.append("            E.GOODS_CODE,");
sb.append("            E.GOODS_NAME,");
sb.append("            TCODE_NAME('J007', E.CLAIM_GB) AS CS_NAME,");
sb.append("            E.CLAIM_CODE || ' ' || H.CODE_NAME AS CLAIM,");
sb.append("            SUM(E.CLAIM_QTY) AS CLAIM_QTY,");
sb.append("            SUM(E.DELY_QTY) AS DELY_QTY,");
sb.append("            SUM(E.RETURN_QTY) AS RETURN_QTY,");
sb.append("            E.MD_CODE,");
sb.append("            E.DELY_TYPE,");
sb.append("            G.MD_NAME");
sb.append("          FROM (");
sb.append("              SELECT");
sb.append("                  B.ENTP_CODE,");
sb.append("                A.GOODS_CODE,");
sb.append("                B.GOODS_NAME,");
sb.append("                A.CLAIM_CODE,");
sb.append("                A.CLAIM_GB,");
sb.append("                B.MD_CODE,");
sb.append("                B.DELY_TYPE,");
sb.append("                SUM(A.SYSLAST) AS CLAIM_QTY,");
sb.append("                0 AS DELY_QTY,");
sb.append("                0 AS RETURN_QTY");
sb.append("              FROM");
sb.append("                  TCLAIMDT A,");
sb.append("                  TGOODS   B");
sb.append("             WHERE A.GOODS_CODE = B.GOODS_CODE");
sb.append("            AND A.CLAIM_DATE >= TO_DATE(#{claim_date_fr, jdbcType=VARCHAR}, 'YYYY/MM/DD')");
sb.append("            AND A.CLAIM_DATE &lt; TO_DATE(#{claim_date_to, jdbcType=VARCHAR}, 'YYYY/MM/DD') + 1");
sb.append("            AND A.WH_CODE = #{wh_code, jdbcType=VARCHAR}");
sb.append("            <choose>");
//sb.append("                <when test="slip_gb ");
sb.append("=");
//sb.append("''">");
sb.append("AND");
sb.append("A.CLAIM_GB");
sb.append("=");
sb.append("#{slip_gb,");
//sb.append("jdbcType=VARCHAR}");
//sb.append("</when>");
//sb.append("<otherwise>");
sb.append("AND");
sb.append("A.CLAIM_GB");
sb.append("IN");
sb.append("('30',");
sb.append("'45')");
//sb.append("</otherwise>");
//sb.append("</choose>");
sb.append("AND");
sb.append("A.SYSLAST");
sb.append(">");
sb.append("0");
//sb.append("<if");
//sb.append("test="goods_code");
//sb.append("= ''">");
sb.append("                AND B.GOODS_CODE = #{goods_code, jdbcType=VARCHAR}");
sb.append("            </if>");
//sb.append("            <if test="lgroup ");
//sb.append("=");
//sb.append("''">");
//sb.append("AND");
//sb.append("B.LGROUP");
//sb.append("=");
//sb.append("#{lgroup,");
//sb.append("jdbcType=VARCHAR}");
//sb.append("</if>");
//sb.append("<if");
//sb.append("test="mgroup");
//sb.append("= ''">");
sb.append("                AND B.MGROUP = #{mgroup, jdbcType=VARCHAR}");
//sb.append("            </if>");
//sb.append("            <if test="entp_code ");
//sb.append("=");
//sb.append("''">");
sb.append("AND");
sb.append("B.ENTP_CODE");
sb.append("=");
sb.append("#{entp_code,");
sb.append("jdbcType=VARCHAR}");
sb.append("</if>");
sb.append("GROUP");
sb.append("BY");
sb.append("B.ENTP_CODE,");
sb.append("A.GOODS_CODE,");
sb.append("B.GOODS_NAME,");
sb.append("A.CLAIM_CODE,");
sb.append("A.CLAIM_GB,");
sb.append("B.MD_CODE,");
sb.append("B.DELY_TYPE");
sb.append("UNION");
sb.append("ALL");
sb.append("SELECT");
sb.append("B.ENTP_CODE,");
sb.append("A.GOODS_CODE,");
sb.append("B.GOODS_NAME,");
sb.append("A.CLAIM_CODE,");
sb.append("A.CLAIM_GB,");
sb.append("B.MD_CODE,");
sb.append("B.DELY_TYPE,");
sb.append("0,");
sb.append("SUM(C.DELY_QTY),");
sb.append("SUM(C.RETURN_QTY)");
sb.append("FROM");
sb.append("TCLAIMDT");
sb.append("A,");
sb.append("TGOODS");
sb.append("B,");
sb.append("TSLIPDT");
sb.append("C,");
sb.append("TSLIPM");
sb.append("D");
sb.append("WHERE");
sb.append("A.GOODS_CODE");
sb.append("=");
sb.append("B.GOODS_CODE");
sb.append("AND");
sb.append("A.ORDER_NO");
sb.append("=");
sb.append("C.ORDER_NO");
sb.append("AND");
sb.append("A.ORDER_G_SEQ");
sb.append("=");
sb.append("C.ORDER_G_SEQ");
sb.append("AND");
sb.append("A.ORDER_D_SEQ");
sb.append("=");
sb.append("C.ORDER_D_SEQ");
sb.append("AND");
sb.append("A.ORDER_W_SEQ");
sb.append("=");
sb.append("C.ORDER_W_SEQ");
sb.append("AND");
sb.append("C.SLIP_I_NO");
sb.append("=");
sb.append("D.SLIP_I_NO");
sb.append("AND");
sb.append("A.WH_CODE");
sb.append("=");
sb.append("#{wh_code,");
sb.append("jdbcType=VARCHAR}");
sb.append("AND");
sb.append("A.CLAIM_DATE");
sb.append(">=");
sb.append("TO_DATE(#{claim_date_fr,");
sb.append("jdbcType=VARCHAR},");
sb.append("'YYYY/MM/DD')");
sb.append("AND");
sb.append("A.CLAIM_DATE");
sb.append("&lt");
sb.append("TO_DATE(#{claim_date_to,");
sb.append("jdbcType=VARCHAR},");
sb.append("'YYYY/MM/DD')");
sb.append("+");
sb.append("1");
sb.append("<choose>");
sb.append("<when");
//sb.append("test="slip_gb");
//sb.append("= ''">");
sb.append("                    AND A.CLAIM_GB = #{slip_gb, jdbcType=VARCHAR}");
//sb.append("                </when>");
//sb.append("                <otherwise>");
sb.append("                        AND A.CLAIM_GB IN ('30', '45')");
//sb.append("                </otherwise>");
//sb.append("            </choose>");
sb.append("            AND A.SYSLAST > 0");
sb.append("            AND D.OUT_CLOSE_YN = '1'");
sb.append("            AND D.SLIP_FLAG = '2'");
sb.append("            AND D.OUT_CLOSE_DATE BETWEEN TO_DATE(#{ok_date_fr, jdbcType=VARCHAR}, 'YYYY/MM/DD')");
sb.append("                                     AND TO_DATE(#{ok_date_to, jdbcType=VARCHAR}, 'YYYY/MM/DD')");
//sb.append("            <if test="goods_code ");
//sb.append("=");
//sb.append("''">");
sb.append("AND");
sb.append("B.GOODS_CODE");
sb.append("=");
//sb.append("#{goods_code,");
//sb.append("jdbcType=VARCHAR}");
//sb.append("</if>");
//sb.append("<if");
//sb.append("test="lgroup");
//sb.append("= ''">");
sb.append("                AND B.LGROUP = #{lgroup, jdbcType=VARCHAR}");
//sb.append("            </if>");
//sb.append("            <if test="mgroup ");
sb.append("=");
//sb.append("''">");
sb.append("AND");
sb.append("B.MGROUP");
sb.append("=");
sb.append("#{mgroup,");
//sb.append("jdbcType=VARCHAR}");
//sb.append("</if>");
//sb.append("<if");
//sb.append("test="entp_code");
//sb.append("= ''">");
//sb.append("                AND B.ENTP_CODE = #{entp_code, jdbcType=VARCHAR}");
//sb.append("            </if>");
sb.append("             GROUP BY");
sb.append("                 B.ENTP_CODE,");
sb.append("                A.GOODS_CODE,");
sb.append("                B.GOODS_NAME,");
sb.append("                A.CLAIM_CODE,");
sb.append("                A.CLAIM_GB,");
sb.append("                B.MD_CODE,");
sb.append("                B.DELY_TYPE");
sb.append("            ) E,");
sb.append("               TENTERPRISE F,");
sb.append("               TMD G,");
sb.append("               TCODE H");
sb.append("         WHERE E.ENTP_CODE = F.ENTP_CODE");
sb.append("           AND E.MD_CODE = G.MD_CODE");
sb.append("           AND ((E.CLAIM_GB = '30' AND H.CODE_LGROUP = 'J013') OR");
sb.append("               (E.CLAIM_GB = '45' AND H.CODE_LGROUP = 'J014'))");
sb.append("           AND E.CLAIM_CODE = H.CODE_MGROUP");
sb.append("           AND E.CLAIM_CODE LIKE #{claim_code, jdbcType=VARCHAR} || '%'");
sb.append("         GROUP BY");
sb.append("             E.ENTP_CODE,");
sb.append("            F.ENTP_NAME,");
sb.append("            E.GOODS_CODE,");
sb.append("            E.GOODS_NAME,");
sb.append("            E.CLAIM_GB,");
sb.append("            E.CLAIM_CODE,");
sb.append("            H.CODE_NAME,");
sb.append("            E.MD_CODE,");
sb.append("            E.DELY_TYPE,");
sb.append("            G.MD_NAME");
sb.append("         ORDER BY E.ENTP_CODE;");
sb.append("*/  ");
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
                new Header("Return Report by item (GoodsReturnReport)"
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
