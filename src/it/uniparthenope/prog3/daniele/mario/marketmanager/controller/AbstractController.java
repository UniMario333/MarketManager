/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.controller;

import javax.swing.JFrame;

/**
 * Fornisce un'interfaccia comune a tutti i {@code Controller} per l'interazione
 * con la view specificata.
 * 
 * @param <T> la view da associare al {@code Controller}
 * 
 * @author Mario Daniele 0124001808
 * @since 1.0
 * @version 1.0
 */
public abstract class AbstractController<T extends JFrame> {
	
    /** La view con cui il {@code Controller} interagirà */
    protected final T view;
    
    /**
     * Inizializza un {@code Controller} per la view specificata.
     * 
     * @param frame l'istanza della view da associare al {@code Controller}
     */
    public AbstractController(T frame) {
        
        this.view = frame;
        
    }
    
}
