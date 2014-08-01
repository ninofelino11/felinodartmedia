package com.cware.back.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.entity.table.Tuser;

public class TxtDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(Construct.LOG_BASE);

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String reqType = null;
		String fileName = null;
		RetrieveModel retrieve = new RetrieveModel();
		HttpSession session = null;
		try {
			//권한체크
			session = request.getSession();
			Tuser user = (Tuser) session.getAttribute("SESSION_USER");
			if (user == null || "".equals(ComUtils.NVL(user.getUser_id()))) {
				return;
			}
			reqType = request.getParameter("REQ_TYPE");
			fileName = request.getParameter("FILE_NAME");

			if (fileName == null)
				fileName = "SAMPLE.txt";
			fileName = new String(fileName.getBytes("8859_1"), "UTF-8");
			if (fileName.indexOf(".") == -1)
				fileName += ".txt";

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/plain");
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			response.setBufferSize(8096);

			//REQUEST TYPE 에 따른 파라미터 세팅
			if ("DM".equals(reqType)) {
				retrieve.put("DM_CODE", request.getParameter("DM_CODE"));
				retrieve.put("DM_YEAR", request.getParameter("DM_YEAR"));
				retrieve.put("SEND_SEQ", request.getParameter("SEND_SEQ"));

				dmFileDownload(retrieve, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	private void dmFileDownload(RetrieveModel retrieve, HttpServletResponse response) throws Exception {
		PrintWriter writer = null;
		StringBuffer buf = new StringBuffer(1024);

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtils.getConnection(Construct.DB_POOL_NAME);
			pstmt = con.prepareStatement(makeSqlDM(retrieve));
			int idx = 1;
			pstmt.setString(idx++, retrieve.getString("DM_CODE"));
			pstmt.setString(idx++, retrieve.getString("DM_YEAR"));
			pstmt.setString(idx++, retrieve.getString("SEND_SEQ"));
			rs = pstmt.executeQuery();

			writer = response.getWriter();

			int cnt = 0;
			while (rs.next()) {
				cnt++;
				buf.append(rs.getString(1)).append("\t");
				buf.append(rs.getString(2)).append("\t");
				buf.append(rs.getString(3)).append("\t");
				buf.append(rs.getString(4)).append("\t");
				buf.append(rs.getString(5));

				if (writer.checkError()) {
					throw new ServerException("Cancel Download...");
				}
				writer.println(buf.toString());
				buf.delete(0, buf.length());
			}
			writer.flush();

		} catch (Exception e) {
			throw e;
		} finally {
			writer.close();
			DBUtils.freeConnection(con, null, pstmt, null, null, rs);
		}
	}

	private String makeSqlDM(RetrieveModel retrieve) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT A.DM_SEND_NO, A.CUST_NO, B.CUST_NAME, D.POST_NO, D.CITY_NAME||' '||D.GU_NAME||' '||D.DONG_NAME||' '||NVL(C.RECEIVER_ADDR, ' ')    \n");
		sb.append("   FROM TDMSENDDT A, TCUSTOMER B, TRECEIVER C, TPOST D                                                                                    \n");
		sb.append("  WHERE A.CUST_NO = B.CUST_NO                                                                                                             \n");
		sb.append("    AND A.CUST_NO = C.CUST_NO                                                                                                             \n");
		sb.append("    AND A.RECEIVER_SEQ = C.RECEIVER_SEQ                                                                                                   \n");
		sb.append("    AND C.RECEIVER_POST = D.POST_NO                                                                                                       \n");
		sb.append("    AND C.RECEIVER_POST_SEQ = D.POST_SEQ                                                                                                  \n");
		sb.append("    AND C.DEFAULT_YN = '1'                                                                                                                \n");
		sb.append("    AND C.USE_YN = '1'                                                                                                                    \n");
		sb.append("    AND A.DM_CODE = ?                                                                                                                     \n");
		sb.append("    AND A.DM_YEAR = ?                                                                                                                     \n");
		sb.append("    AND A.SEND_SEQ = ?                                                                                                                    \n");

		if (log.isDebugEnabled()) {
			log.debug("\n" + sb.toString());
			log.debug("dm_code:  " + retrieve.getString("DM_CODE"));
			log.debug("dm_year: " + retrieve.getString("DM_YEAR"));
			log.debug("dm_year: " + retrieve.getString("SEND_SEQ"));
		}

		return sb.toString();
	}
}