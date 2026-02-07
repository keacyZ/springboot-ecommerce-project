package com.example.test.util.constants;

public enum Authorities {

    ACCESS_ADMIN_PANEL(1L, "ACCESS_ADMIN_PANEL"),
    RESET_ANY_USER_PASSWORD(2L, "RESET_ANY_USER_PASSWORD");

    private final Long authorityId;
    private final String authority;

    Authorities(Long authorityId, String authority) {
        this.authorityId = authorityId;
        this.authority = authority;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public String getAuthority() {
        return authority;
    }
}
