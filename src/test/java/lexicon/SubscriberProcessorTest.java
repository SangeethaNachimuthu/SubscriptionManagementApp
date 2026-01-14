package lexicon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class SubscriberProcessorTest {

    private SubscriberDAO dao;
    private SubscriberProcessor processor;

    @BeforeEach
    void setUp() {
        dao = new SubscriberDAO();
        processor = new SubscriberProcessor();

        dao.save(new Subscriber(1, "abc@gmail.com", true, Plan.BASIC, 5));
        dao.save(new Subscriber(2, "gill@gmail.com", true, Plan.FREE, 5));
        dao.save(new Subscriber(3, "ali@gmail.com", false, Plan.BASIC, 0));
        dao.save(new Subscriber(4, "paul@gmail.com", true, Plan.PRO, 1));
    }

    @Test
    void testActiveSubscribers() {
        List<Subscriber> result = processor.findSubscribers(dao.findAll(), Main.activeSubscriber);

        assertEquals(3, result.size());
        assertTrue(result.stream().allMatch(Subscriber::isActive));
    }

    @Test
    void testExpiringSubscriptions() {
        List<Subscriber> result = processor.findSubscribers(dao.findAll(), Main.expiringSubscription);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(
                subscriber -> subscriber.getMonthsRemaining() <= 1));
    }

    @Test
    void testActiveAndExpiringSubscribers() {
        List<Subscriber> result = processor.findSubscribers(dao.findAll(), Main.activeAndExpiringSubscriber);

        assertEquals(1, result.size());
        assertTrue(result.stream().allMatch(
                subscriber -> (subscriber.isActive() && subscriber.getMonthsRemaining() <= 1)));
    }

    @Test
    void testExtendSubscriptionsForPayingSubscribers() {
        List<Subscriber> result = processor.applyToMatching(dao.findAll(),
                Main.payingSubscriber, Main.extendSubscription);

        assertEquals(3, result.size());
        assertTrue(result.stream().allMatch(subscriber ->
                subscriber.getPlan().equals(Plan.BASIC) || subscriber.getPlan().equals(Plan.PRO)));
    }

    @Test
    void testDeactivateExpiredFreeSubscribers() {
        List<Subscriber> result = processor.applyToMatching(dao.findAll(),
                Main.subscriberByPlan, Main.deactivateSubscriber);

        assertEquals(1, result.size());
        assertTrue(result.stream().allMatch(subscriber -> subscriber.getPlan().equals(Plan.FREE)));
    }

    @Test
    void testFilterSubscribersByPlan() {
        List<Subscriber> result = processor.findSubscribers(dao.findAll(), Main.subscriberByPlan);

        assertEquals(1, result.size());
        assertTrue(result.stream().allMatch(subscriber -> subscriber.getPlan().equals(Plan.FREE)));


    }
}
