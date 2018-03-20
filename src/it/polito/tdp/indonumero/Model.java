package it.polito.tdp.indonumero;

import java.security.InvalidParameterException;

public class Model {
	
	//IL MODELLO SE C'è ERRORE MANDA UN CODICE D'ERRORE, POI IL CONTROLLER DECIDERà COSA FARE
	//IDEM PER LA LINGUA, MODEL MANTIENE LE REGOLE DEL GIOCO ECC, IL CONTROLLER GESTISCE LA LINGUA
	private int NMAX = 100 ;
	private int TMAX = 7 ;
	
	private int segreto ; // numero da indovinare
	private int tentativi ; // tentativi già fatti
	
	private boolean inGame;
	
	public Model() {
		this.inGame=false;
	}
	/**
	 * Avvia una nuova partita, generando un nuvo numero segreto.
	 */
	public void newGame() {
		
		this.segreto = (int)(Math.random()*NMAX)+1 ;
    	
    	this.tentativi = 0 ;
    	this.inGame = true ;
    	
	}
	/**
	 * Controlla se il tentativo fornito rispetta le regole formali del gioco, cioè è nel range[1,NMAX]
	 * @param tentativo
	 * @return {@code true} se il tentativo è valido
	 */
	public boolean valoreValido( int tentativo) {
		return tentativo>=1 && tentativo<=this.NMAX;
	}
	
	
	public int getSegreto() {
		return segreto;
	}
	/**
	 * Fai un tentativo per indovinare il numero segreto
	 * @param t valore numerico del tentativo
	 * @return 0 se è indovinato, +1 se troppo grande, -1 se troppo piccolo
	 */
	public int tentativo(int t) { 
		
		//1) non si può fare tentativo se è esaurito il numero di tentativi possibili o non sono in gioco==>ECCEZIONE
		
		if(!inGame) {
			throw new IllegalStateException("Partita non attiva");
			//messaggio che appare se controller non blocca questa eventualità,
			//chiamando tentativo anche quando partita non attiva
		}
		
		//2) non si possono fare tentativi con valori fuori range==>DEVE GENERARE ERRORE
		
		if(!valoreValido(t)) {
			throw new InvalidParameterException("Tentativo fuori range");
		}
		
		this.tentativi++;
		if(this.tentativi==this.TMAX) {//esauriti i tentativi==>FINE PARTITA
			this.inGame = false;
		}
		if(t==this.segreto) {
			this.inGame = false;
			return 0; //indovina=>FINE PARTITA
		}if(t<this.segreto) {
			return -1;
		}return +1;
	}
	
	public int getTentativi() {
		return this.tentativi;
	}

	public int getNMAX() {
		return NMAX;
	}
	public int getTMAX() {
		return TMAX;
	}
	public boolean isInGame() {
		return inGame;
	}


}
