package com.dartmedia.felino.form;
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
import com.vaadin.ui.PopupDateField;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("InoutReport")
public class InoutReportView extends MVerticalLayout implements View {
//InoutReportSvc data=new InoutReportSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Term <p:calendar /> ~ <p:calendar />    Warehouse <p:selectOneListbox id="basic"> </p:selectOneListbox>     Type <p:selectOneListbox id="basic"> </p:selectOneListbox>     Unit <p:selectOneListbox id="basic"> </p:selectOneListbox>     Vendor <p:inputText />             Item <p:inputText />                              **/
StringBuffer sb = new StringBuffer();
sb.append(" SELECT /* entpin.xml : logistics.entpin.selectAllGoodsdt by InoutReport */");
sb.append("         A.*,");
sb.append("         ROUND(A.INTOT / 1.07, 2) AS BUY_AMT_TH,");
sb.append("         ROUND(A.INTOT - (A.INTOT / 1.07), 2) AS BUY_VAT_TH");
sb.append("          FROM (");
sb.append("                  SELECT ");
sb.append("                   VN.ENTP_CODE                           AS ENTP_CODE,  ");
sb.append("                 VN.ENTP_NAME                           AS ENTP_NAME,  ");
sb.append("                 C1.CODE_NAME                           AS BUY_MED,  ");
sb.append("                 IM.IN_NO                               AS IN_NO,  ");
sb.append("                 IT.LGROUP                              AS LGROUP,  ");
sb.append("                 IT.GOODS_CODE                          AS GOODS_CODE,  ");
sb.append("                 ID.GOODSDT_CODE                        AS GOODSDT_CODE,  ");
sb.append("                 IT.GOODS_NAME                          AS GOODS_NAME,  ");
sb.append("                 UN.GOODSDT_INFO                        AS GOODSDT_INFO,  ");
sb.append("                 IP.BUY_PRICE                           AS BUY_PRICE,  ");
sb.append("                 ID.IN_QTY                              AS AQTY,  ");
sb.append("                 0                                      AS BQTY,  ");
sb.append("                 IP.BUY_COST * ID.IN_QTY                AS BUY_AMT,  ");
sb.append("                 IP.BUY_VAT  * ID.IN_QTY                AS BUY_VAT,  ");
sb.append("                 (IP.BUY_COST + IP.BUY_VAT) * ID.IN_QTY AS INTOT,  ");
sb.append("                 MD.MD_CODE                             AS MD_CODE,  ");
sb.append("                 MD.MD_NAME                             AS MD_NAME  ");
sb.append("            FROM TENTERPRISE    VN,  ");
sb.append("                TGOODS          IT,  ");
sb.append("                TGOODSDT        UN,  ");
sb.append("                TCODE           C1,  ");
sb.append("                TGOODSPRICE     IP,  ");
sb.append("                TINM            IM,  ");
sb.append("                TINDT           ID,  ");
sb.append("                TMD             MD  ");
sb.append("          WHERE IM.IN_NO        = ID.IN_NO  ");
sb.append("            AND ID.GOODS_CODE   = IT.GOODS_CODE  ");
sb.append("            AND ID.GOODS_CODE   = UN.GOODS_CODE  ");
sb.append("            AND ID.GOODSDT_CODE = UN.GOODSDT_CODE  ");
sb.append("            AND ID.IN_QTY       > 0  ");
sb.append("            AND IT.ENTP_CODE    = VN.ENTP_CODE  ");
sb.append("            AND IT.BUY_MED      = C1.CODE_MGROUP  ");
sb.append("            AND C1.CODE_LGROUP  = 'B020'  ");
sb.append("            AND IT.MD_CODE      = MD.MD_CODE  ");
sb.append("            AND ID.GOODS_CODE   = IP.GOODS_CODE  ");
sb.append("            AND IP.APPLY_DATE   =   ");
sb.append("                             (   SELECT MAX(IT2.APPLY_DATE)  ");
sb.append("                                   FROM TGOODSPRICE IT2  ");
sb.append("                                  WHERE IT2.GOODS_CODE  = IP.GOODS_CODE  ");
sb.append("                                    AND IT2.APPLY_DATE <= IM.INSERT_DATE  ");
sb.append("                             )    ");
sb.append("            AND IM.IN_DATE     >= TO_DATE('2014/01/01', 'YYYY/MM/DD')  ");
sb.append("            AND IM.IN_DATE     <= TO_DATE('2014/08/01', 'YYYY/MM/DD')  ");
sb.append("            AND IM.WH_CODE    ='001'  ");
sb.append("/*            AND IT.LGROUP  BETWEEN #{lgroup_fr,jdbcType=VARCHAR} AND #{lgroup_to,jdbcType=VARCHAR}  ");
sb.append("            AND IT.GOODS_CODE LIKE #{goods_code,jdbcType=VARCHAR}||'%'  ");
sb.append("            AND IT.ENTP_CODE  LIKE #{entp_code,jdbcType=VARCHAR}||'%'  */");
sb.append("          UNION ALL ");
sb.append("          SELECT VN.ENTP_CODE                                                    AS ENTP_CODE,  ");
sb.append("                 VN.ENTP_NAME                                                    AS ENTP_NAME,  ");
sb.append("                 C1.CODE_NAME                       AS BUY_MED,  ");
sb.append("                 VM.EOUT_NO                                                      AS IN_NO,  ");
sb.append("                 IT.LGROUP                                                       AS LGROUP,  ");
sb.append("                 IT.GOODS_CODE                                                   AS GOODS_CODE,  ");
sb.append("                 VD.GOODSDT_CODE                                                 AS GOODSDT_CODE,  ");
sb.append("                 IT.GOODS_NAME                                                   AS GOODS_NAME,  ");
sb.append("                 UN.GOODSDT_INFO                                                 AS GOODSDT_INFO,  ");
sb.append("                 IP.BUY_PRICE                                                    AS BUY_PRICE,  ");
sb.append("                 VD.EOUT_AQTY * -1                                               AS AQTY,  ");
sb.append("                 VD.EOUT_BQTY * -1                                               AS BQTY,  ");
sb.append("                 IP.BUY_COST * (VD.EOUT_AQTY + VD.EOUT_BQTY) * -1                AS BUY_AMT,  ");
sb.append("                 IP.BUY_VAT  * (VD.EOUT_AQTY + VD.EOUT_BQTY) * -1                AS BUY_VAT,  ");
sb.append("                 (IP.BUY_COST + IP.BUY_VAT) * (VD.EOUT_AQTY + VD.EOUT_BQTY) * -1 AS  INTOT,  ");
sb.append("                 IT.MD_CODE                                                      AS MD_CODE,  ");
sb.append("                 MD.MD_NAME                                                      AS MD_NAME  ");
sb.append("            FROM TENTERPRISE      VN,  ");
sb.append("                 TGOODS           IT,  ");
sb.append("                 TGOODSDT         UN,  ");
sb.append("                 TCODE            C1,  ");
sb.append("                 TGOODSPRICE      IP,  ");
sb.append("                 TENTPOUTM        VM,  ");
sb.append("                 TENTPOUTDT       VD,  ");
sb.append("                 TMD              MD  ");
sb.append("           WHERE VM.EOUT_NO        = VD.EOUT_NO  ");
sb.append("             AND VD.GOODS_CODE   = IT.GOODS_CODE  ");
sb.append("             AND VD.GOODS_CODE   = UN.GOODS_CODE  ");
sb.append("             AND VD.GOODSDT_CODE = UN.GOODSDT_CODE  ");
sb.append("             AND VD.EOUT_AQTY + VD.EOUT_BQTY > 0  ");
sb.append("             AND IT.ENTP_CODE    = VN.ENTP_CODE  ");
sb.append("             AND IT.BUY_MED      = C1.CODE_MGROUP  ");
sb.append("             AND C1.CODE_LGROUP  = 'B020'  ");
sb.append("             AND IT.MD_CODE      = MD.MD_CODE  ");
sb.append("             AND VD.GOODS_CODE   = IP.GOODS_CODE  ");
sb.append("             AND IP.APPLY_DATE   =  ");
sb.append("                        (   SELECT MAX(IT2.APPLY_DATE)  ");
sb.append("                              FROM TGOODSPRICE IT2  ");
sb.append("                             WHERE IT2.GOODS_CODE  = IP.GOODS_CODE  ");
sb.append("                               AND IT2.APPLY_DATE <= VM.EOUT_DATE   ");
sb.append("                        )   ");
sb.append("              AND VM.EOUT_DATE   >= TO_DATE('2014/01/01', 'YYYY/MM/DD')   ");
sb.append("              AND VM.EOUT_DATE   <= TO_DATE('2014/08/01', 'YYYY/MM/DD')   ");
sb.append("              AND VM.WH_CODE    LIKE '%001%'    ");
sb.append("/*              AND IT.LGROUP  BETWEEN #{lgroup_fr,jdbcType=VARCHAR} AND #{lgroup_to,jdbcType=VARCHAR}  ");
sb.append("              AND IT.GOODS_CODE LIKE #{goods_code,jdbcType=VARCHAR}||'%'  ");
sb.append("              AND IT.ENTP_CODE  LIKE #{entp_code,jdbcType=VARCHAR}||'%'  */");
sb.append("         ORDER BY ENTP_CODE, BUY_MED, IN_NO, GOODS_CODE, GOODSDT_CODE ) A ");
sb.append("         ");
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
toolbar.addComponent(new ComboBox("Ware House"));
toolbar.addComponent(new ComboBox("Type"));
toolbar.addComponent(new ComboBox("Unit"));
toolbar.addComponent(new TextField("Vendor"));
toolbar.addComponent(new Button("Goods Receipt Note"));
toolbar.addComponent(new Button("Take out Form"));





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
                new Header("Receipt/Take Out Report (InoutReport)"
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
