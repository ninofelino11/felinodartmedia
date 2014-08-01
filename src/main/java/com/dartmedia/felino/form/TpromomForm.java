package com.dartmedia.felino.form;

import com.cware.back.entity.table.Tpromom;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import org.vaadin.maddon.fields.MTextField;
import org.vaadin.maddon.form.AbstractForm;
import org.vaadin.maddon.layouts.MVerticalLayout;

public class TpromomForm extends AbstractForm<Tpromom> {
    
    private TextField promo_no = new MTextField("promo_no");
    private TextField promo_name = new MTextField("promo_name");
    private TextField coupon_yn = new MTextField("coupon_yn");
    private TextField app_type = new MTextField("app_type");
    private TextField do_type = new MTextField("do_type");
    private TextField promo_bdate = new MTextField("promo_bdate");
    private TextField promo_edate = new MTextField("promo_edate");
    private TextField order_media_all_yn = new MTextField("order_media_all_yn");
    private TextField order_media = new MTextField("order_media");
    private TextField media_code_all_yn = new MTextField("media_code_all_yn");
    private TextField media_code = new MTextField("media_code");
    private TextField limit_yn = new MTextField("limit_yn");
    private TextField limit_qty = new MTextField("limit_qty");
    private TextField goods_all_yn = new MTextField("goods_all_yn");
    private TextField gross_net_flag = new MTextField("gross_net_flag");
    private TextField app_amt = new MTextField("app_amt");
    private TextField amt_rate_flag = new MTextField("amt_rate_flag");
    private TextField do_rate = new MTextField("do_rate");
    private TextField do_amt = new MTextField("do_amt");
    private TextField select_yn = new MTextField("select_yn");
    private TextField select_qty = new MTextField("select_qty");
    private TextField coupon_promo_no = new MTextField("coupon_promo_no");
    private TextField lottery_promo_no = new MTextField("lottery_promo_no");
    private TextField use_code = new MTextField("use_code");
    private TextField remark = new MTextField("remark");
    private TextField first_order_yn = new MTextField("first_order_yn");
    private TextField memb_gb_all_yn = new MTextField("memb_gb_all_yn");
    private TextField memb_gb = new MTextField("memb_gb");
    private TextField insert_date = new MTextField("insert_date");
    private TextField insert_id = new MTextField("insert_id");
    private TextField modify_date = new MTextField("modify_date");
    private TextField modify_id = new MTextField("modify_id");
    private TextField promo_bdate_org = new MTextField("promo_bdate_org");
    private TextField coupon_use_fix_yn = new MTextField("coupon_use_fix_yn");
    private TextField valid_days = new MTextField("valid_days");
    private TextField coupon_use_day = new MTextField("coupon_use_day");
    private TextField card_all_yn = new MTextField("card_all_yn");
    private TextField card_code = new MTextField("card_code");
    private TextField card_dc_falg = new MTextField("card_dc_falg");
    private TextField norest_allot_months = new MTextField("norest_allot_months");
    
    @Override
    protected Component createContent() {
        return new MVerticalLayout(
                new FormLayout(
                        promo_no,
                        promo_name,
                        coupon_yn,
                        app_type,
                        do_type,
                        promo_bdate,
                        promo_edate,
                        order_media_all_yn,
                        order_media,
                        media_code_all_yn,
                        media_code,
                        limit_yn,
                        limit_qty,
                        goods_all_yn,
                        gross_net_flag,
                        app_amt,
                        amt_rate_flag,
                        do_rate,
                        do_amt,
                        select_yn,
                        select_qty,
                        coupon_promo_no,
                        lottery_promo_no,
                        use_code,
                        remark,
                        first_order_yn,
                        memb_gb_all_yn,
                        memb_gb,
                        insert_date,
                        insert_id,
                        modify_date,
                        modify_id,
                        promo_bdate_org,
                        coupon_use_fix_yn,
                        valid_days,
                        coupon_use_day,
                        card_all_yn,
                        card_code,
                        card_dc_falg,
                        norest_allot_months
                ),
                getToolbar()
        );
    }
    
}
