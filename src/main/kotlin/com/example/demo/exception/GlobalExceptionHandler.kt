package com.example.demo.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.ModelAndView

// 전역 예외 처리를 위한 @ControllerAdvice
@ControllerAdvice
class GlobalExceptionHandler {

    // 특정 예외를 처리하는 @ExceptionHandler
    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // HTTP 상태 코드를 지정할 수 있음
    fun handleIllegalArgumentException(
        request: HttpServletRequest,
        ex: IllegalArgumentException,
        model: Model
    ): ModelAndView {
        model.addAttribute("error", ex.message)
        return ModelAndView("error")  // error.html 템플릿을 렌더링
    }

    // 모든 예외를 처리하는 핸들러
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleAllExceptions(
        request: HttpServletRequest,
        ex: Exception,
        model: Model
    ): ModelAndView {
        model.addAttribute("error", "An unexpected error occurred: ${ex.message}")
        return ModelAndView("error")  // error.html 템플릿을 렌더링
    }
}
