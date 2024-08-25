package software.approximations.gptchatbot.exceptions.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import software.approximations.gptchatbot.exceptions.StatusCodeException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    // {
    //    "timestamp": "2024-08-24T23:50:32.723+00:00",
    //    "status": 500,
    //    "error": "Internal Server Error",
    //    "message": "Email not found.",
    //    "path": "/auth/login"
    //}
    @ExceptionHandler(StatusCodeException.class)
    public Map<String, Object> statusCodeException(HttpServletRequest request, StatusCodeException exception) {
        final Map<String, Object> map = new HashMap<>();

        map.put("timestamp", Instant.now());
        map.put("status", exception.getStatus().value());
        map.put("error", exception.getStatus().getReasonPhrase());
        map.put("message", exception.getMessage());
        map.put("path", request.getServletPath());

        return map;
    }

}
