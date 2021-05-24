package reservation.pms.intercepter;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Aspect
@Component
public class ValidCheckingInterceptor {
	@Around("execution(* reservation.pms.*.*Controller.*(..))")
	public Object anyMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] objs = joinPoint.getArgs();
		for(Object obj : objs){
			if(obj instanceof BindingResult){
				BindingResult bindingResult = (BindingResult)obj;
				if(bindingResult.hasErrors()) {
					List<FieldError> fieldErrors = bindingResult.getFieldErrors();
					StringBuffer errMsg = new StringBuffer();
					for (FieldError fieldError : fieldErrors) {
						errMsg.append(fieldError.getField() + ":" + fieldError.getDefaultMessage() + ",");
					}
					throw new Exception(errMsg.toString());
				}
			}
		}
		return joinPoint.proceed(joinPoint.getArgs());
	}
}
