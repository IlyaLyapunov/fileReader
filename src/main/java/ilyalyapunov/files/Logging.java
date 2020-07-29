package ilyalyapunov.files;

import lombok.NoArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@NoArgsConstructor

@Aspect
@Component
public class Logging {

    @Before("execution(* ilyalyapunov.files.Reader.get*(..))")
    public void init(JoinPoint joinPoint){
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Before: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* ilyalyapunov.files.Reader.get*(..))", returning = "res")
    public void getRes(JoinPoint joinPoint, Object res){
        System.out.println("Method " + joinPoint.getSignature().getName() + " returned " +
                res.toString() + " products");
        System.out.println("---------------------------------------------------------------------------");
    }

    @Around("execution(* ilyalyapunov.files.Reader.read(..))")
    public Object timeSmth(ProceedingJoinPoint joinPoint){
        Object res = null;
        System.out.println("---------------------------------------------------------------------------");
        try {
            long start = System.currentTimeMillis();
            res = joinPoint.proceed();
            long end = System.currentTimeMillis();
            System.out.println("File was read succesfully");
            System.out.println("Reading from file took " + (end - start) + " milliseconds");
        } catch (Throwable throwable){
            System.out.println("Wrong filename");
        }
        System.out.println("---------------------------------------------------------------------------");
        return res;
    }
}
