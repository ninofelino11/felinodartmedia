package com.dartmedia.felino;

import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import org.vaadin.maddon.fields.MTextField;
import org.vaadin.maddon.form.AbstractForm;
import org.vaadin.maddon.layouts.MVerticalLayout;

public class TgoodsForm extends AbstractForm<Tgoods> {
    
    
   HorizontalLayout horizontalLayout = new HorizontalLayout(new TextField(), new TextField());
   
  
      
    
    final HorizontalLayout horizontalLayout2 = new HorizontalLayout(new Label(" Vendor "),new TextField(), new TextField());
    final HorizontalLayout horizontalLayout3 = new HorizontalLayout(new Label(" Vendor rep "),new TextField(), new TextField());
    final HorizontalLayout horizontalLayout4 = new HorizontalLayout(new Label(" Reg date "),new TextField(), new TextField());
    final HorizontalLayout horizontalLayout5 = new HorizontalLayout(new Label(" Sale Type "),new TextField(), new TextField());
    
    final HorizontalLayout horizontalLayout6 = new HorizontalLayout(new Label(" L GRP "),new TextField(), new TextField());
    final HorizontalLayout horizontalLayout7 = new HorizontalLayout(new Label(" S GRP "),new TextField(), new TextField());
    
    final HorizontalLayout horizontalLayout8 = new HorizontalLayout(new Label(" Vat "),new TextField(), new TextField());
    
    private TextField goodsCode = new MTextField("Item");
    private TextField goodsName = new MTextField("Vendor");
    private TextField lgroup = new MTextField("Vendor rep");
    private TextField mgroup = new MTextField("Master code");
    private TextField sgroup = new MTextField("Reg Date");
    private TextField dgroup = new MTextField("Sale Type");
    private TextField lmsdCode = new MTextField("lmsdCode");
    private TextField qcLgroup = new MTextField("qcLgroup");
    private TextField qcMgroup = new MTextField("qcMgroup");
    private TextField saleGb = new MTextField("saleGb");
    private TextField signGb = new MTextField("signGb");
    private TextField entpCode = new MTextField("entpCode");
    private TextField entpManSeq = new MTextField("entpManSeq");
    private TextField accountGb = new MTextField("accountGb");
    private TextField mdCode = new MTextField("mdCode");
    private TextField keyword = new MTextField("keyword");
    private TextField buyMed = new MTextField("buyMed");
    private TextField delyType = new MTextField("delyType");
    private TextField whCode = new MTextField("whCode");
    private TextField postYn = new MTextField("postYn");
    private TextField taxYn = new MTextField("taxYn");
    private TextField vatRate = new MTextField("vatRate");
    private TextField costTaxYn = new MTextField("costTaxYn");
    private TextField costVatRate = new MTextField("costVatRate");
    private TextField makecoCode = new MTextField("makecoCode");
    private TextField originCode = new MTextField("originCode");
    private TextField brandCode = new MTextField("brandCode");
    private TextField handleCode = new MTextField("handleCode");
    private TextField mixpackYn = new MTextField("mixpackYn");
    private TextField packBox = new MTextField("packBox");
    private TextField sqcYn = new MTextField("sqcYn");
    private TextField sqcGb = new MTextField("sqcGb");
    private TextField setYn = new MTextField("setYn");
    private TextField setGoodsYn = new MTextField("setGoodsYn");
    private TextField giftYn = new MTextField("giftYn");
    private TextField payYn = new MTextField("payYn");
    private TextField dmYn = new MTextField("dmYn");
    private TextField giftReturnYn = new MTextField("giftReturnYn");
    private TextField exchYn = new MTextField("exchYn");
    private TextField returnYn = new MTextField("returnYn");
    private TextField asYn = new MTextField("asYn");
    private TextField outStockYn = new MTextField("outStockYn");
    private TextField orderMinQty = new MTextField("orderMinQty");
    private TextField stockChkPlace = new MTextField("stockChkPlace");
    private TextField firstBroadDate = new MTextField("firstBroadDate");
    private TextField orderMediaAllYn = new MTextField("orderMediaAllYn");
    private TextField orderMedia = new MTextField("orderMedia");
    private TextField arsName = new MTextField("arsName");
    private TextField parentGoodsCode = new MTextField("parentGoodsCode");
    private TextField baseGoodsCode = new MTextField("baseGoodsCode");
    private TextField norestAllotYn = new MTextField("norestAllotYn");
    private TextField norestAllotMonths = new MTextField("norestAllotMonths");
    private TextField custDcYn = new MTextField("custDcYn");
    private TextField sampleYn = new MTextField("sampleYn");
    private TextField saleStartDate = new MTextField("saleStartDate");
    private TextField vVolume = new MTextField("vVolume");
    private TextField masterCode = new MTextField("masterCode");
    private TextField inUnit = new MTextField("inUnit");
    private TextField inUnitQty = new MTextField("inUnitQty");
    private TextField recoCnt = new MTextField("recoCnt");
    private TextField orderRank = new MTextField("orderRank");
    private TextField commentCnt = new MTextField("commentCnt");
    private TextField weight = new MTextField("weight");
    private TextField formCode = new MTextField("formCode");
    private TextField sizeCode = new MTextField("sizeCode");
    private TextField whFixYn = new MTextField("whFixYn");
    private TextField directShipYn = new MTextField("directShipYn");
    private TextField payFlag = new MTextField("payFlag");
    private TextField advcRefundYn = new MTextField("advcRefundYn");
    private TextField directReturnYn = new MTextField("directReturnYn");
    private TextField shipManSeq = new MTextField("shipManSeq");
    private TextField returnManSeq = new MTextField("returnManSeq");
    private TextField orderMakeYn = new MTextField("orderMakeYn");
    private TextField installYn = new MTextField("installYn");
    private TextField insertId = new MTextField("insertId");
    private TextField insertDate = new MTextField("insertDate");
    private TextField modifyId = new MTextField("modifyId");
    private TextField modifyDate = new MTextField("modifyDate");
    private TextField delyBoxQty = new MTextField("delyBoxQty");
    private TextField directYn = new MTextField("directYn");
    private TextField entpReturnYn = new MTextField("entpReturnYn");
    private TextField wmsWidth = new MTextField("wmsWidth");
    private TextField wmsLength = new MTextField("wmsLength");
    private TextField wmsHeight = new MTextField("wmsHeight");
    private TextField wmsVolume = new MTextField("wmsVolume");
    private TextField freeDeliveryYn = new MTextField("freeDeliveryYn");
    private TextField extended = new MTextField("extended");
    private TextField warrantyPeriode = new MTextField("warrantyPeriode");
    private TextField exPromoYn = new MTextField("exPromoYn");
    
