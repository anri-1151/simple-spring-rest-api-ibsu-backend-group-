package ge.isbu.demo.entities.enums;

public enum Permission {
    EMPLOYEE_ADD("employee:add"), EMPLOYEE_READ("employee:read");

    private String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
