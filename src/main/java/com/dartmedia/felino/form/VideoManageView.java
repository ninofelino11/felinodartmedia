package com.dartmedia.felino.form;
import com.dartmedia.felino.Tvideomanage;
import com.dartmedia.felino.TvideomanageFacade;
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
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
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
@CDIView("VideoManage")
public class VideoManageView extends MVerticalLayout implements View {
//VideoManageSvc data=new VideoManageSvc();
@Inject   TvideomanageFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/****/
StringBuffer sb = new StringBuffer();
sb.append(" SELECT        /* production.xml : broadcast.production.selectVideoManage by VideoManageController */");
sb.append("            VIDEO_CODE,");
sb.append("            VIDEO_NAME,");
sb.append("            TRUNC((VIDEO_TIME/60),0) AS VIDEO_TIME_M,");
sb.append("            MOD(VIDEO_TIME, 60) AS VIDEO_TIME_S,");
sb.append("            USE_YN,");
sb.append("            VIDEO_KINDS,");
sb.append("            VIDEO_NOTE,");
sb.append("            REVIEW_YN,");
sb.append("            TO_CHAR(REVIEW_DATE, 'YYYY/MM/DD') AS REVIEW_DATE,");
sb.append("            REVIEW_ID,");
sb.append("            REVIEW_NOTE,");
sb.append("            PRODUCTION,");
sb.append("            INSERT_ID,");
sb.append("            TO_CHAR(INSERT_DATE, 'YYYY/MM/DD') AS INSERT_DATE,");
sb.append("            MODIFY_ID,");
sb.append("            MODIFY_DATE");
sb.append("        FROM    TVIDEOMANAGE");
sb.append("        WHERE    VIDEO_CODE LIKE 'Video_Id' || '%'");
sb.append("        AND    VIDEO_KINDS LIKE 'Video_Kind' || '%'");
sb.append("          AND     INSERT_DATE >= TO_DATE('2014-01-01', 'YYYY/MM/DD')");
sb.append("          AND     INSERT_DATE < TO_DATE('2014-01-01', 'YYYY/MM/DD') + 1");
sb.append("            AND     USE_YN = 'Use_Yn'");
sb.append("        ORDER BY INSERT_DATE ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Reg Term"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new ComboBox("Video type"));
toolbar.addComponent(new TextField("Video Code"));
toolbar.addComponent(new TextField(""));
toolbar.addComponent(new CheckBox("All"));
toolbar.addComponent(new CheckBox("use"));
toolbar.addComponent(new CheckBox("unuse"));



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
                new Header("Insert Management (VideoManage)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
        addComponents(isicontents);
//MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
List<Tvideomanage> findAll = cf.findAll();
MTable<Tvideomanage> table=new MTable<Tvideomanage>(Tvideomanage.class);
        //.withProperties("entpName");
table.setBeans(findAll);
table.addMValueChangeListener(new MValueChangeListener<Tvideomanage>() {
    @Override
    public void valueChange(MValueChangeEvent<Tvideomanage> event) {
//    Notification.show("ss");
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
