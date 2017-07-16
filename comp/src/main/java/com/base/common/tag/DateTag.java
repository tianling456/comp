package com.base.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

/**
 *项目名：
 *创建时间：2017-6-13
 *创建人：Aobo
 *类名：DateTag
 *所属包名：com.base.common.tag
 *项目名称：comp
 *类功能描述：
 */
public class DateTag extends BodyTagSupport{
	private PageContext pageContext;

    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }
    @Override
    public int doStartTag() throws JspException {
        
        //返回BodyTag.EVAL_BODY_BUFFERED，表示输出标签体内容
        //返回Tag.SKIP_BODY,表示不输出内容
        return BodyTag.EVAL_BODY_BUFFERED;
        //return Tag.SKIP_BODY;
    }
    @Override
    public int doEndTag() throws JspException {
        //得到BodyContent对象，它包装了标签体里的内容
        BodyContent bodyContent = this.getBodyContent();
        //利用getString方法得到字符串
        String content = bodyContent.getString();
        //改变字符串内容，将小写改为大写
        String change = content.toUpperCase();
        //输出到浏览器
        try {
            this.pageContext.getResponse().getWriter().write(change);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Tag.SKIP_PAGE;
    }
}

