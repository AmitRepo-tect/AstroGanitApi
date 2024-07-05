package com.astroganit.api.payload;

import java.util.List;
import org.springframework.http.HttpStatus;

public class Response {
   private String message;
   private int resultCode;
   private String errorMessage;
   private HttpStatus status;
   private List data;

   public Response() {
   }

   public Response(String message, int resultCode, String errorMessage, HttpStatus status, List data) {
      this.message = message;
      this.resultCode = resultCode;
      this.errorMessage = errorMessage;
      this.status = status;
      this.data = data;
   }

   public Response(String message, int resultCode, List data) {
      this.message = message;
      this.resultCode = resultCode;
      this.data = data;
   }

   public Response(int resultCode, String errorMessage, HttpStatus status) {
      this.resultCode = resultCode;
      this.errorMessage = errorMessage;
      this.status = status;
   }

   public Response(int resultCode, Throwable ex) {
      this();
      this.resultCode = resultCode;
      this.errorMessage = ex.getLocalizedMessage();
   }

   public Response(HttpStatus status, Throwable ex) {
      this();
      this.status = status;
      this.errorMessage = ex.getLocalizedMessage();
   }

   public void setMessage(final String message) {
      this.message = message;
   }

   public void setResultCode(final int resultCode) {
      this.resultCode = resultCode;
   }

   public void setErrorMessage(final String errorMessage) {
      this.errorMessage = errorMessage;
   }

   public void setStatus(final HttpStatus status) {
      this.status = status;
   }

   public void setData(final List data) {
      this.data = data;
   }

   public String getMessage() {
      return this.message;
   }

   public int getResultCode() {
      return this.resultCode;
   }

   public String getErrorMessage() {
      return this.errorMessage;
   }

   public HttpStatus getStatus() {
      return this.status;
   }

   public List getData() {
      return this.data;
   }
}
