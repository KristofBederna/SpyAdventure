package spyAdventure.common.database.dao;

import spyAdventure.common.database.entity.AbstractEntity;

import java.sql.SQLException;
import java.util.List;

public interface IEntityDao<E extends AbstractEntity> {

    List<E> getAll() throws SQLException;

    void add(E entity) throws SQLException;

}
