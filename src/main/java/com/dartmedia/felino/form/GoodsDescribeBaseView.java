package com.dartmedia.felino.form;
import com.dartmedia.felino.Tdescribecode;
import com.dartmedia.felino.TdescribecodeFacade;
import com.dartmedia.felino.gSqlContainer;
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
@CDIView("GoodsDescribeBase")
public class GoodsDescribeBaseView extends MVerticalLayout implements View {
//GoodsDescribeBaseSvc data=new GoodsDescribeBaseSvc();
@Inject   TdescribecodeFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Description Code/Title <p:inputText/>
//select describe_code, describe_title from TDESCRIBECODE**/
StringBuffer sb = new StringBuffer();
sb.append("SELECT");
sb.append("/*");
sb.append("goodsbase.xml");
sb.append(":");
sb.append("manage.goodsbase.selectGoodsDescribeBaseList");
sb.append("by");
sb.append("GoodsDescribeBaseController");
sb.append("*/");
sb.append("A.DESCRIBE_CODE");
sb.append("A.DESCRIBE_TITLE");
sb.append("A.WEB_FLAG");
sb.append("A.REQUIRED_YN");
sb.append("TO_CHAR(A.INSERT_DATE");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("INSERT_DATE");
sb.append("A.INSERT_ID");
sb.append("TO_CHAR(A.MODIFY_DATE");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("MODIFY_DATE");
sb.append("A.MODIFY_ID");
sb.append("A.SORT_SEQ");
sb.append("A.USE_YN");
sb.append("FROM");
sb.append("TDESCRIBECODE");
sb.append("A");
sb.append("WHERE");
sb.append("A.DESCRIBE_CODE");
sb.append("LIKE");
sb.append("'101'");
//String fsql = data.makeSql();
gSqlContainer sumber=new gSqlContainer();
//MTable table=new MTable();
List<Tdescribecode> findAll = cf.findAll();
MTable<Tdescribecode> table=new MTable<Tdescribecode>(Tdescribecode.class);
table.setBeans(findAll);
table.addMValueChangeListener(new MValueChangeListener<Tdescribecode>() {
    @Override
    public void valueChange(MValueChangeEvent<Tdescribecode> event) {
  //  Notification.show("ss");
//    form.setEntity(event.getValue());
    }   
    });





MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
MHorizontalLayout toolmenu;
toolmenu = new MHorizontalLayout();
toolmenu.addComponent(new Button("Ret"));
toolmenu.addComponent(new Button("Ins"));
toolmenu.addComponent(new Button("Save"));
toolmenu.addComponent(new Button("Print"));
toolmenu.addComponent(new Button("XLS"));

toolbar.addComponent(new TextField("Description Code/Title"));      
//-- No, Desc Code Description Title, Web Use,Essential  

addComponents( 
                new Header("GoodsDescribeBase")
        );
        addComponents(toolmenu);
        addComponents(toolbar); 
        addComponents(sidebar);
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
