package com.is3102.controller;

import com.is3102.entity.Message;
import com.is3102.service.MessageService;
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
public class MessageController implements Serializable  {

    private @Inject MessageService das;
    // Selected messages that will be removed
    private Message[] selectedMessages;
    // Lazy loading message list
    private LazyDataModel<Message> lazyModel;
    // Creating new message
    private Message newMessage = new Message();
    // Selected message that will be updated
    private Message selectedMessage = new Message();

    private Logger logger = new Logger();

    /**
     * Default constructor
     */
    public MessageController() {
        logger.log("Message Controller Constructor");
    }

    /**
     * init
     */
    @PostConstruct
    public void init(){
        System.out.println("postConstruct " +  (das == null ? "null" : "notnull"));
        lazyModel = new LazyMessageDataModel(das);
    }

    /**
     * Create, Update and Delete operations
     */
    public void doCreateMessage() {
        System.out.println("doCreateMessage");
        das.create(newMessage);
    }

    /**
     *
     * @param actionMessage
     */
    public void doUpdateMessage(ActionEvent actionMessage){
        System.out.println("doUpdateMessage");

        das.update(selectedMessage);
    }

    /**
     *
     * @param actionMessage
     */
    public void doDeleteMessages(ActionEvent actionMessage){
        das.deleteItems(selectedMessages);
    }

    /**
     * Getters, Setters
     * @return
     */

    public Message getSelectedMessage() {
        return selectedMessage;
    }

    public void setSelectedMessage(Message selectedMessage) {
        this.selectedMessage = selectedMessage;
    }

    public Message[] getSelectedMessages() {
        return selectedMessages;
    }

    public void setSelectedMessages(Message[] selectedMessages) {
        this.selectedMessages = selectedMessages;
    }

    public Message getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(Message newMessage) {
        this.newMessage = newMessage;
    }

    public LazyDataModel<Message> getLazyModel() {
        return lazyModel;
    }

    public MessageService getDas() {
        return das;
    }

    public void setDas(MessageService das) {
        this.das = das;
    }

    public void setLazyModel(LazyDataModel<Message> lazyModel) {
        this.lazyModel = lazyModel;
    }
}
                    