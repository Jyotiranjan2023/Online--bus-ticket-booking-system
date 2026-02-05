package com.busbooking.service;

import com.busbooking.entity.Booking;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;

public class TicketPdfService {

    public static byte[] generateTicketPdf(Booking booking) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, out);

            document.open();

            document.add(new Paragraph("BUS TICKET"));
            document.add(new Paragraph("----------------------------"));
            document.add(new Paragraph("Ticket No: " + booking.getTicketNumber()));
            document.add(new Paragraph("Status: " + booking.getStatus()));
            document.add(new Paragraph("Passenger: " + booking.getUser().getEmail()));
            document.add(new Paragraph("Route: " +
                    booking.getSchedule().getRoute().getSource() + " → " +
                    booking.getSchedule().getRoute().getDestination()));
            document.add(new Paragraph("Date: " + booking.getSchedule().getTravelDate()));
            document.add(new Paragraph("Departure: " + booking.getSchedule().getDepartureTime()));
            document.add(new Paragraph("Seats: " +
                    booking.getSeats().stream()
                            .map(s -> String.valueOf(s.getSeatNumber()))
                            .toList()));

            document.close();

        } catch (Exception e) {
            throw new RuntimeException("PDF generation failed");
        }

        return out.toByteArray();
    }
}
