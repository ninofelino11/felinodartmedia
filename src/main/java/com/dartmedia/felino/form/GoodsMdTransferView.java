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
import com.vaadin.ui.TextField;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("GoodsMdTransfer")
public class GoodsMdTransferView extends MVerticalLayout implements View {
//GoodsMdTransferSvc data=new GoodsMdTransferSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**L Group: <p:inputText />  
//select distinct lgroup, lgroup_name from tgoodskinds order by lgroup 
M Group: <p:inputText />
//select distinct mgroup, mgroup_name from tgoodskinds where LGROUP = '15' order by mgroup
S Group: <p:inputText />
//select distinct sgroup, sgroup_name from tgoodskinds where LGROUP = '15' and MGROUP = '01' order by sgroup
D Group: <p:inputText />
//select distinct dgroup, dgroup_name from tgoodskinds where LGROUP = '15' and MGROUP = '01' and SGROUP = '04' order by dgroup
Item: <p:inputText />
//select goods_code,goods_name, md_code from tgoods where LGROUP = '15' and MGROUP = '01' and SGROUP = '04' and DGROUP = 01 order by goods_name
MD: <p:inputText />
//select md_code, md_name from tmd where md_code = '0002'
MD: <p:inputText />
//select md_code, md_name from tmd**/
StringBuffer sb = new StringBuffer();
sb.append("select");
sb.append("distinct");
sb.append("lgroup");
sb.append("lgroup_name");
sb.append("from");
sb.append("tgoodskinds");
sb.append("order");
sb.append("by");
sb.append("lgroup;");
sb.append("select");
sb.append("distinct");
sb.append("mgroup");
sb.append("mgroup_name");
sb.append("from");
sb.append("tgoodskinds");
sb.append("where");
sb.append("LGROUP");
sb.append("=");
sb.append("'15'");
sb.append("order");
sb.append("by");
sb.append("mgroup");
sb.append("select");
sb.append("distinct");
sb.append("sgroup");
sb.append("sgroup_name");
sb.append("from");
sb.append("tgoodskinds");
sb.append("where");
sb.append("LGROUP");
sb.append("=");
sb.append("'15'");
sb.append("and");
sb.append("MGROUP");
sb.append("=");
sb.append("'01'");
sb.append("order");
sb.append("by");
sb.append("sgroup");
sb.append("select");
sb.append("distinct");
sb.append("dgroup");
sb.append("dgroup_name");
sb.append("from");
sb.append("tgoodskinds");
sb.append("where");
sb.append("LGROUP");
sb.append("=");
sb.append("'15'");
sb.append("and");
sb.append("MGROUP");
sb.append("=");
sb.append("'01'");
sb.append("and");
sb.append("SGROUP");
sb.append("=");
sb.append("'04'");
sb.append("order");
sb.append("by");
sb.append("dgroup");
sb.append("select");
sb.append("goods_code");
sb.append("goods_name");
sb.append("md_code");
sb.append("from");
sb.append("tgoods");
sb.append("where");
sb.append("LGROUP");
sb.append("=");
sb.append("'15'");
sb.append("and");
sb.append("MGROUP");
sb.append("=");
sb.append("'01'");
sb.append("and");
sb.append("SGROUP");
sb.append("=");
sb.append("'04'");
sb.append("and");
sb.append("DGROUP");
sb.append("=");
sb.append("01");
sb.append("order");
sb.append("by");
sb.append("goods_name");
sb.append("select");
sb.append("md_code");
sb.append("md_name");
sb.append("from");
sb.append("tmd");
sb.append("where");
sb.append("md_code");
sb.append("=");
sb.append("'0002'");
sb.append("select");
sb.append("md_code");
sb.append("md_name");
sb.append("from");
sb.append("tmd");
sb.append("where");
sb.append("md_code");
sb.append("=");
sb.append("'0002'");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
//-toolbar.addComponent(new PopupDateField("My Date"));
//-toolbar.addComponent(new TextField("Indv.Query"));
//-toolbar.addComponent(new ComboBox("My ComboBox"));
//-toolbar.addComponent(new Button("My ComboBox"));
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
                new Header("MD Transfer (GoodsMdTransfer)")
        );
        addComponents(toolmenu);
        addComponents(toolbar); 
        addComponents(isicontents);
        isicontents.addComponent(new TextField("L.GRP"));
        isicontents.addComponent(new TextField("M.GRP"));
        isicontents.addComponent(new TextField("S.GRP"));
        isicontents.addComponent(new TextField("D.GRP"));
        isicontents.addComponent(new TextField("ITEM"));
        isicontents.addComponent(new TextField("Md"));
        isicontents.addComponent(new Button("Md"));
        
        isicontents.addComponent(new TextField("Md"));
        
        
        
        
        
        
        
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
