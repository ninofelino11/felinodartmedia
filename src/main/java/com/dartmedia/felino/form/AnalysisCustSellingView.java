package com.dartmedia.felino.form;
import com.dartmedia.felino.gSqlContainer;
import com.dartmedia.felino.fgetsql;
import com.dartmedia.felino.gSqlContainer;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import org.vaadin.maddon.fields.MTable;
import com.vaadin.event.LayoutEvents;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TreeTable;
import com.vaadin.navigator.View;
import com.vaadin.ui.RichTextArea;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.sql.SQLException;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.fields.MValueChangeEvent;
import org.vaadin.maddon.fields.MValueChangeListener;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("AnalysisCustSelling")
public class AnalysisCustSellingView extends MVerticalLayout implements View {
//AnalysisCustSellingSvc data=new AnalysisCustSellingSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Term <p:calendar /> ~ <p:calendar />Ranking <p:inputText />Amount <p:selectOneListbox>                          <f:selectItem itemlabel="VAT Included"/>                          <f:selectItem itemlabel="VAT Excluded"/>                          <f:selectItem itemlabel="Margin"/>                 </p:selectOneListbox>Ad.Type <p:selectOneListbox>                          <f:selectItem itemlabel="All"/>                          <f:selectItem itemlabel="TV"/>                 </p:selectOneListbox>Ad.Media <p:inputText /><p:inputText />Order Media <p:selectOneListbox>                          <f:selectItem itemlabel="All"/>                          <f:selectItem itemlabel="CALL"/>                          <f:selectItem itemlabel="INTERNET"/>                 </p:selectOneListbox>Inquiry Type <p:selectOneListbox>                          <f:selectItem itemlabel="Customer Code/Name"/>                          <f:selectItem itemlabel="Customer+Item"/>                 </p:selectOneListbox>**/
StringBuffer sb = new StringBuffer();
StringBuffer sb2 = new StringBuffer();
StringBuffer sb3 = new StringBuffer();
sb.append("   SELECT /* customer.xml : custcenter.order.selectAnalysisCustSelling1 by AnalysisCustSelling */");
sb.append("                   AA.CUST_NO            AS CUST_NO,                                         ");
sb.append("                 AA.CUST_NAME          AS CUST_NAME,                                       ");
sb.append("                 AA.RANKGRP            AS RANKGPR,                                         ");
sb.append("                 AA.RANK               AS RANK,                                              ");
sb.append("                 SUM(AA.NET_ORDER_QTY) AS NET_ORDER_QTY,                                      ");
sb.append("                 SUM(AA.NET_ORDER_AMT) AS NET_ORDER_AMT,                                      ");
sb.append("                 SUM(AA.ORDER_QTY)     AS ORDER_QTY,                                       ");
sb.append("                 SUM(AA.ORDER_AMT)     AS ORDER_AMT,                                          ");
sb.append("                 SUM(AA.CANCEL_QTY)    AS CANCEL_QTY,                                      ");
sb.append("                 SUM(AA.CANCEL_AMT)    AS CANCEL_AMT,                                      ");
sb.append("                 SUM(AA.CLAIM_QTY)     AS CLAIM_QTY,                                          ");
sb.append("                 SUM(AA.CLAIM_AMT)     AS CLAIM_AMT,                                          ");
sb.append("                 SUM(AA.CLAIM_CAN_QTY) AS CLAIM_CAN_QTY,                                      ");
sb.append("                 SUM(AA.CLAIM_CAN_AMT) AS CLAIM_CAN_AMT,                                      ");
sb.append("                 SUM(AA.EXCH_QTY)      AS EXCH_QTY,                                          ");
sb.append("                 SUM(AA.EXCH_AMT)      AS EXCH_AMT,                                        ");
sb.append("                 SUM(AA.SALE_QTY)      AS SALE_QTY,                                        ");
sb.append("                 SUM(AA.SALE_AMT)      AS SALE_AMT,                                         ");
sb.append("                 SUM(AA.ORDER_OUT_QTY) AS ORDER_OUT_QTY,                                    ");
sb.append("                 SUM(AA.ORDER_OUT_AMT) AS ORDER_OUT_AMT,                                   ");
sb.append("                 SUM(AA.RETURN_QTY)    AS RETURN_QTY,                                       ");
sb.append("                 SUM(AA.RETURN_AMT)    AS RETURN_AMT,                                       ");
sb.append("                 SUM(AA.EXCH_OUT_QTY)  AS EXCH_OUT_QTY,                                      ");
sb.append("                 SUM(AA.EXCH_OUT_AMT)  AS EXCH_OUT_AMT,                                      ");
sb.append("                 SUM(AA.EXCH_IN_QTY)   AS EXCH_IN_QTY,                                     ");
sb.append("                 SUM(AA.EXCH_IN_AMT)   AS EXCH_IN_AMT                                      ");
sb.append("            FROM ( SELECT DECODE(SIGN(#{rank, jdbcType=VARCHAR} - RANK ), -1, '99','00')            AS RANKGRP,   ");
sb.append("                          DECODE(SIGN(#{rank, jdbcType=VARCHAR} - RANK ), -1, '1000',BB.RANK) * 1   AS RANK,      ");
sb.append("                          DECODE(SIGN(#{rank, jdbcType=VARCHAR} - RANK ), -1, 'other',BB.CUST_NO)   AS CUST_NO,   ");
sb.append("                          DECODE(SIGN(#{rank, jdbcType=VARCHAR} - RANK ), -1, 'other',BB.CUST_NAME) AS CUST_NAME, ");
sb.append("                          BB.NET_ORDER_QTY,                                                  ");
sb.append("                          BB.NET_ORDER_AMT,                                                  ");
sb.append("                          BB.ORDER_QTY,                                                       ");
sb.append("                          BB.ORDER_AMT,                                                      ");
sb.append("                          BB.CANCEL_QTY,                                                      ");
sb.append("                          BB.CANCEL_AMT,                                                      ");
sb.append("                          BB.CLAIM_QTY,                                                      ");
sb.append("                          BB.CLAIM_AMT,                                                      ");
sb.append("                          BB.CLAIM_CAN_QTY,                                                  ");
sb.append("                          BB.CLAIM_CAN_AMT,                                                  ");
sb.append("                          BB.EXCH_QTY,                                                      ");
sb.append("                          BB.EXCH_AMT,                                                      ");
sb.append("                          BB.SALE_QTY,                                                         ");
sb.append("                          BB.SALE_AMT,                                                         ");
sb.append("                          BB.ORDER_OUT_QTY,                                                 ");
sb.append("                          BB.ORDER_OUT_AMT,                                                 ");
sb.append("                          BB.RETURN_QTY,                                                     ");
sb.append("                          BB.RETURN_AMT,                                                      ");
sb.append("                          BB.EXCH_OUT_QTY,                                                  ");
sb.append("                          BB.EXCH_OUT_AMT,                                                     ");
sb.append("                          BB.EXCH_IN_QTY,                                                    ");
sb.append("                          BB.EXCH_IN_AMT                                                       ");
sb.append("                    FROM (                                                                    ");
sb.append("                           SELECT RANK() OVER (ORDER BY SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.ORDER_AMT - A.CANCEL_AMT - A.CLAIM_AMT  + A.CLAIM_CAN_AMT,                    ");
sb.append("                                                                   '2',A.ORDER_NET - A.CANCEL_NET - A.CLAIM_AMT  + A.CLAIM_CAN_NET,                   ");
sb.append("                                                                   '3',A.ORDER_FEE - A.CANCEL_FEE - A.CANCEL_FEE + A.CLAIM_CAN_FEE)) DESC) AS RANK,  ");
sb.append("                                B.CUST_NO   AS CUST_NO,                                    ");
sb.append("                                B.CUST_NAME AS CUST_NAME,                                   ");
sb.append("                                   SUM(A.ORDER_QTY - A.CANCEL_QTY - A.CLAIM_QTY + A.CLAIM_CAN_QTY)             AS NET_ORDER_QTY, /* ????? */        ");
sb.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.ORDER_AMT - A.CANCEL_AMT - A.CLAIM_AMT  + A.CLAIM_CAN_AMT,                                          ");
sb.append("                                             '2',A.ORDER_NET - A.CANCEL_NET - A.CLAIM_AMT  + A.CLAIM_CAN_NET,                                             ");
sb.append("                                             '3',A.ORDER_FEE - A.CANCEL_FEE - A.CANCEL_FEE + A.CLAIM_CAN_FEE)) AS NET_ORDER_AMT, /* ????? */        ");
sb.append("                                SUM(A.ORDER_QTY)                     AS ORDER_QTY,                  /* ???? */                                            ");
sb.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.ORDER_AMT,                                                                                          ");
sb.append("                                             '2',A.ORDER_NET,                                                                                           ");
sb.append("                                             '3',A.ORDER_FEE))       AS ORDER_AMT,           ");
sb.append("                               SUM(A.CANCEL_QTY)                    AS CANCEL_QTY,                  ");
sb.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.CANCEL_AMT,                                                                                          ");
sb.append("                                             '2',A.CANCEL_NET,                                                                                       ");
sb.append("                                             '3',A.CANCEL_FEE))      AS CANCEL_AMT,                              ");
sb.append("                                SUM(A.CLAIM_QTY)                     AS CLAIM_QTY,                            ");
sb.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.CLAIM_AMT,                                                                                          ");
sb.append("                                             '2',A.CLAIM_NET,                                                                                           ");
sb.append("                                             '3',A.CLAIM_FEE))       AS CLAIM_AMT,                             ");
sb.append("                                SUM(A.CLAIM_CAN_QTY)                 AS CLAIM_CAN_QTY,                       ");
sb.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.CLAIM_CAN_AMT,                                                                                      ");
sb.append("                                             '2',A.CLAIM_CAN_NET,                                                                                       ");
sb.append("                                             '3',A.CLAIM_CAN_FEE))     AS CLAIM_CAN_AMT,                        ");
sb.append("                                SUM(A.EXCH_QTY)                      AS EXCH_QTY,                                   ");
sb.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.EXCH_AMT,                                                                                          ");
sb.append("                                             '2',A.EXCH_NET,                                                                                           ");
sb.append("                                             '3',A.EXCH_FEE))        AS EXCH_AMT,                                 ");
sb.append("                                SUM(A.ORDER_OUT_QTY - A.RETURN_QTY)  AS SALE_QTY,                                     ");
sb.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',ORDER_OUT_AMT - A.RETURN_AMT,                                                                          ");
sb.append("                                             '2',ORDER_OUT_NET - A.RETURN_NET,                                                                       ");
sb.append("                                             '3',ORDER_OUT_FEE - A.RETURN_FEE)) AS SALE_AMT,                        ");
sb.append("                                SUM(A.ORDER_OUT_QTY)                 AS ORDER_OUT_QTY,                            ");
sb.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.ORDER_OUT_AMT,                                                                                    ");
sb.append("                                             '2',A.ORDER_OUT_NET,                                                                                    ");
sb.append("                                             '3',A.ORDER_OUT_FEE))   AS ORDER_OUT_AMT,                           ");
sb.append("                                SUM(A.RETURN_QTY)                    AS RETURN_QTY,                     ");
sb.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.RETURN_AMT,                                                                                        ");
sb.append("                                             '2',A.RETURN_NET,                                                                                        ");
sb.append("                                             '3',A.RETURN_FEE))      AS RETURN_AMT,         ");
sb.append("                                SUM(A.EXCH_OUT_QTY)                  AS EXCH_OUT_QTY,     ");
sb.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.EXCH_OUT_AMT,                                                                                        ");
sb.append("                                             '2',A.EXCH_OUT_NET,                                                                                        ");
sb.append("                                             '3',A.EXCH_OUT_FEE))    AS EXCH_OUT_AMT,    ");
sb.append("                                SUM(A.EXCH_IN_QTY)                   AS EXCH_IN_QTY,    ");
sb.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.EXCH_IN_AMT,                                                                                        ");
sb.append("                                             '2',A.EXCH_IN_NET,                                                                                        ");
sb.append("                                             '3',A.EXCH_IN_FEE))     AS EXCH_IN_AMT       ");
sb.append("                             FROM VSDCUSTGOODSSALE A,                                                                                                   ");
sb.append("                                  TCUSTOMER        B                                                                                                    ");
sb.append("                            WHERE A.CUST_NO = B.CUST_NO                                                                                                 ");
sb.append("                              AND A.GATHER_DATE BETWEEN TO_DATE(#{from_date, jdbcType=VARCHAR},'YYYY/MM/DD')                                                                         ");
sb.append("                                                 AND TO_DATE(#{to_date, jdbcType=VARCHAR},'YYYY/MM/DD')                                                                         ");
sb.append("                              AND A.MEDIA_GB       LIKE #{media_gb, jdbcType=VARCHAR}    || '%'                                                                                            ");
sb.append("                              AND A.MEDIA_CODE     LIKE #{media_code, jdbcType=VARCHAR}  || '%'                                                                                            ");
sb.append("                              AND A.ORDER_MEDIA    LIKE #{order_media, jdbcType=VARCHAR} || '%'                                                                                              ");
sb.append("                              AND A.ORDER_QTY + A.CANCEL_QTY + A.CLAIM_QTY + A.EXCH_QTY + A.CLAIM_CAN_QTY + A.ORDER_OUT_QTY                             ");
sb.append("                                + A.RETURN_QTY + A.EXCH_OUT_QTY + A.EXCH_IN_QTY > 0                                                                    ");
sb.append("                            GROUP BY B.CUST_NO,B.CUST_NAME                                                                                                 ");
sb.append("                           ) BB                                                                                                                          ");
sb.append("                       ) AA                                                                                                                              ");
sb.append("           GROUP BY AA.RANKGRP,AA.RANK,AA.CUST_NO,AA.CUST_NAME                                                                                        ");
sb.append("           ORDER BY AA.RANKGRP,AA.RANK,AA.CUST_NO ");
sb.append("         ");


sb2.append("SELECT /* customer.xml : custcenter.order.selectAnalysisCustSelling2 by AnalysisCustSelling */");
sb2.append("                    AA.CUST_NO            AS CUST_NO,                                         ");
sb2.append("                 AA.CUST_NAME          AS CUST_NAME,                                       ");
//sb.append("                 AA.GOODS_CODE         AS GOODS_CODE,                                      ");
sb2.append("                 AA.GOODS_NAME         AS GOODS_NAME,                                      ");
sb2.append("                 AA.RANKGRP            AS RANKGPR,                                         ");
sb2.append("                 AA.RANK               AS RANK,                                              ");
sb2.append("                 SUM(AA.NET_ORDER_QTY) AS NET_ORDER_QTY,                                      ");
sb2.append("                 SUM(AA.NET_ORDER_AMT) AS NET_ORDER_AMT,                                      ");
sb2.append("                 SUM(AA.ORDER_QTY)     AS ORDER_QTY,                                       ");
sb2.append("                 SUM(AA.ORDER_AMT)     AS ORDER_AMT,                                          ");
sb2.append("                 SUM(AA.CANCEL_QTY)    AS CANCEL_QTY,                                      ");
sb2.append("                 SUM(AA.CANCEL_AMT)    AS CANCEL_AMT,                                      ");
sb2.append("                 SUM(AA.CLAIM_QTY)     AS CLAIM_QTY,                                          ");
sb2.append("                 SUM(AA.CLAIM_AMT)     AS CLAIM_AMT,                                          ");
sb2.append("                 SUM(AA.CLAIM_CAN_QTY) AS CLAIM_CAN_QTY,                                      ");
sb2.append("                 SUM(AA.CLAIM_CAN_AMT) AS CLAIM_CAN_AMT,                                      ");
sb2.append("                 SUM(AA.EXCH_QTY)      AS EXCH_QTY,                                          ");
sb2.append("                 SUM(AA.EXCH_AMT)      AS EXCH_AMT,                                        ");
sb2.append("                 SUM(AA.SALE_QTY)      AS SALE_QTY,                                        ");
sb2.append("                 SUM(AA.SALE_AMT)      AS SALE_AMT,                                         ");
sb2.append("                 SUM(AA.ORDER_OUT_QTY) AS ORDER_OUT_QTY,                                    ");
sb2.append("                 SUM(AA.ORDER_OUT_AMT) AS ORDER_OUT_AMT,                                   ");
sb2.append("                 SUM(AA.RETURN_QTY)    AS RETURN_QTY,                                       ");
sb2.append("                 SUM(AA.RETURN_AMT)    AS RETURN_AMT,                                       ");
sb2.append("                 SUM(AA.EXCH_OUT_QTY)  AS EXCH_OUT_QTY,                                      ");
sb2.append("                 SUM(AA.EXCH_OUT_AMT)  AS EXCH_OUT_AMT,                                      ");
sb2.append("                 SUM(AA.EXCH_IN_QTY)   AS EXCH_IN_QTY,                                     ");
sb2.append("                 SUM(AA.EXCH_IN_AMT)   AS EXCH_IN_AMT                                      ");
sb2.append("            FROM ( SELECT DECODE(SIGN(#{rank, jdbcType=VARCHAR} - RANK ), -1, '99','00')             AS RANKGRP,    ");
sb2.append("                          DECODE(SIGN(#{rank, jdbcType=VARCHAR} - RANK ), -1, '1000',BB.RANK) * 1    AS RANK,       ");
sb2.append("                          DECODE(SIGN(#{rank, jdbcType=VARCHAR} - RANK ), -1, 'other',BB.CUST_NO)    AS CUST_NO,    ");
sb2.append("                          DECODE(SIGN(#{rank, jdbcType=VARCHAR} - RANK ), -1, 'other',BB.CUST_NAME)  AS CUST_NAME,  ");
sb2.append("                          DECODE(SIGN(#{rank, jdbcType=VARCHAR} - RANK ), -1, 'other',BB.GOODS_CODE) AS GOODS_CODE, ");
sb2.append("                          DECODE(SIGN(#{rank, jdbcType=VARCHAR} - RANK ), -1, 'other',BB.GOODS_NAME) AS GOODS_NAME, ");
sb2.append("                          BB.NET_ORDER_QTY,                                                  ");
sb2.append("                          BB.NET_ORDER_AMT,                                                  ");
sb2.append("                          BB.ORDER_QTY,                                                       ");
sb2.append("                          BB.ORDER_AMT,                                                      ");
sb2.append("                          BB.CANCEL_QTY,                                                      ");
sb2.append("                          BB.CANCEL_AMT,                                                      ");
sb2.append("                          BB.CLAIM_QTY,                                                      ");
sb2.append("                          BB.CLAIM_AMT,                                                      ");
sb2.append("                          BB.CLAIM_CAN_QTY,                                                  ");
sb2.append("                          BB.CLAIM_CAN_AMT,                                                  ");
sb2.append("                          BB.EXCH_QTY,                                                      ");
sb2.append("                          BB.EXCH_AMT,                                                      ");
sb2.append("                          BB.SALE_QTY,                                                         ");
sb2.append("                          BB.SALE_AMT,                                                         ");
sb2.append("                          BB.ORDER_OUT_QTY,                                                 ");
sb2.append("                          BB.ORDER_OUT_AMT,                                                 ");
sb2.append("                          BB.RETURN_QTY,                                                     ");
sb2.append("                          BB.RETURN_AMT,                                                      ");
sb2.append("                          BB.EXCH_OUT_QTY,                                                  ");
sb2.append("                          BB.EXCH_OUT_AMT,                                                     ");
sb2.append("                          BB.EXCH_IN_QTY,                                                    ");
sb2.append("                          BB.EXCH_IN_AMT                                                       ");
sb2.append("                    FROM (                                                                    ");
sb2.append("                           SELECT RANK() OVER (ORDER BY SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.ORDER_AMT - A.CANCEL_AMT - A.CLAIM_AMT  + A.CLAIM_CAN_AMT,                    ");
sb2.append("                                                                   '2',A.ORDER_NET - A.CANCEL_NET - A.CLAIM_AMT  + A.CLAIM_CAN_NET,                   ");
sb2.append("                                                                   '3',A.ORDER_FEE - A.CANCEL_FEE - A.CANCEL_FEE + A.CLAIM_CAN_FEE)) DESC) AS RANK,  ");
sb2.append("                                B.CUST_NO    AS CUST_NO,                                   ");
sb2.append("                                B.CUST_NAME  AS CUST_NAME,                                   ");
sb2.append("                                C.GOODS_CODE AS GOODS_CODE,                                ");
sb2.append("                                C.GOODS_NAME AS GOODS_NAME,                                   ");
sb2.append("                                SUM(A.ORDER_QTY - A.CANCEL_QTY - A.CLAIM_QTY + A.CLAIM_CAN_QTY)                AS NET_ORDER_QTY,  /* ????? */        ");
sb2.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.ORDER_AMT - A.CANCEL_AMT - A.CLAIM_AMT  + A.CLAIM_CAN_AMT,                                          ");
sb2.append("                                             '2',A.ORDER_NET - A.CANCEL_NET - A.CLAIM_AMT  + A.CLAIM_CAN_NET,                                             ");
sb2.append("                                             '3',A.ORDER_FEE - A.CANCEL_FEE - A.CANCEL_FEE + A.CLAIM_CAN_FEE)) AS NET_ORDER_AMT,  /* ????? */        ");
sb2.append("                                SUM(A.ORDER_QTY)                     AS ORDER_QTY,                                    ");
sb2.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.ORDER_AMT,                                                                                          ");
sb2.append("                                             '2',A.ORDER_NET,                                                                                           ");
sb2.append("                                             '3',A.ORDER_FEE))       AS ORDER_AMT,                                ");
sb2.append("                                SUM(A.CANCEL_QTY)                    AS CANCEL_QTY,                   ");
sb2.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.CANCEL_AMT,                                                                                          ");
sb2.append("                                             '2',A.CANCEL_NET,                                                                                       ");
sb2.append("                                             '3',A.CANCEL_FEE))      AS CANCEL_AMT,                            ");
sb2.append("                                SUM(A.CLAIM_QTY)                     AS CLAIM_QTY,                    ");
sb2.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.CLAIM_AMT,                                                                                          ");
sb2.append("                                             '2',A.CLAIM_NET,                                                                                           ");
sb2.append("                                             '3',A.CLAIM_FEE))       AS CLAIM_AMT,                              ");
sb2.append("                                SUM(A.CLAIM_CAN_QTY)                 AS CLAIM_CAN_QTY,                      ");
sb2.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.CLAIM_CAN_AMT,                                                                                      ");
sb2.append("                                             '2',A.CLAIM_CAN_NET,                                                                                       ");
sb2.append("                                             '3',A.CLAIM_CAN_FEE))     AS CLAIM_CAN_AMT,                   ");
sb2.append("                                SUM(A.EXCH_QTY)                      AS EXCH_QTY,                        ");
sb2.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.EXCH_AMT,                                                                                          ");
sb2.append("                                             '2',A.EXCH_NET,                                                                                           ");
sb2.append("                                             '3',A.EXCH_FEE))        AS EXCH_AMT,                       ");
sb2.append("                                SUM(A.ORDER_OUT_QTY - A.RETURN_QTY)  AS SALE_QTY,                        ");
sb2.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',ORDER_OUT_AMT - A.RETURN_AMT,                                                                          ");
sb2.append("                                             '2',ORDER_OUT_NET - A.RETURN_NET,                                                                       ");
sb2.append("                                             '3',ORDER_OUT_FEE - A.RETURN_FEE)) AS SALE_AMT,                 ");
sb2.append("                                SUM(A.ORDER_OUT_QTY)                 AS ORDER_OUT_QTY,                     ");
sb2.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.ORDER_OUT_AMT,                                                                                    ");
sb2.append("                                             '2',A.ORDER_OUT_NET,                                                                                    ");
sb2.append("                                             '3',A.ORDER_OUT_FEE))   AS ORDER_OUT_AMT,                 ");
sb2.append("                                SUM(A.RETURN_QTY)                    AS RETURN_QTY,               ");
sb2.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.RETURN_AMT,                                                                                        ");
sb2.append("                                             '2',A.RETURN_NET,                                                                                        ");
sb2.append("                                             '3',A.RETURN_FEE))      AS RETURN_AMT,               ");
sb2.append("                                SUM(A.EXCH_OUT_QTY)                  AS EXCH_OUT_QTY,                ");
sb2.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.EXCH_OUT_AMT,                                                                                        ");
sb2.append("                                             '2',A.EXCH_OUT_NET,                                                                                        ");
sb2.append("                                             '3',A.EXCH_OUT_FEE))    AS EXCH_OUT_AMT,          ");
sb2.append("                                SUM(A.EXCH_IN_QTY)                   AS EXCH_IN_QTY,             ");
sb2.append("                                SUM(DECODE(#{amt_flag, jdbcType=VARCHAR},'1',A.EXCH_IN_AMT,                                                                                        ");
sb2.append("                                             '2',A.EXCH_IN_NET,                                                                                        ");
sb2.append("                                             '3',A.EXCH_IN_FEE))     AS EXCH_IN_AMT           ");
sb2.append("                             FROM VSDCUSTGOODSSALE A,                                                                                                   ");
sb2.append("                                  TCUSTOMER        B,                                                                                                    ");
sb2.append("                                  TGOODS           C                                                                                                    ");
sb2.append("                            WHERE A.CUST_NO     = B.CUST_NO                                                                                             ");
sb2.append("                              AND A.GOODS_CODE = C.GOODS_CODE                                                                                             ");
sb2.append("                              AND A.GATHER_DATE BETWEEN TO_DATE(#{from_date, jdbcType=VARCHAR},'YYYY/MM/DD')                                                                         ");
sb2.append("                                                 AND TO_DATE(#{to_date,   jdbcType=VARCHAR},'YYYY/MM/DD')                                                                         ");
sb2.append("                              AND A.MEDIA_GB       LIKE #{media_gb,    jdbcType=VARCHAR} || '%'                                                                                            ");
sb2.append("                              AND A.MEDIA_CODE     LIKE #{media_code,  jdbcType=VARCHAR} || '%'                                                                                            ");
sb2.append("                              AND A.ORDER_MEDIA    LIKE #{order_media, jdbcType=VARCHAR} || '%'                                                                                              ");
sb2.append("                              AND A.ORDER_QTY + A.CANCEL_QTY + A.CLAIM_QTY + A.EXCH_QTY + A.CLAIM_CAN_QTY + A.ORDER_OUT_QTY                             ");
sb2.append("                                + A.RETURN_QTY + A.EXCH_OUT_QTY + A.EXCH_IN_QTY > 0                                                                    ");
sb2.append("                            GROUP BY B.CUST_NO,B.CUST_NAME,C.GOODS_CODE,C.GOODS_NAME                                                                      ");
sb2.append("                           ) BB                                                                                                                          ");
sb2.append("                       ) AA                                                                                                                              ");
sb2.append("           GROUP BY AA.RANKGRP,AA.RANK,AA.CUST_NO,AA.CUST_NAME,AA.GOODS_CODE,AA.GOODS_NAME                                                            ");
sb2.append("           ORDER BY AA.RANKGRP,AA.RANK,AA.CUST_NO ");

//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Term"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new TextField("Rangking"));
toolbar.addComponent(new ComboBox("Amount"));
toolbar.addComponent(new ComboBox("Ad.type"));
toolbar.addComponent(new TextField("Ad. Media"));

toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new ComboBox("Ad.type"));

