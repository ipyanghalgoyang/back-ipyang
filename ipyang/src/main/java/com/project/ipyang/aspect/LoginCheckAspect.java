package com.project.ipyang.aspect;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.config.SessionUser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Aspect
@Slf4j
@Component
public class LoginCheckAspect {
    private final HttpSession session;

    @Autowired
    public LoginCheckAspect(HttpSession session) {
        this.session = session;
    }


//    @Around("execution(* com.project.ipyang.domain.board.controller.BoardController.writeBoard(..))|| execution(* com.project.ipyang.domain.board.controller.BoardController.updateBoard(..))")
//    public Object aroundWriteBoardMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        // Perform login check only for writeBoard method
//        SessionUser loggedInUser = (SessionUser) session.getAttribute("loggedInUser");
//        if (loggedInUser == null) {
//            return new ResponseDto("로그인이 필요합니다", HttpStatus.UNAUTHORIZED.value());
//        }
//        Long memberId = loggedInUser.getId();
//        if (memberId == null) {
//            return new ResponseDto("존재하지 않는 회원입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
//        }
//
//        return proceedingJoinPoint.proceed();
//    }




    @Around("execution(* com.project.ipyang.domain.board.controller.*.*(..))")
    public Object aroundBoardMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = methodSignature.getName();

        if (methodName.equals("selectAllBoard")
                || methodName.equals("selectSomeBoard")
                || methodName.equals("readBoard")

        ) {
            return proceedingJoinPoint.proceed();
        }


        SessionUser loggedInUser = (SessionUser) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return new ResponseDto("로그인이 필요합니다", HttpStatus.UNAUTHORIZED.value());
        }
        Long memberId = loggedInUser.getId();
        if (memberId == null) {
            return new ResponseDto("존재하지 않는 회원입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return proceedingJoinPoint.proceed();
    }

    @Around("execution(* com.project.ipyang.domain.product.controller.*.*(..))")
    public Object aroundProductMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = methodSignature.getName();

        if (methodName.equals("selectAllProduct")|| methodName.equals("readProduct")  ) {
            return proceedingJoinPoint.proceed();
        }


        SessionUser loggedInUser = (SessionUser) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return new ResponseDto("로그인이 필요합니다", HttpStatus.UNAUTHORIZED.value());
        }
        Long memberId = loggedInUser.getId();
        if (memberId == null) {
            return new ResponseDto("존재하지 않는 회원입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return proceedingJoinPoint.proceed();
    }

    @Around("execution(* com.project.ipyang.domain.adopt.controller.*.*(..))")
    public Object aroundAdoptMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = methodSignature.getName();

        if (methodName.equals("getAdoptList") || methodName.equals("adoptDetail") || methodName.equals("filterAdopt") ) {
            return proceedingJoinPoint.proceed();
        }

        SessionUser loggedInUser = (SessionUser) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return new ResponseDto("로그인이 필요합니다", HttpStatus.UNAUTHORIZED.value());
        }
        Long memberId = loggedInUser.getId();
        if (memberId == null) {
            return new ResponseDto("존재하지 않는 회원입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return proceedingJoinPoint.proceed();
    }
}
