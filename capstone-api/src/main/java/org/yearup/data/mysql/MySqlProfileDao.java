package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.models.Profile;
import org.yearup.data.ProfileDao;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class MySqlProfileDao extends MySqlDaoBase implements ProfileDao
{
    public MySqlProfileDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public Profile create(Profile profile)
    {
        String sql = "INSERT INTO profiles (first_name, last_name, phone, email, address, city, state, zip) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            ps.setString(1, profile.getFirstName());
            ps.setString(2, profile.getLastName());
            ps.setString(3, profile.getPhone());
            ps.setString(4, profile.getEmail());
            ps.setString(5, profile.getAddress());
            ps.setString(6, profile.getCity());
            ps.setString(7, profile.getState());
            ps.setString(8, profile.getZip());

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    profile.setUserId(keys.getInt(1));
                }
            }

            return profile;
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Error creating profile", e);
        }
    }

    @Override
    public Profile getByUserId(int userId)
    {
        String sql = "SELECT user_id, first_name, last_name, phone, email, address, city, state, zip " +
                "FROM profiles WHERE user_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Profile profile = new Profile();
                    profile.setUserId(rs.getInt("user_id"));
                    profile.setFirstName(rs.getString("first_name"));
                    profile.setLastName(rs.getString("last_name"));
                    profile.setPhone(rs.getString("phone"));
                    profile.setEmail(rs.getString("email"));
                    profile.setAddress(rs.getString("address"));
                    profile.setCity(rs.getString("city"));
                    profile.setState(rs.getString("state"));
                    profile.setZip(rs.getString("zip"));
                    return profile;
                }
            }
            return null;
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Error fetching profile", e);
        }
    }

    @Override
    public Profile update(Profile profile)
    {
        String sql = "UPDATE profiles SET first_name=?, last_name=?, phone=?, email=?, address=?, city=?, state=?, zip=? " +
                "WHERE user_id=?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setString(1, profile.getFirstName());
            ps.setString(2, profile.getLastName());
            ps.setString(3, profile.getPhone());
            ps.setString(4, profile.getEmail());
            ps.setString(5, profile.getAddress());
            ps.setString(6, profile.getCity());
            ps.setString(7, profile.getState());
            ps.setString(8, profile.getZip());
            ps.setInt(9, profile.getUserId());

            ps.executeUpdate();

            return getByUserId(profile.getUserId());
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Error updating profile", e);
        }
    }
}