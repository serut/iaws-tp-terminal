package fr.ups.dl.iaws.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cédric Rohaut (@Oxynos) on 23/03/15 16:03.
 */
@SpringBootApplication
public class Salles implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Creating tables");
        jdbcTemplate.execute("drop table salle if exists");
        jdbcTemplate.execute("drop table salles if exists");
        jdbcTemplate.execute("create table salle(" +
                "id bigint auto_increment, numero INTEGER, ville varchar(255), isImax boolean, is3D boolean)");


        jdbcTemplate.execute("drop table FilmSalle if exists");
        jdbcTemplate.execute("create table film_salle(" +
                "id  bigint auto_increment, id_salle bigint, id_film varchar(255), isImax boolean, is3D boolean)");

        Salle s1 = new Salle(1, 1, "Toulouse", true, false);
        Salle s2 = new Salle(2, 2, "Toulouse", false, true);
        Salle s3 = new Salle(3, 1, "Paris", false, false);
        Salle s4 = new Salle(4, 2, "Paris", true, true);
        List<Salle> ls = new ArrayList<>();
        ls.add(s1);
        ls.add(s2);
        ls.add(s3);
        ls.add(s4);

        for (Salle s : ls) {
            System.out.println("Inserting record : " + s.toString());
            jdbcTemplate.update(
                    "INSERT INTO salle(numero, ville, isImax, is3D) values(?,?,?,?)",
                    s.getNumero(), s.getVille(), s.isImax(), s.is3D());
        }

    }

    public Salle getSalle(int id) {
        List<Salle> ls;
        List<Object> param = new ArrayList<Object>();
        param.add(id);
        ls = jdbcTemplate.query(
                "select id, numero, ville, isImax, is3D from salle where id = ?", param.toArray(),
                new RowMapper<Salle>() {
                    @Override
                    public Salle mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Salle(rs.getInt("id"), rs.getInt("numero"),
                                rs.getString("ville"), rs.getBoolean("isImax"), rs.getBoolean("is3D"));
                    }
                });
        if (ls.size() == 0) {
            return null;
        }
        return ls.get(0);
    }

    public List<Salle> getSalles() {
        List<Salle> ls;
        ls = jdbcTemplate.query(
                "select id, numero, ville from salle",
                new RowMapper<Salle>() {
                    @Override
                    public Salle mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Salle(rs.getInt("id"), rs.getInt("numero"),
                                rs.getString("ville"), rs.getBoolean("isImax"), rs.getBoolean("is3D"));
                    }
                });
        return ls;
    }

    public List<Salle> getSalles(String ville, Boolean isImax, Boolean is3D) {
        List<Salle> ls;
        List<Object> param = new ArrayList<Object>();
        String sqlQuery = "select id, numero, ville, isImax, is3D from salle where ";
        boolean addAnd = false;

        if (! ville.isEmpty()) {
            sqlQuery += "ville = ? ";
            param.add(ville);
            addAnd = true;
        }
        if (isImax != null) {
            if (addAnd) {
                sqlQuery += " and ";
            }
            sqlQuery += "isImax = ? ";
            param.add(isImax.booleanValue());
            addAnd = true;
        }
        if (is3D != null) {
            if (addAnd) {
                sqlQuery += " and ";
            }
            sqlQuery += "is3D = ? ";
            param.add(is3D.booleanValue());
        }
        ls = jdbcTemplate.query(
                sqlQuery, param.toArray(),
                new RowMapper<Salle>() {
                    @Override
                    public Salle mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Salle(rs.getInt("id"), rs.getInt("numero"),
                                rs.getString("ville"), rs.getBoolean("isImax"), rs.getBoolean("is3D"));
                    }
                });
        return ls;
    }

    public List<Salle> getSallesFilm(String id) {
        List<Salle> ls = new ArrayList<>();
        /*ls = jdbcTemplate.query(
                "select id, numero, ville from salles where ville = ?", new Object[] { ville },
                new RowMapper<Salle>() {
                    @Override
                    public Salle mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Salle(rs.getInt("id"), rs.getInt("numero"),
                                rs.getString("ville"));
                    }
                });*/
        return ls;
    }
    
    public void insertFilmSalle(FilmSalle fs) throws Exception {
        System.out.println("Inserting record : " + fs.toString());
        jdbcTemplate.update(
                "INSERT INTO film_salle(id_salle, id_film) values(?,?)",
                fs.getIdSalle(), fs.getIdFilm());
    }
}
