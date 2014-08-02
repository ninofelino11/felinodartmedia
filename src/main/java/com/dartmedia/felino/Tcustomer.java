/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dartmedia.felino;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ni
 */
@Entity
@Table(name = "TCUSTOMER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tcustomer.findAll", query = "SELECT t FROM Tcustomer t"),
    @NamedQuery(name = "Tcustomer.findByCustNo", query = "SELECT t FROM Tcustomer t WHERE t.custNo = :custNo"),
    @NamedQuery(name = "Tcustomer.findByCustName", query = "SELECT t FROM Tcustomer t WHERE t.custName = :custName"),
    @NamedQuery(name = "Tcustomer.findByEname", query = "SELECT t FROM Tcustomer t WHERE t.ename = :ename"),
    @NamedQuery(name = "Tcustomer.findByCustName1", query = "SELECT t FROM Tcustomer t WHERE t.custName1 = :custName1"),
    @NamedQuery(name = "Tcustomer.findByCustName2", query = "SELECT t FROM Tcustomer t WHERE t.custName2 = :custName2"),
    @NamedQuery(name = "Tcustomer.findByCustName3", query = "SELECT t FROM Tcustomer t WHERE t.custName3 = :custName3"),
    @NamedQuery(name = "Tcustomer.findBySex", query = "SELECT t FROM Tcustomer t WHERE t.sex = :sex"),
    @NamedQuery(name = "Tcustomer.findByBirthdayYn", query = "SELECT t FROM Tcustomer t WHERE t.birthdayYn = :birthdayYn"),
    @NamedQuery(name = "Tcustomer.findByBirthday", query = "SELECT t FROM Tcustomer t WHERE t.birthday = :birthday"),
    @NamedQuery(name = "Tcustomer.findByWeddingYn", query = "SELECT t FROM Tcustomer t WHERE t.weddingYn = :weddingYn"),
    @NamedQuery(name = "Tcustomer.findByWeddingDate", query = "SELECT t FROM Tcustomer t WHERE t.weddingDate = :weddingDate"),
    @NamedQuery(name = "Tcustomer.findByEmYn", query = "SELECT t FROM Tcustomer t WHERE t.emYn = :emYn"),
    @NamedQuery(name = "Tcustomer.findByEmNo", query = "SELECT t FROM Tcustomer t WHERE t.emNo = :emNo"),
    @NamedQuery(name = "Tcustomer.findByMembNo", query = "SELECT t FROM Tcustomer t WHERE t.membNo = :membNo"),
    @NamedQuery(name = "Tcustomer.findByMemId", query = "SELECT t FROM Tcustomer t WHERE t.memId = :memId"),
    @NamedQuery(name = "Tcustomer.findByIdInsertDate", query = "SELECT t FROM Tcustomer t WHERE t.idInsertDate = :idInsertDate"),
    @NamedQuery(name = "Tcustomer.findByPasswd", query = "SELECT t FROM Tcustomer t WHERE t.passwd = :passwd"),
    @NamedQuery(name = "Tcustomer.findByPasswdHint", query = "SELECT t FROM Tcustomer t WHERE t.passwdHint = :passwdHint"),
    @NamedQuery(name = "Tcustomer.findByPasswdAnswer", query = "SELECT t FROM Tcustomer t WHERE t.passwdAnswer = :passwdAnswer"),
    @NamedQuery(name = "Tcustomer.findByResidentNo", query = "SELECT t FROM Tcustomer t WHERE t.residentNo = :residentNo"),
    @NamedQuery(name = "Tcustomer.findByJobCode", query = "SELECT t FROM Tcustomer t WHERE t.jobCode = :jobCode"),
    @NamedQuery(name = "Tcustomer.findByCompName", query = "SELECT t FROM Tcustomer t WHERE t.compName = :compName"),
    @NamedQuery(name = "Tcustomer.findByCompDeptname", query = "SELECT t FROM Tcustomer t WHERE t.compDeptname = :compDeptname"),
    @NamedQuery(name = "Tcustomer.findByCountry", query = "SELECT t FROM Tcustomer t WHERE t.country = :country"),
    @NamedQuery(name = "Tcustomer.findByEmailAddr", query = "SELECT t FROM Tcustomer t WHERE t.emailAddr = :emailAddr"),
    @NamedQuery(name = "Tcustomer.findByEmailYn", query = "SELECT t FROM Tcustomer t WHERE t.emailYn = :emailYn"),
    @NamedQuery(name = "Tcustomer.findBySmsYn", query = "SELECT t FROM Tcustomer t WHERE t.smsYn = :smsYn"),
    @NamedQuery(name = "Tcustomer.findByOrderEmailYn", query = "SELECT t FROM Tcustomer t WHERE t.orderEmailYn = :orderEmailYn"),
    @NamedQuery(name = "Tcustomer.findByOrderSmsYn", query = "SELECT t FROM Tcustomer t WHERE t.orderSmsYn = :orderSmsYn"),
    @NamedQuery(name = "Tcustomer.findByReceiveMethod", query = "SELECT t FROM Tcustomer t WHERE t.receiveMethod = :receiveMethod"),
    @NamedQuery(name = "Tcustomer.findByNominateYn", query = "SELECT t FROM Tcustomer t WHERE t.nominateYn = :nominateYn"),
    @NamedQuery(name = "Tcustomer.findByNominateId", query = "SELECT t FROM Tcustomer t WHERE t.nominateId = :nominateId"),
    @NamedQuery(name = "Tcustomer.findByCustSource", query = "SELECT t FROM Tcustomer t WHERE t.custSource = :custSource"),
    @NamedQuery(name = "Tcustomer.findByWithdrawalYn", query = "SELECT t FROM Tcustomer t WHERE t.withdrawalYn = :withdrawalYn"),
    @NamedQuery(name = "Tcustomer.findByWithdrawalCode", query = "SELECT t FROM Tcustomer t WHERE t.withdrawalCode = :withdrawalCode"),
    @NamedQuery(name = "Tcustomer.findByWithdrawalContent", query = "SELECT t FROM Tcustomer t WHERE t.withdrawalContent = :withdrawalContent"),
    @NamedQuery(name = "Tcustomer.findByWithdrawalDate", query = "SELECT t FROM Tcustomer t WHERE t.withdrawalDate = :withdrawalDate"),
    @NamedQuery(name = "Tcustomer.findByEmailFlag", query = "SELECT t FROM Tcustomer t WHERE t.emailFlag = :emailFlag"),
    @NamedQuery(name = "Tcustomer.findByEmailBlockCode", query = "SELECT t FROM Tcustomer t WHERE t.emailBlockCode = :emailBlockCode"),
    @NamedQuery(name = "Tcustomer.findByEmailBlockDate", query = "SELECT t FROM Tcustomer t WHERE t.emailBlockDate = :emailBlockDate"),
    @NamedQuery(name = "Tcustomer.findByRemark1V", query = "SELECT t FROM Tcustomer t WHERE t.remark1V = :remark1V"),
    @NamedQuery(name = "Tcustomer.findByRemark2V", query = "SELECT t FROM Tcustomer t WHERE t.remark2V = :remark2V"),
    @NamedQuery(name = "Tcustomer.findByRemark3V", query = "SELECT t FROM Tcustomer t WHERE t.remark3V = :remark3V"),
    @NamedQuery(name = "Tcustomer.findByInsertId", query = "SELECT t FROM Tcustomer t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Tcustomer.findByInsertDate", query = "SELECT t FROM Tcustomer t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "Tcustomer.findByModifyId", query = "SELECT t FROM Tcustomer t WHERE t.modifyId = :modifyId"),
    @NamedQuery(name = "Tcustomer.findByModifyDate", query = "SELECT t FROM Tcustomer t WHERE t.modifyDate = :modifyDate"),
    @NamedQuery(name = "Tcustomer.findByCustGroup", query = "SELECT t FROM Tcustomer t WHERE t.custGroup = :custGroup")})
