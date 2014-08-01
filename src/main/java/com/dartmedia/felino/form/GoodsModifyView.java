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
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("GoodsModify")
public class GoodsModifyView extends MVerticalLayout implements View {
//GoodsModifySvc data=new GoodsModifySvc();
    @PostConstruct
    public void initComponent() {
/**Reg Term: <p:calendar id='fromDate'/> ~ <p:calendar id='toDate'/>
L Grp. Code <p:inputText/>
//select distinct lgroup, lgroup_name from tgoodskinds; 
M Group Code <p:inputText/>
/*
SELECT /* goodsbase.xml : manage.goodsbase.selectMgroupList by GoodsKindsController */
/*
SELECT /* goodsbase.xml : manage.goodsbase.selectSgroupList by GoodsKindsController */
/*
SELECT /* goodsbase.xml : manage.goodsbase.selectDgroupList by GoodsKindsController */
//*/
//Master Code <p:inputText />
//select master_code, goods_name, TCODE_NAME('B032', SALE_GB) AS SALE_NAME from TGOODS 
//Item <p:inputText />
//select goods_code, goods_name, TCODE_NAME('B032', SALE_GB) AS SALE_NAME from TGOODS **/
StringBuffer sb = new StringBuffer();
sb.append("SELECT");
sb.append("/*");
sb.append("goods.xml");
sb.append(":");
sb.append("manage.goods.selectGoodsModifyList");
sb.append("by");
sb.append("GoodsModifyController");
sb.append("*/");
sb.append("A.GOODS_CODE");
sb.append("A.GOODS_NAME");
sb.append("A.MASTER_CODE");
sb.append("D.GOODS_NAME");
sb.append("AS");
sb.append("MASTER_NAME");
sb.append("A.LGROUP");
sb.append("A.MGROUP");
sb.append("A.SGROUP");
sb.append("A.DGROUP");
sb.append("A.BRAND_CODE");
sb.append("B.BRAND_NAME");
sb.append("A.ORIGIN_CODE");
sb.append("C.CODE_NAME");
sb.append("AS");
sb.append("ORIGIN_NAME");
sb.append("FROM");
sb.append("TGOODS");
sb.append("A");
sb.append("TBRAND");
sb.append("B");
sb.append("TCODE");
sb.append("C");
sb.append("TGOODS");
sb.append("D");
sb.append("WHERE");
sb.append("A.MASTER_CODE");
sb.append("=");
sb.append("D.GOODS_CODE");
sb.append("AND");
sb.append("A.BRAND_CODE");
sb.append("=");
sb.append("B.BRAND_CODE");
sb.append("AND");
sb.append("C.CODE_LGROUP");
sb.append("=");
sb.append("'B023'");
sb.append("AND");
sb.append("A.ORIGIN_CODE");
sb.append("=");
sb.append("C.CODE_MGROUP");
sb.append("AND");
sb.append("A.INSERT_DATE");
sb.append(">=");
sb.append("TO_DATE('2013-01-01'");
sb.append("'YYYY/MM/DD')");
sb.append("AND");
sb.append("A.INSERT_DATE");
sb.append("<");
sb.append("TO_DATE('2014-01-01'");
sb.append("'YYYY/MM/DD')");
sb.append("+");
sb.append("1");
sb.append("AND");
sb.append("A.LGROUP");
sb.append("LIKE");
sb.append("'10'");
sb.append("||");
sb.append("'%'");
sb.append("AND");
sb.append("A.MGROUP");
sb.append("LIKE");
sb.append("'1'");
sb.append("||");
sb.append("'%'");
sb.append("AND");
sb.append("A.SGROUP");
sb.append("LIKE");
sb.append("'1'");
sb.append("||");
sb.append("'%'");
sb.append("AND");
sb.append("A.DGROUP");
sb.append("LIKE");
sb.append("'1'");
sb.append("||");
sb.append("'%'");
sb.append("AND");
sb.append("A.MASTER_CODE");
sb.append("LIKE");
sb.append("'1'");
sb.append("||");
sb.append("'%'");
sb.append("AND");
sb.append("A.GOODS_CODE");
sb.append("LIKE");
sb.append("'1'");
sb.append("||");
sb.append("'%';");
//String fsql = data.makeSql();
gSqlContainer sumber=new gSqlContainer();
MTable table=new MTable();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Reg Term"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new TextField("l grp"));
toolbar.addComponent(new TextField("S GRP"));
toolbar.addComponent(new TextField("M Group"));
toolbar.addComponent(new TextField("D Group"));
toolbar.addComponent(new TextField("Master Code"));
toolbar.addComponent(new TextField("Item"));






//-toolbar.addComponent(new ComboBox("My ComboBox"));
//-toolbar.addComponent(new Button("My ComboBox"));
//-------------------- Header  ------------------------------
//-------------------- Header Table ---judul untuk table----------
table.addContainerProperty("No", String.class,  null);
table.addContainerProperty("ITEM CODE", String.class,  null);
table.addContainerProperty("MASTER CODE", String.class,  null);
table.addContainerProperty("MASTER ITEM NAME", String.class,  null);
table.addContainerProperty("L.GRP", String.class,  null);
table.addContainerProperty("MGRP", String.class,  null);
table.addContainerProperty("S GRP", String.class,  null);
table.addContainerProperty("BRAND", String.class,  null);
table.addContainerProperty("Brand Name", String.class,  null);
table.addContainerProperty("Origin nAME", String.class,  null);

       
        

//-------------------- Header Table ------------------------------ 
MHorizontalLayout toolmenu;
toolmenu = new MHorizontalLayout();
toolmenu.addComponent(new Button("User Enviromnet"));
toolmenu.addComponent(new Button("Ret"));
toolmenu.addComponent(new Button("Ins"));
toolmenu.addComponent(new Button("Save"));
toolmenu.addComponent(new Button("Print"));
toolmenu.addComponent(new Button("XLS"));

        addComponents( 
                new Header("GoodsModify")
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
