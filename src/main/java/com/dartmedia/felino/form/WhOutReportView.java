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
@CDIView("WhOutReport")
public class WhOutReportView extends MVerticalLayout implements View {
//WhOutReportSvc data=new WhOutReportSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Warehouse <p:selectOneListbox></p:selectOneListbox> Shipping date <p:calendar></p:calendar> ~ <p:calendar></p:calendar>L grp <p:inputText></p:inputText>             **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append(" SELECT /* outreport.xml : logistics.outreport.selectSlipTotalCount by SlipTotalCount */");
sb.append("            B.CREATE_SEQ,");
sb.append("            B.PRE_DELY_YN,");
sb.append("            C.CODE_MGROUP,");
sb.append("             C.CODE_NAME,");
sb.append("             NVL(SUM(B.WBORDER), 0)  AS WBORDER,");
sb.append("               NVL(SUM(B.WBCHANGE), 0) AS WBCHANGE,");
sb.append("               NVL(SUM(B.WBORDER), 0) + NVL(SUM(B.WBCHANGE), 0) AS TOT");
sb.append("         FROM (");
sb.append("             SELECT");
sb.append("                 TO_NUMBER(A.CREATE_SEQ) AS CREATE_SEQ,");
sb.append("                A.PRE_DELY_YN,");
sb.append("                A.DELY_GB,");
sb.append("                SUM(DECODE(A.SLIP_GB,'104',0,1)) AS WBORDER,");
sb.append("                SUM(DECODE(A.SLIP_GB,'104',1,0)) AS WBCHANGE");
sb.append("            FROM");
sb.append("                TSLIPM A");
sb.append("              WHERE '104'          >= A.SLIP_GB");
sb.append("            AND   A.SLIP_FLAG     = '1'");
sb.append("            AND   A.DELY_TYPE     = '10'");
sb.append("            AND   A.CREATE_DATE   = TO_DATE(#{create_date, jdbcType=VARCHAR}, 'YYYY/MM/DD')");
sb.append("            AND   TO_NUMBER(A.CREATE_SEQ) BETWEEN #{seq_fr, jdbcType=VARCHAR} AND #{seq_to, jdbcType=VARCHAR}");
sb.append("            AND   A.WH_CODE        LIKE #{wh_code, jdbcType=VARCHAR}    ||'%'");
sb.append("            AND   A.SLIP_PROC||''  LIKE #{slip_proc, jdbcType=VARCHAR}    ||'%'");
sb.append("            AND   A.DELY_GB        LIKE #{dely_gb, jdbcType=VARCHAR}    ||'%'");
sb.append("            /* 2011.06.13 진행단계 전체인 경우 아래의 경우만 표현되게(출고,배송완료)*/");
sb.append("            AND EXISTS (SELECT 1");
sb.append("                         FROM TCODE");
sb.append("                        WHERE CODE_LGROUP = 'L023'");
sb.append("                          AND CODE_GROUP = '1'");
sb.append("                          AND CODE_MGROUP NOT IN ('10', '16', '42', '91')");
sb.append("                          AND USE_YN = '1'");
sb.append("                          AND A.SLIP_PROC = CODE_MGROUP )");
sb.append("            GROUP BY");
sb.append("                A.CREATE_SEQ,");
sb.append("                A.PRE_DELY_YN,");
sb.append("                A.DELY_GB");
sb.append("        ) B,");
sb.append("        TCODE C");
sb.append("        WHERE B.DELY_GB     = C.CODE_MGROUP");
sb.append("        AND   C.CODE_LGROUP = 'B005'");
sb.append("        GROUP BY");
sb.append("            B.CREATE_SEQ,");
sb.append("            B.PRE_DELY_YN,");
sb.append("            C.CODE_MGROUP,");
sb.append("            C.CODE_NAME");
sb.append("        ORDER BY");
sb.append("               B.CREATE_SEQ,");
sb.append("            B.PRE_DELY_YN,");
sb.append("            C.CODE_MGROUP");
sb.append("    </select>");
//sb.append("    <select id="selectWhOutReport" parameterType="hashMap" resultType="hashMap">");
sb.append("        SELECT /* outreport.xml : logistics.outreport.selectWhOutReport by WhOutReport */");
sb.append("            WH_CODE,");
sb.append("               SUM(CHULGO_QTY) AS CHULGO_QTY,");
sb.append("               SUM(CANCEL_QTY) AS CANCEL_QTY,");
sb.append("               SUM(CHANGE_QTY) AS CHANGE_QTY,");
sb.append("               SUM(RETURN_QTY) AS RETURN_QTY,");
sb.append("               (SUM(CHULGO_QTY) + SUM(CHANGE_QTY) - SUM(CANCEL_QTY) - SUM(RETURN_QTY)) AS TOTAL_QTY");
sb.append("          FROM (SELECT");
sb.append("                      A.WH_CODE,");
sb.append("                       SUM(DECODE(A.SLIP_GB,   '101', B.DELY_QTY,");
sb.append("                                              '102', B.DELY_QTY,");
sb.append("                                              '103', B.DELY_QTY, 0))                     AS CHULGO_QTY,");
sb.append("                       SUM(DECODE(A.SLIP_GB, '201', (B.DELY_QTY + B.RETURN_QTY), 0))     AS CANCEL_QTY,");
sb.append("                       SUM(DECODE(A.SLIP_GB, '104', B.DELY_QTY, 0))                     AS CHANGE_QTY,");
sb.append("                       SUM(DECODE(A.SLIP_GB, '203', (B.DELY_QTY + B.RETURN_QTY), 0))     AS RETURN_QTY");
sb.append("                FROM");
sb.append("                    TSLIPM A,");
sb.append("                    TSLIPDT B,");
sb.append("                    TGOODS C");
sb.append("               WHERE A.SLIP_I_NO     = B.SLIP_I_NO");
sb.append("                 AND   B.GOODS_CODE     = C.GOODS_CODE");
sb.append("                 AND   A.OUT_CLOSE_YN     = '1'");
sb.append("                 AND   A.REDELY_YN     = '0'");
sb.append("                 AND   A.OUT_CLOSE_DATE BETWEEN TO_DATE('2013/11/01', 'YYYY/MM/DD')");
sb.append("              AND   TO_DATE('2013/11/01', 'YYYY/MM/DD') + 1");
sb.append("              AND   C.LGROUP         LIKE '%%'");
sb.append("              AND   A.WH_CODE        ='001'");
sb.append("               GROUP BY A.WH_CODE)");
sb.append("         GROUP BY WH_CODE     ");
sb.append("         ORDER BY WH_CODE             ");
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
                new Header("Delivery Report (WhOutReport)"
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