    @Override
    protected Component createContent() {
        return new MVerticalLayout(
                new FormLayout(
                        horizontalLayout,
                        horizontalLayout2,
                        horizontalLayout3,
                        horizontalLayout4,
                        goodsCode,
                        goodsName,
                        lgroup,
                        mgroup,
                        sgroup,
                        dgroup,
                        lmsdCode,
                        qcLgroup,
                        qcMgroup,
                        saleGb,
                        signGb,
                        entpCode,
                        entpManSeq,
                        accountGb,
                        mdCode,
                        keyword,
                        buyMed,
                        delyType,
                        whCode,
                        postYn,
                        taxYn,
                        vatRate,
                        costTaxYn,
                        costVatRate,
                        makecoCode,
                        originCode,
                        brandCode,
                        handleCode,
                        mixpackYn,
                        packBox,
                        sqcYn,
                        sqcGb,
                        setYn,
                        setGoodsYn,
                        giftYn,
                        payYn,
                        dmYn,
                        giftReturnYn,
                        exchYn,
                        returnYn,
                        asYn,
                        outStockYn,
                        orderMinQty,
                        stockChkPlace,
                        firstBroadDate,
                        orderMediaAllYn,
                        orderMedia,
                        arsName,
                        parentGoodsCode,
                        baseGoodsCode,
                        norestAllotYn,
                        norestAllotMonths,
                        custDcYn,
                        sampleYn,
                        saleStartDate,
                        vVolume,
                        masterCode,
                        inUnit,
                        inUnitQty,
                        recoCnt,
                        orderRank,
                        commentCnt,
                        weight,
                        formCode,
                        sizeCode,
                        whFixYn,
                        directShipYn,
                        payFlag,
                        advcRefundYn,
                        directReturnYn,
                        shipManSeq,
                        returnManSeq,
                        orderMakeYn,
                        installYn,
                        insertId,
                        insertDate,
                        modifyId,
                        modifyDate,
                        delyBoxQty,
                        directYn,
                        entpReturnYn,
                        wmsWidth,
                        wmsLength,
                        wmsHeight,
                        wmsVolume,
                        freeDeliveryYn,
                        extended,
                        warrantyPeriode,
                        exPromoYn
                ),
                getToolbar()
        );
    }
    
}
