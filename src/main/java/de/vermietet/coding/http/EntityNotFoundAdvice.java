/*
 * Copyright (C) 2019 USER
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.vermietet.coding.http;

import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

/**
 * @author USER
 */
@ControllerAdvice
public class EntityNotFoundAdvice {
    
    @ResponseBody
    @ExceptionHandler(EntityNotFoundExceptionForHttp.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<ResponseData> employeeNotFoundHandler(
            EntityNotFoundExceptionForHttp ex, WebRequest request) {
        
        final ResponseData body = new ResponseData();
        final HttpStatus status = HttpStatus.NOT_FOUND;
        body.setTimestamp(ZonedDateTime.now());
        body.setError(ex.getMessage());
        body.setStatus(status.value());
        body.setPath(((ServletWebRequest)request).getRequest().getRequestURI());
        
        return new ResponseEntity<>(body, status);    
    }
}
