package yago.ferreira.marketapi.domain.model;

public interface DomainUserDetails {
    String getPassword();
    String getUsername();
    boolean isAccountNonExpired();
    boolean isAccountNonLocked();
    boolean isCredentialsNonExpired();
    boolean isEnabled();
}