public class Tcustomer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "CUST_NO")
    private String custNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "CUST_NAME")
    private String custName;
    @Size(max = 90)
    @Column(name = "ENAME")
    private String ename;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "CUST_NAME1")
    private String custName1;
    @Size(max = 300)
    @Column(name = "CUST_NAME2")
    private String custName2;
    @Size(max = 300)
    @Column(name = "CUST_NAME3")
    private String custName3;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "SEX")
    private String sex;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "BIRTHDAY_YN")
    private String birthdayYn;
    @Size(max = 8)
    @Column(name = "BIRTHDAY")
    private String birthday;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "WEDDING_YN")
    private String weddingYn;
    @Column(name = "WEDDING_DATE")
    @Temporal(TemporalType.DATE)
    private Date weddingDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "EM_YN")
    private String emYn;
    @Size(max = 10)
    @Column(name = "EM_NO")
    private String emNo;
    @Size(max = 10)
    @Column(name = "MEMB_NO")
    private String membNo;
    @Size(max = 300)
    @Column(name = "MEM_ID")
    private String memId;
    @Column(name = "ID_INSERT_DATE")
    @Temporal(TemporalType.DATE)
    private Date idInsertDate;
    @Size(max = 192)
    @Column(name = "PASSWD")
    private String passwd;
    @Size(max = 600)
    @Column(name = "PASSWD_HINT")
    private String passwdHint;
    @Size(max = 600)
    @Column(name = "PASSWD_ANSWER")
    private String passwdAnswer;
    @Size(max = 64)
    @Column(name = "RESIDENT_NO")
    private String residentNo;
    @Size(max = 2)
    @Column(name = "JOB_CODE")
    private String jobCode;
    @Size(max = 300)
    @Column(name = "COMP_NAME")
    private String compName;
    @Size(max = 300)
    @Column(name = "COMP_DEPTNAME")
    private String compDeptname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "COUNTRY")
    private String country;
    @Size(max = 300)
    @Column(name = "EMAIL_ADDR")
    private String emailAddr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "EMAIL_YN")
    private String emailYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "SMS_YN")
    private String smsYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ORDER_EMAIL_YN")
    private String orderEmailYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ORDER_SMS_YN")
    private String orderSmsYn;
    @Size(max = 2)
    @Column(name = "RECEIVE_METHOD")
    private String receiveMethod;
    @Size(max = 1)
    @Column(name = "NOMINATE_YN")
    private String nominateYn;
    @Size(max = 20)
    @Column(name = "NOMINATE_ID")
    private String nominateId;
    @Size(max = 10)
    @Column(name = "CUST_SOURCE")
    private String custSource;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "WITHDRAWAL_YN")
    private String withdrawalYn;
    @Size(max = 2)
    @Column(name = "WITHDRAWAL_CODE")
    private String withdrawalCode;
    @Size(max = 600)
    @Column(name = "WITHDRAWAL_CONTENT")
    private String withdrawalContent;
    @Column(name = "WITHDRAWAL_DATE")
    @Temporal(TemporalType.DATE)
    private Date withdrawalDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "EMAIL_FLAG")
    private String emailFlag;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "EMAIL_BLOCK_CODE")
    private String emailBlockCode;
    @Column(name = "EMAIL_BLOCK_DATE")
    @Temporal(TemporalType.DATE)
    private Date emailBlockDate;
    @Size(max = 900)
    @Column(name = "REMARK1_V")
    private String remark1V;
    @Size(max = 900)
    @Column(name = "REMARK2_V")
    private String remark2V;
    @Size(max = 900)
    @Column(name = "REMARK3_V")
    private String remark3V;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "INSERT_ID")
    private String insertId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INSERT_DATE")
    @Temporal(TemporalType.DATE)
    private Date insertDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "MODIFY_ID")
    private String modifyId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MODIFY_DATE")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;
    @Size(max = 20)
    @Column(name = "CUST_GROUP")
    private String custGroup;

    public Tcustomer() {
    }

    public Tcustomer(String custNo) {
        this.custNo = custNo;
    }

    public Tcustomer(String custNo, String custName, String custName1, String sex, String birthdayYn, String weddingYn, String emYn, String country, String emailYn, String smsYn, String orderEmailYn, String orderSmsYn, String withdrawalYn, String emailFlag, String emailBlockCode, String insertId, Date insertDate, String modifyId, Date modifyDate) {
        this.custNo = custNo;
        this.custName = custName;
        this.custName1 = custName1;
        this.sex = sex;
        this.birthdayYn = birthdayYn;
        this.weddingYn = weddingYn;
        this.emYn = emYn;
        this.country = country;
        this.emailYn = emailYn;
        this.smsYn = smsYn;
        this.orderEmailYn = orderEmailYn;
        this.orderSmsYn = orderSmsYn;
        this.withdrawalYn = withdrawalYn;
        this.emailFlag = emailFlag;
        this.emailBlockCode = emailBlockCode;
        this.insertId = insertId;
        this.insertDate = insertDate;
        this.modifyId = modifyId;
        this.modifyDate = modifyDate;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getCustName1() {
        return custName1;
    }

    public void setCustName1(String custName1) {
        this.custName1 = custName1;
    }

    public String getCustName2() {
        return custName2;
    }

    public void setCustName2(String custName2) {
        this.custName2 = custName2;
    }

    public String getCustName3() {
        return custName3;
    }

    public void setCustName3(String custName3) {
        this.custName3 = custName3;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthdayYn() {
        return birthdayYn;
    }

    public void setBirthdayYn(String birthdayYn) {
        this.birthdayYn = birthdayYn;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWeddingYn() {
        return weddingYn;
    }

    public void setWeddingYn(String weddingYn) {
        this.weddingYn = weddingYn;
    }

    public Date getWeddingDate() {
        return weddingDate;
    }

    public void setWeddingDate(Date weddingDate) {
        this.weddingDate = weddingDate;
    }

    public String getEmYn() {
        return emYn;
    }

    public void setEmYn(String emYn) {
        this.emYn = emYn;
    }

    public String getEmNo() {
        return emNo;
    }

    public void setEmNo(String emNo) {
        this.emNo = emNo;
    }

    public String getMembNo() {
        return membNo;
    }

    public void setMembNo(String membNo) {
        this.membNo = membNo;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public Date getIdInsertDate() {
        return idInsertDate;
    }

    public void setIdInsertDate(Date idInsertDate) {
        this.idInsertDate = idInsertDate;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPasswdHint() {
        return passwdHint;
    }

    public void setPasswdHint(String passwdHint) {
        this.passwdHint = passwdHint;
    }

    public String getPasswdAnswer() {
        return passwdAnswer;
    }

    public void setPasswdAnswer(String passwdAnswer) {
        this.passwdAnswer = passwdAnswer;
    }

    public String getResidentNo() {
        return residentNo;
    }

    public void setResidentNo(String residentNo) {
        this.residentNo = residentNo;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompDeptname() {
        return compDeptname;
    }

    public void setCompDeptname(String compDeptname) {
        this.compDeptname = compDeptname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getEmailYn() {
        return emailYn;
    }

    public void setEmailYn(String emailYn) {
        this.emailYn = emailYn;
    }

    public String getSmsYn() {
        return smsYn;
    }

    public void setSmsYn(String smsYn) {
        this.smsYn = smsYn;
    }

    public String getOrderEmailYn() {
        return orderEmailYn;
    }

    public void setOrderEmailYn(String orderEmailYn) {
        this.orderEmailYn = orderEmailYn;
    }

    public String getOrderSmsYn() {
        return orderSmsYn;
    }

    public void setOrderSmsYn(String orderSmsYn) {
        this.orderSmsYn = orderSmsYn;
    }

    public String getReceiveMethod() {
        return receiveMethod;
    }

    public void setReceiveMethod(String receiveMethod) {
        this.receiveMethod = receiveMethod;
    }

    public String getNominateYn() {
        return nominateYn;
    }

    public void setNominateYn(String nominateYn) {
        this.nominateYn = nominateYn;
    }

    public String getNominateId() {
        return nominateId;
    }

    public void setNominateId(String nominateId) {
        this.nominateId = nominateId;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getWithdrawalYn() {
        return withdrawalYn;
    }

    public void setWithdrawalYn(String withdrawalYn) {
        this.withdrawalYn = withdrawalYn;
    }

    public String getWithdrawalCode() {
        return withdrawalCode;
    }

    public void setWithdrawalCode(String withdrawalCode) {
        this.withdrawalCode = withdrawalCode;
    }

    public String getWithdrawalContent() {
        return withdrawalContent;
    }

    public void setWithdrawalContent(String withdrawalContent) {
        this.withdrawalContent = withdrawalContent;
    }

    public Date getWithdrawalDate() {
        return withdrawalDate;
    }

    public void setWithdrawalDate(Date withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }

    public String getEmailFlag() {
        return emailFlag;
    }

    public void setEmailFlag(String emailFlag) {
        this.emailFlag = emailFlag;
    }

    public String getEmailBlockCode() {
        return emailBlockCode;
    }

    public void setEmailBlockCode(String emailBlockCode) {
        this.emailBlockCode = emailBlockCode;
    }

    public Date getEmailBlockDate() {
        return emailBlockDate;
    }

    public void setEmailBlockDate(Date emailBlockDate) {
        this.emailBlockDate = emailBlockDate;
    }

    public String getRemark1V() {
        return remark1V;
    }

    public void setRemark1V(String remark1V) {
        this.remark1V = remark1V;
    }

    public String getRemark2V() {
        return remark2V;
    }

    public void setRemark2V(String remark2V) {
        this.remark2V = remark2V;
    }

    public String getRemark3V() {
        return remark3V;
    }

    public void setRemark3V(String remark3V) {
        this.remark3V = remark3V;
    }

    public String getInsertId() {
        return insertId;
    }

    public void setInsertId(String insertId) {
        this.insertId = insertId;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public String getModifyId() {
        return modifyId;
    }

    public void setModifyId(String modifyId) {
        this.modifyId = modifyId;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCustGroup() {
        return custGroup;
    }

    public void setCustGroup(String custGroup) {
        this.custGroup = custGroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custNo != null ? custNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tcustomer)) {
            return false;
        }
        Tcustomer other = (Tcustomer) object;
        if ((this.custNo == null && other.custNo != null) || (this.custNo != null && !this.custNo.equals(other.custNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tcustomer[ custNo=" + custNo + " ]";
    }
    
}
