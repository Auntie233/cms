package top.auntie.cms.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SessionInfo implements Serializable {

    private Long id;

    private String username;

    private String password;

    private String type;

}
