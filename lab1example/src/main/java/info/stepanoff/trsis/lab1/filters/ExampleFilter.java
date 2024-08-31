/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.lab1.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;

/**
 *
 * @author Pavel.Stepanov
 */
@WebFilter("/example")
public class ExampleFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        final StringWriter writer = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(writer);

        HttpServletResponseWrapper newResponseWrapper
                = new ExampleResponseWrapper((HttpServletResponse) response, printWriter);

        chain.doFilter(request, newResponseWrapper);
        printWriter.flush();
        StringBuffer httpText = writer.getBuffer();

        int idx = httpText.indexOf("</body>");
        if (idx != -1) {
            httpText.insert(idx, "<p>Generated on " + Instant.now() + "</p>");
        }
        response.getWriter().write(httpText.toString());
    }
}
