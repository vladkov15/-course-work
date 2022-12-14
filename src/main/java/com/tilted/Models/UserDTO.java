package com.tilted.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Range;

public class UserDTO {
    @JsonProperty("id")
    public int Id;
    @JsonProperty("login")
    public String Login;
    @JsonProperty("password")
    public String Password;
}
