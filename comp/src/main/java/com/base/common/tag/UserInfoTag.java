package com.base.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.comp.entities.UserInfo;

/**
 * 项目名： 创建时间：2017-6-13 创建人：Aobo 类名：UserInfoTag 所属包名：com.base.common.tag
 * 项目名称：comp 类功能描述：
 */
public class UserInfoTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private UserInfo user;

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			if (user == null) {
				out.println("No UserInfo Found...");
				return SKIP_BODY;
			}
			out.println("<table width='500px' border='1' align='center'>");
			out.println("<tr>");
			out.println("<td width='20%'>Username:</td>");
			out.println("<td>" + user.getUserName() + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Birthday:</td>");
			out.println("<td>" + user.getBirthday() + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Email:</td>");
			out.println("<td>" + user.getEmail() + "</td>");
			out.println("</tr>");
			out.println("</table>");
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public void release() {
		super.release();
		this.user = null;
	}

	// getter and setters
	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}
}
