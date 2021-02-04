package org.acme;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * inspired by https://stackoverflow.com/a/46953737
 */
@WebFilter(filterName = "ServletFilter")
public class ServletFilter implements Filter {


    @Override
    public void init(FilterConfig config) throws ServletException {
        //enforced by interface
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("ServletFilter was called");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //enforced by interface
    }
}