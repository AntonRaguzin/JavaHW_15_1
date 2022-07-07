package ru.netology.main;

import ru.netology.repository.TicketsRepository;

import java.util.Arrays;

public class TicketManager {

    private TicketsRepository repo;

    public TicketManager(TicketsRepository repo) {
        this.repo = repo;
    }

    public void add(Ticket ticket) {
        repo.add(ticket);
    }

    public void removeById(int id) {
        repo.removeById(id);
    }

    public Ticket[] findAll() {
        return repo.findAll();
    }

    public Ticket[] FindByAirport(String from, String to) {

        Ticket[] foundTickets = new Ticket[0];

        for (Ticket ticket : findAll()) {
            if (ticket.getFrom() == from && ticket.getTo() == to) {
                Ticket[] tmp = new Ticket[foundTickets.length + 1];
                for (int i = 0; i < foundTickets.length; i++) {
                    tmp[i] = foundTickets[i];
                }
                tmp[tmp.length - 1] = ticket;
                foundTickets = tmp;
            }
        }
        Arrays.sort(foundTickets);
        return foundTickets;
    }


}

