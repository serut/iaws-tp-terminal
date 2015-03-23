package fr.ups.dl.iaws.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        jdbcTemplate.execute("drop table salles if exists");
        jdbcTemplate.execute("create table salles(" +
                "id serial, numero INTEGER, ville varchar(255))");

        Salle s1 = new Salle(1, 1, "Toulouse");
        Salle s2 = new Salle(2, 2, "Toulouse");
        Salle s3 = new Salle(3, 1, "Paris");
        Salle s4 = new Salle(4, 2, "Paris");
        List<Salle> ls = new ArrayList<>();
        ls.add(s1);
        ls.add(s2);
        ls.add(s3);
        ls.add(s4);

        for (Salle s : ls) {
            System.out.println("Inserting record : " + s.toString());
            jdbcTemplate.update(
                    "INSERT INTO salles(numero, ville) values(?,?)",
                    s.getNumero(), s.getVille());
        }

        System.out.println("Querying for customer records where ville = 'Toulouse':");
        List<Salle> results = jdbcTemplate.query(
                "select id, numero, ville from salles where ville = ?", new Object[] { "Toulouse" },
                new RowMapper<Salle>() {
                    @Override
                    public Salle mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Salle(rs.getInt("id"), rs.getInt("numero"),
                                rs.getString("ville"));
                    }
                });

        for (Salle s : results) {
            System.out.println(s);
        }
    }

    public List<Salle> getSalles() {
        List<Salle> ls = new ArrayList<>();
        ls = jdbcTemplate.query(
                "select id, numero, ville from salles",
                new RowMapper<Salle>() {
                    @Override
                    public Salle mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Salle(rs.getInt("id"), rs.getInt("numero"),
                                rs.getString("ville"));
                    }
                });
        return ls;
    }

    public List<Salle> getSallesVille(String ville) {
        List<Salle> ls = new ArrayList<>();
        ls = jdbcTemplate.query(
                "select id, numero, ville from salles where ville = ?", new Object[] { ville },
                new RowMapper<Salle>() {
                    @Override
                    public Salle mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Salle(rs.getInt("id"), rs.getInt("numero"),
                                rs.getString("ville"));
                    }
                });
        return ls;
    }
}
