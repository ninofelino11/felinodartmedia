package com.cware.back.entity.table;
import com.cware.back.common.BaseEntity;

public class TPromoSegment extends BaseEntity{

	public TPromoSegment(){
		super();
	}

	private String flag;
    private String promo_no;
    private String promo_name;
    private String segment_code;
    private String segment_name;
    private String proc_resulte;
    private String Insert_id;
    private String Modify_id;
    private String out_proc_cnt;
    private String out_rtn;
    private String out_rtn_msg;
    private String proc_date;


    public String getproc_date() {
		return out_proc_cnt;
	}
	public void setproc_date(String proc_date) {
		this.proc_date = proc_date;
	}


    public String getout_proc_cnt() {
		return out_proc_cnt;
	}
	public void setout_proc_cnt(String out_proc_cnt) {
		this.out_proc_cnt = out_proc_cnt;
	}


    public String getout_rtn() {
		return out_rtn;
	}
	public void setout_rtn(String out_rtn) {
		this.out_rtn = out_rtn;
	}


    public String getout_rtn_msg() {
		return flag;
	}
	public void setout_rtn_msg(String out_rtn_msg) {
		this.out_rtn_msg = out_rtn_msg;
	}



    public String getflag() {
		return flag;
	}
	public void setflag(String flag) {
		this.flag = flag;
	}


    public String getpromo_no() {
		return promo_no;
	}
	public void setpromo_no(String promo_no) {
		this.promo_no = promo_no;
	}


    public String getpromo_name() {
		return promo_name;
	}
    public void setpromo_name(String promo_name) {
		this.promo_name = promo_name;
	}


    public String getsegment_code() {
		return segment_code;
	}
	public void setsegment_code(String segment_code) {
		this.segment_code = segment_code;
	}


    public String getsegment_name() {
		return segment_name;
	}
	public void setsegment_name(String segment_name) {
		this.segment_name = segment_name;
	}


    public String getproc_resulte() {
		return proc_resulte;
	}
	public void setproc_resulte(String proc_resulte) {
		this.proc_resulte = proc_resulte;
	}


	public String getInsert_id() {
		return Insert_id;
	}
	public void setInsert_id(String Insert_id) {
		this.Insert_id = Insert_id;
	}


	public String getModify_id() {
		return Modify_id;
	}
	public void setModify_id(String Modify_id) {
		this.Modify_id = Modify_id;
	}

}
