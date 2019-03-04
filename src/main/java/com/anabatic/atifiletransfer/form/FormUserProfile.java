package com.anabatic.atifiletransfer.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.anabatic.atifiletransfer.entities.User;
import com.anabatic.atifiletransfer.validation.FieldMatch;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@FieldMatch.List({
    @FieldMatch(first = "newPassword", second = "confirmPassword", message = "{message.error.user.password.confirm}"),
    @FieldMatch(first = "user.password", second = "oldPassword", message = "{message.error.user.password.wrong}")
})
public class FormUserProfile {
	
	private String oldPassword;
	
	@NotNull
    @Size(min=5, max=15, message="{message.error.user.newpassword}")
	private String newPassword;
	
	@NotNull
    @Size(min=5, max=15, message="{message.error.user.confirmpassword}")
	private String confirmPassword;
	
	private User user;
	
	public FormUserProfile() {
		
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@JsonCreator
    public FormUserProfile(@JsonProperty("newPassword") String newPassword, @JsonProperty("confirmPassword") String confirmPassword) {
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
	}
	
}
