import dao.JdbcAirportDao;
import model.Airport;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class Application {

    private JdbcAirportDao airportTest;

    public static void main(String[] args) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/airline");
        dataSource.setUsername("postgres");
        dataSource.setPassword("***REMOVED***");

        Application app = new Application(dataSource);
        app.run();
    }

    public Application(DataSource dataSource) {
        airportTest = new JdbcAirportDao(dataSource);
    }

    private void run() {

        // TODO Add command line functionality to application

        // Test methods below
        System.out.println(airportTest.getAirport(5).toString());;
    }

}
