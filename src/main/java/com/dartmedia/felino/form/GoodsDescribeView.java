package com.dartmedia.felino.form;
import com.vaadin.cdi.CDIView;
import com.vaadin.event.LayoutEvents;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.CheckBox;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("GoodsDescribe")
public class GoodsDescribeView extends MVerticalLayout implements View {
//GoodsDescribeSvc data=new GoodsDescribeSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Item: <p:inputText />//select goods_code, goods_name, TCODE_NAME('B032', SALE_GB) AS SALE_NAME from TGOODS **/
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
sb.append(",B.DESCRIBE_TITLE");
sb.append(",A.DESCRIBE_EXT");
sb.append(",A.WEB_FLAG");
sb.append(",A.REQUIRED_YN");
sb.append(",B.WEB_FLAG");
sb.append("AS");
sb.append("FIRST_WEB_FLAG");
sb.append(",A.GOODS_CODE");
sb.append(",B.SORT_SEQ");
sb.append(",TO_CHAR(A.INSERT_DATE,");
sb.append("'YYYY/MM/DD')");
sb.append("AS");
sb.append("INSERT_DATE");
sb.append(",A.INSERT_ID");
sb.append(",TO_CHAR(A.MODIFY_DATE,");
sb.append("'YYYY/MM/DD')");
sb.append("AS");
sb.append("MODIFY_DATE");
sb.append(",A.MODIFY_ID");
sb.append("FROM");
sb.append("TDESCRIBE");
sb.append("A,");
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
sb.append("ASC,");
sb.append("B.DESCRIBE_CODE");
sb.append("SELECT");
sb.append("/*");
sb.append("goods.xml");
sb.append(":");
sb.append("manage.goods.selectExistGoodsDescribe");
sb.append("by");
sb.append("GoodsDescribeController");
sb.append("*/");
sb.append("GOODS_CODE,");
sb.append("DESCRIBE_CODE,");
sb.append("DESCRIBE_TITLE,");
sb.append("DESCRIBE_NOTE,");
sb.append("DESCRIBE_EXT,");
sb.append("WEB_FLAG,");
sb.append("REQUIRED_YN,");
sb.append("INSERT_DATE");
sb.append("FROM");
sb.append("(SELECT");
sb.append("GOODS_CODE,");
sb.append("DESCRIBE_CODE,");
sb.append("DESCRIBE_TITLE,");
sb.append("DESCRIBE_NOTE,");
sb.append("WEB_FLAG,");
sb.append("DESCRIBE_EXT,");
sb.append("REQUIRED_YN,");
sb.append("TO_CHAR(INSERT_DATE,");
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
sb.append("GOODS_CODE,");
sb.append("DESCRIBE_CODE,");
sb.append("DESCRIBE_TITLE,");
sb.append("''");
sb.append("AS");
sb.append("DESCRIBE_NOTE,");
sb.append("WEB_FLAG,");
sb.append("TO_CLOB('')");
sb.append("AS");
sb.append("DESCRIBE_EXT,");
sb.append("REQUIRED_YN,");
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
sb.append("DESCRIBE_CODE");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new TextField("Item"));
toolbar.addComponent(new Button("Descrition Copy"));


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
                new Header("Item Description (GoodsDescribe)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
        addComponents(isicontents);
//MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
//List<Tenterprise> findAll = cf.findAll();
//MTable<Tenterprise> table=new MTable<Tenterprise>(Tenterprise.class).withProperties("entpName");
//table.setBeans(findAll);
//table.addMValueChangeListener(new MValueChangeListener<Tdescribecode>() {
//    @Override
//    public void valueChange(MValueChangeEvent<Tdescribecode> event) {
//    Notification.show("ss");
//    form.setEntity(event.getValue());
//    }
//    });

//-------------------- Header Table ------------------------------
//   isicontents.addComponents(table);
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
