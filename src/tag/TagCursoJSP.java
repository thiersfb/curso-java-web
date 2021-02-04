package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TagCursoJSP extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		
		//super.doTag();
		JspWriter out = getJspContext().getOut();
		out.println("Tag JSP customizada - teste alteração de texto");
	}

}
