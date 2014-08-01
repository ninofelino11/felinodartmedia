package com.dartmedia.felino.form;

import com.cware.back.entity.table.Tenterprise;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import org.vaadin.maddon.fields.MTextField;
import org.vaadin.maddon.form.AbstractForm;
import org.vaadin.maddon.layouts.MVerticalLayout;

public class TenterpriseForm extends AbstractForm<Tenterprise> {
    
    private TextField entp_code = new MTextField("entp_code");
    private TextField entp_name = new MTextField("entp_name");
    private TextField entp_gubun = new MTextField("entp_gubun");
    private TextField s_idno = new MTextField("s_idno");
    private TextField work_type = new MTextField("work_type");
    private TextField work_kind = new MTextField("work_kind");
    private TextField entp_post = new MTextField("entp_post");
    private TextField entp_post_seq = new MTextField("entp_post_seq");
    private TextField entp_addr = new MTextField("entp_addr");
    private TextField entp_ddd = new MTextField("entp_ddd");
    private TextField entp_tel1 = new MTextField("entp_tel1");
    private TextField entp_tel2 = new MTextField("entp_tel2");
    private TextField entp_tel3 = new MTextField("entp_tel3");
    private TextField entp_fax1 = new MTextField("entp_fax1");
    private TextField entp_fax2 = new MTextField("entp_fax2");
    private TextField entp_fax3 = new MTextField("entp_fax3");
    private TextField email_addr = new MTextField("email_addr");
    private TextField owner_name = new MTextField("owner_name");
    private TextField dishonor_yn = new MTextField("dishonor_yn");
    private TextField secret_no = new MTextField("secret_no");
    private TextField first_date = new MTextField("first_date");
    private TextField close_date = new MTextField("close_date");
    private TextField close_reason = new MTextField("close_reason");
    private TextField etc = new MTextField("etc");
    private TextField insert_date = new MTextField("insert_date");
    private TextField insert_id = new MTextField("insert_id");
    private TextField modify_date = new MTextField("modify_date");
    private TextField modify_id = new MTextField("modify_id");
    private TextField dely_gb = new MTextField("dely_gb");
    
    @Override
    protected Component createContent() {
        return new MVerticalLayout(
                new FormLayout(
                        entp_code,
                        entp_name,
                        entp_gubun,
                        s_idno,
                        work_type,
                        work_kind,
                        entp_post,
                        entp_post_seq,
                        entp_addr,
                        entp_ddd,
                        entp_tel1,
                        entp_tel2,
                        entp_tel3,
                        entp_fax1,
                        entp_fax2,
                        entp_fax3,
                        email_addr,
                        owner_name,
                        dishonor_yn,
                        secret_no,
                        first_date,
                        close_date,
                        close_reason,
                        etc,
                        insert_date,
                        insert_id,
                        modify_date,
                        modify_id,
                        dely_gb
                ),
                getToolbar()
        );
    }
    
}
