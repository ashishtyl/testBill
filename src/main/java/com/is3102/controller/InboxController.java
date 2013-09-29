package com.is3102.controller;

import com.is3102.entity.Inbox;
import com.is3102.service.InboxService;
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
public class InboxController implements Serializable  {

    private @Inject InboxService das;
    // Selected inboxs that will be removed
    private Inbox[] selectedInboxs;
    // Lazy loading inbox list
    private LazyDataModel<Inbox> lazyModel;
    // Creating new inbox
    private Inbox newInbox = new Inbox();
    // Selected inbox that will be updated
    private Inbox selectedInbox = new Inbox();

    private Logger logger = new Logger();

    /**
     * Default constructor
     */
    public InboxController() {
        logger.log("Inbox Controller Constructor");
    }

    /**
     * init
     */
    @PostConstruct
    public void init(){
        System.out.println("postConstruct " +  (das == null ? "null" : "notnull"));
        lazyModel = new LazyInboxDataModel(das);
    }

    /**
     * Create, Update and Delete operations
     */
    public void doCreateInbox() {
        System.out.println("doCreateInbox");
        das.create(newInbox);
    }

    /**
     *
     * @param actionInbox
     */
    public void doUpdateInbox(ActionEvent actionInbox){
        System.out.println("doUpdateInbox");

        das.update(selectedInbox);
    }

    /**
     *
     * @param actionInbox
     */
    public void doDeleteInboxs(ActionEvent actionInbox){
        das.deleteItems(selectedInboxs);
    }

    /**
     * Getters, Setters
     * @return
     */

    public Inbox getSelectedInbox() {
        return selectedInbox;
    }

    public void setSelectedInbox(Inbox selectedInbox) {
        this.selectedInbox = selectedInbox;
    }

    public Inbox[] getSelectedInboxs() {
        return selectedInboxs;
    }

    public void setSelectedInboxs(Inbox[] selectedInboxs) {
        this.selectedInboxs = selectedInboxs;
    }

    public Inbox getNewInbox() {
        return newInbox;
    }

    public void setNewInbox(Inbox newInbox) {
        this.newInbox = newInbox;
    }

    public LazyDataModel<Inbox> getLazyModel() {
        return lazyModel;
    }

    public InboxService getDas() {
        return das;
    }

    public void setDas(InboxService das) {
        this.das = das;
    }

    public void setLazyModel(LazyDataModel<Inbox> lazyModel) {
        this.lazyModel = lazyModel;
    }
}
                    