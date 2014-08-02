package com.dartmedia.felino.form;
import com.dartmedia.felino.Tgoods;
import com.dartmedia.felino.TgoodsFacade;
import com.vaadin.cdi.CDIView;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
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
@CDIView("GoodsList")
public class GoodsListView extends MVerticalLayout implements View {
//GoodsListSvc data=new GoodsListSvc();
@Inject   TgoodsFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
StringBuffer sb = new StringBuffer();
sb.append("             select /* goods.xml : manage.goods.selectGoodsList by GoodsList */");
sb.append("               GD.GOODS_CODE");
sb.append("             , GD.GOODS_NAME");
sb.append("             , GD.SET_YN");
sb.append("             , GD.SAMPLE_YN");
sb.append("             , GD.DM_YN");
sb.append("             , GD.GIFT_YN");
sb.append("             , GD.PAY_YN");
sb.append("             , MD.MD_NAME");
sb.append("             , EP.ENTP_CODE");
sb.append("             , EP.ENTP_NAME");
sb.append("             , GD.SALE_GB");
sb.append("             , GD.SQC_GB");
sb.append("             , KD.LGROUP_NAME");
sb.append("             , KD.MGROUP_NAME");
sb.append("             , KD.SGROUP_NAME");
sb.append("             , KD.DGROUP_NAME");
sb.append("             , MD.MD_CODE");
sb.append("             , FUN_GET_GOODS_PRICE(GD.GOODS_CODE, SYSDATE,'3') AS BUY_PRICE");
sb.append("             , FUN_GET_GOODS_PRICE(GD.GOODS_CODE, SYSDATE,'6') AS SALE_PRICE");
sb.append("             , TO_CHAR(GD.INSERT_DATE, 'YYYY/MM/DD') AS INSERT_DATE");
sb.append("             , EU.ENTP_MAN_SEQ");
sb.append("             , EU.ENTP_MAN_NAME");
sb.append("             , DECODE(EU.ENTP_MAN_TEL2, NULL, ' ', LTRIM(EU.ENTP_MAN_DDD) || '-' || LTRIM(EU.ENTP_MAN_TEL1) || '-' || LTRIM(EU.ENTP_MAN_TEL2)) AS ENTP_MAN_TEL");
sb.append("             , DECODE(EU.ENTP_MAN_HP3, NULL, ' ', LTRIM(EU.ENTP_MAN_HP1) || '-' || LTRIM(EU.ENTP_MAN_HP2) || '-' || LTRIM(EU.ENTP_MAN_HP3)) AS ENTP_HAND_TEL");
sb.append("             , GD.NOREST_ALLOT_MONTHS");
sb.append("             , GD.DELY_TYPE");
sb.append("             , GD.SIGN_GB");
sb.append("             , GD.MASTER_CODE");
sb.append("             , BR.BRAND_CODE, BR.BRAND_NAME");
sb.append("             , GD.ORIGIN_CODE, TCODE_NAME('B023',GD.ORIGIN_CODE) AS ORIGIN_NAME");
sb.append("             , MA.MAKECO_CODE, MA.MAKECO_NAME");
sb.append("             , GD.WMS_WIDTH");
sb.append("             , GD.WMS_HEIGHT");
sb.append("             , GD.WMS_LENGTH");
sb.append("             , GD.WMS_VOLUME");
sb.append("          from TGOODS       GD");
sb.append("             , TGOODSKINDS  KD");
sb.append("             , TENTERPRISE  EP");
sb.append("             , TMD          MD");
sb.append("             , TMAKECOMP    MA");
sb.append("             , TBRAND       BR");
sb.append("             , TENTPUSER    EU");
sb.append("         where GD.LGROUP     = KD.LGROUP");
sb.append("           and GD.MGROUP     = KD.MGROUP");
sb.append("           and GD.SGROUP     = KD.SGROUP");
sb.append("           and GD.DGROUP     = KD.DGROUP");
sb.append("           and GD.ENTP_CODE  = EP.ENTP_CODE");
sb.append("           and GD.MD_CODE    = MD.MD_CODE");
sb.append("           AND    GD.BRAND_CODE   = BR.BRAND_CODE");
sb.append("           AND    GD.MAKECO_CODE  = MA.MAKECO_CODE");
sb.append("           AND    GD.ENTP_CODE    = EU.ENTP_CODE");
sb.append("           AND    GD.ENTP_MAN_SEQ = EU.ENTP_MAN_SEQ");
sb.append("    ");
sb.append("        ");
sb.append("                ORDER BY GD.INSERT_DATE DESC    ");
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
toolbar.addComponent(new TextField("MD"));
toolbar.addComponent(new TextField("Vendor"));
toolbar.addComponent(new TextField("L GRP"));
toolbar.addComponent(new TextField("M GRP"));
toolbar.addComponent(new TextField("S GRP"));
toolbar.addComponent(new CheckBox("Sale Type"));

toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new CheckBox("Master Code"));



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
                new Header("Item List (GoodsList)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
        addComponents(isicontents);
      

//-------------------- Header Table ---judul untuk table----------
List<Tgoods> findAll = cf.findAll();
MTable<Tgoods> table=new MTable<Tgoods>(Tgoods.class);
        //.withProperties("entpName");
table.setBeans(findAll);
table.addMValueChangeListener(new MValueChangeListener<Tgoods>() {
    @Override
    public void valueChange(MValueChangeEvent<Tgoods> event) {
    Notification.show("ss");
   // form.setEntity(event.getValue());
    }
    });
  addComponents(table);
//-------------------- Header Table ------------------------------
  // isicontents.addComponents(table);
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
