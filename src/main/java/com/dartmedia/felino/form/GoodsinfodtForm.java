package com.dartmedia.felino.form;

import com.cware.back.entity.table.Goodsinfodt;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import org.vaadin.maddon.fields.MTextField;
import org.vaadin.maddon.form.AbstractForm;
import org.vaadin.maddon.layouts.MVerticalLayout;

public class GoodsinfodtForm extends AbstractForm<Goodsinfodt> {
    
    private TextField cspf_group = new MTextField("cspf_group");
    private TextField cspf_code = new MTextField("cspf_code");
    private TextField cspf_name = new MTextField("cspf_name");
    private TextField use_yn = new MTextField("use_yn");
    private TextField insert_date = new MTextField("insert_date");
    private TextField insert_id = new MTextField("insert_id");
    
    @Override
    protected Component createContent() {
        return new MVerticalLayout(
                new FormLayout(
                        cspf_group,
                        cspf_code,
                        cspf_name,
                        use_yn,
                        insert_date,
                        insert_id
                ),
                getToolbar()
        );
    }
    
}
