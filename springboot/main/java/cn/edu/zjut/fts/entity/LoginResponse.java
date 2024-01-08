package cn.edu.zjut.fts.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse
{
    @JsonProperty("isSuccess")
    private boolean isSuccess;
    private int userID;
}
