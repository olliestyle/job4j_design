package ru.job4j.solid.tdd;

import org.junit.Test;
import ru.job4j.solid.tdd.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CinemaTest {
    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2021, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Test
    public void whenNoSessions() {
        Cinema cinema = new Cinema3D();
        List<Session> sessions = cinema.find(session -> true);
        assertEquals(0, sessions.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTicketOnSamePlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2021, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        Ticket ticket2 = cinema.buy(account, 1, 1, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenOldDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2011, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBadRow() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2011, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 999, 1, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBadColumn() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2011, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 999, date);
    }
}