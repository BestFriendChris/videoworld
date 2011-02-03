package com.thoughtworks.videorental.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.util.Feature;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RolesInterceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class AdminRoleInterceptor extends RolesInterceptor {
    private static final String USER_KEY = "user";

    @Override
    protected boolean isAllowed(HttpServletRequest request, Object action) {
        if (Feature.AdminAccount.isEnabled()) {
            Customer customer = (Customer) request.getSession().getAttribute(USER_KEY);
            return customer != null && customer.isAdmin();
        } else {
            return true;
        }
    }
}
