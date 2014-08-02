package com.dartmedia.felino;

import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import org.vaadin.maddon.fields.MTextField;
import org.vaadin.maddon.form.AbstractForm;
import org.vaadin.maddon.layouts.MVerticalLayout;

public class TenterpriseForm extends AbstractForm<Tenterprise> {
    
    private TextField entpCode = new MTextField("Vendor");
    private TextField entpName = new MTextField("N");
    private TextField entpGubun = new MTextField("Gubun");
    private TextField sIdno = new MTextField("sIdno");
    private TextField workType = new MTextField("workType");
    private TextField workKind = new MTextField("workKind");
    private TextField entpPost = new MTextField("entpPost");
    private TextField entpPostSeq = new MTextField("entpPostSeq");
    private TextField entpAddr = new MTextField("Address");
    private TextField entpDdd = new MTextField("entpDdd");
    private TextField entpTel1 = new MTextField("Telp");
    private TextField entpTel2 = new MTextField("Tel2");
    private TextField entpTel3 = new MTextField("Tel3");
    private TextField entpFax1 = new MTextField("Fax1");
    private TextField entpFax2 = new MTextField("Fax2");
    private TextField entpFax3 = new MTextField("Fax3");
    private TextField emailAddr = new MTextField("Email Adress");
    private TextField ownerName = new MTextField("ownerName");
    private TextField dishonorYn = new MTextField("dishonorYn");
    private TextField secretNo = new MTextField("secretNo");
    private TextField firstDate = new MTextField("firstDate");
    private TextField closeDate = new MTextField("closeDate");
    private TextField closeReason = new MTextField("closeReason");
    private TextField delyGb = new MTextField("delyGb");
    private TextField taxRate = new MTextField("taxRate");
    private TextField etc = new MTextField("etc");
    private TextField insertId = new MTextField("insertId");
    private TextField insertDate = new MTextField("insertDate");
    private TextField modifyId = new MTextField("modifyId");
    private TextField modifyDate = new MTextField("modifyDate");
    private TextField foreignYn = new MTextField("foreignYn");
    private TextField accountGb = new MTextField("accountGb");
    private TextField sapVendorCode = new MTextField("sapVendorCode");
    private TextField paymentTerm = new MTextField("paymentTerm");
    private TextField sentToMnc = new MTextField("sentToMnc");
    
    @Override
    protected Component createContent() {
        return new MVerticalLayout(
                new FormLayout(
                        entpCode,
                        entpName,
                  //      entpGubun,
                        sIdno,
                        workType,
                        workKind,
                        entpPost,
                        entpPostSeq,
                        entpAddr,
                        entpDdd,
                        entpTel1,
                        entpTel2,
                        entpTel3,
                        entpFax1,
                        entpFax2,
                        entpFax3,
                        emailAddr,
                        ownerName,
                     //   dishonorYn,
                     //   secretNo,
                     //   firstDate,
                        closeDate,
                        closeReason,
                        delyGb,
                        taxRate,
                      //  etc,
                      //  insertId,
                      //  insertDate,
                     //   modifyId,
                    //    modifyDate,
                    //    foreignYn,
                     //   accountGb,
                        sapVendorCode,
                        paymentTerm,
                        sentToMnc
                ),
                getToolbar()
        );
    }
    
}
