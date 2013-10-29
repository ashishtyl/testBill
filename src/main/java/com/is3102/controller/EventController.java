package com.is3102.controller;

import com.is3102.entity.Event;
import com.is3102.service.EventService;
import com.is3102.util.Logger;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class EventController implements Serializable  {

    private @Inject EventService das;
    // Selected events that will be removed
    private Event[] selectedEvents;
    // Lazy loading event list
    private LazyDataModel<Event> lazyModel;
    // Creating new event
    private Event newEvent = new Event();
    // Selected event that will be updated
    private Event selectedEvent = new Event();

    private Logger logger = new Logger();

    /**
     * Default constructor
     */
    public EventController() {
        logger.log("Event Controller Constructor");
    }

    /**
     * init
     */
    @PostConstruct
    public void init(){
        System.out.println("postConstruct " +  (das == null ? "null" : "notnull"));
        lazyModel = new LazyEventDataModel(das);
    }

    /**
     * Create, Update and Delete operations
     */
    public void doCreateEvent() {
        System.out.println("doCreateEvent");
        das.create(newEvent);
    }

    /**
     *
     * @param actionEvent
     */
    public void doUpdateEvent(ActionEvent actionEvent){
        System.out.println("doUpdateEvent");

        das.update(selectedEvent);
    }

    /**
     *
     * @param actionEvent
     */
    public void doDeleteEvents(ActionEvent actionEvent){
        das.deleteItems(selectedEvents);
    }

    public void shareEvent(ActionEvent actionEvent){
        das.shareEvents(this.getSelectedEvents());
    }

    /**
     * Getters, Setters
     * @return
     */

    public Event getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public Event[] getSelectedEvents() {
        return selectedEvents;
    }

    public void setSelectedEvents(Event[] selectedEvents) {
        this.selectedEvents = selectedEvents;
    }

    public Event getNewEvent() {
        return newEvent;
    }

    public void setNewEvent(Event newEvent) {
        this.newEvent = newEvent;
    }

    public LazyDataModel<Event> getLazyModel() {
        return lazyModel;
    }

    public EventService getDas() {
        return das;
    }

    public void setDas(EventService das) {
        this.das = das;
    }

    public void setLazyModel(LazyDataModel<Event> lazyModel) {
        this.lazyModel = lazyModel;
    }
}
                    