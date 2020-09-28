package top.auntie.cms.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserRoleDto implements Serializable {

    private Long userId;

    private List<Long> roleIds;

}
