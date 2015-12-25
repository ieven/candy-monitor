package org.matcha.server.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.matcha.server.web.exception.FromIsNullException;
import org.matcha.server.web.exception.TargetIsNullException;
import org.matcha.server.web.exception.UntilIsNullException;
import org.matcha.server.web.po.ErrorResponse;
import org.matcha.server.web.service.RenderService;

import com.yuxindata.iomp.factory.InstanceFactory;
import com.yuxindata.iomp.util.StringUtils;

public class RenderServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONArray array = null;
		try {
			//获取from
			String from = getFrom(req);
			//获取until
			String until = getUntil(req);
			//获取target
			String target = getTarget(req);
			try {
				RenderService service = InstanceFactory.getInstance(RenderService.class.getName());
				array = JSONArray.fromObject(service.getTargetData(from, until, target));
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FromIsNullException | UntilIsNullException | TargetIsNullException e) {
			// TODO Auto-generated catch block
			array = JSONArray.fromObject(new ErrorResponse("0001", e.getMessage()));
			e.printStackTrace();
		}
		
		PrintWriter pw;
		resp.setContentType("text/html; charset=utf-8");
		//增添ajax跨域
		resp.addHeader("Access-Control-Allow-Origin", "*");
		pw = resp.getWriter();
		pw.print(array);
	}
	
	private String getFrom(HttpServletRequest req) throws FromIsNullException
	{
		String from = req.getParameter("from");
		if(StringUtils.hasText(from))
		{
			return from;
		}
		else
		{
			throw new FromIsNullException("from 参数为空");
		}
	}
	
	private String getUntil(HttpServletRequest req) throws UntilIsNullException
	{
		String until = req.getParameter("until");
		if(StringUtils.hasText(until))
		{
			return until;
		}
		else
		{
			throw new UntilIsNullException("until 参数为空");
		}
	}
	
	private String getTarget(HttpServletRequest req) throws TargetIsNullException
	{
		String target = req.getParameter("target");
		if(StringUtils.hasText(target))
		{
			return target;
		}
		else
		{
			throw new TargetIsNullException("target 参数为空");
		}
	}
}
