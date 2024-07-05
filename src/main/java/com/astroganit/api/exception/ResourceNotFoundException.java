package com.astroganit.api.exception;

public class ResourceNotFoundException extends RuntimeException {
   String resourceName;
   String fieldName;
   long fieldValue;

   public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
      super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
      this.resourceName = resourceName;
      this.fieldName = fieldName;
      this.fieldValue = fieldValue;
   }

   public String getResourceName() {
      return this.resourceName;
   }

   public String getFieldName() {
      return this.fieldName;
   }

   public long getFieldValue() {
      return this.fieldValue;
   }

   public void setResourceName(final String resourceName) {
      this.resourceName = resourceName;
   }

   public void setFieldName(final String fieldName) {
      this.fieldName = fieldName;
   }

   public void setFieldValue(final long fieldValue) {
      this.fieldValue = fieldValue;
   }
}
