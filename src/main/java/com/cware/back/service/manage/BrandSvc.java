
package com.cware.back.service.manage;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;



public class BrandSvc {




    public BrandSvc() {}


    private String chkBrandCode(){

        StringBuilder sb;
        sb = new StringBuilder();

        sb.append(" SELECT COUNT(A.BRAND_CODE)   	  \n ");
        sb.append("   FROM TBRAND A \n ");
        sb.append("  WHERE A.BRAND_CODE = ?      \n ");

       
        return sb.toString();
    }   

    public String makeSql() {

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT BRAND_CODE,  \n");
        sb.append("         BRAND_NAME,  \n");
        sb.append("         BRAND_IMAGE,  \n");
        sb.append("         BRAND_DESC,  \n");
        sb.append("         URL,  \n");
        sb.append("         INSERT_ID,  \n");
        sb.append("         TO_CHAR(INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') INSERT_DATE, \n");
        sb.append("         MODIFY_ID,  \n");
        sb.append("         TO_CHAR(MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') MODIFY_DATE, \n");
        sb.append("         '' AS IMAGE,  \n");
        sb.append("         '' AS IMAGE_SHOP  \n");
        sb.append("    FROM TBRAND  \n");
        sb.append("   WHERE BRAND_CODE > '0000'  \n");
        sb.append("     AND BRAND_CODE < '9999'  \n");
      //  sb.append("     AND BRAND_CODE LIKE ?  \n");
      //  sb.append("     AND BRAND_NAME LIKE ?  \n");
        
        //= log SQL -------------------------------------------------
      
        return sb.toString();
    }


    public String makeSqlInsert() {

        StringBuffer sb = new StringBuffer();

        sb.append("  INSERT INTO TBRAND(  \n ");
        sb.append("         BRAND_CODE,  \n ");
        sb.append("         BRAND_NAME,  \n ");
        sb.append("         BRAND_IMAGE,  \n ");
        sb.append("         BRAND_DESC,  \n ");
        sb.append("         URL,  \n ");
        sb.append("         INSERT_DATE,  \n ");
        sb.append("         INSERT_ID,  \n ");
        sb.append("         MODIFY_DATE,  \n ");
        sb.append("         MODIFY_ID  )  \n ");
        sb.append("  VALUES( \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         ?, \n ");
        sb.append("         TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         ? ) \n ");

        //= log SQL -------------------------------------------------
      
        return sb.toString();
    }

    public String makeSqlUpdate() {

        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TBRAND SET \n ");
        sb.append("         BRAND_NAME    = ?, \n ");
        sb.append("         BRAND_IMAGE   = ?, \n ");
        sb.append("         BRAND_DESC    = ?, \n ");
        sb.append("         URL           = ?, \n ");
        sb.append("         MODIFY_DATE   = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         MODIFY_ID     = ? \n ");       
        sb.append("   WHERE BRAND_CODE    = ? \n ");

        //= log SQL -------------------------------------------------
      
        return sb.toString();
    }

  
    public HashMap[] makeSheet(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        String       tempString = "";
        long         retRow     = 0;
        return null;

        
        }
       
    }

   
  
  

