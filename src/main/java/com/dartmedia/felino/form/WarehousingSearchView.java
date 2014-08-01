package com.dartmedia.felino.form;
import com.vaadin.cdi.CDIView;
import org.vaadin.maddon.fields.MTable;
import com.vaadin.event.LayoutEvents;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.PopupDateField;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("WarehousingSearch")
public class WarehousingSearchView extends MVerticalLayout implements View {
//WarehousingSearchSvc data=new WarehousingSearchSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Term <p:calendar /> ~ <p:calendar />    Warehouse <p:selectOneListbox id="basic"> </p:selectOneListbox>     Type <p:selectOneListbox id="basic"> </p:selectOneListbox>     Unit <p:selectOneListbox id="basic"> </p:selectOneListbox>     Vendor <p:inputText />             Item <p:inputText />    **/
StringBuffer sb = new StringBuffer();
sb.append("      SELECT /* entpin.xml : logistics.entpin.selectWarehousingSearch by WarehousingSearch */");
sb.append("                   A.IN_NO,  ");
sb.append("                   A.INVOICE_NO,");
sb.append("                 DECODE(B.BALJU_GB || B.REBALJU_YN, '300', 'IN ADVANCE', 'NORMAL' )  AS PRE_DELY,  ");
sb.append("                 C.ENTP_NAME,  ");
sb.append("                 E.USER_NAME AS IN_MAN_ID_NAME, ");
sb.append("                 F.USER_NAME AS INSERT_ID_NAME, ");
sb.append("                 A.IN_PRINT_CNT,     ");
sb.append("                 A.BALJU_NO,     ");
sb.append("                 D.MD_NAME,     ");
sb.append("                 A.IN_DATE,  ");
sb.append("                 0 OK_FLAG,  ");
sb.append("                 '0' PRT_YN,");
sb.append("                 A.AUTO_YN  ");
sb.append("            FROM TINM          A,  ");
sb.append("                 TBALJUM       B,  ");
sb.append("                 TENTERPRISE   C,  ");
sb.append("                 TMD           D,  ");
sb.append("                 TUSER         E,  ");
sb.append("                 TUSER         F  ");
sb.append("           WHERE A.BALJU_NO    = B.BALJU_NO  ");
sb.append("             AND B.ENTP_CODE   = C.ENTP_CODE  ");
sb.append("             AND B.MD_CODE     = D.MD_CODE  ");
sb.append("             AND A.IN_MAN_ID   = E.USER_ID  ");
sb.append("             AND A.INSERT_ID   = F.USER_ID    ;");
sb.append("/*                        ");
sb.append("       <choose>                        ");
//sb.append("               <when test='checkIn == "0" and checkBalju == "0"'>");
sb.append("                AND A.IN_DATE >= TO_DATE(#{in_date_fr,jdbcType=VARCHAR}, 'YYYY/MM/DD') ");
sb.append("                AND A.IN_DATE &lt;  TO_DATE(#{in_date_to,jdbcType=VARCHAR}, 'YYYY/MM/DD') + 1 ");
sb.append("                AND A.ENTP_CODE LIKE #{entp_code,jdbcType=VARCHAR} ||'%' ");
sb.append("                AND A.IN_MAN_ID LIKE #{in_man_id,jdbcType=VARCHAR} ||'%' ");
sb.append("                AND A.WH_CODE   = #{wh_code,jdbcType=VARCHAR}");
sb.append("                ");
//sb.append("                <if test='pre_dely == "1"'>");
sb.append("                    AND B.BALJU_GB || B.REBALJU_YN NOT IN ('300')");
sb.append("                </if>");
//sb.append("                <if test='pre_dely == "2"'>");
//sb.append("                    AND B.BALJU_GB || B.REBALJU_YN = '300'");
sb.append("                </if>");
sb.append("            </when>");
//sb.append("            <when test='checkIn == "1"'>");
sb.append("                AND A.IN_NO = #{in_no,jdbcType=VARCHAR}");
sb.append("            </when>");
//sb.append("            <when test='checkBalju == "1"'>");
sb.append("                AND A.BALJU_NO = #{balju_no,jdbcType=VARCHAR}");
sb.append("            </when>            ");
sb.append("*/            ");
sb.append("          SELECT /* entpin.xml : logistics.entpin.selectWarehousingSearchDt by WarehousingSearch */");
sb.append("                   F.LGROUP, ");
sb.append("                 F.MGROUP, ");
sb.append("                 E.GOODS_CODE, ");
sb.append("                 E.GOODSDT_CODE,    ");
sb.append("                 E.GOODS_NAME,    ");
sb.append("                 E.GOODSDT_INFO,    ");
sb.append("                 C.BALJU_QTY,    ");
sb.append("                 C.ENTP_CONF_QTY,  ");
sb.append("                 B.IN_QTY,    ");
sb.append("                 B.LOT_NO     ");
sb.append("            FROM TINM A, ");
sb.append("                 TINDT B, ");
sb.append("                 TBALJUDT C, ");
sb.append("                 TGOODSDT E, ");
sb.append("                 TGOODS F ");
sb.append("           WHERE A.IN_NO        = 201310241115");
sb.append("             AND A.IN_NO        = B.IN_NO ");
sb.append("             AND A.BALJU_NO     = C.BALJU_NO    ");
sb.append("             AND B.GOODS_CODE   = C.GOODS_CODE     ");
sb.append("             AND B.GOODS_CODE   = F.GOODS_CODE   ");
sb.append("             AND B.GOODSDT_CODE = C.GOODSDT_CODE       ");
sb.append("             AND C.GOODS_CODE   = E.GOODS_CODE     ");
sb.append("             AND C.GOODSDT_CODE = E.GOODSDT_CODE ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Term"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new CheckBox("Indv.Query"));
toolbar.addComponent(new ComboBox("My ComboBox"));
toolbar.addComponent(new ComboBox("My ComboBox"));
toolbar.addComponent(new ComboBox("My ComboBox"));


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
                new Header("Receipt Report (WarehousingSearch)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
        addComponents(isicontents);
MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
table.addContainerProperty("No", String.class,  null);
table.addContainerProperty("ITEM", String.class,  null);
table.addContainerProperty("No", String.class,  null);
//-------------------- Header Table ------------------------------
   isicontents.addComponents(table);
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
