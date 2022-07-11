package ru.netology.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ru.netology.main.FlightTimeComparator;
import ru.netology.main.Ticket;
import ru.netology.main.TicketManager;

import java.util.Arrays;

public class TicketManagerTest {

    public Ticket t1 = new Ticket(1, 8_000, "DME", "RGK", 260);
    public Ticket t2 = new Ticket(2, 7_500, "SVO", "RGK", 270);
    public Ticket t3 = new Ticket(3, 7_000, "VKO", "RGK", 280);
    public Ticket t4 = new Ticket(4, 6_500, "DME", "RGK", 290);
    public Ticket t5 = new Ticket(5, 6_000, "ZIA", "RGK", 300);
    public Ticket t6 = new Ticket(6, 7_000, "DME", "RGK", 260);

    TicketManager manager = new TicketManager(new TicketsRepository());
    FlightTimeComparator flightTimeComparator = new FlightTimeComparator();

    @Test
    public void shouldRemoveTicket() {
        manager.add(t1);
        manager.add(t2);
        manager.add(t4);
        manager.add(t5);

        manager.removeById(t5.getId());
        manager.removeById(4);

        Ticket[] expected = {t1, t2};
        assertArrayEquals(expected, manager.findAll());
    }

    @Test
    public void shouldSortTicketsByPrice() {

        manager.add(t1);
        manager.add(t2);
        manager.add(t3);
        manager.add(t4);
        manager.add(t5);

        Arrays.sort(manager.findAll());

        Ticket[] expected = {t5, t4, t3, t2, t1};
        Ticket[] actual = manager.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortTicketsByFlightTime() {

        manager.add(t1);
        manager.add(t2);
        manager.add(t3);
        manager.add(t4);
        manager.add(t5);

        Arrays.sort(manager.findAll(), flightTimeComparator);

        Ticket[] expected = {t1, t2, t3, t4, t5};
        Ticket[] actual = manager.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTicketsAndSortByPrice() {

        manager.add(t1);
        manager.add(t2);
        manager.add(t3);
        manager.add(t4);
        manager.add(t5);

        Ticket[] expected = {t4, t1};
        Ticket[] actual = manager.findByAirport("DME", "RGK");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindTickets() {

        manager.add(t1);
        manager.add(t2);
        manager.add(t3);
        manager.add(t4);
        manager.add(t5);

        Ticket[] expected = {};
        Ticket[] actual = manager.findByAirport("SVO", "KZN");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindTicketsIfNull() {

        manager.add(t1);
        manager.add(t2);
        manager.add(t3);
        manager.add(t4);
        manager.add(t5);

        Ticket[] expected = {};
        Ticket[] actual = manager.findByAirport(null, "KZN");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortFoundTicketsByFlightTime() {

        manager.add(t1);
        manager.add(t2);
        manager.add(t3);
        manager.add(t4);
        manager.add(t5);

        Ticket[] expected = {t1, t4};
        Ticket[] actual = manager.findByAirportSortByTime("DME", "RGK", flightTimeComparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortFoundTicketsByFlightTimeIfSameTime() {

        manager.add(t1);
        manager.add(t2);
        manager.add(t3);
        manager.add(t4);
        manager.add(t5);
        manager.add(t6);

        Ticket[] expected = {t1, t6, t4};
        Ticket[] actual = manager.findByAirportSortByTime("DME", "RGK", flightTimeComparator);

        assertArrayEquals(expected, actual);
    }

}

