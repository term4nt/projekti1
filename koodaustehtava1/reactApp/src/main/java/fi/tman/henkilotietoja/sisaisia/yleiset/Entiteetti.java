package fi.tman.henkilotietoja.sisaisia.yleiset;

import org.springframework.lang.NonNull;

import java.util.Objects;

public abstract class Entiteetti {
    private Long tunniste;

    public Entiteetti() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entiteetti that = (Entiteetti) o;
        return Objects.equals(getTunniste(), that.getTunniste());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getTunniste());
    }

    public Long getTunniste() {
        return tunniste;
    }

    public void setTunniste(@NonNull Long tunniste) {
        this.tunniste = tunniste;
    }
}
