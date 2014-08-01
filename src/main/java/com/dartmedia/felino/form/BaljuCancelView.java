package com.dartmedia.felino.form;
import com.vaadin.cdi.CDIView;
import org.vaadin.maddon.fields.MTable;
import com.vaadin.event.LayoutEvents;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.PopupDateField;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("BaljuCancel")
public class BaljuCancelView extends MVerticalLayout implements View {
//BaljuCancelSvc data=new BaljuCancelSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**PO No <p:inputText />Term <p:calendar /> ~ <p:calendar />    MD <p:inputText />                                    Vendor <p:inputText /> ~ <p:inputText />**/
StringBuffer sb = new StringBuffer();
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
sb.append(" select /* sql_logistics.entporder.base.xml : logistics.entporder.base.selectBaljuCancelDt by BaljuCancel */");
sb.append("                   E.LGROUP,");
sb.append("                 E.MGROUP,");
sb.append("                 B.GOODS_CODE,");
sb.append("                 B.GOODSDT_CODE,");
sb.append("                 C.GOODS_NAME,");
sb.append("                 C.GOODSDT_INFO,");
sb.append("                 B.BALJU_QTY,");
sb.append("                 D.BUY_PRICE,");
sb.append("                 B.BALJU_QTY * D.BUY_PRICE AS BALJU_AMT,");
sb.append("                 B.ENTP_CONF_QTY");
sb.append("            from TBALJUM     A,");
sb.append("                 TBALJUDT    B,");
sb.append("                 TGOODSDT    C,");
sb.append("                 TGOODSPRICE D,");
sb.append("                 TGOODS      E");
sb.append("           where A.BALJU_NO     = 201403071297");
sb.append("             and A.BALJU_NO     = B.BALJU_NO");
sb.append("             and B.GOODS_CODE   = C.GOODS_CODE");
sb.append("             and B.GOODS_CODE   = E.GOODS_CODE");
sb.append("             and B.GOODSDT_CODE = C.GOODSDT_CODE");
sb.append("             and B.GOODS_CODE   = D.GOODS_CODE");
sb.append("             and D.APPLY_DATE   = ( select MAX(E.APPLY_DATE)");
sb.append("                                      from TGOODSPRICE E");
sb.append("                                     where E.GOODS_CODE  = B.GOODS_CODE");
sb.append("                                       and E.APPLY_DATE <= A.BALJU_DATE");
sb.append("                                      )  ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new TextField("no"));
toolbar.addComponent(new PopupDateField("Term"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new TextField("Md"));
toolbar.addComponent(new TextField("Vendor"));


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
