package com.dartmedia.felino.form;
import com.dartmedia.felino.fgetsql;
import com.dartmedia.felino.gSqlContainer;
import com.dartmedia.felino.gSqlContainer;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("AnalysisPromoGift")
public class AnalysisPromoGiftView extends MVerticalLayout implements View {
//AnalysisPromoGiftSvc data=new AnalysisPromoGiftSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/****/
StringBuffer sb = new StringBuffer();
sb.append("SELECT");
sb.append("/*");
sb.append("promotion.xml");
sb.append(":");
sb.append("manage.promotion.selectPromoGift");
sb.append("by");
sb.append("Promo");
sb.append("*/");
sb.append("A.PROMO_NO,");
sb.append("A.PROMO_SEQ,");
sb.append("A.GIFT_GOODS_CODE,");
sb.append("B.GOODS_CODE,");
sb.append("B.GOODS_NAME,");
sb.append("A.GIFT_QTY,");
sb.append("A.INSERT_DATE,");
sb.append("A.INSERT_ID,");
sb.append("A.MODIFY_DATE,");
sb.append("A.MODIFY_ID");
sb.append("FROM");
sb.append("TPROMOGIFT");
sb.append("A,");
sb.append("TGOODS");
sb.append("B");
sb.append("WHERE");
sb.append("A.GIFT_GOODS_CODE");
sb.append("=");
sb.append("B.GOODS_CODE");
sb.append("AND");
sb.append("A.PROMO_NO");
sb.append("=");
sb.append("'201302180001'");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();

//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
//-toolbar.addComponent(new PopupDateField("My Date"));
//-toolbar.addComponent(new TextField("Indv.Query"));
//-toolbar.addComponent(new ComboBox("My ComboBox"));
//-toolbar.addComponent(new Button("Button"));
//-ComboBox pilih1=new ComboBox("Custumer Group");
toolbar.addComponent(new PopupDateField("date"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new TextField("Promo No"));
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
                new Header("Promotion Gift Report (AnalysisPromoGift)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
        addComponents(isicontents);
MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
table.addContainerProperty("No", String.class,  null);
table.addContainerProperty("Order date", String.class,  null);
table.addContainerProperty("Promo No", String.class,  null);
table.addContainerProperty("Promo Name", String.class,  null);
table.addContainerProperty("Order No", String.class,  null);
table.addContainerProperty("Cust No", String.class,  null);
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
