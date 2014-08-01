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
@CDIView("WarehousingOk")
public class WarehousingOkView extends MVerticalLayout implements View {
//WarehousingOkSvc data=new WarehousingOkSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**PO No <p:inputText />                                 **/
StringBuffer sb = new StringBuffer();
sb.append(" SELECT /* entpin.xml : logistics.entpin.selectWarehousingOk by WarehousingOk */");
sb.append("                   A.BALJU_NO,  ");
sb.append("                 TO_CHAR(A.CTRL_IN_DATE, 'YYYY/MM/DD') AS CTRL_IN_DATE,  ");
sb.append("                 TO_CHAR(A.BALJU_DATE, 'YYYY/MM/DD') AS BALJU_DATE,  ");
sb.append("                 A.INSERT_ID,  ");
sb.append("                 F.USER_NAME AS BALJU_NAME,  ");
sb.append("                 B.MD_NAME,  ");
sb.append("                 C.ENTP_NAME,  ");
sb.append("                 A.IN_END_YN,  ");
sb.append("                 TO_CHAR(A.IN_END_DATE, 'YYYY/MM/DD') AS IN_END_DATE,  ");
sb.append("                 A.WH_CODE,  ");
sb.append("                 A.ENTP_CODE,  ");
sb.append("                 E.IN_MAN_ID,  ");
sb.append("                 G.USER_NAME AS IN_NAME,  ");
sb.append("                 A.ENTP_MAN_SEQ,  ");
sb.append("                 A.ENTP_BALJU_SEQ,  ");
sb.append("                 A.MD_CODE,  ");
sb.append("                 A.OLD_BALJU_NO,  ");
sb.append("                 A.BALJU_SEQ,  ");
sb.append("                 E.IN_NO  ");
sb.append("            FROM TBALJUM      A,  ");
sb.append("                 TMD          B,  ");
sb.append("                 TENTERPRISE  C,  ");
sb.append("                 TINM         E,  ");
sb.append("                 TUSER        F,  ");
sb.append("                 TUSER        G  ");
sb.append("           WHERE A.MD_CODE   = B.MD_CODE  ");
sb.append("             AND A.ENTP_CODE = C.ENTP_CODE  ");
sb.append("             AND A.BALJU_NO  = E.BALJU_NO (+)  ");
sb.append("             AND A.INSERT_ID = F.USER_ID (+)  ");
sb.append("             AND E.IN_MAN_ID = G.USER_ID (+)  ");
sb.append("             AND A.BALJU_NO  = 201207270818 ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new TextField("Po No"));
toolbar.addComponent(new TextField("Po Date"));
toolbar.addComponent(new TextField("Degree"));
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
                new Header("Goods Receipt by PO [Manual] (WarehousingOk)"
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
