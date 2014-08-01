package com.cware.back.common;


/**
* Construct 정의
*
* @version 1.0, 2006/04/01
* @author kim sungtaek [webzest@commerceware.co.kr]
*/
public class Construct {

	public static String getProperties () {
		if ( RESOURCE_BUNDLE_NAME != null ) return RESOURCE_BUNDLE_NAME;
		/* default : system language, first : tomcat start option ['-Dlocale=ko']*/
		String defaultLocale = System.getProperty("locale");
		if ( defaultLocale == null ) {
			defaultLocale = System.getProperty("user.language");
		}
		if (defaultLocale.equals("ko")) {
			RESOURCE_BUNDLE_NAME = "resource.ko_KR.words";
		} else if (defaultLocale.equals("en")) {
			RESOURCE_BUNDLE_NAME = "resource.en_US.words";
		} else if (defaultLocale.equals("zh")) {
			RESOURCE_BUNDLE_NAME = "resource.zh_CN.words";
		}else {
			RESOURCE_BUNDLE_NAME = "resource.en_US.words";
		}

		return RESOURCE_BUNDLE_NAME;
	}
    /** log file name **/
    public static String LOG_BASE="back-pkg11-100-base";
    public static String LOG_SAVE="back-pkg11-100-save";
    public static String LOG_ORDER="back-pkg11-100-order";
    public static String LOG_SYSTEM="back-pkg11-100-system";

    /** application resource name **/
	public static String RESOURCE_BUNDLE_NAME     = getProperties();//"resource.ko_KR.words";

    /** 파일 경로 **/
    public static String FILE_CARDOK="/data1/back-flex/";

    /** 일반 고정 설정 상수 네이밍룰 : S_XXXX 로 표기함 1 부터 증가(0-999) **/
    public static int P_COMBO_DISPLAY_HTML = 1;
    public static int P_COMBO_DISPLAY_GRID = 2;
    public static int P_COMBO_DISPLAY_HTML_CODE_NAME = 3;
    public static int P_COMBO_DISPLAY_HTML_BLANK = 4;
    public static int P_COMBO_DISPLAY_HTML_CODE_NAME_BLANK = 6;
    public static int P_COMBO_DISPLAY_HTML_FULL_VALUE = 7;
    public static int P_COMBO_DISPLAY_HTML_FULL_VALUE_BLANK = 8;
    public static String DB_POOL_NAME="jdbc/netshop";
    public static String WDB_POOL_NAME="jdbc/netshop";
    public static String SWDB_POOL_NAME="jdbc/netshop_stage";
    public static String DB_SWUSERNAME = "snsus";
    public static String DB_WUSERNAME = "nsus";
    public static String DB_TEST_SWUSERNAME = "tsnsus";
    public static String DB_TEST_WUSERNAME = "tnsus";
    public static String DC_ADD_TYPE="ADD";
    public static String SAVE_ADD_TYPE="ADD";
    public static String G_MEDIA_CODE="T01";
    public static String G_ORDER_MEDIA="11";
    //public static String RESOURCE_BUNDLE_NAME="resource.words";
	/** System environment variable :: Application Config File Name **/
	public static String SYSTEM_ENV_CONFIG_FILE_PATH = "cware_back_config_file";
	public static String DEFAULT_WH_CODE="001";
	public static String ENTP_WH_CODE="990";

//= 삭제 해야 함
//    public static int 	DEFAULT_SCALE_AMT  = 2;
//    public static final int 	DEFAULT_SCALE_RATE = 2;
//    public static final int 	CALCULATE_SCALE    = 10;
//    public static final RoundingMode roundMode = RoundingMode.DOWN;


	/** 최종 소수점 유효 자리수 **/
//	public static int DEFAULT_FINAL_SIZE = 0;
	public static int DEFAULT_FINAL_SIZE = 2;
	/** 기본 반올림 정책  [ 3:버림  4:반올림 2:올림 ] **/
	public static int DEFAULT_MODE       = 4;
	/** 계산시 유효 소수점 자리수 **/
	public static int VALID_DECIMAL      = 6;
	/** 반올림 정책 : 올림   BigDecimal.ROUND_CEILING **/
	public static int MODE_CEIL          = 2;
	/** 반올림 정책 : 버림   BigDecimal.ROUND_FLOOR   **/
	public static int MODE_FLOOR         = 3;
	/** 반올림 정책 : 반올림 BigDecimal.ROUND_HALF_UP **/
	public static int MODE_ROUND         = 4;

