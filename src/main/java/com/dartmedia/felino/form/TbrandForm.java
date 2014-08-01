package com.dartmedia.felino.form;

import com.cware.back.entity.table.Tbrand;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import org.vaadin.maddon.fields.MTextField;
import org.vaadin.maddon.form.AbstractForm;
import org.vaadin.maddon.layouts.MVerticalLayout;

public class TbrandForm extends AbstractForm<Tbrand> {
    
    private TextField brand_code = new MTextField("brand_code");
    private TextField brand_name = new MTextField("brand_name");
    private TextField brand_image = new MTextField("brand_image");
    private TextField brand_desc = new MTextField("brand_desc");
    private TextField url = new MTextField("url");
 //   private TextField insert_date = new MTextField("insert_date");
 //   private TextField insert_id = new MTextField("insert_id");
  //  private TextField modify_date = new MTextField("modify_date");
  //  private TextField modify_id = new MTextField("modify_id");
    
    @Override
    protected Component createContent() {
        return new MVerticalLayout(
                new FormLayout(
                        brand_code,
                        brand_name,
                        brand_image,
                        brand_desc,
                        url
                       // insert_date,
                       // insert_id,
                       // modify_date,
                      //  modify_id
                ),
                getToolbar()
        );
    }
    
}
