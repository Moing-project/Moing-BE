package com.moing.moingbe.domain.workspace.enums;

import java.util.Arrays;

public enum WorkPermissionEnum {

    OWNER("소유자"),
    MANAGER("관리자"),
    MEMBER("멤버"),
    GUEST("손님");

    private final String permission;

    WorkPermissionEnum(String permission) {
        this.permission = permission;
    }

    public String permission(){
        return this.permission;
    }

    public static WorkPermissionEnum get(String keyOrValue){
        for (WorkPermissionEnum value : values()) {
            if(value.name().equalsIgnoreCase(keyOrValue)|| value.permission.equals(keyOrValue))
                return value;
        }
        return null;
    }

    public static String[] names(){
        return (String[]) Arrays.stream(values()).map(Enum::name).toArray();
    }
}
