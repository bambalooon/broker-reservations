import pl.bb.broker.brokerreservations.client.ReservationClient;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 02.06.14
 * Time: 20:06
 * To change this template use File | Settings | File Templates.
 */


public class Test {

    @org.junit.Test
    public void test() throws Exception {
        new ReservationClient().reserve();
    }
}
