package org.matcha.server.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.matcha.server.mongo.impl.DefaultMongoDao;
import org.matcha.server.startup.Bootstrap;
import org.matcha.server.web.dao.MenuDao;
import org.matcha.server.web.service.MenuService;

import com.yuxindata.iomp.db.mongo.BasicDBOperationDao;

/**
 * 用来获取已经收集到数据的主机列表
 * @author lichong
 *
 */
public class MenuServlet extends HttpServlet{

	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		Bootstrap bootstrap = new Bootstrap();
		try {
			bootstrap.init();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return;
		}
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String encoding = "utf-8";
		MenuService service = new MenuService();
		JSONArray array = JSONArray.fromObject(service.menuList());
		PrintWriter pw;
		resp.setContentType("text/html; charset=" + encoding);
		//增添ajax跨域
		resp.addHeader("Access-Control-Allow-Origin", "*");
		pw = resp.getWriter();
		pw.print(array);
	}
	
}
