package com.cware.back.service.manage;
public class GoodsKindsSvc {

    public GoodsKindsSvc() {}

 
    public String makeSqlMgroup() {

        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT DISTINCT A.MGROUP , \n");
        sb.append("        A.MGROUP_NAME  \n");
       sb.append("   FROM TGOODSKINDS A  \n");
       // sb.append("  WHERE A.LGROUP = ?   \n");
      sb.append("  ORDER BY A.MGROUP	\n");
       //   sb.append("select * from TGOODSKINDS");
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Sgroup
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlSgroup(){

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT DISTINCT A.MGROUP, \n");
        sb.append("        A.SGROUP,          \n");
        sb.append("        A.SGROUP_NAME      \n");
        sb.append("   FROM TGOODSKINDS A      \n");
       // sb.append("  WHERE A.LGROUP = ?  \n");
       // sb.append("    AND A.MGROUP = ?  \n");
        sb.append("  ORDER BY A.SGROUP	\n");

      
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Dgroup
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlDgroup() {

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.LGROUP,      \n");
        sb.append("        A.LGROUP_NAME, \n");
        sb.append("        A.MGROUP,      \n");
        sb.append("        A.MGROUP_NAME, \n");
        sb.append("        A.SGROUP,      \n");
        sb.append("        A.SGROUP_NAME, \n");
        sb.append("        A.DGROUP,      \n");
        sb.append("        A.DGROUP_NAME, \n");
        sb.append("        A.INSERT_DATE, \n");
        sb.append("        A.INSERT_ID,   \n");
        sb.append("        A.MODIFY_DATE, \n");
        sb.append("        A.MODIFY_ID    \n");
        sb.append("   FROM TGOODSKINDS A  \n");
       // sb.append("  WHERE A.LGROUP = ?   \n");
      //  sb.append("    AND A.MGROUP = ?   \n");
      //  sb.append("    AND A.SGROUP = ?   \n");
        sb.append("  ORDER BY A.DGROUP	\n");

        //= log SQL -------------------------------------------------
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Print
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlPrint() {

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.LGROUP,      \n");
        sb.append("        A.LGROUP_NAME, \n");
        sb.append("        A.MGROUP,      \n");
        sb.append("        A.MGROUP_NAME, \n");
        sb.append("        A.SGROUP,      \n");
        sb.append("        A.SGROUP_NAME, \n");
        sb.append("        A.DGROUP,      \n");
        sb.append("        A.DGROUP_NAME, \n");
        sb.append("        A.INSERT_DATE, \n");
        sb.append("        A.INSERT_ID,   \n");
        sb.append("        A.MODIFY_DATE, \n");
        sb.append("        A.MODIFY_ID    \n");
        sb.append("   FROM TGOODSKINDS A  \n");
        sb.append("  WHERE A.LGROUP LIKE ? || '%'   \n");
        sb.append("  ORDER BY A.LGROUP, A.MGROUP, A.SGROUP, A.DGROUP   \n");

        //= log SQL -------------------------------------------------
        return sb.toString();
    }
    /**
    * <PRE>
    * Desc : Make SQL ( Insert TGOODSKINDS )
    * </PRE>
    * @param   Tgoodskinds
    * @return  String
    */

    private final String insertSqlTgoodskinds = " INSERT INTO TGOODSKINDS ( \n "
                                              + "        LGROUP, \n "
                                              + "        MGROUP, \n "
                                              + "        SGROUP, \n "
                                              + "        DGROUP, \n "
                                              + "        LGROUP_NAME, \n "
                                              + "        MGROUP_NAME, \n "
                                              + "        SGROUP_NAME, \n "
                                              + "        DGROUP_NAME, \n "
                                              + "        DEF_VAT_RATE, \n "


                                              + "        QC_LGROUP, \n "
                                              + "        QC_MGROUP, \n "
                                              + "        LMSD_CODE, \n "

                                              + "        INSERT_DATE, \n "
                                              + "        INSERT_ID,   \n "
                                              + "        MODIFY_DATE, \n "
                                              + "        MODIFY_ID)   \n "
                                              + "VALUES (   \n "
                                              + "        ?, \n "
                                              + "        ?, \n "
                                              + "        ?, \n "
                                              + "        ?, \n "
                                              + "        (SELECT CODE_NAME FROM TCODE WHERE CODE_LGROUP = 'B053' AND CODE_MGROUP = ?), \n "
                                              + "        ?, \n "
                                              + "        ?, \n "
                                              + "        ?, \n "
                                              + "        0, \n "

                                              + "        ?, \n "
                                              + "        ?, \n "
                                              + "        ?, \n "

                                              + "        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                              + "        ?, \n "
                                              + "        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                              + "        ?) \n ";
    

    /**
    * <PRE>
    * Desc : Make SQL ( Update TGOODSKINDS )
    * </PRE>
    * @param   Tgoodskinds
    * @return  String
    */
    private final String updateSqlTgoodskinds = " UPDATE TGOODSKINDS SET   \n "
                                              + "        LGROUP_NAME = (SELECT CODE_NAME FROM TCODE WHERE CODE_LGROUP = 'B053' AND CODE_MGROUP = ?), \n "
                                              + "        MGROUP_NAME = ? , \n "
                                              + "        SGROUP_NAME = ? , \n "
                                              + "        DGROUP_NAME = ? , \n "
                                              + "        MODIFY_DATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                              + "        MODIFY_ID   = ? \n "
                                              + "  WHERE LGROUP      = ? \n "
                                              + "    AND MGROUP      = ? \n "
                                              + "    AND SGROUP      = ? \n "
                                              + "    AND DGROUP      = ? \n ";

    private int updateSqlLogTgoodskinds =  0;

    

    /**
    * <PRE>
    * Desc : Make SQL ( Update TGOODSKINDS, when mgroup name modified )
    * </PRE>
    * @param   Tgoodskinds
    * @return  String
    */
    private final String updateSqlTgoodskindsMgroupAll
                = " UPDATE TGOODSKINDS SET   \n "
                + "        MGROUP_NAME = ? , \n "
                + "        MODIFY_DATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                + "        MODIFY_ID   = ? \n "
                + "  WHERE LGROUP      = ? \n "
                + "    AND MGROUP      = ? \n ";

    private int updateSqlLogTgoodskindsMgroupAll =  0;

    

    /**
    * <PRE>
    * Desc : Make SQL ( Insert TFORM )
    * </PRE>
    * @param   Tform
    * @return  String
    */

    private final String insertSqlTform = " INSERT INTO TFORM ( \n "
                                        + "        LGROUP, \n "
                                        + "        MGROUP, \n "
                                        + "        SGROUP, \n "
                                        + "        DGROUP, \n "
                                        + "        FORM_CODE, \n "
                                        + "        FORM_NAME, \n "
                                        + "        INSERT_DATE, \n "
                                        + "        INSERT_ID,   \n "
                                        + "        MODIFY_DATE, \n "
                                        + "        MODIFY_ID)   \n "
                                        + "VALUES (   \n "
                                        + "        ?, \n "
                                        + "        ?, \n "
                                        + "        ?, \n "
                                        + "        ?, \n "
                                        + "        ?, \n "
                                        + "        ?, \n "
                                        + "        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                        + "        ?, \n "
                                        + "        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                        + "        ?) \n ";
    private final int insertSqlLogTform =  0;

    
    
    
    

}
