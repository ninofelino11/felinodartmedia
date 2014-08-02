package com.dartmedia.felino.form;
import com.dartmedia.felino.Tenterprise;
import com.dartmedia.felino.TenterpriseFacade;
import com.vaadin.cdi.CDIView;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
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
@CDIView("EntpInput")
public class EntpInputView extends MVerticalLayout implements View {
//EntpInputSvc data=new EntpInputSvc();
@Inject   TenterpriseFacade cf;
@Inject  TenterpriseForm form;
    @PostConstruct

    public void initComponent() {
// @Inject
//TbrandForm form;
/**Vendor <p:inputText />//select a.entp_code, a.entp_name, a.s_idno, b.entp_man_name from tenterprise a, tentpuser b where a.entp_code = b.entp_code**/
StringBuffer sb = new StringBuffer();
sb.append("     select /* entp.xml : manage.entp.selectEntpList by EntpInput, EntpList */");
sb.append("                   A.ENTP_CODE,   ");
sb.append("                   A.ENTP_NAME,  ");
sb.append("                   A.ENTP_GUBUN,");
sb.append("                   A.S_IDNO,");
sb.append("                   A.WORK_TYPE,");
sb.append("                   A.WORK_KIND,");
sb.append("                   A.ENTP_POST,");
sb.append("                   A.ENTP_POST_SEQ,");
sb.append("                   A.ENTP_ADDR,");
sb.append("                   A.ENTP_DDD,");
sb.append("                   A.ENTP_TEL1,");
sb.append("                   A.ENTP_TEL2,");
sb.append("                   A.ENTP_TEL3,");
sb.append("                   A.ENTP_FAX1,");
sb.append("                   A.ENTP_FAX2,");
sb.append("                   A.ENTP_FAX3,");
sb.append("                   A.OWNER_NAME,");
sb.append("                   to_char(A.FIRST_DATE, 'YYYY/MM/DD') as FIRST_DATE,");
sb.append("                   A.DISHONOR_YN,");
sb.append("                   B.PASSWORD AS SECRET_NO,");
sb.append("                   to_char(A.CLOSE_DATE, 'YYYY/MM/DD') as CLOSE_DATE,");
sb.append("                   A.CLOSE_REASON,");
sb.append("                   to_char(A.INSERT_DATE) as INSERT_DATE,");
sb.append("                   A.INSERT_ID,");
sb.append("                   to_char(A.MODIFY_DATE) as MODIFY_DATE,");
sb.append("                   A.MODIFY_ID,");
sb.append("                   A.ETC,");
sb.append("                   A.EMAIL_ADDR,");
sb.append("                   fun_add_postaddr(A.ENTP_POST,A.ENTP_POST_SEQ,'') as COMP_ADDR,   ");
sb.append("                   NVL(A.TAX_RATE, 0) AS TAX_RATE,                                                                ");
sb.append("                   A.DELY_GB,");
sb.append("                   A.ACCOUNT_GB,");
sb.append("                    A.FOREIGN_YN,");
sb.append("                    A.SAP_VENDOR_CODE,");
sb.append("                    RTRIM(A.PAYMENT_TERM, 'D') AS PAYMENT_TERM");
sb.append("              from TENTERPRISE A,          ");
sb.append("                     TSCMUSER B                            ");
sb.append("             where A.ENTP_CODE = B.ENTP_CODE");
sb.append("               and B.USER_ID = 'E'||B.ENTP_CODE");
sb.append("               and A.ENTP_CODE like '100004'");
sb.append("               order by ENTP_CODE ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();


MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new TextField("Vendor"));
toolbar.addComponent(new Button("Staff"));
toolbar.addComponent(new Button("Payment Info"));
//No, Vendor Code Vendor Name

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
                new Header("Vendor Management (EntpInput)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
        addComponents(isicontents);

List<Tenterprise> findAll = cf.findAll();
MTable<Tenterprise> table=new MTable<Tenterprise>(Tenterprise.class).withProperties("entpName");
table.setBeans(findAll);

table.addMValueChangeListener(new MValueChangeListener<Tenterprise>() {
    @Override
    public void valueChange(MValueChangeEvent<Tenterprise> event) {
   // Notification.show("ss");
    form.setEntity(event.getValue());
    }   
    });
//-------------------- Header Table ---judul untuk table----------


//List<Hotel> results = q.getResultList();

//table.addContainerProperty("No", String.class,  null);
//-------------------- Header Table ------------------------------
   isicontents.addComponents(table);
   isicontents.addComponents(form);
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
