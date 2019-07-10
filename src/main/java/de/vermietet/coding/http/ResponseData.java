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

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * @author USER
 */
public class ResponseData implements Serializable{
    
    private ZonedDateTime timestamp;
    
    private Integer status;
    
    private String error;
    
    private Object message;
    
    private String path;

    public ResponseData() { }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.timestamp);
        hash = 97 * hash + Objects.hashCode(this.status);
        hash = 97 * hash + Objects.hashCode(this.error);
        hash = 97 * hash + Objects.hashCode(this.message);
        hash = 97 * hash + Objects.hashCode(this.path);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResponseData other = (ResponseData) obj;
        if (!Objects.equals(this.error, other.error)) {
            return false;
        }
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return true;
    }
}
