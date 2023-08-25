package com.example.taskList.web.dto.user;

import com.example.taskList.web.dto.validation.OnCreate;
import com.example.taskList.web.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
public class UserDto {

    @NotNull(message="Id couldn't be null", groups = OnUpdate.class)//for validation on not null and creating id
    private Long id;

    @NotNull(message="Name couldn't be null", groups= {OnCreate.class,OnUpdate.class})
    @Length(max=255, message="Name length shuold be less 255 symbols",groups= {OnCreate.class,OnUpdate.class})
    private String name;

    @NotNull(message="Username couldn't be null", groups= {OnCreate.class,OnUpdate.class})
    @Length(max=255, message="Username length shuold be less 255 symbols",groups= {OnCreate.class,OnUpdate.class})
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//we can only write it but not send it
    @NotNull(message="Password must be not null", groups= {OnCreate.class,OnUpdate.class})
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//we can only write it but not send it
    @NotNull(message="Password must be not null", groups= {OnCreate.class})//here user can update password
    private String passwordConfirmation;
}
