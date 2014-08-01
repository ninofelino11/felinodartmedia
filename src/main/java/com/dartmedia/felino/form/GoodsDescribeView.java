package com.dartmedia.felino.form;
import com.dartmedia.felino.fgetsql;
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
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("GoodsDescribe")
public class GoodsDescribeView extends MVerticalLayout implements View {
//GoodsDescribeSvc data=new GoodsDescribeSvc();
    @PostConstruct
    public void initComponent() {
/**Item: <p:inputText />
//select goods_code, goods_name, TCODE_NAME('B032', SALE_GB) AS SALE_NAME from TGOODS 

**/
StringBuffer sb = new StringBuffer();
sb.append("SELECT");
sb.append("/*");
sb.append("goods.xml");
sb.append(":");
sb.append("manage.goods.selectGoodsDescribe");
sb.append("by");
sb.append("GoodsDescribeController");
sb.append("*/");
sb.append("B.DESCRIBE_CODE");
sb.append("B.DESCRIBE_TITLE");
sb.append("A.DESCRIBE_EXT");
sb.append("A.WEB_FLAG");
sb.append("A.REQUIRED_YN");
sb.append("B.WEB_FLAG");
sb.append("AS");
sb.append("FIRST_WEB_FLAG");
sb.append("A.GOODS_CODE");
sb.append("B.SORT_SEQ");
sb.append("TO_CHAR(A.INSERT_DATE");
sb.append("'YYYY/MM/DD')");
sb.append("AS");
sb.append("INSERT_DATE");
sb.append("A.INSERT_ID");
sb.append("TO_CHAR(A.MODIFY_DATE");
sb.append("'YYYY/MM/DD')");
sb.append("AS");
sb.append("MODIFY_DATE");
sb.append("A.MODIFY_ID");
sb.append("FROM");
sb.append("TDESCRIBE");
sb.append("A");
sb.append("TDESCRIBECODE");
sb.append("B");
sb.append("WHERE");
sb.append("A.DESCRIBE_CODE");
sb.append("=");
sb.append("B.DESCRIBE_CODE");
sb.append("AND");
sb.append("A.GOODS_CODE");
sb.append("=");
sb.append("'100013'");
sb.append("ORDER");
sb.append("BY");
sb.append("A.DESCRIBE_CODE");
sb.append("ASC");
sb.append("B.DESCRIBE_CODE;");
sb.append("SELECT");
sb.append("/*");
sb.append("goods.xml");
sb.append(":");
sb.append("manage.goods.selectExistGoodsDescribe");
sb.append("by");
sb.append("GoodsDescribeController");
sb.append("*/");
sb.append("GOODS_CODE");
sb.append("DESCRIBE_CODE");
sb.append("DESCRIBE_TITLE");
sb.append("DESCRIBE_NOTE");
sb.append("DESCRIBE_EXT");
sb.append("WEB_FLAG");
sb.append("REQUIRED_YN");
sb.append("INSERT_DATE");
sb.append("FROM");
sb.append("(SELECT");
sb.append("GOODS_CODE");
sb.append("DESCRIBE_CODE");
sb.append("DESCRIBE_TITLE");
sb.append("DESCRIBE_NOTE");
sb.append("WEB_FLAG");
sb.append("DESCRIBE_EXT");
sb.append("REQUIRED_YN");
sb.append("TO_CHAR(INSERT_DATE");
sb.append("'YYYY/MM/DD')");
sb.append("AS");
sb.append("INSERT_DATE");
sb.append("FROM");
sb.append("TDESCRIBE");
sb.append("WHERE");
sb.append("GOODS_CODE");
sb.append("=");
sb.append("'100013'");
sb.append("UNION");
sb.append("ALL");
sb.append("SELECT");
sb.append("'100013'");
sb.append("AS");
sb.append("GOODS_CODE");
sb.append("DESCRIBE_CODE");
sb.append("DESCRIBE_TITLE");
sb.append("''");
sb.append("AS");
sb.append("DESCRIBE_NOTE");
sb.append("WEB_FLAG");
sb.append("TO_CLOB('')");
sb.append("AS");
sb.append("DESCRIBE_EXT");
sb.append("REQUIRED_YN");
sb.append("''");
sb.append("AS");
sb.append("INSERT_DATE");
sb.append("FROM");
sb.append("TDESCRIBECODE");
sb.append("WHERE");
sb.append("USE_YN");
sb.append("=");
sb.append("'1'");
sb.append("AND");
sb.append("DESCRIBE_CODE");
sb.append("NOT");
sb.append("IN");
sb.append("(SELECT");
sb.append("DESCRIBE_CODE");
sb.append("FROM");
sb.append("TDESCRIBE");
sb.append("WHERE");
sb.append("GOODS_CODE");
sb.append("=");
sb.append("'100013'))");
sb.append("ORDER");
sb.append("BY");
sb.append("DESCRIBE_CODE;");
//String fsql = data.makeSql();
gSqlContainer sumber=new gSqlContainer();
MTable table=new MTable();
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new TextField("Item"));
toolbar.addComponent(new TextField(""));
toolbar.addComponent(new ComboBox("My ComboBox"));
toolbar.addComponent(new Button("My ComboBox"));


//-------------------- Header  ------------------------------
//-------------------- Header Table ---judul untuk table----------
table.addContainerProperty("No", String.class,  null);
table.addContainerProperty("Desc.code", String.class,  null);
table.addContainerProperty("Description Title", String.class,  null);
table.addContainerProperty("Web", String.class,  null);
table.addContainerProperty("use", String.class,  null);
table.addContainerProperty("Essential", String.class,  null);
table.addContainerProperty("No", String.class,  null);

//-------------------- Header Table ------------------------------ 
MHorizontalLayout toolmenu;
toolmenu = new MHorizontalLayout();
toolmenu.addComponent(new Button("Ret"));
toolmenu.addComponent(new Button("Ins"));
toolmenu.addComponent(new Button("Save"));
toolmenu.addComponent(new Button("Print"));
toolmenu.addComponent(new Button("XLS"));

        addComponents( 
                new Header("GoodsDescribe")
        );
        addComponents(toolmenu);
        addComponents(toolbar); 
        addComponents(table);   
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
