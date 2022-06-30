import dao.JdbcAirportDao;
import dao.JdbcFlightDao;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class Application {

    private JdbcAirportDao jdbcAirportDao;
    private JdbcFlightDao jdbcFlightDao;

    public static void main(String[] args) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/airline");
        dataSource.setUsername(""); // Redacted
        dataSource.setPassword(""); // Redacted

        Application app = new Application(dataSource);
        app.run();
    }

    public Application(DataSource dataSource) {
        jdbcAirportDao = new JdbcAirportDao(dataSource);
        jdbcFlightDao = new JdbcFlightDao(dataSource);
    }

    private void run() {

        // TODO Add command line functionality to application

        System.out.println("Hello! Welcome to the Sunset Airlines travel concierge. Please select an option below.\r\n");

        // For future implementation: Give user ability to book, update or cancel a flight, as well as see details about the flight, airports and plane.
    }

}
