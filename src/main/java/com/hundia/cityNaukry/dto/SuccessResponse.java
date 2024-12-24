package com.hundia.cityNaukry.dto;

public class SuccessResponse {
	
	  private String message;
	    private String description;
	    private Object details;

	    public SuccessResponse(String message, String description, Object details) {
	        this.message = message;
	        this.description = description;
	        this.details = details;
	    }
	    
	    public SuccessResponse() {
			// TODO Auto-generated constructor stub
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Object getDetails() {
			return details;
		}

		public void setDetails(Object details) {
			this.details = details;
		}

		@Override
		public String toString() {
			return "SuccessResponse [message=" + message + ", description=" + description + ", details=" + details
					+ "]";
		}
	    
	    

}
