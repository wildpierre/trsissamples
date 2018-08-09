/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.lab1.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;
import java.util.Collection;
import java.util.Locale;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pavel.Stepanov
 */

@WebFilter("/example")
public class ExampleFilter implements Filter {

    private FilterConfig filterConfig;    
    
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy()
    {
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
                
        //The same effect could be achieved in much more beautiful way using AOP 
        //but AOP is too advanced for this basic lesson 
        //So we do not use it and instead do ugly and verbose proxy code 
        //To suppress calling of getWriter()/getOutputStream() for real response object 
        //Which can be called only once
        HttpServletResponse newResponse = new HttpServletResponse(){
            @Override
            public String getCharacterEncoding() {
                return response.getCharacterEncoding();
            }

            @Override
            public String getContentType() {
                return response.getContentType();
            }

            @Override
            public ServletOutputStream getOutputStream() throws IOException {
                //nop - this code is not expected to be called
                //ServletOutputStream is too rich object to 
                //construct another proxy without explicit demand
                //so we omit this code (because from the logic of application
                //it is not expected to be called in servlet)               
                
                return null;//We can return some dummy output stream here; but
                //null looks more attractive since it will cause NPE
                //if our assumption above fails. Otherwise all data sent to stream
                //will be lost and that will potentially take a lot of effort debugging
            }

            @Override
            public PrintWriter getWriter() throws IOException {
                return printWriter;
            }

            @Override
            public void setCharacterEncoding(String string) {
                response.setCharacterEncoding(string);
            }

            @Override
            public void setContentLength(int i) {
                response.setContentLength(i);
            }

            @Override
            public void setContentLengthLong(long l) {
                response.setContentLengthLong(l);
            }

            @Override
            public void setContentType(String string) {
                response.setContentType(string);
            }

            @Override
            public void setBufferSize(int i) {
                response.setBufferSize(i);
            }

            @Override
            public int getBufferSize() {
                return response.getBufferSize();
            }

            @Override
            public void flushBuffer() throws IOException {
                response.flushBuffer();
            }

            @Override
            public void resetBuffer() {
                response.resetBuffer();
            }

            @Override
            public boolean isCommitted() {
                return response.isCommitted();
            }

            @Override
            public void reset() {
                response.reset();
            }

            @Override
            public void setLocale(Locale locale) {
                response.setLocale(locale);
            }

            @Override
            public Locale getLocale() {
                return response.getLocale();
            }            

            @Override
            public void addCookie(Cookie cookie) {
                ((HttpServletResponse)response).addCookie(cookie);
            }

            @Override
            public boolean containsHeader(String string) {
                return ((HttpServletResponse)response).containsHeader(string);
            }

            @Override
            public String encodeURL(String string) {
                return ((HttpServletResponse)response).encodeURL(string);
            }

            @Override
            public String encodeRedirectURL(String string) {
                return ((HttpServletResponse)response).encodeRedirectURL(string);
            }

            @Override
            public String encodeUrl(String string) {
                return ((HttpServletResponse)response).encodeUrl(string);
            }

            @Override
            public String encodeRedirectUrl(String string) {
                return ((HttpServletResponse)response).encodeRedirectUrl(string);
            }

            @Override
            public void sendError(int i, String string) throws IOException {
                ((HttpServletResponse)response).sendError(i,string);
            }

            @Override
            public void sendError(int i) throws IOException {
                ((HttpServletResponse)response).sendError(i);
            }

            @Override
            public void sendRedirect(String string) throws IOException {
                ((HttpServletResponse)response).sendRedirect(string);
            }

            @Override
            public void setDateHeader(String string, long l) {
                ((HttpServletResponse)response).setDateHeader(string, l);
            }

            @Override
            public void addDateHeader(String string, long l) {
                ((HttpServletResponse)response).addDateHeader(string, l);
            }

            @Override
            public void setHeader(String string, String string1) {
                ((HttpServletResponse)response).setHeader(string, string1);
            }

            @Override
            public void addHeader(String string, String string1) {
                ((HttpServletResponse)response).addHeader(string, string1);
            }

            @Override
            public void setIntHeader(String string, int i) {
                ((HttpServletResponse)response).setIntHeader(string, i);
            }

            @Override
            public void addIntHeader(String string, int i) {
                ((HttpServletResponse)response).addIntHeader(string, i);
            }

            @Override
            public void setStatus(int i) {
                ((HttpServletResponse)response).setStatus(i);
            }

            @Override
            public void setStatus(int i, String string) {
                ((HttpServletResponse)response).setStatus(i,string);
            }

            @Override
            public int getStatus() {
                return ((HttpServletResponse)response).getStatus();
            }

            @Override
            public String getHeader(String string) {
                return ((HttpServletResponse)response).getHeader(string);
            }

            @Override
            public Collection<String> getHeaders(String string) {
                return ((HttpServletResponse)response).getHeaders(string);
            }

            @Override
            public Collection<String> getHeaderNames() {
                return ((HttpServletResponse)response).getHeaderNames();
            }
        };
  
        chain.doFilter(request, newResponse);
        printWriter.flush();
        StringBuffer httpText = writer.getBuffer();
        
        int idx = httpText.indexOf("</body>");
        if (idx !=-1)
            httpText.insert(idx, "<p>Generated on "+Instant.now()+"</p>");
        response.getWriter().write(httpText.toString());        
    }  
}