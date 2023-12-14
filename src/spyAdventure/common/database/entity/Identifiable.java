package spyAdventure.common.database.entity;

public interface Identifiable <T extends Number> {

    T getId();

    void setId(T id);
}