	/** **/
	public static String DEFAULT_DELY_GB = "10";

    /** POPUP 관련 상수 네이밍룰 : P_XXXX 로 표기함 1000 부터 증가(1000-4999)**/
    public static final int P_CODE_MGROUP = 1000;
    public static final int P_USER_ID = 1001;
    public static final int P_GOODS_CODE_ALL = 1002;
    public static final int P_GOODS_CODE = 1003;
    public static final int P_GOODS_CODE_D = 1004;
    public static final int P_GOODS_CODE_DS = 1005;
    public static final int P_GOODS_CODE_DS1 = 1006;
    public static final int P_GOODS_CODE_UNIT = 1007;
    public static final int P_GOODSDT_CODE_D = 1008;
    public static final int P_SGOODS_CODE_MD = 1009;
    public static final int P_GOODSDT_CODE = 1010;
    public static final int P_LGROUP = 1011;
    public static final int P_MGROUP = 1012;
    public static final int P_SGROUP = 1013;
    public static final int P_DGROUP = 1014;
    public static final int P_COLOR_CODE = 1015;
    public static final int P_SIZE_CODE = 1016;
    public static final int P_PATTERN_CODE = 1017;
    public static final int P_FORM_CODE = 1018;
    public static final int P_ENTP_CODE = 1019;
    public static final int P_ENTP_MAN_SEQ = 1020;
    public static final int P_MD_CODE = 1021;
    public static final int P_DESCRIBE_BASE_CODE = 1022;
    public static final int P_POST_NO = 1023;
    public static final int P_DONG_NAME = 1024;
    public static final int P_AREA_GB = 1025;
    public static final int P_POST_AREA = 1026;
    public static final int P_MAKECOMP_CODE = 1027;
    public static final int P_MEDIA_CODE = 1028;
    public static final int P_RACK_CODE = 1029;
    public static final int P_CHECK_CODE = 1030;
    public static final int P_QC_LGROUP = 1031;
    public static final int P_QC_MGROUP = 1032;
    public static final int P_PROMO_NO = 1034;
    public static final int P_ORIGIN_CODE = 1035;
    public static final int P_GOODS_CODE_DESCRIBE = 1036;
    public static final int P_SLIP_SEARCH = 1037;
    public static final int P_CARD_CODE = 1038;
    public static final int P_GOODSNAME_CODE = 1039;
    public static final int P_WH_CODE = 1040;
    public static final int P_COUNSEL_LGROUP_CODE = 1041;
    public static final int P_COUNSEL_MGROUP_CODE = 1042;
    public static final int P_OB_SEQ = 1043;
    public static final int P_BRAND_CODE = 1044;
    public static final int P_BOARD_CODE = 1045;
    public static final int P_CATEGORY_CODE = 1046;
    public static final int P_SNO = 1047;
    public static final int P_SHOWHOST_ID = 1048;
    public static final int P_PROG_CODE_REAL = 1049;
    public static final int P_MD_CODE_USER = 1050;
    public static final int P_LINK_GOODS_CODE = 1051;
    public static final int P_WITH_CODE = 1052;
    public static final int P_SALE_CODE = 1053;
    public static final int P_EVENT_NO = 1054;
    public static final int P_FAQ_KINDS = 1055;
    public static final int P_FAQ_CODE = 1056;
    public static final int P_BOARD_LKINDS = 1057;
    public static final int P_BOARD_MKINDS = 1058;
    public static final int P_RESEARCH_NO = 1059;
    public static final int P_RECEIPTSBANK_CODE = 1060;
    public static final int P_BANK_CODE = 1061;
    public static final int P_GOODS_CODE_PRICE = 1062;
    public static final int P_GOODS_NAME_PRICE = 1063;
    public static final int P_GOODS_CODE_GIFT= 1064;
    public static final int P_GOODS_NAME_GIFT= 1065;
    public static final int P_GOODS_CODE_DESCRIBE_COPY = 1066;
    public static final int P_CUST_CARD = 1067;
    public static final int P_CUST_RECEIPTSBANK = 1068;
    public static final int P_RACK_CODE_GOODS = 1069;
    public static final int P_GOODS_CODE_BALJU = 1070;
    public static final int P_GOODS_COPY_CODE = 1071;
    public static final int P_GOODS_CODE_ETCINOUT = 1072;
    public static final int P_RACK_CONTROL_NAME = 1073;
    public static final int P_CUST_BANK = 1074;
    public static final int P_ENTP_PURCHASE_NO = 1075;
    public static final int P_DESCRIBE_CODE = 1076;
    public static final int P_GOODS_NAME_UNIT = 1077;
    public static final int P_GOODS_NAME_DS = 1078;
    public static final int P_GOODS_NAME_D = 1079;
    public static final int P_GOODS_NAME = 1080;
    public static final int P_GOODS_NAME_ETCINOUT = 1081;
    public static final int P_GOODS_NAME_BALJU = 1082;
    public static final int P_LOTTERY_PROMO_NO = 1083;
    public static final int P_ORDER_MEDIA = 1084;
    public static final int P_SEG_CODE = 1085;
    public static final int P_CODE_LGROUP = 1086;
    public static final int P_COUNSEL_GROUP = 1087;
    public static final int P_CODE_NAME = 1088;
    public static final int P_MASTER_CODE = 1089;
    public static final int P_MASTER_NAME = 1090;
    public static final int P_RACK_CODE_FIX = 1091;
    public static final int P_RACK_CODE_ETC = 1092;
    public static final int P_GOODS_CODE_TREAT = 1093;
    public static final int P_GOODS_NAME_TREAT = 1094;
    public static final int P_RACK_CODE_NOT_REG = 1095;
    public static final int P_DM_CODE = 1096;
    public static final int P_SAMPLE_NO = 1097;
    public static final int P_MENU_PROGRAM = 1098;
    public static final int P_MMENU = 1099;
    public static final int P_SEND_SEQ = 1100;
    public static final int P_SKINTEST = 1101;
    public static final int P_RESEARCH = 1102;
    public static final int P_GOODS_POLL = 1103;
    public static final int P_THEMEGOODSBOARD_NO = 1104;
    public static final int P_MAGAZINE_NO = 1105;
    public static final int P_SHOP_CODE = 1106;
    public static final int P_RACK_CODE_GOODS_GRADE = 1107;
    public static final int P_PROGRAM_ID = 1108;
    public static final int P_EVENT_M = 1109;
    public static final int P_EMPLOYEE_ID = 1110;
    public static final int P_GOODS_CODE_ENTPTAKEOUT = 1111;
    public static final int P_GOODS_NAME_ENTPTAKEOUT = 1112;
    public static final int P_SALEPOINT_CODE = 1113;
    public static final int P_SALEPOINT_NAME = 1114;
    public static final int P_BRAND_NAME = 1115;
    public static final int P_FRAME = 1116;
    public static final int P_RECEIPTSBANK_NAME = 1117;
    public static final int P_GOODS_SEARCH = 1118;
    public static final int P_GOODS_NAME_DS1 = 1119;
    public static final int P_GOODS_CODE_D_UNIQUE = 1120;
    public static final int P_ORDER_GOODS_CODE = 1121;
    public static final int P_CODE_MGROUP_MULTI = 1122;
    public static final int P_MEDIA_CODE_MULTI = 1123;
    public static final int P_BROAD_SCHEDULE_COPY = 1124;
    public static final int P_DAILY_SALE = 1125;
    public static final int P_TAPE_CODE = 1126;
    public static final int P_TAPE_NAME = 1127;
    public static final int P_GOODS_CODE_MD = 1128;
    public static final int P_GOODS_NAME_MD = 1129;
    public static final int P_BOARD_KINDS = 1130;
    public static final int P_SO_CODE = 1131;
    public static final int P_SO_POST_NO = 1132;
    public static final int P_TAPE_CODE_GOODS = 1133;
    public static final int P_TAPE_NAME_GOODS = 1134;
    public static final int P_STATE_CODE = 1135;
    public static final int P_STATE_NAME = 1136;
    public static final int P_COLOR_PATTERN_MULTI = 1137;
    public static final int P_CSPF_GROUP_COLOR_CODE = 1138;
    public static final int P_CSPF_GROUP_FORM_CODE = 1139;
    public static final int P_CSPF_GROUP_PATTERN_CODE = 1140;
    public static final int P_CSPF_GROUP_SIZE_CODE = 1141;
    public static final int P_USER_GROUP_ID = 1142;
    public static final int P_CSPF_GROUP_CODE = 1143;
    public static final int P_CSPF_GROUP_AUTO = 1144;
    public static final int P_GOODS_CODE_WAREHOUSINGINPUT = 1153;
    public static final int P_GOODS_CODE_EOUT = 1154;
    public static final int P_GOODS_NAME_EOUT = 1155;
    public static final int P_SETTLEINFO = 1156;
    public static final int P_GOODS_NAME_ALL = 1157;
    public static final int P_TEMPLATE_CODE = 1158;
    public static final int P_PLANCLASS_CODE = 1159;
    public static final int P_PLAN_CODE = 1160;
    public static final int P_GOODS_CODE_SCM = 1161;
    public static final int P_GOODS_NAME_SCM = 1162;
    public static final int P_BD_PROGRAM_SELECT = 1163;
    public static final int P_IN_INSPECT = 1164;
    public static final int P_MULTI_BROAD_SCHEDULE_COPY = 1165;
    public static final int P_MODEL_CODE_NAME = 1166;
    public static final int P_MULTI_FRAME = 1167;
    public static final int P_EXPECTED_PROFIT = 1168;
    public static final int P_GIFT_NAME_PRICE = 1169;

