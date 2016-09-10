package challenge.aspect;

import challenge.exception.AuthorizationException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Aspect
@Component
public class ApiMonitor {

    @AfterReturning("execution(* challenge..*Controller.*(..))")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("Completed: " + joinPoint);
    }

    @Before("execution(* challenge..*Controller.*(..))")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length==0) {
            return;
        }
        Object arg = args[0];
        if (arg instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2 = (OAuth2Authentication) arg;
            Authentication auth = oAuth2.getUserAuthentication();
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            if (authorities.size()==0) {
                throw new AuthorizationException();
            }
        }
        System.out.println("Completed: " + joinPoint);
    }

    @After("execution(* challenge..*Controller.*(..))")
    public void after(JoinPoint joinPoint) {

    }

}
