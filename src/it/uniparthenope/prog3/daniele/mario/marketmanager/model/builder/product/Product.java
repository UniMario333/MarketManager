/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.model.builder.product;

/**
 * Rappresenta un prodotto.
 * 
 * @author Mario Daniele 0124001808
 * @since 1.0
 * @version 1.0
 */
public class Product {
    
    /** Nome del prodotto */
    private String name;
    /** Quantità del prodotto */
    private int quantity;
    /** Prezzo del prodotto */
    private float price;
    /** Categoria del prodotto */
    private String category;
    /** Descrizione della categoria */
    private String catDescription;
    
    /**
     * Ottiene il nome del prodotto.
     * 
     * @return il nome del prodotto
     */
    public String getName(){
                     
        return this.name;
                     
    }

    /**
     * Ottiene la quantità del prodotto.
     * 
     * @return la quantità del prodotto
     */
    public int getQuantity() {
                     
        return this.quantity;
                      
    }
    
    /**
     * Ottiene il prezzo del prodotto.
     * 
     * @return il prezzo del prodotto
     */
    public float getPrice() {
        
        return this.price;
                      
    }
    
    /**
     * Ottiene la categoria del prodotto.
     * 
     * @return la categoria del prodotto
     */
    public String getCategory() {
                     
        return this.category;
                      
    }
    
    /**
     * Ottiene la descrizione della categoria.
     * 
     * @return la descrizione della categoria
     */
    public String getCatDescription() {
                     
        return this.catDescription;
                     
    }
    
    /**
     * Imposta la quantità del prodotto.
     * 
     * @param pQuantity la quantità del prodotto
     */
    public void setQuantity(int pQuantity) {
                     
        this.quantity = pQuantity;
                      
    }
    
    /**
     * Il concrete builder per i prodotti.
     * 
     * @author Mario Daniele 0124001808
     * @since 1.0
     * @version 1.0
     */
    public static class ProductBuilder {
        
        private String name;
        private int quantity;
        private float price;
        private String category;
        private String catDescription;
        
        /**
         * Costruttore del builder.
         * 
         * @param pName il nome del prodotto
         */
        public ProductBuilder(String pName) {
            
            this.name = "";
            this.quantity = 0;
            this.price = 0F;
            this.category = "";
            this.catDescription = "";
            
        }

        /**
         * Costruisce un oggetto categoria.
         * 
         * @return un oggetto prodotto
         */
        public Product build(){
                     
            Product product = new Product();
                     
            product.name = this.name;
            product.quantity = this.quantity;
            product.price = this.price;
            product.category = this.category;
            product.catDescription = this.catDescription;
                     
            return product;
                     
        }

        /**
         * Imposta il nome della categoria.
         * 
         * @param pName il nuovo nome del prodotto
         * @return il nuovo nome del prodotto
         */
        public ProductBuilder setName(String pName) {
                     
            this.name = pName;
                     
            return this;
                     
        }
        
        /**
         * Imposta la quantità del prodotto.
         * 
         * @param pQuantity la nuova quantità del prodotto
         * @return la nuova quantità del prodotto
         */
        public ProductBuilder setQuantity(int pQuantity) {
                     
            this.quantity = pQuantity;
                      
            return this;
                      
        }
        
        /**
         * Imposta il prezzo del prodotto.
         * 
         * @param pPrice il nuovo prezzo del prodotto
         * @return la nuova quantità del prodotto
         */
        public ProductBuilder setPrice(float pPrice) {
                     
            this.price = pPrice;
                      
            return this;
                      
        }
        
        /**
         * Imposta la categoria del prodotto.
         * 
         * @param pCategory la nuova categoria del prodotto
         * @return la nuova categoria del prodotto.
         */
        public ProductBuilder setCategory(String pCategory) {
                     
            this.category = pCategory;
                      
            return this;
                      
        }
        
        /**
         * Imposta la descrizione della categoria del prodotto.
         * 
         * @param pCatDescription la nuova descrizione della categoria del prodotto
         * @return la nuova descrizione della categoria del prodotto
         */
        public ProductBuilder setCatDescription(String pCatDescription) {
                     
            this.catDescription = pCatDescription;
                     
            return this;
                     
        }
    
    }
    
    @Override
    public String toString() {
        
        return name + " " + quantity + " " + price + " " + category + " " + catDescription;
      
    }
    
}