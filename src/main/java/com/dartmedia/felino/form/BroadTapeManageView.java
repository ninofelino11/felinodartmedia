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
@CDIView("BroadTapeManage")
public class BroadTapeManageView extends MVerticalLayout implements View {
//BroadTapeManageSvc data=new BroadTapeManageSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**PGM date <p:calendar/><p:selectone>                                        <datasource="tgoodshistory"/>                                        </p:selectone><p:button/> belum ketemi**/
StringBuffer sb = new StringBuffer();
sb.append("SELECT /* schedule.xml : broadcast.schedule.selectTape by BroadTapeManageController */");
sb.append("            A.TAPE_CODE");
sb.append("           ,A.TAPE_NAME");
sb.append("           ,A.TAPE_NO");
sb.append("           ,A.TAPE_BARCODE");
sb.append("           ,TO_CHAR(A.MAKE_DATE, 'YYYY/MM/DD') AS MAKE_DATE");
sb.append("           ,A.SNO");
sb.append("           ,A.SNO1");
sb.append("           ,A.SNO_RATE");
sb.append("           ,A.SNO1_RATE");
sb.append("           ,A.RUNTIME_FR");
sb.append("           ,SUBSTR(A.RUNTIME_FR, 1, 2) AS HH_FR");
sb.append("           ,SUBSTR(A.RUNTIME_FR, 3, 2) AS MI_FR");
sb.append("           ,SUBSTR(A.RUNTIME_FR, 5, 2) AS SS_FR");
sb.append("           ,SUBSTR(A.RUNTIME_FR, 7, 3) AS MS_FR");
sb.append("           ,A.RUNTIME_TO");
sb.append("           ,'00' AS HH_TO");
sb.append("           ,SUBSTR(A.RUNTIME_TO, 1, 3) AS MI_TO");
sb.append("           ,SUBSTR(A.RUNTIME_TO, 4, 2) AS SS_TO");
sb.append("           ,'000' AS MS_TO");
sb.append("           ,A.TAPE_TYPE");
sb.append("           ,A.REVIEW_TYPE");
sb.append("           ,A.TAPE_DESC");
sb.append("           ,A.REVIEW_DESC");
sb.append("           ,A.USE_CODE");
sb.append("      FROM TPGMTAPE A");
sb.append("     WHERE A.TAPE_CODE = 'tape_code'");
sb.append(" ");
sb.append("");
sb.append("SELECT /* schedule.xml : broadcast.schedule.selectGoods by BroadTapeManageController */");
sb.append("            A.GOODS_CODE");
sb.append("            ,A.TAPE_CODE");
sb.append("            ,B.GOODS_NAME");
sb.append("            ,B.MD_CODE");
sb.append("            ,C.MD_NAME");
sb.append("            ,A.SPECIAL_YN");
sb.append("            ,B.LGROUP");
sb.append("       FROM TPGMTAPEGOODS A,");
sb.append("            TGOODS B,");
sb.append("            TMD C");
sb.append("      WHERE A.GOODS_CODE = B.GOODS_CODE");
sb.append("        AND B.MD_CODE = C.MD_CODE");
sb.append("        AND A.TAPE_CODE = 'tape_code'");
sb.append(" ");
sb.append("");
sb.append("SELECT /* schedule.xml : broadcast.schedule.selectShowHost by BroadTapeManageController */");
sb.append("            A.SNO");
sb.append("           ,A.TAPE_CODE");
sb.append("           ,B.USER_NAME");
sb.append("           ,A.SNO_RATE");
sb.append("           ,A.SPECIAL_YN");
sb.append("      FROM TPGMTAPESHOWHOST A,");
sb.append("           TUSER B");
sb.append("     WHERE A.SNO = B.USER_ID");
sb.append("       AND A.TAPE_CODE = 'tape_code'");
sb.append("  ");
sb.append("");
sb.append("SELECT /* schedule.xml : broadcast.schedule.selectTapeCount by BroadTapeManageController */");
sb.append("            COUNT(A.TAPE_CODE) AS CNT");
sb.append("        FROM TMULTIFRAMESCHE A, TMULTIDTBROAD B");
sb.append("        WHERE A.SEQ_FRAME_NO = B.SEQ_FRAME_NO");
sb.append("            AND A.TAPE_CODE = 'tape_code' AND B.BD_FLAG = '20'       ");
sb.append("   ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("PGM DATE"));
toolbar.addComponent(new ComboBox("Chanel"));
toolbar.addComponent(new Button("Broad Programing Tape Copy"));
//--No.,Start Time,Duration,End Time,Program,Program Name,Target Order Amt,Target Net Order Amt




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
                new Header("Tape Management (BroadTapeManage)"
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
