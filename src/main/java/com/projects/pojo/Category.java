package com.projects.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Builder;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDateTime;

@Data
public class Category {
    @NotNull(groups = update.class)
    private Integer id;  // primary key
    @NotEmpty
    private String categoryName;
    @NotEmpty
    private String categoryAlias;
    @JsonIgnore
    private Integer createUser;     //Creator User ID
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    // Use Group to Specify which function the current restriction will be applied
    /*
        1. How to define group? - Define interfaces in the POJO class
        2. How to classify the restrictions? - Use group property
        3. How to specify group when verify the value in the function? - Add the corresponding interface for @Validated annotation
        4. Which group it will be when not specify which group the current restriction belongs to? - Default group
        5. Note: interfaces defined in the POJO class can be extended to inherit the values. It could help avoid adding
            group property for each variable used.
     */

    public interface add extends Default {

    }

    public interface update extends Default {

    }
}
