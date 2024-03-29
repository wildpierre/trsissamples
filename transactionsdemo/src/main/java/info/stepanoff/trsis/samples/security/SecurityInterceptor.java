
package info.stepanoff.trsis.samples.security;

import info.stepanoff.trsis.samples.rest.ForbiddenException;
import info.stepanoff.trsis.samples.service.MyLogin;
import java.lang.reflect.Method;
import java.security.Principal;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Pavel
 */
@Aspect
@Slf4j
public class SecurityInterceptor {

    @Before("@annotation(NeedRole)")
    public void invoke(JoinPoint joinPoint) {
        log.info("SecurityInterceptor: Method Name=" + joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        Principal principal = (Principal) (args[args.length - 1]);
        
        if (principal == null) {
            log.error("Principal is null, user is not logged in");
            throw new ForbiddenException();
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        NeedRole needRoleAnnotation = method.getAnnotation(NeedRole.class);

        String roleRegex = needRoleAnnotation.roleRegex();
        System.out.println("Demand roleRegex = " + roleRegex);
        //MyLogin activeUser = (MyLogin) ((Authentication) principal).getPrincipal();        
        User activeUser = (User) ((Authentication) principal).getPrincipal();        
        
        for (GrantedAuthority authority:activeUser.getAuthorities())
            if (authority.getAuthority().equals(roleRegex)) //should be regex, not equals
                return;

        throw new ForbiddenException();

    }
}
