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
@CDIView("AnalysisPromoDiscount")
public class AnalysisPromoDiscountView extends MVerticalLayout implements View {
//AnalysisPromoDiscountSvc data=new AnalysisPromoDiscountSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Date <p:calendar /> ~ <p:calendar />Promo No <p:inputText />//select promo_no, promo_name from tpromom;**/
StringBuffer sb = new StringBuffer();
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
   toolbar.addComponent(new PopupDateField("date"));
   toolbar.addComponent(new PopupDateField("~"));   
   toolbar.addComponent(new TextField("Promo No")); 
   toolbar.addComponent(new TextField("Promo Name"));    



//-toolbar.addComponent(new PopupDateField("My Date"));
//-toolbar.addComponent(new TextField("Indv.Query"));
//-toolbar.addComponent(new ComboBox("My ComboBox"));
//-toolbar.addComponent(new Button("Button"));
//-ComboBox pilih1=new ComboBox("Custumer Group");
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
                new Header("Promotion Discount Report (AnalysisPromoDiscount)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
        addComponents(isicontents);
MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
table.addContainerProperty("No", String.class,  null);
table.addContainerProperty("Order Date", String.class,  null);
table.addContainerProperty("Promo No", String.class,  null);
table.addContainerProperty("Promo Name", String.class,  null);
table.addContainerProperty("Order No", String.class,  null);
table.addContainerProperty("Cust  No", String.class,  null);
table.addContainerProperty("Cust Name", String.class,  null);



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
