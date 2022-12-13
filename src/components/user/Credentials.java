package components.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Credentials {
    @Getter @Setter private String name;
    @Getter @Setter private String password;
    @Getter @Setter private String accountType;
    @Getter @Setter private String country;
    @Getter @Setter private String balance;

    public Credentials(String name, String password, String accountType, String country, String balance) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.country = country;
        this.balance = balance;
    }

    public Credentials(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Credentials(){
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credentials that = (Credentials) o;
        return name.equals(that.name) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}