toolbar.addComponent(new CheckBox("Indv.Query"));
toolbar.addComponent(new CheckBox("Indv.Query"));
toolbar.addComponent(new CheckBox("Indv.Query"));


//-------------------- Header  ------------------------------
MHorizontalLayout toolmenu;
toolmenu = new MHorizontalLayout();
toolmenu.addComponent(new Button("Ret"));
toolmenu.addComponent(new Button("Ins"));
toolmenu.addComponent(new Button("Del"));
toolmenu.addComponent(new Button("Save"));
toolmenu.addComponent(new Button("Print"));
toolmenu.addComponent(new Button("XLS"));
        addComponents(
                new Header("Customer Sales Report (AnalysisCustSelling)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
//        addComponents(isicontents);
//MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
//List<Tenterprise> findAll = cf.findAll();
//MTable<Tenterprise> table=new MTable<Tenterprise>(Tenterprise.class).withProperties("entpName");
//table.setBeans(findAll);
//table.addMValueChangeListener(new MValueChangeListener<Tdescribecode>() {
//    @Override
//    public void valueChange(MValueChangeEvent<Tdescribecode> event) {
//    Notification.show("ss");
//    form.setEntity(event.getValue());
//    }
//    });
//table.addContainerProperty("No", String.class,  null);
//-------------------- Header Table ------------------------------
//   isicontents.addComponents(table);
try{
            SimpleJDBCConnectionPool connectionPool = new SimpleJDBCConnectionPool(
             "oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@localhost:1521/XE",
             "dartmedia", "dartmedia",2,5);
             SQLContainer container;
              container = new SQLContainer(new FreeformQuery(
              sb.toString(),connectionPool,"AD_MENU_ID"));
             // MTable table= new MTable("MENU",container);
               TreeTable table = new TreeTable("Menu", container);
              addComponents(table);
 } catch (SQLException e) {
     e.printStackTrace();
     RichTextArea rtarea = new RichTextArea();
     rtarea.setValue(sb.toString());
      addComponents(rtarea);
}
        addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent event) {
            }
        });
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
