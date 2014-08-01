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
@CDIView("BroadModelManage")
public class BroadModelManageView extends MVerticalLayout implements View {
//BroadModelManageSvc data=new BroadModelManageSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/****/
StringBuffer sb = new StringBuffer();
sb.append("SELECT");
sb.append("/*");
sb.append("production.xml");
sb.append(":");
sb.append("broadcast.production.selectBroadModelManage");
sb.append("by");
sb.append("BroadModelManageController");
sb.append("*/");
sb.append("MM.MODEL_NO,");
sb.append("MM.MODEL_NAME,");
sb.append("MM.MODEL_ENG_NAME,");
sb.append("MM.MODEL_FLAG,");
sb.append("TCODE_NAME('C095',MM.MODEL_FLAG)");
sb.append("AS");
sb.append("MODEL_FLAG_NAME,");
sb.append("MM.ENTP_CODE,");
sb.append("EP.ENTP_NAME,");
sb.append("MM.COUNTRY_CODE,");
sb.append("TCODE_NAME('B023',MM.COUNTRY_CODE)");
sb.append("AS");
sb.append("COUNTRY_NAME,");
sb.append("MM.REMARK,");
sb.append("MM.USE_YN");
sb.append("FROM");
sb.append("TMODELMASTER");
sb.append("MM,");
sb.append("TENTERPRISE");
sb.append("EP");
sb.append("WHERE");
sb.append("MM.USE_YN");
sb.append("=");
sb.append("'1'");
sb.append("AND");
sb.append("MM.ENTP_CODE");
sb.append("=");
sb.append("EP.ENTP_CODE");
sb.append("AND");
sb.append("MM.ENTP_CODE");
sb.append("LIKE");
sb.append("'entp_code'");
sb.append("||");
sb.append("'%'");
sb.append("AND");
sb.append("MM.MODEL_FLAG");
sb.append("LIKE");
sb.append("'model_flag'");
sb.append("||");
sb.append("'%'");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new ComboBox("My ComboBox"));
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
                new Header("Model Management (BroadModelManage)"
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
