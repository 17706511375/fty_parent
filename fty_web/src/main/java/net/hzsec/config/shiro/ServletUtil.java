package net.hzsec.config.shiro;

import net.hzsec.base.Constant;
import net.hzsec.base.PublicResultConstant;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class ServletUtil {

    public static void buildServeletRes(String header, String info,ServletResponse response){
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setCharacterEncoding("utf-8");
        httpResponse.setHeader(header,info);
    }

    public static void buildServeletRes(String info,ServletResponse response){
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setCharacterEncoding("utf-8");
        httpResponse.setHeader(PublicResultConstant.DEFAULT_MESSAGE_HEADER,info);
    }
}
