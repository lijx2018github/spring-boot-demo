package com.lijx.demo.system.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.lijx.demo.common.enums.ResultEnum;
import com.lijx.demo.common.result.Result;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 重写 FormAuthenticationFilter
 */
public class CustFormAuthenticationFilter extends FormAuthenticationFilter {

    /**
     * 重写认证失败的方法
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //设置HttpServletResponse
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(200);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        Result r = new Result(ResultEnum.AUTH_ERR);
        String result = JSONObject.toJSONString(r);
        out.println(result);
        out.flush();
        out.close();
        return false;
    }
}
