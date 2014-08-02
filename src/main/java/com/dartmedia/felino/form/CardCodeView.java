package com.dartmedia.felino.form;
import com.dartmedia.felino.Tcardcode;
import com.dartmedia.felino.TcardcodeFacade;
import com.vaadin.cdi.CDIView;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.fields.MValueChangeEvent;
import org.vaadin.maddon.fields.MValueChangeListener;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("CardCode")
public class CardCodeView extends MVerticalLayout implements View {
//CardCodeSvc data=new CardCodeSvc();
@Inject   TcardcodeFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Bank code <p:inputText />Bank name <p:inputText /><p:selectOneListbox>            <f:selectItem itemLabel="All" itemValue="" />            <f:selectItem itemLabel="Use" itemValue="1" />            <f:selectItem itemLabel="Unused" itemValue="0" /> </p:selectOneListbox>     **/
StringBuffer sb = new StringBuffer();
sb.append("");
sb.append("        /* Code, Card company Name, Business Registration No, Staff, TelDDD, Tel1, Tel2, Tel3, Use, Card Commission */");
sb.append("  ");
sb.append("        SELECT /*payment.xml : custcenter.payment.selectCardCode by CardCode*/");
sb.append("              A.CARD_CODE,");
sb.append("              A.CARD_NAME,         ");
sb.append("              A.BUSINESS_NO,");
sb.append("              A.CARD_MAN_NAME,");
sb.append("              A.CARD_MAN_DDD,");
sb.append("              A.CARD_MAN_TEL1,");
sb.append("              A.CARD_MAN_TEL2,");
sb.append("              A.CARD_MAN_TEL3,");
sb.append("              A.REST_NO,");
sb.append("              A.REST_RATE,");
sb.append("              A.NOREST_NO,");
sb.append("              A.NOREST_RATE,");
sb.append("              A.USE_YN,");
sb.append("              A.REMARK,");
sb.append("              A.M_VAN_COMP,");
sb.append("              A.S_VAN_COMP,");
sb.append("              A.INSERT_DATE,");
sb.append("              A.INSERT_ID,");
sb.append("              A.INS_VAN_COMP,                                   ");
sb.append("              A.CARD_COMMISSION_RATE");
sb.append("        FROM TCARDCODE A");
sb.append("        WHERE A.CARD_CODE     LIKE '%%'");
sb.append("        AND   A.USE_YN         LIKE '%%'");
sb.append("        ORDER BY A.CARD_CODE ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new TextField("Credit Card"));
toolbar.addComponent(new TextField(""));
toolbar.addComponent(new CheckBox("All"));
toolbar.addComponent(new CheckBox("Use"));
toolbar.addComponent(new CheckBox("Unusedd"));


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
                new Header("Credit Card Code (CardCode)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
        addComponents(isicontents);
//MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
List<Tcardcode> findAll = cf.findAll();
MTable<Tcardcode> table=new MTable<Tcardcode>(Tcardcode.class).withProperties("cardCode","cardName","remark");
table.setBeans(findAll);
table.addMValueChangeListener(new MValueChangeListener<Tcardcode>() {
    @Override
    public void valueChange(MValueChangeEvent<Tcardcode> event) {
    //Notification.show("ss");
//    form.setEntity(event.getValue());
    }
    });
//table.addContainerProperty("No", String.class,  null);
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