    /** TextInput 관련 상수 네이밍룰 : T_XXXX 로 표기함 2000 부터 증가(2000-?)**/
    public static final int T_CUST_NO= 2000;
    public static final int T_COUNSEL_LGROUP_CODE=2001;
    public static final int T_CODE = 2002;
    public static final int T_CUST_NAME= 2003;
    public static final int T_COUNSEL_LGROUP_CODE_FR=2004;
    public static final int T_COUNSEL_LGROUP_CODE_TO=2005;
    public static final int T_ETIME=2006;
    public static final int T_PROGRAM_NAME=2007;
    public static final int T_GET_GOODS_PRICE=2009;
    public static final int T_PLANCLASS_CODE=2013;
    public static final int T_STOCK_QTY=2008;

    /** ComboBox 관련 상수 네이밍룰 : C_XXXX 로 표기함 5000 부터 증가(5000-?)**/
    public static final int C_TCODE         = 5000;
    public static final int C_TCODE_ALL     = 5001;
    public static final int C_TCODE_MODI    = 5002;
    public static final int C_TMEDIA        = 5010;
    public static final int C_WH_CODE       = 5020;
    public static final int C_WH_CODE_ENTP  = 5021;
    public static final int C_WH_CODE_GOODS = 5022;
    public static final int C_WH_CODE_LOC   = 5023;
    public static final int C_WH_CODE_SAMP  = 5024;
    public static final int C_BANK_CODE     = 5030;
    public static final int C_CARD_CODE     = 5040;
    public static final int C_SEND_SEQ      = 5042;
    public static final int C_ENTP_USER     = 5050;
    public static final int C_GOODS_KINDS   = 5060;
    public static final int C_MD_CODE       = 5070;
    public static final int C_USER_CODE     = 5080;
    public static final int C_LMENU         = 5081;
    public static final int C_LGROUP        = 5090;
    public static final int C_MGROUP        = 5100;
    public static final int C_SGROUP        = 5110;
    public static final int C_SKIN_TYPE     = 5111;
    public static final int C_SCPF_FLAG_C   = 5112;
    public static final int C_SCPF_FLAG_P   = 5113;
    public static final int C_SETTLEINFO    = 5114;
    public static final int C_QUICK_GRUOP   = 5115;
    public static final int C_MEDIACHANNEL  = 5116;
    public static final int C_SHOWHOST_CODE = 5117;
    public static final int C_MODEL_CODE  	= 5118;
    public static final int C_GUEST_CODE  	= 5119;
    public static final int C_AREA_GB 		= 5120;

    public static final char DOUBLEQ='"';
    public static final int USER_D_POINT = 2; //= 금액 소숫점 단위
    public static final int FINAL_D_POINT = 2; //= 2:소수점두자리, 0:원단위, -1:원단위 절사
    public static final int VAT_D_POINT = 2; //= 2:소수점두자리, 0:원단위, -1:원단위 절사

}