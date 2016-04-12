/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.s2sgen.api.core;

/**
 * This class is for exceptions that occur in the s2s subsystem.
 */
public class S2SException extends RuntimeException {

    public static final String ERROR_S2S_UNKNOWN = "error.s2s.unknown";

    private String errorMessage;
    private String errorKey = ERROR_S2S_UNKNOWN;
    private int messageType;
    private String[] params;
    private String tabErrorKey;

    public S2SException() {
        super();
    }

    public S2SException(Exception ex) {
        super(ex);
        this.errorMessage = ex.getMessage();
    }

    public S2SException(String message,Exception ex) {
        super(message,ex);
    }

    public S2SException(String msg) {
        super(msg);
        this.errorMessage = msg;
    }

    public S2SException(String errorKey,String msg) {
        super(msg);
        this.errorMessage = msg;
        this.errorKey = errorKey;
    }
    public S2SException(String errorKey,String msg,String... params) {
        super(msg);
        this.errorMessage = msg;
        this.errorKey = errorKey;
        this.params = params;
    }
    public S2SException(String msg, int messageType) {
        super(msg);
        this.errorMessage = msg;
        this.messageType = messageType;
    }

    public String getMessage() {
        return errorMessage;
    }

    /**
     * This method returns the message as the first element followed by all params.
     * @return message and parameters in an array.
     */
    public String[] getMessageWithParams() {
        String[] messageWithParams = new String[getParams().length+1];
        messageWithParams[0]=errorMessage;
        for (int i = 1; i < messageWithParams.length; i++) {
            messageWithParams[i]=params[i-1];
        }
        return messageWithParams;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public String getTabErrorKey() {
        return tabErrorKey;
    }

    public void setTabErrorKey(String tabErrorKey) {
        this.tabErrorKey = tabErrorKey;
    }
}
