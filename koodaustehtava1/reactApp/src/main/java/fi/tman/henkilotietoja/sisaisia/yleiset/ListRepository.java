package fi.tman.henkilotietoja.sisaisia.yleiset;

import java.util.*;

public abstract class ListRepository<T extends Entiteetti> {

    private final Map<Long, T> entiteetit;

    public ListRepository() {
        this(new ArrayList<>());
    }

    public ListRepository(List<T> entiteetit) {
        this.entiteetit = new HashMap<>();
        for (T entiteetti: entiteetit) {
            tallenna(entiteetti);
        }
    }

    public List<T> haeKaikki() {
        return this.entiteetit.values().stream().toList();
    }

    public T haeTunnisteella(Long tunniste) {
        if(this.entiteetit.containsKey(tunniste)) {
            return this.entiteetit.get(tunniste);
        }
        return null;
    }

    public List<T> haeTunnisteilla(List<Long> tunnisteet) {
        List<T> entiteetit = new ArrayList<>();
        for(Long tunniste: tunnisteet) {
            if(this.entiteetit.containsKey(tunniste)) {
                entiteetit.add(this.entiteetit.get(tunniste));
            }
        }
        return entiteetit;
    }

    public T tallenna(T entiteetti) {
        if(entiteetti.getTunniste() == null) {
            entiteetti.setTunniste(seuraavaTunniste());
        }
        this.entiteetit.put(entiteetti.getTunniste(), entiteetti);
        return this.entiteetit.get(entiteetti.getTunniste());
    }

    public void poista(T entiteetti) {
        if(this.entiteetit.containsValue(entiteetti)) {
            this.entiteetit.remove(entiteetti.getTunniste(), entiteetti);
        }
    }

    public void poistaTunnisteella(Long tunniste) {
        if(this.entiteetit.containsKey(tunniste)) {
            this.entiteetit.remove(tunniste);
        }
    }

    private Long seuraavaTunniste() {
        // Järjestetään entiteettisäiliön avaimet, jolloin viimeinen on suurin
        Long tunniste = null;
        if(this.entiteetit.isEmpty()) {
            tunniste = 1L;
        } else {
            tunniste = this.entiteetit.keySet().stream().sorted().toList().get(this.entiteetit.size() - 1) + 1;
        }
        return tunniste;
    }
}
