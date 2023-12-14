package spyAdventure.common.database.dao;

import spyAdventure.common.database.connection.DataSource;
import spyAdventure.common.database.entity.HighScore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HighScoreDao implements IEntityDao<HighScore> {

    private static final String SELECT_ALL_QUERY = "SELECT * FROM high_score";
    private static final String INSERT_QUERY = "INSERT INTO high_score (name, score) VALUES (?, ?)";

    private static final String ATTR_NAME_ID = "id";

    private static final String ATTR_NAME_NAME = "name";

    private static final String ATTR_NAME_SCORE = "score";

    @Override
    public List<HighScore> getAll() throws SQLException {
        try(Connection connection = DataSource.getInstance().getDbConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {

            final List<HighScore> entites = new ArrayList<>();
            while (resultSet.next()) {
                final HighScore entity = new HighScore();
                entity.setId(resultSet.getLong(ATTR_NAME_ID));
                entity.setName(resultSet.getString(ATTR_NAME_NAME));
                entity.setScore(resultSet.getInt(ATTR_NAME_SCORE));
                entites.add(entity);
            }
            return entites;
        }
    }

    @Override
    public void add(HighScore entity) throws SQLException {
        try(Connection connection = DataSource.getInstance().getDbConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
                statement.setString(1, entity.getName());
                statement.setInt(2, entity.getScore());

                statement.executeUpdate();
            }
    }
}
