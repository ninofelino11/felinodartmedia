/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dartmedia.felino;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ni
 */
@MappedSuperclass
@Table(name = "TBALJUDT")
@XmlRootElement
public class Tbaljudt implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TbaljudtPK tbaljudtPK;
    @Size(max = 12)
    @Column(name = "INQC_NO", length = 12)
    private String inqcNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BALJU_QTY", nullable = false)
    private int baljuQty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENTP_CONF_QTY", nullable = false)
    private int entpConfQty;
    @Size(max = 300)
    @Column(name = "ENTP_CONF_NOTE", length = 300)
    private String entpConfNote;
    @JoinColumn(name = "BALJU_NO", referencedColumnName = "BALJU_NO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tbaljum tbaljum;

    public Tbaljudt() {
    }

    public Tbaljudt(TbaljudtPK tbaljudtPK) {
        this.tbaljudtPK = tbaljudtPK;
    }

    public Tbaljudt(TbaljudtPK tbaljudtPK, int baljuQty, int entpConfQty) {
        this.tbaljudtPK = tbaljudtPK;
        this.baljuQty = baljuQty;
        this.entpConfQty = entpConfQty;
    }

    public Tbaljudt(String baljuNo, String goodsCode, String goodsdtCode) {
        this.tbaljudtPK = new TbaljudtPK(baljuNo, goodsCode, goodsdtCode);
    }

    public TbaljudtPK getTbaljudtPK() {
        return tbaljudtPK;
    }

    public void setTbaljudtPK(TbaljudtPK tbaljudtPK) {
        this.tbaljudtPK = tbaljudtPK;
    }

    public String getInqcNo() {
        return inqcNo;
    }

    public void setInqcNo(String inqcNo) {
        this.inqcNo = inqcNo;
    }

    public int getBaljuQty() {
        return baljuQty;
    }

    public void setBaljuQty(int baljuQty) {
        this.baljuQty = baljuQty;
    }

    public int getEntpConfQty() {
        return entpConfQty;
    }

    public void setEntpConfQty(int entpConfQty) {
        this.entpConfQty = entpConfQty;
    }

    public String getEntpConfNote() {
        return entpConfNote;
    }

    public void setEntpConfNote(String entpConfNote) {
        this.entpConfNote = entpConfNote;
    }

    public Tbaljum getTbaljum() {
        return tbaljum;
    }

    public void setTbaljum(Tbaljum tbaljum) {
        this.tbaljum = tbaljum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tbaljudtPK != null ? tbaljudtPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbaljudt)) {
            return false;
        }
        Tbaljudt other = (Tbaljudt) object;
        if ((this.tbaljudtPK == null && other.tbaljudtPK != null) || (this.tbaljudtPK != null && !this.tbaljudtPK.equals(other.tbaljudtPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tbaljudt[ tbaljudtPK=" + tbaljudtPK + " ]";
    }
    
}
