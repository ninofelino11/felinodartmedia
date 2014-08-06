package com.dartmedia.felino;

import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import org.vaadin.maddon.fields.MTextField;
import org.vaadin.maddon.fields.TypedSelect;
import org.vaadin.maddon.form.AbstractForm;
import org.vaadin.maddon.layouts.MHorizontalLayout;

public class TbaljumForm extends AbstractForm<Tbaljum> {
    TypedSelect  whCode =new TypedSelect();
    private TextField baljuNo = new MTextField("baljuNo","");
    private TextField entpCode = new MTextField("entpCode","");
    private TextField entpManSeq = new MTextField("entpManSeq","");
    private TextField entpBaljuSeq = new MTextField("entpBaljuSeq");
    private TextField baljuDate = new MTextField("baljuDate");
    private TextField baljuDelyDate = new MTextField("baljuDelyDate");
    private TextField baljuGb = new MTextField("baljuGb");
    private TextField rebaljuYn = new MTextField("rebaljuYn");
    private TextField rebaljuCode = new MTextField("rebaljuCode");
    private TextField rebaljuNote = new MTextField("rebaljuNote");
    private TextField baljuPrtCnt = new MTextField("baljuPrtCnt");
    private TextField lookYn = new MTextField("lookYn");
    private TextField lookDate = new MTextField("lookDate");
    private TextField lookId = new MTextField("lookId");
    private TextField ipgoPlanDate = new MTextField("ipgoPlanDate");
    private TextField chCode = new MTextField("chCode");
    private TextField chNote = new MTextField("chNote");
    private TextField ctrlInDate = new MTextField("ctrlInDate");
    private TextField ctrlDate = new MTextField("ctrlDate");
    private TextField ctrlId = new MTextField("ctrlId");
    private TextField inEndYn = new MTextField("inEndYn");
    private TextField inPlanDate = new MTextField("inPlanDate");
    private TextField inEndDate = new MTextField("inEndDate");
    private TextField cancelYn = new MTextField("cancelYn");
    private TextField cancelCode = new MTextField("cancelCode");
    private TextField cancelNote = new MTextField("cancelNote");
    private TextField cancelId = new MTextField("cancelId");
    private TextField cancelDate = new MTextField("cancelDate");
    private TextField oldBaljuNo = new MTextField("oldBaljuNo");
    private TextField baljuSeq = new MTextField("baljuSeq");
    private TextField note = new MTextField("note");
    private TextField insertId = new MTextField("insertId");
    private TextField insertDate = new MTextField("insertDate");
    private TextField autoYn = new MTextField("autoYn");
    private TextField sentToMnc = new MTextField("sentToMnc");
    private TextField canceledToMnc = new MTextField("canceledToMnc");
    private TextField mdCode = new MTextField("mdCode");
 //   private TextField whCode = new MTextField("whCode");
    
    @Override
    protected Component createContent() {
        return new MHorizontalLayout(
                new FormLayout(
                        
                        whCode,
                       baljuNo,
                     //   entpCode,
                     //   entpManSeq,
                     //   entpBaljuSeq,
                     //   baljuDate,
                     //   baljuDelyDate,
                    //    baljuGb,
                    //    rebaljuYn,
                     //   rebaljuCode,
                     //   rebaljuNote,
                     //   baljuPrtCnt,
                     //   lookYn,
                     //   lookDate,
                     //   lookId,
                     //   ipgoPlanDate,
                      //  chCode,
                        chNote,
                        ctrlInDate,
                        ctrlDate,
                        ctrlId,
                        inEndYn,
                        inPlanDate,
                     //   inEndDate,
                     //   cancelYn,
                     //   cancelCode,
                     //   cancelNote,
                     //   cancelId,
                     //   cancelDate,
                        oldBaljuNo,
                        baljuSeq,
                        note,
                     //   insertId,
                     //   insertDate,
                    //    autoYn,
                     //   sentToMnc,
                     //   canceledToMnc,
                        mdCode
                ),
                getToolbar()
                
        );
        
    }
    
}
